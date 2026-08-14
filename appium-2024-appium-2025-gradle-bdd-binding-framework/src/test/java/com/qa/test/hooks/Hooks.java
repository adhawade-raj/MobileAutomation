package com.qa.test.hooks;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.qa.test.config.TestModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class Hooks {
    private static Injector injector;
    static final Logger logger = LogManager.getLogger(Hooks.class);

    @BeforeClass
    public static void setup() {
        injector = Guice.createInjector(new TestModule());
        logger.info("******** Initializing Driver ********");
    }

    public static Injector getInjector() {
        return injector;
    }
}
