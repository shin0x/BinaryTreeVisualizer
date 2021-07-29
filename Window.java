import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.regex.Pattern;

public class Window extends Canvas{

    int width;
    int height;
    String title;
    JFrame frame;
    TreeDrawing<Integer> treeDrawing;
    JTextField valueInputField;

    public Window(int width, int height, String title, TreeDrawing treeDrawing){
        setWidth(width);
        setHeight(height);
        setTitle(title);
        setTreeDrawing(treeDrawing);

        frame = new JFrame();

        //set properties of the frame
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle(title);

        //GUI elements to add values to tree
        JPanel bottomPanel = new JPanel(new FlowLayout());
        valueInputField = new JTextField("", 10);
        JButton addElementButton = new JButton("Add Element");
        addElementButton.addActionListener(e -> {
            if(Pattern.compile("-?\\d+(\\.\\d+)?").matcher(valueInputField.getText()).matches())
                treeDrawing.getInsideBST().addNode(Integer.valueOf(valueInputField.getText()));
            else
                JOptionPane.showMessageDialog(null, "Please input a real number");
        });

        JButton removeElementButton = new JButton("Remove Element");
        removeElementButton.addActionListener(e -> {
            if(Pattern.compile("-?\\d+(\\.\\d+)?").matcher(valueInputField.getText()).matches())
                treeDrawing.getInsideBST().removeNode(Integer.valueOf(valueInputField.getText()));
            else
                JOptionPane.showMessageDialog(null, "Please input a real number");
        });

        JButton clearTreeBtn = new JButton("Clear tree");
        clearTreeBtn.addActionListener(e -> {
            BST<Integer> newTree = new BST();
            newTree.addNode(0);
            treeDrawing.updateInsideTree(newTree);
        });

        JButton randomTreeBtn = new JButton("Random tree");
        randomTreeBtn.addActionListener(e -> {
            BST<Integer> randomTree = new BST<>();

            //#### creates random values for the tree ####//
            Random r = new Random();
            for(int i = 0; i<25; i++)  randomTree.addNode(r.nextInt(200));

            treeDrawing.updateInsideTree(randomTree);
            new PrintOutStuff().printInformation(randomTree);
        });

        JButton loadSavedTreeButton = new JButton("Load Tree");
        loadSavedTreeButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            int response = fileChooser.showOpenDialog(this);
            if(response == JFileChooser.APPROVE_OPTION){
                String filepath = fileChooser.getSelectedFile().getPath();
                BST loadedTree = new LoadAndSaveBinaryTree().load(filepath);
                if(loadedTree != null)
                    treeDrawing.updateInsideTree(loadedTree);
                else
                    JOptionPane.showMessageDialog(null, "Couldn't load saved tree", "Error", JOptionPane.ERROR_MESSAGE);
                new PrintOutStuff().printInformation(treeDrawing.getInsideBST());
            }
        });

        JButton saveTreeButton = new JButton("Save Tree");
        saveTreeButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();

            int response = fileChooser.showSaveDialog(this);
            if(response == JFileChooser.APPROVE_OPTION){
                String filepath = fileChooser.getSelectedFile().getPath();
                new LoadAndSaveBinaryTree().save(filepath, treeDrawing.getInsideBST());
            }
        });

        JButton orderBinaryTreeButton = new JButton("Order Tree");
        orderBinaryTreeButton.addActionListener(e -> {
            OrderWindow orderWindow = new OrderWindow(treeDrawing.getInsideBST());
        });

        //add elements to the bottom panel
        bottomPanel.add(valueInputField);
        bottomPanel.add(addElementButton);
        bottomPanel.add(removeElementButton);
        bottomPanel.add(clearTreeBtn);
        bottomPanel.add(randomTreeBtn);
        bottomPanel.add(loadSavedTreeButton);
        bottomPanel.add(saveTreeButton);
        bottomPanel.add(orderBinaryTreeButton);

        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);

        frame.add(treeDrawing);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        treeDrawing.start();
    }

    /**
     * Sets new height.
     *
     * @param height New value of height.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Sets new width.
     *
     * @param width New value of width.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets new title.
     *
     * @param title New value of title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets width.
     *
     * @return Value of width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets height.
     *
     * @return Value of height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets title.
     *
     * @return Value of title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets treeDrawing.
     *
     * @return Value of treeDrawing.
     */
    public TreeDrawing<Integer> getTreeDrawing() {
        return treeDrawing;
    }

    /**
     * Sets new treeDrawing.
     *
     * @param treeDrawing New value of treeDrawing.
     */
    public void setTreeDrawing(TreeDrawing<Integer> treeDrawing) {
        this.treeDrawing = treeDrawing;
    }
}