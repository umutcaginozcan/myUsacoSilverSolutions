package myUsacoSilverSolutions.Sorting;

import java.util.Arrays;

public class manhattanDistance {
    public static void main(String[] args) {
        //sorting points by distance + x coordinate + y coordinate
        Integer points[][] = {{-3, 2}, {-1, 2}, {2, 2}, {4, 4}, {4, 1}, {3, -2}, {2, -2}, {-2, -1}};

        Arrays.sort(points, (p1, p2) -> 
            {int d1 = Math.abs(p1[0]) + Math.abs(p1[1]);
            int d2 = Math.abs(p2[0]) + Math.abs(p2[1]);
            if (d1 != d2)
                return d1 - d2;
        
            if (p1[0] != p2[0])
                return p1[0] - p2[0];
        
            return p1[1] - p2[1];}
            );

        for (Integer[] p: points) {
            System.out.println(p[0] + " " + p[1]);
        }
    }
}
