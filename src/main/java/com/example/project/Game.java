package com.example.project;

import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size){ //the constructor should call initialize() and play()
        this.size = size;
        play();
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){ //write your game logic here
        Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.println("What difficulty would you like the game to be: easy, medium, or hard?");
        input = scanner.nextLine();
        initialize(input);

        while(true){
            grid.display(); // displays the grid
            System.out.println("Treasure collected: " + player.getTreasureCount()); // prints Player's treasure count
            System.out.println("Lives remaining: " + player.getLives()); // prints Player's number of lives
            System.out.println("Enter a direction (w, a, s, d) to move, or enter 'q' to quit.");

            input = scanner.nextLine();
            if (player.isValid(size, input)) { // checks if the direction to move is out of bounds
                player.move(input);
                player.interact(size, input, treasures.length, grid.getGrid()[size - 1 - player.getY()][player.getX()]);
                grid.placeSprite(player, input);
            }

            if (input.equals("q")) { // if input is 'q', the game quits
                System.out.println("You have quit the game.");
                return;
            }

            if (player.getLives() <= 0) { // if Player has no lives left, the program displays the game over screen
                grid.gameover();
                System.out.println("GAME OVER");
                System.out.println("Do you want to play again? (yes/no)");
                input = scanner.nextLine();
                if (input.equals("yes")) { // if the user wants to play again, they have to type yes; if the user does not want to, they can type anything other than yes and the game ends
                    play();
                    return;
                } else {
                    return;
                }
            }

            if (player.getWin() == true) { // if Player has won, the program displays the victory screen
                grid.win();
                System.out.println("YOU WIN!!!");
                System.out.println("Do you want to play again? (yes/no)");
                input = scanner.nextLine();
                if (input.equals("yes")) { // if the user wants to play again, they have to type yes; if the user does not want to, they can type anything other than yes and the game ends
                    play();
                    return;
                } else {
                    return;
                }
            }

            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop

     
            }
            
     
    }

    public void initialize(String difficulty){
        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        grid = new Grid(size);
        player = new Player(0, 0); // Player always starts at (0, 0)
        grid.placeSprite(player);
        int[] newCoords = generateNewCoords(); // generate new coordinates and initializes the newCoords int array
        Enemy e1 = null; // create objects before initializing them
        Enemy e2 = null;
        Enemy e3 = null;
        Enemy e4 = null;
        Enemy e5 = null;
        Enemy e6 = null;
        trophy = new Trophy(newCoords[0], newCoords[1]); // trophy object initialized and placed in grid
        grid.placeSprite(trophy);
        newCoords = generateNewCoords();
        Treasure t1 = new Treasure(newCoords[0], newCoords[1]); // treasure objects initialized and placed in grid
        grid.placeSprite(t1);
        newCoords = generateNewCoords();
        Treasure t2 = new Treasure(newCoords[0], newCoords[1]);
        grid.placeSprite(t2);
        treasures = new Treasure[2]; // initialize treasures array
        treasures[0] = t1; // assign each treasure variable to an element in treasures array
        treasures[1] = t2;
        newCoords = generateNewCoords();
        if (difficulty.equals("easy")) { // if the user chooses easy mode, Player has double the amount of lives and there will be 4 enemies on the map
            player.setLives(player.getLives() * 2);
            e1 = new Enemy(newCoords[0], newCoords[1]);
            grid.placeSprite(e1);
            newCoords = generateNewCoords();
            e2 = new Enemy(newCoords[0], newCoords[1]);
            grid.placeSprite(e2);
            newCoords = generateNewCoords();
            e3 = new Enemy(newCoords[0], newCoords[1]);
            grid.placeSprite(e3);
            newCoords = generateNewCoords();
            e4 = new Enemy(newCoords[0], newCoords[1]);
            grid.placeSprite(e4);
            enemies = new Enemy[4];
            enemies[0] = e1;
            enemies[1] = e2;
            enemies[2] = e3;
            enemies[3] = e4;
        } else if (difficulty.equals("medium")) { // if the user chooses medium mode, Player has the usual amount of lives and there will be 5 enemies on the map
            e1 = new Enemy(newCoords[0], newCoords[1]);
            grid.placeSprite(e1);
            newCoords = generateNewCoords();
            e2 = new Enemy(newCoords[0], newCoords[1]);
            grid.placeSprite(e2);
            newCoords = generateNewCoords();
            e3 = new Enemy(newCoords[0], newCoords[1]);
            grid.placeSprite(e3);
            newCoords = generateNewCoords();
            e4 = new Enemy(newCoords[0], newCoords[1]);
            grid.placeSprite(e4);
            newCoords = generateNewCoords();
            e5 = new Enemy(newCoords[0], newCoords[1]);
            grid.placeSprite(e5);
            enemies = new Enemy[5];
            enemies[0] = e1;
            enemies[1] = e2;
            enemies[2] = e3;
            enemies[3] = e4;
            enemies[4] = e5;
        } else if (difficulty.equals("hard")) { // if the user chooses hard mode, Player has half the amount of lives and there will be 6 enemies on the map
            player.setLives(player.getLives() / 2);
            e1 = new Enemy(newCoords[0], newCoords[1]);
            grid.placeSprite(e1);
            newCoords = generateNewCoords();
            e2 = new Enemy(newCoords[0], newCoords[1]);
            grid.placeSprite(e2);
            newCoords = generateNewCoords();
            e3 = new Enemy(newCoords[0], newCoords[1]);
            grid.placeSprite(e3);
            newCoords = generateNewCoords();
            e4 = new Enemy(newCoords[0], newCoords[1]);
            grid.placeSprite(e4);
            newCoords = generateNewCoords();
            e5 = new Enemy(newCoords[0], newCoords[1]);
            grid.placeSprite(e5);
            newCoords = generateNewCoords();
            e6 = new Enemy(newCoords[0], newCoords[1]);
            grid.placeSprite(e6);
            enemies = new Enemy[6];
            enemies[0] = e1;
            enemies[1] = e2;
            enemies[2] = e3;
            enemies[3] = e4;
            enemies[4] = e5;
            enemies[5] = e6;
        }
        
    }

    public int[] generateNewCoords() { // method made for the extra credit
        int[] xyList = new int[2]; // list used for storing the x and y values
        int randomX = (int) (Math.random() * (size)); // generates a random num from 0 to size - 1
        int randomY = (int) (Math.random() * (size));
        while (!(grid.getGrid()[size - 1 - randomY][randomX] instanceof Dot)) { // if the current object at the generated coordinates is not a Dot object, keep generating coordinates until a Dot object is found
            randomX = (int) (Math.random() * (size + 1));
            randomY = (int) (Math.random() * (size + 1));
        }
        xyList[0] = randomX; // assigns the x value to index 0 and the y value to index 1
        xyList[1] = randomY;
        return xyList;
    }

    public static void main(String[] args) {
        // Grid newGrid = new Grid(10);
        // System.out.println(newGrid.getGrid()[0][0].getRowCol(10));
        // System.out.println(newGrid.getGrid()[0][0].getCoords());
        // Player newPlayer = new Player(0, 0);
        // newGrid.placeSprite(newPlayer);
        // newGrid.display();
        // Trophy newTrophy = new Trophy(0, 9);
        // newGrid.placeSprite(newTrophy);
        // Enemy newEnemy = new Enemy(0, 4);
        // newGrid.placeSprite(newEnemy);
        // System.out.println();
        // newGrid.display();

        Game newGame = new Game(10);
    }
}