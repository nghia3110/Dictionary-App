package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.Word;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class HistoryController extends GeneralController implements Initializable {
    private ArrayList<Word> historyWordTemp = new ArrayList<Word>();

    private void setHistoryListViewItem() {
        historySearch.clear();
        wordListView.getItems().clear();
        for (Word temp : historyWordTemp) {
            historySearch.add(temp.getSearching());
        }
        wordListView.setItems(historySearch);
    }

    @FXML
    public void handleHistorySearchBar() {
        historySearch.clear();
        historyWordTemp.clear();
        String input = searchField.getText();
        int index = getCurrentDic().binaryLookup(0, getCurrentDic().getHistoryVocab().size(), input, getCurrentDic().getHistoryVocab());
        updateWordInListView(input, index, getCurrentDic().getHistoryVocab(), historyWordTemp);
        setHistoryListViewItem();
    }

    @FXML
    private void showHistoryWordDefinition() {
        String spelling = wordListView.getSelectionModel().getSelectedItem();
        int index = Collections.binarySearch(getCurrentDic().getHistoryVocab(), new Word(spelling, null));
        String meaning = getCurrentDic().getHistoryVocab().get(index).getMeaning();
        definitionView.getEngine().loadContent(meaning, "text/html");
    }

    @FXML
    public void handleClickRemoveButton() {
        super.handleClickRemoveButton();
        historySearch.clear();
        wordListView.getItems().clear();
        for (Word temp : getCurrentDic().getHistoryVocab()) {
            historySearch.add(temp.getSearching());
        }
        wordListView.setItems(historySearch);
    }

    public void clearPane(){
        searchField.clear();
        definitionView.getEngine().loadContent("");
        historySearch.clear();
        headText.setText("Nghĩa của từ");
        wordListView.getItems().clear();
        for (Word temp : getCurrentDic().getHistoryVocab()) {
            historySearch.add(temp.getSearching());
        }
        wordListView.setItems(historySearch);
    }

    @FXML
    public void handleClickTransButton(){
        super.handleClickTransButton();
        clearPane();
    }


    protected void initHistoryListView() {
        getCurrentDic().getHistoryVocab().clear();
        wordListView.getItems().clear();
        getCurrentDic().loadDataFromHistoryFile();
        for (Word word : getCurrentDic().getHistoryVocab()) {
            historySearch.add(word.getSearching());
        }
        wordListView.setItems(historySearch);
        if(isEVDic){
            transLanguageEV.setVisible(true);
            transLanguageVE.setVisible(false);
            speaker1Language.setText("UK");
            speaker2.setVisible(true);
            speaker2Language.setVisible(true);
        }
        else{
            transLanguageEV.setVisible(false);
            transLanguageVE.setVisible(true);
            speaker1Language.setText("VIE");
            speaker2.setVisible(false);
            speaker2Language.setVisible(false);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
