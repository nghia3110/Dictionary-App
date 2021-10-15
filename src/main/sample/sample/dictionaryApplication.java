package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.IndexRange;
import javafx.stage.Stage;
import main.VoiceRSS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class dictionaryApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        loadSettingFromFile();
        Parent root = FXMLLoader.load(getClass().getResource("/sample/main.fxml"));
        primaryStage.setTitle("Dictionary");
        primaryStage.setScene(new Scene(root, 855, 600));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            saveSettingToFile();
        });
    }

    public void loadSettingFromFile(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resource/saveSettings/saveSetting.txt"));
            String line;
            int cnt = 0;
            while ((line = reader.readLine()) != null) {
                if(cnt == 0){
                    GeneralController.isEVDic = Boolean.parseBoolean(line);
                }
                if(cnt == 1){
                    VoiceRSS.voiceNameUK = line;
                }
                if (cnt == 2){
                    VoiceRSS.voiceNameUS = line;
                }
                if(cnt == 3){
                    VoiceRSS.speed = Integer.parseInt(line);
                }
                cnt++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveSettingToFile(){
        try{
            File file = new File("src/main/resource/saveSettings/saveSetting.txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(String.valueOf(GeneralController.isEVDic) + "\n");
            fileWriter.write(VoiceRSS.voiceNameUK + "\n");
            fileWriter.write(VoiceRSS.voiceNameUS + "\n");
            fileWriter.write(String.valueOf(VoiceRSS.speed));
            fileWriter.flush();
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
