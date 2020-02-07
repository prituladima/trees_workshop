package com.prituladima.trees;

import com.prituladima.trees.util.VariableSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.Stream;

import static com.prituladima.trees.util.ParametrizedArgumentSupplier.*;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class SubRangeTest {

    public static Stream<Arguments> ARGUMENTS = fromFile(SUB_RANGE).get();

    @ParameterizedTest
    @VariableSource("ARGUMENTS")
    void test_sub_range(File testCaseFile) throws FileNotFoundException {
        try (Scanner inScanner = new Scanner(testCaseFile)) {

            int size = inScanner.nextInt();

            int[] array = new int[size];

            for (int j = 0; j < size; j++) {
                array[j] = inScanner.nextInt();
            }

            int amountOfRequests = inScanner.nextInt();

            int[] fromInclusive = new int[amountOfRequests];
            int[] toExclusive = new int[amountOfRequests];
            int[] expectedRangeSize = new int[amountOfRequests];

            for (int j = 0; j < amountOfRequests; j++) {
                fromInclusive[j] = inScanner.nextInt();
                toExclusive[j] = inScanner.nextInt();
                expectedRangeSize[j] = inScanner.nextInt();
            }

            test_sub_range(size, array, amountOfRequests, fromInclusive, toExclusive, expectedRangeSize);

        }
    }


    void test_sub_range(int size, int[] sortedArray, int amountOfRequests, int[] fromInclusive, int[] toExclusive, int[] expectedIndexes) {
        assertTimeout(ofSeconds(1), () -> {
            BinarySearch binarySearch = new BinarySearch();
            for (int i = 0; i < amountOfRequests; i++) {
                assertEquals(expectedIndexes[i], binarySearch.rangeSize(sortedArray, fromInclusive[i], toExclusive[i]));
            }
        });
    }

}
