import java.util.ArrayList;

public class Relation {

    private String nom;

    private ArrayList<Attribut> attributs;

    private ArrayList<ArrayList<String>> donnees;

    public Relation(String nom) {
        this.nom = nom;
        this.attributs = new ArrayList<>();
        this.donnees = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Attribut> getAttributs() {
        return attributs;
    }


    // AJOUTER UN ATTRIBUT
    

    public void ajouterAttribut(Attribut attribut) {

        if (attribut == null) {
            System.out.println("Attribut invalide.");
            return;
        }

        attributs.add(attribut);
        attribut.getDomaine().ajouterRelation(this);
    }

    public boolean supprimerAttribut(String nomAttribut) {

        for (Attribut attribut : attributs) {
            if (attribut.getNom().equals(nomAttribut)) {
                attributs.remove(attribut);
                System.out.println("Attribut '" + nomAttribut + "' supprimé.");
                return true;
            }
        }

        System.out.println("Attribut introuvable.");
        return false;
    }

  
    // CREATE / INSERT
    

    public void insert(String... valeurs) {

        if (valeurs.length != attributs.size()) {
            System.out.println("Erreur : nombre de valeurs incorrect.");
            return;
        }

        ArrayList<String> ligne = new ArrayList<>();

        for (String valeur : valeurs) {
            ligne.add(valeur);
        }

        donnees.add(ligne);
        System.out.println("Ligne insérée avec succès.");
    }

    
    // READ et SELECT
   

    public void select() {

        if (donnees.isEmpty()) {
            System.out.println("Aucune donnée dans cette relation.");
            return;
        }

        afficherEntete();

        for (ArrayList<String> ligne : donnees) {
            for (String valeur : ligne) {
                System.out.printf("%5s", valeur);
            }
            System.out.println();
        }
    }

    
    // UPDATE
    

    public void update(int ligneIndex, int colonneIndex, String nouvelleValeur) {

        if (ligneIndex < 0 || ligneIndex >= donnees.size()) {
            System.out.println("Ligne inexistante.");
            return;
        }

        if (colonneIndex < 0 || colonneIndex >= attributs.size()) {
            System.out.println("Colonne inexistante.");
            return;
        }

        donnees.get(ligneIndex).set(colonneIndex, nouvelleValeur);
        System.out.println("Ligne modifiée avec succès.");
    }

    
    // DELETE
    

    public void delete(int ligneIndex) {

        if (ligneIndex < 0 || ligneIndex >= donnees.size()) {
            System.out.println("Ligne inexistante.");
            return;
        }

        donnees.remove(ligneIndex);
        System.out.println("Ligne supprimée avec succès.");
    }

    
    // AFFICHAGE
    

    private void afficherEntete() {

        System.out.println();

        for (Attribut attribut : attributs) {
            System.out.printf("%5s", attribut.getNom());
        }

        System.out.println();
        System.out.println("-------------------");
    }

    
    // AFFICHER STRUCTURE
    

    public void afficherStructure() {

        System.out.println("\nRelation : " + nom);

        if (attributs.isEmpty()) {
            System.out.println("Aucun attribut dans cette relation.");
            return;
        }

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