package dao;



import entity.Dil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DilDao extends Connector {

      public List<Dil> findAll(int page_Num,int sarki_Sayisi,String aranacak) {
        List<Dil> dlist = new ArrayList<>();
        page_Num=(page_Num -1) * sarki_Sayisi;
        String query = "select * from dil";
        try {
            PreparedStatement  pst;
            if(aranacak != null){
                query +=" where name like ? order by dil_id asc limit ?,?";
            pst=this.getCon().prepareStatement(query);
            pst.setString(1, "%"+aranacak+"%");
            pst.setInt(2, page_Num);
            pst.setInt(3, sarki_Sayisi); 
            }
            else{
             query += " order by dil_id asc limit ?,?";
            pst=this.getCon().prepareStatement(query);
            pst.setInt(1, page_Num);
            pst.setInt(2, sarki_Sayisi);
            }
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Dil dil = new Dil();
                dil.setDil_id(rs.getLong("dil_id"));
                dil.setDil_name(rs.getString("name"));
                dil.setLast_update(rs.getDate("last_update"));
                dlist.add(dil);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return dlist;
    }

    public int creat(Dil dil) {
       
           int i = 0;
        try {
            PreparedStatement pst = this.getCon().prepareStatement("insert into dil (name) values(?)");
            pst.setString(1, dil.getDil_name());
             i = pst.executeUpdate();
        } catch (Exception e) {
        }
        return i;
    }

    public void update(Dil dil) {
        try {
            PreparedStatement pst = this.getCon().prepareStatement("update dil set name=? where dil_id=?");
            pst.setString(1, dil.getDil_name());
            pst.setLong(2,dil.getDil_id());
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(Dil dil) {
         try {
            PreparedStatement pst = this.getCon().prepareStatement("delete from dil where dil_id=?");
            pst.setLong(1,dil.getDil_id());
            pst.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
      public Dil find_Id(Long id) {
        Dil l = null;

        try {
            PreparedStatement pst = this.getCon().prepareStatement("select * from dil where dil_id=?");
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
            l = new Dil();
            l.setDil_id(rs.getLong("dil_id"));
            l.setDil_name(rs.getString("name"));
            l.setLast_update(rs.getDate("last_update"));
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return l;
    }

      public int findCount(String aranacak) {
        int count = 0;
        String query = "select count(dil_id) as dil_count from dil";
        try {
            PreparedStatement st;
            if(aranacak != null){
            query +=" where name like ?";
            st=this.getCon().prepareStatement(query);
            st.setString(1, "%"+aranacak+"%");
            }
            else
            st = this.getCon().prepareStatement(query);            
            ResultSet rs = st.executeQuery();
           rs.next(); 
               count = rs.getInt("dil_count");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
      
}


