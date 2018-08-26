package com.eviscerator.quoters;

import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

public class PropertyFailApplicationContext extends GenericApplicationContext {
    public PropertyFailApplicationContext(String fileName) {
        PropertiesBeanDefinitionReader reader = new PropertiesBeanDefinitionReader(this);
        int i = reader.loadBeanDefinitions(fileName);
        System.out.println("Found " + i + " beans");
        refresh();
    }

    public static void main(String[] args) {
        PropertyFailApplicationContext context = new PropertyFailApplicationContext("context.properties");
        context.getBean(Quoter.class).sayQuote();
    }
}
