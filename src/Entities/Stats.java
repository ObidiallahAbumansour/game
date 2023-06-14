package Entities;

/**
 * Constructs and deals with the necessary calculations needed for the combat to
 * function
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
public class Stats {

    private String Name;
    private int strength;
    private int intellect;
    private int defense;
    private int resistance;
    private int evasion;
    private int mana;
    private int healthPoint;

    /**
     * @param type
     * @return int
     */
    public int getStat(String type) {
        int result = 0;
        switch (type) {
            case ("hp"): {
                result = this.healthPoint;
                break;
            }
            case ("mp"): {
                result = this.mana;
                break;
            }
            case ("atk"): {
                result = this.strength;
                break;
            }
            case ("int"): {
                result = this.intellect;
                break;
            }
            case ("def"): {
                result = this.defense;
                break;
            }
            case ("res"): {
                result = this.resistance;
                break;
            }
            case ("eva"): {
                result = this.evasion;
                break;
            }

        }
        return result;
    }

    public void dmgTaken(int loss) {

        this.healthPoint = -loss;
    }

}
