package entity;

import java.sql.Date;
import java.util.Objects;

public class KullaniciSarkiListesi {
    
    private Long id;
    private Long kullanici_id;
    private Sarki sarki;
    private Date last_update;

    public KullaniciSarkiListesi() {
    }
     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setKullanici_id(Long kullanici_id) {
        this.kullanici_id = kullanici_id;
    }

    public Long getKullanici_id() {
        return kullanici_id;
    }
    
    public Sarki getSarki() {
        return sarki;
    }

    public void setSarki(Sarki sarki) {
        this.sarki = sarki;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final KullaniciSarkiListesi other = (KullaniciSarkiListesi) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
