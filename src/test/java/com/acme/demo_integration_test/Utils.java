package com.acme.demo_integration_test;



import com.google.common.io.Resources;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Utils {
    public static String getResource(String path)  {
        try {
            return Resources.toString(
                    Resources.getResource(path),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
