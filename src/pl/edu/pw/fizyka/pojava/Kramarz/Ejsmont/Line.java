package pl.edu.pw.fizyka.pojava.Kramarz.Ejsmont;
//author Julia Ejsmont

import java.awt.*;

public class Line {
    private int xLineStart;
    private int yLineStart;
    private int xLineEnd;
    private int yLineEnd;
    private Color color1;
    private Color color2;

    public Line(int xLineStart, int yLineStart, int  xLineEnd, int yLineEnd, Color color1, Color color2) {
        this.xLineStart = xLineStart;
        this.yLineStart = yLineStart;
        this.xLineEnd =  xLineEnd;
        this.yLineEnd = yLineEnd;
        this.color1 = color1;
        this.color2 = color2;
    }

   public void draw(Graphics2D g)
    {
        GradientPaint gradientOfLine = new GradientPaint(xLineStart, yLineStart, color1,  xLineEnd, yLineEnd, color2);
        g.setPaint(gradientOfLine);
        g.drawLine(xLineStart, yLineStart,  xLineEnd, yLineEnd);
    }
}//end of Line class
