package sample;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import main.Spelling;
import main.Word;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class SearchPaneController extends GeneralController implements Initializable {
    private ArrayList<Word> searchWordTemp = new ArrayList<Word>();

    @Override
    public void initialize(URL location, ResourceBundle resources){
        for (Word temp : getCurrentDic().getVocab()) {
            searchWordTemp.add(temp);
            searchList.add(temp.getSearching());
        }
        wordListView.setItems(searchList);
    }

    public void setSearchListViewItem() {
        searchList.clear();
        for (Word temp : searchWordTemp) {
            searchList.add(temp.getSearching());
        }
        wordListView.setItems(searchList);
    }

    @FXML
    public void searchFieldAction() throws IOException {
        searchWordTemp.clear();
        searchList.clear();
        String word = searchField.getText();
        int index = getCurrentDic().binaryLookup(0, getCurrentDic().getVocab().size(), word,  getCurrentDic().getVocab());
        if (index < 0) {
            Spelling corrector = new Spelling("src/main/resource/vocab/spelling.txt");
            word = corrector.correct(word);
            index = getCurrentDic().binaryLookup(0,  getCurrentDic().getVocab().size(), word,  getCurrentDic().getVocab());
        }
        updateWordInListView(word, index,  getCurrentDic().getVocab(), searchWordTemp);
        setSearchListViewItem();
    }


    @FXML
    public void showDefinition() {
        String spelling = wordListView.getSelectionModel().getSelectedItem();
        int index = Collections.binarySearch( getCurrentDic().getVocab(), new Word(spelling, null));
        String meaning =  getCurrentDic().getVocab().get(index).getMeaning();
        definitionView.getEngine().loadContent(meaning, "text/html");
        if (Collections.binarySearch(getCurrentDic().getHistoryVocab(), new Word(spelling, null)) <= 0) {
            getCurrentDic().addWordToFile(spelling, meaning, getCurrentDic().getHISTORY_PATH());        }
    }

    @FXML
    public void handleClickRemoveButton() {
        super.handleClickRemoveButton();
        searchList.clear();
        wordListView.getItems().clear();
        for (Word temp : getCurrentDic().getVocab()) {
            searchList.add(temp.getSearching());
        }
        wordListView.setItems(searchList);
    }

    public void initSearchListView() {
        wordListView.getItems().clear();
        setSearchListViewItem();
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
}
