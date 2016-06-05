import java.util.ArrayList;

/**
 * Created by Darkrengarius on 05.06.2016.
 */
public class KosarajuAlgorithm {
    private ArrayList<ArrayList<Boolean>> graphMatrix;
    private ArrayList<Integer> order;
    private boolean[] used;

    public KosarajuAlgorithm (ArrayList<ArrayList<Boolean>> graphMatrix) {
        this.graphMatrix = graphMatrix;
        order = new ArrayList<>();

        used = new boolean[graphMatrix.size()];
    }

    private void initUsed () {
        for (int i = 0; i < used.length; i++) {
            used[i] = false;
        }
    }

    private void invertGraph () {
        for (int i = 0; i < graphMatrix.size(); i++) {
            for (int j = i + 1; j < graphMatrix.get(i).size(); j++) {
                if (graphMatrix.get(i).get(j) && !graphMatrix.get(j).get(i)) {
                    graphMatrix.get(i).set(j, false);
                    graphMatrix.get(j).set(i, true);
                }
            }
        }
    }

    private void dfs (int v) {
        used[v] = true;
        for (int i = 0; i < graphMatrix.size(); i++) {
            if (!used[i] && graphMatrix.get(v).get(i)) {
                dfs(i);
            }
        }
        order.add(v);
    }

    private ArrayList<Integer> createComponent (int firstInd, int lastInd) {
        ArrayList<Integer> component = new ArrayList<>();
        for (int j = firstInd; j <= lastInd; j++) {
            component.add(order.get(j));
        }
        return component;
    }

    public ArrayList<ArrayList<Integer>> findComponents () {
        ArrayList<ArrayList<Integer>> components = new ArrayList<>();

        invertGraph();
        initUsed();
        for (int i = 0; i < graphMatrix.size(); i++) {
            if (!used[i]) {
                dfs(i);
            }
        }

        invertGraph();
        initUsed();
        int addIndex= graphMatrix.size() - 1;
        boolean firstIter = true;
        for (int i = graphMatrix.size() - 1; i >= 0; i--) {
            if (!used[order.get(i)]) {
                components.add(createComponent(i + 1, addIndex));
                addIndex = i;
                dfs(order.get(i));
            }
        }
        components.add(createComponent(0, addIndex));

        return components;
    }
}
