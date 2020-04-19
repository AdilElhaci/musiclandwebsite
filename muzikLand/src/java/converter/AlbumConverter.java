
package converter;

import dao.AlbumDao;
import entity.Album;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter (value ="albumConverter")
public class AlbumConverter implements Converter{

    private  AlbumDao albumDAo;
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getAlbumDAo().find_albumId(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Album album = (Album) arg2;
        return album.getAlbum_Id().toString();
    }

    public AlbumDao getAlbumDAo() {
        if(this.albumDAo == null)
            this.albumDAo = new AlbumDao();
        return albumDAo;
    }
    
}
