import java.io.Serializable;
import java.util.Comparator;

public class MyComparator<T> implements Comparator<T>, Serializable {

    public int compare(T left, T right) {
        return ((Comparable<T>)left).compareTo(right);
    }
}
