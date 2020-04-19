package dao;

import com.mysql.jdbc.Statement;
import entity.Album;
import entity.Sarkici;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SarkiciDao extends Connector {

    private UlkeDao ulkeDao;
    private AlbumDao albumDao;
    private ResimDocumentDao resimDao;

    public void create(Sarkici sarkici) {

        try {
            String query = "insert into sarkici(name,last_name,yas,ulke_id) values(?,?,?,?)";
            PreparedStatement pst = this.getCon().prepareStatement(query ,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, sarkici.getName());
            pst.setString(2, sarkici.getLast_name());
            pst.setLong(3,sarkici.getYas());
            pst.setLong(4, sarkici.getUlke().getUlke_id());
            pst.executeUpdate();
            
            Long sarkici_ID = null;
             ResultSet gk = pst.getGeneratedKeys();
            if (gk.next()) {
                sarkici_ID = gk.getLong(1);
            }
            
            for (Album album : sarkici.getSarkiciAlbum()) {
                pst = this.getCon().prepareStatement("insert into sarkici_album(sarkici_id,album_id) values(?,?)");
                pst.setLong(1, sarkici_ID);
                pst.setLong(2, album.getAlbum_Id());
                pst.executeUpdate();
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Sarkici> findAll(int page_Num,int sarki_Sayisi,String aranacak) {
        List<Sarkici> sList = new ArrayList<>();
          page_Num=(page_Num -1) * sarki_Sayisi;
          String query="select * from sarkici";
        try {            
            PreparedStatement pst;
            if(aranacak != null){
            query +=" where name like ? order by sarkici_id asc limit ?,?";
            pst = this.getCon().prepareStatement(query);
            pst.setString(1, "%"+aranacak+"%");
            pst.setInt(2, page_Num);
            pst.setInt(3, sarki_Sayisi);
            }
            else{
            query +=" order by sarkici_id asc limit ?,?";
            pst = this.getCon().prepareStatement(query);
            pst.setInt(1, page_Num);
            pst.setInt(2, sarki_Sayisi);
            }
            
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Sarkici sarkici = new Sarkici();
                sarkici.setSarkici_id(rs.getLong("sarkici_id"));
                sarkici.setName(rs.getString("name"));
                sarkici.setLast_name(rs.getString("last_name"));
                sarkici.setYas(rs.getInt("yas"));
                sarkici.setLast_update(rs.getDate("last_update"));
                sarkici.setUlke(this.getUlkeDao().findulkeBy_id(rs.getLong("ulke_id")));
                sarkici.setSarkiciAlbum(this.getAlbumDao().getSarliciAlbum(rs.getLong("sarkici_id")));
                sarkici.setSarkici_resim(this.getResimDao().find(rs.getLong("sarkici_id") + 1000000));
                sList.add(sarkici);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return sList;
    }
    
    public void delete(Sarkici sarkici) {
        try {
            PreparedStatement pst = this.getCon().prepareStatement("delete from sarkici_album where sarkici_id=?");
            pst.setLong(1, sarkici.getSarkici_id());
            pst.executeUpdate();
            
            pst = this.getCon().prepareStatement("delete from sarkici where sarkici_id=?");
            pst.setLong(1, sarkici.getSarkici_id());
            pst.executeUpdate();
            this.getResimDao().delete(sarkici.getSarkici_id());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Sarkici sarkici) {
        try {
            PreparedStatement pst = this.getCon().prepareStatement("update sarkici set name=?, last_name=?, yas=?, ulke_id=?  where sarkici_id=?");
            pst.setString(1, sarkici.getName());
            pst.setString(2,sarkici.getLast_name());
            pst.setInt(3,sarkici.getYas());
            pst.setLong(4,sarkici.getUlke().getUlke_id());
            pst.setLong(5,sarkici.getSarkici_id());
            pst.executeUpdate();
            
            pst = this.getCon().prepareStatement("delete from sarkici_album where sarkici_id=?");
                pst.setLong(1, sarkici.getSarkici_id());
                pst.executeUpdate();
            
            for (Album album : sarkici.getSarkiciAlbum()) {
                pst = this.getCon().prepareStatement("insert into sarkici_album(sarkici_id,album_id) values(?,?)");
                pst.setLong(1, sarkici.getSarkici_id());
                pst.setLong(2, album.getAlbum_Id());
                pst.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public int findCount(String aranacak) {
        int count = 0;
        String query = "select count(sarkici_id) as sarkici_count from sarkici";
        try {
            PreparedStatement pst;
            if(aranacak != null){
             query +=" where name like ? ";
              pst = this.getCon().prepareStatement(query);
              pst.setString(1, "%"+aranacak+"%");
            }
            else{
                pst = this.getCon().prepareStatement(query);
            }            
            ResultSet rs = pst.executeQuery();
           rs.next(); 
               count = rs.getInt("sarkici_count");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
    
    public Sarkici findSarkici(Long sarkici_ID) { 
        Sarkici sarkici = null;
        try {
            PreparedStatement pst;
            String query = "select * from sarkici where sarkici_id= ?" ;
            pst = this.getCon().prepareStatement(query);
            pst.setLong(1 ,sarkici_ID);
            ResultSet rs = pst.executeQuery();
            rs.next();
            sarkici = new Sarkici();
                sarkici.setSarkici_id(rs.getLong("sarkici_id"));
                sarkici.setName(rs.getString("name"));
                sarkici.setLast_name(rs.getString("last_name"));
                sarkici.setYas(rs.getInt("yas"));
                sarkici.setLast_update(rs.getDate("last_update"));
                sarkici.setUlke(this.getUlkeDao().findulkeBy_id(rs.getLong("ulke_id")));
                sarkici.setSarkiciAlbum(this.getAlbumDao().getSarliciAlbum(rs.getLong("sarkici_id")));
                sarkici.setSarkici_resim(this.getResimDao().find(rs.getLong("sarkici_id") + 1000000));
            
    }  catch (SQLException ex) {
            System.out.println(ex.getMessage());
       }
        return sarkici;
    }

    List<Sarkici> getSarkiSarkici(long sarki_id) {
         List<Sarkici> slist =new ArrayList<>();
        try {
           PreparedStatement pst ;
           String query = "select * from sarki_sarkici where sarki_id = ?";
           pst= this.getCon().prepareStatement(query);
           pst.setLong(1, sarki_id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                slist.add(this.findSarkici(rs.getLong("sarkici_id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return slist;
    }

    
    public UlkeDao getUlkeDao() {
        if (this.ulkeDao == null) {
            this.ulkeDao = new UlkeDao();
        }
        return ulkeDao;
    }
    public AlbumDao getAlbumDao() {
        if(this.albumDao == null)
            this.albumDao = new AlbumDao();
        return albumDao;
    }

    public ResimDocumentDao getResimDao() {
        if(this.resimDao == null)
            this.resimDao = new ResimDocumentDao();
        return resimDao;
    }

    
    

}
