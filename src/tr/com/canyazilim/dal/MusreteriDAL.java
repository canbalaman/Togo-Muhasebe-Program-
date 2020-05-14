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
import tr.com.canyazilim.enties.MusteriContract;
import tr.com.canyazilim.interfaces.DALInterfacec;

public class MusreteriDAL extends ObjectHelper implements DALInterfacec<MusteriContract> {

    @Override
    public void Insert(MusteriContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO musteri (AdiSoyadi ,Telefon, Adres, SehirId ) VALUES('"
                    + entity.getAdiSoyadi()
                    + "','"
                    + entity.getTelefonu()
                    + "','"
                    + entity.getAdres()
                    + "',"
                    + entity.getSehirId()
                    + ")");
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<MusteriContract> GetAll() {
        List<MusteriContract> datacontract = new ArrayList<MusteriContract>();
        Connection connection = getConnection();
        MusteriContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Musteri");
            while (resultSet.next()) {
                contract = new MusteriContract();
                contract.setId(resultSet.getInt("Id"));
                contract.setAdiSoyadi(resultSet.getString("AdiSoyadi"));
                contract.setAdres(resultSet.getString("Adres"));
                contract.setSehirId(resultSet.getInt("SehirId"));
                contract.setTelefonu(resultSet.getString("Telefon"));

                datacontract.add(contract);

            }
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datacontract;

    }

    

    @Override
    public MusteriContract Delete(MusteriContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(MusteriContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MusteriContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
