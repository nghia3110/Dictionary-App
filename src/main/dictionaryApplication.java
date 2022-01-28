package main;

import base.VoiceRSS;import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Objects;

public class dictionaryApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        loadSettingFromFile();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/main/main.fxml")));
        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(new Scene(root, 857, 600));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> saveSettingToFile());
    }

    public void loadSettingFromFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/resource/saveSetting/saveSetting.txt"));
            String line;
            int cnt = 0;
            while ((line = reader.readLine()) != null) {
                if (cnt == 0) {
                    GeneralController.isEVDic = Boolean.parseBoolean(line);
                }
                if (cnt == 1) {
                    VoiceRSS.voiceNameUK = line;
                }
                if (cnt == 2) {
                    VoiceRSS.voiceNameUS = line;
                }
                if (cnt == 3) {
                    VoiceRSS.speed = Double.parseDouble(line);
                }
                cnt++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveSettingToFile() {
        try {
            File file = new File("src/resource/saveSetting/saveSetting.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(GeneralController.isEVDic + "\n");
            fileWriter.write(VoiceRSS.voiceNameUK + "\n");
            fileWriter.write(VoiceRSS.voiceNameUS + "\n");
            fileWriter.write(String.valueOf(VoiceRSS.speed));
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
