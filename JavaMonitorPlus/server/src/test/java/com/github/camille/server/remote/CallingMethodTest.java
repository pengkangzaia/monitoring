package com.github.camille.server.remote;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CallingMethodTest {

    @Test
    public void getVersion() throws IOException {
        String version = CallingMethod.getVersion("http://116.85.23.6:8081");
        System.out.println(version);
        Assert.assertNotNull(version);
    }


}