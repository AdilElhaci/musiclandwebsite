
package dao;

import entity.Album;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDao extends Connector {
    
    public List<Album> findAll(int page_Num,int sarki_Sayisi,String aranack) {
        List<Album> albumList = new ArrayList();
        page_Num=(page_Num -1) * sarki_Sayisi;
        PreparedStatement pst;
        try {
            String query ="select * from album";
            if(aranack == null){
                query +=" order by album_id asc limit ?,?";
            pst = this.getCon().prepareStatement(query);
            pst.setInt(1, page_Num);
            pst.setInt(2, sarki_Sayisi);
            }
            else{
                query +=" where album_name like ? order by album_id asc limit ?,?";
            pst = this.getCon().prepareStatement(query);
            pst.setString(1,"%"+aranack+"%");
            pst.setInt(2, page_Num);
            pst.setInt(3, sarki_Sayisi);
            }
           
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Album tmp = new Album(/* rs.getLong("album_id") ,rs.getString("album_name") ,rs.getDate("last_update")*/);
                tmp.setAlbum_Id(rs.getLong("album_id"));
                tmp.setName(rs.getString("album_name"));
                tmp.setAlbum_LastUpdate(rs.getDate("last_update"));

                albumList.add(tmp);
            }

        } catch (SQLException x) {
            System.out.println(x.getMessage());
        }

        return albumList;
    }

    public void create(Album album) {

        try {
            PreparedStatement pst = this.getCon().prepareStatement("insert into album (album_name) values(?)");
            pst.setString(1, album.getName());
            pst.executeUpdate();
        } catch (SQLException x) {
            System.out.println(x.getMessage());
        }
    }

    public void delete(Album album) {

       try {
            PreparedStatement pst = this.getCon().prepareStatement("delete from album where album_id=?");
            pst.setLong(1, album.getAlbum_Id());
            pst.executeUpdate();
        } catch (SQLException x) {
            System.out.println(x.getMessage());
        }

    }

    public void update(Album album) {

       try {
            PreparedStatement pst = this.getCon().prepareStatement("update album set album_name=? where album_id=?");
            pst.setString(1, album.getName());
            pst.setLong(2, album.getAlbum_Id());
            pst.executeUpdate( );
        } catch (SQLException x) {
            System.out.println(x.getMessage());
        }

    }
    
    
    public Album find_albumId(Long album_id) {
         Album album = null;

        try {
            PreparedStatement pst = this.getCon().prepareStatement("select * from album where album_id = ? ");
            pst.setLong(1, album_id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            album = new Album();
            album.setAlbum_Id(rs.getLong("album_id"));
            album.setName(rs.getString("album_name"));
            album.setAlbum_LastUpdate(rs.getDate("last_update"));
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return album;
        
    }
    public List<Album> getSarliciAlbum(Long sarkici_id){
          List<Album> sarkiciList =new ArrayList<>();
        try {
           PreparedStatement pst ;
           String query = "select * from sarkici_album where sarkici_id = ?";
           pst= this.getCon().prepareStatement(query);
           pst.setLong(1, sarkici_id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                sarkiciList.add(this.find_albumId(rs.getLong("album_id")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return sarkiciList;
    }
    
    
     public int findCount(String aranacak) {
        int count = 0;
        String query = "select count(album_id) as album_count from album ";
        PreparedStatement st;
        try {
            if(aranacak == null)
            st = this.getCon().prepareStatement(query);
            else{
            st = this.getCon().prepareStatement(query+"where album_name like ? "); 
            st.setString(1, "%"+aranacak+"%");
            }
            ResultSet rs = st.executeQuery();
           rs.next(); 
               count = rs.getInt("album_count");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }

}
