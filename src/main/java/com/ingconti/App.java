//https://github.com/ingconti/JavaFXWithImagesAndText



package com.ingconti;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class App extends Application {

    public static void main(String[] args) {

        VeryLongThread veryLongThread = new VeryLongThread(players);
        veryLongThread.start();

        // trivial.. if no cmd param start in graphics.. only to show.
        if (args.length>0){
            System.out.println("CLI\n");
        }else{
            launch(args);
        }
    }

    static List<String> players = new ArrayList<String>();

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //enable it if You want to close background stuff when closing window
        // see long runnign thread. If it runs, appp si alive.
        //this makes all stages close and the app exit when the main stage is closed
        primaryStage.setOnCloseRequest(e -> Platform.exit());

        //drawShapes(gc);
        drawCards(gc);

        String myText = readMyText();
        drawText(gc, myText);

        God[] gods = readMyJSONAsText("gods.json");
        for (God g : gods) {
            System.out.println(g.toString());
        }

        // rendering:
        root.getChildren().add(canvas);

        addButtonTo(root);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void drawCards(GraphicsContext gc) {

        Image img =
                new Image("full_0000s_0000_god_and_hero_cards_0056_scylla.png");
        gc.drawImage(img, 20, 20, 100, 100);
    }



    private  void addButtonTo(Group root){

        //button:
        Button b = new Button("button");
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                System.out.println("BTN! " + new Date());
            }
        };
        // when button is pressed
        b.setOnAction(event);

        // create a stack pane
        TilePane r = new TilePane();
        root.getChildren().add(b);
        /// end of button

    }



    private void drawShapes(GraphicsContext gc) {
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        gc.fillArc(60, 110, 30, 30, 45, 240, ArcType.CHORD);
        gc.fillArc(110, 110, 30, 30, 45, 240, ArcType.ROUND);
        gc.strokeArc(10, 160, 30, 30, 45, 240, ArcType.OPEN);
        gc.strokeArc(60, 160, 30, 30, 45, 240, ArcType.CHORD);
        gc.strokeArc(110, 160, 30, 30, 45, 240, ArcType.ROUND);
        gc.fillPolygon(new double[]{10, 40, 10, 40},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);
        gc.strokePolyline(new double[]{110, 140, 110, 140},
                new double[]{210, 210, 240, 240}, 4);
    }


    void drawText(GraphicsContext gc, String text) {
        gc.strokeText(text, 150, 100);
    }


    String readMyText() {

        String fname = "sample_file.txt";
        String fileAsString = readTextFrom(fname);
        System.out.println("Contents of: "  + fname +  "\n" + fileAsString);

        return fileAsString;
    }





    // NOTE: java cannot read easily mulitple lines..
    // see also at:
    // https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
    // a painful mess of ther same code with for...
    God[] readMyJSONAsText(String fname) {

        String fileAsString = readTextFrom(fname);

        Gson gson = new Gson();
        God[] gods = gson.fromJson( fileAsString, God[].class);

        return gods;
    }


    String readTextFrom(String fname) {

        InputStream is;
        is = this.getClass().getClassLoader().getResourceAsStream(fname);
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        String fileAsString = "";

        String line = null;

        while (true) {
            try {
                line = buf.readLine();
                if (line == null)
                    break;
                fileAsString+=line+"\n";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileAsString;
    }






}