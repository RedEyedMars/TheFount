/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Worldly;

import control.Ables.Drawable;
import control.GLApp_LandSmash;
import control.Misc.Bite;
import glapp.GLApp;
import java.awt.Color;
import java.util.Observable;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Geoff
 */
public class Square implements Drawable,Comparable{
        public Square(byte X,byte Y, byte Z, Color C, Environment E){
            xz[0]=X;
            xz[1]=Y;
            xz[2]=Z;
            y=(byte)(7*Math.random()+10);            
            
            mColour=C;
            for(int m=0;m<4*Math.random();m++){
                if(Math.random()>0.66f)mColour=mColour.darker();
                else if(Math.random()>0.5f)mColour=mColour.brighter();
                
            }
            mEnviron=E;
        }
        public Square(byte X,byte Y, byte Z,byte a, Color C, Environment E){
            xz[0]=X;
            xz[1]=Y;
            xz[2]=Z;
            y=a;            
            mColour=C;
            mEnviron=E;
        }
        public Square(Square s,Bite b){
            xz[0]=b.get()[0];
            xz[1]=s.getXZ()[1];
            xz[2]=b.get()[2];
            y=s.y;
            mEnviron=s.getEnviron();
            mColour=s.mColour;
        }
        public Square(Bite b){
            xz[0]=b.get()[0];
            xz[1]=b.get()[1];
            xz[2]=b.get()[2];
            y=1;            
            
        }
        public Square(byte X,byte Y, byte Z, Color C){
            xz[0]=X;
            xz[1]=Y;
            xz[2]=Z;
            y=(byte)(7*Math.random()+1);            
            
            mColour=C;
            for(int m=0;m<4*Math.random();m++){
                if(Math.random()>0.66f)mColour=mColour.darker();
                else if(Math.random()>0.5f)mColour=mColour.brighter();
                
            }
        }
        public Square(){
            xz[0]=0;
            xz[1]=0;
            xz[2]=0;
            y=0;
            mColour=Color.black;
            mEnviron=null;
        }
        public Square(Square s){
            xz[0]=s.xz[0];
            xz[1]=s.xz[1];
            xz[2]=s.xz[2];
            y=s.y;
            mColour=s.mColour;
            
            mEnviron=s.mEnviron;
        }
        Environment mEnviron;
        Byte[] xz=new Byte[3];
        Byte y;
        Color mColour=Color.green;
        public Environment getEnviron(){
            return mEnviron;
        }
        public Environment getVantage(){
            return mEnviron.VantageOf(this);
        }
        public void draw(){            
            GLApp.setMaterial( mColour.getRGBComponents(new float[4]), 1.0f);
        GL11.glVertex3d(xz[0]-0.5f,xz[1]*0.075f,xz[2]-0.5f);
        GL11.glVertex3d(xz[0]+0.5f,xz[1]*0.075f,xz[2]-0.5f);
        GL11.glVertex3d(xz[0]+0.5f,(xz[1]+y)*0.075f,xz[2]-0.5f);
        GL11.glVertex3d(xz[0]-0.5f,(xz[1]+y)*0.075f,xz[2]-0.5f);
        
        GL11.glVertex3d(xz[0]-0.5f,(xz[1]+y)*0.075f,xz[2]+0.5f);
        GL11.glVertex3d(xz[0]+0.5f,(xz[1]+y)*0.075f,xz[2]+0.5f);
        GL11.glVertex3d(xz[0]+0.5f,xz[1]*0.075f,xz[2]+0.5f);
        GL11.glVertex3d(xz[0]-0.5f,xz[1]*0.075f,xz[2]+0.5f);
        
        GL11.glVertex3d(xz[0]+0.5f,xz[1]*0.075f,xz[2]-0.5f);
        GL11.glVertex3d(xz[0]+0.5f,xz[1]*0.075f,xz[2]+0.5f);
        GL11.glVertex3d(xz[0]+0.5f,(xz[1]+y)*0.075f,xz[2]+0.5f);
        GL11.glVertex3d(xz[0]+0.5f,(xz[1]+y)*0.075f,xz[2]-0.5f);
        
        GL11.glVertex3d(xz[0]-0.5f,(xz[1]+y)*0.075f,xz[2]-0.5f);
        GL11.glVertex3d(xz[0]-0.5f,(xz[1]+y)*0.075f,xz[2]+0.5f);
        GL11.glVertex3d(xz[0]-0.5f,xz[1]*0.075f,xz[2]+0.5f);
        GL11.glVertex3d(xz[0]-0.5f,xz[1]*0.075f,xz[2]-0.5f);
        
        GL11.glVertex3d(xz[0]-0.5f,(xz[1]+y)*0.075f,xz[2]-0.5f);
        GL11.glVertex3d(xz[0]+0.5f,(xz[1]+y)*0.075f,xz[2]-0.5f);
        GL11.glVertex3d(xz[0]+0.5f,(xz[1]+y)*0.075f,xz[2]+0.5f);
        GL11.glVertex3d(xz[0]-0.5f,(xz[1]+y)*0.075f,xz[2]+0.5f);
        
        GL11.glVertex3d(xz[0]+0.5f,xz[1]*0.075f,xz[2]-0.5f);
        GL11.glVertex3d(xz[0]-0.5f,xz[1]*0.075f,xz[2]-0.5f);
        GL11.glVertex3d(xz[0]-0.5f,xz[1]*0.075f,xz[2]+0.5f);
        GL11.glVertex3d(xz[0]+0.5f,xz[1]*0.075f,xz[2]+0.5f);
        }
        public Integer getX() {
            return Integer.valueOf(Byte.toString(xz[0]));
        }
        public Integer getY() {
            return Integer.valueOf(Byte.toString(xz[2]));
        }
        public int compareTo(Object o) {
            if(o.getClass().getName().equals("gldemo.Environment$Square")&&mColour.equals(((Square)o).mColour)){
                return 0;
            }
            return 1;
        }
        public boolean equals(Object o){
            //if(GLApp_LandSmash.WORLD.mZoomed==0)System.out.println(o.getClass().getName());
            //System.out.println(o.getClass().getSuperclass().getName());
            if(o.getClass().getName().equals("control.Worldly.Square")||o.getClass().getSuperclass().getName().equals("control.Worldly.Square")){
                //if(GLApp_LandSmash.WORLD.mZoomed==0)System.out.println("here");
                return xz[0]==((Square)o).xz[0]&&xz[2]==((Square)o).xz[2];
            }
            else return false;
        }
        public double distance(Square c){
            return Math.sqrt(Math.pow(xz[0]-c.xz[0],2)+Math.pow(xz[1]-c.xz[1],2)+Math.pow(xz[2]-c.xz[2],2));
        }
        public Square mul(double x){
            Square ret = new Square(this);
            for(byte c:ret.getXZ()){
                c=(byte)(c*x);
            }
            return ret;
        }
        public Square add(double x){
            Square ret = new Square(this);
            for(byte c:ret.xz){
                c=(byte)(c+x);
            }
            return ret;
        }
        public Square add(byte[] x){
            Square ret = new Square(this);
            if(x.length==2){
                ret.xz[0]=(byte)(ret.xz[0]+x[0]);
                ret.xz[2]=(byte)(ret.xz[2]+x[1]);
            }
            if(x.length==3){
                ret.xz[1]=(byte)(ret.xz[1]+x[1]);
            }
            
            //System.out.println(ret);
            return ret;
        }
        public Square add(Square x){
            Square ret = new Square(this);
                ret.xz[0]=(byte)(ret.xz[0]+x.getXZ()[0]);
                ret.xz[2]=(byte)(ret.xz[2]+x.getXZ()[2]);
            
            //System.out.println(ret);
            return ret;
        }
        public void set(byte[] a){
            for(int m=0;m<3;m++){
                xz[m]=a[m];
            }
        }
        public void setYbyte(byte x){
            xz[1]=x;
        }
        public void setAmount(byte x){
            y=x;
        }
        public Byte[] getXZ(){
            return xz;
        }
        public String toString(){
            return "("+xz[0]+","+xz[1]+","+xz[2]+")";
        }
        public Square dif(Square s){
            return new Square((byte)(s.xz[0]-xz[0]),(byte)(s.xz[1]-xz[1]),(byte)(s.xz[2]-xz[2]),s.mColour);
        }
        public double angle(){
            return Math.atan2(xz[2],xz[0]);
        }
        public void decrease(){
            if(y!=0){
                y--;
            }
        }
        public void increase(){
            if(y<127){
                y++;
            }
        }
        public byte getAmount(){
            return y;
        }
        public boolean isBetween(Square s, Square d){
            return xz[0]>=s.xz[0]&&xz[0]<d.xz[0]&&xz[2]>=s.xz[2]&&xz[2]<d.xz[2];
        }
        public byte top(){
            return (byte)(xz[1]+y);
        }
        public byte bot(){
            return xz[1];
        }
        public Square indexify(){
            return new Square((byte)(xz[0]+getEnviron().getWholeArea().getOffsetWidth()),xz[1],(byte)(xz[2]+getEnviron().getWholeArea().getOffsetHeight()),y,this.mColour,this.getEnviron());
        }
    }
