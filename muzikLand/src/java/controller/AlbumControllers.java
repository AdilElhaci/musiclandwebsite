package controller;

import dao.AlbumDao;
import entity.Album;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class AlbumControllers extends PagintionControllers implements Serializable {

    private List<Album> albumList;

    private AlbumDao albumDao;

    private Album album;

    public AlbumControllers() {
        setDao(this.getAlbumDao());
        setSayfa_Adet(2);
        setSayfa_Size(8);
    }
    
    
    public void updateform(Album album) {
        this.album = album;
    }
    public void clearForm() {
        this.album = new Album();
    }
    public void confirmDelete(Album album) {
        this.album= album;
        
    }
    public List<Album> getAlbumList() {
        this.albumList = this.getAlbumDao().findAll(getPage(),getSayfa_Size(),getSearchTerimi());
        return albumList;
    }
    public List<Album> allAlbumList() {
        return this.getAlbumDao().findAll(1,this.getAlbumDao().findCount(getSearchTerimi()),getSearchTerimi());
    }
    public void setAlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }
    public AlbumDao getAlbumDao() {
        if (albumDao == null) {
            this.albumDao = new AlbumDao();
        }

        return albumDao;
    }

    public void setAlbumDao(AlbumDao albumDao) {
        this.albumDao = albumDao;
    }

    public Album getAlbum() {
        if (album == null) {
            this.album = new Album();
        }
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void create() {
        this.getAlbumDao().create(this.getAlbum());
        this.album = new Album();

    }
    public void delete() {

        this.getAlbumDao().delete(this.getAlbum());
        this.album = new Album();
    }

    public void update() {
        this.getAlbumDao().update(this.getAlbum());
        this.album = new Album();

    }

}
