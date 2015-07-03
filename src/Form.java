import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jongeit on 30.06.15.
 */
public class Form extends JFrame{
    private JPanel paneltest;
    private Panel table;
    public Form(){
        super("Draw");
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(1000,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        /*paneltest = new JPanel();
        paneltest.setVisible(true);
        paneltest.setLayout(new FlowLayout());
        paneltest.setSize(550,200);
        paneltest.setLocation(20,20);
        paneltest.setBackground(Color.red);
        add(paneltest, BorderLayout.WEST);
        JComboBox choise = new JComboBox(items);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox)e.getSource();
                String item = (String)box.getSelectedItem();
                addTable(Integer.parseInt(item));
            }
        };
        choise.addActionListener(actionListener);
        choise.setMaximumSize(new Dimension(200, 200));
        paneltest.add(choise);
        add(paneltest);*/
        Panel m = new Panel();
        add(m);
        setVisible(true);
    }

}
