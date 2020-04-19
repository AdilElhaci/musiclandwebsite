
package controller;

import dao.KullaniciDao;
import entity.Kullanici;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import navigation.NavigationControllers;


@Named
@SessionScoped
public class KullaniciControllers implements Serializable{
    private String user;    // girilecek username
    private String pass;    // girilecek password
    private Kullanici kullanici;
    private KullaniciDao kullaniciDao;
    
    private NavigationControllers kullaniciNav;
    private String mesaj;// login sayfasina hatali mesaj return etmek icin
    
    public KullaniciControllers() {        
        this.kullaniciNav = new NavigationControllers();
    }
    
    public String login(){       
        if(this.getUser().equals(this.getKullanici().getUsername()) && this.getPass().equals(this.getKullanici().getPassword()) ){
            if(this.getKullanici().getYetki().getUye_Yetki() > 0){
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_admin",this.getKullanici());         
            return kullaniciNav.pageK("sarkilar");
            }
            else{
             FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("valid_user",this.getKullanici());
             return kullaniciNav.pageK("sarkilar");
            }            
        }
        else{                       
            this.mesaj = "Geçersiz username veya password - Kullanici değilseniz lütfen üye olunuz..";
            return "/login?faces-redirect=true";
        }
    }    
    public Kullanici getKullanici() {               
        this.kullanici = this.getKullaniciDao().find(this.getUser(),this.getPass()); 
            return kullanici;
        
    }
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }    
    public KullaniciDao getKullaniciDao() {
        if(this.kullaniciDao == null)
            this.kullaniciDao = new KullaniciDao();
        return kullaniciDao;
    }        
    public String getMesaj() {
        return mesaj;
    }
    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public NavigationControllers getKullaniciNav() {
        return kullaniciNav;
    }

    public void setKullaniciNav(NavigationControllers kullaniciNav) {
        this.kullaniciNav = kullaniciNav;
    }
    
    
    
}
