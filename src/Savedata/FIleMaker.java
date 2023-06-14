package Savedata;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * only purpose is to make a new file
 * 
 * @param File stores the name of the file really just that
 */
public class FIleMaker {
    private static String File = "Save1";

    public static void newSave() {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(File + ".txt", false));
            writer.write("ScenarioNumber:\n0.0");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
