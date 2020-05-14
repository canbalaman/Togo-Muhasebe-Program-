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
import tr.com.canyazilim.enties.UrunlerContract;
import tr.com.canyazilim.interfaces.DALInterfacec;

public class UrunlerDAL extends ObjectHelper implements DALInterfacec<UrunlerContract> {

    @Override
    public void Insert(UrunlerContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Urunler(Adi,KategoriId,Tarih,Fiyat)"
                    + "VALUES('" 
                    + entity.getAdi() 
                    + "'," 
                    + entity.getKategoriId() 
                    + ",'" 
                    + entity.getTarih() 
                    + "'," 
                    + entity.getFiyat() 
                    + ") ");
            statement.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(UrunlerDAL.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
     public List<UrunlerContract> GetAll() {
        List<UrunlerContract> datacontract = new ArrayList<UrunlerContract>();
        Connection connection = getConnection();
        UrunlerContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM urunler");
            while (resultSet.next()) {
                contract = new UrunlerContract();
                contract.setId(resultSet.getInt("Id"));
                contract.setAdi(resultSet.getString("Adi"));
                contract.setKategoriId(resultSet.getInt("KategoriId"));
                contract.setTarih(resultSet.getString("Tarih"));
                

                datacontract.add(contract);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datacontract;
     }
    @Override
    public UrunlerContract Delete(UrunlerContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(UrunlerContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<UrunlerContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
