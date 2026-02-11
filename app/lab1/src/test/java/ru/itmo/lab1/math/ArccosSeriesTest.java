package ru.itmo.lab1.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArccosSeriesTest {

  private static final double PI = Math.PI;

  @Test
  void invalidGreaterThanOneThrows() {
    assertThrows(IllegalArgumentException.class, () -> ArccosSeries.acos(1.0 + 1e-6));
  }

  @Test
  void invalidLessThanMinusOneThrows() {
    assertThrows(IllegalArgumentException.class, () -> ArccosSeries.acos(-1.0 - 1e-6));
  }

  @Test
  void nanThrows() {
    assertThrows(IllegalArgumentException.class, () -> ArccosSeries.acos(Double.NaN));
  }

  @Test
  void infThrows() {
    assertThrows(IllegalArgumentException.class, () -> ArccosSeries.acos(Double.POSITIVE_INFINITY));
    assertThrows(IllegalArgumentException.class, () -> ArccosSeries.acos(Double.NEGATIVE_INFINITY));
  }

  @Test
  void rangeHoldsForTypicalPoints() {
    double[] xs = {-0.9, -0.5, -0.1, 0.0, 0.1, 0.5, 0.9, -1.0, 1.0, 1-1e-12, -1+1e-12};
    for (double x : xs) {
      try {
        double y = ArccosSeries.acos(x);
        assertTrue(y >= 0.0 && y <= PI, "x=" + x + " y=" + y);
      } catch (UnsupportedOperationException e) {
        throw e;
      }
    }
  }

  @Test
  void symmetryProperty() {
    double[] xs = {-0.9, -0.5, -0.1, 0.1, 0.5, 0.9};
    for (double x : xs) {
      double left = ArccosSeries.acos(-x);
      double right = PI - ArccosSeries.acos(x);
      assertEquals(right, left, 1e-9, "x=" + x);
    }
  }

  @Test
  void monotonicityProperty() {
    double[] xs = {-0.9, -0.5, -0.1, 0.0, 0.1, 0.5, 0.9};
    for (int i = 0; i + 1 < xs.length; i++) {
      double x1 = xs[i];
      double x2 = xs[i + 1];
      double y1 = ArccosSeries.acos(x1);
      double y2 = ArccosSeries.acos(x2);
      assertTrue(y1 > y2, "x1=" + x1 + " x2=" + x2 + " y1=" + y1 + " y2=" + y2);
    }
  }

  @Test
  void matchesMathAcosTypical() {
    double[] xs = {-0.9, -0.5, -0.1, 0.0, 0.1, 0.5, 0.9};
    for (double x : xs) {
      double expected = Math.acos(x);
      double actual = ArccosSeries.acos(x);
      assertEquals(expected, actual, 1e-9, "x=" + x);
    }
  }

  @Test
  void matchesMathAcosNearBoundaries() {
    double[] xs = {1 - 1e-12, 1 - 1e-8, -1 + 1e-12, -1 + 1e-8, -1.0, 1.0};
    for (double x : xs) {
      double expected = Math.acos(x);
      double actual = ArccosSeries.acos(x);
      assertEquals(expected, actual, 1e-7, "x=" + x);
    }
  }
}
