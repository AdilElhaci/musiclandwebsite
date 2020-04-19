package controller;

import dao.ResimDocumentDao;
import entity.Kullanici;
import entity.ResimDocument;
import entity.Sarkici;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

@Named
@SessionScoped
public class RseimDocumentControllers implements Serializable, Validator {

    private ResimDocument docment;
    private ResimDocumentDao resimdDocDao;
    private List<ResimDocument> docList;
    private Part part;
    // bu yolu degistiriniz uploadlar calismasi icin 
    private final String uploadTo = "C:\\Users\\adel\\Desktop\\Muzik land\\muzikLand\\Pictures\\";
    private String eror_mesage;
    private int sonuc;

    @Inject
    private UyelikYapanlarControllers uye;

    public RseimDocumentControllers() {

    }

    public void resim_reset() {
        this.setEror_mesage(null);
        this.setPart(null);
    }

    public void upload(Long id) {
        try {
            if (this.getPart() != null) {
                InputStream input = getPart().getInputStream();
                File file = new File(uploadTo + getPart().getSubmittedFileName());
                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                SimpleDateFormat uploadTime = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
                String d = uploadTime.format(new Date());
                String newName = d + "&&" + file.getName();
                File newFile = new File(uploadTo + newName);
                file.renameTo(newFile);
                this.docment = this.getDocment();
                docment.setDoc_id(id);
                docment.setFileName(newName);
                docment.setFilePath(file.getParent());
                docment.setFileType(getPart().getContentType());
                sonuc = this.getResimdDocDao().insert(docment);
                this.setEror_mesage(null);
            }
        } catch (IOException ex) {

        }
    }

    public void updatePath(Object o) {
        try {
            if (this.getPart() != null) {
                InputStream input = this.getPart().getInputStream();
                File file = new File(uploadTo + this.getPart().getSubmittedFileName());
                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
                SimpleDateFormat uploadTime = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
                String d = uploadTime.format(new Date());
                String newName = d + "&&" + file.getName();
                File newFile = new File(uploadTo + newName);
                file.renameTo(newFile);

                if (o instanceof Kullanici) {
                    docment = ((Kullanici) o).getYetki().getResim();
                } else if (o instanceof Sarkici) {
                    docment = ((Sarkici) o).getSarkici_resim();
                }
                docment.setFileName(newName);
                docment.setFilePath(file.getParent());
                docment.setFileType(this.getPart().getContentType());
                sonuc = this.getResimdDocDao().update(docment);
                this.setEror_mesage(null);
            }
        } catch (IOException ex) {

        }
    }

    public void delete() {
        this.setPart(null);
        if (this.uye.getUpdateToKullanici().getYetki().getResim().getDoc_id() != null) {
            this.getResimdDocDao().delete(this.uye.getUpdateToKullanici().getYetki().getResim().getDoc_id());
        }
        this.uye.getUpdateToKullanici().getYetki().setResim(null);
        this.setEror_mesage(null);

    }

    @Override
    public void validate(FacesContext arg0, UIComponent arg1, Object arg2) throws ValidatorException {
        Part p = (Part) arg2;
        if (p == null) {
            this.setEror_mesage("lutfen bir dosya seciniz");
        } else if (p.getSize() > 3145728) {
            this.setEror_mesage("max boyut 3 MB");
        } else if (!"image/jpeg".equals(p.getContentType()) && !"image/png".equals(p.getContentType())) {
            this.setEror_mesage("dosya turu sadece jpeg olacak");
        }
    }

    public ResimDocument getDocment() {
        if (this.docment == null) {
            this.docment = new ResimDocument();
        }
        return docment;
    }

    public ResimDocumentDao getResimdDocDao() {
        if (this.resimdDocDao == null) {
            this.resimdDocDao = new ResimDocumentDao();
        }
        return resimdDocDao;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Part getPart() {
        return part;
    }

    public String getUploadTo() {
        return uploadTo;
    }

    public void setEror_mesage(String eror_mesage) {
        this.eror_mesage = eror_mesage;
    }

    public String getEror_mesage() {
        return eror_mesage;
    }

    public void setSonuc(int sonuc) {
        this.sonuc = sonuc;
    }

    public int getSonuc() {
        return sonuc;
    }

}
