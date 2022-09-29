package com.qualogy.repository;

import com.qualogy.entities.Planet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlanetRepo {

    List<Planet> planets = new ArrayList<>();

    public PlanetRepo() {
        planets.add(new Planet("Mars", 100));
        planets.add(new Planet("Venus", 200));
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public Optional<Planet> findPlanet(String name) {
        return planets.stream().filter(p -> p.getName().equals(name)).findFirst();
    }

}
