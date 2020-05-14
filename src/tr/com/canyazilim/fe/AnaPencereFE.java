package tr.com.canyazilim.fe;

import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import tr.com.canyazilim.complex.enties.SatisContractComplex;
import tr.com.canyazilim.complex.enties.StokContractComplex;
import tr.com.canyazilim.complex.enties.StokContractTotalComplex;
import tr.com.canyazilim.dal.MusreteriDAL;
import tr.com.canyazilim.dal.SatisDAL;
import tr.com.canyazilim.dal.StokDAL;
import tr.com.canyazilim.dal.UrunlerDAL;
import tr.com.canyazilim.enties.MusteriContract;
import tr.com.canyazilim.enties.PersonelContract;
import tr.com.canyazilim.enties.SatisContract;
import tr.com.canyazilim.enties.StokContract;
import tr.com.canyazilim.enties.UrunlerContract;
import tr.com.canyazilim.interfaces.FeInterfaces;
import tr.com.canyazilim.utilities.MenulerCom;

public class AnaPencereFE extends JFrame implements FeInterfaces {
    
    public AnaPencereFE() {
        initPencere();
        
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/ıco/genel.png"));
        
    }
    
    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        JMenuBar bar = initBar();
        
        add(panel);
        setJMenuBar(bar);
        setTitle("Togo Satış Ve Stok Programı");//Başlık İsmi
        setSize(1280, 1024);// Pencer Boyutu
        setVisible(true);//Pencere Açılması
        setLocationRelativeTo(null);//Pencerenin Merkezde Durması
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    @Override
    public JMenuBar initBar() {
        JMenuBar bar = MenulerCom.initBar();// menulercom içindeki kodları bar atadık

        return bar;
        
    }
    
    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JTabbedPane pane = initTabs();
        panel.add(pane, BorderLayout.CENTER);
        
        return panel;
        
    }
    
    @Override
    public JTabbedPane initTabs() {
        JTabbedPane pane = new JTabbedPane();
        ImageIcon satısIcon = new ImageIcon("src/ıco/satıs.png");
        ImageIcon stokIcon = new ImageIcon("src/ıco/stok.png");
        ImageIcon musteriIcon = new ImageIcon("src/ıco/dollar.png");
        
        JPanel stokPanel = new JPanel(new BorderLayout());
        JPanel satisPanel = new JPanel(new BorderLayout());
        JPanel musteriPanel = new JPanel(new BorderLayout());
        /* Stok işlemleri*/
        
        JPanel stokSolPanel = new JPanel(new BorderLayout());
        JPanel stokSolUstPanel = new JPanel(new GridLayout(5, 2));
        JPanel stokSolAltPanel = new JPanel();
        
        stokSolPanel.setBorder(BorderFactory.createTitledBorder("Stok ekle İşlemleri"));
        
        Object[] stokKolonlar = {"Id", "Personel Adı", "Ürün Adı", "Adeti", "Tarih"};
        DefaultTableModel model = new DefaultTableModel(stokKolonlar, 0);
        JTable table = new JTable(model);
        JScrollPane StokTablPane = new JScrollPane(table);
        for (StokContractComplex contract : new StokDAL().GetAllStok()) {
            model.addRow(contract.getVeriler());
        }
        
        JLabel stokUrunAdiLabel = new JLabel("Ürün Adı ", JLabel.RIGHT);
        stokSolUstPanel.add(stokUrunAdiLabel);
        JComboBox stokUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
        stokSolUstPanel.add(stokUrunAdiBox);
        JLabel stokAdetLabel = new JLabel("Adet ", JLabel.RIGHT);
        stokSolUstPanel.add(stokAdetLabel);
        JTextField stokAdetField = new JTextField(10);
        stokSolUstPanel.add(stokAdetField);
        JLabel stokTarihLabel = new JLabel("Stok Tarihi ", JLabel.RIGHT);
        stokSolUstPanel.add(stokTarihLabel);
        JDateChooser stokTarihi = new JDateChooser();
        stokSolUstPanel.add(stokTarihi);
        JButton stokEkleButton = new JButton("Stok Ekle");
        JButton stokYenileButton = new JButton("Stok Yenile");
        stokSolUstPanel.add(stokEkleButton);
        stokSolUstPanel.add(stokYenileButton);
        JButton stoktopButton = new JButton("Stok Toplamı");
        stokSolUstPanel.add(stoktopButton);
        stokYenileButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int satir = model.getRowCount();
                for (int i = 0; i < satir; i++) {
                    model.removeRow(0);
                    
                }
                for (StokContractComplex compcontract : new StokDAL().GetAllStok()) {
                    model.addRow(compcontract.getVeriler());
                }
            }
        });
        
        stokEkleButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                StokContract contract = new StokContract();
                PersonelContract pContract = (PersonelContract) LoginFE.emailbox.getSelectedItem();
                UrunlerContract uContract = (UrunlerContract) stokUrunAdiBox.getSelectedItem();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String date = format.format(stokTarihi.getDate());
                contract.setPersonelId(pContract.getId());
                contract.setUrunId(uContract.getId());
                contract.setTarih(date);
                contract.setAdet(Integer.parseInt(stokAdetField.getText()));
                new StokDAL().Insert(contract);
                JOptionPane.showMessageDialog(null, uContract.getAdi() + " Adlı Ürün Eklenmiştir");
                int satir = model.getRowCount();
                for (int i = 0; i < satir; i++) {
                    model.removeRow(0);
                    
                }
                for (StokContractComplex compcontract : new StokDAL().GetAllStok()) {
                    model.addRow(compcontract.getVeriler());
                }
            }
        });
        stoktopButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int satir = model.getRowCount();
                for (int i = 0; i < satir; i++) {
                    model.removeRow(0);
                    //  Object[] stokKolonlar = {"Id", "Personel Adı", "Ürün Adı", "Toplamı", "Tarih"};

                }
                for (StokContractTotalComplex total : new StokDAL().GetTotalStok()) {
                    model.addRow(total.getVeriler());
                }
                
            }
        });


        /* Satiş Ekranı*/
        JPanel satisSagPanel = new JPanel(new BorderLayout());
        JPanel satisSagUstPanel = new JPanel(new GridLayout(5, 2));
        JPanel satisSagAltPanell = new JPanel();
        Object[] satisKolonlar = {"Id", "Personel Adı", "Müşteri Adı", "Ürün Adı", "Adeti", "Tarih"};
        DefaultTableModel satisModel = new DefaultTableModel(satisKolonlar, 0);
        for (SatisContractComplex contract : new SatisDAL().GetAllSatis()) {
            satisModel.addRow(contract.getVeriler());
        }
        
        JTable satisTable = new JTable(satisModel);
        JScrollPane satisTablPane = new JScrollPane(satisTable);
        JLabel MusteriLabel = new JLabel("Müşteri Adı:", JLabel.RIGHT);
        satisSagUstPanel.add(MusteriLabel);
        JComboBox MusteriAdiBox = new JComboBox(new MusreteriDAL().GetAll().toArray());
        satisSagUstPanel.add(MusteriAdiBox);
        JLabel satisUrunAdiLabel = new JLabel("Ürün Adı ", JLabel.RIGHT);
        satisSagUstPanel.add(satisUrunAdiLabel);
        JComboBox satisUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
        satisSagUstPanel.add(satisUrunAdiBox);
        JLabel satiskAdetLabel = new JLabel("Adet ", JLabel.RIGHT);
        satisSagUstPanel.add(satiskAdetLabel);
        JTextField satisAdetField = new JTextField(10);
        satisSagUstPanel.add(satisAdetField);
        JLabel satisTarihLabel = new JLabel("Satış Tarihi ", JLabel.RIGHT);
        satisSagUstPanel.add(satisTarihLabel);
        JDateChooser satisTarihi = new JDateChooser();
        satisSagUstPanel.add(satisTarihi);
        JButton satisEkleButton = new JButton("Satış Yap ");
        JButton satisYenileButton = new JButton(" Yenile");
        satisSagUstPanel.add(satisEkleButton);
        satisSagUstPanel.add(satisYenileButton);
        satisEkleButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                SatisContract contract = new SatisContract();
                PersonelContract pContract = (PersonelContract) LoginFE.emailbox.getSelectedItem();
                UrunlerContract uContract = (UrunlerContract) satisUrunAdiBox.getSelectedItem();
                MusteriContract Mcontract = (MusteriContract) MusteriAdiBox.getSelectedItem();
                
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String date = format.format(satisTarihi.getDate());
                
                contract.setMusteriId(Mcontract.getId());
                contract.setPersonelId(pContract.getId());
                contract.setTarih(date);
                contract.setUrunId(uContract.getId());
                contract.setAdet(Integer.parseInt(satisAdetField.getText()));
                new SatisDAL().Insert(contract);
                StokContract stokContract = new StokContract();
                stokContract.setPersonelId(pContract.getId());
                stokContract.setUrunId(uContract.getId());
                stokContract.setAdet(-Integer.parseInt(satisAdetField.getText()));
                stokContract.setTarih(date);
                
                new StokDAL().Insert(stokContract);
                
                JOptionPane.showMessageDialog(null, Mcontract.getAdiSoyadi() + " Adlı Müşteriye " + uContract.getAdi() + " Adlı Ürün Satılmılştır");
                int satir = satisModel.getRowCount();
                for (int i = 0; i < satir; i++) {
                    satisModel.removeRow(0);

                }
                for (SatisContractComplex Yenileme : new SatisDAL().GetAllSatis()) {
                    satisModel.addRow(Yenileme.getVeriler());
                }
            }
            
            
        });
        satisYenileButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int satir = satisModel.getRowCount();
                for (int i = 0; i < satir; i++) {
                    satisModel.removeRow(0);

                }
                for (SatisContractComplex contract : new SatisDAL().GetAllSatis()) {
                    satisModel.addRow(contract.getVeriler());
                }
            }
        });
        
        stokPanel.add(stokSolPanel, BorderLayout.WEST);
        stokPanel.add(StokTablPane, BorderLayout.CENTER);
        
        satisPanel.add(satisSagPanel, BorderLayout.EAST);
        satisPanel.add(satisTablPane, BorderLayout.CENTER);
        
        satisSagPanel.add(satisSagUstPanel, BorderLayout.NORTH);
        satisSagPanel.add(satisSagAltPanell, BorderLayout.SOUTH);
        
        stokSolPanel.add(stokSolUstPanel, BorderLayout.NORTH);
        stokSolPanel.add(stokSolAltPanel, BorderLayout.SOUTH);
        /*Müşteri Panel İşlemleri*/
        Object[] musterikolonlar = {"Id", "Müşteri Adı", "Telefonu", "Adres", "Şehir Id",};
        DefaultTableModel musterimodel = new DefaultTableModel(musterikolonlar, 0);
        for (MusteriContract contract : new MusreteriDAL().GetAll()) {
            musterimodel.addRow(contract.getVeriler());
        }
        JTable musteriTable = new JTable(musterimodel);
        JScrollPane musteriTablPane = new JScrollPane(musteriTable);
        musteriPanel.add(musteriTablPane);
        
        
        
        
        pane.addTab("Stok Hareketi  ", stokIcon, stokPanel, "does nothing");
        pane.addTab("Satış Hareketi  ", satısIcon, satisPanel, "does nothing");
        pane.addTab("Müşteri Bilgileri  ", musteriIcon, musteriPanel, "does nothing");
        
        
        return pane;
        
    }
    
}
