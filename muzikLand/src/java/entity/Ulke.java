
package entity;

import java.sql.Date;
import java.util.Objects;
import org.hibernate.validator.constraints.NotEmpty;

public class Ulke {
    private Long ulke_id;
    @NotEmpty(message="Ulke name bos birakilamaz!")
    private String ulke;
    private Date last_update;

    public Long getUlke_id() {
        return ulke_id;
    }

    public void setUlke_id(Long ulke_id) {
        this.ulke_id = ulke_id;
    }
    public String getUlke() {
        return ulke;
    }

    public void setUlke(String ulke) {
        this.ulke = ulke;
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
        hash = 79 * hash + Objects.hashCode(this.ulke_id);
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
        final Ulke other = (Ulke) obj;
        if (!Objects.equals(this.ulke_id, other.ulke_id)) {
            return false;
        }
        return true;
    }

    public Ulke() {
    }

    @Override
    public String toString() {
        return "Ulke{" + "ulke_id=" + ulke_id + ", ulke=" + ulke + ", last_update=" + last_update + '}';
    }
    
    
    
}
