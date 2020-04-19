
package dao;

import entity.ResimDocument;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ResimDocumentDao extends Connector{
    public ResimDocument find(Long id){
        ResimDocument docment = null;
        try {
            PreparedStatement pst;
            String query ="select * from resim_document where id = ?";            
            pst = this.getCon().prepareStatement(query);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            rs.next();
                docment = new ResimDocument();
                docment.setDoc_id(rs.getLong("id"));  
                docment.setFileName(rs.getString("file_name"));
                docment.setFilePath(rs.getString("file_path"));
                docment.setFileType(rs.getString("file_type"));
                docment.setLast_update(rs.getDate("last_update"));
        } catch (Exception e) {
        }
        return docment;
    }
    public int insert(ResimDocument docment){
        int i = 0;
        try {
            PreparedStatement pst;
            String query ="insert into resim_document(id,file_name,file_path,file_type) values(?,?,?,?)";
            pst = this.getCon().prepareStatement(query);
            pst.setLong(1, docment.getDoc_id());
            pst.setString(2, docment.getFileName());
            pst.setString(3, docment.getFilePath());
            pst.setString(4, docment.getFileType());
           i = pst.executeUpdate();            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return i;
    }
    public int update(ResimDocument docment){
        int i = 0;
        try {            
            PreparedStatement pst;
            String query ="update resim_document set file_name = ? , file_path = ? , file_type = ? where id = ?";
            pst = this.getCon().prepareStatement(query); 
            pst.setString(1, docment.getFileName());
            pst.setString(2, docment.getFilePath());
            pst.setString(3, docment.getFileType());
            pst.setLong(4, docment.getDoc_id());
           i = pst.executeUpdate(); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return i;
    }
    
    public int delete(Long id){
        int i = 0;
        try {
            PreparedStatement pst;
            String query ="delete from resim_document where id = ?";
            pst = this.getCon().prepareStatement(query);
            pst.setLong(1, id);
           i = pst.executeUpdate();              
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return i;
    }
    
}
