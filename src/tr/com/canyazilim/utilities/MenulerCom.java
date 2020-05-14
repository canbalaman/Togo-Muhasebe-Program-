package tr.com.canyazilim.utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import tr.com.canyazilim.dal.AccountsDAL;
import tr.com.canyazilim.enties.PersonelContract;
import tr.com.canyazilim.fe.KategoriEkleFe;
import tr.com.canyazilim.fe.LoginFE;
import tr.com.canyazilim.fe.MusteriEkleFE;
import tr.com.canyazilim.fe.PersonelEkleFE;
import tr.com.canyazilim.fe.SifreİslemFE;
import tr.com.canyazilim.fe.UrunEkleFE;
import tr.com.canyazilim.fe.YetkiEkleFE;

public class MenulerCom {

    public static JMenuBar initBar() {
        ImageIcon dosyaIcon = new ImageIcon("src/ıco/dosya.png");
        ImageIcon çıkışIcon = new ImageIcon("src/ıco/çıkış.png");
        ImageIcon ürünlerIcon = new ImageIcon("src/ıco/ürünler.png");
        ImageIcon ürünekleIcon = new ImageIcon("src/ıco/ürünekle.png");
        ImageIcon kategoriekleIcon = new ImageIcon("src/ıco/kategoriekle.png");
        ImageIcon personelIcon = new ImageIcon("src/ıco/personal.png");
        ImageIcon personelekleIcon = new ImageIcon("src/ıco/personelekle.png");
        ImageIcon yetkiekleIcon = new ImageIcon("src/ıco/yetkiekle.png");
        ImageIcon şifreekleIcon = new ImageIcon("src/ıco/password.png");
        ImageIcon müşteriekleIcon = new ImageIcon("src/ıco/müşteriekle.png");
        ImageIcon müşteriIcon = new ImageIcon("src/ıco/musteri.png");

        JMenuBar bar = new JMenuBar();

        JMenu dosyamenu = new JMenu("Dosya");
        dosyamenu.setIcon(dosyaIcon);
        bar.add(dosyamenu);
        /* ----------------------*/
        JMenuItem cıkısItem = new JMenuItem("Çıkış");
        cıkısItem.setIcon(çıkışIcon);
        dosyamenu.add(cıkısItem);

        /* Urunler Kısmı*/
        JMenu urunlerMenu = new JMenu("Ürünler");
        bar.add(urunlerMenu);
        urunlerMenu.setIcon(ürünlerIcon);
        /* ----------------------*/
        JMenuItem urunekleıtem = new JMenuItem("Ürün Ekle");
        urunlerMenu.add(urunekleıtem);
        urunekleıtem.setIcon(ürünekleIcon);
        /* ----------------------*/
        JMenuItem KatagoriEkleItem = new JMenuItem("Katagori Ekle");
        urunlerMenu.add(KatagoriEkleItem);
        KatagoriEkleItem.setIcon(kategoriekleIcon);
        /* ----------------------*/

        /* Personel Kısmı*/
        JMenu personellerMenu = new JMenu("Personel İşlemleri");
        bar.add(personellerMenu);
        personellerMenu.setIcon(personelIcon);
        /* ----------------------*/
        JMenuItem PersonelEkleItem = new JMenuItem("Personel Ekle");
        personellerMenu.add(PersonelEkleItem);
        PersonelEkleItem.setIcon(personelekleIcon);
        /* ----------------------*/
        JMenuItem YetkiEkleItem = new JMenuItem("Yetki Ekle");
        personellerMenu.add(YetkiEkleItem);
        YetkiEkleItem.setIcon(yetkiekleIcon);
        /* ----------------------*/
        JMenuItem sifreBelirleItem = new JMenuItem("Şifre Belirleme");
        personellerMenu.add(sifreBelirleItem);
        sifreBelirleItem.setIcon(şifreekleIcon);
        /* ----------------------*/

        /* Müşteri  Kısmı*/
        JMenu musterilerMenu = new JMenu("Müşteriler");
        bar.add(musterilerMenu);
        musterilerMenu.setIcon(müşteriIcon);
        /* ----------------------*/
        JMenuItem MusteriEkle = new JMenuItem("Müşteri Ekle");
        musterilerMenu.add(MusteriEkle);
        MusteriEkle.setIcon(müşteriekleIcon);

        PersonelContract contract = (PersonelContract) LoginFE.emailbox.getSelectedItem();
        if (new AccountsDAL().GetYetkiId(contract.getId()).getYetkiId() == 2) {
            personellerMenu.hide();

        } else if (new AccountsDAL().GetYetkiId(contract.getId()).getYetkiId() == 3) {
            musterilerMenu.hide();
            personellerMenu.hide();
            urunlerMenu.hide();
        }
        cıkısItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        urunekleıtem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    public void run() {
                        new UrunEkleFE();

                    }
                });
            }
        });
        KatagoriEkleItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new KategoriEkleFe();
            }

        });

        PersonelEkleItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        new PersonelEkleFE();
                    }
                });

            }
        });
        YetkiEkleItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {

                    @Override
                    public void run() {
                        new YetkiEkleFE();
                    }
                });
            }
        });
        sifreBelirleItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new SifreİslemFE();
            }
        });
        MusteriEkle.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new MusteriEkleFE();
            }
        });

        return bar;
    }

}
