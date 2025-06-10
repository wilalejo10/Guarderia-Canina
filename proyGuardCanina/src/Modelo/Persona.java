
package Modelo;

/**
 *
 * @author User
 */
public abstract class Persona {
    protected String id, nom, tel, email;

    public Persona(String id, String nom, String tel, String email) {
        this.id = id;
        this.nom = nom;
        this.tel = tel;
        this.email = email;
    }
    public Persona() {
        this.id = "";
        this.nom = "";
        this.tel = "";
        this.email = "";
    }

    @Override
    public String toString() {
        return "\nId: " + id + "\nNombre: " + nom + "\nTelefono: " + tel + "\nEmail: " + email;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
