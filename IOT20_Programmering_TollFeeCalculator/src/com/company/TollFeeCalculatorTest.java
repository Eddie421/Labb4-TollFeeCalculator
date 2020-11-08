package com.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;

public class TollFeeCalculatorTest {
    @Test
    @DisplayName("Its testing if getTotalFeeCost returns the correct value")
    public void getTotalFeeCost() {
        LocalDateTime[] testDates = new LocalDateTime[]{
                LocalDateTime.parse("2020-06-30 00:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse("2020-06-30 06:34", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse("2020-06-30 08:52", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse("2020-06-30 10:13", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse("2020-06-30 10:25", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse("2020-06-30 11:04", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse("2020-06-30 16:50", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse("2020-06-30 18:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse("2020-06-30 21:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse("2020-07-01 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
        };

        int result = TollFeeCalculator.getTotalFeeCost(testDates);
        assertEquals(55, result);

    }

    @Test
    @DisplayName("Its testing if getTollFeePerPassing returns the right value")
    public void getTollFeePerPassing() {
        LocalDateTime dateTime1 = LocalDateTime.parse("2020-06-30 00:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime dateTime2 = LocalDateTime.parse("2020-06-30 06:34", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime dateTime3 = LocalDateTime.parse("2020-06-30 08:52", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime dateTime4 = LocalDateTime.parse("2020-06-30 10:13", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime dateTime5 = LocalDateTime.parse("2020-06-30 10:25", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime dateTime6 = LocalDateTime.parse("2020-06-30 11:04", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime dateTime7 = LocalDateTime.parse("2020-06-30 16:50", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime dateTime8 = LocalDateTime.parse("2020-06-30 18:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime dateTime9 = LocalDateTime.parse("2020-06-30 21:30", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime dateTime10 = LocalDateTime.parse("2020-07-01 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        int result1 = TollFeeCalculator.getTollFeePerPassing(dateTime1);
        int result2 = TollFeeCalculator.getTollFeePerPassing(dateTime2);
        int result3 = TollFeeCalculator.getTollFeePerPassing(dateTime3);
        int result4 = TollFeeCalculator.getTollFeePerPassing(dateTime4);
        int result5 = TollFeeCalculator.getTollFeePerPassing(dateTime5);
        int result6 = TollFeeCalculator.getTollFeePerPassing(dateTime6);
        int result7 = TollFeeCalculator.getTollFeePerPassing(dateTime7);
        int result8 = TollFeeCalculator.getTollFeePerPassing(dateTime8);
        int result9 = TollFeeCalculator.getTollFeePerPassing(dateTime9);
        int result10 = TollFeeCalculator.getTollFeePerPassing(dateTime10);

        assertEquals(0, result1);
        assertEquals(13, result2);
        assertEquals(8, result3);
        assertEquals(8, result4);
        assertEquals(8, result5);
        assertEquals(8, result6);
        assertEquals(18, result7);
        assertEquals(8, result8);
        assertEquals(0, result9);
        assertEquals(0, result10);
    }

    @Test
    @DisplayName("Testing the NullPointerException is not thrown")
    public void constructorCatchesException() {
        try {
            TollFeeCalculator testCalculater = new TollFeeCalculator(null);
        } catch (NullPointerException e) {
            fail();
        }
    }

    @Test
    @DisplayName("Testing the output console is correct")
    public void printsCorrectOutput() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        TollFeeCalculator testCalculator = new TollFeeCalculator("testData/Lab4_f.txt");
        String actualOutput = outContent.toString();
        actualOutput = actualOutput.replace("\r\n", "\n");
        String expectedOutput =
                "2020-06-30T00:05\n" +
                        "2020-06-30T06:34\n" +
                        "2020-06-30T08:52\n" +
                        "2020-06-30T10:13\n" +
                        "2020-06-30T10:25\n" +
                        "2020-06-30T11:04\n" +
                        "2020-06-30T16:50\n" +
                        "2020-06-30T18:00\n" +
                        "2020-06-30T21:30\n" +
                        "2020-07-01T00:00\n" +
                        "The total fee for the inputfile is55\n";

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    @DisplayName("Testing the row count that is printed to the console is correct")
    public void testCorrectRowCount() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        TollFeeCalculator testCalculator = new TollFeeCalculator("testData/Lab4_f.txt");
        String actualOutput = outContent.toString();
        actualOutput = actualOutput.replace("\r\n", "\n");
        String[] dateStrings = actualOutput.split("\n");

        assertEquals(11, dateStrings.length);
    }

    @Test
    @DisplayName("Its testing all date time rows are printed to the console")
    public void testCorrectDateTimeCount() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        TollFeeCalculator testCalculator = new TollFeeCalculator("testData/Lab4_f.txt");
        String actualOutput = outContent.toString();
        actualOutput = actualOutput.replace("\r\n", "\n");
        String[] dateStrings = actualOutput.split("\n");
        String nextTolast = dateStrings[dateStrings.length - 2];

        assertEquals("2020-07-01T00:00", nextTolast);
    }

    @Test
    @DisplayName("Testing that NoSuchElementException is not thrown if the file is empty")
    public void catchesExceptionIfFileIsEmpty() {
        try {
            TollFeeCalculator testCalculator = new TollFeeCalculator("testData/lab4_empty.txt");
        } catch (NoSuchElementException e) {
            fail();
        }
    }

    @Test
    @DisplayName("Testing that DateTimeParseException is not thrown if the file contains incorrect format")
    public void catchesExceptionIfInputFileDosentContainDayTime() {
        try {
            TollFeeCalculator testCalulator = new TollFeeCalculator("testData/lab4_incorrectformat.txt");
        } catch (DateTimeParseException e) {
            fail();
        }
    }

    @Test
    @DisplayName("Testing that ArrayIndexOutOfBoundsException is not thrown if this file contains a single date")
    public void catchesExceptionIfSingelDateTime() {
        try {
            TollFeeCalculator testCalulator = new TollFeeCalculator("testData/lab4_onedate.txt");
        } catch (ArrayIndexOutOfBoundsException e) {
            fail();
        }
    }

    @Test
    @DisplayName("Testing that getTotalFeeCost doesn't return more then 60 crowns")
    public void tollFeeCalculatorReturnsMaxs60() {
        LocalDateTime[] testDates = new LocalDateTime[]{
                LocalDateTime.parse("2020-06-30 06:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), // 8 kr
                LocalDateTime.parse("2020-06-30 07:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), // 18 kr
                LocalDateTime.parse("2020-06-30 08:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), // 13 kr
                LocalDateTime.parse("2020-06-30 09:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), // 8 kr
                LocalDateTime.parse("2020-06-30 10:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), // 8
                LocalDateTime.parse("2020-06-30 11:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), // 8 kr
                LocalDateTime.parse("2020-06-30 12:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), // 8
                LocalDateTime.parse("2020-06-30 13:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), // 8 kr
                LocalDateTime.parse("2020-06-30 14:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), // 8 kr
                LocalDateTime.parse("2020-06-30 15:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), // 13 kr
        };

        int result = TollFeeCalculator.getTotalFeeCost(testDates);
        assertEquals(60, result);
    }
}

