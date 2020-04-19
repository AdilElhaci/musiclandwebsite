
package entity;

import java.sql.Date;
import java.util.Objects;

public class Extra {
    
    private Long id;
    private Sarki sarki;
    private Ulke ulke;
    private float sarki_puan;
    private float sarki_sure;
    private String sarkici_sirketi;
    private Date last_update;

    public Extra() {
    }

    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sarki getSarki() {
        return sarki;
    }

    public void setSarki(Sarki sarki) {
        this.sarki = sarki;
    }

    public float getSarki_puan() {
        return sarki_puan;
    }

    public void setSarki_puan(float sarki_puan) {
        this.sarki_puan = sarki_puan;
    }

    public float getSarki_sure() {
        return sarki_sure;
    }

    public void setSarki_sure(float sarki_sure) {
        this.sarki_sure = sarki_sure;
    }

    public String getSarkici_sirketi() {
        return sarkici_sirketi;
    }

    public void setSarkici_sirketi(String sarkici_sirketi) {
        this.sarkici_sirketi = sarkici_sirketi;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public void setUlke(Ulke ulke) {
        this.ulke = ulke;
    }

    public Ulke getUlke() {
        return ulke;
    }

    @Override
    public String toString() {
        return "Extra{" + "id=" + id + ", sarki=" + sarki + ", ulke=" + ulke + ", sarki_puan=" + sarki_puan + ", sarki_sure=" + sarki_sure + ", sarkici_sirketi=" + sarkici_sirketi + ", last_update=" + last_update + '}';
    }
    
    
}
