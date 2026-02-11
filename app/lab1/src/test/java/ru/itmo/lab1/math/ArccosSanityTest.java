package ru.itmo.lab1.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArccosSanityTest {

  @Test
  void sanityAgainstMathAcos() {
    double[] xs = {
        -1.0, -0.9, -0.5, -0.1,
         0.0,  0.1,  0.5,  0.9,  1.0,
         1.0 - 1e-8, 1.0 - 1e-12,
        -1.0 + 1e-8, -1.0 + 1e-12
    };

    double maxErr = 0.0;
    double worstX = 0.0;

    for (double x : xs) {
      double expected = Math.acos(x);
      double actual = ArccosSeries.acos(x);
      double err = Math.abs(expected - actual);

      if (err > maxErr) { maxErr = err; worstX = x; }

      double tol = (Math.abs(1.0 - Math.abs(x)) <= 1e-6) ? 1e-7 : 1e-9;
      assertTrue(err <= tol, "x=" + x + " err=" + err + " tol=" + tol +
          " expected=" + expected + " actual=" + actual);
      assertTrue(actual >= 0.0 && actual <= Math.PI, "range fail x=" + x + " y=" + actual);
    }
  }
}
