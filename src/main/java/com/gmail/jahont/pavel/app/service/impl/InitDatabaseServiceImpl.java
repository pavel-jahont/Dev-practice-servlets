package com.gmail.jahont.pavel.app.service.impl;

import java.lang.invoke.MethodHandles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gmail.jahont.pavel.app.constant.SqlConstant;
import com.gmail.jahont.pavel.app.repository.ConnectionRepository;
import com.gmail.jahont.pavel.app.repository.impl.ConnectionRepositoryImpl;
import com.gmail.jahont.pavel.app.service.InitDatabaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InitDatabaseServiceImpl implements InitDatabaseService {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static InitDatabaseServiceImpl instance;
    private ConnectionRepository connectionRepository = ConnectionRepositoryImpl.getInstance();

    private InitDatabaseServiceImpl() {
    }

    public static InitDatabaseServiceImpl getInstance() {
        if (instance == null) {
            instance = new InitDatabaseServiceImpl();
        }
        return instance;
    }

    @Override
    public boolean isDatabaseExist(String databaseName) {
        boolean isDbExist = false;
        try (
                Connection connection = connectionRepository.getConnection();
                PreparedStatement statement = connection.prepareStatement(SqlConstant.SHOW_DATABASES);
                ResultSet result = statement.executeQuery();) {
            while (result.next()) {
                if (result.getString("Database").equals(databaseName)) {
                    isDbExist = true;
                }
            }
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
            isDbExist = false;
        }
        return isDbExist;
    }

    @Override
    public void createDatabase(String databaseName) {
        if (isDatabaseExist(databaseName)) {
            dropDatabase(databaseName);
        }
        try (
                Connection connection = connectionRepository.getConnection();
                PreparedStatement statement = connection.prepareStatement(SqlConstant.CREATE_DATABASE + " " + databaseName)) {
            statement.execute();
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void dropDatabase(String databaseName) {
        try (
                Connection connection = connectionRepository.getConnection();
                PreparedStatement statement = connection.prepareStatement(SqlConstant.DROP_DATABASE + " " + databaseName)) {
            statement.execute();
        } catch (SQLException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}