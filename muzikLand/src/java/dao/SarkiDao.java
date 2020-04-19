package dao;

import entity.Category;
import entity.Sarki;
import entity.SarkiDocument;
import entity.Sarkici;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SarkiDao extends Connector {

    private DilDao dilDao;
    private AlbumDao albumDao;
    private SarkiciDao sarkiciDao;
    private CategoryDao categoryDao;
    private SarkiDocumentDao sarkiDocDao;
    
    public List<Sarki> findAll(int page_Num,int sarki_Sayisi,String aranacak,Long cat_id) {// pagination için kullaniliyor
        List<Sarki> sList = new ArrayList<>();
        page_Num=(page_Num -1) * sarki_Sayisi;
        try {
            PreparedStatement pst;
            String query ="select * from sarki";   
            ResultSet rs = null;
            if(aranacak !=null && cat_id == null){
            query += " where sarki_id in(select file_id from sarki_document where file_id in(select sarki_id from sarki where tmp_name like ? )) order by sarki_id asc limit ?,? ";
            pst = this.getCon().prepareStatement(query);
            pst.setString(1, "%"+aranacak+"%");
            pst.setInt(2, page_Num);
            pst.setInt(3, sarki_Sayisi);
             rs = pst.executeQuery();    
            }
            
            else if(aranacak ==null && cat_id != null){
            query += " where sarki_id in(select sarki_id from sarki_category where category_id = ?) order by sarki_id asc limit ?,? ";
            pst = this.getCon().prepareStatement(query);
            pst.setLong(1, cat_id);
            pst.setInt(2, page_Num);
            pst.setInt(3, sarki_Sayisi);
             rs = pst.executeQuery();    
            }
            else if(aranacak !=null && cat_id != null){
            query += " where sarki_id in(select file_id from sarki_document where file_id in(select sarki_id from sarki where tmp_name like ? ))"
                    + " and sarki_id in( select sarki_id from sarki_category where category_id = ?) order by sarki_id asc limit ?,? ";
            pst = this.getCon().prepareStatement(query);
            pst.setString(1, "%"+aranacak+"%");
            pst.setLong(2, cat_id);
            pst.setInt(3, page_Num);
            pst.setInt(4, sarki_Sayisi);
            rs = pst.executeQuery();    
            }
            
            else if(aranacak ==null && cat_id == null){
            query += " order by sarki_id asc limit ?,? ";
            pst = this.getCon().prepareStatement(query);
            pst.setInt(1, page_Num);
            pst.setInt(2, sarki_Sayisi);
            rs = pst.executeQuery();    
            }                                           
            while (rs.next()) {
                Sarki sarki = new Sarki();
                sarki.setSarki_id(rs.getLong("sarki_id"));
                sarki.setTmp_name(rs.getString("tmp_name"));
                sarki.setDescription(rs.getString("description"));
                sarki.setYil(rs.getInt("yil"));
                sarki.setLast_update(rs.getDate("last_update"));
                sarki.setSarkiDili(this.getDilDao().find_Id(rs.getLong("dil_id")));
                sarki.setAlbum(this.getAlbumDao().find_albumId(rs.getLong("album_id")));
                 sarki.setSarkiCategory(this.getCategoryDao().getSarki_category(rs.getLong("sarki_id")));
                sarki.setSarkiSarkici(this.getSarkiciDao().getSarkiSarkici(rs.getLong("sarki_id")));
                sarki.setSarkiDoc(this.getSarkiDocDao().find(rs.getLong("sarki_id")));
                sList.add(sarki);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return sList;
    }    
    public int findCount(String aranacak,Long id) {
        int count = 0;
        try {
            String query = "select count(sarki_id) as sarki_count from sarki ";
            PreparedStatement st;
            ResultSet rs = null ;
            if(aranacak != null && id == null){
              query +="where tmp_name like ? ";
              st = this.getCon().prepareStatement(query);
              st.setString(1, "%"+aranacak+"%");  
              rs = st.executeQuery();
            }
            else if(aranacak == null && id != null){
              query +="where sarki_id in( select sarki_id from sarki_category where category_id = ?) ";
              st = this.getCon().prepareStatement(query);
              st.setLong(1, id);      
              rs = st.executeQuery();
            }
            else if(aranacak != null && id != null){
              query +="where tmp_name like ? and sarki_id in( select sarki_id from sarki_category where category_id = ?)";
              st = this.getCon().prepareStatement(query);
              st.setString(1, "%"+aranacak+"%");
              st.setLong(2, id);
              rs = st.executeQuery();
            }
            else if(aranacak == null && id == null){
                st = this.getCon().prepareStatement(query);
                rs = st.executeQuery();
            }
                rs.next(); 
               count = rs.getInt("sarki_count");                                                
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
    public int create(SarkiDocument sarki) {
        int i = 0;
        try {
            PreparedStatement pst ;
            String query = "insert into sarki(sarki_id,tmp_name) values(?,?)";
            pst= this.getCon().prepareStatement(query);
            pst.setLong(1, sarki.getDoc_id());
            pst.setString(2, sarki.getFileName());
            i = pst.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }

    public int update(Sarki sarki) {
        int i = 0;
        try {
            String query = "update sarki set tmp_name=?, description=?, yil=?, dil_id=?, album_id=? where sarki_id=?";
            PreparedStatement pst = this.getCon().prepareStatement(query);
            pst.setString(1, sarki.getTmp_name());
            pst.setString(2, sarki.getDescription());
            pst.setInt(3 , sarki.getYil());
            pst.setLong(4, sarki.getSarkiDili().getDil_id());
            pst.setLong(5, sarki.getAlbum().getAlbum_Id());
            pst.setLong(6, sarki.getSarki_id());
          i =  pst.executeUpdate();
          
          pst = this.getCon().prepareStatement("delete from sarki_category where sarki_id=?");
                pst.setLong(1, sarki.getSarki_id());
                pst.executeUpdate();
            
            for (Category category : sarki.getSarkiCategory()) {// bu işi genede sarki_category classlarni olustrup orada yapabilirdik..
                pst = this.getCon().prepareStatement("insert into sarki_category(sarki_id,category_id) values(?,?)");
                pst.setLong(1, sarki.getSarki_id());
                pst.setLong(2, category.getId());
                pst.executeUpdate();
            }
            
            pst = this.getCon().prepareStatement("delete from sarki_sarkici where sarki_id=?");
                pst.setLong(1, sarki.getSarki_id());
                pst.executeUpdate();
            
            for (Sarkici sarkici : sarki.getSarkiSarkici()) {// bu işi genede sarki_category classlarni olustrup orada yapabilirdik..
                pst = this.getCon().prepareStatement("insert into sarki_sarkici(sarki_id,sarkici_id) values(?,?)");
                pst.setLong(1, sarki.getSarki_id());
                pst.setLong(2, sarkici.getSarkici_id());
                pst.executeUpdate();
            }
            

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }

    public int delete(Sarki sarki) {
        PreparedStatement pst;
        int i = 0;
        try{
        pst = this.getCon().prepareStatement("delete from sarki_category where sarki_id=?");
                pst.setLong(1, sarki.getSarki_id());
                pst.executeUpdate();
        
        pst = this.getCon().prepareStatement("delete from sarki_sarkici where sarki_id=?");
                pst.setLong(1, sarki.getSarki_id());
                pst.executeUpdate();
                
                pst = this.getCon().prepareStatement("delete from sarki where sarki_id=?");
                pst.setLong(1, sarki.getSarki_id());
                i = pst.executeUpdate();
        } catch(Exception e ){
            System.out.println(e.getMessage());
        }
        return i;
    }

    public Sarki findSarki(long sarki_id) {

        Sarki sarki = null;
        try {
            PreparedStatement pst = this.getCon().prepareStatement("select * from sarki where sarki_id=?");
            pst.setLong(1, sarki_id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            sarki = new Sarki();
            sarki.setSarki_id(rs.getLong("sarki_id"));
            sarki.setTmp_name(rs.getString("tmp_name"));
            sarki.setDescription(rs.getString("description"));
            sarki.setYil(rs.getInt("yil"));
            sarki.setAlbum(this.getAlbumDao().find_albumId(rs.getLong("album_id")));
            sarki.setSarkiDili(this.getDilDao().find_Id(rs.getLong("dil_id")));
             sarki.setSarkiCategory(this.getCategoryDao().getSarki_category(rs.getLong("sarki_id")));
             sarki.setSarkiSarkici(this.getSarkiciDao().getSarkiSarkici(rs.getLong("sarki_id")));
             sarki.setSarkiDoc(this.getSarkiDocDao().find(rs.getLong("sarki_id")));
            sarki.setLast_update(rs.getDate("last_update"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return sarki;
    }
    public AlbumDao getAlbumDao() {
        if (this.albumDao == null) {
            this.albumDao = new AlbumDao();
        }
        return albumDao;
    }

    public DilDao getDilDao() {
        if (this.dilDao == null) {
            this.dilDao = new DilDao();
        }
        return dilDao;
    }

    public SarkiciDao getSarkiciDao() {
        if(this.sarkiciDao == null)
            this.sarkiciDao = new SarkiciDao();
        return sarkiciDao;
    }

    public CategoryDao getCategoryDao() {
        if(this.categoryDao == null)
            this.categoryDao = new CategoryDao();
        return categoryDao;
    }

    public SarkiDocumentDao getSarkiDocDao() {
        if(this.sarkiDocDao == null)
            this.sarkiDocDao = new SarkiDocumentDao();
        return sarkiDocDao;
    }

    
    
}
