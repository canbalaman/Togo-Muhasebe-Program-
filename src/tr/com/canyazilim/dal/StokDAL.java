package tr.com.canyazilim.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tr.com.canyazilim.complex.enties.StokContractComplex;
import tr.com.canyazilim.complex.enties.StokContractTotalComplex;
import tr.com.canyazilim.core.ObjectHelper;
import tr.com.canyazilim.enties.StokContract;
import tr.com.canyazilim.enties.UrunlerContract;
import tr.com.canyazilim.interfaces.DALInterfacec;

public class StokDAL extends ObjectHelper implements DALInterfacec<StokContract> {
    
    @Override
    public void Insert(StokContract entity) {
        
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Stok (PersonelId ,UrunId,Tarih,Adet) VALUES(" + entity.getPersonelId() + "," + entity.getUrunId() + ",'" + entity.getTarih() + "'," + entity.getAdet() + ")");
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<StokContractComplex> GetAllStok() {
        List<StokContractComplex> datacontract = new ArrayList<StokContractComplex>();
        Connection connection = getConnection();
        StokContractComplex contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT stok.Id, Adisoyadi , Adi ,Adet ,stok.Tarih FROM stok "
                    + "LEFT JOIN urunler on stok.UrunId =urunler.Id "
                    + "LEFT JOIN personel on stok.PersonelId=Personel.id ORDER BY stok.id DESC");
            while (resultSet.next()) {
                contract = new StokContractComplex();
                contract.setId(resultSet.getInt("Id"));
                contract.setPersonelAdi(resultSet.getString("AdiSoyadi"));
                contract.setUrunAdi(resultSet.getString("Adi"));
                contract.setAdet(resultSet.getInt("Adet"));
                contract.setTarih(resultSet.getString("stok.Tarih"));
                
                datacontract.add(contract);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return datacontract;
    }
    public List<StokContractTotalComplex> GetTotalStok() {
        List<StokContractTotalComplex> datacontract = new ArrayList<StokContractTotalComplex>();
        Connection connection = getConnection();
        StokContractTotalComplex contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT SUM(Adet) as toplam, stok.Id, Adisoyadi , Adi ,Adet ,stok.Tarih FROM stok "
                    + "LEFT JOIN urunler on stok.UrunId =urunler.Id "
                    + "LEFT JOIN personel on stok.PersonelId=Personel.id GROUP BY UrunId");
            while (resultSet.next()) {
                contract = new StokContractTotalComplex();
                contract.setId(resultSet.getInt("Id"));
                contract.setPersonelAdi(resultSet.getString("AdiSoyadi"));
                contract.setUrunAdi(resultSet.getString("Adi"));
                contract.setAdet(resultSet.getInt("Adet"));
                contract.setTarih(resultSet.getString("stok.Tarih"));
                contract.setToplam(resultSet.getInt("toplam"));
                
                datacontract.add(contract);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return datacontract;
    }
    
    @Override
    public List<StokContract> GetAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public StokContract Delete(StokContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void Update(StokContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<StokContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
