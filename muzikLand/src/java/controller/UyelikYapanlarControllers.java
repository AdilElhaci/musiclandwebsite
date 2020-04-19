package controller;

import dao.KullaniciDao;
import entity.Kullanici;
import entity.Sarki;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.validator.Validator;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@SessionScoped
public class UyelikYapanlarControllers extends PagintionControllers implements Serializable{

    private Kullanici kullanici;
    private Kullanici updateToKullanici;   // tmp nesne kullanici tarafinda profile bilgilerini guncellenemek icin.
    private List<Kullanici> KullaniciList;
    private KullaniciDao kullaniciDao;
    private int sonuc;  // database tarafindaan bize return olacak sonuc
    private int i,j,k;     // giris yapan bilgilerini sadece ilk seferde kullanmak için geçici değişkenlerdir
    private boolean edit;  // profile sayfasinda edit button aktiflesitirme kontrolu
    private String tmpUser;
    private String tmpPass;
    
    @Inject
    private KullaniciControllers girisYapan; // giris yapan kullanicinin bilgilerine erişmek için
    @Inject 
    private RseimDocumentControllers kullanici_resmi;

    public UyelikYapanlarControllers(){   
        
        setDao(this.getKullaniciDao());
        setSayfa_Adet(2);
        setSayfa_Size(6);    
        this.setYetki_saviye(0);
        this.sonuc = -1;
            i=1;
            j=1;
            k = 1;
    }
    public List<Kullanici> getKullaniciList() {
        this.KullaniciList = this.getKullaniciDao().findAll(getPage(),getSayfa_Size(),0,getSearchTerimi());        
        return KullaniciList;
    }

    public void updateForm(Kullanici kullanici) {     
        this.kullanici = kullanici;
        this.sonuc = -1;
    }

     public void updateForm2() {// kullanici tarafinda profile bilgilerini guncellemek icin
        this.sonuc = -1;
        this.edit = true;
    }
     
    public void clearForm() {
        this.kullanici = new Kullanici();
        this.sonuc = -1;
    }

    public void create() {
        if(this.kullanici_resmi.getEror_mesage() == null){
          this.sonuc = this.getKullaniciDao().create(this.getKullanici());
          this.kullanici_resmi.upload(this.getKullaniciDao().getKullanici_bilgi_id());     
          this.getGirisYapan().getKullanici().getYetki().setResim(this.kullanici_resmi.getDocment());
          //this.getUpdateToKullanici().getYetki().setResim(this.kullanici_resmi.getDocment());  // null pointer exp dusuyor
          if(this.sonuc == 1)
          this.kullanici = new Kullanici();  
          this.kullanici_resmi.setEror_mesage(null);
        }        
    }
    public void update() { // admin tarafinda olan kullanici icinde olan uyelerin guncellemesi icin        
        this.sonuc = this.getKullaniciDao().update(this.getKullanici());
        this.kullanici = new Kullanici();
    }
    
    public void update2() {
        if(this.kullanici_resmi.getEror_mesage() == null){            
        this.sonuc = this.getKullaniciDao().update(this.getUpdateToKullanici());
        // sonuc = 1 ise islem dogru ve update işlemi yapilsin hataliysa listedeki bilgiler etkilenmesin        
            if(sonuc==1){        
            this.updateToKullanici = this.getKullaniciDao().find(this.getUpdateToKullanici().getUsername(), this.getUpdateToKullanici().getPassword());
            this.setTmpUser(this.updateToKullanici.getUsername());
            this.setTmpPass(this.updateToKullanici.getPassword());  
            if(this.getUpdateToKullanici().getYetki().getResim().getDoc_id() == null){
                this.getKullanici_resmi().upload(this.getUpdateToKullanici().getYetki().getId());
                this.getUpdateToKullanici().getYetki().setResim(this.kullanici_resmi.getDocment());
                this.updateToKullanici = this.getKullaniciDao().find(this.getUpdateToKullanici().getUsername(), this.getUpdateToKullanici().getPassword());
            }
            else
            this.getKullanici_resmi().updatePath(this.getUpdateToKullanici()); 
            this.kullanici_resmi.setEror_mesage(null);
            this.setEdit(false);
           }
        }        
    }
    public void delete() {
        this.sonuc = this.getKullaniciDao().delete(this.getKullanici());
        this.kullanici = new Kullanici();
    }  
    public Kullanici getUpdateToKullanici() {
        // ilk sefer giris yapan kullanicin bilgilerini gostereceğiz ama sonra 
        //password bilgisi guncellenebildiğinden dolayi profile bilgilerin getKullanici() ile çağiramayiz
        //o yüzden updatetoKullanicida olan bilgiler ile yeni bir sorgulama yapip return yapacağız
        // bu sorgulama update2()'de yeniden KullaniciDao'da find() metod ile yapacağız
        if(i == 1 ){
       this.updateToKullanici = this.getGirisYapan().getKullanici(); 
        i=0;
        }        
        return updateToKullanici;
    }
    public String getTmpUser() {
        if(j == 1){
            j = 0;
            this.tmpUser = this.getUpdateToKullanici().getUsername();
        }
        return tmpUser;
    }
    public String getTmpPass() {
        if(k == 1){
            k = 0;
            this.tmpPass = this.getUpdateToKullanici().getPassword();
        }
        return tmpPass;
    }
    public void setUpdateToKullanici(Kullanici updateToKullanici) {
        this.updateToKullanici = updateToKullanici;
    }
    public void setTmpPass(String tmpPass) {
        this.tmpPass = tmpPass;
    }
    public void setTmpUser(String tmpUser) {
        this.tmpUser = tmpUser;
    }
    public int getSonuc() {
        return sonuc;
    }
    public void setSonuc(int sonuc) {
        this.sonuc = sonuc;
    }
    public boolean isEdit() {        
        return edit;
    }
    public void setEdit(boolean edit) {
        this.kullanici_resmi.setPart(null);
        this.kullanici_resmi.setEror_mesage(null);
        this.edit = edit;
    }

    public KullaniciControllers getGirisYapan() {
        return girisYapan;
    }
     public Kullanici getKullanici() {
        if (this.kullanici == null) {
            this.kullanici = new Kullanici();
        }
        return kullanici;
    }
    public KullaniciDao getKullaniciDao() {
        if (this.kullaniciDao == null) {
            this.kullaniciDao = new KullaniciDao();
        }
        return kullaniciDao;
    }
    public void setKullaniciList(List<Kullanici> KullaniciList) {
        this.KullaniciList = KullaniciList;
    }

    public RseimDocumentControllers getKullanici_resmi() {
        return kullanici_resmi;
    }    
}
