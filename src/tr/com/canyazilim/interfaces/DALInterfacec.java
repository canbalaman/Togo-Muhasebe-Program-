package tr.com.canyazilim.interfaces;

import java.util.List;

public interface DALInterfacec<T> {

    public void Insert(T entity);//Veri Tabanı kay ekleme

    public List<T> GetAll();// 

    public T Delete(T entity);// Veri Tabanı Silme

    public void Update(T entity);//Veri Tabanı Ekleme

    public List<T> GetById(int id);

}
