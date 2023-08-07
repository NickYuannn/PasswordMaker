package sample;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.*;

import java.util.Random;

public class passwordMaker {
    public String alphabet = "abcdefghijklmnopqrstuvwxyz";
    public String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public String numbers = "1234567890";
    public String specialCharacters = "!@#$%^&*()-_+=`~,<.>/?;:'{[}]";
    public String characterBank = "";
    public int length;

    Random random = new Random();

    passwordMaker(Stage stage){
        GridPane root  = new GridPane();
        Scene scene = new Scene(root, 500,300);

        //nodes
        CheckBox specialCB = new CheckBox("Special Characters");
        CheckBox numbersCB = new CheckBox("Numbers");
        CheckBox upperCaseCB = new CheckBox("Uppercase Letters");
        Button generate = new Button("Generate Password");
        Label lengthLabel = new Label("Enter a number for the length of the password");
        Label impossible = new Label("Impossible to create a password with \n this length and requirements");
        TextField lengthField = new TextField();
        TextArea passwordTextArea = new TextArea();
        Label notNum = new Label("Invalid length, enter a number");

        //customizations
        lengthField.setMaxWidth(50);
        passwordTextArea.setMaxSize(300,20);
        notNum.setVisible(false);
        impossible.setVisible(false);


        //add to gridpane
        root.add(specialCB, 0,0);
        root.add(numbersCB,0,1);
        root.add(upperCaseCB, 0, 2);
        root.add(lengthLabel,0,3);
        root.add(notNum, 1,4);
        root.add(lengthField,0,4);
        root.add(generate, 0,5);
        root.add(passwordTextArea, 0, 6);
        root.add(impossible, 1,6);

        generate.setOnAction(event -> {
            passwordTextArea.setText("");
            boolean numberCheck = true;
            notNum.setVisible(false);
            impossible.setVisible(false);
            for (int i = 0; i < lengthField.getText().length();i++)
            {
                if (!Character.isDigit(lengthField.getText().charAt(i))){
                    numberCheck = false;
                    break;
                }
            }
            System.out.println("number check is " + numberCheck + " and the lengthField is " + lengthField.getText());
            if (numberCheck){
                length = Integer.parseInt(lengthField.getText());
                int requirements = 0;
                if (numbersCB.isSelected())
                {
                    requirements++;
                }
                if (specialCB.isSelected())
                {
                    requirements++;
                }
                if (upperCaseCB.isSelected())
                {
                    requirements++;
                }
                if (length < requirements)
                {
                    impossible.setVisible(true);
                }
                else {
                    int met = 0;
                    String possiblePassword = generatePassword(specialCB, numbersCB, upperCaseCB) ;

                    while(met != requirements)
                    {
                        met = 0;
                        possiblePassword = generatePassword(specialCB, numbersCB, upperCaseCB);
                        System.out.println(possiblePassword);
                        System.out.println("met is " + met + " and req is " + requirements);
                        if (numbersCB.isSelected())
                        {
                            for (int i =0; i < possiblePassword.length();i++)
                            {
                                for (int j = 0; j < numbers.length(); j++)
                                {
                                    if (possiblePassword.charAt(i) == numbers.charAt(j) ){
                                        met++;
                                        i = possiblePassword.length();
                                        break;
                                    }
                                }
                            }
                        }
                        if (specialCB.isSelected())
                        {
                            for (int i =0; i < possiblePassword.length();i++)
                            {
                                for (int j = 0; j < specialCharacters.length(); j++)
                                {
                                    if (possiblePassword.charAt(i) == specialCharacters.charAt(j) ){
                                        met++;
                                        i = possiblePassword.length();
                                        break;
                                    }
                                }
                            }
                        }
                        if (upperCaseCB.isSelected())
                        {
                            for (int i =0; i < possiblePassword.length();i++)
                            {
                                for (int j = 0; j < uppercase.length(); j++)
                                {
                                    if (possiblePassword.charAt(i) == uppercase.charAt(j) ){
                                        met++;
                                        i = possiblePassword.length();
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    passwordTextArea.setText(possiblePassword);
                }

            }
            else if (!numberCheck){
                notNum.setVisible(true);
            }
        });


        stage.setScene(scene);
        stage.show();
    }

    public String generatePassword(CheckBox spCB, CheckBox numCB, CheckBox upCB){
        String password = "";
        characterBank = "";
        characterBank += alphabet;
        if (spCB.isSelected()){
            characterBank += specialCharacters;
        }
        if (numCB.isSelected()){
            characterBank += numbers;
        }
        if (upCB.isSelected()){
            characterBank += uppercase;
        }
        for (int i = 0; i < length; i++)
        {
            password += characterBank.charAt(random.nextInt(characterBank.length()));
        }
        return password;
    }
}
