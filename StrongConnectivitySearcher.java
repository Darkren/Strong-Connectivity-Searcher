import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by Darkrengarius on 06.06.2016.
 */
public class StrongConnectivitySearcher {
    private static ArrayList<ArrayList<Boolean>> graphMatrix = new ArrayList<>();

    public static void main(String[] args) {
        //Creating the file choosing dialog
        JFileChooser fileOpener = new JFileChooser();
        //Adding the extensions filter AccountsFileFilter
        fileOpener.setFileFilter(new InputFileFilter(".txt", ".txt"));
        //Forbid the "AllFiles" type
        fileOpener.setAcceptAllFileFilterUsed(false);
        int retCode = fileOpener.showDialog(null, "Choose file");
        //If the file was chosen to be opened
        if (retCode == JFileChooser.APPROVE_OPTION) {
            File file = fileOpener.getSelectedFile();
            //If the file doesn't exist - show error message
            if (!file.exists()) {
                JOptionPane.showMessageDialog(new JFrame(), "File doesn't exist", "Error!",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            graphMatrix = readGraphFromFile(file);
        } else {
            System.exit(0);
        }

        MainFrame mainFrame = new MainFrame(graphMatrix);
    }

    public static ArrayList<ArrayList<Boolean>> readGraphFromFile (File file) {
        ArrayList<ArrayList<Boolean>> graph = new ArrayList<>();

        try (BufferedReader rd = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = rd.readLine()) != null) {
                String[] vertexes = line.split(" ");
                ArrayList<Boolean> row = new ArrayList<>();
                for (String s : vertexes) {
                    row.add((s.equals("0")) ? false : true);
                }
                graph.add(row);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }

        return graph;
    }
}
