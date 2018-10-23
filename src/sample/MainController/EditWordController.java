package sample.MainController;

import BinarySearch.BinarySearch;
import Dictionary.Word;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Comparator;
import java.util.ResourceBundle;

import static jdk.nashorn.internal.objects.NativeString.trim;
import static sample.MainController.MainController.wordObservableList;

public class EditWordController implements Initializable {
    @FXML
    private TextField oldWord;

    @FXML
    private TextField newWord;
    @FXML
    private TextArea meaning;

    @FXML
    private Button Save;

    public void EditWord() {
        Save.setOnAction((ActionEvent eventEdit) -> {
            int size = wordObservableList.size();
            String [] List = new String[size];
            for(int i=0; i<size; i++){
                List[i] = trim(wordObservableList.get(i).getWordTarget());
            }

            String oldWordText = oldWord.getText();
            String newWordText = newWord.getText();
            String meaningText = meaning.getText();

            if(BinarySearch.binarySearch(List, trim(oldWordText)) != -1) {
                for (int i = 0; i < size; i++) {
                    if (trim(oldWordText).equals(trim(MainController.wordObservableList.get(i).getWordTarget()))) {
                        MainController.wordObservableList.get(i).setWordTarget(trim(newWordText));
                        MainController.wordObservableList.get(i).setWordExplain(trim(meaningText));
                    }
                }

                //Sap xep lai danh sach sau khi sua
                if (!wordObservableList.isEmpty()) {
                    FXCollections.sort(wordObservableList, new Comparator<Word>() {
                        @Override
                        public int compare(Word w1, Word w2) {
                            return w1.getWordTarget().compareTo(w2.getWordTarget());
                        }
                    });
                }

                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Edit Word");
                alert1.setContentText("Edit success!");
                alert1.show();
                Stage stage = (Stage) Save.getScene().getWindow();
                stage.close();
            }
            else{
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Edit Word");
                alert2.setContentText("Edit error!");
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
        meaning.textProperty().addListener((observable, oldValue, newValue) -> {
            saveButton.setDisable(newValue.trim().isEmpty());
        });
    }
}

