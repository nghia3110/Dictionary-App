package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import main.DictionaryManagement;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane mainContent;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private AnchorPane historyPane;
    @FXML
    private AnchorPane bookmarkPane;
    @FXML
    private AnchorPane translatePane;
    @FXML
    private AnchorPane settingPane;

    @FXML
    private SearchPaneController searchPaneController;
    @FXML
    private HistoryController historyController;
    @FXML
    private BookmarkController bookmarkController;

    @FXML
    private Button searchButton;
    @FXML
    private Button translateButton;
    @FXML
    private Button mainHistoryButton;
    @FXML
    private Button bookmarkButton;
    protected GeneralController currentController;

    private void setMainContent(AnchorPane anchorPane) {
        mainContent.getChildren().setAll(anchorPane);
    }

    @FXML
    public void showSearchPane() {
        searchPaneController.initSearchListView();
        setMainContent(searchPane);
    }

    @FXML
    public void showHistoryPane() {
        historyController.initHistoryListView();
        setMainContent(historyPane);
    }

    @FXML
    public void showBookmarkPane() {
        bookmarkController.initBookmarkListView();
        setMainContent(bookmarkPane);
    }

    @FXML
    public void showTranslatePane() {
        setMainContent(translatePane);
    }

    @FXML
    public void showSettingPane() {
        setMainContent(settingPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchPane.fxml"));
            searchPane = loader.load();
            searchPaneController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("historyPane.fxml"));
            historyPane = loader.load();
            historyController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookmarkPane.fxml"));
            bookmarkPane = loader.load();
            bookmarkController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("translate.fxml"));
            translatePane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Settings.fxml"));
            settingPane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainContent.getChildren().setAll(searchPane);
    }
}
