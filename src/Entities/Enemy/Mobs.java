package Entities.Enemy;

import java.util.ArrayList;

import Entities.Stats;

/**
 * will list the mobs names and stats in here
 * 
 * accumilated stats does not go above 40 for mobs
 * and for bosses are 60 with an exception to mana bein multipuls of 5
 * 
 * @param name        Mainly deals with mobs names.
 * @param strength    How strong physically an entity is.
 * @param intellect   How effective an entity is with magic.
 * @param defense     How well an entity can withstand a physical attack.
 * @param resistance  How well an entity can withstand a magical attack.
 * @param evasion     The chance of an entity evading an attack.
 * @param mana        The entity's pool of mp.
 * @param healthPoint The entity's pool of hp.
 */
public class Mobs extends Stats {
    static ArrayList<Mobs> arry = new ArrayList<>();

    private String Name;
    private int str;
    private int inte;
    private int def;
    private int res;
    private int eva;
    private int mp;
    private int hp;

    public Mobs() {
        // buffer just there
    }

    // for premade stuff only
    public Mobs(int strength, int intellect, int defense, int resistance, int evasion, int mana,
            int healthPoint, String name) {
        this.str = strength;
        this.inte = intellect;
        this.def = defense;
        this.res = resistance;
        this.eva = evasion;
        this.mp = mana;
        this.hp = healthPoint;
        this.Name = name;

    }

    // the star of this whole class tbh
    public Mobs(int classType) {
        switch (classType) {
            case (0): {
                this.str = 5;
                this.inte = 1;
                this.def = 5;
                this.res = 1;
                this.eva = 2;
                this.mp = 0;
                this.hp = 10;
                this.Name = "Milita";
                break;
            }
            case (1): {
                this.str = 10;
                this.inte = 1;
                this.def = 5;
                this.res = 1;
                this.eva = 2;
                this.mp = 0;
                this.hp = 15;
                this.Name = "Knight";
                break;
            }
            case (2): {
                this.str = 1;
                this.inte = 10;
                this.def = 3;
                this.res = 7;
                this.eva = 5;
                this.mp = 10;
                this.hp = 5;
                this.Name = "Witch";
                break;
            }
            case (3): {
                this.str = 5;
                this.inte = 2;
                this.def = 3;
                this.res = 3;
                this.eva = 10;
                this.mp = 0;
                this.hp = 5;
                this.Name = "thief";
                break;
            }
            case (4): {

                this.str = 5;
                this.inte = 5;
                this.def = 5;
                this.res = 10;
                this.eva = 0;
                this.mp = 5;
                this.hp = 10;
                this.Name = "MageSeeker";
                break;
            }
            case (5): {
                this.str = 5;
                this.inte = 4;
                this.def = 3;
                this.res = 3;
                this.eva = 10;
                this.mp = 5;
                this.hp = 10;
                this.Name = "The Broker";

                break;
            }
            case (6): {
                this.str = 5;
                this.inte = 1;
                this.def = 1;
                this.res = 1;
                this.eva = 10;
                this.mp = 0;
                this.hp = 5;
                this.Name = "Rabid Bunny";
                break;
            }
            case (7): {
                this.str = 9;
                this.inte = 1;
                this.def = 3;
                this.res = 2;
                this.eva = 10;
                this.mp = 5;
                this.hp = 10;
                this.Name = "Snow Wolf";

                break;
            }
            case (8): {
                this.str = 10;
                this.inte = 10;
                this.def = 5;
                this.res = 5;
                this.eva = 2;
                this.mp = 10;
                this.hp = 20;
                this.Name = "Fenrir";
                break;
            }
            case (9): {
                this.str = 15;
                this.inte = 5;
                this.def = 7;
                this.res = 3;
                this.eva = 0;
                this.mp = 5;
                this.hp = 25;
                this.Name = "The Black Knight";
                break;
            }
            case (10): {
                this.str = 0;
                this.inte = 20;
                this.def = 3;
                this.res = 7;
                this.eva = 5;
                this.mp = 10;
                this.hp = 15;
                this.Name = "Arch Magus";
                break;
            }
            case (11): {
                this.str = 5;
                this.inte = 5;
                this.def = 5;
                this.res = 10;
                this.eva = 20;
                this.mp = 5;
                this.hp = 10;
                this.Name = "The Red Eye";
                break;
            }
        }

    }

    /**
     * gets name
     * 
     * @return String
     */

    public String getName() {
        return this.Name;
    }

    // enters object to array
    public static void inserToArray(Mobs enemy) {
        arry.add(enemy);
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

    @Override
    public void dmgTaken(int loss) {

        this.hp = this.hp - loss;
    }

    public void mpUsed() {
        this.mp = this.mp - 5;
    }

    public void mpInc() {
        this.mp = this.mp + 5;
    }

    public void changeHp(int ch) {
        this.hp = ch;
    }

    public void healup(int hup) {
        this.hp = this.hp + hup;
    }
}