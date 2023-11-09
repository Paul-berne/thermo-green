package view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import control.Controller;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {

    private Controller myController;
    private JPanel contentPane;
    private JTextField txtLogin;
    private JTextField txtPwd;

    public Login(Controller unController) {
        this.myController = unController;

        setResizable(false);
        setTitle("Vinci Thermo Green");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 278, 155);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblLogin = new JLabel("Login : ");
        lblLogin.setBounds(10, 11, 46, 14);
        contentPane.add(lblLogin);

        JLabel lblPwd = new JLabel("Password :");
        lblPwd.setBounds(10, 36, 62, 14);
        contentPane.add(lblPwd);

        txtLogin = new JTextField();
        txtLogin.setBounds(87, 11, 86, 20);
        contentPane.add(txtLogin);
        txtLogin.setColumns(10);

        txtPwd = new JPasswordField();
        txtPwd.setColumns(10);
        txtPwd.setBounds(87, 36, 86, 20);
        contentPane.add(txtPwd);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(23, 82, 89, 23);
        contentPane.add(btnLogin);
        
        JButton btnChangePassword = new JButton("change password");
        btnChangePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomUtilisateur = txtLogin.getText();
                String motDePasse = txtPwd.getText();

                if (unController.verifyUserLogin(nomUtilisateur, motDePasse)) {
                    myController.CreateFrameChangePassword(nomUtilisateur);
                    dispose(); // Ferme la fenêtre de login
                } else {
                    JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect.");
                }
            }
        });
        btnChangePassword.setBounds(134, 82, 118, 23);
        contentPane.add(btnChangePassword);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomUtilisateur = txtLogin.getText();
                String motDePasse = txtPwd.getText();

                if (unController.verifyUserLogin(nomUtilisateur, motDePasse)) {
                    myController.CreateConsoleGUI();
                    dispose(); // Ferme la fenêtre de login
                } else {
                    JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect.");
                }
            }
        });

    }
}