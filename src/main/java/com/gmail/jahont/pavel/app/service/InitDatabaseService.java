package com.gmail.jahont.pavel.app.service;

public interface InitDatabaseService {

    boolean isDatabaseExist(String databaseName);

    void createDatabase(String databaseName);

    void dropDatabase(String databaseName);
}
