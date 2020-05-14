
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
import tr.com.canyazilim.enties.SehirlerContract;
import tr.com.canyazilim.interfaces.DALInterfacec;

public class SehirlerDAL extends ObjectHelper implements DALInterfacec<SehirlerContract>{

    @Override
    public void Insert(SehirlerContract entity) {
         
    }

    @Override
    public List<SehirlerContract> GetAll() {
      
        List<SehirlerContract> datacontract = new ArrayList<SehirlerContract>();
        Connection connection = getConnection();
        SehirlerContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sehirler");
            while (resultSet.next()) {
                contract = new SehirlerContract();
                contract.setId(resultSet.getInt("Id"));
                contract.setSehirId(resultSet.getInt("sehirid"));
                contract.setSehir(resultSet.getString("sehir"));
                datacontract.add(contract);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(KategoriDAL.class.getName()).log(Level.SEVERE, null, ex);
        }

        return datacontract;
    }

    @Override
    public SehirlerContract Delete(SehirlerContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(SehirlerContract entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SehirlerContract> GetById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
