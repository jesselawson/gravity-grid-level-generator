package com.turkey.gravitygrid;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static LevelGenerator levelGenerator;

    public static int[] CreateLevel(int a, int b, int c, int d, boolean e, boolean f) {
        int[] level;

        levelGenerator = new LevelGenerator();

        level = levelGenerator._GenerateLevel(a,b,c,d,e,f);

        // TODO: TEST MANY ITERATIONS OF THE _GENERATELEVEL

        return level;
    }

    public static final int[] numPlanetsTable = new int[] {1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,6,7,7,7,7,7,7,7,7,7,8,8,8,8,8,8,8,9,9,9,9,9,9,9,10,10,10,10,10,10,11,11,11,11,11,11,12,12,12,12,12,13,13,13,13};

    public static final int[] complexityTable = new int[] {3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,5,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8};

    public static void main(String[] args) {

        int randComplexity = ThreadLocalRandom.current().nextInt(1, complexityTable.length-1);
        int randRed = ThreadLocalRandom.current().nextInt(1, numPlanetsTable.length-1);
        int randBlue = ThreadLocalRandom.current().nextInt(1, numPlanetsTable.length-1);
        int randGreen = ThreadLocalRandom.current().nextInt(1, numPlanetsTable.length-1);

        for(int a=0; a<100; a++){
            int tmp[] = CreateLevel(3,5,5,0,false,false);
            if(tmp[0] == tmp[1] && tmp[0] > 0){
                System.out.println("RED FAILED AT "+a);
            }

            if(tmp[2] == tmp[3] && tmp[2] > 0) {
                System.out.println("BLUE FAILED AT "+a);
            }

            if(tmp[4] == tmp[5] && tmp[4] > 0) {
                System.out.println("GREEN FAILED AT "+a);
            }

            //System.out.println("("+tmp[0]+"/"+tmp[1]+") ("+tmp[2]+"/"+tmp[3]+") ("+tmp[4]+"/"+tmp[5]+")");
        }

        /*

        // Print the levels
        for(int x=0; x<level.length; x++) {
            System.out.print("{");
            for (int a = 0; a < 52; a++) {
                System.out.print(level[x][a] + ",");
            }
            // print the last one so we can println instead of print
            System.out.println(level[x][52] + "}, // Level "+(x+1+50));
        }*/

    }
}
