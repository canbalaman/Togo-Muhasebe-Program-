package tr.com.canyazilim.enties;

public class AccountsContract {

    private int id;
    private int yetkiId;
    private int PersonelId;
    private String sifre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYetkiId() {
        return yetkiId;
    }

    public void setYetkiId(int yetkiId) {
        this.yetkiId = yetkiId;
    }

    public int getPersonelId() {
        return PersonelId;
    }

    public void setPersonelId(int PersonelId) {
        this.PersonelId = PersonelId;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    @Override
    public String toString() {
        return id + " " + PersonelId + " " + sifre + " " + yetkiId;
    }

}
