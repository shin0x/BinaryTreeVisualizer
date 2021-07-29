public class PrintOutStuff {
    public void printInformation(BST BST){
        System.out.println("Minimal number in BST: " + BST.getMinimum(BST.getRoot()).getValue());
        System.out.println("Maximum number in BST: " + BST.getMaximum(BST.getRoot()).getValue());
        System.out.println("BST has : " + BST.countLeaves(BST.getRoot()) + " leaves.");
        System.out.println("BST has: " + BST.countNodes(BST.getRoot()) + " nodes.");
        System.out.println("The height of the BST is: " + BST.countHeight(BST.getRoot()));
    }
}
