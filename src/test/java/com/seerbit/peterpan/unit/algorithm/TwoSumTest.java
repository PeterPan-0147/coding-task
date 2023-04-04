package com.seerbit.peterpan.unit.algorithm;

import org.junit.jupiter.api.Test;

import static com.seerbit.peterpan.algorithm.AlgorithmSolutions.hasTwoIntegersWithSum;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TwoSumTest {
    @Test
    public void testHasTwoIntegersWithSum() {
        // Test case 1: Empty array
        assertFalse(hasTwoIntegersWithSum(new int[]{}, 5));

        // Test case 2: Array with one element
        assertFalse(hasTwoIntegersWithSum(new int[]{3}, 5));

        // Test case 3: Array with two elements
        assertTrue(hasTwoIntegersWithSum(new int[]{2, 3}, 5));
        assertFalse(hasTwoIntegersWithSum(new int[]{2, 3}, 6));

        // Test case 4: Array with three or more elements
        assertTrue(hasTwoIntegersWithSum(new int[]{2, 3, 4, 5}, 7));
        assertTrue(hasTwoIntegersWithSum(new int[]{2, 3, 4, 5}, 9));
        assertTrue(hasTwoIntegersWithSum(new int[]{2, 3, 4, 5}, 6));
        assertTrue(hasTwoIntegersWithSum(new int[]{2, 3, 4, 5}, 8));
        assertFalse(hasTwoIntegersWithSum(new int[]{2, 3, 4, 5}, 10));
        assertFalse(hasTwoIntegersWithSum(new int[]{2, 3, 4, 5}, 1));
    }
}
