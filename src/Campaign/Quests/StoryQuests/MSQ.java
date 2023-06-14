package Campaign.Quests.StoryQuests;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Entities.Hero.Hero;

/**
 * this class will type in the scenarios(0 to ?) for the player to follow will
 * 
 * @param scenarioName    searches for the scenario number in the save file then
 *                        adds it into scenario
 * @param scenario        variable to store in the story
 * @param foundScenario   checks if the scenario was found
 * @param num             takes a number from game class
 * @param scenarioCounter stores the scenario number to then be used to save it
 *                        into the save file using FIleSaver(wip)
 */
public class MSQ {
    static double scenarioCounter;

    /**
     * @param num
     * @param name
     * @return String
     */
    // gets the story from txt file and saves it into a string variable
    public static String getScenario(double num, Hero name) {
        String scenarioName = "scenario" + num;
        String scenarioId = name.getName();
        String scenario = "";
        boolean foundScenario = false;
        boolean foundId = false;

        try (BufferedReader reader = new BufferedReader(
                new FileReader("./MainStoryScenarios.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.equals(scenarioId) || foundId == true) {
                    foundId = true;
                    if (line.equals(scenarioName)) {
                        foundScenario = true;

                    } else if (foundScenario && !line.startsWith("scenario")) {
                        // Add the line to the text variable if it's part of the scenario
                        scenario += line + "\n";
                        for (double i = 0.0; (i + "").equals(line); i += 0.5) {
                            scenarioCounter = i;

                        }
                    } else if (foundScenario) {
                        // Stops reading lines from the file once the scenario is complete
                        break;
                    }

                }
                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        scenarioCounter += 0.5;
        return scenario;
    }

    // gets save
    public static int getSave() {
        int i = -1;
        int clasNum = -1;
        boolean found = false;

        try (BufferedReader reader = new BufferedReader(
                new FileReader("./Save1.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.equals("Job:")) {
                    found = true;

                } else if ((found && !line.startsWith("Job:") == true)) {
                    for (; line.equals((i - 1) + "") == false; i = i + 1) {
                        if ((i + "").equals(line) == true) {
                            clasNum = i;
                        }

                    }
                } else if (found) {
                    // Stops reading lines from the file once the scenario is complete
                    break;
                }

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        getScenarioCounter();
        return clasNum;
    }

    // returns scenario number
    public static double getScenarioCounter() {
        return scenarioCounter;
    }

    // checks from loaded file if they fought any battles
    public static int numOfFightFought() {
        int result = 0;
        for (double i = 0; (i == scenarioCounter) == true; i += 0.5) {
            if ((int) i / 1 == 1 && i >= 1) {
                result++;
            }
            if ((int) i / 2 == 1 && i >= 1) {
                result++;
            }
        }
        return (result);
    }

    // fixes the scenario number into the saved file one
    public static void fixer(double savFil) {
        scenarioCounter = savFil;
    }

    // similar to the above method
    public static double fixCounter() {
        boolean found = false;
        boolean doubleF = false;
        double Counter = 0;

        try (BufferedReader reader = new BufferedReader(
                new FileReader("./Save1.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.equals("ScenarioNumber:")) {
                    found = true;

                } else if ((found && !line.startsWith("ScenarioNumber:") == true)) {
                    for (double i = 0; (line.equals((i - 0.5) + "") == false && !doubleF == true); i = i
                            + 0.5) {
                        if ((i + "").equals(line) == true) {
                            Counter = i;
                            doubleF = true;
                        }

                    }
                } else if (found) {
                    // Stops reading lines from the file once the scenario is complete
                    break;
                }

                line = reader.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
        return Counter;
    }

}
