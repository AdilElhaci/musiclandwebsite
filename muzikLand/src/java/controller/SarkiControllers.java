package controller;

import dao.SarkiDao;
import entity.Sarki;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class SarkiControllers extends PagintionControllers implements Serializable {
    
    private Sarki sarki;
    private List<Sarki> slist;
    private SarkiDao sdao;
    private int sonuc;
    @Inject
    private DilControllers dilControl;
    @Inject
    private AlbumControllers albumControl;
    
    @Inject
    private CategoryControllers categoryControl;
    
    @Inject
    private SarkiciControllers sarkiciKontrol;
    
     private int sayfaSize = 6;
    private int sayfaAdet = 3;
    
    public SarkiControllers() {
        setSayfa_Adet(sayfaAdet);
        setSayfa_Size(sayfaSize);
        setDao(this.getSdao());
        this.sonuc = -1;
    }    
    public Sarki getSarki() {
        if (this.sarki == null) {
            this.sarki = new Sarki();
        }
        return sarki;
    }

    public List<Sarki> getSlist() {
        this.slist = this.getSdao().findAll(getPage(),getSayfa_Size(),getSearchTerimi(),getId());
        return slist;
    }
    
    public List<Sarki> allSong(){
        return this.getSdao().findAll(1, this.getSdao().findCount(getSearchTerimi(),getId()),getSearchTerimi(),null);
    }
    
    public void updateForm(Sarki sarki) {
        this.sarki = sarki;        
    }
    
    public void update() {
        this.sonuc = this.getSdao().update(this.getSarki());
        this.sarki = new Sarki();
        
    }       
    public SarkiDao getSdao() {
        if (this.sdao == null) {
            this.sdao = new SarkiDao();
        }
        return sdao;
    }
    
    public int getSonuc() {
        return sonuc;
    }
    
    public void setSonuc(int sonuc) {
        this.sonuc = sonuc;
    }
    
    public DilControllers getDilControl() {
        return dilControl;
    }
    
    public void setDilControl(DilControllers dilControl) {
        this.dilControl = dilControl;
    }
    
    public AlbumControllers getAlbumControl() {
        return albumControl;
    }
    
    public void setAlbumControl(AlbumControllers albumControl) {
        this.albumControl = albumControl;
    }

    public CategoryControllers getCategoryControl() {
        return categoryControl;
    }

    public SarkiciControllers getSarkiciKontrol() {
        return sarkiciKontrol;
    }
    
    
}
