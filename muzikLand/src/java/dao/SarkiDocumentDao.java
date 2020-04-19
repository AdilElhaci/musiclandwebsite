
package dao;

import entity.Sarki;
import entity.SarkiDocument;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SarkiDocumentDao extends Connector{
    
    private SarkiDao sarkiDao;
    public List<SarkiDocument> findAll(int page_Num,int sarki_Sayisi,String aranacak,Long cat_id){   
        page_Num=(page_Num -1) * sarki_Sayisi;
        List<SarkiDocument> docList = new ArrayList<>();
        try {
            PreparedStatement pst;
            ResultSet rs = null;
            String query ="select * from sarki_document";                    
            if(aranacak !=null && cat_id == null){
            query += " where file_id in(select sarki_id from sarki where tmp_name like ? ) order by file_id asc limit ?,? ";
            pst = this.getCon().prepareStatement(query);
            pst.setString(1, "%"+aranacak+"%");
            pst.setInt(2, page_Num);
            pst.setInt(3, sarki_Sayisi);
            rs = pst.executeQuery();
            }
            else if(aranacak ==null && cat_id != null){
            query += " where file_id in(select sarki_id from sarki_category where category_id = ? ) order by file_id asc limit ?,? ";
            pst = this.getCon().prepareStatement(query);
            pst.setLong(1, cat_id);
            pst.setInt(2, page_Num);
            pst.setInt(3, sarki_Sayisi);
            rs = pst.executeQuery();
            }
            else if(aranacak !=null && cat_id != null){
            query += " where file_id in(select sarki_id from sarki where tmp_name like ? )"
                    + " and file_id in( select sarki_id from sarki_category where category_id = ?) order by file_id asc limit ?,? ";
            pst = this.getCon().prepareStatement(query);
            pst.setString(1, "%"+aranacak+"%");
            pst.setLong(2, cat_id);
            pst.setInt(3, page_Num);
            pst.setInt(4, sarki_Sayisi);
            rs = pst.executeQuery();
            }
            else{
            query += " order by file_id asc limit ?,? ";
            pst = this.getCon().prepareStatement(query);
            pst.setInt(1, page_Num);
            pst.setInt(2, sarki_Sayisi);
            rs = pst.executeQuery();
            }                        
            while(rs.next()){
                SarkiDocument docment = new SarkiDocument();
                docment.setDoc_id(rs.getLong("file_id"));
                docment.setFileName(rs.getString("file_name"));
                docment.setFile_size(rs.getLong("file_size"));
                docment.setFilePath(rs.getString("file_path"));
                docment.setFileType(rs.getString("file_type"));
                docment.setSarki(this.getSarkiDao().findSarki(rs.getLong("file_id")));
                docList.add(docment);                
            }            
        } catch (Exception e) {
        }        
        return docList;
    }
    public int insert(SarkiDocument docment){
        int i = 0;
        try {
            PreparedStatement pst;
            String query ="insert into sarki_document(file_name,file_size,file_path,file_type) values(?,?,?,?)";
            pst = this.getCon().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, docment.getFileName());
            pst.setLong(2, docment.getFile_size());
            pst.setString(3, docment.getFilePath());
            pst.setString(4, docment.getFileType());
           i = pst.executeUpdate();
            
            Long sarkiId = null;
            ResultSet gk = pst.getGeneratedKeys();
            if (gk.next())
                sarkiId = gk.getLong(1);            
            docment.setDoc_id(sarkiId);
             this.getSarkiDao().create(docment);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return i;
    }
    public int update(SarkiDocument docment){
        int i = 0;
             Sarki s = docment.getSarki();
             s.setTmp_name(docment.getFileName());
        try {            
            PreparedStatement pst;
            String query ="update sarki_document set file_name = ?,file_size = ?,file_path = ? , file_type = ? where file_id = ?";
            pst = this.getCon().prepareStatement(query); 
             pst.setString(1, docment.getFileName());
             pst.setLong(2, docment.getFile_size());
            pst.setString(3, docment.getFilePath());
            pst.setString(4, docment.getFileType());
            pst.setLong(5, docment.getDoc_id());
           i = pst.executeUpdate();            
           this.getSarkiDao().update(s);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return i;
    }
    
    public int delete(SarkiDocument docment){
        int i = 0;
        try {
            PreparedStatement pst;
            String query ="delete from sarki_document where file_id = ?";
            pst = this.getCon().prepareStatement(query);
            pst.setLong(1, docment.getDoc_id());
           i = pst.executeUpdate();            
           this.getSarkiDao().delete(docment.getSarki());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return i;
    }
    
        public int findCount(String aranacak,Long cat_id){           
        return this.getSarkiDao().findCount(aranacak,cat_id);
    }
    public SarkiDao getSarkiDao() {
        if(this.sarkiDao == null)
            this.sarkiDao = new SarkiDao();
        return sarkiDao;
    }

    public SarkiDocument find(long id) {
        SarkiDocument docment = null;
         try {
            PreparedStatement pst;
            String query ="select * from sarki_document where file_id = ?";
            pst = this.getCon().prepareStatement(query);
            pst.setLong(1,id);                       
            ResultSet rs = pst.executeQuery();
            rs.next();
                 docment = new SarkiDocument();
                docment.setDoc_id(rs.getLong("file_id"));
                docment.setFileName(rs.getString("file_name"));
                docment.setFile_size(rs.getLong("file_size"));
                docment.setFilePath(rs.getString("file_path"));
                docment.setFileType(rs.getString("file_type"));
                                                      
        } catch (Exception e) {
        }        
        return docment;
        
    }
    
}
