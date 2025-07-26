package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolarSystemPanel extends JPanel implements ActionListener {
    private SolarSystem solarSystem;
    private Timer timer;
    private boolean paused = false;
    private double scale = 0.8;
    private double timeStep = 0.01; // 1 = 1 year per tick, 0.01 = slow

    private boolean showMagnetic = true;
    private int focusPlanetIndex = -1; // -1 = center on sun

    public SolarSystemPanel() {
        solarSystem = new SolarSystem();
        timer = new Timer(50, this);
        timer.start();
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(1000, 800));    
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        // If focusing on a planet, adjust center
        if (focusPlanetIndex >= 0 && focusPlanetIndex < solarSystem.getPlanets().size()) {
            Planet p = solarSystem.getPlanets().get(focusPlanetIndex);
            double px = p.getX() * scale;
            double py = p.getY() * scale;
            centerX -= (int) px;
            centerY -= (int) py;
        }

        // Draw the sun
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(centerX - 15, centerY - 15, 30, 30);

        // Draw planets
        for (Planet planet : solarSystem.getPlanets()) {
            planet.draw(g2d, centerX, centerY, scale, showMagnetic);
        }

        // Draw gravity effect line if Nibiru is close to Earth
        Planet earth = null, nibiru = null;
        for (Planet p : solarSystem.getPlanets()) {
            if (p.getName().equalsIgnoreCase("Earth")) earth = p;
            if (p.getName().equalsIgnoreCase("Nibiru")) nibiru = p;
        }
        if (earth != null && nibiru != null) {
            double ex = centerX + earth.getX() * scale;
            double ey = centerY + earth.getY() * scale;
            double nx = centerX + nibiru.getX() * scale;
            double ny = centerY + nibiru.getY() * scale;
            double dx = nx - ex, dy = ny - ey;
            double distance = Math.sqrt(dx*dx + dy*dy) / scale;
            if (distance < 5.0) {
                g2d.setColor(Color.MAGENTA);
                g2d.drawLine((int)ex, (int)ey, (int)nx, (int)ny);

                // Draw tidal force arrow on Earth
                double arrowLength = 30;
                double fx = ex + (dx / distance) * arrowLength;
                double fy = ey + (dy / distance) * arrowLength;
                g2d.setColor(Color.CYAN);
                g2d.drawLine((int)ex, (int)ey, (int)fx, (int)fy);
                g2d.fillOval((int)fx - 3, (int)fy - 3, 6, 6);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!paused) {
            solarSystem.update(timeStep);
            repaint();
        }
    }

    public void togglePause() {
        paused = !paused;
    }

    public void zoomIn() {
        scale *= 1.1;
        repaint();
    }

    public void zoomOut() {
        scale /= 1.1;
        repaint();
    }

    public void stopAnimation() {
        timer.stop();
    }

    public void increaseTimeStep() {
        timeStep *= 2;
    }

    public void decreaseTimeStep() {
        timeStep /= 2;
    }

    public void triggerNibiruApproach() {
        solarSystem.triggerNibiruApproach();
    }

    public void toggleMagneticVisualization() {
        showMagnetic = !showMagnetic;
        repaint();
    }

    public void resetSimulation() {
        solarSystem.reset();
        focusPlanetIndex = -1;
        repaint();
    }

    public void focusOnPlanet(int index) {
        focusPlanetIndex = index;
        repaint();
    }
}