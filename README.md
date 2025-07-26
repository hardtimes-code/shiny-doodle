# Shiny Doodle – Solar System Simulation 🪐

A Java Swing-based interactive model of our solar system, featuring orbital mechanics, axial tilts, geomagnetic visualization, and speculative events like Nibiru’s mysterious flyby.

---

## 🔍 Core Features

| Feature                      | Description |
|-----------------------------|-------------|
| Realistic Orbit Simulation  | Models planetary trajectories with accurate eccentricities and inclinations |
| Axial Rotation & Tilt       | Visualizes seasonal variance through orbital tilt |
| Nibiru Flyby Trigger        | Manually invoke an anomalous event with dynamic pathing |
| Magnetic Field Effects      | Toggle magnetic visualization and observe pole reversal patterns |
| Speed & Time Controls       | Accelerate, decelerate, or pause simulation time |
| Zoom & Planet Focus         | Hotkey-based planetary centering for detailed views |
| Reset System                | Restart simulation to initial conditions instantly |

---

## 🎮 Controls

| Key / Button      | Action                             |
|-------------------|------------------------------------|
| `Spacebar`        | Pause / Resume simulation          |
| `→ / ←` Arrows    | Adjust time speed (faster/slower)  |
| `+ / -`           | Zoom in / Zoom out                 |
| `1–9`             | Focus on planets (1 = Mercury → 9 = Neptune) |
| `N`               | Trigger Nibiru approach            |
| `M`               | Toggle magnetic field visuals      |
| `R`               | Reset simulation                   |
| **GUI Buttons**   | Mirror hotkey actions              |

---

## 🚀 How to Run

Ensure you're in the root `shiny-doodle` directory. Then:

bash
javac -d bin Planet.java SolarSystemSimulation.java SolarSystemPanel.java SolarSystem.java
java -cp bin ui.SolarSystemSimulation

🖼️ Screenshots
Planetary system in motion with Nibiru activated.

Pole reversal visual overlay.

🧠 Creator’s Vision
Kristi (@hardtimes-code) builds immersive simulations that blend astrophysics, real-time data, and speculative design. Shiny Doodle is a testbed for her UniverseGenesis platform — where science and imagination orbit the same star.

🧪 Tech Stack
Java Swing GUI

Procedural orbit modeling

Axial rotation dynamics

Event-driven control structure

Speculative event simulation (Nibiru)

📦 Releases & Versioning (Coming Soon)
Stay tuned for version tagging, binary releases, and build automation via GitHub Actions.

📜 License
MIT License — open for learning and expansion.

Enjoy exploring the cosmos! 🌌




