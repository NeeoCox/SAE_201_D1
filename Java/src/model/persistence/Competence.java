package model.persistence;

/**
 * Représente une compétence d'un secouriste.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class Competence {
    /**
     * L'intitulé de la compétence.
     */
    private String intitule;

    
    /**
     * Accesseur de l'attribut intitule.
     * @return L'intitulé de la compétence.
     */
    public String getIntitule() {
        return this.intitule;
    }

    /**
     * Mutateur de l'attribut intitule.
     * @param unIntitule Le nouvel intitulé de la compétence.
     */
    public void setIntitule(String unIntitule) {
        this.intitule = unIntitule;
    }

    
    @Override
    public String toString() {
        return "Compétence{" +
                "intitule='" + intitule + '\'' +
                '}';
    }
}