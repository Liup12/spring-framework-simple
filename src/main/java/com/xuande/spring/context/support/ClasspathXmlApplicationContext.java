package com.xuande.spring.context.support;


/**
 * @author : xuande
 * @date : 2022-02-19 17:54
 **/
public class ClasspathXmlApplicationContext extends AbstractXmlApplicationContext{

    private String [] configLocations;


    public ClasspathXmlApplicationContext() {
    }

    public ClasspathXmlApplicationContext(String configLocation) {
        this(new String[]{configLocation});
    }

    public ClasspathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        refresh();
    }



    @Override
    public String[] getConfigLocations() {
        return this.configLocations;
    }



}
