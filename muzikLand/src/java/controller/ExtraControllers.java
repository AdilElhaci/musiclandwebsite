
package controller;

import dao.ExtraDao;
import entity.Extra;
import entity.Sarki;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ExtraControllers extends PagintionControllers implements Serializable{
    
    private Extra extra;
    private Extra ozet; // kullanici tarafinda her sarkinin tüm bilgilerini yazdirmak için
    private List<Extra> exList;
    private ExtraDao extraDao;
    private int sonuc;

    @Inject
    private SarkiControllers sarkiContriller;
    
    @Inject
    private UlkeControllers ulkeList;

    public ExtraControllers() {
        setDao(this.getExtraDao());
        setSayfa_Adet(2);
        setSayfa_Size(8);
    }
    
    public void updateform(Extra extra){
        this.extra = extra;
        this.sonuc = -1;
    }
    public void confirmDelete(Extra extra){
        this.extra = extra;
        this.sonuc = -1;
    }
     public void clearForm() {
        this.extra = new Extra();
        this.sonuc = -1;
    }
    public void delete(){
        this.sonuc = this.getExtraDao().delete(this.getExtra());
        this.extra = new Extra();
    }
    public void create(){
        this.sonuc = this.getExtraDao().create(this.getExtra());
        this.extra = new Extra();
    }
    public void update(){
       this.sonuc = this.getExtraDao().update(this.getExtra());
        this.extra = new Extra();
    }
    public Extra getExtra() {
        if(this.extra == null)
            this.extra = new Extra();
        return extra;
    }
    
    public void sarkiOzeti(Sarki sarki){
        Extra bilgi = this.getExtraDao().find(sarki.getSarki_id());
        this.ozet = bilgi;
    }
    public Extra sarkiOzeti(){
        return ozet;
    }
    public List<Extra> getExList() {
        this.exList = this.getExtraDao().findAll(getPage(),getSayfa_Size(),getSearchTerimi());
        return exList;
    }
    public List<Extra> allExtra(){
        return this.getExtraDao().findAll(1,this.getExtraDao().findCount(getSearchTerimi()),getSearchTerimi());
    }
    public void setExList(List<Extra> exList) {
        this.exList = exList;
    }

    
    public ExtraDao getExtraDao() {
        if(this.extraDao == null)
            this.extraDao = new ExtraDao();
        return extraDao;
    }

    public SarkiControllers getSarkiContriller() {
        return sarkiContriller;
    }

    public void setSarkiContriller(SarkiControllers sarkiContriller) {
        this.sarkiContriller = sarkiContriller;
    }

    public int getSonuc() {
        return sonuc;
    }

    public void setSonuc(int sonuc) {
        this.sonuc = sonuc;
    }

    
    public UlkeControllers getUlkeList() {
        return ulkeList;
    }
    
    
    
    
    
}
