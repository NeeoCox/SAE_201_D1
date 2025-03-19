package metier.persistence;

/**
 * Établit la liaison entre la classe Compétence et la classe DPS.
 * DPS (0..*) ---a besoin de---&gt; (1..*) Competence.
 * Besoin est un attribut porté.
 * 
 * @author Maël COIGNARD, Adrien COUDIERE, Léa VIMART - Groupe D1
 */
public class Besoin {
    /**
     * Le nombre de compétences dont a besoin le DPS.
     */
    private int nombre;

    /**
     * La compétence dont a besoin le DPS.
     */
    private Competence laCompetence;
    /**
     * L'intitulé de la compétence dont a besoin le DPS.
     */
    private String intituleCompetence;
    
    /**
     * Le DPS qui a besoin de la compétence.
     */
    private DPS leDPS;
    /**
     * L'identifiant du DPS qui a besoin de la compétence.
     */
    private long idDPS;


    /**
     * Accesseur de l'attribut nombre.
     * @return Le nombre de compétences dont a besoin le DPS.
     */
    public int getNombre() {
        return this.nombre;
    }
    
    /**
     * Mutateur de l'attribut nombre.
     * @param unNombre Le nouveau nombre de compétences dont a besoin le DPS.
     */
    public void setNombre(int unNombre) {
        this.nombre = unNombre;
    }

    
    /**
     * Accesseur de l'attribut laCompetence.
     * @return La compétence dont a besoin le DPS.
     */
    public Competence getLaCompetence() {
        return this.laCompetence;
    }

    /**
     * Mutateur de l'attribut laCompetence.
     * @param uneCompetence La nouvelle compétence dont a besoin le DPS.
     */
    public void setLaCompetence(Competence uneCompetence) {
        this.laCompetence = uneCompetence;
    }

    
    /**
     * Accesseur de l'attribut intituleCompetence.
     * @return L'intitulé de la compétence dont a besoin le DPS.
     */
    public String getIntituleCompetence() {
        return this.intituleCompetence;
    }

    /**
     * Mutateur de l'attribut intituleCompetence.
     * @param unIntituleCompetence Le nouvel intitulé de la compétence dont a besoin le DPS.
     */
    public void setIntituleCompetence(String unIntituleCompetence) {
        this.intituleCompetence = unIntituleCompetence;
    }


    /**
     * Accesseur de l'attribut leDPS.
     * @return Le DPS qui a besoin de la compétence.
     */
    public DPS getLeDPS() {
        return this.leDPS;
    }

    /**
     * Mutateur de l'attribut leDPS.
     * @param unDPS Le nouveau DPS qui a besoin de la compétence.
     */
    public void setLeDPS(DPS unDPS) {
        this.leDPS = unDPS;
    }

    
    /**
     * Accesseur de l'attribut idDPS.
     * @return L'identifiant du DPS qui a besoin de la compétence.
     */
    public long getIdDPS() {
        return this.idDPS;
    }

    /**
     * Mutateur de l'attribut idDPS.
     * @param unIdDPS Le nouvel identifiant du DPS qui a besoin de la compétence.
     */
    public void setIdDPS(long unIdDPS) {
        this.idDPS = unIdDPS;
    }
}