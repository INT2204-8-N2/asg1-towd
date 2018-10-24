package sample.MainController;

import Dictionary.Word;
import ReadFile.ReadFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Sound.TestSound;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static jdk.nashorn.internal.objects.NativeString.trim;

public class MainController implements Initializable {
    @FXML
    public  TableView<Word> wordTableView;

    @FXML
    private TableColumn<Word, String> English;

    @FXML
    public static ObservableList<Word> wordObservableList = FXCollections.observableArrayList();

    @FXML
    private TextField word;

    @FXML
    private TextArea meaning;

    @FXML
    private Button Add;

    @FXML
    private Button Edit;

    @FXML
    private Button Search;

    @FXML
    private Button Delete;

    @FXML
    private Button Translate;

    @FXML
    private Button Pronounce;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ReadFile File = new ReadFile();
            ArrayList<String> englishWordList = new ArrayList<String>();
            englishWordList = File.getEnglishWordList();
            String[] meaningList = File.getMeaningList();

            for (int i = 0; i < englishWordList.size()-1; i++) {
                Word w = new Word();
                w.setWordTarget(englishWordList.get(i));
                w.setWordExplain(meaningList[i]);
                wordObservableList.add(w);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        English.setCellValueFactory(new PropertyValueFactory<Word, String>("wordTarget"));

        FilteredList<Word> filteredData = new FilteredList<>(wordObservableList, w -> true);

        word.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(word -> {
                if (newValue == null || trim(newValue.isEmpty())) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (word.getWordTarget().toLowerCase().contains(lowerCaseFilter))
                    return true;
                return false;
            });
        });

        SortedList<Word> wordSortedList = new SortedList<>(filteredData);
        wordSortedList.comparatorProperty().bind(wordTableView.comparatorProperty());
        wordTableView.setItems(wordSortedList);
    }


    @FXML
    private void handleButtonAction() {
        Add.setOnAction((ActionEvent eventAdd) ->{
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("AddSample.fxml"));
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            Scene scene = new Scene(root);
            Stage newWindow = new Stage();
            newWindow.setTitle("Add Word");
            newWindow.setScene(scene);
            newWindow.show();
        });


        Edit.setOnAction((ActionEvent eventEdit) -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("EditSample.fxml"));
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            Scene scene = new Scene(root);
            Stage newWindow = new Stage();
            newWindow.setTitle("Edit Word");
            newWindow.setScene(scene);
            newWindow.show();
        });

        Delete.setOnAction((ActionEvent eventDelete) ->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Are you want to delete?");

            Optional<ButtonType> result = alert.showAndWait();
            if(result.get()== ButtonType.OK){
                Word selection = wordTableView.getSelectionModel().getSelectedItem();
                wordObservableList.remove(selection);
            }
        });

        Search.setOnAction((ActionEvent eventSearch) ->{
            Word newSearchWord = wordTableView.getSelectionModel().getSelectedItem();
            for(Word w : wordObservableList){
                if(w.getWordTarget().equals(newSearchWord.getWordTarget())){
                    meaning.setText(w.getWordExplain());
                }
            }
        });

        Pronounce.setOnAction((ActionEvent eventPronounce) ->{
            try {
                Word wordPronouce = wordTableView.getSelectionModel().getSelectedItem();
                TestSound testSound = new TestSound(wordPronouce.getWordTarget());
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        });

        Translate.setOnAction((ActionEvent eventTranslate) ->{
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("GTranslate.fxml"));
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
            Scene scene = new Scene(root);
            Stage newWindow = new Stage();
            newWindow.setTitle("Google Translate");
            newWindow.setScene(scene);
            newWindow.show();
        });
    }
}

