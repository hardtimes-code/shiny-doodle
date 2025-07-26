package ui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class SolarSystem { 
    private final List<Planet> planets;

    public SolarSystem() {
        planets = new ArrayList<>();
        initializePlanets();
    }

    private void initializePlanets() {
        // name, semiMajorAxis (AU), eccentricity, inclination (deg), size, color, orbitalPeriod (years), axisTilt (deg)
        planets.add(new Planet("Mercury", 0.39, 0.205, 7.0, 4, new Color(169,169,169), 0.24, 0.03));
        planets.add(new Planet("Venus",   0.72, 0.007, 3.4, 6, new Color(255,165,0), 0.62, 177.4));
        planets.add(new Planet("Earth",   1.00, 0.017, 0.0, 6, new Color(0,100,200), 1.00, 23.5));
        planets.add(new Planet("Mars",    1.52, 0.093, 1.85, 5, new Color(255,69,0), 1.88, 25.2));
        planets.add(new Planet("Jupiter", 5.20, 0.049, 1.3, 20, new Color(255,140,0), 11.86, 3.1));
        planets.add(new Planet("Saturn",  9.58, 0.056, 2.5, 18, new Color(255,215,0), 29.46, 26.7));
        planets.add(new Planet("Uranus", 19.18, 0.046, 0.8, 12, new Color(64,224,208), 84.01, 97.8));
        planets.add(new Planet("Neptune",30.07, 0.010, 1.8, 12, new Color(0,0,255), 164.8, 28.3));
        // Hypothetical planet
        planets.add(new Planet("Nibiru", 60.0, 0.5, 30.0, 15, new Color(139,69,19), 360.0, 45.0, 10.0)); // 10 Earth masses
    }

    public void update(double timeStep) {
        Planet nibiru = null;
        Planet earth = null;
        for (Planet p : planets) {
            if (p.getName().equalsIgnoreCase("Nibiru")) nibiru = p;
            if (p.getName().equalsIgnoreCase("Earth")) earth = p;
        }
        for (Planet planet : planets) {
            planet.update(timeStep);
        }
        // Affect Earth's poles if Nibiru is close
        if (nibiru != null && earth != null) {
            double dx = earth.getX() - nibiru.getX();
            double dy = earth.getY() - nibiru.getY();
            double distance = Math.sqrt(dx*dx + dy*dy);
            if (distance < 2.0) {
                double effect = (2.0 - distance) / 2.0;
                earth.setEccentricity(0.017 + effect * 0.2); // exaggerate for visualization
            } else {
                earth.setEccentricity(0.017); // reset to normal
            }
        }
    }

    public void triggerNibiruApproach() {
        for (Planet p : planets) {
            if (p.getName().equalsIgnoreCase("Nibiru")) {
                // Set a highly elliptical orbit that brings Nibiru close to Earth
                p.setSemiMajorAxis(1.5);      // AU, between Earth and Mars
                p.setEccentricity(0.8);       // Very elliptical
                p.setInclination(30.0);       // Optional: tilt the orbit
                p.setOrbitalPeriod(2.0);      // Shorten period for visible motion
                break;
            }
        }
    }

    public void reset() {
        planets.clear();
        initializePlanets();
    }

    public List<Planet> getPlanets() {
        return planets;
    }
}