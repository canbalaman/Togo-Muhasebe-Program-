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
import tr.com.canyazilim.interfaces.DALInterfacec;
import tr.com.canyazilim.enties.KategoriContract;

public class KategoriDAL extends ObjectHelper implements DALInterfacec<KategoriContract> {

    public static Object getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Insert(KategoriContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO Kategori (Adi ,ParentId) VALUES('" + entity.getAdi() + "'," + entity.getParentId() + ")");
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<KategoriContract> GetAll() {
        List<KategoriContract> datacontract = new ArrayList<KategoriContract>();
        Connection connection = getConnection();
        KategoriContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori");
            while (resultSet.next()) {
                contract = new KategoriContract();
                contract.setId(resultSet.getInt("Id"));
                contract.setAdi(resultSet.getString("Adi"));
                contract.setParentId(resultSet.getInt("ParentId"));

                datacontract.add(contract);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return datacontract;
    }

    public List<KategoriContract> GetAllParentId() {
        List<KategoriContract> datacontract = new ArrayList<KategoriContract>();
        Connection connection = getConnection();
        KategoriContract contract;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori WHERE parentId=0");
            while (resultSet.next()) {
                contract = new KategoriContract();
                contract.setId(resultSet.getInt("Id"));
                contract.setAdi(resultSet.getString("Adi"));
                contract.setParentId(resultSet.getInt("ParentId"));

                datacontract.add(contract);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return datacontract;
    }

    @Override
    public KategoriContract Delete(KategoriContract entity) {
        return null;
    }

    @Override
    public void Update(KategoriContract entity) {
        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE Kategori SET Adi='" + entity.getAdi() + "',ParentId=" + entity.getParentId() + " WHERE id =" + entity.getId() + "");
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<KategoriContract> GetById(int id) {
        return null;
    }

    public List<KategoriContract> GetSearchKategori(String kategoriAdi) {
        List<KategoriContract> datacontract = new ArrayList<KategoriContract>();

        Connection connection = getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT *FROM Kategori WHERE Adi LIKE '" + "%" + kategoriAdi + "%" + "'");

            while (resultSet.next()) {
                KategoriContract contract = new KategoriContract();

                contract.setAdi(resultSet.getString("Adi"));
                contract.setParentId(resultSet.getInt("ParentId"));

                datacontract.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datacontract;

    }

}
