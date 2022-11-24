package com.vow.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: wushaopeng
 * @date: 2022/11/24 9:39
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
