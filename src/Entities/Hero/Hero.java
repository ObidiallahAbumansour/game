package Entities.Hero;

import java.util.ArrayList;
import Entities.Stats;

import main.Game;

/**
 * premade classes since making a custom on is a bit of a pain to allow the
 * player to do that (^: maybe will change my mind
 * 
 * base stats of 50
 * stat increase for hero will be increased by 1 to 9 for all the stats
 * differently allocated depending on the type
 * e.g. physical - mage - tank
 * 
 * @param name        Mainly deals with mobs names.
 * @param strength    How strong physically an entity is.
 * @param intellect   How effective an entity is with magic.
 * @param defense     How well an entity can withstand a physical attack.
 * @param resistance  How well an entity can withstand a magical attack.
 * @param evasion     The chance of an entity evading an attack.
 * @param mana        The entity's pool of mp.
 * @param healthPoint The entity's pool of hp.
 * @return return gives the specific var to the methods.
 */
public class Hero extends Stats {
    static ArrayList<Hero> arry = new ArrayList<>();

    private int str;
    private int inte;
    private int def;
    private int res;
    private int eva;
    private int mp;
    private int hp;
    private String Name;

    public Hero() {
        // nothing of use
    }

    // used in declaring the classes
    public Hero(int strength, int intellect, int defense, int resistance, int evasion, int mana,
            int healthPoint) {
        this.str = strength;
        this.inte = intellect;
        this.def = defense;
        this.res = resistance;
        this.eva = evasion;
        this.mp = mana;
        this.hp = healthPoint;

    }

    /**
     * @param classType
     */
    // used in new games
    public void heroType(int classType) {
        switch (classType) {
            case (1): {
                this.str = 10;
                this.inte = 2;
                this.def = 14;
                this.res = 10;
                this.eva = 1;
                this.mp = 5;
                this.hp = 15;
                this.Name = "Royal";
                Game.combatOrder(20);
                break;
            }
            case (0): {
                this.str = 8;
                this.inte = 4;
                this.def = 2;
                this.res = 2;
                this.eva = 10;
                this.mp = 10;
                this.hp = 10;
                this.Name = "Archer";

                Game.combatOrder(10);
                break;
            }
            case (2): {
                this.str = 2;
                this.inte = 10;
                this.def = 0;
                this.res = 5;
                this.eva = 5;
                this.mp = 20;
                this.hp = 10;
                this.Name = "Mage";
                Game.combatOrder(30);
                break;
            }
            case (3): {
                this.str = 12;
                this.inte = 5;
                this.def = 3;
                this.res = 3;
                this.eva = 12;
                this.mp = 10;
                this.hp = 5;
                this.Name = "Rogue";
                Game.combatOrder(40);
                break;
            }
        }
    }

    // enters object to array
    public static void inserToArray(Hero hero) {
        arry.add(hero);
    }

    @Override
    public int getStat(String type) {
        int result = 0;
        switch (type) {
            case ("hp"): {
                result = this.hp;
                break;
            }
            case ("mp"): {
                result = this.mp;
                break;
            }
            case ("atk"): {
                result = this.str;
                break;
            }
            case ("int"): {
                result = this.inte;
                break;
            }
            case ("def"): {
                result = this.def;
                break;
            }
            case ("res"): {
                result = this.res;
                break;
            }
            case ("eva"): {
                result = this.eva;
                break;
            }

        }
        return result;
    }

    public String getName() {
        return this.Name;
    }

    public void mpUsed() {
        this.mp = this.mp - 5;
    }

    public void mpInc() {
        this.mp = this.mp + 5;
    }

    public void healup(int hup) {
        this.hp = this.hp + hup;
    }

    @Override
    public void dmgTaken(int loss) {

        this.hp = this.hp - loss;
    }
}
