package com.prituladima.trees;

import com.prituladima.trees.entities.Edge;
import com.prituladima.trees.util.VariableSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Stream;

import static com.prituladima.trees.util.ParametrizedArgumentSupplier.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MinimalSpanningTreeTest {


    public static Stream<Arguments> ARGUMENTS = fromFile(KRUSKAL).get();

    @ParameterizedTest
    @VariableSource("ARGUMENTS")
    void test_kruskal_algo(File testCaseFile) throws FileNotFoundException {
        try (Scanner inScanner = new Scanner(testCaseFile)) {

            List<Edge<Integer>> edgeList = new ArrayList<>();

            int vertexCount = inScanner.nextInt();
            int edgeCount = inScanner.nextInt();
            boolean isDirected = inScanner.nextBoolean();

            for (int i = 0; i < edgeCount; i++) {
                int from = inScanner.nextInt();
                int to = inScanner.nextInt();
                int cost = inScanner.nextInt();

                edgeList.add(new Edge<>(from, to, cost));
            }

            int spanningTreeSize = vertexCount - 1;

            List<Edge<Integer>> spanningTree = new ArrayList<>();

            for (int i = 0; i < spanningTreeSize; i++) {
                int from = inScanner.nextInt();
                int to = inScanner.nextInt();
                int cost = inScanner.nextInt();

                spanningTree.add(new Edge<>(from, to, cost));
            }

            test_kruskal_algo(vertexCount, edgeList, spanningTree);

        }
    }


    void test_kruskal_algo(int vertexCount, List<Edge<Integer>> edgeList, List<Edge<Integer>> expectedSpanningTree) {
        MinimalSpanningTree minimalSpanningTree = new MinimalSpanningTree();
        List<Edge<Integer>> actualSpanningTree = minimalSpanningTree.kruskal(vertexCount, edgeList);

        Set<Edge<Integer>> set1 = new HashSet<>(expectedSpanningTree);
        Set<Edge<Integer>> set2 = new HashSet<>(actualSpanningTree);

        System.out.println("Expected:");
        System.out.println(set1);
        System.out.println("Actual:");
        System.out.println(set2);

        assertEquals(
                set1.stream().mapToInt(Edge::getCost).sum(),
                set2.stream().mapToInt(Edge::getCost).sum()
        );

//        assertTrue(set1.containsAll(set2));
//        assertTrue(set2.containsAll(set1));
    }

}
