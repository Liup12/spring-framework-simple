package com.xuande.spring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author : xuande
 * @date : 2022-02-19 14:09
 **/
public interface Resource {

    InputStream getInputStream() throws IOException;
}
