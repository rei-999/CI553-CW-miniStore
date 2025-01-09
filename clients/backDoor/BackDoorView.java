package clients.backDoor;

import middle.MiddleFactory;
import middle.StockReadWriter;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Implements the Customer view.
 */
public class BackDoorView implements Observer {
    private static final String RESTOCK = "Add";
    private static final String CLEAR = "Clear";
    private static final String QUERY = "Query";
    private static final String CHANGE_COLOR = "BG Col";

    private static final int H = 300; // Height of window pixels
    private static final int W = 400; // Width of window pixels

    private final JLabel pageTitle = new JLabel();
    private final JLabel theAction = new JLabel();
    private final JTextField theInput = new JTextField();
    private final JTextField theInputNo = new JTextField();
    private final JTextArea theOutput = new JTextArea();
    private final JScrollPane theSP = new JScrollPane();
    private final JButton theBtClear = new JButton(CLEAR);
    private final JButton theBtRStock = new JButton(RESTOCK);
    private final JButton theBtQuery = new JButton(QUERY);
    private final JButton theBtChangeColor = new JButton(CHANGE_COLOR);

    private StockReadWriter theStock = null;
    private BackDoorController cont = null;

    /**
     * Construct the view
     * @param rpc Window in which to construct
     * @param mf  Factory to deliver order and stock objects
     * @param x   x-coordinate of position of window on screen
     * @param y   y-coordinate of position of window on screen
     */
    public BackDoorView(RootPaneContainer rpc, MiddleFactory mf, int x, int y) {
        try {
            theStock = mf.makeStockReadWriter(); // Database access
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        Container cp = rpc.getContentPane(); // Content Pane
        Container rootWindow = (Container) rpc; // Root Window
        cp.setLayout(null); // No layout manager
        rootWindow.setSize(W, H); // Size of Window
        rootWindow.setLocation(x, y);
        cp.setBackground(Color.BLUE);

        Font f = new Font("Monospaced", Font.PLAIN, 12); // Font f is

        pageTitle.setBounds(110, 0, 270, 20);
        pageTitle.setText("Staff check and manage stock");
        cp.add(pageTitle);

        theBtQuery.setBounds(16, 25 + 60 * 0, 80, 40); // Query button
        theBtQuery.addActionListener(e -> cont.doQuery(theInput.getText()));
        cp.add(theBtQuery);

        theBtRStock.setBounds(16, 25 + 60 * 1, 80, 40); // Restock Button
        theBtRStock.addActionListener(e -> cont.doRStock(theInput.getText(), theInputNo.getText()));
        cp.add(theBtRStock);

        theBtClear.setBounds(16, 25 + 60 * 2, 80, 40); // Clear button
        theBtClear.addActionListener(e -> cont.doClear());
        cp.add(theBtClear);

        theBtChangeColor.setBounds(16, 25 + 60 * 3, 80, 40); // Change Color button
        theBtChangeColor.addActionListener(e -> {
            Color randomColor = new Color(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255),
                (int) (Math.random() * 255)
            );
            cp.setBackground(randomColor);
        });
        cp.add(theBtChangeColor);

        theAction.setBounds(110, 25, 270, 20); // Message area
        theAction.setText(""); // Blank
        cp.add(theAction);

        theInput.setBounds(110, 50, 120, 40); // Input Area
        theInput.setText(""); // Blank
        cp.add(theInput);

        theInputNo.setBounds(260, 50, 120, 40); // Input Area
        theInputNo.setText("0"); // 0
        cp.add(theInputNo);

        theSP.setBounds(110, 100, 270, 160); // Scrolling pane
        theOutput.setText(""); // Blank
        theOutput.setFont(f); // Uses font
        cp.add(theSP);
        theSP.getViewport().add(theOutput); // In TextArea
        rootWindow.setVisible(true); // Make visible
        theInput.requestFocus(); // Focus is here
    }

    public void setController(BackDoorController c) {
        cont = c;
    }

    /**
     * Update the view, called by notifyObservers(theAction) in model,
     * @param modelC The observed model
     * @param arg    Specific args
     */
    @Override
    public void update(Observable modelC, Object arg) {
        BackDoorModel model = (BackDoorModel) modelC;
        String message = (String) arg;
        theAction.setText(message);

        theOutput.setText(model.getBasket().getDetails());
        theInput.requestFocus();
    }
}
