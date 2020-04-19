
package navigation;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped // bir tane quest bizim içinde yeterli

public class NavigationControllers implements Serializable {

    public String pageA( String page){
        return "/site/admin/"+page+"/"+page+"?faces-redirect=true";
    }
    
    public String pageK( String page){
        return "/site/kullanici/"+page+"/"+page+"?faces-redirect=true";
    }

}
