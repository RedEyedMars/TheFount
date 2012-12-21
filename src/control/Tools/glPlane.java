/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Tools;

import glapp.GLApp;
import java.util.*;

/**
 *
 * @author Geoff
 */
public class glPlane {
    double mA=0;
    double mB=0;
    double mC=0;
    double mD=0;
    
    glPoint dP;
    glPoint n;
    
    glPoint p1;
    glPoint p2;
    glPoint p3;
    
    public static glLine publicLine=new glLine(0,0,0,0);
    
    Vector mIntersections=new Vector();
    public glPlane(){}
    public glPlane(glPlane p){mA=p.mA;mB=p.mB;mC=p.mC;mD=p.mD;dP=new glPoint(p.dP); n=new glPoint(p.n); p1=new glPoint(p.p1); p2= new glPoint(p.p2); p3 = new glPoint(p.p3);}
    public void make(glPoint point1, glPoint point2, glPoint point3, glPoint dirPoint)
    {        
        p1=new glPoint(point1);
        p2=new glPoint(point2);
        p3=new glPoint(point3);
        
        double x1=point1.get(0);
        double y1=point1.get(1);
        double z1=point1.get(2);
        
        double x2=point2.get(0);
        double y2=point2.get(1);
        double z2=point2.get(2);
        
        double x3=point3.get(0);
        double y3=point3.get(1);
        double z3=point3.get(2);
        
        mA = y1* (z2 - z3) + y2 * (z3 - z1) + y3 *(z1 - z2); 
        mB = z1 *(x2 - x3) + z2 *(x3 - x1) + z3 *(x1 - x2); 
        mC = x1 *(y2 - y3) + x2 *(y3 - y1) + x3 *(y1 - y2); 
        mD = -(x1 *(y2 *z3 - y3 *z2) + x2 *(y3 *z1 - y1 *z3) + x3 *(y1* z2 - y2 *z1) );
        
        n = new glPoint(p2.minus(p1).Cross(p3.minus(p1))); 
        dP= new glPoint(dirPoint);
        //=new glPoint(mA,mB,mC);
    }
    
    public glLine Intersect(glPlane oPl)
    {
        //.out.println("A" + mA + "B" + mB + "C" + mC + "mD" + mD);
        glPoint mPerpV=new glPoint(mA,mB,mC);
        glPoint oPerpV=new glPoint(oPl.mA,oPl.mB,oPl.mC);
        glPoint mCrossV=mPerpV.Cross(oPerpV);
        glPoint firstPoint= new glPoint();
        if(mC!=0&&oPl.mC!=0&&(mA*oPl.mB-oPl.mA*mB)!=0)
        {
            firstPoint=new glPoint((mB*oPl.mD-oPl.mB*mD)/(mA*oPl.mB-oPl.mA*mB),(mD*oPl.mA-oPl.mD*mA)/(mA*oPl.mB-oPl.mA*mB),0);
            
        }
        else
        {
            if(mB!=0&&(mA*oPl.mC-oPl.mA*mC)!=0)
            {
                firstPoint=new glPoint((mC*oPl.mD-oPl.mC*mD)/(mA*oPl.mC-oPl.mA*mC),0,(mD*oPl.mA-oPl.mD*mA)/(mA*oPl.mC-oPl.mA*mC));
            }
            else
            {
                 if(mA!=0&&(mB*oPl.mC-oPl.mB*mC)!=0)
                {
                    firstPoint=new glPoint(0,(mC*oPl.mD-oPl.mC*mD)/(mB*oPl.mC-oPl.mB*mC),(mD*oPl.mB-oPl.mD*mB)/(mB*oPl.mC-oPl.mB*mC));
                }
                else
                {
                   firstPoint=new glPoint(mD,mD,mD);
                } 
            } 
        }
        glPoint secondPoint=firstPoint.Pretend(mCrossV);
        
        return new glLine(firstPoint,secondPoint);
        
        
    }
    
/*public
int
intersect3D_2Planes( glPlane Pn1, glPlane Pn2 )
{
    glPoint   u = Pn1.n.Cross(Pn2.n);         // cross product
    double    ax = (u.get(0) >= 0 ? u.get(0) : -u.get(0));
    double    ay = (u.get(1) >= 0 ? u.get(1) : -u.get(1));
    double    az = (u.get(2) >= 0 ? u.get(2) : -u.get(2));

    // test if the two planes are parallel
    if ((ax+ay+az) < 0.000001) {       // Pn1 and Pn2 are near parallel
        // test if disjoint or coincide
        glPoint   v = Pn2.p2.minus(Pn2.p1).minus(Pn1.p2.minus(Pn2.p1));
        if (Pn1.n.dot(v) == 0)         // Pn2.V0 lies in Pn1
            return 1;                   // Pn1 and Pn2 coincide
        else 
            return 0;
        // Pn1 and Pn2 are disjoint
    }
    else
    {

    // Pn1 and Pn2 intersect in a line
    // first determine max abs coordinate of cross product
    int      maxc;                      // max coordinate
    if (ax > ay) {
        if (ax > az)
             maxc = 1;
        else maxc = 3;
    }
    else {
        if (ay > az)
             maxc = 2;
        else maxc = 3;
    }

    // next, to get a point on the intersect line
    // zero the max coord, and solve for the other two
    glPoint    iP=new glPoint();               // intersect point
    double    d1, d2;           // the constants in the 2 plane equations
    d1 = -Pn1.n.dot(Pn1.p2.minus(Pn1.p1));  // note: could be pre-stored with plane
    d2 = -Pn2.n.dot(Pn2.p2.minus(Pn2.p1));  // ditto

    switch (maxc) {            // select max coordinate
    case 1:                    // intersect with x=0
        iP.set(0, 0);
        iP.set(1,(d2*Pn1.n.get(2) - d1*Pn2.n.get(2)) / u.get(0));
        iP.set(2,(d1*Pn2.n.get(1) - d2*Pn1.n.get(1)) / u.get(0));
        break;
    case 2:                    // intersect with y=0
        iP.set(0,(d1*Pn2.n.get(2) - d2*Pn1.n.get(2)) / u.get(1));
        iP.set(1,0);
        iP.set(2,(d2*Pn1.n.get(0) - d1*Pn2.n.get(0)) / u.get(1));
        break;
    case 3:                    // intersect with z=0
        iP.set(0, (d2*Pn1.n.get(1) - d1*Pn2.n.get(1)) / u.get(2));
        iP.set(1, (d1*Pn2.n.get(0) - d2*Pn1.n.get(0)) / u.get(2));
        iP.set(2,0);
    }
    publicLine.X=iP.get(0);
    publicLine.Y=iP.get(1);
    publicLine.Z=iP.get(2);
    
    publicLine.A=iP.get(0)+u.get(0);
    publicLine.B=iP.get(1)+u.get(1);
    publicLine.C=iP.get(2)+u.get(2);
    return 2;
    }}*/

public int intersect_RayTriangle( glLine R)
{
    glPoint   u, v, n;             // triangle vectors
    glPoint    dir, w0, w;          // ray vectors
    double     r, a, b;             // params to calc ray-plane intersect

    // get triangle edge vectors and plane normal
    u = new glPoint(p2.minus(p1));
    v = new glPoint(p3.minus(p1));
                // cross product
    if (this.n.x()==0&&this.n.y()==0&&this.n.z()==0)            // triangle is degenerate
        return -1;                 // do not deal with this case

    glPoint Rayp0=new glPoint(R.X,R.Y,R.Z);
    glPoint Rayp1=new glPoint(R.A,R.B,R.C);
    dir = new glPoint(Rayp1.minus(Rayp0));             // ray direction vector
    w0 = new glPoint(Rayp0.minus(p1));
    a = -this.n.dot(w0);
    b = this.n.dot(dir);
    if (Math.abs(b) < GLApp.SMALL_NUM) {     // ray is parallel to triangle plane
        if (a == 0)                // ray lies in triangle plane
            return 2;
        else {return 0;}             // ray disjoint from plane
    }

    // get intersect point of ray with triangle plane
    r = a / b;
    
    if (r<=0||r > 1.0f)                   // ray goes away from triangle
    {
        //System.out.println("no interesect");
        return 0;  }                // => no intersect
    // for a segment, also test if (r > 1.0) => no intersect

    glLine.publicPoint = new glPoint(Rayp0.add(dir.mul(r)));           // intersect point of ray and plane

    // is I inside T?
    double    uu, uv, vv, wu, wv, D;
    uu = u.dot(u);
    uv = u.dot(v);
    vv = v.dot(v);
    w = glLine.publicPoint.minus(p1);
    wu = w.dot(u);
    wv = w.dot(v);
    D = uv * uv - uu * vv;

    // get and test parametric coords
    double s, t;
    s = (uv * wv - vv * wu) / D;
    if (s < 0.0 || s > 1.0)        // I is outside T
    {return 0;}
    t = (uv * wu - uu * wv) / D;
    if (t < 0.0 || (s + t) > 1.0)  // I is outside T
    {return 0;}

    return 1;                      // I is in T
}
}

