
package entity;

import java.sql.Date;

public class SarkiDocument {
    private Long doc_id;
    private String fileName;
    private Long file_size;
    private String filePath;
    private String fileType;
    private Sarki sarki;

    public SarkiDocument() {
        this.sarki = new Sarki();
    }    

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
    
    public Sarki getSarki() {
        if(this.sarki == null)
                this.sarki = new Sarki();
        return sarki;
    }

    public void setDoc_id(Long doc_id) {
        this.doc_id = doc_id;
    }

    public Long getDoc_id() {
        return doc_id;
    }

    public void setFile_size(Long file_size) {
        this.file_size = file_size;
    }

    public Long getFile_size() {
        return file_size;
    }
    
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }
    
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    
    public void setSarki(Sarki sarki) {
        this.sarki = sarki;
    }

}
