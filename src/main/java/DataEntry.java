import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.util.Hashtable;

public class DataEntry extends JFrame {

    Container con = getContentPane();

    JLabel title = new JLabel("Entry Villa");
    JLabel no = new JLabel("No Villa");
    JLabel lokasi = new JLabel("Lokasi Villa");
    JLabel tipe = new JLabel("Tipe Villa");
    JLabel harga = new JLabel("Harga Villa");
    JLabel kapasitas = new JLabel("Kapasitas");
    JLabel fasilitas = new JLabel("Fasilitas");

    String Lokasi[]={"Pilih Lokasi","Bandung", "Bali", "Lombok"};
    String Tipe[]={"Pilih Tipe","Standard", "Deluxe"};

    JComboBox comboBoxLokasi = new JComboBox(Lokasi);
    JComboBox comboBoxTipe = new JComboBox(Tipe);

    JTextField textFieldNomor = new JTextField();
    JTextField textFieldHarga = new JTextField();

    JSpinner spinnerKapasitas = new JSpinner();

    JTextArea textAreaFasilitas = new JTextArea();


    JButton save = new JButton("Save");
    JButton cancel = new JButton("Cancel");
    JButton checkin = new JButton("Check In");

    public DataEntry() {
        setTitle("Entry Villa");
        setSize(350, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        con.setLayout(null);

        title.setBounds(120,5,320,30);
        title.setFont(new Font("Plasma",Font.BOLD,20));

        no.setBounds(10,50,400,30);
        no.setFont(new Font("Plasma",Font.BOLD,14));
        textFieldNomor.setBounds(140,50,180,30);

        lokasi.setBounds(10,90,400,30);
        lokasi.setFont(new Font("Plasma",Font.BOLD,14));
        comboBoxLokasi.setBounds(140,90,180,30);

        tipe.setBounds(10,130,400,30);
        tipe.setFont(new Font("Plasma",Font.BOLD,14));
        comboBoxTipe.setBounds(140,130,180,30);

        harga.setBounds(10,170,400,30);
        harga.setFont(new Font("Plasma",Font.BOLD,14));
        textFieldHarga.setBounds(140,170,180,30);

        kapasitas.setBounds(10,210,400,30);
        kapasitas.setFont(new Font("Plasma",Font.BOLD,14));
        spinnerKapasitas.setBounds(140,210,40,30);

        fasilitas.setBounds(10,250,400,30);
        fasilitas.setFont(new Font("Plasma",Font.BOLD,14));
        textAreaFasilitas.setBounds(140,250,180,50);
        textAreaFasilitas.setLineWrap(true);

        save.setBounds(240,320,80,25);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertdata();
            }
        });

        checkin.setBounds(30,320,90,25);
        checkin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Check_In();
                dispose();
            }
        });

        cancel.setBounds(140,320,80,25);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
                dispose();
            }
        });

        add(title);
        add(no);
        add(lokasi);
        add(tipe);
        add(harga);
        add(kapasitas);
        add(fasilitas);

        add(textFieldNomor);
        add(textFieldHarga);
        add(textAreaFasilitas);

        add(comboBoxTipe);
        add(comboBoxLokasi);
        add(spinnerKapasitas);

        add(cancel);
        add(save);
        add(checkin);
        comboBoxTipe.addItemListener(new Listener());

        setVisible(true);

    }

    class Listener implements ItemListener {
        public void itemStateChanged(ItemEvent e) {
            String items = (String) comboBoxTipe.getSelectedItem();

            if ("Standard".equals(items)) {
                textFieldHarga.setText("Rp8.000.000/Malam");
            }
            else if ("Deluxe".equals(items)) {
                textFieldHarga.setText("Rp30.000.000/Malam");
            }else{
                textFieldHarga.setText("");
            }
        }
    }

    public void insertdata(){
        String nomor = textFieldNomor.getText();
        String lokasi = comboBoxLokasi.getSelectedItem().toString();
        String tipe = comboBoxTipe.getSelectedItem().toString();
        String harga = textFieldHarga.getText();
        int kapasitas = Integer.parseInt(spinnerKapasitas.getValue().toString());
        String fasilitas = textAreaFasilitas.getText();

        try{
            String sql = "INSERT INTO entryVilla VALUES(?,?,?,?,?,?)";
            PreparedStatement ps = Database.koneksi.prepareStatement(sql);
            ps.setString(1,nomor);
            ps.setString(2,lokasi);
            ps.setString(3,tipe);
            ps.setString(4,harga);
            ps.setInt(5,kapasitas);
            ps.setString(6,fasilitas);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Berhasil Simpan Data");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Gagal Simpan Data");
        }
    }

}
