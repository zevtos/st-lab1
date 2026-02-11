package ru.itmo.lab1.sort;

import java.util.ArrayList;
import java.util.List;

public final class InsertionSort {
  private InsertionSort() {}

  public static List<TracePoint> sortWithTrace(int[] a) {
    List<TracePoint> trace = new ArrayList<>();
    if (a == null) throw new IllegalArgumentException("array must not be null");

    for (int i = 1; i < a.length; i++) {
      trace.add(TracePoint.OUTER);
      int key = a[i];
      int j = i - 1;

      boolean shifted = false;
      while (j >= 0 && a[j] > key) {
        trace.add(TracePoint.WHILE_TRUE);
        a[j + 1] = a[j];
        trace.add(TracePoint.SHIFT);
        shifted = true;
        j--;
      }

      if (!shifted) {
        trace.add(TracePoint.WHILE_FALSE);
      }

      a[j + 1] = key;
      trace.add(TracePoint.INSERT);
    }

    return trace;
  }
}
