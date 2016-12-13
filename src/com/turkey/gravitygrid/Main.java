package com.turkey.gravitygrid;

import java.util.concurrent.ThreadLocalRandom;

public class Main {

    public static LevelGenerator levelGenerator;

    public static int[] CreateLevel(int a, int b, int c, int d, boolean e, boolean f) {
        int[] level;

        levelGenerator = new LevelGenerator();

        boolean goodResult = false;
        int mytmp[] = null;
        while(!goodResult) {
            int tmp[] = level = levelGenerator._GenerateLevel(a, b, c, d, e, f);

            if(tmp[0] == tmp[1] && tmp[0] > 0){

            } else if(tmp[2] == tmp[3] && tmp[2] > 0) {

            } else if(tmp[4] == tmp[5] && tmp[4] > 0) {

            } else {
                goodResult = true;
                mytmp = new int[tmp.length];
                mytmp = tmp;
                //System.out.println("Generated OK level "+a+": ("+tmp[0]+"/"+tmp[1]+"), ("+tmp[2]+"/"+tmp[3]+"), "+tmp[4]+"/"+tmp[5]+")");
            }
            //System.out.println("("+tmp[0]+"/"+tmp[1]+") ("+tmp[2]+"/"+tmp[3]+") ("+tmp[4]+"/"+tmp[5]+")");
        }

        if(goodResult) {
            return mytmp;
        }

        return mytmp;
    }



    public static void main(String[] args) {

        for(int a=0; a<100; a++){
            int tmp[] = CreateLevel(5,11,4,5,true,true);
            if(tmp[0] == tmp[1] && tmp[0] > 0){
                System.out.println("RED FAILED AT "+a+": "+tmp[0]+"/"+tmp[1]);
            } else if(tmp[2] == tmp[3] && tmp[2] > 0) {
                System.out.println("BLUE FAILED AT "+a+": "+tmp[2]+"/"+tmp[3]);
            } else if(tmp[4] == tmp[5] && tmp[4] > 0) {
                System.out.println("GREEN FAILED AT "+a+": "+tmp[4]+"/"+tmp[5]);
            } else {
                System.out.println("Generated OK level "+a+": ("+tmp[0]+"/"+tmp[1]+"), ("+tmp[2]+"/"+tmp[3]+"), "+tmp[4]+"/"+tmp[5]+")");
            }
            //System.out.println("("+tmp[0]+"/"+tmp[1]+") ("+tmp[2]+"/"+tmp[3]+") ("+tmp[4]+"/"+tmp[5]+")");
        }

    }
}
