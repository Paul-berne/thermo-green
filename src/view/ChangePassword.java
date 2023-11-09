package view;

import org.passay.*;

import DAO.DataFileUser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import control.Controller;

public class ChangePassword extends JFrame {

    private Controller myController;
    private JPanel contentPane;
    private JPasswordField txtPwd;
    private JPasswordField txtConfirmPwd;
    private JLabel lblPasswordCheck;
    private DataFileUser leDAOUser;

    public ChangePassword(Controller unController, String login) {
        this.myController = unController;
        this.leDAOUser = new DataFileUser(myController);
        
        setResizable(false);
        setTitle("Vinci Thermo Green");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 320, 185);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLogin = new JLabel("New password :");
        lblLogin.setBounds(10, 11, 100, 14);
        contentPane.add(lblLogin);

        JLabel lblPwd = new JLabel("Confirm password :");
        lblPwd.setBounds(10, 36, 100, 14);
        contentPane.add(lblPwd);

        txtPwd = new JPasswordField();
        txtPwd.setBounds(108, 8, 86, 20);
        contentPane.add(txtPwd);

        txtConfirmPwd = new JPasswordField();
        txtConfirmPwd.setBounds(108, 36, 86, 20);
        contentPane.add(txtConfirmPwd);

        lblPasswordCheck = new JLabel("");
        lblPasswordCheck.setForeground(Color.RED);
        lblPasswordCheck.setBounds(10, 83, 280, 20);
        contentPane.add(lblPasswordCheck);

        JButton btnChangePassword = new JButton("Change password");
        btnChangePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                char[] motDePasse = txtPwd.getPassword();
                String lemotDePasse = new String(motDePasse);
                
                char[] confirmationMotDePasse = txtConfirmPwd.getPassword();                
                // Vérification des règles de mot de passe avec Passay
                PasswordValidator validator = new PasswordValidator(
                    new LengthRule(8, 30),
                    new CharacterRule(EnglishCharacterData.UpperCase, 1),
                    new CharacterRule(EnglishCharacterData.LowerCase, 1),
                    new CharacterRule(EnglishCharacterData.Digit, 1),
                    new CharacterRule(EnglishCharacterData.Special, 1),
                    new WhitespaceRule()
                );

                RuleResult result = validator.validate(new PasswordData(new String(motDePasse)));

                if (motDePasse.length == 0) {
                    lblPasswordCheck.setText("Password cannot be empty.");
                } else if (!Arrays.equals(motDePasse, confirmationMotDePasse)) {
                    lblPasswordCheck.setText("Passwords do not match.");
                } else if (!result.isValid()) {
                    lblPasswordCheck.setText("Password does not meet security rules.");
                } else {
                	System.out.println(login);
                	System.out.println(lemotDePasse);
                    lblPasswordCheck.setText("");
                    leDAOUser.ChangePasswordUser(login, lemotDePasse);
                    myController.CreateConsoleGUI();
                    dispose();
                }
            }
        });
        btnChangePassword.setBounds(10, 110, 220, 23);
        contentPane.add(btnChangePassword);
    }
}
