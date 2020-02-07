package com.prituladima.trees;

import com.prituladima.trees.entities.Graph;
import com.prituladima.trees.entities.Node;
import com.prituladima.trees.util.VariableSource;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.stream.Stream;

import static com.prituladima.trees.util.ParametrizedArgumentSupplier.*;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class ShortestPathTest {

    public static Stream<Arguments> ARGUMENTS = fromFile(DIJKSTRA).get();

    @ParameterizedTest
    @VariableSource("ARGUMENTS")
    void test_shortest_path(File testCaseFile) throws FileNotFoundException {
        try (Scanner inScanner = new Scanner(testCaseFile)) {


            Graph<Integer> graph = new Graph<>();

            int vertexCount = inScanner.nextInt();
            int edgeCount = inScanner.nextInt();
            boolean isDirected = inScanner.nextBoolean();

            for (int i = 0; i < vertexCount; i++) {
                graph.put(i, new LinkedHashSet<>());
            }

            for (int i = 0; i < edgeCount; i++) {
                int from = inScanner.nextInt();
                int to = inScanner.nextInt();
                int cost = inScanner.nextInt();

                graph.get(from).add(new Node<>(to, cost));
                if (!isDirected) {
                    graph.get(to).add(new Node<>(from, cost));
                }
            }

            int source = inScanner.nextInt();

            int[] expectedCosts = new int[vertexCount];
            for (int i = 0; i < vertexCount; i++) {
                expectedCosts[i] = inScanner.nextInt();
            }

            test_shortest_path(source, graph, expectedCosts);

        }
    }


    void test_shortest_path(int source, Graph<Integer> graph, int[] expectedCosts) {
        ShortestPath shortestPath = new ShortestPath();

        int[] actualCosts = shortestPath.dijkstra(source, graph);



        System.out.println("Dijkstra test:");
        System.out.println("Expected:");
        System.out.println(Arrays.toString(expectedCosts));
        System.out.println("Actual:");
        System.out.println(Arrays.toString(actualCosts));

        assertEquals(expectedCosts.length, actualCosts.length);
        for (int i = 0; i < expectedCosts.length; i++) {
            assertEquals(expectedCosts[i], actualCosts[i]);
        }
    }

}
