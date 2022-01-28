package main;

import base.Word;import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class BookmarkController extends GeneralController implements Initializable {
    private final ArrayList<Word> bookmarkWordTemp = new ArrayList<>();

    private void setBookmarkListViewItem() {
        bookmarkSearch.clear();
        wordListView.getItems().clear();
        for (Word temp : bookmarkWordTemp) {
            bookmarkSearch.add(temp.getSearching());
        }
        wordListView.setItems(bookmarkSearch);
    }

    @FXML
    public void handleBookmarkSearchBar() {
        bookmarkWordTemp.clear();
        bookmarkSearch.clear();
        String input = searchField.getText();
        int index = getCurrentDic().binaryLookup(0, getCurrentDic().getBookmarkVocab().size() - 1, input, getCurrentDic().getBookmarkVocab());
        updateWordInListView(input, index, getCurrentDic().getBookmarkVocab(), bookmarkWordTemp);
        setBookmarkListViewItem();
    }

    @FXML
    private void showBookmarkWordDefinition() {
        String spelling = wordListView.getSelectionModel().getSelectedItem();
        int index = Collections.binarySearch(getCurrentDic().getBookmarkVocab(), new Word(spelling, null));
        String meaning = getCurrentDic().getBookmarkVocab().get(index).getMeaning();
        definitionView.getEngine().loadContent(meaning, "text/html");
    }

    public void clearPane() {
        searchField.clear();
        definitionView.getEngine().loadContent("");
        bookmarkSearch.clear();
        headText.setText("Nghĩa của từ");
        wordListView.getItems().clear();
        for (Word temp : getCurrentDic().getBookmarkVocab()) {
            bookmarkSearch.add(temp.getSearching());
        }
        wordListView.setItems(bookmarkSearch);
    }

    @FXML
    public void handleClickTransButton() {
        super.handleClickTransButton();
        clearPane();
    }

    protected void initBookmarkListView() {
        getCurrentDic().getBookmarkVocab().clear();
        wordListView.getItems().clear();
        getCurrentDic().loadDataFromHTMLFile(getCurrentDic().getBOOKMARK_PATH(), getCurrentDic().getBookmarkVocab());
        for (Word word : getCurrentDic().getBookmarkVocab()) {
            bookmarkSearch.add(word.getSearching());
        }
        wordListView.setItems(bookmarkSearch);
        setLanguage();
    }

    @FXML
    public void handleClickBookmarkButton() {
        String spelling = searchField.getText();
        if (spelling.equals("")) {
            showWarningAlert();
            return;
        }
        int index = Collections.binarySearch(getCurrentDic().getBookmarkVocab(), new Word(spelling, null));
        removeBookmark(getCurrentDic().getBookmarkVocab().get(index));
        searchField.clear();
        headText.setText("Nghĩa của từ");
        definitionView.getEngine().loadContent("");
        bookmarkSearch.clear();
        wordListView.getItems().clear();
        for (Word temp : getCurrentDic().getBookmarkVocab()) {
            bookmarkSearch.add(temp.getSearching());
        }
        wordListView.setItems(bookmarkSearch);
    }

    @FXML
    public void handleClickRemoveButton() {
        super.handleClickRemoveButton();
        bookmarkSearch.clear();
        wordListView.getItems().clear();
        for (Word temp : getCurrentDic().getBookmarkVocab()) {
            bookmarkSearch.add(temp.getSearching());
        }
        wordListView.setItems(bookmarkSearch);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}