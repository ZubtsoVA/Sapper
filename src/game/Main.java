package game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.media.Media;
import javafx.scene.media.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.util.Random;






public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public static GridPane create(int height, int width, int bombCount) {
        int[][] bombs = new int[height][width];
        Random rand = new Random();
        int j;
        for (int i = 0; i < bombCount; ) {
            j = rand.nextInt(height * width);
            if (bombs[j / width][j % width] != -1) {
                bombs[j / width][j % width] = -1;
                i = i + 1;
            }
        }


        Fields fields = new Fields(bombs, height ,width );
        GridPane grid = new GridPane();
        for (int i = 0; i < height; i = i + 1) {
            for (j = 0; j < width; j = j + 1) {
                grid.add(fields.fields[i][j], i, j);
            }

        }
        return grid;
    }
    @Override
        public void start(Stage stage) {
        Image StImage = new Image(getClass().getResourceAsStream("/1sapper.jpg"));
        ImageView img = new ImageView(StImage);
        double DefHeight = 400;
        double DefWidth = 400;
        stage.setX(250);
        stage.setY(250);
        stage.setMinHeight(DefHeight);
        stage.setMaxHeight(DefHeight);
        stage.setMinWidth(DefHeight);
        stage.setMaxWidth(DefWidth);
        Pane mainRoot = new Pane();
        Scene scene = new Scene(mainRoot);
        stage.setScene(scene);
        mainRoot.getChildren().add(img);
        stage.setTitle("Sapper");
        stage.show();
        img.setFitHeight(DefHeight);
        img.setFitWidth(DefWidth);
        String MusicFile = "music.mp3";
        Media sound = new Media(new File(MusicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        VBox MenuBox = new VBox();
        Button SoundEnable = new Button("Enable sound");
        boolean[] songIsPlaying = new boolean[1];
        Button SoundDisable = new Button("Disable sound");
        SoundDisable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.pause();
                songIsPlaying[0] = false;

            }
        });
        SoundEnable.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                songIsPlaying[0] = true;
                mediaPlayer.play();
            }
        });
        VBox OptionsRoot = new VBox();
        Button Exit = new Button("Exit");
        Exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
            }
        });
        Button BackBtn = new Button("Back");
        BackBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene);
            }
        });
        int[] runValue = new int[3];
        boolean DifChosen[] = new boolean[1];
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
        OptionsRoot.setTranslateX(150);
        OptionsRoot.setTranslateY(75);
        OptionsRoot.getChildren().addAll(Difficulty, SoundDisable, SoundEnable, BackBtn);
        OptionsRoot.setSpacing(20);
        Scene OptionsScene = new Scene(OptionsRoot);
        Button NewGameBtn = new Button("New Game");
        Button OptionsBtn = new Button("Options");
        OptionsBtn.setOnAction((new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                stage.setScene(OptionsScene);
            }
        }));

        NewGameBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (DifChosen[0]) {
                    GridPane grid = create(runValue[0], runValue[1], runValue[2]);
                    Field.createScenenStage(grid);
                    Field.curStage.show();

                    stage.close();
                } else {
                    GridPane grid = create(5, 7 ,8);
                    Field.createScenenStage(grid);
                    stage.close();
                    Field.curStage.show();

                }

            }

        });
        Button InGameSound = new Button("Sound");
        InGameSound.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (songIsPlaying[0]) {
                    mediaPlayer.pause();
                    songIsPlaying[0] = false;
                } else {
                    songIsPlaying[0] = true;
                    mediaPlayer.play();

                }
            }
        });
        MenuBox.getChildren().addAll(NewGameBtn, OptionsBtn, Exit);
        MenuBox.setSpacing(20);
        MenuBox.setAlignment(Pos.CENTER);
        MenuBox.setTranslateY(150);
        MenuBox.setTranslateX(150);
        mainRoot.getChildren().add(MenuBox);


    }
}


