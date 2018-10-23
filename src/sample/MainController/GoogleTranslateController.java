package sample.MainController;

import GoogleTranslate.GoogleTranslate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import static jdk.nashorn.internal.objects.NativeString.trim;

public class GoogleTranslateController implements Initializable {
    @FXML
    private Button GoogleDich;

    @FXML
    private Button Cancle;

    @FXML
    private TextArea word;

    @FXML
    private TextArea meaning;

    public void setTranslate(){
        GoogleDich.setOnAction((ActionEvent eventTranslate) ->{
            try {
                GoogleTranslate translator = new GoogleTranslate();
                translator.setSrcLang(GoogleTranslate.LANGUAGE.ENGLISH);
                translator.setDestLang(GoogleTranslate.LANGUAGE.VIETNAMESE);

                String word_ = trim(word.getText());
                String data = translator.translate(word_);
                meaning.setText(data);
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        });
    }

    public void Cancle(){
        Cancle.setOnAction((ActionEvent eventCancle) ->{
            Stage stage = (Stage) Cancle.getScene().getWindow();
            stage.close();
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node saveButton = GoogleDich;
        saveButton.setDisable(true);
        word.textProperty().addListener((observable, oldValue, newValue) -> {
            saveButton.setDisable(newValue.trim().isEmpty());
        });
    }
}
