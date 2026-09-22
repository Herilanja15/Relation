public class Attribut {

    private String nom;
    private Domaine domaine;

    public Attribut(String nom, Domaine domaine) {
        this.nom = nom;
        this.domaine = domaine;
    }

    public String getNom() {
        return nom;
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public void afficher() {
        System.out.println(
            "Attribut : " + nom +
            " | Domaine : " + domaine.getNom()
        );
    }
}