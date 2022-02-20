package com.xuande.spring.core.io;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author : xuande
 * @date : 2022-02-19 14:14
 **/
public class ClassPathResource implements Resource{

    private final String path;

    private ClassLoader classLoader;




    public ClassPathResource(String path) {
        this(path, null);
    }


    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader =  (classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream resourceAsStream = classLoader.getResourceAsStream(path);

        if (resourceAsStream == null){
            throw new FileNotFoundException(this.path + "cannot be opened because it does not exist");
        }
        return resourceAsStream;
    }
}
