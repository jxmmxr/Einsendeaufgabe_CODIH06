package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PrimeFactorsTest {
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void before() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void after() {
        System.setOut(originalOut);
    }

    @ParameterizedTest(name = "primeFactors({0}) -> \"{1}\"")
    @CsvSource({
            "16, '2 2 2 2'",
            "20, '2 2 5'",
            "64, '2 2 2 2 2 2'",
            "9,  '3 3'",
            "19, '19'",
            "18, '2 3 3'",
            "28, '2 2 7'",
            "1,  ''",
            "-1, ''",
            "0,  ''"
    })
    void primeFactors_prints_expected_factors(int input, String expected) {
        outContent.reset();

        assertTimeoutPreemptively(Duration.ofMillis(200), () ->
                PrimeFactors.primeFactors(input)
        );

        String output = outContent.toString().replace("\r\n", "\n").trim();

        assertEquals(expected, output);
    }
}