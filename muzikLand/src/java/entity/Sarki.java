package entity;

import java.sql.Date;
import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.NotEmpty;

public class Sarki {

    private Long sarki_id;
    @NotEmpty(message="1.)Bu alan bos birakilamaz!")    
    private String tmp_name;
    private String description;
    
    private int yil = 2019;
    private Dil sarkiDili;
    private Album album;
    private List<Category> sarkiCategory;
    private List<Sarkici> sarkiSarkici;
    private SarkiDocument sarkiDoc;
    private Date last_update;

    public Sarki() {
    }
    
     public List<Category> getSarkiCategory() {
        return sarkiCategory;
    }

    public List<Sarkici> getSarkiSarkici() {
        return sarkiSarkici;
    }
    
    public Long getSarki_id() {
        return sarki_id;
    }

    public void setSarki_id(Long sarki_id) {
        this.sarki_id = sarki_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYil() {
        return yil;
    }

    public void setYil(int yil) {
        this.yil = yil;
    }

    public Date getLast_update() {
        return last_update;
    }

    public void setLast_update(Date last_update) {
        this.last_update = last_update;
    }

    public Dil getSarkiDili() {
        return sarkiDili;
    }

    public void setSarkiDili(Dil sarkiDili) {
        this.sarkiDili = sarkiDili;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setSarkiCategory(List<Category> sarkiCategory) {
        this.sarkiCategory = sarkiCategory;
    }

   
    
    public void setSarkiSarkici(List<Sarkici> sarkiSarkici) {
        this.sarkiSarkici = sarkiSarkici;
    }

    public void setTmp_name(String tmp_name) {
        this.tmp_name = tmp_name;
    }

    public String getTmp_name() {
        return tmp_name;
    }

    public void setSarkiDoc(SarkiDocument sarkiDoc) {
        this.sarkiDoc = sarkiDoc;
    }

    public SarkiDocument getSarkiDoc() {
        return sarkiDoc;
    }
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.sarki_id);
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
        final Sarki other = (Sarki) obj;
        if (!Objects.equals(this.sarki_id, other.sarki_id)) {
            return false;
        }
        return true;
    }


    
}
