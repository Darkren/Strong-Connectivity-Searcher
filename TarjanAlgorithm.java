import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Darkrengarius on 05.06.2016.
 */
public class TarjanAlgorithm {
    private ArrayList<ArrayList<Boolean>> graphMatrix;
    private Stack<Integer> stack = new Stack<>();
    private boolean used[];
    private int lowlinks[];
    private int indexes[];
    private int id;

    public TarjanAlgorithm (ArrayList<ArrayList<Boolean>> graphMatrix) {
        this.graphMatrix = graphMatrix;
        lowlinks = new int[graphMatrix.size()];
        indexes = new int[graphMatrix.size()];
        used = new boolean[graphMatrix.size()];
        stack = new Stack<>();
    }

    public ArrayList<ArrayList<Integer>> findComponents ()
        {
            ArrayList<ArrayList<Integer>> components = new ArrayList<>();

            id = 0;
            for (int i = 0; i < graphMatrix.size(); i++) {
                indexes[i] = 0;
                lowlinks[i] = graphMatrix.size();
                used[i] = false;
            }

            for (int i = 0; i < graphMatrix.size(); i++)
                if (!used[i])
                    dfs(i, components);

            return components;
        }

        private void dfs(int v, ArrayList<ArrayList<Integer>> components)
        {
            used[v] = true;
            lowlinks[v] = id;
            indexes[v] = id++;
            stack.push(v);
            for (int i = 0; i < graphMatrix.size(); i++) {
                if (!used[i] && graphMatrix.get(v).get(i)) {
                    dfs(i, components);
                    lowlinks[v] = Integer.min(lowlinks[v], lowlinks[i]);
                } else if (stack.contains(i)) {
                    lowlinks[v] = Integer.min(lowlinks[v], indexes[i]);
                }
            }
            ArrayList<Integer> component = new ArrayList<>();
            if (indexes[v] == lowlinks[v]) {
                while (stack.peek() != v) {
                    component.add(stack.pop());
                }
                component.add(stack.pop());
                components.add(component);
            }
        }
}
