package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionJavaScript;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main extends Application {

    File pdf;
    File js;
    String text;
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        // create a button
        Button b = new Button("Select your pdf file");
        Button b2 = new Button("Select your js file");
        Button b3 = new Button("Perform the injection");
        // create a stack pane
        Label l = new Label("File not selected");
        Label l2 = new Label("File not selected");
        b.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                FileChooser fileChooser = new FileChooser();
                pdf = fileChooser.showOpenDialog(primaryStage);
                l.setText(pdf.getAbsolutePath() + " selected    ");
            }
        });

        b2.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                FileChooser fileChooser = new FileChooser();
                js = fileChooser.showOpenDialog(primaryStage);
                try {
                    text = new String(Files.readAllBytes(Paths.get(js.getAbsolutePath())), StandardCharsets.UTF_8);
                    l2.setText(js.getAbsolutePath() + " selected    " + text);
                }  catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        b3.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                try {
                    File file = new File(pdf.getAbsolutePath());
                    PDDocument document = PDDocument.load(file);
                    PDActionJavaScript javascript = new PDActionJavaScript(text);
                    document.getDocumentCatalog().setOpenAction(javascript);
                    document.save(file);
                    System.out.println("done");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        TilePane r = new TilePane();

        // create a label


        // add button
        r.getChildren().add(b);
        r.getChildren().add(l);
        r.getChildren().add(b2);
        r.getChildren().add(l2);
        r.getChildren().add(b3);

        // create a scene
        Scene sc = new Scene(r, 100, 200);

        // set the scene
        primaryStage.setScene(sc);


        primaryStage.show();
    }


    public static void main(String[] args) {
        File file = new File("C:/Users/Michael/Test.pdf");

        launch(args);
    }
}
