package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Planet {
    private String name;
    private double semiMajorAxis; // in AU or scaled units
    private double eccentricity;  // 0 = circle, >0 = ellipse
    private double inclination;   // in degrees
    private double size;          // for drawing
    private Color color;
    private double angle;         // current position in orbit (radians)
    private double orbitalPeriod; // in Earth years
    private double axisTilt;      // in degrees
    private boolean magneticDisturbed = false;
    private boolean polesDisturbed = false;
    private double originalAxisTilt;
    private double mass; // in Earth masses

    public Planet(String name, double semiMajorAxis, double eccentricity, double inclination,
                  int size, Color color, double orbitalPeriod, double axisTilt) {
        this.name = name;
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.inclination = inclination;
        this.size = size;
        this.color = color;
        this.orbitalPeriod = orbitalPeriod;
        this.axisTilt = axisTilt;
        this.angle = Math.random() * 2 * Math.PI;
        this.originalAxisTilt = axisTilt;
        this.mass = 1.0; // Default mass if not specified
    }

    public Planet(String name, double semiMajorAxis, double eccentricity, double inclination,
                  int size, Color color, double orbitalPeriod, double axisTilt, double mass) {
        this.name = name;
        this.semiMajorAxis = semiMajorAxis;
        this.eccentricity = eccentricity;
        this.inclination = inclination;
        this.size = size;
        this.color = color;
        this.orbitalPeriod = orbitalPeriod;
        this.axisTilt = axisTilt;
        this.angle = Math.random() * 2 * Math.PI;
        this.originalAxisTilt = axisTilt;
        this.mass = mass;
    }

    public void update(double timeStep) {
        // Advance angle based on orbital period (simplified Kepler's law)
        angle += (2 * Math.PI / orbitalPeriod) * timeStep;
        angle %= 2 * Math.PI;
    }

    public void draw(Graphics2D g2d, int centerX, int centerY, double scale, boolean showMagnetic) {
        // Calculate ellipse parameters
        double a = semiMajorAxis * scale;
        double b = a * Math.sqrt(1 - eccentricity * eccentricity);

        // Inclination: rotate the orbit ellipse
        double incRad = Math.toRadians(inclination);

        // Position in orbit
        double x = a * Math.cos(angle);
        double y = b * Math.sin(angle);

        // Apply inclination (rotate around X axis)
        double yInclined = y * Math.cos(incRad);

        // Draw orbit
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawOval(centerX - (int)a, centerY - (int)b, (int)(2*a), (int)(2*b));

        // Draw planet
        g2d.setColor(color);
        g2d.fill(new Ellipse2D.Double(centerX + x - size/2, centerY + yInclined - size/2, size, size));

        // Draw axis tilt as a line
        double tiltRad = Math.toRadians(axisTilt);
        double axisLength = size * 1.2;
        double px = centerX + x;
        double py = centerY + yInclined;
        double x1 = px + Math.cos(tiltRad) * axisLength / 2;
        double y1 = py + Math.sin(tiltRad) * axisLength / 2;
        double x2 = px - Math.cos(tiltRad) * axisLength / 2;
        double y2 = py - Math.sin(tiltRad) * axisLength / 2;

        g2d.setColor(Color.WHITE);
        g2d.drawLine((int)x1, (int)y1, (int)x2, (int)y2);

        // Aurora effect if poles disturbed
        if (polesDisturbed) {
            g2d.setColor(new Color(0, 255, 128, 128)); // semi-transparent green
            g2d.fillOval((int)px - (int)size, (int)py - (int)size, (int)size*2, (int)size*2);
        }

        // Draw pole highlights if disturbed
        if (polesDisturbed) {
            g2d.setColor(Color.RED);
            g2d.fillOval((int)x1 - 3, (int)y1 - 3, 6, 6); // North pole
            g2d.setColor(Color.BLUE);
            g2d.fillOval((int)x2 - 3, (int)y2 - 3, 6, 6); // South pole
        }

        g2d.setColor(Color.WHITE);
        g2d.drawString(name, (int)px + 10, (int)py);
    }

    public String getName() {
        return name;
    }

    public double getSemiMajorAxis() {
        return semiMajorAxis;
    }

    public double getEccentricity() {
        return eccentricity;
    }

    public double getInclination() {
        return inclination;
    }

    public double getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public double getAngle() {
        return angle;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public double getAxisTilt() {
        return axisTilt;
    }

    public double getOriginalAxisTilt() {
        return originalAxisTilt;
    }

    public double getMass() { return mass; }

    public void setName(String name) {
        this.name = name;
    }

    public void setSemiMajorAxis(double a) { this.semiMajorAxis = a; }
    public void setEccentricity(double e) { this.eccentricity = e; }
    public void setInclination(double i) { this.inclination = i; }
    public void setSize(double size) {
        this.size = size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setOrbitalPeriod(double p) { this.orbitalPeriod = p; }
    public void setAxisTilt(double axisTilt) {
        this.axisTilt = axisTilt;
    }

    public void setMagneticDisturbed(boolean disturbed) {
        this.magneticDisturbed = disturbed;
    }

    public void setPolesDisturbed(boolean disturbed) { this.polesDisturbed = disturbed; }

    public double getX() {
        // Calculate current X position in orbit (similar to draw method)
        double a = semiMajorAxis;
        double b = a * Math.sqrt(1 - eccentricity * eccentricity);
        return a * Math.cos(angle);
    }

    public double getY() {
        double a = semiMajorAxis;
        double b = a * Math.sqrt(1 - eccentricity * eccentricity);
        return b * Math.sin(angle);
    }

    public boolean isPolesDisturbed() { return polesDisturbed; }
}