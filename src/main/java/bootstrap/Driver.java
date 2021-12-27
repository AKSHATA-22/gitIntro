package bootstrap;

import java.util.*;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.apache.log4j.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import java.util.concurrent.*;


public class Driver {
    public static Logger logger = LoggerFactory.getLogger(Driver.class);
    public static void main(String[] args) {
        configureLogging("var/log/gitIntro/introToGit","INFO");
        System.out.println("Hello world");
        logger.info("Program started");
    }
    public static String configureLogging(String logFile, String logLevel){
        DailyRollingFileAppender dailyRollingFileAppender = new DailyRollingFileAppender();
        String logFileName = "";
        switch (logLevel){
            case "DEBUG":{
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.DEBUG_INT));
            }
            case "WARN":{
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.WARN_INT));
            }
            case "ERROR":{
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.ERROR_INT));
            } default:{
                dailyRollingFileAppender.setThreshold(Level.toLevel(Priority.INFO_INT));
            }
            break;
        }
        System.out.println("Log files written out at "+logFile);
        dailyRollingFileAppender.setFile(logFile);
        dailyRollingFileAppender.setLayout(new EnhancedPatternLayout("%-6d [%25.35t] %-5p %40.80c - %m%n"));
        dailyRollingFileAppender.activateOptions();
        org.apache.log4j.Logger.getRootLogger().addAppender(dailyRollingFileAppender);
        return dailyRollingFileAppender.getFile();
    }
}
