package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.*;
import java.io.File;
import javafx.scene.text.Font;





public class Main extends Application {
    Label msgLabel = new Label("");
    public static void main(String[] args) {
        launch(args);
    }
    @Override
       public void start(Stage stage){
        String musicfile = "music.mp3";
        Media sound = new Media(new File(musicfile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        VBox root = new VBox();
        Stage NewGameWindow = new Stage();
        Stage OptionsWindow = new Stage();
        OptionsWindow.setX(200);
        OptionsWindow.setY(200);
        OptionsWindow.setMinHeight(400);
        OptionsWindow.setMinWidth(400);
        Button SoundEnable = new Button("Enable sound");
        Button SoundDisable = new Button("Disable sound");
        SoundDisable.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent event) {
           mediaPlayer.pause();

        }});
        SoundEnable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.play();
            }
        });
        VBox root2 = new VBox();
        Button Exit = new Button("Exit");
        Exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OptionsWindow.close();
                stage.close();
            }
        });
        int[] runValue = new int[3];
        boolean DifChosen[]= new boolean[1];
        DifChosen[0] = false;
        MenuItem Easy = new MenuItem("Easy");
        MenuItem Normal = new MenuItem("Normal");
        MenuItem Hard = new MenuItem("Hard");
        MenuItem Insane = new MenuItem("Insane");
        Easy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                runValue[0] = 5;
                runValue[1] = 7;
                runValue[2] = 8;
                DifChosen[0] = true;
            }
        });
        Normal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                runValue[0] = 8;
                runValue[1] = 10;
                runValue[2] = 18;
                DifChosen[0] = true;
            }
        });
        Hard.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                runValue[0] = 12;
                runValue[1] = 14;
                runValue[2] = 25;
                DifChosen[0] = true;
            }
        }));
        Insane.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                runValue[0] = 25;
                runValue[1] = 30;
                runValue[2] = 150;
                DifChosen[0] = true;
            }
        });
        MenuButton Difficulty = new MenuButton("Difficulty");
        Difficulty.getItems().addAll(Easy, Normal, Hard, Insane);
        root2.getChildren().addAll(Difficulty, SoundDisable, SoundEnable);
        Scene OptionsScene = new Scene(root2);
        OptionsWindow.setScene(OptionsScene);
        NewGameWindow.setX(300);
        NewGameWindow.setY(300);
        NewGameWindow.setMinWidth(200);
        NewGameWindow.setMinHeight(200);
        Button NewGameBtn = new Button("New Game");
        Button OptionsBtn = new Button("Options");
        OptionsBtn.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                OptionsWindow.show();
            }
        }));
        NewGameBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                NewGameWindow.show();
                stage.close();
            }
        });
        VBox croot = new VBox();
        root.getChildren().addAll(NewGameBtn, OptionsBtn, Exit);
        stage.setX(250);
        stage.setY(250);
        stage.setMinHeight(350);
        stage.setMinWidth(350);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        root.setStyle("-fx-padding: 150;");
        stage.setTitle("Sapper");
        stage.show();
        Stage LossStage = new Stage();
        VBox lossroot = new VBox();
        Scene LossScene = new Scene(lossroot);
        lossroot.setStyle("-fx-padding: 150");
        Text losstext = new Text("You Died");
        losstext.setFont(Font.font(52));
        lossroot.getChildren().add(losstext);
        lossroot.setMinSize(250,250);
        lossroot.setPrefSize(500,500);
        String lossfile = "lossmusic.mp3";
        Media losssound = new Media(new File(lossfile).toURI().toString());
        MediaPlayer lossPlayer = new MediaPlayer(losssound);
        mediaPlayer.stop();
        lossPlayer.play();
        LossStage.setScene(LossScene);
        LossStage.show();



    }
    public void printMessage(String msg){
        msgLabel.setText(msg);
    }
}
