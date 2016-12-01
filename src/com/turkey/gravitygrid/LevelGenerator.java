package com.turkey.gravitygrid;

/**
 * Created by lawsonje on 11/15/2016.
 */
/*
 * Copyright (c) 2016 Jesse Lawson. All Rights Reserved. No part of this code may be redistributed, reused, or otherwise used in any way, shape, or form without written permission from the author.
 */

import java.util.ArrayList;

import java.util.concurrent.ThreadLocalRandom;

import java.util.ArrayList;

public class LevelGenerator {

    /* Board components. Helps differentiate between edges/corners and middle of board. */
    private static final int[] boardMiddle = new int[] { 36,37,38,39,40,29,30,31,32,33,22,23,24,25,26,15,16,17,18,19,8,9,10,11,12 };
    private static final int[] boardEdge = new int[] { 42,43,44,45,46,47,48,35,28,21,14,7,0,1,2,3,4,5,6,13,20,27,34,41 };

    // IntArrayContains is used specifically for the above boardMiddle and boardEdge arrays and in the canMoveAccordingToLogic function
    private boolean IntArrayContains(int[] list, int number) {

        for(int i=0; i<list.length; i++) {
            if(list[i] == number) {
                return true;
            }
        }
        return false;
    }

    // This array holds the values of each tile
    private static final int[] tileValueTable = new int[] { 0,1,3,5,3,1,0,1,2,4,6,4,2,1,3,4,6,8,6,4,3,5,6,8,10,8,6,5,3,4,6,8,6,4,3,1,2,4,6,4,2,1,0,1,3,5,3,1,0 };

    private enum TileType {
        NONE, REDPLANET, BLUEPLANET, GREENPLANET, ASTEROID, SUN, BLOCKED
    }

    private class Tile {
        public TileType type;
        boolean isSelected;

        public int tileNum;
        public int value;

        public Tile(int value, int tileNum) {
            this.type = TileType.NONE; // default
            this.value = value;
            this.tileNum = tileNum;
            isSelected = false;
        }
    }

    public boolean GoodMoveVerified(ArrayList<Tile> tile, int tileToCheck, TileType selectedType, int selectedNum) {
        if( tile.get(tileToCheck).type == selectedType && tile.get(tileToCheck).tileNum != selectedNum ) {
            System.out.println("(3/4): Good tile "+tileToCheck+" found for selected num "+selectedNum);
            return true;
        } else {
            return false;
        }
    }

    public boolean canMoveAccordingToRules(ArrayList<Tile> tile, int destinationTileNum) {

        // First determine the select tile's tileNum so we can get it's TileType
        TileType selectedType = TileType.NONE; // init'd
        int selectedNum = 0; // init'd

        // This `outcome` is what is returned by this function. If outcome = true, then the function has determined
        // that the tile in question "can move according to rules"
        boolean outcome = false;

        // Loop through tiles and find the selected one
        findSelected:
        for (Tile theTile : tile) {
            // Check for selected tile
            if (theTile.isSelected) {
                // Extract the type of the selected tile so we can assign the appropriate rules to the planet type
                selectedType = theTile.type;
                selectedNum = theTile.tileNum;
                System.out.println("(2/4): canMoveAccordingToRules: Tile #"+selectedNum+" ("+selectedType+") is selected. Trying to move to "+destinationTileNum+"...");
                break findSelected;
            }
        }
        // Now we're in the meat and potatoes. The function seeks to answer: can a tile of type selectedType move to
        // the destinationTileNum type?

        switch (selectedType) {

            case REDPLANET:

                // First check the body of the board. If the body doesn't return true, try the edges and corners (the most complicated of the logic because we check them each individually).
                if (IntArrayContains(boardMiddle, destinationTileNum)) {
                    // Check the middle section of the board (i.e., all tiles except edges and corners)
                    if (
                            (GoodMoveVerified(tile,destinationTileNum + 8, selectedType, selectedNum))
                                    || (GoodMoveVerified(tile,destinationTileNum + 6, selectedType, selectedNum))
                                    || (GoodMoveVerified(tile,destinationTileNum - 6, selectedType, selectedNum))
                                    || (GoodMoveVerified(tile,destinationTileNum - 8, selectedType, selectedNum))) {
                        outcome = true;
                        System.out.println("destinationTileNum: " + destinationTileNum + ". SelectedNum: " + selectedNum);
                        return outcome; // Leave this function immediately!
                    }

                } else {
                    // If we're here, then we're on an edge or corner tile. We need to check these manually... Previous attempts at complicated if-else statements have proven unwieldy and confusing.
                    // So why not this switch in a switch?

                    switch (destinationTileNum) {
                        case 42: outcome = GoodMoveVerified(tile,36,selectedType,selectedNum); break;
                        case 43:
                        case 44:
                        case 45:
                        case 46:
                        case 47: outcome = (GoodMoveVerified(tile,destinationTileNum-6,selectedType,selectedNum) || GoodMoveVerified(tile,destinationTileNum-8,selectedType,selectedNum)); break;
                        case 48: outcome = GoodMoveVerified(tile,40,selectedType,selectedNum); break;
                        case 41:
                        case 34:
                        case 27:
                        case 20:
                        case 13: outcome = (GoodMoveVerified(tile,destinationTileNum+6,selectedType,selectedNum) || GoodMoveVerified(tile,destinationTileNum-8,selectedType,selectedNum)); break;
                        case  6: outcome = GoodMoveVerified(tile,12,selectedType,selectedNum); break;
                        case  5:
                        case  4:
                        case  3:
                        case  2:
                        case  1: outcome = (GoodMoveVerified(tile,destinationTileNum+6,selectedType,selectedNum) || GoodMoveVerified(tile,destinationTileNum+8,selectedType,selectedNum)); break;
                        case  0: outcome = GoodMoveVerified(tile,8,selectedType,selectedNum); break;
                        case  7:
                        case 14:
                        case 21:
                        case 28:
                        case 35: outcome = (GoodMoveVerified(tile,destinationTileNum+8,selectedType,selectedNum) || GoodMoveVerified(tile,destinationTileNum-6,selectedType,selectedNum)); break;
                        default: break;
                    }

                    return outcome; // Break immediately because there's no point in continuing
                }

                break;
            case BLUEPLANET:
                // First check the body of the board. If the body doesn't return true, try the edges and corners (the most complicated of the logic because we check them each individually).
                if (IntArrayContains(boardMiddle, destinationTileNum)) {
                    // Check the middle section of the board (i.e., all tiles except edges and corners)
                    if (
                            (GoodMoveVerified(tile,destinationTileNum + 1, selectedType, selectedNum))
                                    || (GoodMoveVerified(tile,destinationTileNum - 1, selectedType, selectedNum))
                                    || (GoodMoveVerified(tile,destinationTileNum + 7, selectedType, selectedNum))
                                    || (GoodMoveVerified(tile,destinationTileNum - 7, selectedType, selectedNum))) {
                        outcome = true;
                        System.out.println("destinationTileNum: " + destinationTileNum + ". SelectedNum: " + selectedNum);
                        return outcome; // Leave this function immediately!
                    }

                } else {
                    // If we're here, then we're on an edge or corner tile. We need to check these manually... Previous attempts at complicated if-else statements have proven unwieldy and confusing.
                    // So why not this switch in a switch?

                    switch (destinationTileNum) {
                        case 42: outcome = ( GoodMoveVerified(tile,35,selectedType,selectedNum) || GoodMoveVerified(tile,43,selectedType,selectedNum) ); break;
                        case 43:
                        case 44:
                        case 45:
                        case 46:
                        case 47: outcome = (GoodMoveVerified(tile,destinationTileNum-1,selectedType,selectedNum) || GoodMoveVerified(tile,destinationTileNum+1,selectedType,selectedNum) || GoodMoveVerified(tile,destinationTileNum-7,selectedType,selectedNum)); break;
                        case 48: outcome = ( GoodMoveVerified(tile,47,selectedType,selectedNum) || GoodMoveVerified(tile,41,selectedType,selectedNum) ); break;
                        case 41:
                        case 34:
                        case 27:
                        case 20:
                        case 13: outcome = (GoodMoveVerified(tile,destinationTileNum-1,selectedType,selectedNum) || GoodMoveVerified(tile,destinationTileNum-7,selectedType,selectedNum) || GoodMoveVerified(tile,destinationTileNum+7,selectedType,selectedNum)); break;
                        case  6: outcome = ( GoodMoveVerified(tile,5,selectedType,selectedNum) || GoodMoveVerified(tile,13,selectedType,selectedNum) ); break;
                        case  5:
                        case  4:
                        case  3:
                        case  2:
                        case  1: outcome = (GoodMoveVerified(tile,destinationTileNum-1,selectedType,selectedNum) || GoodMoveVerified(tile,destinationTileNum+1,selectedType,selectedNum) || GoodMoveVerified(tile,destinationTileNum+7,selectedType,selectedNum)); break;
                        case  0: outcome = ( GoodMoveVerified(tile,7,selectedType,selectedNum) || GoodMoveVerified(tile,1,selectedType,selectedNum) ); break;
                        case  7:
                        case 14:
                        case 21:
                        case 28:
                        case 35: outcome = (GoodMoveVerified(tile,destinationTileNum-7,selectedType,selectedNum) || GoodMoveVerified(tile,destinationTileNum+1,selectedType,selectedNum) || GoodMoveVerified(tile,destinationTileNum+7,selectedType,selectedNum)); break;
                        default: break;
                    }

                    return outcome; // Break immediately because there's no point in continuing
                }

                break;

            case GREENPLANET:
                // First check the body of the board. If the body doesn't return true, try the edges and corners (the most complicated of the logic because we check them each individually).
                if (IntArrayContains(boardMiddle, destinationTileNum)) {
                    // Check the middle section of the board (i.e., all tiles except edges and corners)
                    if (    // For green planets, it's left, right, and all three on top and three on bottom
                            !GoodMoveVerified(tile,destinationTileNum - 1, selectedType, selectedNum)
                                    && !GoodMoveVerified(tile,destinationTileNum - 8, selectedType, selectedNum)
                                    && !GoodMoveVerified(tile,destinationTileNum - 7, selectedType, selectedNum)
                                    && !GoodMoveVerified(tile,destinationTileNum - 6, selectedType, selectedNum)
                                    && !GoodMoveVerified(tile,destinationTileNum + 1, selectedType, selectedNum)
                                    && !GoodMoveVerified(tile,destinationTileNum + 6, selectedType, selectedNum)
                                    && !GoodMoveVerified(tile,destinationTileNum + 7, selectedType, selectedNum)
                                    && !GoodMoveVerified(tile,destinationTileNum + 8, selectedType, selectedNum)
                            ) {
                        outcome = true;
                        System.out.println("destinationTileNum: " + destinationTileNum + ". SelectedNum: " + selectedNum);
                        return outcome; // Leave this function immediately!
                    }

                } else {
                    // If we're here, then we're on an edge or corner tile. We need to check these manually... Previous attempts at complicated if-else statements have proven unwieldy and confusing.
                    // So why not this switch in a switch?

                    switch (destinationTileNum) {
                        case 42: outcome = ( !GoodMoveVerified(tile,35,selectedType,selectedNum) && !GoodMoveVerified(tile,43,selectedType,selectedNum) && !GoodMoveVerified(tile,36,selectedType,selectedNum) ); break;
                        case 43:
                        case 44:
                        case 45:
                        case 46:
                        case 47: outcome = (!GoodMoveVerified(tile,destinationTileNum-1,selectedType,selectedNum) && !GoodMoveVerified(tile,destinationTileNum+1,selectedType,selectedNum) && !GoodMoveVerified(tile,destinationTileNum-6,selectedType,selectedNum) && !GoodMoveVerified(tile,destinationTileNum-7,selectedType,selectedNum) && !GoodMoveVerified(tile,destinationTileNum-8,selectedType,selectedNum)); break;
                        case 48: outcome = ( !GoodMoveVerified(tile,47,selectedType,selectedNum) && !GoodMoveVerified(tile,40,selectedType,selectedNum) && !GoodMoveVerified(tile,41,selectedType,selectedNum)); break;
                        case 41:
                        case 34:
                        case 27:
                        case 20:
                        case 13: outcome = ( !GoodMoveVerified(tile,destinationTileNum+6,selectedType,selectedNum) && !GoodMoveVerified(tile,destinationTileNum+7,selectedType,selectedNum) && !GoodMoveVerified(tile,destinationTileNum-1,selectedType,selectedNum) && !GoodMoveVerified(tile,destinationTileNum-6,selectedType,selectedNum) && !GoodMoveVerified(tile,destinationTileNum-7,selectedType,selectedNum)); break;
                        case  6: outcome = ( !GoodMoveVerified(tile,5,selectedType,selectedNum) && !GoodMoveVerified(tile,12,selectedType,selectedNum) && !GoodMoveVerified(tile,13,selectedType,selectedNum) ); break;
                        case  5:
                        case  4:
                        case  3:
                        case  2:
                        case  1: outcome = ( !GoodMoveVerified(tile,destinationTileNum+6,selectedType,selectedNum) && !GoodMoveVerified(tile,destinationTileNum+7,selectedType,selectedNum) && !GoodMoveVerified(tile,destinationTileNum+8,selectedType,selectedNum) && !GoodMoveVerified(tile,destinationTileNum-1,selectedType,selectedNum) && !GoodMoveVerified(tile,destinationTileNum+1,selectedType,selectedNum)); break;
                        case  0: outcome = ( !GoodMoveVerified(tile,7,selectedType,selectedNum) && !GoodMoveVerified(tile,8,selectedType,selectedNum) && !GoodMoveVerified(tile,1,selectedType,selectedNum) ); break;
                        case  7:
                        case 14:
                        case 21:
                        case 28:
                        case 35: outcome = ( !GoodMoveVerified(tile,destinationTileNum+6,selectedType,selectedNum) && !GoodMoveVerified(tile,destinationTileNum+7,selectedType,selectedNum) && !GoodMoveVerified(tile,destinationTileNum+1,selectedType,selectedNum) && !GoodMoveVerified(tile,destinationTileNum-6,selectedType,selectedNum) && !GoodMoveVerified(tile,destinationTileNum-7,selectedType,selectedNum)); break;
                        default: break;
                    }

                    return outcome; // Break immediately because there's no point in continuing
                }

                break;
            default:
                System.out.println("canMoveAccordingToRules: No Planet Type understood!");
                break;
        }
        return outcome;
    }

    // The generator works by creating a level backwards. You start with a random organization of planets and non-movables according to the desired set of rules passed.

    public int[] GenerateLevel(int complexity, int redWeight, int blueWeight, int greenWeight, boolean genAsteroids, boolean genSuns) {

        ArrayList<Tile> tile = new ArrayList<Tile>();

        // These will be the scores needed to beat the level
        int numMovesNeeded = 0;
        int numRedNeeded = 0;
        int numBlueNeeded = 0;
        int numGreenNeeded = 0;

        // Running totals to make sure we aren't accidentally creating a level with no difference in value
        int thisLevelCurrentRedTotal = 0;
        int thisLevelCurrentBlueTotal = 0;
        int thisLevelCurrentGreenTotal = 0;

        int[] levelFinal = {
                0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0
        };

        // Keeps track of our movements during level generation so we don't choose a tile we've already been to.
        // Anytime a planet has been on a tile or is on a tile or moves to a tile, it's flagged. At the end, if there are any remaining
        // tiles, we can stuff them full of asteroids
        int[] levelFlagged = {
                0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0
        };

        // First step is to randomly place planets and non-movables according to 1) whether we say to use them, and 2) how complex we want to make the level.
        // Complexity is defined as the minimum number of moves required to beat the level.

        int tileNum = 0;

        System.out.println("Creating new level.");

        // Instantiate a dummy map to use
        for(int r = 6; r > -1; r--) {
            for(int c = 0; c < 7; c++) {
                tile.add(new Tile(tileValueTable[(r*7)+c], tileNum));
                tileNum++;
            }
        }

        System.out.println("Map array instantiated.");

        // Now we have an ArrayList full of tiles. Let's stick some tiles in there.

        int numRedTiles = redWeight;//ThreadLocalRandom.current().nextInt(2,redWeight); // use 2 here to always have at least one planet to move to another planet
        int numBlueTiles = blueWeight;//(blueWeight > 0 ? ThreadLocalRandom.current().nextInt(0,blueWeight) : 0);
        int numGreenTiles = greenWeight;//(greenWeight > 0 ? ThreadLocalRandom.current().nextInt(0,greenWeight) : 0);

        System.out.println("Creating new level with "+numRedTiles+" red, "+numBlueTiles+" blue, and "+numGreenTiles+" green.");

        // Now, randomly go through the level map and place a random tile
        // Start with red
        for(int a=0; a<(numRedTiles); a++) {

            int randomTile = ThreadLocalRandom.current().nextInt(0,48);

            if(levelFlagged[randomTile] == 0) {
                tile.get(randomTile).type = TileType.REDPLANET;
                levelFlagged[randomTile] = 1; // Flag it so we don't select it next time
            } else {
                a--; // If we selected a flagged tile then we don't want this iteration to count
            }
        }

        System.out.println("Red tiles handled.");

        // Next up is blue
        for(int a=0; a<(numBlueTiles); a++) {

            int randomTile = ThreadLocalRandom.current().nextInt(0,48);

            if(levelFlagged[randomTile] == 0) {
                tile.get(randomTile).type = TileType.BLUEPLANET;
                levelFlagged[randomTile] = 1; // Flag it so we don't select it next time
            } else {
                a--; // If we selected a flagged tile then we don't want this iteration to count
            }
        }

        System.out.println("Blue tiles handled.");

        // Finally, we do green
        for(int a=0; a<(numGreenTiles); a++) {

            int randomTile = ThreadLocalRandom.current().nextInt(0,48);

            if(levelFlagged[randomTile] == 0) {
                tile.get(randomTile).type = TileType.GREENPLANET;
                levelFlagged[randomTile] = 1; // Flag it so we don't select it next time
            } else {
                a--; // If we selected a flagged tile then we don't want this iteration to count
            }
        }

        System.out.println("Green tiles handled.");

        // At this point, we have a map with random planets everywhere, and the levelFlagged has been updated.
        // This representation of the planets is going to give us the level value totals to strive for after we have moved the planets
        // around a bit.

        // We can get the values needed for each planet color for the level now.
        // Flush the current scores
        numRedNeeded = 0;
        numBlueNeeded = 0;
        numGreenNeeded = 0;
        thisLevelCurrentGreenTotal = 0;
        thisLevelCurrentRedTotal = 0;
        thisLevelCurrentBlueTotal = 0;
        // Also, we should update our value totals that we'll use below during our complexity movements
        // Flush the current scores
        thisLevelCurrentBlueTotal = 0;
        thisLevelCurrentRedTotal = 0;
        thisLevelCurrentGreenTotal = 0;

        // Iterate through each tile and update the current color values
        for(Tile theTile : tile) {
            switch(theTile.type) {
                case REDPLANET:
                    thisLevelCurrentRedTotal += theTile.value;
                    break;
                case BLUEPLANET:
                    thisLevelCurrentBlueTotal += theTile.value;
                    break;
                case GREENPLANET:
                    thisLevelCurrentGreenTotal += theTile.value;
                    break;
                default: break;
            }
        }

        numRedNeeded = thisLevelCurrentRedTotal;
        numBlueNeeded = thisLevelCurrentBlueTotal;
        numGreenNeeded = thisLevelCurrentGreenTotal;

        System.out.println("Level values set (red: "+numRedNeeded+", blue: "+numBlueNeeded+", green:"+numGreenNeeded+")");

        // The last thing we need is the par value moves for the level, which we will get by moving tiles around. In doing so, we
        // will also get the actual level we will deliver to the player.

        // Now, for every complexity point, let's move a random tile to a non-levelFlagged square according to that tile's rules.
        // Sometimes we might move the same tile a few times, other times we might just move other tiles. Who knows.


        for(int i=0; i<complexity; i++) {

            int selectedValue = 0;  // the value of the selected tile
            int selectedNum = 0;    // the tileNum of the selected tile

            TileType selectedType = TileType.NONE;

            // Select a random tile that is a planet
            boolean selected = false;
            System.out.println("Looking for a tile for move #"+i);
            while( !selected ) {

                int rand = ThreadLocalRandom.current().nextInt(0,48);

                // Check if this is a planet tile
                if( tile.get(rand).type != TileType.NONE ) {

                    tile.get(rand).isSelected = true;
                    selectedValue = tile.get(rand).value;
                    selectedNum = tile.get(rand).tileNum;
                    selectedType = tile.get(rand).type;
                    // We don't have to worry about flagging it in levelFlagged because it already has been flagged
                    selected = true;
                    System.out.println("> Selecting tile #"+tile.get(rand).tileNum+" ("+tile.get(rand).type+")");
                } else {
                    System.out.println("> Skipping tile #"+tile.get(rand).tileNum);
                }

            }

            // At this point, we have selected a random a tile that is not of type NONE.
            // Next, let's select a random destination for that tile. We must make sure of two things:
            // 1) we should not move this tile to another tile of the same value, and 2) we should not
            // move this tile against the rules. We will check for both of these before committing to
            // a move.
            boolean goodDestinationFound = false;

            while( !goodDestinationFound ) {

                int rand = ThreadLocalRandom.current().nextInt(0, 48);

                System.out.println("Checking tile "+tile.get(rand).tileNum+" for being a good destination for selection "+selectedNum+"...");

                // Great! We found a blank tile. Not so fast, though. Let's check to see if this
                // new tile has a value different from the old one.

                // Let's ensure the location hasn't been flagged yet
                if (levelFlagged[tile.get(rand).tileNum] == 0) {

                    System.out.println("(1/4): Tile #" + tile.get(rand).tileNum + " has not already been flagged...");

                    // Finally, let's make sure we can move according to rules
                    if (canMoveAccordingToRules(tile, tile.get(rand).tileNum)) {

                        System.out.println("(4/4): Tile #" + tile.get(rand).tileNum + " satisfies movement rules!");

                        // Destination
                        tile.get(rand).type = selectedType;         // Set destination to the selectedType
                        levelFlagged[tile.get(rand).tileNum] = 1;   // Flag the location as dirty

                        // Previous Location
                        tile.get(selectedNum).type = TileType.NONE; // Set the old one to none
                        tile.get(selectedNum).isSelected = false; // Set the destination to none

                        // Flush the current scores
                        thisLevelCurrentBlueTotal = 0;
                        thisLevelCurrentRedTotal = 0;
                        thisLevelCurrentGreenTotal = 0;

                        // Iterate through each tile and update the current color values
                        for(Tile theTile : tile) {
                            switch(theTile.type) {
                                case REDPLANET:
                                    thisLevelCurrentRedTotal += theTile.value;
                                    break;
                                case BLUEPLANET:
                                    thisLevelCurrentBlueTotal += theTile.value;
                                    break;
                                case GREENPLANET:
                                    thisLevelCurrentGreenTotal += theTile.value;
                                    break;
                                default: break;
                            }
                        }

                        goodDestinationFound = true;

                        //if (thisLevelCurrentBlueTotal == numBlueNeeded || thisLevelCurrentRedTotal == numRedNeeded || thisLevelCurrentGreenTotal == numGreenNeeded) {
                        //goodDestinationFound = false; // Reset this if now that we've moved we have the same damn value as we began with.
                        //}

                    } else {continue;}

                } else {continue;}
            }
            numMovesNeeded++; // Increment our par moves counter... I guess this is just equal to the complexity, isn't it, Jesse?
        }

        // At this point, we have all the things we need for our level. We should go through and look for places to stick an asteroid and
        // a sun, or both, or combinations.

        if(genAsteroids) {
            // Suns are not generated if complexity < 2
            // Asteroids will show up anytime

            // First figure out how many possible total asteroids we can get
            ArrayList<Integer> possibleAsteroids = new ArrayList<Integer>();

            for(int a=0; a<49; a++) {
                if(levelFlagged[a] == 0) {
                    possibleAsteroids.add(a);
                }
            }

            // Now, every possibleAsteroids element is a tile that can be set to an asteroid if we want

            if(possibleAsteroids.size() > 0) {
                int numAsteroids = ThreadLocalRandom.current().nextInt(1, possibleAsteroids.size()); //(int) Math.log((double) ThreadLocalRandom.current().nextInt(1, complexity + 1) * (numRedNeeded + numBlueNeeded + numGreenNeeded)); // gen number of asteroids

                for (int a = 0; a < numAsteroids; a++) {

                    int rand = possibleAsteroids.get(ThreadLocalRandom.current().nextInt(0, possibleAsteroids.size()));
                    System.out.println("Trying to put an asteroid at tile " + rand + "...");

                    boolean placed = false;
                    while (!placed) {
                        if (levelFlagged[rand] == 0) {
                            // This tile has never been used. Stick an asteroid there!
                            tile.get(rand).type = TileType.ASTEROID;
                            placed = true;
                        }
                    }
                }
            } else {
                System.out.println("I was told to place asteroids, but there's no spot for asteroids!");
            }
        }

        if(genSuns) {

            // First figure out how many possible total suns we can get
            ArrayList<Integer> possibleSuns = new ArrayList<Integer>();

            for(int a=0; a<49; a++) {

                if(IntArrayContains(boardEdge, a)) {
                    // Check for squares specially
                    switch (a) {
                        case 42: if(levelFlagged[35] == 0 && levelFlagged[43] == 0 && levelFlagged[36] == 0) { possibleSuns.add(a); } break;
                        case 43:
                        case 44:
                        case 45:
                        case 46:
                        case 47: if(levelFlagged[a-1] == 0 && levelFlagged[a+1] == 0 && levelFlagged[a-6] == 0 && levelFlagged[a-7] == 0 && levelFlagged[a-8] == 0) { possibleSuns.add(a); } break;
                        case 48: if(levelFlagged[47] == 0 && levelFlagged[40] == 0 && levelFlagged[41] == 0) { possibleSuns.add(a); } break;
                        case 41:
                        case 34:
                        case 27:
                        case 20:
                        case 13: if(levelFlagged[a+6] == 0 && levelFlagged[a+1] == 0 && levelFlagged[a+7] == 0 && levelFlagged[a-1] == 0 && levelFlagged[a-6] == 0 && levelFlagged[a-7] == 0) { possibleSuns.add(a); } break;
                        case  6: if(levelFlagged[5] == 0 && levelFlagged[12] == 0 && levelFlagged[13] == 0) { possibleSuns.add(a); } break;
                        case  5:
                        case  4:
                        case  3:
                        case  2:
                        case  1: if(levelFlagged[a+6] == 0 && levelFlagged[a+7] == 0 && levelFlagged[a+8] == 0 && levelFlagged[a-1] == 0 && levelFlagged[a+1] == 0) { possibleSuns.add(a); } break;
                        case  0: if(levelFlagged[7] == 0 && levelFlagged[8] == 0 && levelFlagged[1] == 0) { possibleSuns.add(a); } break;
                        case  7:
                        case 14:
                        case 21:
                        case 28:
                        case 35: if(levelFlagged[a+6] == 0 && levelFlagged[a+7] == 0 && levelFlagged[a+1] == 0 && levelFlagged[a-6] == 0 && levelFlagged[a-7] == 0) { possibleSuns.add(a); } break;
                        default: break;
                    }
                } else if (IntArrayContains(boardMiddle, a)) {
                    // Check the middle section of the board (i.e., all tiles except edges and corners)
                    if (    // For green planets, it's left, right, and all three on top and three on bottom
                            levelFlagged[a-1] == 0 &&
                            levelFlagged[a+1] == 0 &&
                            levelFlagged[a-6] == 0 &&
                            levelFlagged[a-7] == 0 &&
                            levelFlagged[a-8] == 0 &&
                            levelFlagged[a+6] == 0 &&
                            levelFlagged[a+7] == 0 &&
                            levelFlagged[a+8] == 0
                            ) {
                        possibleSuns.add(a);
                    }
                } else {
                    // do nothing
                }
            }

            // Now, every possibleSuns element is a tile that can be set to an asteroid if we want

            if(possibleSuns.size() > 0) {
                int numSuns = (possibleSuns.size() == 1 ? 1 : ThreadLocalRandom.current().nextInt(1, possibleSuns.size())); //(int) Math.log((double) ThreadLocalRandom.current().nextInt(1, complexity + 1) * (numRedNeeded + numBlueNeeded + numGreenNeeded)); // gen number of asteroids

                for (int a = 0; a < numSuns; a++) {

                    int rand = possibleSuns.get(ThreadLocalRandom.current().nextInt(0, possibleSuns.size()));
                    System.out.println("Trying to put a sun at tile " + rand + "...");

                    boolean placed = false;
                    while (!placed) {
                        if(IntArrayContains(boardEdge, a)) {
                            // Check for squares specially
                            switch (rand) {
                                case 42: if(levelFlagged[35] == 0 && levelFlagged[43] == 0 && levelFlagged[36] == 0) { tile.get(rand).type = TileType.SUN;tile.get(35).type = TileType.BLOCKED;tile.get(43).type = TileType.BLOCKED;tile.get(36).type = TileType.BLOCKED;placed = true; } break;
                                case 43:
                                case 44:
                                case 45:
                                case 46:
                                case 47: if(levelFlagged[rand-1] == 0 && levelFlagged[rand+1] == 0 && levelFlagged[rand-6] == 0 && levelFlagged[rand-7] == 0 && levelFlagged[rand-8] == 0) {
                                    tile.get(rand).type = TileType.SUN;
                                    tile.get(rand-1).type = TileType.BLOCKED;tile.get(rand+1).type = TileType.BLOCKED;tile.get(rand-6).type = TileType.BLOCKED;tile.get(rand-7).type = TileType.BLOCKED;tile.get(rand-8).type = TileType.BLOCKED; placed = true;; } break;
                                case 48: if(levelFlagged[47] == 0 && levelFlagged[40] == 0 && levelFlagged[41] == 0) {
                                    tile.get(rand).type = TileType.SUN;tile.get(47).type = TileType.BLOCKED;tile.get(40).type = TileType.BLOCKED;tile.get(41).type = TileType.BLOCKED;placed = true; } break;
                                case 41:
                                case 34:
                                case 27:
                                case 20:
                                case 13: if(levelFlagged[rand+6] == 0 && levelFlagged[rand+1] == 0 && levelFlagged[rand+7] == 0 && levelFlagged[rand-1] == 0 && levelFlagged[rand-6] == 0 && levelFlagged[rand-7] == 0) {
                                    tile.get(rand).type = TileType.SUN;tile.get(rand+6).type = TileType.BLOCKED;tile.get(rand+1).type = TileType.BLOCKED;tile.get(rand+7).type = TileType.BLOCKED;tile.get(rand-1).type = TileType.BLOCKED;tile.get(rand-6).type = TileType.BLOCKED;tile.get(rand-7).type = TileType.BLOCKED;placed = true; } break;
                                case  6: if(levelFlagged[5] == 0 && levelFlagged[12] == 0 && levelFlagged[13] == 0) {
                                    tile.get(rand).type = TileType.SUN;tile.get(5).type = TileType.BLOCKED;tile.get(12).type = TileType.BLOCKED;tile.get(13).type = TileType.BLOCKED;placed = true; } break;
                                case  5:
                                case  4:
                                case  3:
                                case  2:
                                case  1: if(levelFlagged[rand+6] == 0 && levelFlagged[rand+7] == 0 && levelFlagged[rand+8] == 0 && levelFlagged[rand-1] == 0 && levelFlagged[rand+1] == 0) {
                                    tile.get(rand).type = TileType.SUN;tile.get(rand+6).type = TileType.BLOCKED;tile.get(rand+7).type = TileType.BLOCKED;tile.get(rand+8).type = TileType.BLOCKED;tile.get(rand-1).type = TileType.BLOCKED;tile.get(rand+1).type = TileType.BLOCKED;placed = true; } break;
                                case  0: if(levelFlagged[7] == 0 && levelFlagged[8] == 0 && levelFlagged[1] == 0) {
                                    tile.get(rand).type = TileType.SUN;tile.get(7).type = TileType.BLOCKED;tile.get(8).type = TileType.BLOCKED;tile.get(1).type = TileType.BLOCKED;placed = true; } break;
                                case  7:
                                case 14:
                                case 21:
                                case 28:
                                case 35: if(levelFlagged[rand+6] == 0 && levelFlagged[rand+7] == 0 && levelFlagged[rand+1] == 0 && levelFlagged[rand-6] == 0 && levelFlagged[rand-7] == 0) {
                                    tile.get(rand).type = TileType.SUN;tile.get(rand+6).type = TileType.BLOCKED;tile.get(rand+7).type = TileType.BLOCKED;tile.get(rand+1).type = TileType.BLOCKED;tile.get(rand-6).type = TileType.BLOCKED;tile.get(rand-7).type = TileType.BLOCKED;placed = true; } break;
                                default: break;
                            }
                        } else if (IntArrayContains(boardMiddle, a)) {
                            // Check the middle section of the board (i.e., all tiles except edges and corners)
                            if (    // For green planets, it's left, right, and all three on top and three on bottom
                                    levelFlagged[rand-1] == 0 &&
                                            levelFlagged[rand+1] == 0 &&
                                            levelFlagged[rand-6] == 0 &&
                                            levelFlagged[rand-7] == 0 &&
                                            levelFlagged[rand-8] == 0 &&
                                            levelFlagged[rand+6] == 0 &&
                                            levelFlagged[rand+7] == 0 &&
                                            levelFlagged[rand+8] == 0
                                    ) {
                                tile.get(rand).type = TileType.SUN;
                                tile.get(rand+6).type = TileType.BLOCKED;
                                tile.get(rand+7).type = TileType.BLOCKED;
                                tile.get(rand+8).type = TileType.BLOCKED;
                                tile.get(rand-1).type = TileType.BLOCKED;
                                tile.get(rand+1).type = TileType.BLOCKED;
                                tile.get(rand-6).type = TileType.BLOCKED;
                                tile.get(rand-7).type = TileType.BLOCKED;
                                tile.get(rand-8).type = TileType.BLOCKED;
                                
                                        placed = true;
                            }
                        } else {
                            // do nothing
                        }
                        }
                    }
                } else {
                System.out.println("I was told to place suns, but there's no spot for suns!");
            }
        }

        // Finally, let's construct the actual level string
        for(int r = 6; r > -1; r--) {
            for(int c = 0; c < 7; c++) {

                int position = (r*7)+c; // Where we are in the tile ArrayList
                int typeToWrite = 0;    // What number to write to the level file
                // Convert the TileType to a number for the level string
                // Determine the tile type
                switch( tile.get(position).type ) {
                    case NONE:
                        typeToWrite = 0;
                        break;
                    case REDPLANET:
                        typeToWrite = 1;
                        break;
                    case BLUEPLANET:
                        typeToWrite = 2;
                        break;
                    case GREENPLANET:
                        typeToWrite = 3;
                        break;
                    case ASTEROID:
                        typeToWrite = 4;
                        break;
                    case SUN:
                        typeToWrite = 5;
                        break;
                    case BLOCKED:
                        typeToWrite = 9;
                        break;
                    default:
                        typeToWrite = 0;
                        break;
                }

                levelFinal[position] = typeToWrite;

                tileNum++;
            }
        }

        // Add the final elements of the level string
        levelFinal[49] = numRedNeeded;
        levelFinal[50] = numBlueNeeded;
        levelFinal[51] = numGreenNeeded;
        levelFinal[52] = numMovesNeeded;

        System.out.println("Level generated!");
        System.out.println("Creating new level with "+numRedTiles+" red, "+numBlueTiles+" blue, and "+numGreenTiles+" green.");

        return levelFinal;
    }
}