package myUsacoSilverSolutions.PS_1_Sorting;

import java.util.Arrays;
import java.util.Scanner;
/**
 * Problem 12: Theater Seating [Rob Kolstad, 2010]
 * http://tjsct.wikidot.com/usaco-jan10-bronze
 */
public class usaco2010jan {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int column = in.nextInt();
        int row = in.nextInt();

        Seat[] seats = new Seat[column * row];
        int counter = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                seats[counter] = new Seat(i, j, calculate_distance(i, j, column, row));
                counter++;
            }
        }
        Arrays.sort(seats);

        int[][] dream_theater = new int[row][column];
        for (int i = 0; i < seats.length; i++) {
            dream_theater[seats[i].y_coordinate][seats[i].x_coordinate] = i + 1;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(dream_theater[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static int calculate_distance(int y, int x, int column, int row) {
        // Correcting variable usage according to their meaning
        int middleColumn = (column - 1) / 2;
        int bottomRow = row - 1;
        return (int) Math.pow((x - middleColumn), 2) + (int) Math.pow((y - bottomRow), 2);  
    }
}

class Seat implements Comparable<Seat> {
    public int x_coordinate;
    public int y_coordinate;
    public int dist_to_origin;

    public Seat(int y_coordinate, int x_coordinate, int dist_to_origin) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
        this.dist_to_origin = dist_to_origin;
    }

    @Override
    public int compareTo(Seat s2) {
        if (this.dist_to_origin == s2.dist_to_origin) {
            if (this.y_coordinate == s2.y_coordinate) {
                return Integer.compare(this.x_coordinate, s2.x_coordinate);
            }
            return -Integer.compare(this.y_coordinate, s2.y_coordinate);
        }
        return Integer.compare(this.dist_to_origin, s2.dist_to_origin);
    }
}
