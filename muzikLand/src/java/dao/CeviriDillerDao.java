package dao;

import entity.CeviriDiller;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CeviriDillerDao extends Connector {

    private SarkiDao sarkiDao;
    private DilDao dildao;      
    public List<CeviriDiller> findAll(int page_Num,int sarki_Sayisi,String aranacak) {        
        List<CeviriDiller> cevList = new ArrayList<>();  
        page_Num=(page_Num -1) * sarki_Sayisi;
        try {             
            String query ="select * from ceviri_dil";                          
            PreparedStatement pst;
            if(aranacak != null){               
                  query +=" where sarki_id in(select sarki_id from sarki where tmp_name like ? ) order by id asc limit ?,?";
                  pst = this.getCon().prepareStatement(query);
                  pst.setString(1 , "%"+aranacak+"%");
                  pst.setInt(2,page_Num);
                  pst.setInt(3, sarki_Sayisi);                  
            }            
            else{            
              query +=" order by id asc limit ?,? ";
              pst= this.getCon().prepareStatement(query);
              pst.setInt(1, page_Num);
              pst.setInt(2, sarki_Sayisi);                                            
             }             
             ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    CeviriDiller cdil = new CeviriDiller();
                    cdil.setId(rs.getLong("id"));
                    cdil.setSarki_Text(rs.getString("sarki_Text"));
                    cdil.setDil(this.getDildao().find_Id(rs.getLong("dil_id")));
                    cdil.setSarki(this.getSarkiDao().findSarki(rs.getLong("sarki_id")));
                    cdil.setLast_update(rs.getDate("last_update"));
                    cevList.add(cdil);
                }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }        
        return cevList;
    }
    public void create(CeviriDiller ceviriDiller) {
        try {

            PreparedStatement pst = this.getCon().prepareStatement("insert into ceviri_dil(sarki_id, dil_id, sarki_Text) values(?,?,?)");
            pst.setLong(1,ceviriDiller.getSarki().getSarki_id());
            pst.setLong(2,ceviriDiller.getDil().getDil_id());
            pst.setString(3, ceviriDiller.getSarki_Text());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int delete(CeviriDiller CeviriDiller) {
        PreparedStatement pst;
        int i = 0;
        try {
            pst = this.getCon().prepareStatement("delete from ceviri_dil where id=?");
            pst.setLong(1, CeviriDiller.getId());
            i = pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return i;
    }

    public int update(CeviriDiller CeviriDiller) {

        PreparedStatement st;
        int i = 0;
        try {
            st = this.getCon().prepareStatement("update ceviri_dil set sarki_Text=?,dil_id=?,sarki_id=? where id=?");
            st.setString(1, CeviriDiller.getSarki_Text());
            st.setLong(2, CeviriDiller.getDil().getDil_id());
            st.setLong(3, CeviriDiller.getSarki().getSarki_id());
            st.setLong(4, CeviriDiller.getId());
            i = st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }
    
    public int findCount(String aranacak) {                
        int count = 0;        
        try {         
            String query = "select count(id) as ceviri_count from ceviri_dil";
                PreparedStatement pst;
                if(aranacak != null){                    
                    query +=" where sarki_id in(select sarki_id from sarki where tmp_name like ? )";
                    pst= this.getCon().prepareStatement(query);
                    pst.setString(1 , "%"+aranacak+"%");                   
                }
                else
                    pst= this.getCon().prepareStatement(query);
                
                    ResultSet rs = pst.executeQuery();
                    rs.next(); 
                    count = rs.getInt("ceviri_count");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
    public CeviriDiller find(Long sarki_id){
        CeviriDiller cevDil = null;          
        try {
            PreparedStatement pst ;  
           String query ="select * from ceviri_dil where sarki_id = ? ";
            pst= this.getCon().prepareStatement(query);
            pst.setLong(1, sarki_id); 
             ResultSet rs = pst.executeQuery();                     
           if(rs.next()){ 
                cevDil = new CeviriDiller();
                cevDil.setId(rs.getLong("id"));
                cevDil.setSarki_Text(rs.getString("sarki_Text"));
                cevDil.setLast_update(rs.getDate("last_update"));
                cevDil.setSarki(this.getSarkiDao().findSarki(rs.getLong("sarki_id")));
                cevDil.setDil(this.getDildao().find_Id(rs.getLong("dil_id"))); 
           }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return cevDil;
    }
     public DilDao getDildao() {
        if (this.dildao == null) {
            this.dildao = new DilDao();
        }
        return dildao;
    }

    public SarkiDao getSarkiDao() {
        if (this.sarkiDao == null) {
            this.sarkiDao = new SarkiDao();
        }
        return sarkiDao;
    }
}
