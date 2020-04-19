package entity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.NotEmpty;

public class Sarkici {

    private Long sarkici_id;
    @NotEmpty(message="Name alani bos birakilamaz...")
    private String name;
    @NotEmpty(message="Surname alani bos birakilamaz...")
    private String last_name;
    private int yas;
    private List<Album> sarkiciAlbum;
    private ResimDocument sarkici_resim;
    private Date last_update;

    private Ulke ulke;
    
    public Sarkici() {
        sarkici_resim = new ResimDocument();
    }

    public Long getSarkici_id() {
        return sarkici_id;
    }

    public void setSarkici_id(Long sarkici_id) {
        this.sarkici_id = sarkici_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public Ulke getUlke() {
        return ulke;
    }

    public void setUlke(Ulke ulke) {
        this.ulke = ulke;
    }

    public void setSarkiciAlbum(List<Album> sarkiciAlbum) {
        this.sarkiciAlbum = sarkiciAlbum;
    }

    public List<Album> getSarkiciAlbum() {
        return sarkiciAlbum;
    }
    
    
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.sarkici_id);
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
        final Sarkici other = (Sarkici) obj;
        if (!Objects.equals(this.sarkici_id, other.sarkici_id)) {
            return false;
        }
        return true;
    }

    public void setSarkici_resim(ResimDocument sarkici_resim) {
        this.sarkici_resim = sarkici_resim;
    }

    public ResimDocument getSarkici_resim() {
        return sarkici_resim;
    }    

}
