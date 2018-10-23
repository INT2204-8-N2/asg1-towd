package GoogleTranslate;

import java.io.IOException;
import java.net.MalformedURLException;
import org.json.simple.parser.ParseException;

/**
 *Lấy code trên mạng
 * @author Code4LifeVn
 */

public class TestMain {
    public static void main(String[] args) throws MalformedURLException, IOException, ParseException {
        GoogleTranslate translator = new GoogleTranslate();
        translator.setSrcLang(GoogleTranslate.LANGUAGE.ENGLISH);
        translator.setDestLang(GoogleTranslate.LANGUAGE.VIETNAMESE);
        String data = translator.translate("I heard, that you're settled down\n" +
                "That you found a girl and you're, married now\n" +
                "I heard, that your dreams came true\n" +
                "I guess she gave you things\n" +
                "I didn't give to you\n" +
                "Old friend, why are you so shy\n" +
                "Ain't like you to hold back\n" +
                "Or hide from the light");
        System.out.println(data);
    }


}
