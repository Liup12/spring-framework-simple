package com.xuande.spring.core.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author : xuande
 * @date : 2022-02-19 14:15
 **/
public class FileSystemResource implements Resource{

    private final String path;

    private final File file;

    public FileSystemResource(String path) {
        this.path = path;
        this.file = new File(path);
    }

    public FileSystemResource(File file) {
        this.file = file;
        this.path = file.getPath();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return new FileInputStream(path);
    }

    public String getPath() {
        return path;
    }
}
