package com.Cinema_dti_java.CRUD_SQLite.service;

import org.springframework.stereotype.Component;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

@Component
public class LogControlService {

    private boolean debugMode = false;

    public void enableDebug() {
        setHibernateLogLevel(Level.DEBUG);
        debugMode = true;
        System.out.println(" Modo DEBUG ativado - Queries SQL serão exibidas");
    }


    public void disableDebug() {
        setHibernateLogLevel(Level.WARN);
        debugMode = false;
        System.out.println("Modo DEBUG desativado");
    }

    public boolean isDebugEnabled() {
        return debugMode;
    }

    private void setHibernateLogLevel(Level level) {

        Logger logger = (Logger) LoggerFactory.getLogger("org.hibernate.SQL");
        logger.setLevel(level);

        Logger paramLogger = (Logger) LoggerFactory.getLogger("org.hibernate.type.descriptor.sql.BasicBinder");
        paramLogger.setLevel(level);
    }
}