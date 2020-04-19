package dao;

import entity.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KullaniciDao extends Connector {
    
    private KullaniciBilgileriDao kullaniciBigiDao;
    private Long kullanici_bilgi_id;

    public Kullanici find(String username, String password) { // login kontrol icin ve update'lerde kullaniliyor 
        Kullanici girisYapan = new Kullanici();
        try {
            String query = "select * from uyelik_yapan where username=? and password=?";
            PreparedStatement pst = this.getCon().prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            rs.next();
            girisYapan.setKullanici_id(rs.getLong("kullanici_id"));
            girisYapan.setUsername(rs.getString("username"));
            girisYapan.setPassword(rs.getString("password"));
            girisYapan.setYetki(this.getKullaniciBigiDao().find(rs.getLong("kullanici")));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return girisYapan;
    }
    public List<Kullanici> findAll(int page_Num ,int kullanici_Sayi,int yetkiSaviye,String aranacak){ // bana kullanici listini dondurecek
        List<Kullanici> kullaniciList = new ArrayList<>();
        page_Num=(page_Num -1) * kullanici_Sayi;
        String query = "select * from uyelik_yapan";
        try {
            PreparedStatement pst ;
            
            if(aranacak !=null){
                query += " where username like ? and kullanici in(select id from uyelik_yapan_bilgileri where uye_id = ?)"
                      + " order by kullanici_id asc limit ?,? ";
              pst  = this.getCon().prepareStatement(query);
            pst.setString(1, "%"+aranacak+"%");
            pst.setInt(2, yetkiSaviye);
            pst.setInt(3, page_Num);
            pst.setInt(4, kullanici_Sayi);
            }
            else{
                 query += " where kullanici in(select id from uyelik_yapan_bilgileri where uye_id = ?)"
                       + " order by kullanici_id asc limit ?,? ";
               pst = this.getCon().prepareStatement(query);
            pst.setInt(1, yetkiSaviye);
            pst.setInt(2, page_Num);
            pst.setInt(3, kullanici_Sayi);
            }
            
            ResultSet rs = pst.executeQuery();
            while (rs.next()){
                Kullanici kullanici = new Kullanici();               
                kullanici.setKullanici_id(rs.getLong("kullanici_id"));
                kullanici.setUsername(rs.getString("username"));
                kullanici.setPassword(rs.getString("password"));
                kullanici.setYetki(this.getKullaniciBigiDao().find(rs.getLong("kullanici")));
                kullaniciList.add(kullanici);  
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return kullaniciList;
    }  
    public int create(Kullanici kullanici) {
        int i = 0;
        try {
           Long id =  this.getKullaniciBigiDao().create(kullanici);//once kullaniciBilgiDao^da bilgi eklenecek ve
           // oradan bana bir id return olacak bu id 'i kullanici'ye atariz                       
           String query = "insert into uyelik_yapan (username,password,kullanici) values (?,?,?)";
           PreparedStatement pst = this.getCon().prepareStatement(query);
           pst.setString(1, kullanici.getUsername());
           pst.setString(2, kullanici.getPassword());
           pst.setLong(3, id);
           i = pst.executeUpdate();        
           this.setKullanici_bilgi_id(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }        
        return i;
    }

    public int delete(Kullanici kullanici) {
        int i = 0;
        Long id = kullanici.getYetki().getId();
        try {            
            PreparedStatement pst = this.getCon().prepareStatement("delete from uyelik_yapan where kullanici_id=?");
            pst.setLong(1, kullanici.getKullanici_id());            
            i = pst.executeUpdate();
            if(i==1)
            this.getKullaniciBigiDao().delete(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }

    public int update(Kullanici kullanici) {
        int i = 0;
        try {
            this.getKullaniciBigiDao().update(kullanici);
            String query = "update uyelik_yapan set username=? , password = ? where kullanici_id = ? " ;
            PreparedStatement pst = this.getCon().prepareStatement(query);
            pst.setString(1, kullanici.getUsername());
            pst.setString(2, kullanici.getPassword());                        
            pst.setLong(3, kullanici.getKullanici_id());
            i = pst.executeUpdate();            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return i;
    }         
    
     public int findCount(String aranacak,int yetkiSaviye) {
        int count = 0;
        String query = "select count(kullanici_id) as kullanici_count from uyelik_yapan"
                + " where kullanici in(select id from uyelik_yapan_bilgileri where uye_id = ?)";
        try {
            PreparedStatement pst;
            if(aranacak != null){
                query +=" and username like ?";
                pst  = this.getCon().prepareStatement(query);
                pst.setInt(1, yetkiSaviye);
              pst.setString(2, "%"+aranacak+"%");
            }
            else{
                pst = this.getCon().prepareStatement(query);  
                pst.setInt(1, yetkiSaviye);
            }
            
            ResultSet rs = pst.executeQuery();
           rs.next(); 
               count = rs.getInt("kullanici_count");               
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }  
    
    public KullaniciBilgileriDao getKullaniciBigiDao() {
        if(this.kullaniciBigiDao == null)
            this.kullaniciBigiDao = new KullaniciBilgileriDao();
        return kullaniciBigiDao;
    }
    public void setKullanici_bilgi_id(Long kullanici_bilgi_id) {
        this.kullanici_bilgi_id = kullanici_bilgi_id;
    }

    public Long getKullanici_bilgi_id() {
        return kullanici_bilgi_id;
    }
    
}
