
package controller;

import dao.UlkeDao;
import entity.Ulke;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class UlkeControllers extends PagintionControllers implements Serializable{
    private Ulke ulkeSehir;
    private UlkeDao udao;
    private List<Ulke> ulist;

    public UlkeControllers() {
        setDao(this.getUdao());
        setSayfa_Adet(2);
        setSayfa_Size(8);
    }
    public void create(){
        this.getUdao().create(this.getUlkeSehir());
        this.ulkeSehir = new Ulke();
    }
    public void updatefrom(Ulke ulkeSehir){
       this.ulkeSehir = ulkeSehir;
    }
    public void update(){
        this.getUdao().update(this.getUlkeSehir());
        this.ulkeSehir = new Ulke();
    }
    public void confirmDelete(Ulke ulkeSehir){
        this.ulkeSehir = ulkeSehir;
    }
    public void delete(){
        this.getUdao().delete(this.getUlkeSehir());
        this.ulkeSehir = new Ulke();
    }
    public void clearForm() {
        this.ulkeSehir = new Ulke();
    }
    public List<Ulke> getUlist() {
        this.ulist = this.getUdao().findAll(getPage(),getSayfa_Size(),getSearchTerimi());
        return ulist;
    }
    public List<Ulke> allUlke() {
        return this.getUdao().findAll(1,this.getUdao().findCount(getSearchTerimi()),getSearchTerimi());
    }
    public Ulke getUlkeSehir() {
        if(ulkeSehir == null)
            this.ulkeSehir = new Ulke();
        return ulkeSehir;
    }

    public UlkeDao getUdao() {
        if(this.udao == null)
            this.udao = new UlkeDao();
        return udao;
    }    
}
