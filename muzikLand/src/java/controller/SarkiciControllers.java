package controller;

import dao.SarkiciDao;
import entity.Sarkici;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
@Named
@SessionScoped
public class SarkiciControllers extends PagintionControllers implements Serializable{
    private Sarkici sarkici;
    private SarkiciDao sdao;
    private List<Sarkici> slist;
    @Inject
    private UlkeControllers ulkeList;
    @Inject
    private AlbumControllers albumKontrol;
    @Inject
    private RseimDocumentControllers sarkici_profile;

    public SarkiciControllers() {
        setDao(this.getSdao());
        setSayfa_Adet(2);
        setSayfa_Size(8);
    }
    
    public void create(){
        this.getSdao().create(this.getSarkici());
        this.sarkici = new Sarkici();
    }
     public List<Sarkici> getSlist() {
         this.slist = this.getSdao().findAll(getPage(),getSayfa_Size(),getSearchTerimi());
        return slist;
    }
     public List<Sarkici> allSarkici() {
        return this.getSdao().findAll(1,this.getSdao().findCount(getSearchTerimi()),getSearchTerimi());
    }
     
    public void updatefrom(Sarkici sarkici){
       this.sarkici = sarkici;
       
    }
    public void update() {
        if(this.getSarkici_profile().getEror_mesage() == null){
        this.getSdao().update(this.getSarkici());
        if(this.getSarkici().getSarkici_resim().getDoc_id() == null)
        this.getSarkici_profile().upload(this.sarkici.getSarkici_id() + 1000000);
        else
          this.getSarkici_profile().updatePath(this.getSarkici());
        this.sarkici = new Sarkici();
        this.getSarkici_profile().resim_reset();
        }        
    }
    public void confirmDelete(Sarkici sarkici){
        this.sarkici = sarkici;
    }
    public void delete(){
       this.getSdao().delete(this.getSarkici());
        this.sarkici = new Sarkici();
        this.getSarkici_profile().resim_reset();
    }
    public void clearForm() {
        this.getSarkici_profile().resim_reset();
        this.sarkici = new Sarkici();
    }

    public Sarkici getSarkici() {
        if(this.sarkici == null)
            this.sarkici = new Sarkici();
        return sarkici;
    }

    public SarkiciDao getSdao() {
        if(this.sdao == null)
            this.sdao = new SarkiciDao();
        return sdao;
    }

    public UlkeControllers getUlkeList() {
        return ulkeList;
    }

    public AlbumControllers getAlbumKontrol() {
        return albumKontrol;
    }

    public RseimDocumentControllers getSarkici_profile() {
        return sarkici_profile;
    }

    
    
    
    
   
   
}
