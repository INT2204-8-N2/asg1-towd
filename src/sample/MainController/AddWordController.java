package sample.MainController;

import BinarySearch.BinarySearch;
import Dictionary.Word;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import static jdk.nashorn.internal.objects.NativeString.trim;
import static sample.MainController.MainController.wordObservableList;

public class AddWordController implements Initializable {
    @FXML
    private TextField word;

    @FXML
    private TextArea meaning;

    @FXML
    private GridPane gridPane;

    @FXML
    private Button Save;

    public void AddWord(){
        Save.setOnAction((ActionEvent event) ->{
            Word word_ = new Word();
            word_.setWordTarget(trim(word.getText()));
            word_.setWordExplain(trim(meaning.getText()));

            int size = wordObservableList.size();
            String [] List = new String[size];
            for(int i=0; i<size; i++){
                List[i] = trim(wordObservableList.get(i).getWordTarget());
            }

            System.out.println(BinarySearch.binarySearch(List, word.getText()));

            if(BinarySearch.binarySearch(List, trim(word.getText())) == -1){
                wordObservableList.add(word_);
                //Sap xep lai danh sach sau khi them
                if (!wordObservableList.isEmpty()) {
                    FXCollections.sort(wordObservableList, new Comparator<Word>() {
                        @Override
                        public int compare(Word w1, Word w2) {
                            return w1.getWordTarget().compareTo(w2.getWordTarget());
                        }
                    });
                }

                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Add Word");
                alert1.setContentText("Add success!");
                alert1.show();
                Stage stage = (Stage) Save.getScene().getWindow();
                stage.close();
            }
            else{
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Add Word");
                alert2.setContentText("Add error!");
                alert2.show();

            }
        });
    }

    @FXML
    private Button Cancle;

    public void Cancle(){
        Cancle.setOnAction((ActionEvent eventCancle) ->{
            Stage stage = (Stage) Cancle.getScene().getWindow();
            stage.close();
        });
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Node saveButton = Save;
        saveButton.setDisable(true);
        meaning.textProperty().addListener((observable, oldValue, newValue) ->{
            saveButton.setDisable(newValue.trim().isEmpty());
        });
    }

}

