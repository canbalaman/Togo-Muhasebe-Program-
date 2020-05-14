package tr.com.canyazilim.fe;

import static java.awt.Dialog.DEFAULT_MODALITY_TYPE;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import tr.com.canyazilim.enties.KategoriContract;
import tr.com.canyazilim.interfaces.FeInterfaces;

public class KategoriEkleFe extends JDialog implements FeInterfaces {

    public KategoriEkleFe() {
        initPencere();
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/ıco/genel.png"));
    }

    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Kategori Ekleme Alanı"));
        add(panel);
        setTitle("Kategori Ekle");
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
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel adiLabel = new JLabel("Kategori Adı: ", JLabel.RIGHT);
        panel.add(adiLabel);
        JTextField adiField = new JTextField(10);
        panel.add((adiField));
        JLabel kategoriLabel = new JLabel("Kategori Seç: ", JLabel.RIGHT);
        panel.add(kategoriLabel);
        JComboBox KategoriBox = new JComboBox(new KategoriDAL().GetAllParentId().toArray());
        panel.add(KategoriBox);
        KategoriBox.insertItemAt("-- Kategori Seçiniz -- ", 0);// sıfırıncı değişken kategori seçiniz diyoruz
        KategoriBox.setSelectedIndex(0);// sıfırıncı değer gözüküyor ekranda
        JButton kaydetButton = new JButton("Kaydet");
        panel.add(kaydetButton);
        kaydetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                KategoriContract contract = new KategoriContract();
                if (KategoriBox.getSelectedIndex() != 0) {
                    KategoriContract casContract = (KategoriContract) KategoriBox.getSelectedItem();
                    contract.setAdi(adiField.getText());
                    contract.setParentId(casContract.getId());
                    
                    new KategoriDAL().Insert(contract);
                    JOptionPane.showMessageDialog(null, contract.getAdi() + " Adlı Kategori Başarılı Bir  Şekilde Kayıt edilmiştir");

                } else {
                    contract.setAdi(adiField.getText());
                    contract.setParentId(KategoriBox.getSelectedIndex());
                    new KategoriDAL().Insert(contract);
                    JOptionPane.showMessageDialog(null, contract.getAdi() + " Adlı Kategori Başarılı Bir  Şekilde Kayıt edilmiştir");
                  
                }

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
