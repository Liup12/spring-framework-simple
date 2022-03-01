package com.xuande.spring.beans.factory;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.PropertyValue;
import com.xuande.spring.beans.PropertyValues;
import com.xuande.spring.beans.factory.config.BeanDefinition;
import com.xuande.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.xuande.spring.beans.factory.config.BeanPostProcessor;
import com.xuande.spring.core.io.DefaultResourceLoader;
import com.xuande.spring.core.io.Resource;
import com.xuande.spring.util.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/1  10:29:18
 */
public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    /**
     * Default placeholder prefix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * Default placeholder suffix: {@value}
     */
    public static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;


    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 加载属性文件
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);

                PropertyValues propertyValues = beanDefinition.getPropertyValues();
                for (PropertyValue propertyValue : propertyValues.getPropertyValueList()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) {
                        continue;
                    }
                    value = resolvePlaceholder((String) value, properties);

                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), value));

                }
            }
            PlaceholderResolvingStringValueResolver stringValueResolver = new PlaceholderResolvingStringValueResolver(properties);
            beanFactory.addEmbeddedValueResolver(stringValueResolver);

        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    private String resolvePlaceholder(String value, Properties properties) {
        String strVal = value;
        StringBuilder buffer = new StringBuilder(strVal);
        int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
            String propKey = strVal.substring(startIdx + 2, stopIdx);
            String propVal = properties.getProperty(propKey);
            buffer.replace(startIdx, stopIdx + 1, propVal);
        }
        return buffer.toString();
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private class PlaceholderResolvingStringValueResolver implements StringValueResolver{

        private final Properties properties;

        public PlaceholderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolverStringValue(String strValue) {
            return PropertyPlaceholderConfigurer.this.resolvePlaceholder(strValue, properties);
        }
    }
}
