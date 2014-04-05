package com.pingpong.server;

import com.pingpong.core.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by tihon on 03.04.14.
 */
public class Config extends Properties {

    private static final String CONF_BASE_DIR = "conf/";

    private static class SingletonHolder {
        private static final Config instance = new Config();
    }

    private Config() {
    }

    public static Config getInstance() {
        return SingletonHolder.instance;
    }

    public void initServerConf() {
        String fileName = String.format(CONF_BASE_DIR + "server.properties");
        System.out.printf("Loading config properties from: [%s]\n", fileName);
        try {
            load(new FileReader(fileName));
        } catch (IOException e) {
            String msg = "Failed to load config properties: " + e.getMessage();
            System.out.println(msg);
            throw new RuntimeException(msg);
        }
    }

    public void initLoggerConf() {
        try {
            String logFile = getProperty("log4j.path", "log4j.properties");

            String logLevel = getProperty("log_level");
            if (logLevel == null || logLevel.isEmpty())
                logLevel = "INFO";

            PropertyConfigurator.configure(Config.CONF_BASE_DIR + logFile);

            LogManager.getRootLogger().setLevel(Level.toLevel(logLevel));

            Logger.i("Loading log4j from: [%s]", logFile);
            Logger.i("Loglevel was set to: %s", logLevel);
        } catch (Exception e) {
            Logger.w("./log4j.properties does not exists in current dir, use default values");
        }
    }
}
