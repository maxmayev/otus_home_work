package ru.otus.base;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.stat.EntityStatistics;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.otus.core.HibernateUtils;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.Manager;
import ru.otus.crm.repository.ClientRepository;
import ru.otus.crm.repository.ManagerRepository;
import ru.otus.crm.service.DBServiceClient;
import ru.otus.crm.service.DBServiceManager;
import ru.otus.crm.service.DbServiceClientImpl;
import ru.otus.crm.service.DbServiceManagerImpl;
import ru.otus.sessionmanager.TransactionManagerSpring;

import static ru.otus.ActionDemo.HIBERNATE_CFG_FILE;


public abstract class AbstractHibernateTest {
    protected SessionFactory sessionFactory;
    protected TransactionManagerSpring transactionManager;
    protected ManagerRepository managerRepository;
    protected ClientRepository clientRepository;
    protected DBServiceClient dbServiceClient;
    protected DBServiceManager dbServiceManager;


    private static TestContainersConfig.CustomPostgreSQLContainer CONTAINER;

    public AbstractHibernateTest(ManagerRepository managerRepository, ClientRepository clientRepository) {
        this.managerRepository = managerRepository;
        this.clientRepository = clientRepository;
    }

    @BeforeAll
    public static void init() {
        CONTAINER = TestContainersConfig.CustomPostgreSQLContainer.getInstance();
        CONTAINER.start();
    }

    @AfterAll
    public static void shutdown() {
        CONTAINER.stop();
    }

    @BeforeEach
    public void setUp() {
        String dbUrl = System.getProperty("app.datasource.demo-db.jdbcUrl");
        String dbUserName = System.getProperty("app.datasource.demo-db.username");
        String dbPassword = System.getProperty("app.datasource.demo-db.password");

        Configuration configuration = new Configuration().configure(HIBERNATE_CFG_FILE);
        configuration.setProperty("hibernate.connection.url", dbUrl);
        configuration.setProperty("hibernate.connection.username", dbUserName);
        configuration.setProperty("hibernate.connection.password", dbPassword);

        sessionFactory = HibernateUtils.buildSessionFactory(configuration, Client.class, Manager.class);

        transactionManager = new TransactionManagerSpring();
        dbServiceClient = new DbServiceClientImpl(transactionManager, clientRepository);
        dbServiceManager = new DbServiceManagerImpl(managerRepository, transactionManager);
    }

    protected EntityStatistics getUsageStatistics() {
        Statistics stats = sessionFactory.getStatistics();
        return stats.getEntityStatistics(Client.class.getName());
    }
}
