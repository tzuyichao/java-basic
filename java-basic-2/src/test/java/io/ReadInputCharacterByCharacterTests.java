package io;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * Source: https://www.baeldung.com/java-read-input-character
 */
public class ReadInputCharacterByCharacterTests {
    @Test
    void givenInputFromConsole_whenUsingBufferedStream_thenReadCharByChar() throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream("TestInput".getBytes());
        System.setIn(inputStream);

        try(BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in))) {
            char[] result = new char[9];
            int index = 0;
            int c;
            while((c = buffer.read()) != -1) {
                result[index++] = (char) c;
            }

            assertArrayEquals("TestInput".toCharArray(), result);
        }
    }
}
