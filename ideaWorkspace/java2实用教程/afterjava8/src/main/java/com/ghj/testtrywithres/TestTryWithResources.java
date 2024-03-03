package com.ghj.testtrywithres;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * Description:
 * <p>
 *
 * @author guohuanjun1
 * @date 2024/2/25 19:45
 */
public class TestTryWithResources {
    public static void main(String[] args) throws IOException {
        try (InputStream inputStream = new ByteArrayInputStream(new byte[]{1,3,4})) {
        }
    }
}
