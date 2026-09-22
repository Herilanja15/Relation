import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BaseDeDonnees bd = creerBaseExemple();

        System.out.println("\n=== BASE RELATIONNELLE ===");
        bd.afficherRelations();

        Relation relation = bd.chercherRelation("R");
        if (relation != null) {
            System.out.println("\nDonnées de la relation R :");
            relation.select();
        }

        Scanner scanner = new Scanner(System.in);
        boolean continuer = true;

        while (continuer) {
            System.out.println("\n=== MENU CRUD ===");
            System.out.println("1. Créer une relation");
            System.out.println("2. Ajouter un attribut à une relation");
            System.out.println("3. Insérer une ligne");
            System.out.println("4. Afficher une relation");
            System.out.println("5. Modifier une valeur");
            System.out.println("6. Supprimer une ligne");
            System.out.println("7. Supprimer une relation");
            System.out.println("8. Afficher la structure d'une relation");
            System.out.println("9. Afficher toutes les relations");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");

            String choixTexte = scanner.nextLine();

            int choix;
            try {
                choix = Integer.parseInt(choixTexte);
            } catch (NumberFormatException e) {
                System.out.println("Choix invalide.");
                continue;
            }

            switch (choix) {
                case 1:
                    creerRelation(bd, scanner);
                    break;
                case 2:
                    ajouterAttribut(bd, scanner);
                    break;
                case 3:
                    insererLigne(bd, scanner);
                    break;
                case 4:
                    afficherRelation(bd, scanner);
                    break;
                case 5:
                    modifierValeur(bd, scanner);
                    break;
                case 6:
                    supprimerLigne(bd, scanner);
                    break;
                case 7:
                    supprimerRelation(bd, scanner);
                    break;
                case 8:
                    afficherStructure(bd, scanner);
                    break;
                case 9:
                    bd.afficherRelations();
                    break;
                case 0:
                    continuer = false;
                    break;
                default:
                    System.out.println("Choix inconnu.");
                    break;
            }
        }

        System.out.println("\nFin du programme.");
        scanner.close();
    }

    private static BaseDeDonnees creerBaseExemple() {
        BaseDeDonnees bd = new BaseDeDonnees("BaseRelationnelle");
        Domaine domaine = new Domaine("Alphabet");

        Relation relation = new Relation("R");

        relation.ajouterAttribut(new Attribut("A", domaine));
        relation.ajouterAttribut(new Attribut("B", domaine));
        relation.ajouterAttribut(new Attribut("C", domaine));

        bd.ajouterRelation(relation);

        relation.insert("a", "a", "b");
        relation.insert("b", "e", "c");
        relation.insert("c", "c", "d");
        relation.insert("d", "d", "e");
        relation.insert("e", "b", "a");

        return bd;
    }

    private static void creerRelation(BaseDeDonnees bd, Scanner scanner) {
        System.out.print("Nom de la relation : ");
        String nomRelation = scanner.nextLine().trim();

        if (nomRelation.isEmpty()) {
            System.out.println("Le nom de la relation est obligatoire.");
            return;
        }

        Relation relation = new Relation(nomRelation);

        System.out.print("Nombre d'attributs : ");
        int nombreAttributs;

        try {
            nombreAttributs = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Nombre invalide.");
            return;
        }

        if (nombreAttributs <= 0) {
            System.out.println("Le nombre d'attributs doit être positif.");
            return;
        }

        for (int i = 0; i < nombreAttributs; i++) {
            System.out.print("Nom de l'attribut " + (i + 1) + " : ");
            String nomAttribut = scanner.nextLine().trim();

            System.out.print("Domaine de l'attribut " + (i + 1) + " : ");
            String nomDomaine = scanner.nextLine().trim();

            if (nomAttribut.isEmpty() || nomDomaine.isEmpty()) {
                System.out.println("Le nom de l'attribut et le domaine sont obligatoires.");
                return;
            }

            relation.ajouterAttribut(new Attribut(nomAttribut, new Domaine(nomDomaine)));
        }

        bd.ajouterRelation(relation);
    }

    private static void ajouterAttribut(BaseDeDonnees bd, Scanner scanner) {
        Relation relation = choisirRelation(bd, scanner, "ajouter un attribut");

        if (relation == null) {
            return;
        }

        System.out.print("Nom de l'attribut : ");
        String nomAttribut = scanner.nextLine().trim();

        System.out.print("Domaine de l'attribut : ");
        String nomDomaine = scanner.nextLine().trim();

        if (nomAttribut.isEmpty() || nomDomaine.isEmpty()) {
            System.out.println("Le nom et le domaine sont obligatoires.");
            return;
        }

        relation.ajouterAttribut(new Attribut(nomAttribut, new Domaine(nomDomaine)));
    }

    private static void insererLigne(BaseDeDonnees bd, Scanner scanner) {
        Relation relation = choisirRelation(bd, scanner, "insérer une ligne");

        if (relation == null) {
            return;
        }

        if (relation.getAttributs().isEmpty()) {
            System.out.println("La relation ne contient aucun attribut.");
            return;
        }

        String[] valeurs = new String[relation.getAttributs().size()];

        for (int i = 0; i < relation.getAttributs().size(); i++) {
            System.out.print("Valeur pour " + relation.getAttributs().get(i).getNom() + " : ");
            valeurs[i] = scanner.nextLine().trim();
        }

        relation.insert(valeurs);
    }

    private static void afficherRelation(BaseDeDonnees bd, Scanner scanner) {
        Relation relation = choisirRelation(bd, scanner, "afficher");

        if (relation != null) {
            relation.select();
        }
    }

    private static void modifierValeur(BaseDeDonnees bd, Scanner scanner) {
        Relation relation = choisirRelation(bd, scanner, "modifier une valeur");

        if (relation == null) {
            return;
        }

        if (relation.getAttributs().isEmpty()) {
            System.out.println("La relation ne contient aucun attribut.");
            return;
        }

        System.out.print("Indice de la ligne : ");
        int ligne;
        try {
            ligne = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Indice invalide.");
            return;
        }

        System.out.print("Indice de la colonne : ");
        int colonne;
        try {
            colonne = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Indice invalide.");
            return;
        }

        System.out.print("Nouvelle valeur : ");
        String nouvelleValeur = scanner.nextLine().trim();

        relation.update(ligne, colonne, nouvelleValeur);
    }

    private static void supprimerLigne(BaseDeDonnees bd, Scanner scanner) {
        Relation relation = choisirRelation(bd, scanner, "supprimer une ligne");

        if (relation == null) {
            return;
        }

        System.out.print("Indice de la ligne à supprimer : ");
        int indice;
        try {
            indice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Indice invalide.");
            return;
        }

        relation.delete(indice);
    }

    private static void supprimerRelation(BaseDeDonnees bd, Scanner scanner) {
        Relation relation = choisirRelation(bd, scanner, "supprimer");

        if (relation == null) {
            return;
        }

        bd.supprimerRelation(relation.getNom());
    }

    private static void afficherStructure(BaseDeDonnees bd, Scanner scanner) {
        Relation relation = choisirRelation(bd, scanner, "afficher la structure");

        if (relation != null) {
            relation.afficherStructure();
        }
    }

    private static Relation choisirRelation(BaseDeDonnees bd, Scanner scanner, String action) {
        if (bd.getRelations().isEmpty()) {
            System.out.println("Aucune relation dans la base.");
            return null;
        }

        System.out.println("Relations disponibles :");
        for (Relation relation : bd.getRelations()) {
            System.out.println("- " + relation.getNom());
        }

        System.out.print("Nom de la relation pour " + action + " : ");
        String nomRelation = scanner.nextLine().trim();
        Relation relation = bd.chercherRelation(nomRelation);

        if (relation == null) {
            System.out.println("Relation introuvable.");
            return null;
        }

        return relation;
    }
}