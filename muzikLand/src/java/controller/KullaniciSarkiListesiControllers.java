
package controller;
import dao.KullaniciSarkiListesiDao;
import entity.KullaniciSarkiListesi;
import entity.Sarki;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class KullaniciSarkiListesiControllers extends PagintionControllers implements Serializable {
    private  KullaniciSarkiListesi kullaniciList;
    private List<KullaniciSarkiListesi> kullSarkiList;
    private KullaniciSarkiListesiDao kullSarkiDao;
        
    @Inject
    private UyelikYapanlarControllers uye;
    
    public KullaniciSarkiListesiControllers() {
        this.kullaniciList = new KullaniciSarkiListesi();        
        setSayfa_Adet(3);
        setSayfa_Size(8);
        setDao(this.getKullSarkiDao());
    }
    
    public void create(Sarki sarki){// kullanici tarafindaki sarkilarda ekleme islemi yapar
        Long kullanici_id = this.getUye().getUpdateToKullanici().getKullanici_id();
        this.kullaniciList.setKullanici_id(kullanici_id);
        this.kullaniciList.setSarki(sarki);
        this.getKullSarkiDao().create(this.getKullaniciList());
    }
    public void confirmDelete(KullaniciSarkiListesi k){
        this.kullaniciList = k;
    }
    public void delete(){
        this.getKullSarkiDao().delete(this.getKullaniciList());
    }
    public List<KullaniciSarkiListesi> getKullSarkiList() {
        Long kullanici_id = this.getUye().getUpdateToKullanici().getKullanici_id();
        this.kullSarkiList = this.getKullSarkiDao().findAll(kullanici_id,getPage(),getSayfa_Size(),getSearchTerimi());
        return kullSarkiList;
    }
     public KullaniciSarkiListesi getKullaniciList() {            
        return kullaniciList;
    }
    public KullaniciSarkiListesiDao getKullSarkiDao() {
        if(this.kullSarkiDao == null)
            this.kullSarkiDao = new KullaniciSarkiListesiDao();
        return kullSarkiDao;
    }
    public UyelikYapanlarControllers getUye() {
        return uye;
    }   
}
