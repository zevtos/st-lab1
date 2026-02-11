package ru.itmo.lab1.sort;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTraceTest {

  @Test
  void traceA_empty() {
    int[] a = {};
    List<TracePoint> t = InsertionSort.sortWithTrace(a);
    assertEquals(List.of(), t);
    assertArrayEquals(new int[]{}, a);
  }

  @Test
  void traceB_single() {
    int[] a = {1};
    List<TracePoint> t = InsertionSort.sortWithTrace(a);
    assertEquals(List.of(), t);
    assertArrayEquals(new int[]{1}, a);
  }

  @Test
  void traceC_sorted() {
    int[] a = {1,2,3};
    List<TracePoint> t = InsertionSort.sortWithTrace(a);
    assertEquals(List.of(
        TracePoint.OUTER, TracePoint.WHILE_FALSE, TracePoint.INSERT,
        TracePoint.OUTER, TracePoint.WHILE_FALSE, TracePoint.INSERT
    ), t);
    assertArrayEquals(new int[]{1,2,3}, a);
  }

  @Test
  void traceD_reverse() {
    int[] a = {3,2,1};
    List<TracePoint> t = InsertionSort.sortWithTrace(a);
    assertEquals(List.of(
        TracePoint.OUTER, TracePoint.WHILE_TRUE, TracePoint.SHIFT, TracePoint.INSERT,
        TracePoint.OUTER, TracePoint.WHILE_TRUE, TracePoint.SHIFT, TracePoint.WHILE_TRUE, TracePoint.SHIFT, TracePoint.INSERT
    ), t);
    assertArrayEquals(new int[]{1,2,3}, a);
  }

  @Test
  void traceE_duplicates() {
    int[] a = {2,1,2};
    List<TracePoint> t = InsertionSort.sortWithTrace(a);
    assertEquals(List.of(
        TracePoint.OUTER, TracePoint.WHILE_TRUE, TracePoint.SHIFT, TracePoint.INSERT,
        TracePoint.OUTER, TracePoint.WHILE_FALSE, TracePoint.INSERT
    ), t);
    assertArrayEquals(new int[]{1,2,2}, a);
  }

  @Test
  void traceF_negativesAndDup() {
    int[] a = {-1,3,0,-1};
    List<TracePoint> t = InsertionSort.sortWithTrace(a);
    assertEquals(List.of(
        TracePoint.OUTER, TracePoint.WHILE_FALSE, TracePoint.INSERT,
        TracePoint.OUTER, TracePoint.WHILE_TRUE, TracePoint.SHIFT, TracePoint.INSERT,
        TracePoint.OUTER, TracePoint.WHILE_TRUE, TracePoint.SHIFT, TracePoint.WHILE_TRUE, TracePoint.SHIFT, TracePoint.INSERT
    ), t);
    assertArrayEquals(new int[]{-1,-1,0,3}, a);
  }
}
