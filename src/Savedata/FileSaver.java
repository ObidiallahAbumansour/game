package Savedata;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import Campaign.Quests.StoryQuests.MSQ;

/**
 * saves progress relative to player choices
 * 
 * @param jobType/type used to see which hero they chose to save it as a number
 * @param writer       writes into the file
 * @param File         save file name
 */
public class FileSaver {
    private static String File = "Save1.txt";

    /**
     * @param jobType
     */
    public static void saveProg(String jobType) {
        int type = -1;
        if (jobType.equals("Archer")) {
            type = 0;
        } else if (jobType.equals("Royal")) {
            type = 1;
        } else if (jobType.equals("Mage")) {
            type = 2;
        } else if (jobType.equals("Rogue")) {
            type = 3;
        } else {
            System.out.println("Save file error, no class type found");
            System.exit(1);
        }
        try {
            File file = new File(File);
            if (file.exists()) {
                file.delete();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(File, true));
            writer.write("ScenarioNumber:\n" + (MSQ.getScenarioCounter()));
            writer.write("\nJob:\n" + type + "");
            writer.write("\r\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
