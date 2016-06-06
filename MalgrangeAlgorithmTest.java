/**
 * Created by Darkrengarius on 06.06.2016.
 */

import junit.framework.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

public class MalgrangeAlgorithmTest {
    @Test
    public void testMalgrangeAlg () {
        File graphFile = new File("C:\\JavaFiles\\input.txt");
        ArrayList<ArrayList<Boolean>> graph = StrongConnectivitySearcher.readGraphFromFile(graphFile);
        MalgrangeAlgorithm malgrangeSearcher = new MalgrangeAlgorithm(graph);
        ArrayList<ArrayList<Integer>> components = malgrangeSearcher.findComponents();

        ArrayList<ArrayList<Integer>> componentsTest = new ArrayList<>();

        ArrayList<Integer> component1 = new ArrayList<>();
        component1.add(0);
        component1.add(1);
        component1.add(2);

        ArrayList<Integer> component2 = new ArrayList<>();
        component2.add(3);
        component2.add(4);
        component2.add(5);

        ArrayList<Integer> component3 = new ArrayList<>();
        component3.add(6);
        component3.add(7);
        component3.add(8);

        componentsTest.add(component1);
        componentsTest.add(component2);
        componentsTest.add(component3);

        Assert.assertEquals(components, componentsTest);
    }
}
