package ru.otus.core.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.DbServiceDemo;
import ru.otus.base.AbstractHibernateTest;
import ru.otus.crm.model.Client;
import ru.otus.crm.model.ClientDetails;
import ru.otus.crm.model.Manager;
import ru.otus.crm.repository.ClientRepository;
import ru.otus.crm.repository.ManagerRepository;

import java.util.ArrayList;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = DbServiceDemo.class)
class ManagerRepositoryTest extends AbstractHibernateTest {

    @Autowired
    public ManagerRepositoryTest(ManagerRepository managerRepository, ClientRepository clientRepository) {
        super(managerRepository, clientRepository);
    }

    @Test
    @DisplayName("корректно сохраняет менеджеров и клиентов и находит всех менеджеров")
    void shouldFindAll() {
        //given

        var managerName = "m:" + System.currentTimeMillis();
        var managerSecond = dbServiceManager.saveManager(new Manager(managerName, "ManagerSecond",
                Set.of(new Client("managClient1", managerName, 1, new ClientDetails("inf01")),
                        new Client("managClient2", managerName, 2, new ClientDetails("info2"))),
                new ArrayList<>(), true));

        /// создаем Client
        var firstClient = dbServiceClient.saveClient(
                new Client("dbServiceFirst" + System.currentTimeMillis(), managerSecond.getId(), 1,
                        new ClientDetails("init1")));

        var clientSecond = dbServiceClient.saveClient(
                new Client("dbServiceSecond" + System.currentTimeMillis(), managerSecond.getId(), 2,
                        new ClientDetails("init2")));

        //then
        assertThat(managerRepository.findAll()).isNotNull();
        assertThat(managerRepository.findAll()).hasSizeGreaterThan(1);

    }
}