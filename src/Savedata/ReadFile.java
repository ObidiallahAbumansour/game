package Savedata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * main objective is to read the file to pass it into another class
 * e.g. Game
 * 
 * @param Snum          saves the scneario number to pass it into game to msq
 *                      class
 * @param foundScenario checks if the scenario(keyword) was found
 * @param checker       will take the i variable value and store it to transfer
 *                      it to Snum
 * @param reader        will be used to read the file
 * @param line          will read the next line after it found the keyword to
 *                      save as string
 * @param i             will keep increasing by .5 each time till it's equal to
 *                      line
 */
public class ReadFile {

    /**
     * @return double
     */
    // finds the scenarioNumber from save file
    public static double saveDataRead() {
        double Snum = 0.0;
        boolean foundScenario = false;
        boolean cCheck = false;
        double checker = 0.0;
        // reads the save file
        try {
            BufferedReader reader = new BufferedReader(new FileReader("./MainStoryScenarios.txt"));
            String line = reader.readLine();
            while (line != null) {
                if (line.equals("Job:") && cCheck == false) {
                    line = reader.readLine();
                    line = reader.readLine();
                    cCheck = true;
                }
                if (line.equals("ScenarioNumber:") && cCheck == true) {
                    foundScenario = true;

                } else if (foundScenario && cCheck && !line.startsWith("Scenario:") == true) {
                    // reads the second line after it found the key word then, be adds the scenario
                    // number into Snum to identify the scenario number
                    for (double i = 0.0; (i + "") != line;) {
                        if (((i + "").equals(line)) == true) {
                            checker = i;
                            break;
                        }
                        i += 0.5;
                    }
                    Snum = checker;
                } else if (foundScenario) {
                    // Stop reading lines from the file once it have nothing more to read
                    break;
                }
                line = reader.readLine();

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Snum;
    }

    // gets the chosen class from save file
    public static String ReadClass() {
        String name = "";
        boolean foundName = false;

        try (
                BufferedReader reader = new BufferedReader(new FileReader("./Save1.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.equals("Job:")) {
                    foundName = true;

                } else if (foundName && !line.startsWith("Job:")) {
                    // Add the line to the text variable if it's part of the scenario
                    name += line + "\n";
                } else if (foundName) {
                    // Stops reading lines from the file once the scenario is complete
                    break;
                }
                line = reader.readLine();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

}
