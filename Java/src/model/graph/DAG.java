package model.graph;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
public class DAG {
    // Structure du graphe : compétence -> liste des compétences préalables
    private Map<String, List<String>> competencesDep = new HashMap<>();

    private String[] COMPETENCES = {"PSE1", "PSE2", "SSA", "VPSP", "CE", "CP", "CO", "PBF", "PBC"};

    public DAG(){
        initDep();
    }

    /**
     * Ajoute une dépendance entre deux compétences 
     * @param requis la compétence requise pour acquérir une autre compétence
     * @param pourAvoir
     */
    public void addDependence(String requis, String pourAvoir){
        if((requis == null || pourAvoir == null) || (requis.isEmpty() || pourAvoir.isEmpty())) {
            throw new IllegalArgumentException("Les compétences ne peuvent pas être nulles");
        }
        else if(!estUneCompetence(requis) || !estUneCompetence(pourAvoir)){
            throw new IllegalArgumentException("N'est pas une compétences pour les secouristes");
        }
        else{
            List<String> listeDep = competencesDep.get(requis);
            if(listeDep != null) {    
                competencesDep.put(requis, listeDep);
            }
            else{
                listeDep = new ArrayList<>();
                listeDep.add(pourAvoir);
                competencesDep.put(requis, new ArrayList<>());
            } 
        }
    }

    public void initDep(){
        addDependence("CO", "CP");
        addDependence("CP", "CE");
        addDependence("CE", "PSE2");
        addDependence("PSE2", "PSE1");
        addDependence("SSA", "PSE1");
        addDependence("VPSP", "PSE2");
        addDependence("PBF", "PBC");
    }

    /**
     * renvoie vrai si la compétences est possédée
     * @param competences la compétences à vérifier
     * @return true si la compétence est possédée, false sinon
     */
    public boolean estPossedee(String competences){
        boolean possede = false;
        if(competences == null || competences.isEmpty()) {
            throw new IllegalArgumentException("La compétence ne peut pas être nulle ou vide");
        }
        else if(!estUneCompetence(competences)){
            throw new IllegalArgumentException("N'est pas une compétences pour les secouristes");
        }
        for(String comp : COMPETENCES){
            if(comp.equals(competences)){
                possede = true;
                break;
            }
        }
        return possede;
    }

    public boolean estUneCompetence(String competences){
        boolean estCompetence = false;
        if(competences == null || competences.isEmpty()) {
            throw new IllegalArgumentException("La compétence ne peut pas être nulle ou vide");
        }
        for(String comp : COMPETENCES){
            if(comp.equals(competences)){
                estCompetence = true;
                break;
            }
        }
        return estCompetence;
    }
    /**
     * Renvoi la liste des compétences requisee pour aquérir une compétence donnée
     * @param competences la compétences pour laquelle on veut connaitre les compétences requises
     * @return la liste des compétences requises pour aquérir la compétence
     */
    public List<String> getCompetencesRequise(String competences){
        List<String> listeRequise;
        if(competences == null || competences.isEmpty()) {
            throw new IllegalArgumentException("La compétence ne peut pas être nulle ou vide");
        }
        else if(!estUneCompetence(competences)){
            throw new IllegalArgumentException("N'est pas une compétences pour les secouristes");
        }
        listeRequise = competencesDep.get(competences);
        return listeRequise;
    }

    
    /**
     * Vérifie si les compétences requises sont possedées pour acquérir une compétences donnée
     * @param competences la compétence pour laquelle on veut vérifier les compétences requises
     * @return true si les compétences requises sont possédées, false sinon
     */
    public boolean respectDepCompetences(String competences){
        boolean respect = false;
        if(competences == null || competences.isEmpty()) {
            throw new IllegalArgumentException("La compétence ne peut pas être nulle ou vide");
        }
        else if(!estUneCompetence(competences)){
            throw new IllegalArgumentException("N'est pas une compétences pour les secouristes");
        }
        List<String> listeDep = getCompetencesRequise(competences);
        if(listeDep == null || listeDep.isEmpty()) {
            respect = true; //Pas de dépendances donc réspectée
        } else {
            for(String dep : listeDep) {
                if(estPossedee(dep)) {
                    respect = true; //Au moins une dépendance est possédée
                    if(listeDep.size() > 1) {
                        for(String dep2 : listeDep) {
                            if(!estPossedee(dep2)) {
                                respect = false; //Une dépendance n'est pas possédée
                                break;
                            }
                        }
                    }
                } else {
                    respect = false; //Une dépendance n'est pas possédée
                    break;
                }
            }
        }
        return respect;
    }

    public void addCompetences(String comp){
        
    }
}