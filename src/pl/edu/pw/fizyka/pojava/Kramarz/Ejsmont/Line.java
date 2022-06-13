package pl.edu.pw.fizyka.pojava.Kramarz.Ejsmont;
//author Julia Ejsmont, Natalia Kramarz (emissionTime)

import java.awt.*;

public class Line {
  private int xLineStart;
  private int yLineStart;
  private int xLineEnd;
  private int yLineEnd;
  private Color emissionColor;
  long startEmissionTime;
  int emissionTime;

  public Line(int xLineStart, int yLineStart, int  xLineEnd, int yLineEnd, Color emissionColor, long startEmissionTime) {
      this.xLineStart = xLineStart;
      this.yLineStart = yLineStart;
      this.xLineEnd =  xLineEnd;
      this.yLineEnd = yLineEnd;
      this.emissionColor = emissionColor;
      this.startEmissionTime = startEmissionTime;
      calculateEmissionTime();
  }//end Line constructror
  
  public void calculateEmissionTime() {
  	if(emissionColor.getGreen() == 225) {
  		setEmissionTime(70);
  	}
  	else if(emissionColor.getRed() == 255) {
  		setEmissionTime(1100);
  	}
  	else if (emissionColor.getBlue() == 255) {
  		setEmissionTime(60);
  	}
  }//end calculateEmissionTieme
  
 public void draw(Graphics2D g)
  {
      g.setColor(emissionColor);
      g.drawLine(xLineStart, yLineStart, xLineEnd, yLineEnd);
  }//end draw

 public long getStartEmissionTime() {
	   return startEmissionTime;
 }
 public void setStartEmissionTime(long startEmissionTime) {
	   this.startEmissionTime = startEmissionTime;
 }
 public int getEmissionTime() {
	   return emissionTime;
 }
 public void setEmissionTime(int emissionTime) {
	   this.emissionTime = emissionTime;
 }
 public int getxLineStart() {
	   return xLineStart;
 }

 public void setxLineStart(int xLineStart) {
	   this.xLineStart = xLineStart;
 }

 public int getyLineStart() {
	   return yLineStart;
 }

 public void setyLineStart(int yLineStart) {
	   this.yLineStart = yLineStart;
 }

 public int getxLineEnd() {
	   return xLineEnd;
 }

 public void setxLineEnd(int xLineEnd) {
	   this.xLineEnd = xLineEnd;
 }

 public int getyLineEnd() {
	   return yLineEnd;
 }

 public void setyLineEnd(int yLineEnd) {
	   this.yLineEnd = yLineEnd;
 }

 public Color getemissionColor() {
	   return emissionColor;
 }

 public void setemissionColor(Color emissionColor) {
	   this.emissionColor = emissionColor;
 }

}//end of Line class