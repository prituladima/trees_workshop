package com.prituladima.trees;

import com.prituladima.trees.util.TestCase;
import com.prituladima.trees.util.VariableSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.Stream;

import static com.prituladima.trees.util.ParametrizedArgumentSupplier.*;
import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class BITTest {

    public static Stream<Arguments> ARGUMENTS = fromFile(BIT1D).get();

    @ParameterizedTest
    @VariableSource("ARGUMENTS")
    void test_binary_indexed_tree(File testCaseFile) throws FileNotFoundException {
        try (Scanner inScanner = new Scanner(testCaseFile)) {

            int size = inScanner.nextInt();

            int[] array = new int[size];

            for (int j = 0; j < size; j++) {
                array[j] = inScanner.nextInt();
            }

            int amountOfRequests = inScanner.nextInt();

            TestCase[] requests = new TestCase[amountOfRequests];

            for (int j = 0; j < amountOfRequests; j++) {
                String type = inScanner.next();
                if(type.equals("S")){
                    requests[j] = TestCase.getRangeSumTestCase(inScanner.nextInt(), inScanner.nextInt(), inScanner.nextLong());
                }else if(type.equals("M")){
                    requests[j] = TestCase.modifySingleElementTestCase(inScanner.nextInt(), inScanner.nextLong());
                }

            }

            test_binary_indexed_tree(size, array, amountOfRequests, requests);

        }
    }


    void test_binary_indexed_tree(int size, int[] array, int amountOfRequests, TestCase[] requests) {
        assertTimeout(ofMillis(100), () -> {
            BIT binaryIndexedTree = new BIT(size);
            for (int i = 0; i < size; i++) {
                binaryIndexedTree.add(i, array[i]);
            }

            for (int i = 0; i < amountOfRequests; i++) {
                if (requests[i].getQueryType() == TestCase.QueryType.MODIFY) {
                    binaryIndexedTree.add(requests[i].getIndex(), requests[i].getDelta());
                }else if(requests[i].getQueryType() == TestCase.QueryType.READ){
                    assertEquals(requests[i].getExpectedValue(), binaryIndexedTree.get(requests[i].getL(), requests[i].getR()));
                }

            }
        });
    }


}
