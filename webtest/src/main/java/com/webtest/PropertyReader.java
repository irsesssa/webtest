package com.webtest;


import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

    public static String getUrl(String parameter) throws Exception {
        Properties properties = new Properties();
        FileInputStream fileInputStream=new FileInputStream("C:\\Users\\iraci\\IdeaProjects\\webtest\\src\\main\\resources\\config.properties");
        properties.load(fileInputStream);
        return properties.getProperty(parameter);
    }
}
