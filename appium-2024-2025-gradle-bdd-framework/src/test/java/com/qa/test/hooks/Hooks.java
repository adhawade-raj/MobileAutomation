package com.qa.test.hooks;

import com.qa.factory.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;

public class Hooks {

    static final Logger logger = LogManager.getLogger(Hooks.class);

    @BeforeClass
    public static void setup() {
        logger.info("******** Initializing Driver ********");
        DriverFactory driverFactory = new DriverFactory();
        driverFactory.initDriver();  // Ensure driver initialization is done before tests
    }

}
