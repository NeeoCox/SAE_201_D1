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


    /**
     * Vérifie si l'intitulé correspond à celui de la compétence.
     * @param intitule L'intitulé à comparer.
     * @return true si égal, false sinon.
     */
    public boolean equalsIntitule(String intitule) {
        return this.intitule != null && this.intitule.equals(intitule);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Competence that = (Competence) obj;
        return intitule != null && intitule.equals(that.intitule);
    }

    @Override
    public int hashCode() {
        return intitule != null ? intitule.hashCode() : 0;
    }

}