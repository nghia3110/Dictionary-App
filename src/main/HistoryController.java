package main;

import base.Word;import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HistoryController extends GeneralController implements Initializable {
    private final ArrayList<Word> historyWordTemp = new ArrayList<>();

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
        int index = getCurrentDic().binaryLookup(0, getCurrentDic().getHistoryVocab().size() - 1, input, getCurrentDic().getHistoryVocab());
        updateWordInListView(input, index, getCurrentDic().getHistoryVocab(), historyWordTemp);
        setHistoryListViewItem();
    }

    @FXML
    private void showHistoryWordDefinition() {
        String spelling = wordListView.getSelectionModel().getSelectedItem();
        int index = 0;
        for (int i = 0; i < getCurrentDic().getHistoryVocab().size(); i++) {
            if (getCurrentDic().getHistoryVocab().get(i).getSearching().equals(spelling)) {
                index = i;
                break;
            }
        }
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

    public void clearPane() {
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
    public void handleClickTransButton() {
        super.handleClickTransButton();
        clearPane();
    }


    protected void initHistoryListView() {
        getCurrentDic().getHistoryVocab().clear();
        wordListView.getItems().clear();
        getCurrentDic().loadDataFromHistoryFile();
        for (int i = getCurrentDic().getHistoryVocab().size() - 1; i >= 0; i--) {
            historySearch.add(getCurrentDic().getHistoryVocab().get(i).getSearching());
        }
        wordListView.setItems(historySearch);
        setLanguage();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}