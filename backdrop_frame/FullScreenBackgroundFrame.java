package backdrop_frame;

import javax.swing.*;
import java.awt.*;

public class FullScreenBackgroundFrame {
    public JFrame createFullScreenBackground() {
        // Create the JFrame
        JFrame frame = new JFrame();

        // Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        
        // Set frame size to match screen size
        frame.setSize(screenSize);
        
        // Set the frame to be non-interactive and always in the background
        frame.setUndecorated(true); // Remove window decorations like title bar
        frame.setOpacity(0.7f); // Optional: make the frame semi-transparent
        frame.setAlwaysOnTop(false); // Make sure it stays behind other windows
        
        // Set the frame's location to the top-left corner (0,0)
        frame.setLocation(0, 0);
        
        // Make the frame visible
        frame.setVisible(true);
        
        return frame; // Return the frame so it can be used in other parts of the program
    }
}

