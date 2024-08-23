package myUsacoSilverSolutions.Sorting;

import java.util.Arrays;

public class SortingObjects {
    public static void main(String[] args) {
        final int N = 5;
        //an array of Rectangle
        Rectangle[] rectangles = {new Rectangle("blue", 5, 4), new Rectangle("white", 2, 2), new Rectangle("red", 3, 5),
            new Rectangle ("black", 4, 6), new Rectangle ("yellow", 1, 1)};

    Arrays.sort(rectangles, (r1, r2) -> r1.color.compareTo(r2.color));
    System.out.println("After sorted by color:");
    for (Rectangle rec: rectangles) {
        System.out.println(rec.color + " " + rec.height + " " + rec.width);
    }


    Arrays.sort(rectangles, (r1, r2) -> r1.height * r1.width - r2.height * r2.width);
    System.out.println("After sorted by area:");
    for (Rectangle rec: rectangles) {
        System.out.println(rec.color + " " + rec.height + " " + rec.width);
    }
}
}
//Rectangle object
class Rectangle {
    String color;
    int height;
    int width;

    public Rectangle(String color, int height, int width) {
        this.color = color;
        this.height = height;
        this.width = width;
    }
}