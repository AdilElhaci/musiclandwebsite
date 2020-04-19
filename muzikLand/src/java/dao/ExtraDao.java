package dao;

import entity.Extra;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExtraDao extends Connector {
   
    private SarkiDao sarkiDao;
    private UlkeDao ulkeDao;
    public int create(Extra extra) {   
        int i = 0;
        try {
            String query = "insert into extra(sarki_id,sarki_puan,sarki_sure,ulke_id,sarkici_sirketi) values(?,?,?,?,?)";
           PreparedStatement pst = this.getCon().prepareStatement(query);
           pst.setLong(1,extra.getSarki().getSarki_id());
           pst.setFloat(2, extra.getSarki_puan());
           pst.setFloat(3, extra.getSarki_sure());
           pst.setLong(4,extra.getUlke().getUlke_id());
           pst.setString(5, extra.getSarkici_sirketi());
           i= pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }
    public List<Extra> findAll(int page_Num,int extra_Sayisi,String aranacak) {
        List<Extra> exList = new ArrayList<>();
        page_Num=(page_Num -1) * extra_Sayisi;
        try {
            String query="select * from extra";  
            PreparedStatement pst;
            if(aranacak !=null){
                query +=" where sarki_id in(select sarki_id from sarki where tmp_name like ?) order by id asc limit ?,?";
                pst= this.getCon().prepareStatement(query);
                pst.setString(1, "%"+aranacak+"%");
                pst.setInt(2, page_Num);
                pst.setInt(3, extra_Sayisi);
            }
            else{
                query +=" order by id asc limit ?,?";
                pst= this.getCon().prepareStatement(query);
                pst.setInt(1, page_Num);
                pst.setInt(2, extra_Sayisi);
            }            
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Extra exstra = new Extra();
                exstra.setId(rs.getLong("id"));
                exstra.setSarki(this.getSarkiDao().findSarki(rs.getLong("sarki_id")));
                exstra.setSarki_puan(rs.getFloat("sarki_puan"));
                exstra.setSarki_sure(rs.getFloat("sarki_sure"));
                exstra.setUlke(this.getUlkeDao().findulkeBy_id(rs.getLong("ulke_id")));
                exstra.setSarkici_sirketi(rs.getString("sarkici_sirketi"));
                exstra.setLast_update(rs.getDate("last_update"));
                exList.add(exstra);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exList;
    }
    

    public int delete(Extra exstra) {
        int i=0;
        try {
            PreparedStatement pst = this.getCon().prepareStatement("delete from extra where id=?");
            pst.setLong(1,exstra.getId());
            i= pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }

    public int update(Extra exstra) {
        int i=0;
        try {
            PreparedStatement pst = this.getCon().prepareStatement("update extra set sarki_id=?, sarki_puan=?, sarki_sure=? ,ulke_id = ? , sarkici_sirketi=? where id =?");
            pst.setLong(1,exstra.getSarki().getSarki_id());
            pst.setFloat(2,exstra.getSarki_puan());
            pst.setFloat(3,exstra.getSarki_sure());
            pst.setLong(4,exstra.getUlke().getUlke_id());
            pst.setString(5,exstra.getSarkici_sirketi());
            pst.setLong(6,exstra.getId());
            i = pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }
    
    public Extra find(Long id){
        Extra ex = null;
        try{
        PreparedStatement pst ;
        String query = "select * from extra where sarki_id = ?";
        pst = this.getCon().prepareStatement(query);
        pst.setLong(1, id);        
        ResultSet rs = pst.executeQuery();
        rs.next();
        ex = new Extra();
        ex.setId(rs.getLong("id"));
        ex.setSarki(this.getSarkiDao().findSarki(rs.getLong("sarki_id")));
        ex.setSarki_puan(rs.getFloat("sarki_puan"));
        ex.setSarki_sure(rs.getFloat("sarki_sure"));
        ex.setSarkici_sirketi(rs.getString("sarkici_sirketi"));
        ex.setUlke(this.getUlkeDao().findulkeBy_id(rs.getLong("ulke_id")));
        ex.setLast_update(rs.getDate("last_update"));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ex;
    }
    
    public int findCount(String aranacak) {
        int count = 0;
        try {
            String query = "select count(id) as extra_count from extra";  
            PreparedStatement pst;
            if(aranacak !=null){
                query +=" where sarki_id in(select sarki_id from sarki where tmp_name like ?)";
                pst = this.getCon().prepareStatement(query);
                pst.setString(1, "%"+aranacak+"%");
            }
            else
               pst = this.getCon().prepareStatement(query);
                
            ResultSet rs = pst.executeQuery();
           rs.next(); 
               count = rs.getInt("extra_count");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
    public SarkiDao getSarkiDao() {
        if(this.sarkiDao == null)
            this.sarkiDao = new SarkiDao();
        return sarkiDao;
    }

    public UlkeDao getUlkeDao() {
        if(this.ulkeDao == null)
            this.ulkeDao = new UlkeDao();
        return ulkeDao;
    }
    
}
