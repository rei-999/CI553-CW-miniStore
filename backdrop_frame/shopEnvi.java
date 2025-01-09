package backdrop_frame;

import javax.swing.*;
import java.awt.*;

public class shopEnvi {
    public static void main(String[] args) {
        // Create the backdrop (full screen background)
        FullScreenBackgroundFrame backdrop = new FullScreenBackgroundFrame();
        backdrop.createFullScreenBackground();

        // Create the shop environment GUI on top of the backdrop
        JFrame shopFrame = new JFrame("Shop Environment");

        // Set the size for the shop window
        shopFrame.setSize(800, 600);
        shopFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        shopFrame.setLocationRelativeTo(null); // Center the shop window

        // Change background color to black
        shopFrame.getContentPane().setBackground(Color.BLACK);

        // Add some components to the shop window
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK); // Set panel background to black
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Welcome to the Shop!");
        label.setForeground(Color.WHITE); // Set text color to white for visibility
        panel.add(label);

        shopFrame.add(panel);

        // Make the shop window visible
        shopFrame.setVisible(true);
    }
}
