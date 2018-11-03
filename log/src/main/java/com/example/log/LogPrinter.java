package com.example.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogPrinter {

    public final Logger logger = LoggerFactory.getLogger(LogPrinter.class);

    public void infoLevel() {
        logger.info("this is info log");
    }

    public void debugLevel() {
        logger.debug("this is debug log");
    }
}
