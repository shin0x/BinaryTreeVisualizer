import java.util.Random;

public class Main {
    public static void main(String[] args) {
        BST<Integer> BST = new BST<>();
        //create set of values for the BST
        BST.addNode(100);
        BST.addNode(150);
        BST.addNode(50);
        BST.addNode(25);
        BST.addNode(75);
        BST.addNode(175);
        BST.addNode(125);


        //#### creates random binary search tree ####//
        Random r = new Random();
        for(int i = 0; i<20; i++)  BST.addNode(r.nextInt(200));

        new PrintOutStuff().printInformation(BST);

        new TreeDrawing<>(BST);

    }

}
