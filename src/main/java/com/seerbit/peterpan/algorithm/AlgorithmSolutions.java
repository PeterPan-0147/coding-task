package com.seerbit.peterpan.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

public class AlgorithmSolutions {
    public static List<Interval> mergeIntervals(List<Interval> intervals) {
        List<Interval> mergedIntervals = new ArrayList<>();
        if (intervals == null || intervals.isEmpty()) {
            return mergedIntervals;
        }
        Interval current = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval interval = intervals.get(i);
            if (current.getEnd().isAfter(interval.getStart())) {
                current = new Interval(current.getStart(),
                        current.getEnd().isAfter(interval.getEnd()) ? current.getEnd() : interval.getEnd());
            } else {
                mergedIntervals.add(current);
                current = interval;
            }
        }
        mergedIntervals.add(current);
        return mergedIntervals;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Interval {
        private LocalDateTime start;
        private LocalDateTime end;
    }
    public static boolean hasTwoIntegersWithSum(int[] arr, int sum) {
        Set<Integer> set = new HashSet<>();
        for (int j : arr) {
            int complement = sum - j;
            if (set.contains(complement)) {
                return true;
            }
            set.add(j);
        }
        return false;
    }
    public static int[] findLowAndHighIndex(int[] arr, int key) {
        int[] result = {-1, -1};
        int left = 0;
        int right = arr.length - 1;
        int mid;
        // Find the leftmost index of the key
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] < key) {
                left = mid + 1;
            } else if (arr[mid] > key) {
                right = mid - 1;
            } else {
                result[0] = mid;
                right = mid - 1;
            }
        }
        // Find the rightmost index of the key
        left = 0;
        right = arr.length - 1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (arr[mid] < key) {
                left = mid + 1;
            } else if (arr[mid] > key) {
                right = mid - 1;
            } else {
                result[1] = mid;
                left = mid + 1;
            }
        }
        return result;
    }
}
