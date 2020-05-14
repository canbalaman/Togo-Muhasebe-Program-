package tr.com.canyazilim.fe;

import static java.awt.Dialog.DEFAULT_MODALITY_TYPE;
import java.awt.GridLayout;
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
import static javax.swing.WindowConstants.HIDE_ON_CLOSE;
import tr.com.canyazilim.dal.YetkilerDAL;
import tr.com.canyazilim.enties.YetkilerContract;
import tr.com.canyazilim.interfaces.FeInterfaces;

public class YetkiEkleFE extends JDialog implements FeInterfaces {

    public YetkiEkleFE() {
        initPencere();
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/ıco/genel.png"));

    }

    @Override
    public void initPencere() {
        JPanel panel = initPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Yetki Ekleme Alanı"));
        add(panel);
        setTitle(("Yetki Ekleyin"));
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
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JLabel AdiLabel = new JLabel("Yetki Adi:", JLabel.RIGHT);
        panel.add(AdiLabel);
        JTextField adiField = new JTextField(10);
        panel.add(adiField);

        JButton kaydetButton = new JButton("Kaydet");
        JButton iptalButton = new JButton("İptal");
        panel.add(kaydetButton);
        panel.add(iptalButton);
        
        kaydetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                YetkilerContract contract=new YetkilerContract();
                contract.setAdı(adiField.getText());
                new YetkilerDAL().Insert(contract);
                JOptionPane.showMessageDialog(null, contract.getAdı()+" Adlı Yetki Başarılı bir Şekilde Eklendi");
            }
        });
        return panel;
    }

    @Override
    public JTabbedPane initTabs() {
        return null;
    }

}
