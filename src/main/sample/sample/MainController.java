package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private AnchorPane mainContent;
    @FXML
    private AnchorPane searchPane;
    @FXML
    private AnchorPane translatePane;
    @FXML
    private AnchorPane bookmarkPane;
    @FXML
    private AnchorPane historyPane;
    @FXML
    private AnchorPane settingPane;

    @FXML
    private SearchController searchController;
    @FXML
    private BookmarkController bookmarkController;
    @FXML
    private HistoryController historyController;

    @FXML
    private Button searchButton;
    @FXML
    private Button translateButton;
    @FXML
    private Button bookmarkButton;
    @FXML
    private Button mainHistoryButton;
    @FXML
    private Button settingButton;

    private void setMainContent(AnchorPane anchorPane) {
        mainContent.getChildren().setAll(anchorPane);
    }

    public void resetStyleNav() {
        searchButton.getStyleClass().removeAll("active");
        translateButton.getStyleClass().removeAll("active");
        bookmarkButton.getStyleClass().removeAll("active");
        mainHistoryButton.getStyleClass().removeAll("active");
        settingButton.getStyleClass().removeAll("active");
    }

    @FXML
    public void showSearchPane() {
        resetStyleNav();
        searchButton.getStyleClass().add("active");
        searchController.initSearchListView();
        setMainContent(searchPane);
    }

    @FXML
    public void showTranslatePane() {
        resetStyleNav();
        translateButton.getStyleClass().add("active");
        setMainContent(translatePane);
    }

    @FXML
    public void showBookmarkPane() {
        resetStyleNav();
        bookmarkButton.getStyleClass().add("active");
        bookmarkController.initBookmarkListView();
        setMainContent(bookmarkPane);
    }

    @FXML
    public void showHistoryPane() {
        resetStyleNav();
        mainHistoryButton.getStyleClass().add("active");
        historyController.initHistoryListView();
        setMainContent(historyPane);
    }

    @FXML
    public void showSettingPane() {
        resetStyleNav();
        settingButton.getStyleClass().add("active");
        setMainContent(settingPane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("search.fxml"));
            searchPane = loader.load();
            searchController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("history.fxml"));
            historyPane = loader.load();
            historyController = loader.getController();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookmark.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("setting.fxml"));
            settingPane = loader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        searchButton.getStyleClass().add("active");
        mainContent.getChildren().setAll(searchPane);
    }
}