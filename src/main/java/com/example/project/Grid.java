package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        grid = new Sprite[size][size]; // creates a 2D array with size number of rows and columns
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) { // iterates through every element in grid and sets it to a Dot object
                grid[size - 1 - i][j] = new Dot(j, i); // every Dot object is initalized with the correct x-y coordinates based on where it is located on the grid
            }
        }
        this.size = size;
    }

 
    public Sprite[][] getGrid(){return grid;} // returns grid



    public void placeSprite(Sprite s){ //place sprite in new spot
        grid[size - 1 - s.getY()][s.getX()] = s;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot and sets the original position of sprite into a Dot object
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] instanceof Player) {
                    grid[i][j] = new Dot(size - 1 - i, j);
                    placeSprite(s);
                }
            }
        }
    }


    public void display() { //print out the current grid to the screen 
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] instanceof Dot) { // if element is a Dot object, then print "🟩"
                    System.out.print("🟩");
                } else if (grid[i][j] instanceof Enemy) { // if element is an Enemy object, then print "🐍"
                    System.out.print("🐍");
                } else if (grid[i][j] instanceof Treasure && !(grid[i][j] instanceof Trophy)) { // if element is a Treasure object and not a Trophy object, then print "🍎"
                    System.out.print("🍎");
                } else if (grid[i][j] instanceof Trophy) { // if element is a Trophy object, then print "🐣"
                    System.out.print("🐣");
                } else if (grid[i][j] instanceof Player) { // if element is a Player object, then print "🐦"
                    System.out.print("🐦");
                }
            }
            System.out.println();
        }
    }
    
    public void gameover(){ //use this method to display a loss
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (!(grid[i][j] instanceof Player)) {
                    System.out.print("💀");
                } else {
                    System.out.print("🐦");
                }
            }
            System.out.println();
        }
    }

    public void win(){ //use this method to display a win 
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (!(grid[i][j] instanceof Player)) {
                    System.out.print("🏆");
                } else {
                    System.out.print("🐦");
                }
            }
            System.out.println();
        }
    }


}