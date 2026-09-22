import java.util.ArrayList;

public class Relation {

    private String nom;

    private ArrayList<Attribut> attributs;

    private ArrayList<ArrayList<Integer>> donnees;

    public Relation(String nom) {
        this.nom = nom;
        this.attributs = new ArrayList<>();
        this.donnees = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    // =========================
    // AJOUTER UN ATTRIBUT
    // =========================

    public void ajouterAttribut(Attribut attribut) {

        attributs.add(attribut);

        // Le domaine connaît cette relation
        attribut.getDomaine().ajouterRelation(this);
    }

    // =========================
    // CREATE / INSERT
    // =========================

    public void insert(int... valeurs) {

        if (valeurs.length != attributs.size()) {
            System.out.println(
                "Erreur : nombre de valeurs incorrect."
            );
            return;
        }

        ArrayList<Integer> ligne = new ArrayList<>();

        for (int valeur : valeurs) {
            ligne.add(valeur);
        }

        donnees.add(ligne);

        System.out.println("Ligne insérée avec succès.");
    }

    // =========================
    // READ / SELECT
    // =========================

    public void select() {

        afficherEntete();

        for (ArrayList<Integer> ligne : donnees) {

            for (Integer valeur : ligne) {
                System.out.printf("%5d", valeur);
            }

            System.out.println();
        }
    }

    // =========================
    // UPDATE
    // =========================

    public void update(
        int ligneIndex,
        int colonneIndex,
        int nouvelleValeur
    ) {

        if (ligneIndex < 0 ||
            ligneIndex >= donnees.size()) {

            System.out.println("Ligne inexistante.");
            return;
        }

        if (colonneIndex < 0 ||
            colonneIndex >= attributs.size()) {

            System.out.println("Colonne inexistante.");
            return;
        }

        donnees
            .get(ligneIndex)
            .set(colonneIndex, nouvelleValeur);

        System.out.println("Ligne modifiée avec succès.");
    }

    // =========================
    // DELETE
    // =========================

    public void delete(int ligneIndex) {

        if (ligneIndex < 0 ||
            ligneIndex >= donnees.size()) {

            System.out.println("Ligne inexistante.");
            return;
        }

        donnees.remove(ligneIndex);

        System.out.println("Ligne supprimée avec succès.");
    }

    // =========================
    // AFFICHAGE
    // =========================

    private void afficherEntete() {

        System.out.println();

        for (Attribut attribut : attributs) {
            System.out.printf("%5s", attribut.getNom());
        }

        System.out.println();

        System.out.println("-------------------");
    }

    // =========================
    // AFFICHER STRUCTURE
    // =========================

    public void afficherStructure() {

        System.out.println("\nRelation : " + nom);

        for (Attribut attribut : attributs) {

            System.out.println(
                "- " +
                attribut.getNom() +
                " : " +
                attribut.getDomaine().getNom()
            );
        }
    }
}