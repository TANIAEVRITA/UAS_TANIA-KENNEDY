import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Check_In extends JFrame {
    private JSpinner jumtmu = new JSpinner();
    private JTextField date = new JTextField();
    private JTextField inpnama = new JTextField();
    private JTextField lamainap = new JTextField();
    private JTextField inbill = new JTextField();
    private JButton btninsert = new JButton("Insert");
    private JButton btncheckin = new JButton("Check Out");
    private JButton btnBack = new JButton("Back");

    String nokmr[]={"A1","A2","A3","A4","A5","B1","B2","B3","B4","B5","C1","C2","C3","C4","C5"};
    String tpkmr[]={"Deluxe","Standard"};
    String lks[]={"Bali","Lombok"};
    private JComboBox cmbnokmar = new JComboBox(nokmr);
    private JComboBox cmbtipekmr = new JComboBox(tpkmr);
    private JComboBox cmblokasi = new JComboBox(lks);

    private JTable table = new JTable();
    private JScrollPane tb = new JScrollPane(table);

    DefaultTableModel dtm;
    Statement st;
    ResultSet rs;
    public Check_In(){
        new Database();
        setTitle("CHECK IN");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Font font1 = new Font("Arial", Font.BOLD,38);
        Font font2 = new Font("Arial", Font.BOLD,17);

        JLabel judul = new JLabel("CHECK IN");
        JLabel nama = new JLabel("Nama Tamu");
        JLabel nkamr = new JLabel("No. Kamar");
        JLabel lokasi = new JLabel("Lokasi");
        JLabel lama = new JLabel("Lama");
        JLabel biaya = new JLabel("Biaya");
        JLabel jmltmu = new JLabel("Jumlah Tamu");
        JLabel tpkmr = new JLabel("Tipe Villa");
        JLabel tgl = new JLabel("Tanggal Check In");

        judul.setBounds(330,25, 300,30);
        judul.setFont(font1);
        nama.setBounds(40,85, 400,30);
        nama.setFont(font2);
        nkamr.setBounds(40,135, 400,30);
        nkamr.setFont(font2);
        lokasi.setBounds(40,185, 400,30);
        lokasi.setFont(font2);
        tpkmr.setBounds(40,235, 400,30);
        tpkmr.setFont(font2);
        lama.setBounds(450,85, 400,30);
        lama.setFont(font2);
        biaya.setBounds(450,135, 400,30);
        biaya.setFont(font2);
        jmltmu.setBounds(450,185, 400,30);
        jmltmu.setFont(font2);
        tgl.setBounds(450,235, 400,30);
        tgl.setFont(font2);

        inpnama.setBounds(180,90,170,25);
        inbill.setBounds(620,140,170,25);
        lamainap.setBounds(620,90,170,25);

        cmbtipekmr.setBounds(180,240,120,25);
        cmblokasi.setBounds(180,190,120,25);
        cmbnokmar.setBounds(180,140,120,25);

        btncheckin.setBounds(680,520,110,25);
        btninsert.setBounds(705,290,80,25);
        btnBack.setBounds(550,520,110,25);

        jumtmu.setBounds(620,190,50,25);

        date.setBounds(620, 240, 170, 25);

        tb.setBounds(40,350,750,150);

        btninsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertdata();
            }
        });

        btncheckin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Check_Out();
                dispose();
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DataEntry();
                dispose();
            }
        });
        tampilkan();

        add(date);
        add(judul);
        add(nama);
        add(nkamr);
        add(lokasi);
        add(lama);
        add(biaya);
        add(jmltmu);
        add(tpkmr);
        add(tgl);
        add(btncheckin);
        add(btninsert);
        add(btnBack);
        add(inbill);
        add(inpnama);
        add(lamainap);
        add(cmblokasi);
        add(cmbnokmar);
        add(cmbtipekmr);
        add(jumtmu);
        add(tb);
        setSize(850,600);
        setLayout(null);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void insertdata(){
        String nama = inpnama.getText();
        String nomor = cmbnokmar.getSelectedItem().toString();
        String lokasi = cmblokasi.getSelectedItem().toString();
        String tipe = cmbtipekmr.getSelectedItem().toString();
        String lama = lamainap.getText();
        int biaya = Integer.parseInt(inbill.getText());
        int jumlahTamu = Integer.parseInt(jumtmu.getValue().toString());
        String tglcheckin = date.getText();

        try{
            String sql = "INSERT INTO checkin VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = Database.koneksi.prepareStatement(sql);
            ps.setString(1,nama);
            ps.setString(2,nomor);
            ps.setString(3,lokasi);
            ps.setString(4,tipe);
            ps.setString(5,lama);
            ps.setInt(6,biaya);
            ps.setInt(7,jumlahTamu);
            ps.setString(8,tglcheckin);
            ps.execute();
            tampilkan();
            JOptionPane.showMessageDialog(null,"Berhasil Simpan Data");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Gagal Simpan Data");
        }
    }

    public void tampilkan(){
        cmbnokmar.addItem("");
        cmblokasi.addItem("");
        cmbtipekmr.addItem("");

        try{
            String set[]={"Nama","No. Kamar", "Lokasi", "Tipe Kamar", "Lama", "Biaya", "Jumlah Tamu", "Tanggal Check In"};
            dtm = new DefaultTableModel(null,set);
            table.setModel(dtm);
            try{
                String sql = "SELECT * FROM checkin";
                st = Database.koneksi.createStatement();
                rs = st.executeQuery(sql);
                while(rs.next()){
                    Object[] row = {
                            rs.getString("namaTamu"),
                            rs.getString("noKamar"),
                            rs.getString("lokasi"),
                            rs.getString("tipeVilla"),
                            rs.getString("lama"),
                            rs.getInt("biaya"),
                            rs.getInt("jumlahTamu"),
                            rs.getString("tanggalCheckIn")
                    };
                    dtm.addRow(row);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
