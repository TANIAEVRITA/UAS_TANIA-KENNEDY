import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Check_Out extends JFrame {
    private JSpinner jumtmu = new JSpinner();
    private JTextField inpnama = new JTextField();
    private JTextField lamainap = new JTextField();
    private JTextField cheout = new JTextField();
    private JTextField inBill = new JTextField();
    private JButton btnInsert = new JButton("Insert");
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

    public Check_Out(){
        new Database();
        setTitle("CHECK OUT");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Font font1 = new Font("Arial", Font.BOLD,38);
        Font font2 = new Font("Arial", Font.BOLD,17);

        JLabel judul = new JLabel("CHECK OUT");
        JLabel nama = new JLabel("Nama Tamu");
        JLabel nkamr = new JLabel("No. Kamar");
        JLabel lokasi = new JLabel("Lokasi");
        JLabel cin = new JLabel("Tanggal Check In");
        JLabel biaya = new JLabel("Biaya");
        JLabel jmltmu = new JLabel("Jumlah Tamu");
        JLabel tpkmr = new JLabel("Tipe Villa");
        JLabel cout = new JLabel("Tanggal Check Out");

        judul.setBounds(320,20, 300,30);
        judul.setFont(font1);
        nama.setBounds(40,85, 400,30);
        nama.setFont(font2);
        nkamr.setBounds(40,135, 400,30);
        nkamr.setFont(font2);
        lokasi.setBounds(40,185, 400,30);
        lokasi.setFont(font2);
        tpkmr.setBounds(40,235, 400,30);
        tpkmr.setFont(font2);
        cin.setBounds(450,85, 400,30);
        cin.setFont(font2);
        biaya.setBounds(450,235, 400,30);
        biaya.setFont(font2);
        jmltmu.setBounds(450,185, 400,30);
        jmltmu.setFont(font2);
        cout.setBounds(450,135, 400,30);
        cout.setFont(font2);

        inpnama.setBounds(180,90,170,25);
        inBill.setBounds(620,240,170,25);
        lamainap.setBounds(620,90,170,25);

        cmbtipekmr.setBounds(180,240,120,25);
        cmblokasi.setBounds(180,190,120,25);
        cmbnokmar.setBounds(180,140,120,25);

        btnBack.setBounds(680,520,110,25);
        btnInsert.setBounds(705,290,80,25);

        jumtmu.setBounds(620,190,50,25);

        cheout.setBounds(620,140,170,25);

        tb.setBounds(40,350,750,150);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Check_In();
                dispose();
            }
        });

        btnInsert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertdata();
            }
        });
        tampilkan();
        add(judul);
        add(nama);
        add(nkamr);
        add(lokasi);
        add(cin);
        add(biaya);
        add(jmltmu);
        add(tpkmr);
        add(cout);
        add(cheout);
        add(btnBack);
        add(btnInsert);
        add(inBill);
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
        int biaya = Integer.parseInt(inBill.getText());
        int jumlahTamu = Integer.parseInt(jumtmu.getValue().toString());
        String tglcheckout = cheout.getText();
        String tglcheckin = lamainap.getText();

        try{
            String sql = "INSERT INTO checkout VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = Database.koneksi.prepareStatement(sql);
            ps.setString(1,nama);
            ps.setString(2,nomor);
            ps.setString(3,lokasi);
            ps.setString(4,tipe);
            ps.setString(5,tglcheckin);
            ps.setString(6,tglcheckout);
            ps.setInt(7,jumlahTamu);
            ps.setInt(8,biaya);
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
            String set[]={"Nama","No. Kamar", "Lokasi", "Tipe Kamar", "Tanggal Check In", "Tanggal Check Out", "Jumlah Tamu","BiayaTanggal Check Out"};
            dtm = new DefaultTableModel(null,set);
            table.setModel(dtm);
            try{
                String sql = "SELECT * FROM checkout";
                st = Database.koneksi.createStatement();
                rs = st.executeQuery(sql);
                while(rs.next()){
                    Object[] row = {
                            rs.getString("namaTamu"),
                            rs.getString("noKamar"),
                            rs.getString("lokasi"),
                            rs.getString("tipeVilla"),
                            rs.getString("tanggalCheckIn"),
                            rs.getString("tanggalCheckOut"),
                            rs.getInt("jumlahTamu"),
                            rs.getInt("biaya")
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
