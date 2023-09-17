package ru.otus.homework;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.homework.core.repository.executor.DbExecutorImpl;
import ru.otus.homework.core.sessionmanager.TransactionRunnerJdbc;
import ru.otus.homework.crm.datasource.DriverManagerDataSource;
import ru.otus.homework.crm.model.Client;
import ru.otus.homework.crm.model.Manager;
import ru.otus.homework.crm.service.DbServiceClientCachedImpl;
import ru.otus.homework.crm.service.DbServiceClientImpl;
import ru.otus.homework.crm.service.DbServiceManagerCachedImpl;
import ru.otus.homework.crm.service.DbServiceManagerImpl;
import ru.otus.homework.mapper.*;

import javax.sql.DataSource;

public class HomeWork {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "pg";
    private static final String PASSWORD = "pg";

    private static final Logger log = LoggerFactory.getLogger(HomeWork.class);

    public static void main(String[] args) {
// Общая часть
        var dataSource = new DriverManagerDataSource(URL, USER, PASSWORD);
        flywayMigrations(dataSource);
        var transactionRunner = new TransactionRunnerJdbc(dataSource);
        var dbExecutor = new DbExecutorImpl();

        System.out.println("Executing time cache = " + Benchmark(transactionRunner, dbExecutor, false));
       // System.out.println("Executing time with cache = " + Benchmark(transactionRunner, dbExecutor, true));

    }

    private static Long Benchmark(TransactionRunnerJdbc transactionRunner, DbExecutorImpl dbExecutor, Boolean cache) {
        // Работа с клиентом без кеша
        Long startTime = System.nanoTime();
        EntityClassMetaData entityClassMetaDataClient = new EntityClassMetaDataImpl(Client.class);
        EntitySQLMetaData entitySQLMetaDataClient = new EntitySQLMetaDataImpl(entityClassMetaDataClient);
        var dataTemplateClient = new DataTemplateJdbc<Client>(dbExecutor, entitySQLMetaDataClient, entityClassMetaDataClient); //реализация DataTemplate, универсальная

// Код дальше должен остаться
        var dbServiceClient = cache ? new DbServiceClientCachedImpl(transactionRunner, dataTemplateClient) : new DbServiceClientImpl(transactionRunner, dataTemplateClient);
        dbServiceClient.saveClient(new Client("dbServiceFirst"));

        var clientSecond = dbServiceClient.saveClient(new Client("dbServiceSecond"));
        var clientSecondSelected = dbServiceClient.getClient(clientSecond.getId())
                .orElseThrow(() -> new RuntimeException("Client not found, id:" + clientSecond.getId()));
        log.info("clientSecondSelected:{}", clientSecondSelected);

// Сделайте тоже самое с классом Manager (для него надо сделать свою таблицу) без кеша

        EntityClassMetaData entityClassMetaDataManager = new EntityClassMetaDataImpl(Manager.class);
        EntitySQLMetaData entitySQLMetaDataManager = new EntitySQLMetaDataImpl(entityClassMetaDataManager);
        var dataTemplateManager = new DataTemplateJdbc<Manager>(dbExecutor, entitySQLMetaDataManager, entityClassMetaDataManager);

        var dbServiceManager = cache ? new DbServiceManagerCachedImpl(transactionRunner, dataTemplateManager) : new DbServiceManagerImpl(transactionRunner, dataTemplateManager);
        dbServiceManager.saveManager(new Manager("ManagerFirst"));

        var managerSecond = dbServiceManager.saveManager(new Manager("ManagerSecond"));
        var managerSecondSelected = dbServiceManager.getManager(managerSecond.getNo())
                .orElseThrow(() -> new RuntimeException("Manager not found, id:" + managerSecond.getNo()));
        log.info("managerSecondSelected:{}", managerSecondSelected);
        Long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static void flywayMigrations(DataSource dataSource) {
        log.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        log.info("db migration finished.");
        log.info("***");
    }
}
