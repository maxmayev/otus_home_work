package ru.otus.homework.crm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.homework.cachehw.HwCache;
import ru.otus.homework.cachehw.HwListener;
import ru.otus.homework.cachehw.MyCache;
import ru.otus.homework.core.repository.DataTemplate;
import ru.otus.homework.core.sessionmanager.TransactionRunner;
import ru.otus.homework.crm.model.Manager;

import java.util.List;
import java.util.Optional;


public class DbServiceManagerCachedImpl implements DBServiceManager {
    private static final Logger log = LoggerFactory.getLogger(DbServiceManagerCachedImpl.class);

    private final DataTemplate<Manager> managerDataTemplate;
    private final TransactionRunner transactionRunner;
    private final HwCache<Long, Manager> cache;

    public DbServiceManagerCachedImpl(TransactionRunner transactionRunner, DataTemplate<Manager> managerDataTemplate) {
        this.transactionRunner = transactionRunner;
        this.managerDataTemplate = managerDataTemplate;
        cache = new MyCache<>();
        HwListener<Long, Manager> listener = (key, value, action) -> log.info("key:{}, value:{}, action: {}", key, value, action);
        cache.addListener(listener);
    }

    @Override
    public Manager saveManager(Manager manager) {
        return transactionRunner.doInTransaction(connection -> {
            if (manager.getNo() == null) {
                var managerNo = managerDataTemplate.insert(connection, manager);
                var createdManager = new Manager(managerNo, manager.getLabel(), manager.getParam1());
                log.info("created manager: {}", createdManager);

                cache.put(managerNo,createdManager);
                return createdManager;
            }
            managerDataTemplate.update(connection, manager);
            log.info("updated manager: {}", manager);

            cache.remove(manager.getNo());
            return manager;
        });
    }

    @Override
    public Optional<Manager> getManager(long no) {
        try {
           return Optional.of(cache.get(no));
        } catch (NoSuchFieldError exception) {
            return transactionRunner.doInTransaction(connection -> {
                var managerOptional = managerDataTemplate.findById(connection, no);
                log.info("manager: {}", managerOptional);

                if(managerOptional.isPresent()){
                    Manager manager = managerOptional.get();
                    cache.put(manager.getNo(),manager);
                }
                return managerOptional;
            });
        }
    }

    @Override
    public List<Manager> findAll() {
        return transactionRunner.doInTransaction(connection -> {
            var managerList = managerDataTemplate.findAll(connection);
            log.info("managerList:{}", managerList);
            if(!managerList.isEmpty()){
                managerList.forEach(manager -> cache.put(manager.getNo(),manager));
            }
            return managerList;
        });
    }
}
