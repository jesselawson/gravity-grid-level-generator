package com.turkey.gravitygrid;

public class Main {

    public static LevelGenerator levelGenerator;
    public static int[] level;

    public static void CreateLevel() {
        level = new int[53];

        levelGenerator = new LevelGenerator();

        level = levelGenerator.GenerateLevel(4,9,9,0, false, false);

    }

    public static void main(String[] args) {

        CreateLevel();


        System.out.print("= new int[] {");
        for (int a = 0; a < 52; a++) {
            System.out.print(level[a] + ",");
        }
        // print the last one
        System.out.println(level[52] + "};");





    }
}
