package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x, y);
        treasureCount = 0;
        numLives = 2;
    }


    public int getTreasureCount(){return treasureCount;}
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}

    public void setLives(int lives) { // method used for the extra credit
        numLives = lives;
    }

  
    //move method should override parent class, sprite
    @Override
    public void move(String direction) { //move the (x,y) coordinates of the player
        if (direction.equals("w")) { // w moves Player one tile up
            setY(getY() + 1);
        } else if (direction.equals("a")) { // a moves Player one tile to the left
            setX(getX() - 1);
        } else if (direction.equals("s")) { // s moves Player one tile down
            setY(getY() - 1);
        } else if (direction.equals("d")) { // d moves Player one tile to the right
            setX(getX() + 1);
        }
    }


    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
    //numTreasures is the total treasures at the beginning of the game
        if (obj instanceof Trophy) {
            if (treasureCount >= numTreasures) { // if obj is a Trophy object and Player's treasureCount is >= numTreasures, they win
                win = true;
            }
        } else if (obj instanceof Treasure) { // if obj is a Treasure object and not a Trophy object, Player's treasureCount increases by 1
            treasureCount++;
        } else if (obj instanceof Enemy) { // if obj is an Enemy object, Player's numLives decreases by 1
            numLives--;
        }
    }


    public boolean isValid(int size, String direction){ //check grid boundaries
        if (direction.equals("w")) { // checks which direction
            if (getY() + 1 < size) { // if y + 1 is less than size, it is within the grid boundaries and Player can move in that direction
                return true;
            }
        } else if (direction.equals("a")) { // checks which direction
            if (getX() - 1 >= 0) { // if x - 1 is greater than or equal to size, it is within the grid boundaries and Player can move in that direction
                return true;
            }
        } else if (direction.equals("s")) { // checks which direction
            if (getY() - 1 >= 0) { // if y - 1 is greater than or equal to size, it is within the grid boundaries and Player can move in that direction
                return true;
            }
        } else if (direction.equals("d")) { // checks which direction
            if (getX() + 1 < size) { // if x + 1 is less than size, it is within the grid boundaries and Player can move in that direction
                return true;
            }
        }
        return false; // if all conditions fail, the direction to move will result in moving out of the grid boundaries, so the method returns false because Player can't move in that direction
    }


    @Override
    public String getCoords() {
        return "Player:" + super.getCoords(); // returns the x-y coordinates of Player
    }

    @Override
    public String getRowCol(int size) {
        return "Player:" + super.getRowCol(size); // returns the row and column Player is at
    }
}



