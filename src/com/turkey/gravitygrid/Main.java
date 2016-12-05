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
                // Galaxy 1
                CreateLevel(3,5,5,0,false,false),
                CreateLevel(3,5,5,0,false,false),
                CreateLevel(3,6,5,0,false,false),
                CreateLevel(3,7,5,0,false,false),
                CreateLevel(3,8,5,0,false,false),
                CreateLevel(3,5,6,0,false,false),
                CreateLevel(3,5,7,0,false,false),
                CreateLevel(3,5,8,0,false,false),
                CreateLevel(3,6,6,0,false,false),
                CreateLevel(3,7,6,0,false,false),
                CreateLevel(3,8,7,0,false,false),
                CreateLevel(3,9,8,0,false,false),
                CreateLevel(4,9,8,0,false,false),
                CreateLevel(4,9,8,0,false,false),
                CreateLevel(4,9,8,0,false,false),
                CreateLevel(4,9,8,0,false,false),
                CreateLevel(4,9,8,0,false,false),
                CreateLevel(4,9,8,0,false,false),
                CreateLevel(4,9,8,0,false,false),
                CreateLevel(4,9,8,0,false,false),
                CreateLevel(4,9,8,0,false,false),
                CreateLevel(4,9,8,0,false,false),
                CreateLevel(4,9,8,0,false,false),
                CreateLevel(4,9,8,0,false,false),
                CreateLevel(4,9,8,0,false,false),

                // Galaxy 2
                CreateLevel(3,5,5,0,true,false),
                CreateLevel(3,5,5,0,true,false),
                CreateLevel(4,6,5,0,true,false),
                CreateLevel(3,7,5,0,true,false),
                CreateLevel(3,8,5,0,true,false),
                CreateLevel(4,5,6,0,true,false),
                CreateLevel(3,5,7,0,true,false),
                CreateLevel(3,5,8,0,true,false),
                CreateLevel(4,6,6,0,true,false),
                CreateLevel(3,7,6,0,true,false),
                CreateLevel(3,8,7,0,true,false),
                CreateLevel(4,9,8,0,true,false),
                CreateLevel(4,9,8,0,true,false),
                CreateLevel(4,9,8,0,true,false),
                CreateLevel(5,9,8,0,true,false),
                CreateLevel(4,9,8,0,true,false),
                CreateLevel(4,9,8,0,true,false),
                CreateLevel(5,9,8,0,true,false),
                CreateLevel(4,9,8,0,true,false),
                CreateLevel(4,9,8,0,true,false),
                CreateLevel(5,9,8,0,true,false),
                CreateLevel(4,9,8,0,true,false),
                CreateLevel(4,9,8,0,true,false),
                CreateLevel(5,9,8,0,true,false),
                CreateLevel(4,9,8,0,true,false),

                // Galaxy 3
                CreateLevel(1,0,0,5,true,false),
                CreateLevel(2,0,0,6,true,false),
                CreateLevel(3,0,0,7,true,false),
                CreateLevel(4,0,0,8,true,false),
                CreateLevel(3,4,4,4,true,false),
                CreateLevel(3,4,4,5,true,false),
                CreateLevel(3,5,5,5,true,false),
                CreateLevel(3,6,6,6,true,false),
                CreateLevel(3,6,6,5,true,false),
                CreateLevel(3,7,6,6,true,false),
                CreateLevel(3,8,7,5,true,false),
                CreateLevel(3,9,8,6,true,false),
                CreateLevel(4,6,5,9,true,false),
                CreateLevel(4,7,5,8,true,false),
                CreateLevel(4,9,8,5,true,false),
                CreateLevel(5,9,8,6,true,false),
                CreateLevel(4,7,8,8,true,false),
                CreateLevel(4,9,8,7,true,false),
                CreateLevel(5,7,5,9,true,false),
                CreateLevel(4,5,6,8,true,false),
                CreateLevel(5,9,8,6,true,false),
                CreateLevel(4,3,6,4,true,false),
                CreateLevel(5,9,8,5,true,false),
                CreateLevel(5,9,5,7,true,false),
                CreateLevel(5,9,2,6,true,false),

                // Galaxy 4
                CreateLevel(4,3,6,3,true,true),
                CreateLevel(5,9,7,7,true,true),
                CreateLevel(5,9,7,7,true,true),
                CreateLevel(6,9,7,7,true,true),
                CreateLevel(4,9,7,7,true,true),
                CreateLevel(4,9,7,7,true,true),
                CreateLevel(5,3,6,3,true,true),
                CreateLevel(5,3,6,3,true,true),
                CreateLevel(5,3,6,3,true,true),
                CreateLevel(6,3,6,3,true,true),
                CreateLevel(6,3,6,3,true,true),
                CreateLevel(6,3,6,3,true,true),
                CreateLevel(5,3,6,3,true,true),
                CreateLevel(5,3,6,3,true,true),
                CreateLevel(4,3,6,3,true,true),
                CreateLevel(5,3,6,3,true,true),
                CreateLevel(6,3,6,3,true,true),
                CreateLevel(6,3,6,3,true,true),
                CreateLevel(6,3,6,3,true,true),
                CreateLevel(5,3,6,3,true,true),
                CreateLevel(6,3,6,3,true,true),
                CreateLevel(7,3,6,3,true,true),
                CreateLevel(8,3,6,3,true,true),
                CreateLevel(8,3,6,3,true,true),
                CreateLevel(8,3,6,3,true,true),

                // Galaxy 5

        };

        // Print the levels
        for(int x=0; x<level.length; x++) {
            System.out.print("{");
            for (int a = 0; a < 52; a++) {
                System.out.print(level[x][a] + ",");
            }
            // print the last one so we can println instead of print
            System.out.println(level[x][52] + "},");
        }

    }
}
