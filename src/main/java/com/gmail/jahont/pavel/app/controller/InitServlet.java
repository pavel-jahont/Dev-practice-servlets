package com.gmail.jahont.pavel.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.jahont.pavel.app.constant.SqlConstant;
import com.gmail.jahont.pavel.app.service.InitDatabaseService;
import com.gmail.jahont.pavel.app.service.TableService;
import com.gmail.jahont.pavel.app.service.impl.InitDatabaseServiceImpl;
import com.gmail.jahont.pavel.app.service.impl.TableServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InitServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    private static InitDatabaseService initDatabaseService = InitDatabaseServiceImpl.getInstance();
    private static TableService tableService = TableServiceImpl.getInstance();

    @Override
    public void init() {
        initDatabaseService.createDatabase(SqlConstant.DATABASE_9_10);
        logger.info("Delete all tables: ");
        tableService.deleteAllTables();
        logger.info("Create all tables: ");
        tableService.createAllTables();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (PrintWriter out = resp.getWriter()) {
            out.println("<html><body>");
            out.println("Tables has deleted and than has created... ");
            out.println("</body></html>");
        }
    }
}