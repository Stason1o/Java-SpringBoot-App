package com.sbogdanschi.springboot.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class Log4J2YamlConfTest {

    private final static Logger LOGGER = LogManager.getLogger(Log4J2YamlConfTest.class);

    @Test
    public void testPerformSomeTask() throws Exception {
        Log4J2YamlConf log4J2YamlConf = new Log4J2YamlConf();
        log4J2YamlConf.performSomeTask();
        LOGGER.warn("Test succeeded");
    }

}