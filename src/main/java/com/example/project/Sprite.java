package com.example.project;

public class Sprite {
    private int x, y;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;}
    public int getY(){return y;}

    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }

    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "(" + x + "," + y + ")";
    }

    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]"
        int rowNum = size - 1 - y;
        return "[" + rowNum + "]" + "[" + x + "]";
    }
    

    public void move(String direction) { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }



}
