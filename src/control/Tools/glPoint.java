/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Tools;

/**
 *
 * @author Geoff
 */

import java.util.*;
import org.lwjgl.opengl.GL11;

public class glPoint {
    double[] pos = new double[3];
    double[] typ = new double[3];
    public int[] convertInt(){
        return new int[]{(int)pos[0],(int)pos[1],(int)pos[2]};
    }
    public glPoint(){
        pos = new double[3];
        pos[0] = 0f;
        pos[1] = 0f;
        pos[2] = 0f;
        typ[0] = 0f;
        typ[1] = 0f;
        typ[2] = 1f;
    }
    public glPoint(double a, double b, double c){
        pos = new double[3];
        pos[0] = a;
        pos[1] = b;
        pos[2] = c;
    }
    public glPoint(double a, double b, double c,double t){
        pos = new double[3];
        pos[0] = a;
        pos[1] = b;
        pos[2] = c;
        typ= new double[3];
        typ[0]=0;
        typ[1]=0;
        typ[2]=t;
    }
    public glPoint(double a, double b, double c,double r, double s,double t){
        pos = new double[3];
        pos[0] = a;
        pos[1] = b;
        pos[2] = c;
        typ= new double[3];
        typ[0]=r;
        typ[1]=s;
        typ[2]=t;
    }
    public glPoint(float[] a){
        pos = new double[3];
        pos[0] = a[0];
        pos[1] = a[1];
        pos[2] = a[2];
        typ= new double[3];
        typ[2]= a[3];
    }
    public glPoint(float[] a,double b, double c, double d){
        pos = new double[3];
        pos[0] = a[0];
        pos[1] = a[1];
        pos[2] = a[2];
        typ= new double[3];
        typ[2]= a[3];
        typ[0]= b;
        typ[1]= c;
    }
    public glPoint(double[] a,double [] b){
        pos = new double[3];
        pos[0] = a[0];
        pos[1] = a[1];
        pos[2] = a[2];
        typ= new double[3];
        typ[0]= b[0];
        typ[1]= b[1];
        typ[2]= b[2];
    }
    public glPoint(glPoint rhs){
        pos = new double[3];
        typ = new double[3];
        pos[0]=rhs.pos[0];
        pos[1]=rhs.pos[1];
        pos[2]=rhs.pos[2];
        typ[0]=rhs.typ[0];
        typ[1]=rhs.typ[1];
        typ[2]=rhs.typ[2];
    }
    public glPoint(double a, double b){
        pos = new double[3];
        pos[0] = a;
        pos[1] = b;
        pos[2] = 0f;
    }
    public glPoint abs()
    {
        return new glPoint(Math.abs(pos[0]),Math.abs(pos[1]),Math.abs(pos[2]));
    }
    public double get(int a){
        return pos[a];
    }
    public float[] colour()
    {
        return new float[] {(float)pos[0],(float)pos[1],(float)pos[2],(float)typ[2]};
    }
    public float[] hue()
    {
        float h=(float)pos[0];
        float S=(float)pos[1];
        float V=(float)pos[2];
        
        float var_h=h/60;
        if ( var_h == 6 ) var_h = 0 ;
        float var_i = (float)Math.floor( var_h );  
        float var_1 = V * ( 1 - S );
        float var_2 = V * ( 1 - S * ( var_h - var_i ) );
        float var_3 = V * ( 1 - S * ( 1 - ( var_h - var_i ) ) );

        
        glPoint nme=new glPoint();
        if      ( var_i == 0 ) { nme.pos[0]= V     ; nme.pos[1] = var_3 ; nme.pos[2] = var_1; }
        else if ( var_i == 1 ) { nme.pos[0] = var_2 ; nme.pos[1] = V     ; nme.pos[2] = var_1; }
        else if ( var_i == 2 ) { nme.pos[0] = var_1 ; nme.pos[1] = V     ; nme.pos[2] = var_3; }
        else if ( var_i == 3 ) { nme.pos[0] = var_1 ; nme.pos[1] = var_2 ; nme.pos[2] = V;     }
        else if ( var_i == 4 ) { nme.pos[0] = var_3 ; nme.pos[1] = var_1 ; nme.pos[2] = V;     }
        else                   { nme.pos[0] = V     ; nme.pos[1] = var_1 ; nme.pos[2] = var_2; }
        return new float[] {(float)nme.x(),(float)nme.y(),(float)nme.z(),(float)typ[2]};
    }
    public float[] convertRGBhue()
    {
        double H=0;
        double S=0;
        double V=0;
        
        double var_r = pos[0];                     //RGB from 0 to 255
        double var_g = pos[1];
        double var_b = pos[2];

        double var_Min = Math.min( var_r, Math.min(var_g, var_b ));    //Min. value of RGB
        double var_Max = Math.max( var_r, Math.max(var_g, var_b ));    //Max. value of RGB
        double del_Max = var_Max - var_Min;             //Delta RGB value 

        V = var_Max*1.0f;
        

            if ( del_Max == 0 )                     //This is a gray, no chroma...
            {
                H = 0;                                //HSV results from 0 to 1
                S = 0;
            }
            else                                    //Chromatic data...
            {
                 S = del_Max / var_Max;

                 double del_R = ( ( ( var_Max - var_r ) / 6f ) + ( del_Max / 2f ) ) / del_Max;
                 double del_G = ( ( ( var_Max - var_g ) / 6f ) + ( del_Max / 2f ) ) / del_Max;
                 double del_B = ( ( ( var_Max - var_b ) / 6f ) + ( del_Max / 2f ) ) / del_Max;

                if      ( var_r == var_Max ) H = del_B - del_G;
                else if ( var_g == var_Max ) H = ( 1f / 3f ) + del_R - del_B;
                else if ( var_b == var_Max ) H = ( 2f / 3f ) + del_G - del_R;

                if ( H < 0 ) H += 1;
                if ( H > 1 ) H -= 1;
        }
//            System.out.println((new float[] {(float)H*360,(float)S,(float)V,(float)typ[2]})[2]);
        return new float[] {(float)H*360,(float)S,(float)V,(float)1};
    }
    public void set(int xyz, double value)
    {
        pos[xyz] = value;
    }
    public double tget(int a){
        return typ[a];
    }
    
    public void tset(int xyz, double value)
    {
        typ[xyz] = value;
    }
    public void minus(int xyz)
    {
        //System.out.println(pos[xyz]);
        pos[xyz]-=01.0f;
        //System.out.println(pos[xyz]);
    }
    public glPoint xy(float x, float y)
    {
        return new glPoint(pos[0]+x,pos[1],pos[2]+y,typ[0],typ[1],typ[2]);
    }
    public void add(int xyz)
    {
        pos[xyz]+=1.0f;
    }
    public void tadd(int xyz)
    {
        typ[xyz]+=0.01f;
    }
    public double x()
    {
        return pos[0];
    }
    public double y()
    {
        return pos[1];
    }
    public double z()
    {
        return pos[2];
    }
    public char[] getColourChars()
    {
        //System.out.println(((int)pos[0]*255+50)+""+((int)pos[1]*255+50)+""+((int)pos[2]*255+50));
        return new char[]{(char)((int)(pos[0]*255)),(char)((int)(pos[1]*255)),(char)((int)(pos[2]*255))};
    }
    public glPoint(char[] buff)
    {
        pos[0]=((float)buff[0])/255f;
        pos[1]=((float)buff[1])/255f;
        pos[2]=((float)buff[2])/255f;
        typ[2]=1;
    }
    public glPoint Pretebbbbnd(double a, double b, double c)
    {
        return new glPoint(pos[0]+a,pos[1]+b,pos[2]+c); 
    }
    public glPoint Pretend(glPoint p)
    {
        return new glPoint(pos[0]+p.get(0),pos[1]+p.get(1),pos[2]+p.get(2)); 
    }
    
    public void drawPoint()
    {
        GL11.glVertex3d(pos[0],pos[1],pos[2]);
    }
    public glPoint minus (glPoint oP)
    {
        return new glPoint(x()-oP.x(),y()-oP.y(),z()-oP.z());
    }
    public glPoint add (glPoint a)
    {
        return new glPoint(pos[0]+a.pos[0],pos[1]+a.pos[1],pos[2]+a.pos[2]);
    }
    public glPoint mul (double a)
    {
        return new glPoint(pos[0]*a,pos[1]*a,pos[2]*a);
    }
    public boolean equals(glPoint o)
    {
        if(x()==o.x()&&y()==o.y()&&z()==o.z())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public void drawCube()
    {
        GL11.glVertex3d(pos[0]-0.025f,pos[1]-0.025f,pos[2]-0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]-0.025f,pos[2]-0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]+0.025f,pos[2]-0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]+0.025f,pos[2]-0.025f);
        
        GL11.glVertex3d(pos[0]-0.025f,pos[1]-0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]-0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]+0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]+0.025f,pos[2]+0.025f);
        
        GL11.glVertex3d(pos[0]+0.025f,pos[1]-0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]-0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]-0.025f,pos[2]-0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]-0.025f,pos[2]-0.025f);
        
        GL11.glVertex3d(pos[0]-0.025f,pos[1]+0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]+0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]+0.025f,pos[2]-0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]+0.025f,pos[2]-0.025f);
        
        GL11.glVertex3d(pos[0]-0.025f,pos[1]-0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]+0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]+0.025f,pos[2]-0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]-0.025f,pos[2]-0.025f);
        
        GL11.glVertex3d(pos[0]+0.025f,pos[1]+0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]-0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]-0.025f,pos[2]-0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]+0.025f,pos[2]-0.025f);
        
        GL11.glVertex3d(pos[0]+0.025f,pos[1]-0.025f,pos[2]-0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]-0.025f,pos[2]-0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]+0.025f,pos[2]-0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]+0.025f,pos[2]-0.025f);
        
        GL11.glVertex3d(pos[0]+0.025f,pos[1]-0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]-0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]+0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]+0.025f,pos[2]+0.025f);
        
        GL11.glVertex3d(pos[0]-0.025f,pos[1]-0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]-0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]-0.025f,pos[2]-0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]-0.025f,pos[2]-0.025f);
        
        GL11.glVertex3d(pos[0]+0.025f,pos[1]+0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]+0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]+0.025f,pos[2]-0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]+0.025f,pos[2]-0.025f);
        
        GL11.glVertex3d(pos[0]-0.025f,pos[1]+0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]-0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]-0.025f,pos[2]-0.025f);
        GL11.glVertex3d(pos[0]-0.025f,pos[1]+0.025f,pos[2]-0.025f);
        
        GL11.glVertex3d(pos[0]+0.025f,pos[1]-0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]+0.025f,pos[2]+0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]+0.025f,pos[2]-0.025f);
        GL11.glVertex3d(pos[0]+0.025f,pos[1]-0.025f,pos[2]-0.025f);
        
    }
    public glPoint Cross(glPoint oP)
    {
        return new glPoint(pos[1]*oP.get(2) - pos[2]*oP.get(1) , pos[2]*oP.get(0) - pos[0]*oP.get(2), pos[0]*oP.get(1) - pos[1]*oP.get(0));
        
    }
    public double dot(glPoint v){
    return (get(0) * (v).get(0) + get(1) * v.get(1) + get(2) * (v).get(2));
    }
    public double distance()
    {
        return Math.sqrt(Math.pow(x(),2)+Math.pow(y(),2)+Math.pow(z(),2));
    }
    public glPoint divide(glPoint d)
    {
        return new glPoint(pos[0]/d.pos[0],pos[1]/d.pos[1],pos[2]/d.pos[2]);
    }
}
