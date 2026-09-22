public class Main {

    public static void main(String[] args) {

       
        // CREATION DE LA BASE DE DONNEES
        

        BaseDeDonnees bd =
            new BaseDeDonnees("MaBase");


                //  CREATION DU DOMAINE
        
        Domaine entier =
            new Domaine("INTEGER");


                //  CREATION DE LA RELATION
        
        Relation relation =
            new Relation("R");


                //  CREATION DES ATTRIBUTS
        
        Attribut A =
            new Attribut("A", entier);

        Attribut B =
            new Attribut("B", entier);

        Attribut C =
            new Attribut("C", entier);


                //  AJOUT DES ATTRIBUTS A LA RELATION
        
        relation.ajouterAttribut(A);
        relation.ajouterAttribut(B);
        relation.ajouterAttribut(C);


                //  AJOUT DE LA RELATION A LA BD
        
        bd.ajouterRelation(relation);


                //  INSERT
        
        relation.insert(1, 1, 2);
        relation.insert(2, 3, 5);
        relation.insert(3, 4, 4);
        relation.insert(4, 4, 5);
        relation.insert(5, 2, 1);


                //  SELECT
        
        System.out.println("\n--- SELECT ---");

        relation.select();


                // UPDATE
        
        System.out.println("\n--- UPDATE ---");

        // Modifier la première ligne,
        // colonne B
        //
        // ligne 0
        // colonne 1

        relation.update(0, 1, 9);


        System.out.println(
            "\nAprès modification :"
        );

        relation.select();


                //  DELETE
        
        System.out.println("\n--- DELETE ---");

        // Supprimer la deuxième ligne

        relation.delete(1);


        System.out.println(
            "\nAprès suppression :"
        );

        relation.select();


                //  STRUCTURE
        
        System.out.println(
            "\n--- STRUCTURE ---"
        );

        relation.afficherStructure();


                // RELATIONS DU DOMAINE
        
        System.out.println(
            "\n--- DOMAINE ---"
        );

        entier.afficherRelations();
    }
}