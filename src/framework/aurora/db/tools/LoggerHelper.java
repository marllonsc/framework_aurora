package framework.aurora.db.tools;

import framework.aurora.db.connection.DataBaseConfiguration;

import java.util.logging.Logger;
import java.util.logging.Level;

public class LoggerHelper {

    private static Logger LOGGER;

    public LoggerHelper(String name){
        LOGGER = Logger.getLogger(name);
    }

    public void info(String msg){
        LOGGER.log(Level.INFO, msg);
    }

    public void warning(String msg){
        LOGGER.log(Level.WARNING, msg);
    }

    public void error(String msg){
        LOGGER.log(Level.SEVERE, msg);
    }

    public void fine(String msg){
        LOGGER.log(Level.FINE, msg);
    }

}
