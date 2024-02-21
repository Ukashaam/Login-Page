import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Login();

    }

    public static void Login() {

        JFrame f = new JFrame("Login");
        f.setSize(500, 500);
        f.setLayout(null);

        JLabel title = new JLabel("LOGIN");
        title.setBounds(220, 50, 150, 100);

        JLabel l = new JLabel("Username");
        l.setBounds(150, 150, 100, 30);

        JTextField f1 = new JTextField("");
        f1.setBounds(250, 150, 150, 30);

        JLabel l1 = new JLabel("Password");
        l1.setBounds(150, 200, 100, 30);

        JPasswordField f2 = new JPasswordField("");
        f2.setBounds(250, 200, 150, 30);

        JButton b = new JButton("Login");
        b.setBounds(220, 250, 100, 30);

        JButton b1 = new JButton("Create Account");
        b1.setBounds(200, 300, 150, 30);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MySQLConnection connection = new MySQLConnection();
                Statement statement = connection.connect();
                String sql = "Select * from users";
                ResultSet result;
                try {
                    result = statement.executeQuery(sql);
                    boolean isTrue = false;
                    while (result.next()) {
                        if (f1.getText().equals(result.getString("username")) && f2.getText().equals(result.getString("pass"))) {
                            JOptionPane.showMessageDialog(null, "Login Successfull");
                            Welcome();
                            f.dispose();
                        //    f1.setText(null);
                        //    f2.setText(null);
                            isTrue = true;
                            break;
                        }
                    }
                    if (!isTrue) {
                        JOptionPane.showMessageDialog(null, "No user Exist. Signup First");
                        f1.setText(null);
                        f2.setText(null);
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                signup();
                f.dispose();
            }
        });
        f.add(f1);
        f.add(title);
        f.add(l);
        f.add(l1);
        f.add(f2);
        f.add(b);
        f.add(b1);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void signup() {
        JFrame f = new JFrame("Signup");
        f.setSize(500, 500);
        f.setLayout(null);

        JLabel title = new JLabel("SIGNUP");
        title.setBounds(220, 50, 150, 100);

        JLabel l = new JLabel("Username");
        l.setBounds(150, 150, 100, 30);

        JTextField f1 = new JTextField("");
        f1.setBounds(250, 150, 150, 30);

        JLabel l1 = new JLabel("Password");
        l1.setBounds(150, 200, 100, 30);

        JPasswordField f2 = new JPasswordField("");
        f2.setBounds(250, 200, 150, 30);

        JButton b = new JButton("Signup");
        b.setBounds(220, 250, 100, 30);


        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MySQLConnection connection = new MySQLConnection();
                Statement statement = connection.connect();
                if (f1.getText().equals("") || f2.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Wrong Parameters");
                } else {

                    String name = "\"" + f1.getText() + "\"";
                    String password = "\"" + f2.getText() + "\"";
                    String sql = "INSERT INTO users(username,pass) values(" + name + "," + password + ");";

                    System.out.println(sql);
                    try {
                        statement.execute(sql);
                        JOptionPane.showMessageDialog(null, "Signup Successfull.");
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    Login();
                    f.dispose();

                }
            }
        });
        f.add(f1);
        f.add(title);
        f.add(l);
        f.add(l1);
        f.add(f2);
        f.add(b);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void Welcome() {
        JFrame f = new JFrame("Home");
        f.setSize(500, 500);
        f.setLayout(null);

        JLabel title = new JLabel("Welcome");
        title.setBounds(200, 150, 150, 100);
        f.add(title);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}