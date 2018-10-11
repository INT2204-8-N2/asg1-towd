package readFile;
import sample.Word;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import static java.nio.charset.StandardCharsets.UTF_8;

public class readFile {
    public static void readFile() throws IOException{
        String fileName = "D:\\anhviet109K.txt";
        String content = new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8);
        String [] word = content.split("\n\n");

        //System.out.println(word[3]);

        ArrayList<Word> anh = new ArrayList<Word>();

        for(int i=0; i<10; i++){
            String [] b;
            b = word[i].split("/");

//            int size = b.length;
//            String wordExplane = new String();
//            for(int j=1; j<size; j++) {
//                wordExplane += b[j].concat("\n");
//            }
//            Word word1 = new Word();
//            word1.setWordTarget(b[0]);
//            word1.setWordExplain(wordExplane);
//            anh.add(word1);

        }
        System.out.println(anh.get(3).getWordTarget() + "\nNghia: " + anh.get(3).getWordExplain());

}
    public static void main(String[] args) throws IOException {
        readFile();
    }
}
