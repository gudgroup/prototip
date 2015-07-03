import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by jongeit on 30.06.15.
 */
public class Panel2 extends JPanel
    {
        private static int[][] PointGraf = {{100,200},{100,110},{160,30},{280,0},
                {380,30},{480,110},{480,220},{380,280},{280,330},{180,280}};
        private int ActivPoint;
        private boolean[][] ActivRibs;
        private String[][] GrafRib;
        private int quantity;
        //public static int[][] PointGraf = {{50,50},{90,20},{130,20},{170,50},{170,80},{130,110},{90,110},{50,80}};
        public Panel2(int summ,String[][] Rib,boolean[][] A_Ribs,int A_Point){
            GrafRib = Rib;
            ActivPoint = A_Point;
            ActivRibs = A_Ribs;
            quantity = summ;
        }
        public void paintComponent(Graphics g){
            super.paintComponents(g);
            Rib(GrafRib,g);
            Font font = new Font("Arial", Font.BOLD, 15);
            g.setColor(Color.red);
            g.setFont(font);
            for (int i = 0; i < quantity; i++) {
                if(i != ActivPoint) {
                    g.fillOval(PointGraf[i][0], PointGraf[i][1], 40, 40);
                }
                else{
                    g.setColor(Color.blue);
                    g.fillOval(PointGraf[i][0], PointGraf[i][1], 40, 40);
                    g.setColor(Color.red);
                }
            }
            g.setColor(Color.white);
            for (int i = 0; i < quantity; i++) {
                g.drawString(Integer.toString(i + 1), PointGraf[i][0]+13, PointGraf[i][1]+25);
            }
            /*g.setColor(Color.blue);
            g.fillOval(PointGraf[2][0],PointGraf[2][1],40,40);
            g.setColor(Color.white);
            g.drawString(Integer.toString(3), PointGraf[2][0]+15, PointGraf[2][1]+25);
            //g.drawLine();
            /*g.drawLine(20, 20, 360, 20);
            Color oldColor = g.getColor();
            Color newColor = new Color(0, 0, 255);
            g.setColor(newColor);
            g.drawLine(20, 30, 360, 30);
            g.setColor(oldColor);
            g.drawRect(20, 40, 340, 20);
            newColor = new Color(0, 215, 255);
            g.setColor(newColor);
            g.fillRect(21, 41, 339, 19);
            g.setColor(oldColor);
            g.drawRoundRect(20, 70, 340, 30, 20, 15);
            g.drawOval(20, 110, 150, 60);
            g.drawOval(200, 110, 60, 60);
            g.drawArc(280, 110, 80, 60, 0, 180);
            int[] arrayX = {20, 100, 100, 250, 250, 20, 20, 50};
            int[] arrayY = {180, 180, 200, 200, 220, 200, 200, 190};
            Polygon poly = new Polygon(arrayX, arrayY, 8);
            g.drawPolygon(poly);
            Point aPoint = new Point(50, 190);
            if(poly.contains(aPoint))
            {
                g.drawString("Yes", 50, 190);
            }
            newColor = new Color(0, 0, 255);
            g.setColor(newColor);
            Font font = new Font("Tahoma", Font.BOLD|Font.ITALIC, 40);
            Font oldFont = g.getFont();
            g.setFont(font);
            g.drawString("SBP", 270, 220);
            g.setFont(oldFont);
            g.setColor(oldColor);
            // Draw axes;
            g.drawLine(20, 220, 20, 350);
            g.drawLine(20, 350, 360, 350);
            g.drawString("Y", 25, 230);
            g.drawString("X", 350, 346);
            // Draw a curve;
            int[] xArray = {20, 40, 60, 80, 100, 120, 130, 140, 280, 332};
            int[] yArray = {350, 345, 340, 310, 290, 280, 275, 273, 271, 269};
            int nPoint = 10;
            g.setColor(newColor);
            g.drawPolyline(xArray, yArray, nPoint);
            g.setColor(oldColor);
            g.drawString("y = f(x)", 180, 267);*/
            super.repaint();
        }
        public void drawRib(int from, int in,String cost, Graphics g){
            Graphics2D g2 = (Graphics2D)g;
            if(ActivRibs != null && ActivRibs[from][in] == true){
                g2.setStroke(new BasicStroke(2.0f));
                g2.setColor(Color.red);
            }
            else{
                g2.setStroke(new BasicStroke(1.0f));
                g2.setColor(Color.black);
            }
            Font font = new Font("Arial", Font.BOLD, 10);
            g.setFont(font);
            if(from<in) {
                g2.drawLine(PointGraf[from][0] + 10, PointGraf[from][1] + 10, PointGraf[in][0] + 10, PointGraf[in][1] + 10);
                if(PointGraf[in][0] >= PointGraf[from][0] && PointGraf[in][1] >= PointGraf[from][1]) {
                    //g.drawString(cost, ((PointGraf[in][0] + 10, PointGraf[from][1]*(PointGraf[in][1] - PointGraf[from][1]));
                    g.drawString(cost, PointGraf[from][0] + 20, PointGraf[from][1]+20);
                }

                else if(PointGraf[in][0] >= PointGraf[from][0] && PointGraf[in][1] <= PointGraf[from][1]){
                    //g.drawString(cost, ((PointGraf[in][0] - PointGraf[from][0]) / 2) + PointGraf[from][0] + 10, PointGraf[from][1]*(PointGraf[from][1] - PointGraf[in][1]));
                    g.drawString(cost, PointGraf[from][0] + 20, PointGraf[from][1]-20);
                }
                else if(PointGraf[in][0] <= PointGraf[from][0] && PointGraf[in][1] >= PointGraf[from][1]){
                    //g.drawString(cost, ((PointGraf[from][0] - PointGraf[in][0]) / 2) + PointGraf[in][0] + 10, PointGraf[from][1]*(PointGraf[in][1] - PointGraf[from][1]));
                    g.drawString(cost, PointGraf[from][0] - 20, PointGraf[from][1] + 20);
                }
                else{
                    //g.drawString(cost, ((PointGraf[from][0] - PointGraf[in][0]) / 2) + PointGraf[in][0] + 10, PointGraf[from][1]*(PointGraf[from][1] - PointGraf[in][1]));
                    g.drawString(cost, PointGraf[from][0] - 20, PointGraf[from][1]-20);
                }

              // g.drawString(cost, PointGraf[from][0] + 10, PointGraf[from][1]);*/
            }
            else{
                g2.drawLine(PointGraf[from][0] + 30, PointGraf[from][1] + 30, PointGraf[in][0] + 30, PointGraf[in][1] + 30);
                if(PointGraf[in][0] >= PointGraf[from][0] && PointGraf[in][1] >= PointGraf[from][1]) {
                    g.drawString(cost, PointGraf[from][0] + 20, PointGraf[from][1]+20);
                   // g.drawString(cost, ((PointGraf[in][0] - PointGraf[from][0]) / 2) + PointGraf[from][0] + 10, PointGraf[in][1]*(PointGraf[in][1] - PointGraf[from][1]));
                }
                else if(PointGraf[in][0] >= PointGraf[from][0] && PointGraf[in][1] <= PointGraf[from][1]){
                   // g.drawString(cost, ((PointGraf[in][0] - PointGraf[from][0]) / 2) + PointGraf[from][0] + 10, PointGraf[in][1]*(PointGraf[from][1] - PointGraf[in][1]));
                    g.drawString(cost, PointGraf[from][0] + 20, PointGraf[from][1]+20);
                }
                else if(PointGraf[in][0] <= PointGraf[from][0] && PointGraf[in][1] >= PointGraf[from][1]){
                    //g.drawString(cost, ((PointGraf[from][0] - PointGraf[in][0]) / 2) + PointGraf[in][0] + 10, PointGraf[in][1]*(PointGraf[in][1] - PointGraf[from][1]));
                    g.drawString(cost, PointGraf[from][0] - 20, PointGraf[from][1]+20);
                }
                else {
                    //g.drawString(cost, ((PointGraf[from][0] - PointGraf[in][0]) / 2) + PointGraf[in][0] + 10, PointGraf[in][1] * (PointGraf[from][1] - PointGraf[in][1]));
                    g.drawString(cost, PointGraf[from][0] - 20, PointGraf[from][1]-20);

                }
            }

        }
        public void Rib(String[][] Rib, Graphics g){
            for(int i = 0; i < quantity; i++){
                for(int j = 0; j < quantity; j++){
                    if(Rib[i][j] != "∞" && Rib[i][j] != null && i != j && Rib[i][j] != "0"){
                        drawRib(i,j,Rib[i][j],g);
                    }
                }
            }
        }
        public static void mixingOX(int mixing){
            for (int i = 0; i < PointGraf.length; i++) {
                PointGraf[i][0] = PointGraf[i][0]+mixing;
            }
        }
        public static void mixingOY(int mixing){
            for (int i = 0; i < PointGraf.length; i++) {
                PointGraf[i][1] = PointGraf[i][1]+mixing;
            }
        }
     /*   public static Shape createArrowShape(Point fromPt, Point toPt) {
            Polygon arrowPolygon = new Polygon();
            arrowPolygon.addPoint(-6,1);
            arrowPolygon.addPoint(6,0);
            arrowPolygon.addPoint(3,3);
            arrowPolygon.addPoint(6,0);
            arrowPolygon.addPoint(3,-3);
            arrowPolygon.addPoint(6,0);


            Point midPoint = midpoint(fromPt, toPt);

            double rotate = Math.atan2(toPt.y - fromPt.y, toPt.x - fromPt.x);

            AffineTransform transform = new AffineTransform();
            transform.translate(midPoint.x, midPoint.y);
            double ptDistance = fromPt.distance(toPt);
            double scale = ptDistance / 12.0; // 12 because it's the length of the arrow polygon.
            transform.scale(scale, scale);
            transform.rotate(rotate);

            return transform.createTransformedShape(arrowPolygon);
        }

        private static Point midpoint(Point p1, Point p2) {
            return new Point((int)((p1.x + p2.x)/2.0),
                    (int)((p1.y + p2.y)/2.0));
        }*/

    }
