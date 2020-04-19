
package converter;

import dao.UlkeDao;
import entity.Ulke;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter (value = "ulkeConverter")
public class UlkeConverter implements Converter{
    private UlkeDao udao;
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getUdao().findulkeBy_id(Long.valueOf(value));
        
    }
    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Ulke ulke = (Ulke) arg2;
        return ulke.getUlke_id().toString();
        
    }
    public UlkeDao getUdao() {
        if(this.udao == null)
            this.udao = new UlkeDao();
        return udao;
    }
    
}
