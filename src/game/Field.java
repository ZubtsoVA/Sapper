package game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;


public class Field extends Button implements EventHandler<MouseEvent>{
    private int mineCount;
    private int row;
    private int col;
    private int height;
    private int width;
    private boolean closed;
    private boolean isMarked;
    private Fields fields;
    private static final String lossfile = "lossmusic.mp3";
    private static final  Media losssound = new Media(new File(lossfile).toURI().toString());
    private static final MediaPlayer lossPlayer = new MediaPlayer(losssound);

    public static Stage curStage;
    public Field(int inRow, int inCol,int inHeight,int inWidth,  int inMineCount, Fields inFields ) {
        row = inRow;
        col = inCol;
        height = inHeight;
        width = inWidth;
        closed = true;
        this.mineCount = inMineCount;
        this.setMinWidth(40);
        this.setMinHeight(40);
        this.setMaxWidth(40);
        this.setMaxHeight(40);
        fields = inFields;
        //this.setArcHeight(5);
        //this.setArcWidth(5);
        //this.setFill(Color.rgb(137,129,118));
        setOnMouseClicked(this);
    }
    private void vin(){
        VBox wonRoot = new VBox();
        wonRoot.setAlignment(Pos.CENTER);
        Text wonText = new Text("You win!");
        wonText.setFont(Font.font(72));
        wonRoot.getChildren().add(wonText);
        Scene scene = new Scene(wonRoot);
        curStage.setScene(scene);
    }
    public static void createScenenStage(GridPane grid){
        Scene scene = new Scene(grid);
        Stage stage = new Stage();
        stage.setScene(scene);
        Field.curStage = stage;
    }


    private void Open(){
        if (closed){
            closed = false;
            fields.IncOpened();
            if (this.mineCount == -1) {
                Pane LossPic = new Pane();
                MenuButton nGBtn = new MenuButton("Start over?");
                MenuItem Easy = new MenuItem("Easy");
                Easy.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        GridPane grid = Main.create(5, 7, 8);
                        curStage.close();
                        Field.createScenenStage(grid);
                        Field.curStage.show();
                        Field.lossPlayer.stop();
                    }
                });
                MenuItem Normal = new MenuItem("Normal");
                Normal.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        GridPane grid = Main.create(8, 10, 18);
                        curStage.close();
                        Field.createScenenStage(grid);
                        Field.curStage.show();
                        Field.lossPlayer.stop();
                    }
                });
                MenuItem Hard = new MenuItem("Hard");
                Hard.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        GridPane grid = Main.create(9, 9, 18);
                        curStage.close();
                        Field.createScenenStage(grid);
                        Field.curStage.show();
                        Field.lossPlayer.stop();
                    }
                });
                MenuItem Insane = new MenuItem("Insane");
                Insane.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        GridPane grid = Main.create(20, 25, 150);
                        curStage.close();
                        Field.createScenenStage(grid);
                        Field.curStage.show();
                        Field.lossPlayer.stop();
                    }
                });
                nGBtn.getItems().addAll(Easy, Normal, Hard, Insane);
                Image LossImg = new Image(getClass().getResourceAsStream("/lossimg.jpg"));
                ImageView ViewLossImg = new ImageView(LossImg);
                ViewLossImg.setFitHeight(500);
                ViewLossImg.setFitWidth(500);
                LossPic.getChildren().add(ViewLossImg);
                VBox lossroot = new VBox();
                lossroot.setAlignment(Pos.TOP_CENTER);
                Text losstext = new Text("You Died");
                losstext.setFont(Font.font(52));
                lossroot.getChildren().addAll(losstext, nGBtn);
                lossroot.setMinSize(250, 250);
                lossroot.setPrefSize(500, 500);
                LossPic.getChildren().add(lossroot);
                lossPlayer.play();
                Scene scene = new Scene(LossPic);
                curStage.setScene(scene);



            } else if (this.mineCount == 0) {
                try {
                    if (this.fields.fields[row + 1][col + 1].mineCount == -1)
                        this.mineCount++;
                }
                catch (Exception e){}
                try {
                    if (this.fields.fields[row + 1][col].mineCount == -1)
                        this.mineCount++;
                }
                catch (Exception e){}
                try {
                    if (this.fields.fields[row + 1][col - 1].mineCount == -1)
                        this.mineCount++;
                }
                catch (Exception e){}
                try {
                    if (this.fields.fields[row][col + 1].mineCount == -1)
                        this.mineCount++;
                }
                catch (Exception e){}
                try {
                    if (this.fields.fields[row][col - 1].mineCount == -1)
                        this.mineCount++;
                }
                catch (Exception e){}
                try {
                    if (this.fields.fields[row - 1][col + 1].mineCount == -1)
                        this.mineCount++;
                }
                catch (Exception e){}
                try {
                    if (this.fields.fields[row - 1][col].mineCount == -1)
                        this.mineCount++;
                }
                catch (Exception e){}
                try {
                    if (this.fields.fields[row - 1][col - 1].mineCount == -1)
                        this.mineCount++;
                }
                catch (Exception e){}
                if (this.mineCount == 0) {
                    if (row < 1) {
                        if (col < 1) {
                            this.fields.fields[row + 1][col + 1].Open();
                            this.fields.fields[row + 1][col].Open();
                            this.fields.fields[row][col + 1].Open();
                        } else if (col > width - 2) {
                            this.fields.fields[row + 1][col].Open();
                            this.fields.fields[row + 1][col - 1].Open();
                            this.fields.fields[row][col - 1].Open();


                        } else {
                            this.fields.fields[row + 1][col + 1].Open();
                            this.fields.fields[row + 1][col].Open();
                            this.fields.fields[row + 1][col - 1].Open();
                            this.fields.fields[row][col + 1].Open();
                            this.fields.fields[row][col - 1].Open();

                        }
                    } else if (row > height - 2) {
                        if (col < 1) {
                            this.fields.fields[row][col + 1].Open();
                            this.fields.fields[row - 1][col + 1].Open();
                            this.fields.fields[row - 1][col].Open();
                        } else if (col > width - 2) {
                            this.fields.fields[row][col - 1].Open();
                            this.fields.fields[row - 1][col].Open();
                            this.fields.fields[row - 1][col - 1].Open();
                        } else {
                            this.fields.fields[row][col + 1].Open();
                            this.fields.fields[row][col - 1].Open();
                            this.fields.fields[row - 1][col + 1].Open();
                            this.fields.fields[row - 1][col].Open();
                            this.fields.fields[row - 1][col - 1].Open();

                        }
                    } else if (col < 1) {
                        this.fields.fields[row + 1][col + 1].Open();
                        this.fields.fields[row + 1][col].Open();
                        this.fields.fields[row][col + 1].Open();
                        this.fields.fields[row - 1][col + 1].Open();
                        this.fields.fields[row - 1][col].Open();
                    } else if (col > width - 2) {
                        this.fields.fields[row + 1][col].Open();
                        this.fields.fields[row + 1][col - 1].Open();
                        this.fields.fields[row][col - 1].Open();
                        this.fields.fields[row - 1][col].Open();
                        this.fields.fields[row - 1][col - 1].Open();
                    } else {
                        this.fields.fields[row + 1][col + 1].Open();
                        this.fields.fields[row + 1][col].Open();
                        this.fields.fields[row + 1][col - 1].Open();
                        this.fields.fields[row][col + 1].Open();
                        this.fields.fields[row][col - 1].Open();
                        this.fields.fields[row - 1][col + 1].Open();
                        this.fields.fields[row - 1][col].Open();
                        this.fields.fields[row - 1][col - 1].Open();
                    }
                    this.setText(Integer.toString(this.mineCount));
                }
                else {
                this.setText(Integer.toString(this.mineCount));
                }
            }
        }

    }
    @Override
    public void handle(MouseEvent event) {
        if (event.getButton() == MouseButton.SECONDARY){
            if (this.closed && !this.isMarked) {
                this.setText("m");
                this.isMarked = true;
                fields.IncMarked();
                if(fields.GetMarked()+fields.GetOpened() == height*width){
                    vin();
                }
            }
            else if(this.isMarked) {
                this.setText("");
                this.isMarked = false;
                fields.DecMarked();
            }


        }
        else if (event.getButton() == MouseButton.PRIMARY){
            if (!this.isMarked) {
                this.Open();
                if (fields.GetMarked() + fields.GetOpened() == height * width) {
                    vin();
                }
            }


        }

    }
}
