package ie.gmit.mypackage;

import java.io.File;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application implements Serializable {

    private static final long serialVersionUID = 1L;
    PhoneManager pm = new PhoneManager(); 

    @Override
    public void start(Stage primaryStage) {
        // Create TextArea node for bottom of scene 1
        TextArea taMyOutput = new TextArea();
        taMyOutput.setPrefHeight(100); // sets height of the TextArea to 400 pixels
        taMyOutput.setPrefWidth(100); // sets width of the TextArea to 300 pixels

        // Show total number of phones
        Button btnShowTotal = new Button("Show Phone");
        TextField tfTotalNumberOfPhones = new TextField();
        
        tfTotalNumberOfPhones.setPromptText("0");
        tfTotalNumberOfPhones.setEditable(false); // This box is not editable. Only displays result.
      
        btnShowTotal.setOnAction(e -> {
            tfTotalNumberOfPhones.setText("hELLO");
        });

        // Add Phone

        Button btnAddPhone = new Button("Add Phone");
        TextField tfPhoneID = new TextField();
        TextField tfPhoneModel = new TextField();
        TextField tfPhonePrice = new TextField();

        tfPhoneID.setPromptText("PhoneID");
        tfPhoneModel.setPromptText("Phone Model");
        tfPhonePrice.setPromptText("Phone Price");


        btnAddPhone.setOnAction(e -> {
            if (tfPhoneID.getText().trim().equals("")) { // If text field is empty           
                taMyOutput.setText("Invalid");
            } else {
                Phone phone = new Phone(tfPhoneID.getText(), tfPhoneModel.getText(), tfPhonePrice.getText());
                pm.addPhone(phone); // Add phone to list
                tfPhoneID.clear();
                tfPhoneModel.clear();
                tfPhonePrice.clear();               
            }
        });

        // Delete Phone
        TextField tfPhoneDel = new TextField();
        Button btnDelPhone = new Button("Delete Phone");

        tfPhoneDel.setPromptText("Enter Phone ID");

        btnDelPhone.setOnAction(e -> {

            pm.deletePhoneById(tfPhoneDel.getText());

        });

        // Search for phone by ID
        TextField tfPhoneSearch = new TextField();
        Button btnPhoneSearch = new Button("Search Phone By ID");
       
        tfPhoneSearch.setPromptText("Search Phone ID");
        btnPhoneSearch.setOnAction(e -> {

            Phone phoneObject = pm.searchForPhoneById(tfPhoneSearch.getText());
            taMyOutput.setText(phoneObject.getPhoneModel() + " " + phoneObject.getPhonePrice());

        });



        //Save to DB
        Button btnSaveDB = new Button ("Save Phone to DB");
        btnSaveDB.setOnAction(e -> {
        
            if (pm.findTotalPhones() > 0) {
                try {
                    File phoneDB = new File("./resources/phoneDB.ser");
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(phoneDB));
                    out.writeObject(pm);
                    out.close();
                    taMyOutput.setText("Phone Database Saved");
                } catch (Exception exception) {
                    System.out.print("[Error] Cannont save DB. Cause: ");
                    exception.printStackTrace();
                    taMyOutput.setText("ERROR: Failed to save Phones DB!");
                }
            } else {
                taMyOutput.setText("No Phones in List to save!");
            }
        });

        // Load from DB
     Button btnLoadDB = new Button("Load Phones to DB");
     TextField tfLoadPhones = new TextField();
     tfLoadPhones.setPromptText("Enter Path to DB");

       btnLoadDB.setOnAction(e -> {

       try {
              File phoneDB = new File("./resources/phoneDB.ser");
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(phoneDB));
                 pm = (PhoneManager) in.readObject();
                    in.close();
                    taMyOutput.setText("Phone Database Loaded");

                   } catch (Exception exception) {
                    System.out.print("[Error] Cannont save DB. Cause: ");
                    exception.printStackTrace();
                    taMyOutput.setText("ERROR: Failed to save Phone DB!");

       }
        });

        //Add Quit Button
         Button btnQuit= new Button("Quit");
         btnQuit.setOnAction(e -> {
             Platform.exit();

         });


    

        // Adding and arranging all the nodes in the grid - add(node, column, row)
        GridPane gridPane1 = new GridPane();
        gridPane1.add(tfPhoneID, 0, 0);
        gridPane1.add(tfPhoneModel, 1, 0);
        gridPane1.add(tfPhonePrice, 2, 0);
        gridPane1.add(btnAddPhone, 3, 0);   
        gridPane1.add(btnShowTotal, 0, 1);
        gridPane1.add(tfPhoneModel, 1, 1);
        gridPane1.add(tfPhoneDel, 0, 2);
        gridPane1.add(btnDelPhone, 1, 2);
        gridPane1.add(tfPhoneSearch, 0, 3);
        gridPane1.add(btnPhoneSearch, 1, 3);
        gridPane1.add(btnSaveDB, 0, 4);
        gridPane1.add(btnLoadDB, 0, 5);
		gridPane1.add(tfLoadPhones, 1, 5);
        gridPane1.add(taMyOutput, 0, 6, 2, 1);
        gridPane1.add(btnQuit, 0, 7);
       

       
        Scene scene1 = new Scene(gridPane1, 850, 550);
        

        if (getParameters().getRaw().size() == 0) {
            primaryStage.setTitle("Phone Manager Application");
        } else {
            primaryStage.setTitle(getParameters().getRaw().get(0));
        }

        // Setting the scene to Stage
        primaryStage.setScene(scene1);
        // Displaying the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
