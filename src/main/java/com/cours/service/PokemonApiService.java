package com.cours.service;
import com.cours.modele.Pokemon;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class PokemonApiService {
    // Notre URL d'api
    private static final String URL = "https://pokeapi.co/api/v2/pokemon/";

    // On crée le client Http qui envoi les requetes
    private final HttpClient client = HttpClient.newHttpClient();

    // L'objet jackson qui permettra de lire le json
    private final ObjectMapper mapper = new ObjectMapper();

    // Notre methode recuperer un Pokemon depuis l'API
    public Pokemon recuperer(String recherche) throws Exception {

        // Construction d'une requête HTTP GET.
        HttpRequest req = HttpRequest
                .newBuilder(URI.create(URL + recherche))
                .GET()
                .build();

        // Envoie la requête et récupère la réponse sous forme de texte JSON
        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

        if (res.statusCode() != 200) {
            throw new RuntimeException("Erreur API"+ res.statusCode());
        }

        // Transforme le texte JSON en objet JsonNode pour pouvoir lire ses champs.
        JsonNode pokemon = mapper.readTree(res.body());

        // On crée notre objet pokemon vide pour le moment
        Pokemon p = new Pokemon();

        // Récupère l'id du Pokémon.
        p.setId(pokemon.get("id").asInt());

        // Récupère le nom.
        p.setNom(pokemon.get("name").asText());

        // Récupère la taille.
        p.setTaille(pokemon.get("height").asInt());

        // Récupère le poids.
        p.setPoids(pokemon.get("weight").asInt());

        // Récupère l'URL de l'image du Pokémon.
        p.setImageUrl(
                pokemon
                        .get("sprites")
                        .get("front_default")
                        .asText()
        );

        // Liste qui contiendra tous les types du Pokémon.
        List<String> types = new ArrayList<>();

        // On parcours la liste et on ajoute les elements à notre types
        for (JsonNode type : pokemon.get("types")) {
            types.add(
                    type
                            .get("type")
                            .get("name")
                            .asText()
            );
        }

        // Associe la liste des types au Pokémon.
        p.setTypes(types);

        // Renvoie l'objet Pokemon complètement rempli.
        return p;

    }

}



