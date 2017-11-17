package tp;

import java.beans.EventHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.control.Button;

public class Main extends Application {

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
    	String competences = getAllCompetence();
        primaryStage.setTitle("TP Java FX");
        Label labelle = new Label();
        labelle.setText(competences);
        
        StackPane layout = new StackPane();
        layout.getChildren().add(labelle);

        Scene scene = new Scene(layout,300,300);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }


    public String getAllCompetence(){
    	String result = "";
        try {

            URL url = new URL("http://localhost:8080/competences");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            result = br.readLine();
            conn.disconnect();

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return result;
    }
}
