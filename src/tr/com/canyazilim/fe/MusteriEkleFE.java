package tr.com.canyazilim.fe;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import tr.com.canyazilim.dal.MusreteriDAL;
import tr.com.canyazilim.dal.SehirlerDAL;
import tr.com.canyazilim.enties.MusteriContract;
import tr.com.canyazilim.enties.SehirlerContract;
import tr.com.canyazilim.interfaces.FeInterfaces;

public class MusteriEkleFE extends JDialog implements FeInterfaces{

    public MusteriEkleFE(){
        initPencere();
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/ıco/genel.png"));
        
    }
    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Müşteri Ekleme Alanı"));
        add(panel);
        setTitle(("Müşteri Ekle"));
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
        JPanel panel=new JPanel(new BorderLayout());
        JPanel FieldPanel =new JPanel(new GridLayout(5, 2));
        JPanel ButtonPanel =new JPanel(new GridLayout(1,2));
        JLabel AdiSoyadiLabel =new JLabel("Adi Soyadi",JLabel.RIGHT);
        FieldPanel.add(AdiSoyadiLabel);
        JTextField AdiSoyField =new JTextField(15);
        FieldPanel.add(AdiSoyField);
        JLabel TelLabel =new JLabel("Telefon",JLabel.RIGHT);
        FieldPanel.add(TelLabel);
        JTextField TelField =new JTextField(15);
        FieldPanel.add(TelField);
        JLabel SehirSecLabel =new JLabel("Şehir Seç",JLabel.RIGHT);
        FieldPanel.add(SehirSecLabel);
        JComboBox sehirlerComboBox=new JComboBox(new SehirlerDAL().GetAll().toArray());
        FieldPanel.add(sehirlerComboBox);
        JLabel AdresLabel =new JLabel("Adres Giriniz ");
        FieldPanel.add(AdresLabel);
        JTextArea adresArea =new JTextArea(5,2  );
        JScrollPane pane= new JScrollPane(adresArea);
        pane.setBorder(BorderFactory.createTitledBorder("Adres bilgisi"));
        JButton kaydetButton = new JButton("Kaydet");
        JButton iptalButton = new JButton("İptal");
        ButtonPanel.add(kaydetButton);
        ButtonPanel.add(iptalButton);
        kaydetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MusteriContract contract =new MusteriContract();
                SehirlerContract casContract = (SehirlerContract) sehirlerComboBox.getSelectedItem();
                contract.setAdiSoyadi(AdiSoyField.getText());
                contract.setAdres(adresArea.getText());
                contract.setSehirId(casContract.getId());
                contract.setTelefonu(TelField.getText());
                new MusreteriDAL().Insert(contract);
                JOptionPane.showMessageDialog(null, contract.getAdiSoyadi()+ " Adlı Müşteri Başarıyla Eklenmiştir.");
            }
        });
        panel.add(FieldPanel,BorderLayout.NORTH);
        panel.add(pane,BorderLayout.CENTER);
        panel.add(ButtonPanel,BorderLayout.SOUTH);
        return panel;
        
    }

    @Override
    public JTabbedPane initTabs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
