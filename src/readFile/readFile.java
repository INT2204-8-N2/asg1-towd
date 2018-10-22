package ReadFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ReadFile {
    private ArrayList<String> englishWordList = new ArrayList<String>();
    private String[] meaningList;

    /**
     * Contructor
     *
     * @param englishWordList
     */
    public ReadFile(ArrayList<String> englishWordList) throws IOException {
        this.englishWordList = englishWordList;
    }

    public ReadFile() throws IOException {}

    private String content = new String(Files.readAllBytes(Paths.get("anhviet.txt")), StandardCharsets.UTF_8);
    private String[] str = content.split("\n\n");

    public ArrayList<String> getEnglishWordList() {
        for (String w : str) {
            String[] a;
            a = w.split("/");
            englishWordList.add(removeCharAt(a[0], 0));
        }
        return englishWordList;
    }

    public String[] getMeaningList() {
        meaningList = str;
        return meaningList.clone();
    }

    public static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    public static void main(String[] args) {
        try {
            ReadFile file = new ReadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

