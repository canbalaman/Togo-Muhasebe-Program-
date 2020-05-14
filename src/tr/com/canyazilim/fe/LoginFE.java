package tr.com.canyazilim.fe;

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
import tr.com.canyazilim.dal.AccountsDAL;
import tr.com.canyazilim.dal.PersonelDAL;
import tr.com.canyazilim.enties.PersonelContract;
import tr.com.canyazilim.interfaces.FeInterfaces;

public class LoginFE extends JDialog implements FeInterfaces {
 
    public static JComboBox emailbox;
    
    public  LoginFE(){
        initPencere();
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/ıco/genel.png"));
    }
    @Override
    public void initPencere() {
        JPanel panel =initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Lütfen Sisteme Giriş Yapmak İçin Bİlgilerinizi Giriniz"));
        add(panel);
        setTitle("Giriş Yapınnız");
        pack();
     //   setModalityType(DEFAULT_MODALITY_TYPE);
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
        JPanel panel =new JPanel(new GridLayout(3,2));
        JLabel emailLabel =new JLabel("Email",JLabel.RIGHT);
        panel.add(emailLabel);
        emailbox =new JComboBox(new PersonelDAL().GetAll().toArray());
        panel.add(emailbox); 
        JLabel passwordLabel =new JLabel("Şifreniz",JLabel.RIGHT);
        panel.add(passwordLabel);
        JPasswordField passwordField=new JPasswordField(15);
        panel.add(passwordField);
        JButton loginButton = new JButton("Giriş Yap");
        panel.add(loginButton);
        JButton iptalButton = new JButton("İptal");
        panel.add(iptalButton);
        
        loginButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                
                PersonelContract contract =(PersonelContract)emailbox.getSelectedItem();
                String sifre =passwordField.getText();
                if (new AccountsDAL().GetPersonelIdVeSifre(contract.getId(), sifre).getId()!=0) {
                    
                    
                    new AnaPencereFE();
                    
                    
                    
                    
                }
                else 
                    JOptionPane.showMessageDialog(null," Şifre Ve Email Uyuşmuyor");
            }
        });
        
        
        return panel;
        
    }

    @Override
    public JTabbedPane initTabs() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
