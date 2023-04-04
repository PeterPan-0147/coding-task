package com.seerbit.peterpan.unit.algorithm;

import com.seerbit.peterpan.algorithm.AlgorithmSolutions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class BinarySearchTest {
    @Test
    public void testEmptyArray() {
        int[] arr = {};
        int key = 5;
        int[] expected = {-1, -1};
        assertArrayEquals(expected, AlgorithmSolutions.findLowAndHighIndex(arr, key));
    }
    @Test
    public void testKeyNotFound() {
        int[] arr = {1, 2, 3, 4, 5};
        int key = 6;
        int[] expected = {-1, -1};
        assertArrayEquals(expected, AlgorithmSolutions.findLowAndHighIndex(arr, key));
    }
    @Test
    public void testSingleKey() {
        int[] arr = {5};
        int key = 5;
        int[] expected = {0, 0};
        assertArrayEquals(expected, AlgorithmSolutions.findLowAndHighIndex(arr, key));
    }
    @Test
    public void testMultipleKeys() {
        int[] arr = {1, 2, 2, 2, 3, 4, 4, 5};
        int key = 2;
        int[] expected = {1, 3};
        assertArrayEquals(expected, AlgorithmSolutions.findLowAndHighIndex(arr, key));
    }
    @Test
    public void testLargeArray() {
        int[] arr = new int[1000000];
        for (int i = 0; i < 1000000; i++) {
            arr[i] = i % 10; // add some duplicates
        }
        int key = 5;
        int[] expected = {22195, 22265};
        assertArrayEquals(expected, AlgorithmSolutions.findLowAndHighIndex(arr, key));
    }
}
