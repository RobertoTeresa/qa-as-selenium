package com.as.digital.utils;

public class Flags {

    /** Variables */

    private static final String BROWSER = "browser";
    private static final String PROXY = "proxy";
    private static final String DOCKER = "docker";
    private final String browser = System.getProperty(BROWSER);
    private final String proxy = System.getProperty(PROXY);
    private final boolean isDocker = this.parseBoolean(System.getProperty(DOCKER));
    private static Flags instance;

    /** Methods */

    private boolean parseBoolean(String string) {
        String result = (string == null) ? "false" : string;
        result = result.toLowerCase().trim();
        return (result.equals("true") || result.equals("false")) && Boolean.parseBoolean(result);
    }

    public static Flags getInstance() {
        if (instance == null) { instance = new Flags(); }
        return instance;
    }

    public String getBrowser() { return this.browser; }

    public String getProxy() { return this.proxy; }

    public boolean isDocker() {
        return this.isDocker;
    }
}