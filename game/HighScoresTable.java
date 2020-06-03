package game;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class HighScoresTable.
 * @author Eldad levi 316363399
 */
public class HighScoresTable {
    private int sizeOfTable;
    private List<ScoreInfo> highscoreList;

    /***
     * Create an empty high-scores table with the specified size.
     * @param size - means that the table holds up to size top scores.
     */
    public HighScoresTable(int size) {
        this.sizeOfTable = size;
        this.highscoreList = new ArrayList<ScoreInfo>();
    }

    /**
     * Add a high-score.
     * @param score - the score of the player
     */
    public void add(ScoreInfo score) {
        this.highscoreList.add(score);
        sortingTheList();
        if (this.highscoreList.size() > this.sizeOfTable) {
            this.highscoreList.remove(this.sizeOfTable);
        }

    }

    /**
     * @return table size
     */
    public int size() {
        return this.sizeOfTable;
    }

    /**
     * The list is sorted such that the highest scores come first.
     * @return the current high scores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.highscoreList;

    }

    /**
     * @param score - the score of the player
     * @return - the place of the player in the high score
     */
    // return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    // be added to the list.
    public int getRank(int score) {
        int counter = 1;
        for (int i = 0; i < this.highscoreList.size(); i++) {
            if (score < this.highscoreList.get(i).getScore()) {
                counter++;
            }
        }

        return counter;
    }

    /**
     * clearing the table.
     */
    public void clear() {
        this.highscoreList = new ArrayList<ScoreInfo>();
    }

    /**
     * Load table data from file. Current table data is cleared.
     * @param filename - the file name
     * @throws IOException - in case of exception
     */
    // Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        String str;
        if (!filename.exists()) {
            filename.createNewFile();
        }
        Scanner input = new Scanner(filename);
        while (input.hasNextLine()) {
            str = input.nextLine();
            String[] information = str.split(",");
            ScoreInfo scoreInfo = new ScoreInfo(information[0], Integer.parseInt(information[1]));
            this.highscoreList.add(scoreInfo);

        }
        input.close();
    }

    /**
     * Save table data to the specified file.
     * @param filename - the file name
     * @throws IOException - in case of exception
     */
    public void save(File filename) throws IOException {

        FileOutputStream fos = new FileOutputStream(filename);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

        sortingTheList();
        for (int i = 0; i < this.highscoreList.size(); i++) {
            String str = String.join(",", this.highscoreList.get(i).getName(),
                    Integer.toString(this.highscoreList.get(i).getScore()));
            bw.write(str);
            if (i < this.highscoreList.size() - 1) {
                bw.newLine();
            }
        }

        bw.close();
    }

    /**
     * @param filename - the file name
     * @return - the file that hold the table
     */
    // Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {

        HighScoresTable table = new HighScoresTable(3);
        try {
            table.load(filename);
        } catch (IOException e) {

            e.printStackTrace();
        }
        return table;
    }

    /**
     * sorting the list that holds the scores.
     * @return the sorted list
     */
    public List sortingTheList() {
        if (this.highscoreList.size() < 1) {
            return this.highscoreList;
        }

        ScoreInfo temp;
        for (int i = this.highscoreList.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (this.highscoreList.get(j).getScore() < this.highscoreList.get(j + 1).getScore()) {
                    temp = this.highscoreList.get(j);
                    this.highscoreList.set(j, this.highscoreList.get(j + 1));
                    this.highscoreList.set(j + 1, temp);
                }
            }
        }
        return this.highscoreList;
    }

    /**
     * printing the highscores.
     */
    public void printing() {
        for (int i = 0; i < this.highscoreList.size(); i++) {
            System.out.println(this.highscoreList.get(i).getName() + "," + this.highscoreList.get(i).getScore());
            System.out.println();

        }
    }
}