package main;

import java.io.File;
import java.util.Random;
import java.util.Scanner;
import Entities.Hero.Hero;
import Entities.Stats;
import Entities.Enemy.Mobs;

/**
 * writes and deals with the algorithm & calculation of player choice in combat
 * 
 * @param rng                     used in enemy action and healing
 * @param scnr                    reads input from user(used by @playerControl)
 * @param hero/mob                obj that gives the class the numbers/values it
 *                                needs to calculate
 * @param cd/eCd                  gives the healing move a cooldown so it cant
 *                                be abused
 * @param enemeyStatus/heroStatus makes sure that both are alive and if not
 *                                which lost
 * 
 */
public class Battle extends Stats {
    private Random rng = new Random();
    File file = new File("Save1.txt");
    Scanner scnr = new Scanner(System.in);

    /**
     * @param hero
     * @param mob
     * @return boolean
     */
    // text wall for combat
    public boolean getCombatStatus(Hero hero, Mobs mob) {
        int cd = 1;
        int eCd = 1;
        int diceHo = 0;
        boolean enemyStatus = true;
        boolean heroStatus = true;
        System.out.print("\033[H\033[2J");
        int playerControl;
        System.out.println("                         Combat Commence!!");
        // keeps the combat active till either side dies
        while (enemyStatus == true && heroStatus == true) {
            if (cd % 2 == 0) {
                cd++;
            }

            System.out.println(
                    "\n---------------------------------------------------------------------------------------------");

            System.out.printf(
                    "Protagonist [   HP: %d         MP: %d                      HP: %d         MP: %d    ] %s\n",
                    hero.getStat("hp"),
                    hero.getStat("mp"), mob.getStat("hp"), mob.getStat("mp"), mob.getName());

            System.out.printf(
                    "            [   ATK: %d        MTK: %d         VS          ATK: %d        MTK: %d   ]\n",
                    hero.getStat("atk"), hero.getStat("int"), mob.getStat("atk"), mob.getStat("int"));

            System.out.printf(
                    "            [   DEF: %d        RES: %d                    DEF: %d        RES: %d   ]\n",
                    hero.getStat("def"), hero.getStat("res"),
                    mob.getStat("def"), mob.getStat("res"));
            System.out.printf("            [   EVA: %d                                                EVA: %d     ]\n",
                    hero.getStat("eva"), mob.getStat("eva"));

            if (hero.getStat("hp") <= 0) {
                heroStatus = false;
                break;
            } else if (mob.getStat("hp") <= 0) {
                enemyStatus = false;
                break;
            }

            System.out.println("\t1-attack\t  2-magic\t  3-special attack\t  4-heal");

            if (hero.getStat("hp") <= 0) {
                heroStatus = false;
            } else if (mob.getStat("hp") <= 0) {
                enemyStatus = false;
            }

            playerControl = scnr.nextInt();
            // choice for the player
            if (cd % 2 == 1) {

                if (playerControl > 4 && playerControl < 1) {
                    while (playerControl > 4 && playerControl < 1) {
                        System.out.println("Please enter a valid option");
                        System.out.println("\t1-attack\t  2-magic\t  3-special attack\t  4-heal");
                        playerControl = scnr.nextInt();
                    }
                } else if (playerControl > 0 && playerControl < 5) {
                    attacksAndStuff(playerControl, hero, mob);
                    if (playerControl == 4) {
                        cd++;
                    }
                }
            } else if (cd % 2 == 0) {
                if (playerControl > 3 && playerControl < 1) {
                    while (playerControl > 3 && playerControl < 1) {
                        System.out.println("Please enter a valid option");
                        System.out.println("\t    1-attack\t  2-magic\t  3-special attack\t");
                        playerControl = scnr.nextInt();
                    }
                } else if (playerControl > 0 && playerControl < 4) {
                    attacksAndStuff(playerControl, hero, mob);
                }
            }
            // enemy action
            if (eCd % 2 == 0) {
                diceHo = rng.nextInt(3) + 1;
            } else {
                diceHo = rng.nextInt(4) + 1;
            }
            if (diceHo == 1) {
                attackyHehe(diceHo, hero, mob);
            } else if (diceHo == 2) {
                attackyHehe(diceHo, hero, mob);
            } else if (diceHo == 3) {
                attackyHehe(diceHo, hero, mob);
            } else if (diceHo == 4 && eCd % 2 == 1) {
                attackyHehe(diceHo, hero, mob);
                eCd++;
            }
            if (eCd % 2 == 0) {
                eCd++;
            }

        }
        if (enemyStatus == false) {
            System.out.print("\033[H\033[2J");
            System.out.println("Engagement Won! (≧∇≦)ｷｬｰ♪");
            return false;
        } else if (heroStatus == false) {
            System.out.print("\033[H\033[2J");
            System.out.println("Your journey have ended! ( 〇□〇） \n  exiting program (●｀･ω･)");
            file.delete();
            System.exit(0);
            return false;
        }
        return true;
    }

    // deals with the logic of the choices for hero
    public void attacksAndStuff(int ch, Hero hero, Mobs enemy) {
        int dmgGiven = 0;
        int hpLost = 0;
        int hpG;

        switch (ch) {
            case (1): {
                if (enemy.getStat("def") == 0) {
                    dmgGiven = hero.getStat("atk");
                    if (rng.nextInt(101) < (enemy.getStat("eva") * 5)) {
                        System.out.print("\033[H\033[2J");
                        System.out.println("The enemy have evaded the attack!? (ﾉ;><)ﾉ");
                        break;
                    }
                } else if (enemy.getStat("def") >= hero.getStat("atk")) {
                    dmgGiven = 0;
                    System.out.print("\033[H\033[2J");
                    System.out.println("The enemy resisted all damages (TωT)ﾟ");
                } else {
                    dmgGiven = hero.getStat("atk") - enemy.getStat("def");
                    if (rng.nextInt(101) < (enemy.getStat("eva") * 5)) {
                        System.out.print("\033[H\033[2J");
                        System.out.println("The enemy have evaded the attack!? (ﾉ;><)ﾉ");
                        break;
                    }
                }
                hpLost = dmgGiven;
                enemy.dmgTaken(hpLost);
                System.out.print("\033[H\033[2J");
                System.out.printf("\nYou dealt %d worth of HP! (*ﾟｰﾟ)=○)ﾟＯﾟ)\n", dmgGiven);
                break;
            }
            case (2): {
                if (enemy.getStat("res") == 0) {
                    dmgGiven = hero.getStat("int");
                    if (rng.nextInt(101) < (enemy.getStat("eva") * 5)) {
                        System.out.print("\033[H\033[2J");
                        System.out.println("The enemy have evaded the attack!? (ﾉ;><)ﾉ");
                        break;
                    }
                } else if (enemy.getStat("res") >= hero.getStat("int")) {
                    dmgGiven = 0;
                    System.out.print("\033[H\033[2J");
                    System.out.println("The enemy resisted all damages (TωT)ﾟ");
                } else {
                    dmgGiven = hero.getStat("int") - enemy.getStat("res");
                    if (rng.nextInt(101) < (enemy.getStat("eva") * 5)) {
                        System.out.print("\033[H\033[2J");
                        System.out.println("The enemy have evaded the attack!? (ﾉ;><)ﾉ");
                        break;
                    }
                }
                hpLost = dmgGiven;
                enemy.dmgTaken(hpLost);
                System.out.print("\033[H\033[2J");
                System.out.printf("\nYou dealt %d worth of HP! （☆≧▽≦）／～★\n", dmgGiven);
                break;
            }
            case (3): {
                if (hero.getStat("mp") <= 0) {
                    System.out.print("\033[H\033[2J");
                    System.out.println("You are replenishing your mana! ヽ( `д´*)/");
                    hero.mpInc();
                    break;
                } else if (hero.getStat("mp") > 0) {
                    dmgGiven = (int) ((hero.getStat("atk") + hero.getStat("int")) * 0.75);
                    hpLost = dmgGiven;
                    hero.mpUsed();
                    enemy.dmgTaken(hpLost);
                    System.out.print("\033[H\033[2J");
                    System.out.printf("\nYou dealt %d worth of HP! nice |/// |・ω・)ﾉ\n", dmgGiven);
                    break;
                }
            }
            case (4): {
                hpG = rng.nextInt(5) + 1;
                hero.healup(hpG);
                System.out.print("\033[H\033[2J");
                System.out.printf("You are healed by %d  ┏(*`＞ω<)┛\n", hpG);
                break;
            }
        }
    }

    // logic for foe's moves similar to attacksAndStuff
    public void attackyHehe(int choice, Hero hero, Mobs enemy) {
        int dmgGiven = 0;
        int hpLost = 0;
        int hpG;
        switch (choice) {
            case (1): {
                if (hero.getStat("def") == 0) {
                    dmgGiven = enemy.getStat("atk");
                    if (rng.nextInt(101) < (hero.getStat("eva") * 5)) {
                        System.out.println("You have evaded the attack! (三・o・)三☆三(｀ε´三)");
                        break;
                    }
                } else if (hero.getStat("def") >= enemy.getStat("atk")) {
                    dmgGiven = 0;
                    System.out.println("You have resisted all the attack! （★>з<）");
                    break;
                } else {
                    dmgGiven = enemy.getStat("atk") - hero.getStat("def");
                    if (rng.nextInt(101) < (hero.getStat("eva") * 5)) {
                        System.out.println("You have evaded the attack! (三・o・)三☆三(｀ε´三)");
                        break;
                    }
                }
                hpLost = dmgGiven;
                hero.dmgTaken(hpLost);
                System.out.printf("\nYou got hurt by %d worth of HP! Y'ouch (=^┬ ┬^) ", dmgGiven);
                break;
            }
            case (2): {
                if (hero.getStat("res") == 0) {
                    dmgGiven = enemy.getStat("int");
                    if (rng.nextInt(101) < (hero.getStat("eva") * 5)) {
                        System.out.println("You have evaded the attack! (三・o・)三☆三(｀ε´三)");
                        break;
                    }
                } else if (enemy.getStat("res") >= enemy.getStat("int")) {
                    dmgGiven = 0;
                    System.out.println("You have resisted all the attack! （★>з<）");
                    break;
                } else {
                    dmgGiven = enemy.getStat("int") - hero.getStat("res");
                    if (rng.nextInt(101) < (enemy.getStat("eva") * 5)) {
                        System.out.println("You have evaded the attack! (三・o・)三☆三(｀ε´三)");
                        break;
                    }
                }
                hpLost = dmgGiven;
                hero.dmgTaken(hpLost);
                System.out.printf("\nYou got hurt by %d worth of HP! Y'ouch (=^┬ ┬^) ", dmgGiven);
                break;
            }
            case (3): {
                if (enemy.getStat("mp") <= 0) {
                    System.out.println("Foe is replenishing their mana! （;≧皿≦）");
                    enemy.mpInc();
                    break;
                } else if (enemy.getStat("mp") > 0) {
                    dmgGiven = (int) ((enemy.getStat("atk") + enemy.getStat("int")) * 0.75);
                    hpLost = dmgGiven;
                    enemy.mpUsed();
                    hero.dmgTaken(hpLost);
                    System.out.printf("\nYou got hurt by %d worth of HP! Y'ouch (=^┬ ┬^) ", dmgGiven);
                    break;
                }
            }
            case (4): {
                hpG = rng.nextInt(5) + 1;
                enemy.healup(hpG);
                System.out.printf("Foe healed by %d （ｐ>□<ｑ*)\n", hpG);
                break;
            }
        }
    }
}