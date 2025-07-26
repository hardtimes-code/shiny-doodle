package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SolarSystemSimulation extends JFrame implements KeyListener {
    private SolarSystemPanel solarSystemPanel;

    public SolarSystemSimulation() {
        setTitle("Solar System Simulation with Nibiru");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        solarSystemPanel = new SolarSystemPanel();
        add(solarSystemPanel, BorderLayout.CENTER);

        JPanel controlPanel = createControlPanel();
        add(controlPanel, BorderLayout.SOUTH);

        addKeyListener(this);
        setFocusable(true);

        setLocationRelativeTo(null);
    }

    private JPanel createControlPanel() {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(Color.DARK_GRAY);

        JButton pauseButton = new JButton("Pause/Resume");
        pauseButton.addActionListener(e -> {
            solarSystemPanel.togglePause();
            this.requestFocusInWindow(); // <-- Add this line
        });

        JButton zoomInButton = new JButton("Zoom In (+)");
        zoomInButton.addActionListener(e -> {
            solarSystemPanel.zoomIn();
            this.requestFocusInWindow(); // <-- Add this line
        });

        JButton zoomOutButton = new JButton("Zoom Out (-)");
        zoomOutButton.addActionListener(e -> {
            solarSystemPanel.zoomOut();
            this.requestFocusInWindow(); // <-- Add this line
        });   

        JLabel infoLabel = new JLabel("Nibiru: hypothetical planet");
        infoLabel.setForeground(Color.WHITE);

        panel.add(pauseButton);
        panel.add(zoomInButton);    
        panel.add(zoomOutButton);

        panel.add(Box.createHorizontalStrut(20)); // Fixed typo: Horizontial -> Horizontal
        panel.add(infoLabel);

        return panel;   
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                solarSystemPanel.togglePause();
                break;
            case KeyEvent.VK_PLUS:
            case KeyEvent.VK_EQUALS:
                solarSystemPanel.zoomIn();
                break;
            case KeyEvent.VK_MINUS:
                solarSystemPanel.zoomOut();
                break;
            case KeyEvent.VK_RIGHT:
                solarSystemPanel.increaseTimeStep();
                break;
            case KeyEvent.VK_LEFT:
                solarSystemPanel.decreaseTimeStep();
                break;
            case KeyEvent.VK_N: // Nibiru approach
                solarSystemPanel.triggerNibiruApproach();
                break;
            case KeyEvent.VK_M: // Toggle magnetic field visualization
                solarSystemPanel.toggleMagneticVisualization();
                break;
            case KeyEvent.VK_R: // Reset simulation
                solarSystemPanel.resetSimulation();
                break;
            case KeyEvent.VK_1: case KeyEvent.VK_2: case KeyEvent.VK_3: case KeyEvent.VK_4:
            case KeyEvent.VK_5: case KeyEvent.VK_6: case KeyEvent.VK_7: case KeyEvent.VK_8: case KeyEvent.VK_9:
                int planetIndex = e.getKeyCode() - KeyEvent.VK_1;
                solarSystemPanel.focusOnPlanet(planetIndex);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void dispose() {
        solarSystemPanel.stopAnimation();
        super.dispose();        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            new SolarSystemSimulation().setVisible(true);   
        });
    }
}