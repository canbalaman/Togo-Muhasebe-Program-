package tr.com.canyazilim.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tr.com.canyazilim.complex.enties.SatisContractComplex;
import tr.com.canyazilim.core.ObjectHelper;
import tr.com.canyazilim.enties.SatisContract;
import tr.com.canyazilim.interfaces.DALInterfacec;

public class SatisDAL extends ObjectHelper implements DALInterfacec<SatisContract> {

    @Override
    public void Insert(SatisContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO satis (UrunId, MusteriId, Tarih, Adet, PersonelId) VALUES("
                    + entity.getUrunId() + "," + entity.getMusteriId() + ",'" + entity.getTarih() + "'," + entity.getAdet() + "," + entity.getPersonelId() + ")");

            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<SatisContractComplex> GetAllSatis() {
        List<SatisContractComplex> datacontract = new ArrayList<SatisContractComplex>();

        Connection conn = getConnection();
        SatisContractComplex contract;
        Statement statement;
        try {
            statement = conn.createStatement();
            ResultSet resultset = statement.executeQuery("SELECT satis.Id, personel.Adisoyadi ,musteri.AdiSoyadi,Adi ,Adet ,satis.Tarih FROM satis "
                    + "LEFT JOIN musteri ON satis.MusteriId=musteri.Id "
                    + "LEFT JOIN urunler on satis.UrunId =urunler.Id "
                    + "LEFT JOIN personel on satis.PersonelId=Personel.id ORDER BY satis.id DESC");
            while (resultset.next()) {                
                contract =new SatisContractComplex();
                contract.setId(resultset.getInt("satis.Id"));
                contract.setMusteriAdi(resultset.getString("musteri.AdiSoyadi"));
                contract.setPersonelAdi(resultset.getString("personel.Adisoyadi"));
                contract.setTarih(resultset.getString("satis.Tarih"));
                contract.setUrunAdi(resultset.getString("Adi"));
                contract.setAdet(resultset.getInt("Adet"));
                datacontract.add(contract);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SatisDAL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datacontract;
    }

    @Override
    public List<SatisContract> GetAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SatisContract Delete(SatisContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(SatisContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SatisContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
