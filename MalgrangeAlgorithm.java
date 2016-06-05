import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Darkrengarius on 05.06.2016.
 */
public class MalgrangeAlgorithm {
    private ArrayList<ArrayList<Boolean>> graphMatrix;
    private HashSet<Integer> directTransitClosure;
    private HashSet<Integer> reverseTransitClosure;
    private HashSet<Integer> deletedVertexes;

    private boolean[] used;

    public MalgrangeAlgorithm (ArrayList<ArrayList<Boolean>> graphMatrix) {
        this.graphMatrix = graphMatrix;
        directTransitClosure = new HashSet<>();
        reverseTransitClosure = new HashSet<>();
        deletedVertexes = new HashSet<>();

        used = new boolean[graphMatrix.size()];
    }

    private void initUsed () {
        for (int i = 0; i < used.length; i++) {
            used[i] = false;
        }
    }

    private void createDirectClosure (int v) {
        used[v] = true;
        directTransitClosure.add(v);

        for (int i = 0; i < graphMatrix.size(); i++) {
            if (!used[i] && graphMatrix.get(v).get(i)) {
                createDirectClosure(i);
            }
        }
    }

    private void createReverseClosure (int v) {
        used[v] = true;
        reverseTransitClosure.add(v);

        for (int i = 0; i < graphMatrix.size(); i++) {
            if (!used[i] && graphMatrix.get(i).get(v)) {
                createReverseClosure(i);
            }
        }
    }

    public ArrayList<ArrayList<Integer>> findComponents () {
        ArrayList<ArrayList<Integer>> components = new ArrayList<>();

        for (int i = 0; i < graphMatrix.size(); i++) {
            if (!deletedVertexes.contains(i)) {
                initUsed();
                directTransitClosure.clear();
                createDirectClosure(i);

                initUsed();
                reverseTransitClosure.clear();
                createReverseClosure(i);

                ArrayList<Integer> component = new ArrayList<>();
                for (Integer v : directTransitClosure) {
                    if (reverseTransitClosure.contains(v)) {
                        component.add(v);
                        deletedVertexes.add(v);
                    }
                }
                components.add(component);
            }
        }

        return components;
    }
}
