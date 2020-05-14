package tr.com.canyazilim.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tr.com.canyazilim.core.ObjectHelper;
import tr.com.canyazilim.enties.AccountsContract;
import tr.com.canyazilim.interfaces.DALInterfacec;

public class AccountsDAL extends ObjectHelper implements DALInterfacec<AccountsContract> {

    @Override
    public void Insert(AccountsContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO accounts (PersonelId ,yetkiId,sifre) VALUES("
                    + entity.getPersonelId()
                    + ","
                    + entity.getYetkiId()
                    + ",'"
                    + entity.getSifre()
                    + "')");

            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public AccountsContract GetPersonelIdVeSifre(int PersonelId, String sifre) {
        AccountsContract contract = new AccountsContract();
        List<AccountsContract> listele = new ArrayList<AccountsContract>();
        Connection baglanti = getConnection();
        try {
            Statement sorgu = baglanti.createStatement();
            ResultSet rs = sorgu.executeQuery("SELECT * FROM accounts  WHERE PersonelId=" + PersonelId + " AND Sifre='" + sifre.trim() + "'");
            while (rs.next()) {
                contract.setId(rs.getInt("Id"));
                contract.setPersonelId(rs.getInt("PersonelId"));
                contract.setSifre(rs.getString("Sifre"));
                contract.setYetkiId(rs.getInt("YetkiId"));
            }
            sorgu.close();
            baglanti.close();
        } catch (SQLException e) {
        }
        return contract;
    }

    public AccountsContract GetYetkiId(int PersonelId) {
        AccountsContract contract = new AccountsContract();
        List<AccountsContract> listele = new ArrayList<AccountsContract>();
        Connection baglanti = getConnection();
        try {
            Statement sorgu = baglanti.createStatement();
            ResultSet rs = sorgu.executeQuery("SELECT * FROM accounts  WHERE PersonelId=" + PersonelId + "");
            while (rs.next()) {
                contract.setId(rs.getInt("Id"));
                contract.setPersonelId(rs.getInt("PersonelId"));

                contract.setYetkiId(rs.getInt("YetkiId"));
            }
            sorgu.close();
            baglanti.close();
        } catch (SQLException e) {
        }
        return contract;
    }

    @Override
    public List<AccountsContract> GetAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AccountsContract Delete(AccountsContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(AccountsContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AccountsContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
