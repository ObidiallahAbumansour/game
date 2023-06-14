package main;

import java.io.File;
import java.util.Scanner;

import Entities.Enemy.Mobs;
import Entities.Hero.Hero;
import Savedata.FIleMaker;
import Savedata.ReadFile;

/**
 * Prints the title screen and the options for it and waits for the user to make
 * a choice of either make a new/continue/exit the game
 * 
 * @param scnr        general use for the user to input an option for both
 *                    decision and double check
 * @param file        makes the program know where the file is at or name
 * @param gameState   dedicates if the game is running or not
 * @param check       checks if the user choose continue or not
 * @param decision    reads user inputs till either 2 or 3 is written
 * @param doubleCheck after decision have been used this will give the user a
 *                    yes or no for the continue screen but no file was found
 */
public class MainClass extends Exception {

    public static void main(String[] args) {
        // ignore this I couldnt figure out how to make them work with no problem in a
        // cleaner way but it initialize the objects both mobs and hero classes with
        // objects
        Hero royalGuard = new Hero(10, 0, 14, 10, 1, 5, 15);
        Hero archer = new Hero(8, 4, 2, 2, 10, 10, 10);
        Hero mage = new Hero(2, 10, 0, 5, 5, 20, 10);
        Hero rogue = new Hero(12, 5, 3, 3, 12, 10, 5);
        Mobs Militia = new Mobs(5, 0, 5, 1, 2, 0, 10, "Militia");
        Mobs Knight = new Mobs(10, 0, 7, 3, 0, 0, 15, "Knight");
        Mobs Witch = new Mobs(0, 10, 3, 7, 5, 10, 5, "Witch");
        Mobs thief = new Mobs(5, 0, 3, 3, 10, 0, 5, "thief");
        Mobs MageSeeker = new Mobs(5, 5, 5, 10, 0, 5, 10, "MgeSeeker");
        Mobs The_Broker = new Mobs(5, 4, 3, 3, 10, 5, 10, "The Broker");
        Mobs Rabid_Bunny = new Mobs(5, 0, 1, 1, 10, 0, 5, "Rabid Bunny");
        Mobs Winter_wolf = new Mobs(9, 1, 3, 2, 10, 5, 10, "Winter wolf");
        Mobs Fenrir = new Mobs(10, 10, 5, 5, 2, 8, 20, "Fenrir");
        Mobs The_Black_Knight = new Mobs(15, 5, 7, 3, 0, 5, 25, "The Black Knight");
        Mobs Arch_Magus = new Mobs(0, 20, 3, 7, 5, 10, 15, "Arch Magus");
        Mobs The_Black_Eye = new Mobs(5, 5, 5, 10, 20, 5, 10, "The Red Eye");
        Hero.inserToArray(royalGuard);
        Hero.inserToArray(archer);
        Hero.inserToArray(mage);
        Hero.inserToArray(rogue);
        Mobs.inserToArray(Militia);
        Mobs.inserToArray(Knight);
        Mobs.inserToArray(Witch);
        Mobs.inserToArray(thief);
        Mobs.inserToArray(MageSeeker);
        Mobs.inserToArray(The_Broker);
        Mobs.inserToArray(Rabid_Bunny);
        Mobs.inserToArray(Winter_wolf);
        Mobs.inserToArray(Fenrir);
        Mobs.inserToArray(The_Black_Knight);
        Mobs.inserToArray(Arch_Magus);
        Mobs.inserToArray(The_Black_Eye);

        /////////////////////////////

        Scanner scnr = new Scanner(System.in);
        boolean gameState = false;
        boolean check = false;
        int decision = 0;
        int doubleCheck = 0;
        File file = new File("Save1.txt");

        System.out.print("-----------------------------------------------------------------------------");
        System.out.print(
                "\n\t\t\t\tElpis  \n\n\t1-New Game\t  2-Continue Game\t  3-Quit\n  (Warning choosing new game will overwrite existing save if it exists)\n");

        while (gameState == false) {
            if (check == false) {
                decision = scnr.nextInt();
            } else if (check == true) {
                doubleCheck = scnr.nextInt();
            }

            // makes new game
            if (decision == 1 || doubleCheck == 1) {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                FIleMaker.newSave();
                try {
                    Thread.sleep(55); // wait for 55 milliseconds
                    gameState = true;
                    new Game(ReadFile.saveDataRead(), false);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.err.println("Error: " + "something wrong happened. (o-ω-)o ??");
                    e.printStackTrace();
                }
            }
            // continues from old saved date
            else if (decision == 2) {
                System.out.print("\033[H\033[2J");
                System.out.flush();

                if (file.exists() == true) {
                    gameState = true;
                    new Game(ReadFile.saveDataRead(), true);

                } else if (file.exists() == false) {
                    System.out.println(
                            "It appears you dont have a saved game, do you wish to create a new game or exit?");
                    System.out.println("\t\t 1-new game \t 2-exit".toUpperCase());
                    check = true;
                }

            }
            // exits game
            else if (decision == 3 || doubleCheck == 2) {
                System.out.println("You have successfully closed the game.");
                gameState = true;
            }
            // prompts user to choose a valid option
            else if ((decision > 1 && decision < 3) == false) {
                System.out.println("Please enter a valid choice");
            } else if ((doubleCheck > 2 || doubleCheck < 1) == true) {
                System.out.println("Please enter a valid choice");
            }
        }
        scnr.close();
    }
}
