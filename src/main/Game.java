package main;

import java.io.File;
import java.util.Scanner;

import Campaign.Quests.StoryQuests.*;
import Savedata.FileSaver;
import Entities.Enemy.Mobs;
import Entities.Hero.Hero;

/**
 * still work in progress but it will have the save file method implemented here
 * and exiting the program through here
 * and will implement the combat in here prob
 *
 * @param scenario     will get the story using the method in MSQ class
 * @param plyr         initializing player character to speicifc stats
 * @param enemy        makes 3 objects of type mob that will be fought in an
 *                     order
 *                     of 1 to 3
 * @param file         used to delete savefile if player won or lost(different
 *                     from saving)
 * @param order[]      an array that holds 3 values used by @enemy1-3
 * @param sId          used in continuing from old save
 * @param choosenClass deals with choice of class when starting game
 */
public class Game extends Battle {
    public int choosenClass;
    File file = new File("path/to/file.txt");
    private int count;
    private boolean cc = false;
    double sId;
    Hero plyr = new Hero();
    Mobs enemy1;
    Mobs enemy2;
    Mobs enemy3;
    public static int[] order = { 0, 0, 0 };

    public Game(Double scenarioNum, boolean cont) {

        System.out.println("game started:");
        boolean Class = false;
        Scanner scnr = new Scanner(System.in);
        boolean cococombatChecker = false;
        boolean active = true;
        // if game conti. from where it left off it will start from here
        if (cont == true) {

            Class = true;
            plyr = new Hero();
            plyr.heroType(MSQ.getSave());
            sId = MSQ.fixCounter();
            MSQ.fixer(sId - 0.5);
            System.out.print("\033[H\033[2J");
        }

        // choice of class + story
        while (Class != true) {
            System.out
                    .println(
                            "\t\tPlease select a class that's shown below\n\t1-Archer\t 2-Royal Guard\t     3-Mage\t  4-Rogue");

            choosenClass = scnr.nextInt();

            if (choosenClass >= 1 && choosenClass <= 4) {
                choosenClass -= 1;
                Class = true;
                plyr = new Hero();
                plyr.heroType(choosenClass);
                FileSaver.saveProg(plyr.getName());
                System.out.print("\033[H\033[2J");

            }
        }
        // makes sure the fights are on the right order
        if (cc != true) {
            count = MSQ.numOfFightFought();
            cc = true;
            commence();
        }

        // starts combat and reads the story
        if (cont == true) {
            System.out.print("\033[H\033[2J");
            storyWriter(active, sId);
            cont = false;
        } else {
            System.out.print("\033[H\033[2J");
            storyWriter(active, scenarioNum);
        }

        scnr.close();
        FileSaver.saveProg(plyr.getName());
        System.out.println("You have successfully closed and saved the game.");
        System.exit(0);
    }

    /**
     * @param active
     * @param num
     */
    // reads from file
    public void readScen(boolean active, double num) {
        String scenario = MSQ.getScenario(num, plyr);

        for (int i = 0; i < scenario.length(); i++) {
            System.out.print(scenario.charAt(i));
            try {
                Thread.sleep(15); // wait for 15 milliseconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    // makes an order on how the fights will go in a chronological order
    public static void combatOrder(int Storyid) {
        if (Storyid == 10) {
            order[0] = 6;
            order[1] = 7;
            order[2] = 8;
        } else if (Storyid == 20) {
            order[0] = 0;
            order[1] = 1;
            order[2] = 9;
        } else if (Storyid == 30) {
            order[0] = 2;
            order[1] = 4;
            order[2] = 10;
        } else if (Storyid == 40) {
            order[0] = 3;
            order[1] = 5;
            order[2] = 11;
        }
    }

    // sets the enemies variables to a mob using the order from @order[]
    public void commence() {

        enemy1 = new Mobs(order[0]);

        enemy2 = new Mobs(order[1]);

        enemy3 = new Mobs(order[2]);

    }

    // sets hero to a type of class
    public void cont(String cls) {
        cls.toLowerCase();
        if (cls.equals("Archer")) {
            plyr.heroType(0);
        } else if (cls.equals("Royal")) {
            plyr.heroType(1);
        } else if (cls.equals("Mage")) {
            plyr.heroType(2);
        } else if (cls.equals("Rogue")) {
            plyr.heroType(3);
        }
    }

    // writes story text
    public void storyWriter(boolean active, double prog) {
        boolean cococombatChecker = false;

        do {
            readScen(active, prog);
            if (prog > 3) {
                System.out.println("You have finished the game! congrats ʕ•ﻌ•ʔ");
                file.delete();
                active = false;
                break;
            }
            int userInput = scnr.nextInt();
            if (userInput == 0) {
                active = false;
                break;
            } else if (userInput == 5) {
                prog = MSQ.getScenarioCounter();
                if (prog % 1 == 0.5) {
                    cococombatChecker = true;
                    while (cococombatChecker == true) {
                        if ((prog) / 1 == 0.50) {
                            cococombatChecker = getCombatStatus(plyr, enemy1);
                            if (cococombatChecker == false) {
                                enemy1.changeHp(0);
                            }
                        } else if (prog / 1 == 1.5) {
                            cococombatChecker = getCombatStatus(plyr, enemy2);
                            enemy2.changeHp(0);
                        } else if (prog / 1 == 2.5) {
                            cococombatChecker = getCombatStatus(plyr, enemy3);
                            enemy3.changeHp(0);
                        }

                    }
                } else if (userInput != 5) {
                    System.out.println("Please enter a valid option");
                }

            }
        } while (active = true);
    }
}
