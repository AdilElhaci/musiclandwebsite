
package converter;

import dao.DilDao;
import entity.Dil;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter (value="dilConverter")
public class DilConverter implements Converter{

    private DilDao dildao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getDildao().find_Id(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
        Dil dil = (Dil) arg2;
        return dil.getDil_id().toString();
    }

    public DilDao getDildao() {
        if(this.dildao == null)
            this.dildao = new DilDao();
        return dildao;
    }

  


    
}
