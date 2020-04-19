
package converter;

import dao.SarkiciDao;
import entity.Sarkici;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
@FacesConverter (value = "sarkiciConverter" )
public class SarkiciConverter implements Converter{

    private SarkiciDao sarkiciDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getSarkiciDao().findSarkici(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Sarkici sarkici = (Sarkici)arg2;
        return sarkici.getSarkici_id().toString();
    }

    public SarkiciDao getSarkiciDao() {
        if(this.sarkiciDao == null)
            this.sarkiciDao = new SarkiciDao();
        return sarkiciDao;
    }
    
}
