package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import sprites.BlockBuild;
import sprites.SpaceBuild;

/**
 * Class BLockDefintionReader.
 * @author Eldad Levi 316363399
 */
public class BlocksDefinitionReader {

    /**
     * @param reader - the file that we read from
     * @return - factory that will hold two lists, a block list and a space list
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        ArrayList<BlockBuild> blockList = new ArrayList<BlockBuild>();
        ArrayList<BlockBuild> deafultBlockList = new ArrayList<BlockBuild>();
        ArrayList<SpaceBuild> spaceList = new ArrayList<SpaceBuild>();
        Map<String, String> deafultInfo = new HashMap<String, String>();
        Scanner input = new Scanner(reader);
        String line;
        line = input.nextLine(); // # Block definition
        while (line != null) {
            if (line.startsWith(" ") || line.startsWith("#") || line.length() == 0) { // none usful information
                line = input.nextLine();
            }
            if (line.startsWith("bdef")) { // reading the bdef part
                Map<String, String> bdefInfo = new HashMap<String, String>();
                line = line.substring(5);
                String[] split = line.split(" ");
                for (String x : split) {
                    String[] y = x.split(":");
                    bdefInfo.put(y[0], y[1]);
                }
                BlockBuild blockBuild = new BlockBuild();
                for (Map.Entry<String, String> entry2 : deafultInfo.entrySet()) {
                    switch (entry2.getKey()) {
                    case "width":
                        blockBuild.setStringWidth(entry2.getValue());
                        break;
                    case "height":
                        blockBuild.setStringHeight(entry2.getValue());
                        break;
                    default:
                        break;
                    }
                }
                for (Map.Entry<String, String> entry : deafultInfo.entrySet()) {
                    switch (entry.getKey()) {
                    case "symbol":
                        blockBuild.setStringSymbol(entry.getValue());
                        break;
                    case "width":
                        blockBuild.setStringWidth(entry.getValue());
                        break;
                    case "height":
                        blockBuild.setStringHeight(entry.getValue());
                        break;
                    case "hit_points":
                        blockBuild.setStringForce(entry.getValue());
                        break;
                    case "fill":
                        blockBuild.setStringFill(entry.getValue());
                        break;
                    case "fill_1":
                        blockBuild.setStringfillK(entry.getValue());
                        break;
                    case "fill_2":
                        blockBuild.setStringfillK(entry.getValue());
                        break;
                    case "stroke":
                        blockBuild.setStringStroke(entry.getValue());
                        break;
                    default:
                        break;
                    }
                }
                for (Map.Entry<String, String> entry2 : bdefInfo.entrySet()) {
                    switch (entry2.getKey()) {
                    case "width":
                        blockBuild.setStringWidth(entry2.getValue());
                        break;
                    case "height":
                        blockBuild.setStringHeight(entry2.getValue());
                        break;
                    default:
                        break;
                    }
                }
                for (Map.Entry<String, String> entry : bdefInfo.entrySet()) {
                    switch (entry.getKey()) {
                    case "symbol":
                        blockBuild.setStringSymbol(entry.getValue());
                        break;
                    case "width":
                        blockBuild.setStringWidth(entry.getValue());
                        break;
                    case "height":
                        blockBuild.setStringHeight(entry.getValue());
                        break;
                    case "hit_points":
                        blockBuild.setStringForce(entry.getValue());
                        break;
                    case "fill":
                        blockBuild.setStringFill(entry.getValue());
                        break;
                    case "fill-1":
                        blockBuild.setStringfillK(String.join(":", entry.getKey(), entry.getValue()));
                        break;
                    case "fill-2":
                        blockBuild.setStringfillK(String.join(":", entry.getKey(), entry.getValue()));
                        break;
                    case "fill-3":
                        blockBuild.setStringfillK(String.join(":", entry.getKey(), entry.getValue()));
                        break;
                    case "stroke":
                        blockBuild.setStringStroke(entry.getValue());
                        break;
                    default:
                        break;
                    }
                }
                blockList.add(blockBuild);
            }
            if (line.startsWith("sdef")) { // reading the sdef part
                Map<String, String> sdefInfo = new HashMap<String, String>();
                line = line.substring(5);
                String[] split2 = line.split(" ");
                for (String x : split2) {
                    String[] y = x.split(":");
                    sdefInfo.put(y[0], y[1]);
                }
                SpaceBuild spaceBuild = new SpaceBuild();
                for (Map.Entry<String, String> entry2 : sdefInfo.entrySet()) {
                    switch (entry2.getKey()) {
                    case "symbol":
                        spaceBuild.setSymbol(entry2.getValue());
                        break;
                    case "width":
                        spaceBuild.setWidthFromString(entry2.getValue());
                        break;
                    default:
                        break;
                    }
                }
                spaceList.add(spaceBuild);
            }
            if (line.startsWith("default")) { // reading the default part
                line = line.substring(8);
                String[] split3 = line.split(" ");
                for (String x : split3) {
                    String[] y = x.split(":");
                    deafultInfo.put(y[0], y[1]);
                }
            }
            if (input.hasNextLine()) {
                line = input.nextLine();
            } else {
                input.close();
                break;
            }
        }
        return new BlocksFromSymbolsFactory(blockList, spaceList);
    }
}
