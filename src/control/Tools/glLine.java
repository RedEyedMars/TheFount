/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Tools;

/**
 *
 * @author Geoff
 */
import control.Tools.glPoint;
import org.lwjgl.opengl.GL11;
public class glLine {
        double X=0;
        double Y=0;
        double Z=0;
        double A=0;
        double B=0;
        double C=0;
    char mChar = ' ';
    
    public glLine(double a, double b, double c, double d)
    {
        X=a;
        Y=b;
        Z=0;
        A=c;
        B=d;
        C=0;
    }
    public glLine(glPoint a, glPoint b)
    {
        X=a.get(0);
        Y=a.get(1);
        Z=a.get(2);
        A=b.get(0);
        B=b.get(1);
        C=b.get(2);
    }
    
    public glLine(double a, double b, double c, double d, char cin)
    {
        X=a;
        Y=b;
        Z=0;
        A=c;
        B=d;
        C=0;
        
        mChar = cin;
    }
    
    public char get()
    {
        return mChar;
    }
    public static glPoint publicPoint=new glPoint();
    public int
intersect(glPlane Pn )
{return 0;/*
    glPoint    u = new glPoint(X,Y,Z).minus(new glPoint(A,B,C));
    glPoint   w = new glPoint(A,B,C).minus(Pn.p2.minus(Pn.p1));

    double     D = Pn.n.dot( u);
    double     N = -Pn.n.dot( w);

    if (Math.abs(D) < 0.00000001f) {          // segment is parallel to plane
        if (N == 0)                     // segment lies in plane
            return 2;
        else
            return 0;                   // no intersection
    }
    // they are not parallel
    // compute intersect param
    double sI = N / D;
    if (sI < 0 || sI > 1)
        return 0;                       // no intersection

    publicPoint=new glPoint(A + sI * u.get(0),B+ sI * u.get(1),C+ sI * u.get(2));                 // compute segment intersect point
    return 1;*/
}
    
    public glPoint plane_intersect(glPlane Pl)
    {
        glPoint n=new glPoint(Pl.mA,Pl.mB,Pl.mC);
        glPoint u=new glPoint(X-A,Y-B,Z-C);
        double s = -(Pl.mA*A+Pl.mB*B+Pl.mC*C+Pl.mD)/(n.dot(u));
        
        glPoint Pit=new glPoint(A+s*u.get(0),B+s*u.get(1),C+s*u.get(2));
        if(s>=0&&s<=1)
        {
           
                return Pit;//new glPoint(Pl.p1.get(0) +SI*U.get(0)+TI*V.get(0),Pl.p1.get(1) +SI*U.get(1)+TI*V.get(1),Pl.p1.get(2) +SI*U.get(2)+TI*V.get(2));
        }
            return Pit;
    }
    public void draw()
    {
        GL11.glVertex3d(X,Y,Z);
        GL11.glVertex3d(A,B,C);
    }
    
    public boolean intersectPoint(glPoint ip)
    {
        glPoint mp=new glPoint(X,Y,Z);
        glPoint np=new glPoint(A,B,C);
        if((ip.minus(mp).Cross(ip.minus(np)).distance())/((mp.minus(np)).distance())<0.001f)return true;
        else return false;
    }
}
