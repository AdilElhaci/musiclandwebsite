
package dao;

import entity.KullaniciSarkiListesi;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KullaniciSarkiListesiDao extends Connector{
    
    private SarkiDao sarkiDaoo;
    
      public void create(KullaniciSarkiListesi sarki){
        try {
            String query = "insert into kullanici_sarki_tablo(kullanici_id,sarki_id)values(?,?)";
            PreparedStatement pst = this.getCon().prepareStatement(query);
            pst.setLong(1,sarki.getKullanici_id());
            pst.setLong(2, sarki.getSarki().getSarki_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public List<KullaniciSarkiListesi> findAll(Long kullanici_id,int page_Num ,int sarki_Sayisi,String aranacak){
        List<KullaniciSarkiListesi> kList = new ArrayList<>();
        page_Num=(page_Num -1) * sarki_Sayisi;
        try {
            String query = "select * from kullanici_sarki_tablo";
            PreparedStatement pst;
            if(aranacak !=null){
                query +=" where kullanici_id=? and sarki_id in(select sarki_id from sarki where tmp_name like ? )"
                        + "order by id asc limit ?,?";
                    pst = this.getCon().prepareStatement(query);                      
                    pst.setLong(1, kullanici_id);
                    pst.setString(2, "%"+aranacak+"%");
                    pst.setInt(3, page_Num);
                    pst.setInt(4, sarki_Sayisi); 
            }
            else{
                    query +=" where kullanici_id=? order by id asc limit ?,?";
                    pst = this.getCon().prepareStatement(query);           
                    pst.setLong(1, kullanici_id);
                    pst.setInt(2, page_Num);
                    pst.setInt(3, sarki_Sayisi);  
            }
                         
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                KullaniciSarkiListesi kullaniciList = new KullaniciSarkiListesi();
                kullaniciList.setId(rs.getLong("id"));
                kullaniciList.setKullanici_id(rs.getLong("kullanici_id"));
                kullaniciList.setSarki(this.getSarkiDaoo().findSarki(rs.getLong("sarki_id")));
                kullaniciList.setLast_update(rs.getDate("last_update"));
                kList.add(kullaniciList);
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kList;
    }

    public void delete(KullaniciSarkiListesi sarki) {
        try {
            PreparedStatement pst = this.getCon().prepareStatement("delete from kullanici_sarki_tablo where id=?");
            pst.setLong(1, sarki.getId());
            pst.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     public int findCount(Long id,String aranacak) {
        int count = 0;
        try {PreparedStatement pst ;
            String query = "select count(id) as kullanici_sarki_list_count from kullanici_sarki_tablo where kullanici_id = ?";      
            if(aranacak !=null){
                query +=" and sarki_id in(select sarki_id from sarki where tmp_name like ?)";
                 pst = this.getCon().prepareStatement(query);  
                 pst.setLong(1, id);
                 pst.setString(2, "%"+aranacak+"%");
            }
            else{
               pst = this.getCon().prepareStatement(query);  
               pst.setLong(1, id); 
               }            
            ResultSet rs = pst.executeQuery();
           rs.next(); 
               count = rs.getInt("kullanici_sarki_list_count");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
      public SarkiDao getSarkiDaoo() {
        if(this.sarkiDaoo == null)
            this.sarkiDaoo = new SarkiDao();
        return sarkiDaoo;
    }
    
    
}
