
package dao;

import entity.Ulke;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UlkeDao extends Connector{
    public void create(Ulke konum){
         
        try {
            PreparedStatement pst = this.getCon().prepareStatement("insert into ulke(ulke) values(?)");
            pst.setString(1,konum.getUlke() );
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
   
    public List<Ulke> findAll(int page_Num,int sarki_Sayisi,String aranacak){
        List<Ulke> uList = new ArrayList<>();
        page_Num=(page_Num -1) * sarki_Sayisi;
        String query = "select * from ulke";
        try {
            PreparedStatement pst;
            if(aranacak != null){
              query +=" where ulke like ? order by id asc limit ?,?";
             pst =this.getCon().prepareStatement(query);
            pst.setString(1, "%"+aranacak+"%");
             pst.setInt(2, page_Num);
            pst.setInt(3, sarki_Sayisi);
            }
            else{
             query+=" order by id asc limit ?,?";
            pst =this.getCon().prepareStatement(query);
            pst.setInt(1, page_Num);
            pst.setInt(2, sarki_Sayisi);
            }            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Ulke us = new Ulke();
                us.setUlke_id(rs.getLong("id"));
                us.setUlke(rs.getString("ulke"));
                us.setLast_update(rs.getDate("last_update"));
                uList.add(us);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return uList;
    }
    
    public void delete(Ulke ulke) {
        try {
           
            PreparedStatement pst = this.getCon().prepareStatement("delete from ulke where id=?");
            pst.setLong(1, ulke.getUlke_id());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(Ulke ulke) {
         try {
             PreparedStatement pst = this.getCon().prepareStatement("update ulke set ulke=? where id=?");
             pst.setString(1, ulke.getUlke());
             pst.setLong(2,  ulke.getUlke_id());
             pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Ulke findulkeBy_id(Long id) {
          Ulke ulke = null;

        try {
            PreparedStatement pst = this.getCon().prepareStatement("select * from ulke where id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            ulke = new Ulke();
            ulke.setUlke_id(rs.getLong("id"));
            ulke.setUlke(rs.getString("ulke"));
           ulke.setLast_update(rs.getDate("last_update"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return ulke;
    }
    
    public int findCount(String aranacak) {
        int count = 0;
        String query = "select count(id) as ulke_count from ulke";
        try {
            PreparedStatement st ;
            if(aranacak !=null){
                query +=" where ulke like ?";
            st = this.getCon().prepareStatement(query);     
            st.setString(1, "%"+aranacak+"%");
            }
            else
            st = this.getCon().prepareStatement(query);            
            ResultSet rs = st.executeQuery();            
           rs.next(); 
               count = rs.getInt("ulke_count");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
}
