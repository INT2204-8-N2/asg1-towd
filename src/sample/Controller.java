package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller<name> implements Initializable {
    @FXML
    private TableView<Word> wordTableView;

    @FXML
    private TableColumn<Word, String> wordTargetColum;

    private ObservableList<Word> wordObservableList;

    @FXML
    private TextField wordTargetText;

    @FXML
    private TextField getSearchWord;

    @FXML
    private TextArea textArea = new TextArea();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        wordObservableList = FXCollections.observableArrayList(
                new Word("target", "muc dich"),
                new Word("student", "hoc sinh"),
                new Word("school", "truong hoc")
        );

        wordTargetColum.setCellValueFactory(new PropertyValueFactory<Word, String>("wordTarget"));

        // tìm kiếm tuyến tính
        FilteredList<Word> filteredData = new FilteredList<>(wordObservableList, w -> true);

        getSearchWord.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(word -> {
                if (newValue == null || newValue.isEmpty()) {
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

    public void searchWord(ActionEvent eventSearchWord){ // tìm kiếm cả từ
        Word newSearchWord = wordTableView.getSelectionModel().getSelectedItem();
        for(Word w : wordObservableList){
            if(w.getWordTarget().equals(newSearchWord.getWordTarget())){
                textArea.setText(w.getWordExplain());
            }
        }
    }

    // xóa từ khỏi từ điển
    public void delete(ActionEvent event){
        Word selection = wordTableView.getSelectionModel().getSelectedItem();
        wordObservableList.remove(selection);
    }

    public void deleteWord(ActionEvent eventDeleteWord){
        TextInputDialog inputDialog = new TextInputDialog("Nhap tu can xoa: ");
        Optional<String> result = inputDialog.showAndWait();

        Word word = new Word();
        result.ifPresent(deleteWord ->{

            word.setWordTarget(deleteWord);
            wordObservableList.remove(word);
        });
    }

    @FXML
    public void addWord(ActionEvent eventAddWord) {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Insert Word");

        ButtonType InsertButtonType = new ButtonType("Insert", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(InsertButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        final TextField newWord = new TextField();
        newWord.setPromptText("Target");

        final TextField explain = new TextField();
        explain.setPromptText("Explain");

        grid.add(new javafx.scene.control.Label("Word: "), 0, 0);
        grid.add(newWord, 1, 0);
        grid.add(new javafx.scene.control.Label("Meaning: "), 0, 1);
        grid.add(explain, 1, 1);

        final Node Add = dialog.getDialogPane().lookupButton(InsertButtonType);
        Add.setDisable(true);

        newWord.textProperty().addListener((observable, oldvalue, newvalue) -> {
            Add.setDisable(newvalue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == InsertButtonType) {
                return new Pair<String, String>(newWord.getText(), explain.getText());
            }
            return null;
        });

        Optional<Pair<String,String>> result = dialog.showAndWait();
        result.ifPresent(oldTargetnewTarget -> {
            Word newWordTarget = new Word();
            newWordTarget.setWordTarget(newWord.getText());
            newWordTarget.setWordExplain(explain.getText());
            wordObservableList.add(newWordTarget);
            if(true) {
                //thông báo alert neu thêm thành công
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Insert Word");
                alert1.setContentText("Add success!");
                alert1.show();
            }
            else {
                //thông báo neu thanh cong
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Insert Word");
                alert1.setContentText("Add error!");
                alert1.show();
            }
        });
    }
}
