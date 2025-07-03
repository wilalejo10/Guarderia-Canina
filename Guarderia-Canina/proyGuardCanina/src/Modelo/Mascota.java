
package Modelo;

/**
 *
 * @author User
 */
public class Mascota {
    private String nom, id, raza;
    private int edad;

    public Mascota(String nom, String id, String raza, int edad) {
        this.nom = nom;
        this.id = id;
        this.raza = raza;
        this.edad = edad;
    }
    public Mascota() {
        this.nom = "";
        this.id = "";
        this.raza = "";
        this.edad = 0;
    }

    @Override
    public String toString() {
        return "\nDatos Mascota\nRaza: " + raza+ "\tId: " + id +
                "\t Nombre" + nom + "\tEdad: " + edad;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
}
