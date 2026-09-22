import java.util.ArrayList;

public class Domaine {

    private String nom;
    private ArrayList<Relation> relations;

    public Domaine(String nom) {
        this.nom = nom;
        this.relations = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void ajouterRelation(Relation relation) {
        if (!relations.contains(relation)) {
            relations.add(relation);
        }
    }

    public ArrayList<Relation> getRelations() {
        return relations;
    }

    public void afficherRelations() {
        System.out.println("Domaine : " + nom);
        System.out.println("Relations utilisant ce domaine :");

        for (Relation relation : relations) {
            System.out.println("- " + relation.getNom());
        }
    }
}