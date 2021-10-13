package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import main.DictionaryManagement;
import main.NewDictionary;
import main.VoiceRSS;
import main.Word;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class GeneralController extends MainController implements Initializable {
    private static String EV_PATH = "src/main/resource/vocab/eng_vie.txt";
    private static String VE_PATH = "src/main/resource/vocab/vie_eng.txt";
    private static String ENG_HISTORY_PATH = "src/main/resource/vocab/eng_history.txt";
    private static String VIE_HISTORY_PATH = "src/main/resource/vocab/vie_history.txt";
    private static String ENG_BOOKMARK_PATH = "src/main/resource/vocab/eng_bookmark.txt";
    private static String VIE_BOOKMARK_PATH = "src/main/resource/vocab/vie_bookmark.txt";

    protected final ArrayList<String> historyList = new ArrayList<String>();
    protected final ArrayList<String> bookmarkList = new ArrayList<String>();
    protected ObservableList<String> bookmarkSearch = FXCollections.observableArrayList();
    protected final ObservableList<String> searchList = FXCollections.observableArrayList();
    protected ObservableList<String> historySearch = FXCollections.observableArrayList();

    @FXML
    protected ListView<String> wordListView;
    @FXML
    protected WebView definitionView;
    @FXML
    protected HTMLEditor editDefinition;
    @FXML
    protected Button saveChangeButton;
    @FXML
    protected TextField searchField;
    @FXML
    protected Button bookmarkButton;
    @FXML
    protected Button removeButton;
    @FXML
    protected Button editButton;
    @FXML
    protected Button speaker1;
    @FXML
    protected Button speaker2;
    @FXML
    protected Button transLanguageEV;
    @FXML
    protected Button transLanguageVE;
    @FXML
    protected Label headText;
    @FXML
    protected Label speaker1Language;
    @FXML
    protected Label speaker2Language;
    @FXML
    protected ChoiceBox<String> choiceboxuk;
    @FXML
    protected ChoiceBox<String> choiceboxus;
    @FXML
    protected Slider slider;

    protected boolean isOnEditDefinition = false;
    protected static boolean isEVDic = true;

    protected static NewDictionary evDic = new NewDictionary(EV_PATH, ENG_HISTORY_PATH, ENG_BOOKMARK_PATH);
    protected static NewDictionary veDic = new NewDictionary(VE_PATH, VIE_HISTORY_PATH, VIE_BOOKMARK_PATH);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void speak(String language) throws Exception {
        VoiceRSS.language = language;
        VoiceRSS.speakWord(headText.getText());
    }

    @FXML
    public void handleClickSpeaker1() throws Exception {
        if(isEVDic){
            VoiceRSS.Name = VoiceRSS.voiceNameUK;
            speak("en-gb");
        }
        else{
            VoiceRSS.Name = "Chi";
            speak("vi-vn");
        }
    }

    @FXML
    public void handleClickSpeaker2() throws Exception {
        VoiceRSS.Name = VoiceRSS.voiceNameUS;
        speak("en-us");
    }

    public void clearPane(){
        searchField.clear();
        definitionView.getEngine().loadContent("");
        searchList.clear();
        headText.setText("Nghĩa của từ");
        wordListView.getItems().clear();
        for (Word temp : getCurrentDic().getVocab()) {
            searchList.add(temp.getSearching());
        }
        wordListView.setItems(searchList);
    }

    @FXML
    public void handleClickTransButton(){
        if(isEVDic){
            isEVDic = false;
            transLanguageEV.setVisible(false);
            transLanguageVE.setVisible(true);
            speaker1Language.setText("VIE");
            speaker2.setVisible(false);
            speaker2Language.setVisible(false);
        }
        else{
            isEVDic = true;
            transLanguageEV.setVisible(true);
            transLanguageVE.setVisible(false);
            speaker1Language.setText("UK");
            speaker2.setVisible(true);
            speaker2Language.setVisible(true);
        }
        clearPane();
    }

    public NewDictionary getCurrentDic() {
        if(isEVDic) return evDic;
        else return veDic;
    }

    public void updateWordInListView(String word, int index, ArrayList<Word> res, ArrayList<Word> des) {
        if (index < 0) {
            return;
        }
        int j = index;
        while (j >= 0) {
            if (DictionaryManagement.isContain(word, res.get(j).getSearching()) == 0) {
                j--;
            } else {
                break;
            }
        }
        for (int i = j + 1; i <= index; i++) {
            Word temp = new Word(res.get(i).getSearching(), res.get(i).getMeaning());
            des.add(temp);
        }
        for (int i = index + 1; i < res.size(); i++) {
            if (DictionaryManagement.isContain(word, res.get(i).getSearching()) == 0) {
                Word temp = new Word(res.get(i).getSearching(), res.get(i).getMeaning());
                des.add(temp);
            } else {
                break;
            }
        }
    }

    @FXML
    public void handleClickListView() {
        String spelling = wordListView.getSelectionModel().getSelectedItem();
        searchField.setText(spelling);
        headText.setText(spelling);
    }

    protected void addBookmark(Word word) {
        getCurrentDic().getBookmarkVocab().add(word);
        getCurrentDic().addWordToFile(word.getSearching(), word.getMeaning(),getCurrentDic().getBOOKMARK_PATH());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Lưu từ thành công!");
        alert.showAndWait();
    }

    protected void removeBookmark(Word word) {
        getCurrentDic().getBookmarkVocab().remove(word);
        getCurrentDic().updateWordToFile(getCurrentDic().getBOOKMARK_PATH(), getCurrentDic().getBookmarkVocab());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Đã xoá từ khỏi danh sách yêu thích!");
        alert.showAndWait();
    }

    @FXML
    public void handleClickBookmarkButton() {
        String spelling = searchField.getText();
        if(spelling.equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Không có từ nào được chọn!");
            alert.showAndWait();
            return;
        }
        int _index = Collections.binarySearch(getCurrentDic().getVocab(), new Word(spelling, null));
        int index = Collections.binarySearch(getCurrentDic().getBookmarkVocab(), new Word(spelling, null));
        if (index < 0) {
            addBookmark(getCurrentDic().getVocab().get(_index));
        } else {
            removeBookmark(getCurrentDic().getBookmarkVocab().get(index));
        }
    }

    @FXML
    public void handleClickEditButton() {
        String spelling = searchField.getText();
        if(spelling.equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Không có từ nào được chọn!");
            alert.showAndWait();
            return;
        }
        if (isOnEditDefinition) {
            isOnEditDefinition = false;
            editDefinition.setVisible(false);
            saveChangeButton.setVisible(false);
            return;
        }
        isOnEditDefinition = true;
        saveChangeButton.setVisible(true);
        editDefinition.setVisible(true);
        int index = Collections.binarySearch(getCurrentDic().getVocab(), new Word(spelling, null));
        String meaning = getCurrentDic().getVocab().get(index).getMeaning();
        editDefinition.setHtmlText(meaning);
    }

    @FXML
    public void handleClickSaveButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Sửa từ thành công!");
        alert.showAndWait();
        editDefinition.setVisible(false);
        isOnEditDefinition = false;
        saveChangeButton.setVisible(false);
        String newMeaning = editDefinition.getHtmlText().replace(" dir=\"ltr\"", "");
        String spelling = searchField.getText();
        saveWordToFile(getCurrentDic().getPATH(), getCurrentDic().getVocab(), spelling, newMeaning);
        saveWordToFile(getCurrentDic().getHISTORY_PATH(), getCurrentDic().getHistoryVocab(), spelling, newMeaning);
        saveWordToFile(getCurrentDic().getBOOKMARK_PATH(), getCurrentDic().getBookmarkVocab(), spelling, newMeaning);
        definitionView.getEngine().loadContent(newMeaning, "text/html");
    }

    private void saveWordToFile(String path, ArrayList<Word> temp, String spelling, String meaning) {
        int index = Collections.binarySearch(temp, new Word(spelling, null));
        if (index >= 0) {
            temp.get(index).setMeaning(meaning);
            getCurrentDic().updateWordToFile(path, temp);
        }
    }

    @FXML
    public void handleClickRemoveButton() {
        String spelling = searchField.getText();
        if(spelling.equals("")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setHeaderText(null);
            alert.setContentText("Không có từ nào được chọn!");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText("Bạn có chắc chắn muốn xoá từ này không?");
        alert.showAndWait();
        if(alert.getResult() == ButtonType.OK) {
            getCurrentDic().removeWord(spelling, getCurrentDic().getPATH(), getCurrentDic().getVocab());
            getCurrentDic().removeWord(spelling, getCurrentDic().getHISTORY_PATH(), getCurrentDic().getHistoryVocab());
            getCurrentDic().removeWord(spelling, getCurrentDic().getBOOKMARK_PATH(), getCurrentDic().getBookmarkVocab());
            headText.setText("Nghĩa của từ");
            searchField.clear();
            definitionView.getEngine().loadContent("");
        }
    }
}