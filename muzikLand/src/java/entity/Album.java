package entity;

import java.sql.Date;
import java.util.Objects;
import org.hibernate.validator.constraints.NotEmpty;

public class Album {

    private Long album_Id;
    @NotEmpty(message="Bu alan bos birakilamaz...")
    private String name;
    private Date album_LastUpdate;
    
    
    public Album() {
    }


    public Long getAlbum_Id() {
        return album_Id;
    }

    public void setAlbum_Id(Long album_Id) {
        this.album_Id = album_Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAlbum_LastUpdate() {
        return album_LastUpdate;
    }

    public void setAlbum_LastUpdate(Date album_LastUpdate) {
        this.album_LastUpdate = album_LastUpdate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.album_Id);
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
        final Album other = (Album) obj;
        if (!Objects.equals(this.album_Id, other.album_Id)) {
            return false;
        }
        return true;
    }


}
