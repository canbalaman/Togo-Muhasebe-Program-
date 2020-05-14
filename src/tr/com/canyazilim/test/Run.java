package tr.com.canyazilim.test;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import tr.com.canyazilim.fe.LoginFE;

public class Run {

    public static void main(String[] args) {
        try{
            for(UIManager.LookAndFeelInfo info :UIManager.getInstalledLookAndFeels()){
                if ("Nimbus".equals(info.getName())) {//Tema
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
                 else {
                UIManager.setLookAndFeel  ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            }
            }
        }
        catch(Exception e){
            
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              
                new LoginFE();
            }
        });;
    }

}
