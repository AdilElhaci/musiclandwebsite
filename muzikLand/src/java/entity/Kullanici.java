
package entity;

import java.util.Objects;

public class Kullanici {
    private Long Kullanici_id;
    private String username;
    private String password;    
    private KullaniciBilgileri yetki;// her kullanicinin kendi bilgilerini ve yetki seviyesini içerir

    public Kullanici() {
        this.yetki = new KullaniciBilgileri();
    }

    public Long getKullanici_id() {
        return Kullanici_id;
    }

    public void setKullanici_id(Long Kullanici_id) {
        this.Kullanici_id = Kullanici_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public KullaniciBilgileri getYetki() {
        return yetki;
    }

    public void setYetki(KullaniciBilgileri yetki) {
        this.yetki = yetki;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.Kullanici_id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kullanici other = (Kullanici) obj;
        if (!Objects.equals(this.Kullanici_id, other.Kullanici_id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Kullanici{" + "Kullanici_id=" + Kullanici_id + ", username=" + username + ", password=" + password + ", yetki=" + yetki + '}';
    }
    
}
