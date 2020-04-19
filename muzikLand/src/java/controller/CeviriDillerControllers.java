
package controller;

import dao.CeviriDillerDao;
import dao.SarkiDao;
import entity.CeviriDiller;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named 
@SessionScoped
public class CeviriDillerControllers extends PagintionControllers implements Serializable{
  
    private CeviriDillerDao cevriDao;
    private List<CeviriDiller> cevrilist; 
    private CeviriDiller CeviriDiller;

    @Inject
    private SarkiControllers sarkicontrollers;
    
    @Inject
    private DilControllers dilcontrollers;

    public CeviriDillerControllers() {
        setDao(this.getCevriDao());
        setSayfa_Adet(2);
        setSayfa_Size(8);
    }

    
    
    public DilControllers getDilcontrollers() {
        return dilcontrollers;
    }

    public void setDilcontrollers(DilControllers dilcontrollers) {
        this.dilcontrollers = dilcontrollers;
    }
    
    public SarkiControllers getSarkicontrollers() {
        if(this.sarkicontrollers==null)
            this.sarkicontrollers=new SarkiControllers();
        return sarkicontrollers;
    }

    public void setSarkicontrollers(SarkiControllers sarkicontrollers) {
        this.sarkicontrollers = sarkicontrollers;
    }

    public void updateForm(CeviriDiller ceviri){
        this.CeviriDiller=ceviri;
    }
    public void delete()
    {
        this.cevriDao.delete(this.CeviriDiller);
        this.CeviriDiller=new CeviriDiller();
    }
    public void deleteForm(CeviriDiller cevridil)
    {
        this.CeviriDiller=cevridil;
    }
    public void clearForm()
    {
        this.CeviriDiller=new CeviriDiller();
    }
    public void update()
    {
        this.getCevriDao().update(this.CeviriDiller);
        this.CeviriDiller=new CeviriDiller();
    }
    public void create()
    {
        this.getCevriDao().create(this.getCeviriDiller());
        this.CeviriDiller=new CeviriDiller();
    }
    public CeviriDillerDao getCevriDao() {
        if(cevriDao==null)
            this.cevriDao=new CeviriDillerDao();
        return cevriDao;
    }
    public void setCevriDao(CeviriDillerDao cevriDao) {
        this.cevriDao = cevriDao;
    }
    public List<CeviriDiller> getCevrilist() {
        this.cevrilist=this.getCevriDao().findAll(getPage(),getSayfa_Size(),getSearchTerimi());       
        return cevrilist;
    }
    public List<CeviriDiller> allCevrilist() {
        return this.getCevriDao().findAll(1,this.getCevriDao().findCount(getSearchTerimi()),getSearchTerimi());
    }
    public void setCevrilist(List<CeviriDiller> cevrilist) {
        this.cevrilist = cevrilist;
    }
    public CeviriDiller getCeviriDiller() {
        if(this.CeviriDiller==null)
            this.CeviriDiller=new CeviriDiller();
        return CeviriDiller;
    }
    public void setCeviriDiller(CeviriDiller CeviriDiller) {
        this.CeviriDiller = CeviriDiller;
    }
}
