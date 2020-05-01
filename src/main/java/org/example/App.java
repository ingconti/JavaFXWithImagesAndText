package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import java.io.*;
import java.nio.file.Files;




public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //drawShapes(gc);

        drawCards(gc);

        String myText = readMyText();
        drawText(gc, myText);

        readWTFJson("god.json");
        // rendering:

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void drawCards(GraphicsContext gc) {

        Image img =
                new Image("full_0000s_0000_god_and_hero_cards_0056_scylla.png");
        gc.drawImage(img, 20, 20, 100, 100);
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

        InputStream is = null;
        is = this.getClass().getClassLoader().getResourceAsStream("sample_file.txt");
        BufferedReader buf = new BufferedReader(new InputStreamReader(is));

        String line = null;
        try {
            line = buf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();

        while (line != null) {
            sb.append(line).append("\n");
            try {
                line = buf.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String fileAsString = sb.toString();
        System.out.println("Contents : " + fileAsString);

        return fileAsString;
    }



    void readWTFJson(String fname){

        String file = "";
        //was: File tempFile = new File(getClass().getClassLoader().getResource("cards/god.json").getFile());
        File tempFile = new File(getClass().getClassLoader().getResource(fname).getFile());
        try {
            file = new String(Files.readAllBytes(tempFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        Gson gson = new Gson();
        JsonArray jsonArray = gson.fromJson( file, JsonArray.class);

        for(int i = 0 ; i< jsonArray.size() ; i++) {

            //was: godsInGame.add(new Card(jsonArray.get(i).getAsJsonObject().get("name").getAsString(),jsonArray.get(i).getAsJsonObject().get("description").getAsString()));
            JsonObject o = jsonArray.get(i).getAsJsonObject();
            String s1 = o.get("name").getAsString();
            String s2 = o.get("description").getAsString();
            System.out.println(s1 + " : " + s2);

        }


    }



}