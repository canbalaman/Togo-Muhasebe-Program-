package tr.com.canyazilim.enties;

public class MusteriContract {

    private int id;
    private String adiSoyadi;
    private String telefonu;
    private String adres;
    private int sehirId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdiSoyadi() {
        return adiSoyadi;
    }

    public void setAdiSoyadi(String adiSoyadi) {
        this.adiSoyadi = adiSoyadi;
    }

    public String getTelefonu() {
        return telefonu;
    }

    public void setTelefonu(String telefonu) {
        this.telefonu = telefonu;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getSehirId() {
        return sehirId;
    }

    public void setSehirId(int sehirId) {
        this.sehirId = sehirId;
    }
     public Object[] getVeriler() {
        Object[] veriler = {id,adiSoyadi,telefonu,adres,sehirId};
        return veriler;
    }

    @Override
    public String toString() {
        return adiSoyadi;
    }

}
