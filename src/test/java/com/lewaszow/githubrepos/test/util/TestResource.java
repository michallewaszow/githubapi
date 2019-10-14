package com.lewaszow.githubrepos.test.util;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class TestResource {

    public String getResourceAsString(final String fileName, final String encoding) throws IOException {
        final InputStream resourceAsStream = getClass().getResourceAsStream(fileName);
        return IOUtils.toString(resourceAsStream, encoding);
    }

}
