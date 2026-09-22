import java.util.ArrayList;

public class BaseDeDonnees {

    private String nom;

    private ArrayList<Relation> relations;

    public BaseDeDonnees(String nom) {
        this.nom = nom;
        this.relations = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Relation> getRelations() {
        return relations;
    }

    public void ajouterRelation(Relation relation) {

        if (relation == null) {
            System.out.println("Relation invalide.");
            return;
        }

        if (chercherRelation(relation.getNom()) != null) {
            System.out.println("La relation '" + relation.getNom() + "' existe déjà.");
            return;
        }

        relations.add(relation);

        System.out.println(
            "Relation " +
            relation.getNom() +
            " ajoutée à la base."
        );
    }

    public Relation chercherRelation(String nom) {

        for (Relation relation : relations) {

            if (relation.getNom().equals(nom)) {
                return relation;
            }
        }

        return null;
    }

    public boolean supprimerRelation(String nom) {

        Relation relationASupprimer = chercherRelation(nom);

        if (relationASupprimer == null) {
            System.out.println("Relation introuvable.");
            return false;
        }

        relations.remove(relationASupprimer);
        System.out.println("Relation '" + nom + "' supprimée.");
        return true;
    }

    public void afficherRelations() {

        System.out.println(
            "\nBase de données : " + nom
        );

        if (relations.isEmpty()) {
            System.out.println("Aucune relation pour le moment.");
            return;
        }

        for (Relation relation : relations) {

            System.out.println(
                "- " + relation.getNom()
            );
        }
    }
}