/**
 * The Login class represents the graphical user interface for the login functionality
 * of the Vinci Thermo Green application.
 * It extends the JFrame class and includes fields for entering a username and password,
 * as well as buttons for logging in and changing the password.
 *
 * @author [Your Name]
 * @version [Version Number]
 * @see javax.swing.JFrame
 * @see javax.swing.JPanel
 * @see javax.swing.JPasswordField
 * @see javax.swing.JLabel
 * @see javax.swing.JTextField
 * @see javax.swing.JButton
 * @see java.awt.event.ActionEvent
 * @see java.awt.event.ActionListener
 */
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

    // Controller instance to handle interactions
    private Controller myController;
    private JPanel contentPane;
    private JTextField txtLogin;
    private JTextField txtPwd;

    /**
     * Constructs a new Login instance.
     *
     * @param unController The Controller instance for handling interactions.
     */
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

        // Text field for entering the username
        txtLogin = new JTextField();
        txtLogin.setBounds(87, 11, 86, 20);
        contentPane.add(txtLogin);
        txtLogin.setColumns(10);

        // Password field for entering the password
        txtPwd = new JPasswordField();
        txtPwd.setColumns(10);
        txtPwd.setBounds(87, 36, 86, 20);
        contentPane.add(txtPwd);

        // Button for normal login
        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(23, 82, 89, 23);
        contentPane.add(btnLogin);

        // Button for changing the password
        JButton btnChangePassword = new JButton("Change Password");
        btnChangePassword.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomUtilisateur = txtLogin.getText();
                String motDePasse = txtPwd.getText();

                if (unController.verifyUserLogin(nomUtilisateur, motDePasse)) {
                    myController.CreateFrameChangePassword(nomUtilisateur);
                    dispose(); // Closes the login window
                } else {
                    JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect.");
                }
            }
        });
        btnChangePassword.setBounds(134, 82, 118, 23);
        contentPane.add(btnChangePassword);

        // Action listener for the normal login button
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomUtilisateur = txtLogin.getText();
                String motDePasse = txtPwd.getText();

                if (unController.verifyUserLogin(nomUtilisateur, motDePasse)) {
                    myController.CreateConsoleGUI();
                    dispose(); // Closes the login window
                } else {
                    JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect.");
                }
            }
        });
    }
}
