package Lesson4.HomeWork4;

public class Triangle {

    public static double TriangleSquareGerone( int a, int b, int c ) throws TriangleException {

        if (a <= 0 || b <= 0 || c <= 0) {
            throw new TriangleException("Не верно заданы стороны треугольника!");
        }

        if ((b + c == a) || (a + c) == b ||  (a + b == c)) {
            System.out.println("Треугольник является вырожденным, его площадь составляет 0");
            return 0;
        }

        if (((b + c) > a) && ((a + c) > b) && ((a + b) > c)) {
            double pp = (a + b + c) / 2.0;  // полупериметр
            double square = Math.sqrt(pp * (pp - a) * (pp - b) * (pp - c)); // по формуле Герона
            System.out.println("Площадь треугольника равна = " + square);
            return square;
        } else {
            throw new TriangleException("Не верно заданы стороны треугольника!");
        }

    }
}
