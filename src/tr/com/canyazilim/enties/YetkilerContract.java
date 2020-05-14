package tr.com.canyazilim.enties;

public class YetkilerContract {

    private int id;
    private String adi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdı() {
        return adi;
    }

    public void setAdı(String adı) {
        this.adi = adı;
    }

    @Override
    public String toString() {
        return adi;
    }

}
