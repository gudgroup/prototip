import com.sun.org.apache.bcel.internal.generic.FCMPG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by jongeit on 30.06.15.
 */
public class Panel extends JPanel{
    private static String[] items = {"2","3","4","5","6","7","8","9","10"};
    private String[][] data;
    private boolean[][] ActivRibs;
    private int[][] temp;
    private String[] header;
    private static int move = 0;
    private JTable matrix;
    private JButton make;
    private JTextArea log;
    private JButton begin;
    private JButton forward;
    private Panel2 canvas;
    private static int sizearr;
    public Panel(){
        setLayout(null);
        setLocation(20,20);
        setSize(500,500);
        JTextArea title = new JTextArea("Выберите количество вершин:");
        title.setEnabled(false);
        title.setDisabledTextColor(Color.black);
        title.setLocation(10,0);
        title.setSize(300,20);
        add(title);
        JComboBox choise = new JComboBox(items);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JComboBox box = (JComboBox)e.getSource();
                String item = (String)box.getSelectedItem();
                addTable(Integer.parseInt(item));
            }
        };
        choise.addActionListener(actionListener);
        choise.setLocation(10,30);
        choise.setSize(80,20);
        add(choise);
    }
    public void addTable(int sizetable){
        sizearr = sizetable;
        move = 0;
        data = new String[sizetable][sizetable];
        header = new String[sizetable];
        for(int i = 0; i < sizetable; i++){
            for(int j = 0; j < sizetable; j++) {
                if(i != j) {
                    data[i][j] = null;
                }
                else{
                    data[i][j] = "∞";
                }
            }
        }
        for(int i = 0; i < sizetable; i++){
            header[i] = Integer.toString(i+1);
        }
        if(matrix != null){
            remove(matrix.getTableHeader());
            remove(matrix);
            remove(make);
            if(canvas != null){
                remove(log);
                remove(begin);
                remove(forward);
                remove(canvas);
            }
        }
        matrix = new JTable(data,header);
        matrix.setLocation(10,76);
        matrix.setSize(30*sizetable,16*(sizetable));
        for(int i = 0; i < sizetable; i++) {
            matrix.getColumnModel().getColumn(i).setMinWidth(30);
            matrix.getColumnModel().getColumn(i).setMaxWidth(30);
        }
        matrix.getTableHeader().setSize(30*sizetable,16);
        matrix.getTableHeader().setLocation(10,60);
        add(matrix.getTableHeader());
        add(matrix);
        make = new JButton("Построить граф");
        make.setSize(150,30);
        make.setLocation(10,16*sizetable+80);
        add(make);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                drawGraf(sizetable);
            }
        };
        make.addActionListener(actionListener);
        //matrix.di
        updateUI();
    }
    public void drawGraf(int size){
        matrix.setEnabled(false);
        make.setEnabled(false);
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                data[i][j] = (String)matrix.getValueAt(i,j);
            }
        }
        log = new JTextArea();
        log.setEnabled(false);
        log.setDisabledTextColor(Color.black);
        log.setLocation(10,16*size+130);
        log.setSize(340,200);
        add(log);
        canvas=new Panel2(size,data,null,-1);
        canvas.setLocation(30*size+200,60);
        canvas.setSize(700,700);
        begin = new JButton("Начать с начала");
        begin.setSize(170, 30);
        begin.setLocation(30*size+200, 480);
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(matrix.getTableHeader());
                remove(matrix);
                remove(make);
                remove(log);
                remove(begin);
                remove(forward);
                remove(canvas);
                updateUI();
            }
        };
        begin.addActionListener(actionListener);
        forward = new JButton("Следующий шаг");
        forward.setSize(170, 30);
        forward.setLocation(30*size+380, 480);
        ActionListener actionListener1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove(matrix.getTableHeader());
                remove(matrix);
                remove(log);
                remove(canvas);
                floid(move);
                matrix = new JTable(data,header);
                matrix.setLocation(10,76);
                matrix.setSize(30*size,16*(size));
                matrix.getTableHeader().setSize(30*size,16);
                matrix.getTableHeader().setLocation(10,60);
                canvas=new Panel2(size,data,ActivRibs,move);
                canvas.setLocation(30*size+200,60);
                canvas.setSize(700,700);
                add(matrix.getTableHeader());
                add(matrix);
                add(canvas);
                updateUI();
                move++;
                if(move == sizearr){
                    JOptionPane.showMessageDialog(null, "Это последний шаг алгоритма", "Сообщение", JOptionPane.INFORMATION_MESSAGE);
                    forward.setEnabled(false);
                }
            }
        };
        forward.addActionListener(actionListener1);
        add(make);
        add(begin);
        add(forward);
        add(canvas);
        updateUI();
    }
    public void floid(int move){
        log = new JTextArea();
        log.setEnabled(false);
        log.setDisabledTextColor(Color.black);
        log.setLocation(10,16*sizearr+130);
        log.setSize(340,200);
        add(log);
        temp = new int[sizearr][sizearr];
        int tt;
        for(int i = 0; i < sizearr; i++){
            for(int j = 0; j < sizearr; j++){
                if(data[i][j] != null && data[i][j] != "∞" && data[i][j] != "")
                {
                    temp[i][j] = Integer.parseInt(data[i][j]);
                }
                else if(i==j){
                    temp[i][j] = 0;
                }
                else{
                    temp[i][j] = 9999;
                }
            }
        }
        log.append("Ищем оптимальные пути от вершины " + (move+1) + "\n");
        for (int i = 0; i < sizearr; i++) {
            ActivRibs = new boolean[sizearr][sizearr];
            for (int j = 0; j < sizearr; j++) {
                tt = temp[i][j];
                temp[i][j] = Math.min(temp[i][j], temp[i][move] + temp[move][j]);
                if(temp[i][j] < 9999 && (temp[i][move] + temp[move][j]) < 9999){
                    log.append("Ищем минимум (" + temp[i][j] + "," + (temp[i][move] + temp[move][j]) + ")\n");
                }
                if(tt != temp[i][j]){
                    log.append("Найден оптимальный путь от вершины " + (i+1) + " до " + (j+1) + " = " + temp[i][j] + "\n");
                    ActivRibs[i][move]=true;
                    ActivRibs[move][j]=true;
                }
            }
        }
        for(int i = 0; i < sizearr; i++){
            for(int j = 0; j < sizearr; j++){
                if(temp[i][j] != 9999 && temp[i][j] != 0)
                {
                    data[i][j] = Integer.toString(temp[i][j]);
                }
                else{
                    data[i][j] = "∞";
                }
            }
        }
    }
}
