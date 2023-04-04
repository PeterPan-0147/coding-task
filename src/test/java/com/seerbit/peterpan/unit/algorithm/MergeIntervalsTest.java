package com.seerbit.peterpan.unit.algorithm;

import com.seerbit.peterpan.algorithm.AlgorithmSolutions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MergeIntervalsTest {
    @Test
    public void testMerge_emptyInputArray() {
        List<AlgorithmSolutions.Interval> intervals = new ArrayList<>();
        List<AlgorithmSolutions.Interval> result = AlgorithmSolutions.mergeIntervals(intervals);
        assertTrue(result.isEmpty());
    }
    @Test
    public void testMerge_singleInterval() {
        LocalDateTime start = LocalDateTime.of(2023, 4, 4, 17, 0);
        LocalDateTime end = LocalDateTime.of(2023, 4, 4, 18, 0);
        List<AlgorithmSolutions.Interval> intervals = new ArrayList<>();
        intervals.add(new AlgorithmSolutions.Interval(start, end));
        List<AlgorithmSolutions.Interval> result = AlgorithmSolutions.mergeIntervals(intervals);
        assertEquals(1, result.size());
        assertEquals(start, result.get(0).getStart());
        assertEquals(end, result.get(0).getEnd());
    }
    @Test
    public void testMerge_nonOverlappingIntervals() {
        LocalDateTime start1 = LocalDateTime.of(2023, 4, 4, 17, 0);
        LocalDateTime end1 = LocalDateTime.of(2023, 4, 4, 18, 0);
        LocalDateTime start2 = LocalDateTime.of(2023, 4, 4, 19, 0);
        LocalDateTime end2 = LocalDateTime.of(2023, 4, 4, 20, 0);
        List<AlgorithmSolutions.Interval> intervals = new ArrayList<>();
        intervals.add(new AlgorithmSolutions.Interval(start1, end1));
        intervals.add(new AlgorithmSolutions.Interval(start2, end2));
        List<AlgorithmSolutions.Interval> result = AlgorithmSolutions.mergeIntervals(intervals);
        assertEquals(2, result.size());
        assertEquals(start1, result.get(0).getStart());
        assertEquals(end1, result.get(0).getEnd());
        assertEquals(start2, result.get(1).getStart());
        assertEquals(end2, result.get(1).getEnd());
    }
    @Test
    public void testMerge_overlappingIntervals() {
        LocalDateTime start1 = LocalDateTime.of(2023, 4, 4, 17, 0);
        LocalDateTime end1 = LocalDateTime.of(2023, 4, 4, 19, 0);
        LocalDateTime start2 = LocalDateTime.of(2023, 4, 4, 18, 0);
        LocalDateTime end2 = LocalDateTime.of(2023, 4, 4, 20, 0);
        List<AlgorithmSolutions.Interval> intervals = new ArrayList<>();
        intervals.add(new AlgorithmSolutions.Interval(start1,end1));
        intervals.add(new AlgorithmSolutions.Interval(start2,end2));
        List<AlgorithmSolutions.Interval> result = AlgorithmSolutions.mergeIntervals(intervals);
        assertEquals(1,result.size());
        assertEquals(start1,result.get(0).getStart());
        assertEquals(end2,result.get(0).getEnd());
    }
    @Test
    public void testMerge_partiallyOverlappingIntervals() {
        LocalDateTime start1 = LocalDateTime.of(2023,4 ,4 ,17 ,0);
        LocalDateTime end1 = LocalDateTime.of(2023 ,4 ,4 ,19 ,0);
        LocalDateTime start2 = LocalDateTime.of(2023 ,4 ,4 ,18 ,30);
        LocalDateTime end2 = LocalDateTime.of(2023 ,4 ,5 ,20 ,0);
        List<AlgorithmSolutions.Interval> intervals = new ArrayList<>();
        intervals.add(new AlgorithmSolutions.Interval(start1,end1));
        intervals.add(new AlgorithmSolutions.Interval(start2,end2));
        List<AlgorithmSolutions.Interval> result = AlgorithmSolutions.mergeIntervals(intervals);
        assertEquals(1,result.size());
        assertEquals(start1,result.get(0).getStart());
        assertEquals(end2,result.get(0).getEnd());
    }

    @Test
    public void testMerge_nestedIntervals() {
        LocalDateTime start1 = LocalDateTime.of(2023 ,4 ,4 ,17 ,0);
        LocalDateTime end1 = LocalDateTime.of(2023 ,4 ,5 ,20 ,0);
        LocalDateTime start2 = LocalDateTime.of(2023 ,4 ,5 ,18 ,30);
        LocalDateTime end2 = LocalDateTime.of(2023 ,4 ,5 ,19 ,30);
        List<AlgorithmSolutions.Interval> intervals = new ArrayList<>();
        intervals.add(new AlgorithmSolutions.Interval(start1,end1));
        intervals.add(new AlgorithmSolutions.Interval(start2,end2));
        List<AlgorithmSolutions.Interval> result = AlgorithmSolutions.mergeIntervals(intervals);
        assertEquals(1,result.size());
        assertEquals(start1,result.get(0).getStart());
        assertEquals(end1,result.get(0).getEnd());
    }

}
