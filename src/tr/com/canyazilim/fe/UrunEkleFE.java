package tr.com.canyazilim.fe;

import com.toedter.calendar.JDateChooser;
import static java.awt.Dialog.DEFAULT_MODALITY_TYPE;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import tr.com.canyazilim.dal.KategoriDAL;
import tr.com.canyazilim.dal.UrunlerDAL;
import tr.com.canyazilim.enties.KategoriContract;
import tr.com.canyazilim.enties.UrunlerContract;
import tr.com.canyazilim.interfaces.FeInterfaces;

public class UrunEkleFE extends JDialog implements FeInterfaces {

    public UrunEkleFE() {
        initPencere();// Ana Ekran Açılmasını Sağlıyor
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/ıco/genel.png"));

    }

    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Ürün Kayıt Alanı"));

        add(panel);
        setTitle(("Ürün Ekleyin"));
        pack();
        setModalityType(DEFAULT_MODALITY_TYPE);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public JMenuBar initBar() {
        return null;
    }

    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JLabel adiLabel = new JLabel("Ürün Adı:", JLabel.RIGHT);
        panel.add(adiLabel);
        JTextField adiField = new JTextField(10);
        panel.add(adiField);
        JLabel kategoriLabel = new JLabel("Kategori Seç", JLabel.RIGHT);
        panel.add(kategoriLabel);
        JComboBox kategoriBox = new JComboBox(new KategoriDAL().GetAll().toArray());
        panel.add(kategoriBox);
        JLabel tarihLabel = new JLabel("Tarih Seç :", JLabel.RIGHT);
        panel.add(tarihLabel);
        JDateChooser tarihDate = new JDateChooser();
        panel.add(tarihDate);
        JLabel fiyatLabel = new JLabel("Fiyat Gir: ", JLabel.RIGHT);
        panel.add(fiyatLabel);
        JTextField fiyatField = new JTextField(10);
        panel.add(fiyatField);
        JButton kaydetButton = new JButton("Kaydet");
        panel.add(kaydetButton);
        kaydetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                UrunlerContract contract = new UrunlerContract();
                KategoriContract casContract = (KategoriContract) kategoriBox.getSelectedItem();
                String date = format.format(tarihDate.getDate());
                contract.setAdi(adiField.getText());
                contract.setKategoriId(casContract.getId());
                contract.setTarih(date);
                contract.setFiyat(Float.parseFloat(fiyatField.getText()));
                new UrunlerDAL().Insert(contract);
                JOptionPane.showMessageDialog(null, contract.getAdi() + " Adlı Ürün Başarıyla Eklenmiştir.");
            }
        });
        JButton iptalButton = new JButton("İptal");
        panel.add(iptalButton);
        return panel;

    }

    @Override
    public JTabbedPane initTabs() {
        return null;

    }

}
