import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OrderWindow extends JFrame {
    BST<Integer> binarySearchTree;
    JTextField orderLabel;

    public OrderWindow(BST<Integer> binarySearchTree){
        setBinarySearchTree(binarySearchTree);
        initWindow();
    }

    public void initWindow(){
        this.setTitle("Order");
        this.setSize(new Dimension(1000, 200));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new FlowLayout());

        //init buttons for the order of the BsTree
        JButton[] orderButtons = new JButton[3];
        String[] orders = {"Inorder", "Postorder", "Preorder"};
        for (int i = 0; i < orders.length; i++){
            orderButtons[i] = new JButton(orders[i]);
            this.add(orderButtons[i]);
            int finalI = i;
            orderButtons[i].addActionListener(e -> {
                ArrayList<Integer> orderedBTS = new ArrayList<>();
                orderBTS(getBinarySearchTree(), orderedBTS, finalI);
            });
        }

        orderLabel = new JTextField("Order: ", 80);
        this.add(orderLabel);

        this.setVisible(true);
    }

    public void orderBTS(BST BST, ArrayList<Integer> list, int mode) {
        //modes:
        //0: Inorder
        //1: Postorder
        //2: Preorder
        switch(mode) {
            case (0):
                BST.inOrder(BST.getRoot(), list);
                orderLabel.setText("Inorder: ");
                break;
            case (1):
                BST.postOrder(BST.getRoot(), list);
                orderLabel.setText("Postorder: ");
                break;
            case(2):
                BST.preOrder(BST.getRoot(), list);
                orderLabel.setText("Preorder: ");
                break;
        }
        for(int i:list){
            orderLabel.setText(orderLabel.getText() + " " + i);
        }
    }


    /**
     * Gets binarySearchTree.
     *
     * @return Value of binarySearchTree.
     */
    public BST getBinarySearchTree() {
        return binarySearchTree;
    }

    /**
     * Sets new binarySearchTree.
     *
     * @param binarySearchTree New value of binarySearchTree.
     */
    public void setBinarySearchTree(BST binarySearchTree) {
        this.binarySearchTree = binarySearchTree;
    }
}
