
package tr.com.canyazilim.enties;

public class SehirlerContract {
    private int id;
    private int sehirId;
    private String sehir;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSehirId() {
        return sehirId;
    }

    public void setSehirId(int sehirId) {
        this.sehirId = sehirId;
    }

    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }

    @Override
    public String toString() {
        return sehir;
    }
    
}
