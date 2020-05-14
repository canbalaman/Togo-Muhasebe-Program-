package tr.com.canyazilim.fe;

import static java.awt.Dialog.DEFAULT_MODALITY_TYPE;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.control.PasswordField;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import tr.com.canyazilim.dal.AccountsDAL;
import tr.com.canyazilim.dal.PersonelDAL;
import tr.com.canyazilim.dal.YetkilerDAL;
import tr.com.canyazilim.enties.AccountsContract;
import tr.com.canyazilim.enties.PersonelContract;
import tr.com.canyazilim.enties.YetkilerContract;
import tr.com.canyazilim.interfaces.FeInterfaces;

public class SifreİslemFE extends JDialog implements FeInterfaces {

    public SifreİslemFE() {
        initPencere();
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/ıco/genel.png"));

    }

    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Şifre İşlemleri "));
        add(panel);
        setTitle(("Şifre İşlemleri"));
        pack();
        setModalityType(DEFAULT_MODALITY_TYPE);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public JMenuBar initBar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JPanel initPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JLabel PersonelLabel = new JLabel("Personel Seç", JLabel.RIGHT);
        panel.add(PersonelLabel);
        JComboBox PersonelBox = new JComboBox(new PersonelDAL().GetAll().toArray());
        panel.add(PersonelBox);
        JLabel yetkiLabel = new JLabel("Yetki Seç", JLabel.RIGHT);
        panel.add(yetkiLabel);
        JComboBox yetkiBox = new JComboBox(new YetkilerDAL().GetAll().toArray());
        panel.add(yetkiBox);
        JLabel passwordLabel = new JLabel("Şifre Belirle", JLabel.RIGHT);
        panel.add(passwordLabel);
        JPasswordField passField = new JPasswordField(10);
        panel.add(passField);
        JLabel passTekrarLabel = new JLabel("Şifre Tekrar Giriniz", JLabel.RIGHT);
        panel.add(passTekrarLabel);
        JPasswordField PassTekrar = new JPasswordField(10);
        panel.add(PassTekrar);
        JButton kaydetButton = new JButton("Kaydet");
        JButton iptalButton = new JButton("İptal");
        panel.add(kaydetButton);
        panel.add(iptalButton);
        kaydetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                AccountsContract contract = new AccountsContract();
                PersonelContract pContract = (PersonelContract) PersonelBox.getSelectedItem();
                YetkilerContract yContract = (YetkilerContract) yetkiBox.getSelectedItem();
                if (passField.getText().equals(PassTekrar.getText())) {
                    contract.setPersonelId(pContract.getId());
                    contract.setYetkiId(yContract.getId());
                    contract.setSifre(passField.getText());
                    new AccountsDAL().Insert(contract);
                    JOptionPane.showMessageDialog(null,pContract.getAdiSoyadi()+" Adlı Kişinin "+yContract.getAdı()+" Adlı Yetki Başarıyla Bir Şekilde Eklenmiştir");
                } else {
                    JOptionPane.showMessageDialog(null, "Şifreler Uyuşmuyor ");
                }

            }
        });

        return panel;
    }

    @Override
    public JTabbedPane initTabs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
