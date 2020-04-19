
package entity;

import java.sql.Date;
import java.util.Objects;

public class CeviriDiller {
    
    private Long id;
    private String sarki_Text;
    private Date last_update;
    private Sarki sarki;
    private Dil dil;

    public CeviriDiller() {
    }
    
    public Dil getDil() {
        return dil;
    }

    public void setDil(Dil dil) {
        this.dil = dil;
    }

    public Sarki getSarki() {
        return sarki;
    }

    public void setSarki(Sarki sarki) {
        this.sarki = sarki;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSarki_Text() {
        return sarki_Text;
    }

    public void setSarki_Text(String sarki_Text) {
        this.sarki_Text = sarki_Text;
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
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final CeviriDiller other = (CeviriDiller) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
