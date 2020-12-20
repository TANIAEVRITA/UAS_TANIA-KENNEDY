import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.Statement;

public class Login extends JFrame {
    JPanel p = new JPanel();
    JLabel judul = new JLabel("LOGIN");
    JLabel email = new JLabel("E-Mail");
    JLabel password = new JLabel("Password");
    JLabel create = new JLabel("Don't Have a Account ? Register Click Here");
    JTextField emailTextField = new JTextField();
    JPasswordField passwordTextField = new JPasswordField();
    JButton btnLogin = new JButton("Login");

    public Login(){
        new Database();
        setTitle("Login");
        setSize(350,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        p.setLayout(null);

        judul.setBounds(40,25,100,30);
        judul.setFont(new Font("Arial Black",Font.PLAIN,20));
        email.setBounds(40,70,100,30);
        password.setBounds(40,115,100,30);
        create.setBounds(40,210,250,30);
        create.setFont(new Font("Calibri",Font.ITALIC,13));
        create.setForeground(Color.BLUE);

        emailTextField.setBounds(130,75,160,25);
        passwordTextField.setBounds(130,120,160,25);

        btnLogin.setBounds(180,160,110,30);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordTextField.setEchoChar((char)0);
                String pass = String.valueOf(passwordTextField.getPassword());
                try{
                    String sql = "SELECT email,password FROM akun WHERE email='"+emailTextField.getText()+"' AND password='"+pass+"'";
                    Statement st = Database.koneksi.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    if(rs.next()){
                        if(emailTextField.getText().equals(rs.getString("email")) && pass.equals(rs.getString("password"))){
                            JOptionPane.showMessageDialog(null, "berhasil login");
                            new DataEntry();
                            dispose();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "username atau password salah");
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        create.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Register();
                dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                create.setText("<html><u>Don't Have a Account ? Register Click Here</u></html>");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                create.setText("Don't Have a Account ? Register Click Here");
            }
        });

        p.add(judul);
        p.add(email);
        p.add(password);
        p.add(create);
        p.add(emailTextField);
        p.add(passwordTextField);
        p.add(btnLogin);

        add(p);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
