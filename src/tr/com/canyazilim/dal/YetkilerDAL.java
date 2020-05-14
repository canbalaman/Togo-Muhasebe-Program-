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
import tr.com.canyazilim.enties.PersonelContract;
import tr.com.canyazilim.enties.YetkilerContract;
import tr.com.canyazilim.interfaces.DALInterfacec;

public class YetkilerDAL extends ObjectHelper implements DALInterfacec<YetkilerContract> {

    @Override
    public void Insert(YetkilerContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Yetkiler (Adi) VALUES('" + entity.getAdı() + "' )");
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<YetkilerContract> GetAll() {

        List<YetkilerContract> datacontract = new ArrayList<YetkilerContract>();
        Connection connection = getConnection();
        YetkilerContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Yetkiler");
            while (resultSet.next()) {
                contract = new YetkilerContract();
                contract.setId(resultSet.getInt("Id"));
                contract.setAdı(resultSet.getString("Adı"));

                datacontract.add(contract);

            }
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datacontract;
    }

    @Override
    public YetkilerContract Delete(YetkilerContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(YetkilerContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<YetkilerContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
