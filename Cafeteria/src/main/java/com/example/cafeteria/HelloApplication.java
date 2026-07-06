package com.example.cafeteria;

import java.io.IOException;
/*    */ import javafx.application.Application;
/*    */ import javafx.fxml.FXMLLoader;
/*    */ import javafx.scene.Parent;
/*    */ import javafx.scene.Scene;
/*    */ import javafx.stage.Stage;

 public class HelloApplication
   extends Application {
     @Override
     public void start(Stage stage) {
         try {
             FXMLLoader fxmlLoader = new FXMLLoader(
                     HelloApplication.class.getResource("crud.fxml")
             );

             Scene scene = new Scene(fxmlLoader.load(), 820, 640);

             stage.setTitle("Cafeteria");
             stage.setScene(scene);
             stage.show();

         } catch (Exception e) {
             e.printStackTrace();

             Stage errorStage = new Stage();
             javafx.scene.control.Label lbl =
                     new javafx.scene.control.Label("Error al iniciar la app:\n" + e.getMessage());

             errorStage.setScene(new Scene(new javafx.scene.layout.StackPane(lbl), 500, 200));
             errorStage.show();
         }
     }
     public static void main(String[] args) {
         launch(args);
     }
 }


