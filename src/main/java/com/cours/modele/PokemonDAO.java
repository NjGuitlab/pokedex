package com.cours.modele;
import com.cours.util.Connexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;


public class PokemonDAO {

    // ----------------------- Methode sauvegarder --------------------------------- //

    public void sauvegarder(Pokemon pokemon) throws SQLException{

        String sql = """
                INSERT INTO pokemon
                (id, nom, image_url, taille, poids, types)
                VALUES (?, ?, ?, ?, ?, ?)
                ON CONFLICT (id)
                DO UPDATE SET
                    nom = EXCLUDED.nom,
                    image_url = EXCLUDED.image_url,
                    taille = EXCLUDED.taille,
                    poids = EXCLUDED.poids,
                    types = EXCLUDED.types;
                """;

        try (Connection co = Connexion.getConnexion(); PreparedStatement ps = co.prepareStatement(sql)){

                ps.setInt(1, pokemon.getId());
                ps.setString(2, pokemon.getNom());
                ps.setString(3, pokemon.getImageUrl());
                ps.setInt(4, pokemon.getTaille());
                ps.setInt(5, pokemon.getPoids());
                ps.setString(6, String.join(",",pokemon.getTypes()));

                // Sert à executer le insert ou le update
                ps.executeUpdate();

        }

    }

    // -------------------- Methode recuperer ------------------------------------- //

    public List<Pokemon> recupererTous() throws SQLException{

        String sql = """
                SELECT * FROM pokemon
                
                """;

        // Liste qui va contenir les pokemons récuprérés
        List<Pokemon> pokemons = new ArrayList<>();

        // Ouverture de la connexion
        try(Connection co = Connexion.getConnexion(); PreparedStatement ps = co.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                //On crée le pokemon
                Pokemon p = new Pokemon();

                // Récupération des colonnes de la table
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setImageUrl(rs.getString("image_url"));
                p.setTaille(rs.getInt("taille"));
                p.setPoids(rs.getInt("poids"));

                // Transformation de la chaine en liste:
                String typesString = rs.getString("types");
                p.setTypes(List.of(typesString.split(",")));

                // Ajout du pokémon dans la liste
                pokemons.add(p);

            }

        }

        return  pokemons;
    }

    // -------------------- Methode supprimer ------------------------------ //

    public void supprimer(int id) throws SQLException {
        String sql = """
            DELETE FROM pokemon
            WHERE id = ?
            """;

        try (Connection co = Connexion.getConnexion();
             PreparedStatement ps = co.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        }

    }

}
