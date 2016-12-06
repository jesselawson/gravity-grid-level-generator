package com.turkey.gravitygrid;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static LevelGenerator levelGenerator;

    public static int[] CreateLevel(int a, int b, int c, int d, boolean e, boolean f) {
        int[] level;

        levelGenerator = new LevelGenerator();

        level = levelGenerator.GenerateLevel(a,b,c,d,e,f);

        return level;
    }

    public static final int[] numPlanetsTable = new int[] {1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,6,7,7,7,7,7,7,7,7,7,8,8,8,8,8,8,8,9,9,9,9,9,9,9,10,10,10,10,10,10,11,11,11,11,11,11,12,12,12,12,12,13,13,13,13};

    public static final int[] complexityTable = new int[] {3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,5,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8};

    public static void main(String[] args) {

        int randComplexity = ThreadLocalRandom.current().nextInt(1, complexityTable.length-1);
        int randRed = ThreadLocalRandom.current().nextInt(1, numPlanetsTable.length-1);
        int randBlue = ThreadLocalRandom.current().nextInt(1, numPlanetsTable.length-1);
        int randGreen = ThreadLocalRandom.current().nextInt(1, numPlanetsTable.length-1);

        int[][] level = new int[][] {

                /*// Galaxy 1
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,16,0,0,1}, // Custom 1
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0,1,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,32,0,0,2}, // Custom 2
                {0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,9,0,0,1}, // Level 3
                CreateLevel(2,6,0,0,false,false),
                CreateLevel(2,7,0,0,false,false),
                CreateLevel(2,8,0,0,false,false),
                {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,2,2,0,4,0,1}, // Level 7 W/ Tutorial Overlay
                CreateLevel(3,5,8,0,false,false),
                CreateLevel(4,6,6,0,false,false),
                CreateLevel(3,7,6,0,false,false),
                CreateLevel(4,8,7,0,false,false),
                CreateLevel(3,7,8,0,false,false),
                CreateLevel(4,9,5,0,false,false),
                CreateLevel(4,11,6,0,false,false),
                CreateLevel(4,3,7,0,false,false),
                CreateLevel(4,0,11,0,false,false),
                CreateLevel(3,9,4,0,false,false),
                CreateLevel(4,9,9,0,false,false),
                CreateLevel(4,8,8,0,false,false),
                CreateLevel(4,7,7,0,false,false),
                CreateLevel(4,13,3,0,false,false),
                CreateLevel(3,3,13,0,false,false),
                CreateLevel(4,7,14,0,false,false),
                CreateLevel(4,9,9,0,false,false),
                CreateLevel(5,6,6,0,false,false),*/

                // Galaxy 2
                CreateLevel(3,2,2,0,true,false),
                CreateLevel(3,3,3,0,true,false),
                CreateLevel(3,4,4,0,true,false),
                CreateLevel(4,5,5,0,true,false),
                CreateLevel(4,6,6,0,true,false),
                CreateLevel(4,7,6,0,true,false),
                CreateLevel(5,8,6,0,true,false),
                CreateLevel(4,9,7,0,true,false),
                CreateLevel(4,10,8,0,true,false),
                CreateLevel(5,11,11,0,true,false),
                CreateLevel(5,11,0,0,true,false),
                CreateLevel(4,0,11,0,true,false),
                CreateLevel(4,7,11,0,true,false),
                CreateLevel(5,11,7,0,true,false),
                CreateLevel(5,9,7,0,true,false),
                CreateLevel(5,9,9,0,true,false),
                CreateLevel(5,9,9,0,true,false),
                CreateLevel(5,9,9,0,true,false),
                CreateLevel(5,9,9,0,true,false),
                CreateLevel(5,11,4,0,true,false),
                CreateLevel(5,11,4,0,true,false),
                CreateLevel(5,4,11,0,true,false),
                CreateLevel(5,4,11,0,true,false),
                CreateLevel(5,8,8,0,true,false),
                CreateLevel(5,8,8,0,true,false),


        };

        // Print the levels
        for(int x=0; x<level.length; x++) {
            System.out.print("{");
            for (int a = 0; a < 52; a++) {
                System.out.print(level[x][a] + ",");
            }
            // print the last one so we can println instead of print
            System.out.println(level[x][52] + "}, // Level "+(x+1+50));
        }

    }
}
