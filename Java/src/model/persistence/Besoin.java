package model.persistence;

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
     * Constructeur de la classe Besoin.
     * @param unDPS Le DPS qui a besoin de la compétence.
     * @param uneCompetence La compétence dont a besoin le DPS.
     * @param unNombre Le nombre de compétences dont a besoin le DPS.
     */
    public Besoin(int unNombre, Competence uneCompetence, DPS unDPS) {
        this.nombre = unNombre;
        this.laCompetence = uneCompetence;
        this.intituleCompetence = uneCompetence.getIntitule();
        this.leDPS = unDPS;
        this.idDPS = unDPS.getId();
    }

    /**
     * Constructeur de la classe Besoin.
     * @param unIdDPS L'identifiant du DPS qui a besoin de la compétence.
     * @param unIntituleCompetence L'intitulé de la compétence dont a besoin le DPS.
     * @param unNombre Le nombre de compétences dont a besoin le DPS.
     */
    public Besoin(int unNombre, String unIntituleCompetence, long unIdDPS) {
        this.nombre = unNombre;
        this.laCompetence = null;
        this.intituleCompetence = unIntituleCompetence;
        this.leDPS = null;
        this.idDPS = unIdDPS;
    }


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

    @Override
    public String toString() {
        return "Besoin{" +
                "nombre=" + nombre +
                ", laCompetence=" + laCompetence +
                ", intituleCompetence='" + intituleCompetence + '\'' +
                ", leDPS=" + leDPS +
                ", idDPS=" + idDPS +
                '}';
    }


    /**
     * Vérifie si ce besoin concerne une compétence donnée.
     * @param intitule L'intitulé de la compétence recherchée.
     * @return true si ce besoin concerne cette compétence, false sinon.
     */
    public boolean concerneCompetence(String intitule) {
        return (laCompetence != null && laCompetence.getIntitule().equals(intitule))
            || (intituleCompetence != null && intituleCompetence.equals(intitule));
    }
}