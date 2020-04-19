
package controller;

import dao.KullaniciDao;
import entity.Kullanici;
import java.io.Serializable;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
@Named
@ViewScoped
public class AdminControllers extends PagintionControllers implements Serializable{
    private Kullanici admin;
    private List<Kullanici> adminList;
    private KullaniciDao adminDao;
    private int sonuc; // database bize return edeceği sonucu gostermek icin
    
    @Inject
    private UyelikYapanlarControllers kullanici;
    
    public AdminControllers() {     
        this.setDao(new KullaniciDao());
        setSayfa_Size(6); 
        this.setYetki_saviye(1);
        this.sonuc = -1;
    }  
        public void updateForm(Kullanici kullanici){
        this.admin = kullanici;
        this.sonuc = -1;        
    }    
    public void clearForm(){
        this.admin = new Kullanici();
        this.sonuc = -1;
    }
    public void create(){
        this.sonuc = this.getAdminDao().create(this.getAdmin());
        this.admin = new Kullanici();        
    }
    public void update(){
       this.getKullanici().setUpdateToKullanici(this.getAdmin());       
        this.getKullanici().update2();
        this.sonuc = this.getKullanici().getSonuc();
       if(this.sonuc == 1){           
        this.admin = new Kullanici(); 
       }
       else
           this.sonuc = 0;
    }
    public String delete(){      
            this.getAdminDao().delete(this.getAdmin());         
             return "/logout.xhtml?faces-redirect=true";      
    }
    public void deleteFromSuper(Kullanici oneAdmin){
         this.getAdminDao().delete(oneAdmin);
    }
     public List<Kullanici> getAdminList() {
         this.adminList = this.getAdminDao().findAll(getPage(), getSayfa_Size(),1,getSearchTerimi());                 
         return adminList;
         
    }
     
    public Kullanici getAdmin() {
        if(this.admin == null)
            this.admin = new  Kullanici();
        return admin;
    }

    public KullaniciDao getAdminDao() {
        if(this.adminDao == null)
            this.adminDao = new KullaniciDao();
        return adminDao;
    }

    public int getSonuc() {
        return sonuc;
    }

    public void setSonuc(int sonuc) {
        this.sonuc = sonuc;
    }
    public UyelikYapanlarControllers getKullanici() {
        return kullanici;
    }
    
}
   
    

