package entity;

import java.sql.Date;
import java.util.Objects;

public class Dil {

    private Long dil_id;
    private String dil_name;
    private Date last_update;

    public Dil() {
    }

    public Dil(Long dil_id, String dil_name, Date last_update) {
        this.dil_id = dil_id;
        this.dil_name = dil_name;
        this.last_update = last_update;
    }

    public Long getDil_id() {
        return dil_id;
    }

    public void setDil_id(Long dil_id) {
        this.dil_id = dil_id;
    }

    public String getDil_name() {
        return dil_name;
    }

    public void setDil_name(String dil_name) {
        this.dil_name = dil_name;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.dil_id);
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
        final Dil other = (Dil) obj;
        if (!Objects.equals(this.dil_id, other.dil_id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Dil{" + "dil_id=" + dil_id + ", dil_name=" + dil_name + ", last_update=" + last_update + '}';
    }

}
