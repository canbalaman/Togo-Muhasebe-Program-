package tr.com.canyazilim.fe;

import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import tr.com.canyazilim.dal.PersonelDAL;
import tr.com.canyazilim.enties.PersonelContract;
import tr.com.canyazilim.interfaces.FeInterfaces;

public class PersonelEkleFE  extends  JDialog implements FeInterfaces{
  
    public PersonelEkleFE(){
        initPencere();
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/ıco/genel.png"));
    }
    
    @Override
    public void initPencere() {
        setTitle("Personel Ekle");
        JPanel panel=initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Personel Kayıt Alanı"));
        add(panel);
        pack();
        setLocationRelativeTo(null);
        setModalityType(DEFAULT_MODALITY_TYPE);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
    }

    @Override
    public JMenuBar initBar() {
       throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public JPanel initPanel() {
         JPanel panel =new JPanel( new GridLayout(3,2));
        JLabel adisoyadiLabel =new JLabel("Adi Soyadı",JLabel.RIGHT);
        panel.add(adisoyadiLabel);
        JTextField adiSoyadiField= new JTextField(10);
        panel.add(adiSoyadiField);
        JLabel eMailLabel =new JLabel("Email",JLabel.RIGHT);
        panel.add(eMailLabel);
        JTextField EmailField =new JTextField(15);
        panel.add(EmailField);
        JButton kaydetButton =new JButton(" Kaydet ");
        JButton iptalButton =new JButton(" İptal ");
        panel.add(kaydetButton);
        panel.add(iptalButton);
        kaydetButton.addActionListener(new ActionListener() {

             @Override
             public void actionPerformed(ActionEvent e) {
                 PersonelContract contract =new PersonelContract();
                 contract.setAdiSoyadi(adiSoyadiField.getText());
                 contract.setEmail(EmailField.getText());
                 new PersonelDAL().Insert(contract);
                 JOptionPane.showMessageDialog(null, contract.getAdiSoyadi()+" Adlı Personel eklenmiştir.");
             }
         } );
        
        
        
                
        return  panel;
    }

    @Override
    public JTabbedPane initTabs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
