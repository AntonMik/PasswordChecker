package password;

//----------  ----------  ----------
//
//PLEASE READ | From Prof. Thai
//
//This code is incomplete!  It's provided as a starting point only.  Each student is welcome to implement their own UI
//
//----------  ----------  ----------


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.text.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;


/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Anton Mikhaylenko
 *
 */

public class PasswordMain extends BorderPane
{
	private Label passwordLabel, passwordALabel, instruction1Label, instruction2Label,instruction3Label,instruction4Label;
	private Label instruction5Label, instruction6Label;
	
	private TextField passwordText, passwordAText ;
	private TextField passwordText0, passwordAText0 ;
	private ArrayList<String> illegalPasswords;
	private Button checkPwdButton, exitButton, checkPwdsInFileButton;
	DecimalFormat format = new DecimalFormat("#0.000");
	
	PasswordCheckerUtility pwdChecker = new PasswordCheckerUtility();
	Alert alert = new Alert(AlertType.INFORMATION);
	Tooltip tooltipPass = new Tooltip();
	Tooltip tooltipPass2 = new Tooltip();
	Tooltip tooltipCheckPass = new Tooltip();
	Tooltip tooltipCheckFile = new Tooltip();
	Tooltip tooltipExit = new Tooltip();
	
	public PasswordMain()
	{
		pwdChecker = new PasswordCheckerUtility();
		illegalPasswords = new ArrayList<String>();
		
		VBox subpanel = new VBox();
		instruction1Label = new Label("Use the following rules when creating your passwords:");
		instruction2Label = new Label("\t1.  Length must be greater than 6; a strong password will contain at least 10 characters");
		instruction3Label = new Label("\t2.  Must contain at least one upper case alpha character");
		instruction4Label = new Label("\t3.  Must contain at least one lower case alpha character");
		instruction5Label = new Label("\t4.  Must contain at least one numeric charcter");
		instruction6Label = new Label("\t5.  May not have more than 2 of the same character in sequence");
		VBox.setMargin(instruction1Label, new Insets(2,10,2,10));
		VBox.setMargin(instruction2Label, new Insets(2,10,2,10));
		VBox.setMargin(instruction3Label, new Insets(2,10,2,10));
		VBox.setMargin(instruction4Label, new Insets(2,10,2,10));
		VBox.setMargin(instruction5Label, new Insets(2,10,2,10));
		VBox.setMargin(instruction6Label, new Insets(2,10,2,10));

		subpanel.setAlignment(Pos.CENTER_LEFT);
		subpanel.getChildren().addAll(instruction1Label, instruction2Label, instruction3Label,
				instruction4Label, instruction5Label, instruction6Label);
		
		HBox subpanel1a = new HBox();
		passwordLabel = new Label ("Password");
		
		passwordText = new TextField();
		passwordText0 = new TextField();
		HBox.setMargin(passwordLabel, new Insets(10,10,10,10));
		HBox.setMargin(passwordText, new Insets(10,10,10,10));
		subpanel1a.setAlignment(Pos.CENTER);
		subpanel1a.getChildren().addAll(passwordLabel, passwordText);
		
		HBox subpanel1b = new HBox();
		passwordALabel = new Label ("Re-type\nPassword");
		
		passwordAText = new TextField();
		passwordAText0 = new TextField();
		HBox.setMargin(passwordALabel, new Insets(10,10,10,10));
		HBox.setMargin(passwordAText, new Insets(10,10,10,10));
		subpanel1b.setAlignment(Pos.CENTER);
		subpanel1b.getChildren().addAll(passwordALabel, passwordAText);
		
		VBox subpanel1 = new VBox();
		VBox.setMargin(subpanel1a, new Insets(10,10,10,10));
		VBox.setMargin(subpanel1b, new Insets(10,10,10,10));
		subpanel1.setAlignment(Pos.CENTER);
		subpanel1.getChildren().addAll(subpanel1a, subpanel1b);
		
				
		checkPwdsInFileButton = new Button("Check Passwords in _File");
		checkPwdsInFileButton.setOnAction(
     		event -> {
     			try {
						readFile();
					} catch (Exception e) {
						e.printStackTrace();
					}
     		});
		
		checkPwdButton = new Button ("Check _Password");
		checkPwdButton.setOnAction(
     		event -> {
     			try {
					addPassword();
				} catch (UnmatchedExcpetion e) {
					
					e.printStackTrace();
				}
     		});
		
		exitButton = new Button("E_xit");
		
	    //use a lambda expression for the EventHandler class for exitButton
	    exitButton.setOnAction(
     		event -> {
	            	 Platform.exit();
	                 System.exit(0);
     		}
     	);
		 
	
		HBox buttonPanel = new HBox();
		HBox.setMargin(checkPwdButton, new Insets(10,10,10,10));
		HBox.setMargin(checkPwdsInFileButton, new Insets(10,10,10,10));
		HBox.setMargin(exitButton, new Insets(10,10,10,10));
		buttonPanel.setAlignment(Pos.CENTER);
		buttonPanel.getChildren().addAll(checkPwdButton, checkPwdsInFileButton, exitButton);

		setTop(subpanel);
		setCenter(subpanel1);
		setBottom(buttonPanel);
		
		
		tooltipPass.setText("Password");
		tooltipPass2.setText("Repeat password");
		tooltipCheckPass.setText("Check Passwords");
		tooltipCheckFile.setText("Select File");
		tooltipExit.setText("Exit");
		
		passwordText.setTooltip(tooltipPass);
		passwordAText.setTooltip(tooltipPass2);
		checkPwdsInFileButton.setTooltip(tooltipCheckFile);
		checkPwdButton.setTooltip(tooltipCheckPass);
		exitButton.setTooltip(tooltipExit);
	
	}

	public void addPassword() throws UnmatchedExcpetion {
		//Get information
		
		alert.setTitle("Password Status");
		
		
		if (passwordText.getText().equals(passwordAText.getText())) {
			
			try {
			if (pwdChecker.isValidPassword(passwordText.getText())) {
				alert.setContentText("The passwords pass!");
				alert.showAndWait();
			}
			}
			catch (LengthException e) {
				
				alert.setContentText("The password must be at least 6 character long");
				alert.showAndWait();
			}
			catch (NoDigitException e) {
			
				alert.setContentText("The password must contain at least one digit");
				alert.showAndWait();
			}
			catch (NoUpperAlphaException e) {
				alert.setContentText("The password must contain at least one uppercase alphabetic character");
				alert.showAndWait();
			}
			catch (NoLowerAlphaException e) {
				alert.setContentText("The password must contain at least one lowercase alphabetic character");
				alert.showAndWait();
			}
			catch (InvalidSequenceException e) {
				alert.setContentText("The password cannot contain more than two of the same character in sequence.");
				alert.showAndWait();
			}
			
	}
		
		else {
			alert.setContentText("The password do not match.");
			alert.showAndWait();
		}
	}
	//Listens for the Display Apps button to be pressed
	private class displayIllegalPasswords implements ActionListener
	{
		public void actionPerformed (ActionEvent theEvent)
		{
			
		}
	}

	public void readFile() {
			FileChooser chooser = new FileChooser();
			BufferedReader reader = null;
			ArrayList<String> passwords = new ArrayList();
			String temp = "";
			
			chooser.setTitle("Open Password File");
			try {
			File file = chooser.showOpenDialog(null);
			if (file != null) {
				reader = new BufferedReader(new FileReader(file));
			}
			
			while ((temp = reader.readLine()) != null) {
				passwords.add(temp);
				
			}
				
			}
			catch (Exception e) {
				alert.setContentText("File could not be read!");
				alert.showAndWait();
			}
			passwords = pwdChecker.validPasswords(passwords);
			
			String output = "";
			for (int i = 0; i < passwords.size(); i++) {
				
				output += passwords.get(i) + "\n";
				
			}
				alert.setContentText(output);
				alert.showAndWait();
			
			
			
			
	}
}
