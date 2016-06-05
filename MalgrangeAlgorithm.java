import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Darkrengarius on 05.06.2016.
 */
public class MalgrangeAlgorithm {
    private ArrayList<ArrayList<Boolean>> graphMatrix;
    private HashSet<Integer> directTransitClosure;
    private HashSet<Integer> reverseTransitClosure;

    public MalgrangeAlgorithm (ArrayList<ArrayList<Boolean>> graphMatrix) {
        this.graphMatrix = graphMatrix;
        directTransitClosure = new HashSet<>();
        reverseTransitClosure = new HashSet<>();
    }

    
}
