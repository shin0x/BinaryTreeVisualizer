import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoadAndSaveBinaryTree {
    public void save(String filename, BST BST){
        //try to save the binary Tree
        try {
            //Creating stream and writing the object
            FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(BST);
            out.flush();
            //closing the stream
            out.close();
            System.out.println("Succesfully saved the binary tree to " + filename );
        }
        catch (Exception e){e.printStackTrace();}
    }

    public BST load(String filename){
        try{
            //Creating stream to read the object
            ObjectInputStream in=new ObjectInputStream(new FileInputStream(filename));
            BST BST = (BST)in.readObject();
            //closing the stream
            in.close();
            return BST;
        }catch(Exception e){System.out.println(e); return null;}
    }
}
