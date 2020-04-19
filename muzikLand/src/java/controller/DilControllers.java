
package controller;

import dao.DilDao;
import entity.Dil;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
@Named
@SessionScoped
public class DilControllers extends PagintionControllers implements Serializable{
  private DilDao dildao;
  private List<Dil> dlist;
  private Dil dil;

    public DilControllers() {
        setDao(this.getDildao());
        setSayfa_Adet(2);
        setSayfa_Size(8);
    }
  
  public void delete()
  {
      this.dildao.delete(this.dil);
      this.dil=new Dil();
  }
  
  public void deleteConfirm(Dil dil){
      this.dil=dil;
  }
  
  public void clearForm()
  {
      this.dil=new Dil();
  }
  public void creat()
  {
      this.dildao.creat(dil);
      this.dil=new Dil();
  }
   public void updateForm(Dil dil){
       this.dil=dil;
   }
   public void update(){
       this.dildao.update(this.dil);
       this.dil=new Dil();
   }
    public DilDao getDildao() {
        if(this.dildao==null)
            this.dildao=new DilDao();
        return dildao;
    }

    public void setDildao(DilDao dildao) {
        this.dildao = dildao;
    }

    public List<Dil> getDlist() {
        this.dlist=this.getDildao().findAll(getPage(),getSayfa_Size(),getSearchTerimi());
        return dlist;
    }
    public List<Dil> allDil() {
        return this.getDildao().findAll(1,this.getDildao().findCount(getSearchTerimi()),getSearchTerimi());
    }
    public void setDlist(List<Dil> dlist) {
        this.dlist = dlist;
    }
    
    public Dil getDil() {
        if(this.dil==null){
            this.dil=new Dil();
        }
        return dil;
    }

    public void setDil(Dil dil) {
        this.dil = dil;
    }
  
}
