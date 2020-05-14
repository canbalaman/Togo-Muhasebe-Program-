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
import tr.com.canyazilim.enties.KategoriContract;
import tr.com.canyazilim.enties.PersonelContract;
import tr.com.canyazilim.interfaces.DALInterfacec;

public class PersonelDAL extends ObjectHelper implements DALInterfacec<PersonelContract> {
    
    @Override
    public void Insert(PersonelContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO PERSONEL (AdiSoyadi ,Email) VALUES('"
                    + entity.getAdiSoyadi()
                    + "','"
                    + entity.getEmail()
                    + "')");
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public List<PersonelContract> GetAll() {
        List<PersonelContract> datacontract = new ArrayList<PersonelContract>();
        Connection connection = getConnection();
        PersonelContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Personel");
            while (resultSet.next()) {
                contract = new PersonelContract();
                contract.setId(resultSet.getInt("Id"));
                contract.setAdiSoyadi(resultSet.getString("AdiSoyadi"));
                contract.setEmail(resultSet.getString("Email"));
                
                datacontract.add(contract);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datacontract;
    }
    
    @Override
    public PersonelContract Delete(PersonelContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void Update(PersonelContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<PersonelContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
