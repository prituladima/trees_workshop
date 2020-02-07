package com.prituladima.trees;

import com.prituladima.trees.util.TestCase2D;
import com.prituladima.trees.util.VariableSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.Stream;

import static com.prituladima.trees.util.ParametrizedArgumentSupplier.BIT2D;
import static com.prituladima.trees.util.ParametrizedArgumentSupplier.fromFile;
import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class BIT2DTest {

    public static Stream<Arguments> ARGUMENTS = fromFile(BIT2D).get();

    @ParameterizedTest
    @VariableSource("ARGUMENTS")
    void test_binary_indexed_tree_2d(File testCaseFile) throws FileNotFoundException {
        try (Scanner inScanner = new Scanner(testCaseFile)) {

            int sizeX = inScanner.nextInt();
            int sizeY = inScanner.nextInt();

            int[][] array = new int[sizeX][sizeY];

            for (int i = 0; i < sizeX; i++) {
                for (int j = 0; j < sizeY; j++) {
                    array[i][j] = inScanner.nextInt();
                }
            }

            int amountOfRequests = inScanner.nextInt();

            TestCase2D[] requests = new TestCase2D[amountOfRequests];

            for (int j = 0; j < amountOfRequests; j++) {
                String type = inScanner.next();
                if (type.equals("S")) {
                    requests[j] = TestCase2D.getRangeSumTestCase(inScanner.nextInt(), inScanner.nextInt(), inScanner.nextInt(), inScanner.nextInt(), inScanner.nextLong());
                } else if (type.equals("M")) {
                    requests[j] = TestCase2D.modifySingleElementTestCase(inScanner.nextInt(), inScanner.nextInt(), inScanner.nextLong());
                }

            }

            test_binary_indexed_tree_2d(sizeX, sizeY, array, amountOfRequests, requests);

        }
    }


    void test_binary_indexed_tree_2d(int sizeX, int sizeY, int[][] array, int amountOfRequests, TestCase2D[] requests) {
        assertTimeout(ofMillis(300), () -> {
            BIT2D binaryIndexedTree = new BIT2D(sizeX, sizeY);
            for (int i = 0; i < sizeX; i++) {
                for (int j = 0; j < sizeY; j++) {
                    binaryIndexedTree.add(i, j,  array[i][j]);
                }
            }

            for (int i = 0; i < amountOfRequests; i++) {
                if (requests[i].getQueryType() == TestCase2D.QueryType.MODIFY) {
                    binaryIndexedTree.add(requests[i].getIndex1(), requests[i].getIndex2(), requests[i].getDelta());
                } else if (requests[i].getQueryType() == TestCase2D.QueryType.READ) {
                    assertEquals(requests[i].getExpectedValue(), binaryIndexedTree.get(requests[i].getX1(), requests[i].getX2(), requests[i].getY1(), requests[i].getY2()));
                }

            }
        });
    }

}
