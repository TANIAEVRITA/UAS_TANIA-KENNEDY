import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class Register extends JFrame {
    private JTextField inpnama = new JTextField();
    private JTextField intlp = new JTextField();
    private JTextField inalamat = new JTextField();
    private JTextField inemail = new JTextField();
    private JTextField inpas = new JTextField();
    private JButton btnDaftar = new JButton("DAFTAR");
    private JButton btnCancel = new JButton("CANCEL");

    String gender[] ={"Laki-Laki","Perempuan"};
    private JComboBox ingender = new JComboBox(gender);

    public Register(){
        new Database();
        setTitle("REGISTER");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel labjudul = new JLabel("REGISTER");
        JLabel labnama = new JLabel("Nama");
        JLabel labtlp = new JLabel("No. Telepon");
        JLabel labalamat = new JLabel("Alamat");
        JLabel labgender = new JLabel("Kelamin");
        JLabel labemail = new JLabel("Email");
        JLabel labpass = new JLabel("Password");

        Font font1 = new Font("Arial", Font.BOLD,38);
        Font font2 = new Font("Arial", Font.BOLD,16);

        labjudul.setBounds(100,20,300,30);
        labjudul.setFont(font1);
        labnama.setBounds(45,80,300,30);
        labnama.setFont(font2);
        labtlp.setBounds(45,130,300,30);
        labtlp.setFont(font2);
        labalamat.setBounds(45,180,300,30);
        labalamat.setFont(font2);
        labgender.setBounds(45,230,300,30);
        labgender.setFont(font2);
        labemail.setBounds(45,280,300,30);
        labemail.setFont(font2);
        labpass.setBounds(45,330,300,30);
        labpass.setFont(font2);

        inpnama.setBounds(165,84,170,25);
        intlp.setBounds(165,134,170,25);
        inalamat.setBounds(165,184,170,25);
        ingender.setBounds(165,234,170,25);
        inemail.setBounds(165,284,170,25);
        inpas.setBounds(165,334,170,25);

        btnDaftar.setBounds(245,380,90,25);
        btnDaftar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regis();
            }
        });

        btnCancel.setBounds(130,380,90,25);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                dispose();
            }
        });
        add(labjudul);
        add(labnama);
        add(labalamat);
        add(labemail);
        add(labgender);
        add(labpass);
        add(labtlp);
        add(inalamat);
        add(inemail);
        add(inpas);
        add(ingender);
        add(inpnama);
        add(intlp);
        add(btnDaftar);
        add(btnCancel);
        setSize(400,480);
        setLayout(null);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    public void regis(){
        String nama = inpnama.getText();
        int noTelp = Integer.parseInt(intlp.getText());
        String alamat = inalamat.getText();
        String kelamin = ingender.getSelectedItem().toString();
        String email = inemail.getText();
        String password = inpas.getText();

        try{
            String sql = "INSERT INTO akun(nama,noTelp,alamat,kelamin,email,password) VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = Database.koneksi.prepareStatement(sql);
            ps.setString(1,nama);
            ps.setInt(2,noTelp);
            ps.setString(3,alamat);
            ps.setString(4,kelamin);
            ps.setString(5,email);
            ps.setString(6,password);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Berhasil Daftar");
            new Login();
            dispose();
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Gagal Daftar");
        }
    }
}
