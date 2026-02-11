package ru.itmo.lab1.math;

public final class ArccosSeries {
  private ArccosSeries() {}

  private static final double PI = Math.PI;
  private static final double HALF_PI = Math.PI / 2.0;

  private static final double EPS = 1e-10;
  private static final int MAX_TERMS = 10_000;

  private static final double NEAR_ONE = 1e-6;

  public static double acos(double x) {
    if (Double.isNaN(x) || Double.isInfinite(x) || x < -1.0 || x > 1.0) {
      throw new IllegalArgumentException("x must be in [-1, 1] and finite");
    }

    if (x == 1.0) return 0.0;
    if (x == -1.0) return PI;

    if (x >= 1.0 - NEAR_ONE) {
      double t = Math.sqrt((1.0 - x) / 2.0);
      return 2.0 * asinSeries(t);
    }
    if (x <= -1.0 + NEAR_ONE) {
      double t = Math.sqrt((1.0 + x) / 2.0);
      return PI - 2.0 * asinSeries(t);
    }

    return HALF_PI - asinSeries(x);
  }

  // asin(x) via power series with term recurrence
  private static double asinSeries(double x) {
    double term = x;   // n=0 term
    double sum = term;

    double x2 = x * x;

    for (int n = 0; n < MAX_TERMS - 1; n++) {
      // term_{n+1} = term_n * ((2n+1)^2 / ((2n+2)(2n+3))) * x^2
      double a = (2.0 * n + 1.0);
      double ratio = (a * a) / ((2.0 * n + 2.0) * (2.0 * n + 3.0));
      term *= ratio * x2;
      sum += term;

      if (Math.abs(term) < EPS) break;
    }

    return sum;
  }
}
