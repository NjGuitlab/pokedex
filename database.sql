-- ============================================================
-- Projet Pokédex
-- Script de création de la base de données
-- ============================================================

-- Suppression de la table si elle existe déjà
DROP TABLE IF EXISTS pokemon;

-- Création de la table
CREATE TABLE pokemon (

                         id INTEGER PRIMARY KEY,

                         nom VARCHAR(100) NOT NULL,

                         image_url TEXT,

                         taille INTEGER,

                         poids INTEGER,

                         types TEXT

);

-- ============================================================
-- Données de démonstration
-- ============================================================

INSERT INTO pokemon
(id, nom, image_url, taille, poids, types)
VALUES
    (
        1,
        'bulbasaur',
        'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png',
        7,
        69,
        'grass,poison'
    )
    ON CONFLICT (id) DO NOTHING;

INSERT INTO pokemon
(id, nom, image_url, taille, poids, types)
VALUES
    (
        4,
        'charmander',
        'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png',
        6,
        85,
        'fire'
    )
    ON CONFLICT (id) DO NOTHING;

INSERT INTO pokemon
(id, nom, image_url, taille, poids, types)
VALUES
    (
        25,
        'pikachu',
        'https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/25.png',
        4,
        60,
        'electric'
    )
    ON CONFLICT (id) DO NOTHING;