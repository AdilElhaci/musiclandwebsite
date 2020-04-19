
package converter;

import dao.SarkiDao;
import entity.Sarki;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter (value="sarkiConverter")
public class SarkiConverter implements Converter{

    private SarkiDao sarkiDao;
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getSarkiDao().findSarki(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Sarki sarki = (Sarki) arg2;
        return sarki.getSarki_id().toString();
    }

    public SarkiDao getSarkiDao() {
        if(this.sarkiDao == null)
            this.sarkiDao = new SarkiDao();
        return sarkiDao;
    }
    
    
    
}
