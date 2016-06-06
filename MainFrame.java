import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Darkrengarius on 06.06.2016.
 */
public class MainFrame extends JFrame {
    private ArrayList<ArrayList<Boolean>> graphMatrix;
    private ArrayList<ArrayList<Integer>> tarjanComponents;
    private ArrayList<ArrayList<Integer>> malgrangeComponents;
    private ArrayList<ArrayList<Integer>> kosarajuComponents;

    public MainFrame (ArrayList<ArrayList<Boolean>> graphMatrix) {
        super("Strong connectivity components searcher");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.graphMatrix = graphMatrix;

        tarjanComponents = new ArrayList<>();
        malgrangeComponents = new ArrayList<>();
        kosarajuComponents = new ArrayList<>();

        JPanel resultsPanel = new JPanel();

        JLabel tarjanResultCaption = new JLabel("Tarjan algorithm results:");
        JLabel malgrangeResultCaption = new JLabel("Malgrange algorithm results:");
        JLabel kosarajuResultCaption = new JLabel("Kosaraju algorithm results:");

        JLabel tarjanResult = new JLabel("");
        JLabel malgrangeResult = new JLabel("");
        JLabel kosarajuResult = new JLabel("");

        JPanel tarjanPanel = new JPanel();
        tarjanPanel.setLayout(new BoxLayout(tarjanPanel, BoxLayout.Y_AXIS));
        tarjanPanel.add(tarjanResultCaption);
        tarjanPanel.add(tarjanResult);

        JButton tarjanButton = new JButton("Calculate");
        tarjanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TarjanAlgorithm tarjanSearcher = new TarjanAlgorithm(graphMatrix);
                tarjanComponents = tarjanSearcher.findComponents();
                setLabelText(tarjanComponents, tarjanResult);
                tarjanButton.setVisible(false);
            }
        });
        tarjanPanel.add(tarjanButton);

        JPanel malgrangePanel = new JPanel();
        malgrangePanel.setLayout(new BoxLayout(malgrangePanel, BoxLayout.Y_AXIS));
        malgrangePanel.add(malgrangeResultCaption);
        malgrangePanel.add(malgrangeResult);

        JButton malgrangeButton = new JButton("Calculate");
        malgrangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MalgrangeAlgorithm malgrangeSearcher = new MalgrangeAlgorithm(graphMatrix);
                malgrangeComponents = malgrangeSearcher.findComponents();
                setLabelText(malgrangeComponents, malgrangeResult);
                malgrangeButton.setVisible(false);
            }
        });
        malgrangePanel.add(malgrangeButton);

        JPanel kosarajuPanel = new JPanel();
        kosarajuPanel.setLayout(new BoxLayout(kosarajuPanel, BoxLayout.Y_AXIS));
        kosarajuPanel.add(kosarajuResultCaption);
        kosarajuPanel.add(kosarajuResult);

        JButton kosarajuButton = new JButton("Calculate");
        kosarajuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KosarajuAlgorithm kosarajuSearcher = new KosarajuAlgorithm(graphMatrix);
                kosarajuComponents = kosarajuSearcher.findComponents();
                setLabelText(kosarajuComponents, kosarajuResult);
                kosarajuButton.setVisible(false);
            }
        });
        kosarajuPanel.add(kosarajuButton);

        resultsPanel.add(tarjanPanel);
        resultsPanel.add(malgrangePanel);
        resultsPanel.add(kosarajuPanel);

        add(resultsPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setLabelText (ArrayList<ArrayList<Integer>> components, JLabel label) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < components.size(); i++) {
            stringBuilder.append("[ ");
            for (int j = 0; j < components.get(i).size() - 1; j++) {
                stringBuilder.append("" + components.get(i).get(j) + ", ");
            }
            stringBuilder.append("" + components.get(i).get(components.get(i).size() - 1) + " ]\n");
        }
        label.setText(stringBuilder.toString());
    }
}
