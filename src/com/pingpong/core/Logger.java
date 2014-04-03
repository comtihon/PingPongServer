package com.pingpong.core;

/**
 * Created by tihon on 03.04.14.
 */
public class Logger {

    public static void e(String msg, Object... args) {
        org.apache.log4j.Logger.getLogger(getCallerClassName()).error(format(msg, args));
    }

    public static void w(String msg, Object... args) {
        org.apache.log4j.Logger.getLogger(getCallerClassName()).warn(format(msg, args));
    }

    public static void i(String msg, Object... args) {
        org.apache.log4j.Logger.getLogger(getCallerClassName()).info(format(msg, args));
    }

    public static void d(String msg, Object... args) {
        org.apache.log4j.Logger.getLogger(getCallerClassName()).debug(format(msg, args));
    }


    /**
     * @return who called the logger
     */
    private static String getCallerClassName() {
        final int level = 5;
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        StackTraceElement caller = callStack[level];
        return caller.getClassName();
    }

    /**
     * Try to format messages using java Formatter.
     * Fall back to the plain message if error.
     */
    private static String format(String msg, Object... args) {
        try {
            if (args != null && args.length > 0) {
                return String.format(msg, args);
            }
            return msg;
        } catch (Exception e) {
            return msg;
        }
    }
}
