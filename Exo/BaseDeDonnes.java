import java.util.ArrayList;

public class BaseDeDonnees {

    private String nom;

    private ArrayList<Relation> relations;

    public BaseDeDonnees(String nom) {
        this.nom = nom;
        this.relations = new ArrayList<>();
    }

    public void ajouterRelation(Relation relation) {

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

    public void afficherRelations() {

        System.out.println(
            "\nBase de données : " + nom
        );

        for (Relation relation : relations) {

            System.out.println(
                "- " + relation.getNom()
            );
        }
    }
}