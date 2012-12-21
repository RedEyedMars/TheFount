/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Tools;

import glapp.*;
/**
 *
 * @author Geoff
 */
import java.util.*;
import org.lwjgl.opengl.GL11;
import control.Tools.glPlane;
public class Tool {
    glPoint mLoc=new glPoint(0,0,0);// "0,0" basically
    glPoint mExtension=new glPoint(1,1,1);//the dimensions(width, length, height);
    glPoint mEx=new glPoint();
    public Tool(glPoint a, glPoint b){mLoc=a;mExtension=b;mEx=new glPoint(mExtension.get(0)+mLoc.get(0),mExtension.get(1)+mLoc.get(1),mExtension.get(2)+mLoc.get(2));}
    Vector mEdges=new Vector();//the renderable and interactable edges
    Vector mConnections=new Vector();//vector of index numbers(glTwoInt)'s
    Vector mTris=new Vector();
    Vector mPoints=new Vector();//the points used to make the endes;
    
    Vector mInters=new Vector();
    
    
    
    public void makeRect(glPoint dir){        
        mPoints.add(new  glPoint(mLoc.get(0),mLoc.get(1)+mExtension.get(1),mLoc.z()+mExtension.z()));        
        mPoints.add(new  glPoint(mLoc.get(0),mLoc.get(1)+mExtension.get(1),mLoc.z()));
        mPoints.add(new  glPoint(mLoc.get(0)+mExtension.get(0),mLoc.get(1)+mExtension.get(1),mLoc.z()));
        mPoints.add(new  glPoint(mLoc.get(0)+mExtension.get(0),mLoc.get(1)+mExtension.get(1),mLoc.z()+mExtension.z()));
        
        mTris.add(new glPlane());
        mTris.add(new glPlane());
        ((glPlane)mTris.get(0)).make((glPoint)mPoints.get(0),(glPoint)mPoints.get(1),(glPoint)mPoints.get(2),dir);
        ((glPlane)mTris.get(1)).make((glPoint)mPoints.get(0),(glPoint)mPoints.get(3),(glPoint)mPoints.get(2),dir);
        
        mPoints.add(new  glPoint(mLoc.get(0),mLoc.get(1),mLoc.z()+mExtension.z()));        
        mPoints.add(new  glPoint(mLoc.get(0),mLoc.get(1),mLoc.z()));
        mPoints.add(new  glPoint(mLoc.get(0)+mExtension.get(0),mLoc.get(1),mLoc.z()));
        mPoints.add(new  glPoint(mLoc.get(0)+mExtension.get(0),mLoc.get(1),mLoc.z()+mExtension.z()));
        
        mTris.add(new glPlane());
        mTris.add(new glPlane());
        ((glPlane)mTris.get(2)).make((glPoint)mPoints.get(4),(glPoint)mPoints.get(5),(glPoint)mPoints.get(6),dir);
        ((glPlane)mTris.get(3)).make((glPoint)mPoints.get(4),(glPoint)mPoints.get(7),(glPoint)mPoints.get(6),dir);
        
        mPoints.add(new  glPoint(mLoc.get(0),mLoc.get(1),mLoc.z()));        
        mPoints.add(new  glPoint(mLoc.get(0),mLoc.get(1)+mExtension.get(1),mLoc.z()));
        mPoints.add(new  glPoint(mLoc.get(0),mLoc.get(1)+mExtension.get(1),mLoc.z()+mExtension.z()));
        mPoints.add(new  glPoint(mLoc.get(0),mLoc.get(1),mLoc.z()+mExtension.z()));
        
        mTris.add(new glPlane());
        mTris.add(new glPlane());
        ((glPlane)mTris.get(4)).make((glPoint)mPoints.get(8),(glPoint)mPoints.get(9),(glPoint)mPoints.get(10),dir);
        ((glPlane)mTris.get(5)).make((glPoint)mPoints.get(8),(glPoint)mPoints.get(11),(glPoint)mPoints.get(10),dir);
        
        mPoints.add(new  glPoint(mLoc.get(0)+mExtension.get(0),mLoc.get(1),mLoc.z()));        
        mPoints.add(new  glPoint(mLoc.get(0)+mExtension.get(0),mLoc.get(1)+mExtension.get(1),mLoc.z()));
        mPoints.add(new  glPoint(mLoc.get(0)+mExtension.get(0),mLoc.get(1)+mExtension.get(1),mLoc.z()+mExtension.z()));
        mPoints.add(new  glPoint(mLoc.get(0)+mExtension.get(0),mLoc.get(1),mLoc.z()+mExtension.z()));
        
        mTris.add(new glPlane());
        mTris.add(new glPlane());
        ((glPlane)mTris.get(6)).make((glPoint)mPoints.get(12),(glPoint)mPoints.get(13),(glPoint)mPoints.get(14),dir);
        ((glPlane)mTris.get(7)).make((glPoint)mPoints.get(12),(glPoint)mPoints.get(15),(glPoint)mPoints.get(14),dir);
        
        mPoints.add(new  glPoint(mLoc.get(0),mLoc.get(1),mLoc.z()+mExtension.get(2)));        
        mPoints.add(new  glPoint(mLoc.get(0),mLoc.get(1)+mExtension.get(1),mLoc.z()+mExtension.get(2)));
        mPoints.add(new  glPoint(mLoc.get(0)+mExtension.get(0),mLoc.get(1)+mExtension.get(1),mLoc.z()+mExtension.z()));
        mPoints.add(new  glPoint(mLoc.get(0)+mExtension.get(0),mLoc.get(1),mLoc.z()+mExtension.z()));
        
        mTris.add(new glPlane());
        mTris.add(new glPlane());
        ((glPlane)mTris.get(8)).make((glPoint)mPoints.get(16),(glPoint)mPoints.get(17),(glPoint)mPoints.get(18),dir);
        ((glPlane)mTris.get(9)).make((glPoint)mPoints.get(16),(glPoint)mPoints.get(19),(glPoint)mPoints.get(18),dir);
       
        mPoints.add(new  glPoint(mLoc.get(0),mLoc.get(1),mLoc.z()));        
        mPoints.add(new  glPoint(mLoc.get(0),mLoc.get(1)+mExtension.get(1),mLoc.z()));
        mPoints.add(new  glPoint(mLoc.get(0)+mExtension.get(0),mLoc.get(1)+mExtension.get(1),mLoc.z()));
        mPoints.add(new  glPoint(mLoc.get(0)+mExtension.get(0),mLoc.get(1),mLoc.z()));
        
        mTris.add(new glPlane());
        mTris.add(new glPlane());
        ((glPlane)mTris.get(10)).make((glPoint)mPoints.get(20),(glPoint)mPoints.get(21),(glPoint)mPoints.get(22),dir);
        ((glPlane)mTris.get(11)).make((glPoint)mPoints.get(20),(glPoint)mPoints.get(23),(glPoint)mPoints.get(22),dir);
    }
    public void Draw()
    {
        
        for(int m=0;m<mTris.size();m++)
        {
            GL11.glBegin(GL11.GL_LINE_LOOP);
            ((glPlane)mTris.get(m)).p1.drawPoint();
            ((glPlane)mTris.get(m)).p2.drawPoint();
            ((glPlane)mTris.get(m)).p3.drawPoint();
            GL11.glEnd();
        }
        
        GL11.glPointSize(8.0f);
        GL11.glBegin(GL11.GL_POINTS);
        for(int p=0;p<mTris.size();p++)
        {
        for(int m=0;m<((glPlane)mTris.get(p)).mIntersections.size();m++)
        {
            ((glPoint)((glPlane)mTris.get(p)).mIntersections.get(m)).drawPoint();
        }
        }
        GLApp.setMaterial(new float[]{1.0f,0.0f,0.0f,1.0f}, 1.0f);
        ((glPoint)mPoints.get(4)).drawPoint();
        GL11.glEnd();
        GLApp.setMaterial(new float[]{1.0f,1.0f,1.0f,1.0f}, 1.0f);
        //System.out.println(glPlane.publicLine.plane_intersect(((glPlane)mTris.get(0))).pos[0]+","+glPlane.publicLine.plane_intersect(((glPlane)mTris.get(0))).pos[1]+","+glPlane.publicLine.plane_intersect(((glPlane)mTris.get(0))).pos[2]);
        //System.out.println(glPlane.publicLine.X+","+glPlane.publicLine.Y+","+glPlane.publicLine.Z+")("+glPlane.publicLine.A+","+glPlane.publicLine.B+","+glPlane.publicLine.C);
        
    }
    public void Intersect(Tool oT)
    { 
        for(int p=0;p<mTris.size();p++)
            {
        ((glPlane)mTris.get(p)).mIntersections.clear();
        
            }
        for(int t=0;t<oT.mTris.size();t++)
        {
            //
            
            for(int p=0;p<mTris.size();p++)
            {
                
               if(((glPlane)(oT.mTris.get(t))).intersect_RayTriangle(new glLine(((glPlane)mTris.get(p)).p1,((glPlane)mTris.get(p)).p2))==1)
               {
                    ((glPlane)mTris.get(p)).mIntersections.add(new glPoint(glLine.publicPoint));
               }
               
               if(((glPlane)(oT.mTris.get(t))).intersect_RayTriangle(new glLine(((glPlane)mTris.get(p)).p2,((glPlane)mTris.get(p)).p3))==1)
               {     
                   ((glPlane)mTris.get(p)).mIntersections.add(new glPoint(glLine.publicPoint));
               }
               
               
               if(((glPlane)(oT.mTris.get(t))).intersect_RayTriangle(new glLine(((glPlane)mTris.get(p)).p3,((glPlane)mTris.get(p)).p1))==1)
               {
                    ((glPlane)mTris.get(p)).mIntersections.add(new glPoint(glLine.publicPoint));
               }
            }           
        }
        
    }
    public void Inject(glPoint ip)
    {
        int pick=0;
        for(int m=0;m<mPoints.size();m++)
        {
            if(((glPoint)mPoints.get(pick)).minus(ip).distance()>((glPoint)mPoints.get(m)).minus(ip).distance())pick=m;
        }
        System.out.println(pick);
        boolean doom=false;
        for(int p=0;p<mTris.size();p++)
        {
            doom=false;
        for(int m=0;m<((glPlane)mTris.get(p)).mIntersections.size();m++)
        {//must add the intersecting point to the original, i.e. if the new point is part of the intersecting plus old point, then that old point is replaced by the intersecting point
            if(((glPoint)mPoints.get(pick)).equals(((glPlane)mTris.get(p)).p1))
            {
                ((glPlane)mTris.get(p)).make(((glPlane)mTris.get(p)).p2, ((glPlane)mTris.get(p)).p3, ((glPoint)((glPlane)mTris.get(p)).mIntersections.get(m)), ip);
                mTris.add(new glPlane((glPlane)mTris.get(p)));
                doom=true;
            }
            if(((glPoint)mPoints.get(pick)).equals(((glPlane)mTris.get(p)).p2))
            {
                ((glPlane)mTris.get(p)).make(((glPlane)mTris.get(p)).p1, ((glPlane)mTris.get(p)).p3, ((glPoint)((glPlane)mTris.get(p)).mIntersections.get(m)), ip);
                mTris.add(new glPlane((glPlane)mTris.get(p)));
                doom=true;
            }
            if(((glPoint)mPoints.get(pick)).equals(((glPlane)mTris.get(p)).p3))
            {
                ((glPlane)mTris.get(p)).make(((glPlane)mTris.get(p)).p2, ((glPlane)mTris.get(p)).p1, ((glPoint)((glPlane)mTris.get(p)).mIntersections.get(m)), ip);
                mTris.add(new glPlane((glPlane)mTris.get(p)));
                doom=true;
            }
        }
        if(doom)mTris.remove(p);
        }
    }
    public void Destroy(Tool oT)
    {
        boolean doom=false;
        for(int m=0;m<mInters.size();m++)
        {
            doom=false;
            for(int n=0;n<mPoints.size();m++)
            {
               if(((glPoint)mInters.get(m)).minus(((glPoint)mPoints.get(n))).equals(new glPoint(0.0f,0.0f,0.0f))) 
               {
                   mPoints.remove(n);
               }
            }
        }
    }
    
}
    /*
        mInters.clear();
        glPoint temp=new glPoint();
        for(int m=0;m<oT.mPoints.size();m++)
        {
            temp=new glPoint((mEx.minus(mLoc)).minus((glPoint)oT.mPoints.get(m)).minus(mLoc));
            if(temp.get(0)>=0&&temp.get(1)>=0&& ((glPoint)oT.mPoints.get(m)).get(0)>=mLoc.get(0)&& ((glPoint)oT.mPoints.get(m)).get(1)>=mLoc.get(1))
            {
                mInters.add(((glPoint)oT.mPoints.get(m)));
            }
                
        }
        for(int m=0;m<mPoints.size();m++)
        {
            temp=new glPoint((oT.mEx.minus(oT.mLoc)).minus((glPoint)mPoints.get(m)).minus(oT.mLoc));
            if(temp.get(0)>=0&&temp.get(1)>=0&& ((glPoint)mPoints.get(m)).get(0)>=oT.mLoc.get(0)&& ((glPoint)mPoints.get(m)).get(1)>=oT.mLoc.get(1))
            {
                mInters.add(((glPoint)mPoints.get(m)));
            }
                
        }
        if(mInters.size()<=0)return;
        glPoint TtB=new glPoint(mLoc.minus(oT.mEx));
        glPoint TtT=new glPoint(oT.mEx.minus(mEx));
        glPoint BtB=new glPoint(mLoc.minus(oT.mLoc));
        System.out.println(BtB.get(0)+","+BtB.get(1)+")("+TtT.get(0)+","+TtT.get(1));
        
        if(BtB.get(0)==0&&TtT.get(0)<0)
        {
           if(BtB.get(1)==0&&TtT.get(1)==0)
            {
                    mInters.add(new glPoint(oT.mEx.get(0),mEx.get(1)));
                    mInters.add(new glPoint(oT.mEx.get(0),mLoc.get(1)));
                              
            } 
        }
        if(BtB.get(0)<0&&TtT.get(0)==0)
        {
           if(BtB.get(1)==0&&TtT.get(1)==0)
            {
                    mInters.add(new glPoint(oT.mLoc.get(0),mEx.get(1)));
                    mInters.add(new glPoint(oT.mLoc.get(0),mLoc.get(1)));
                              
            } 
        }
        if(BtB.get(0)==0&&TtT.get(0)==0)
        {
           if(BtB.get(1)==0&&TtT.get(1)<0)
            {
                    mInters.add(new glPoint(mLoc.get(0),oT.mEx.get(1)));
                    mInters.add(new glPoint(oT.mEx.get(0),oT.mEx.get(1)));
                              
            } 
        }
        if(BtB.get(0)==0&&TtT.get(0)==0)
        {
           if(BtB.get(1)<0&&TtT.get(1)==0)
            {
                    mInters.add(new glPoint(mLoc.get(0),oT.mLoc.get(1)));
                    mInters.add(new glPoint(oT.mEx.get(0),oT.mLoc.get(1)));
                              
            } 
        }
        if(BtB.get(0)<0&&TtT.get(0)<0)
        {
           if(BtB.get(1)==0&&TtT.get(1)==0)
            {
                    mInters.add(new glPoint(oT.mLoc.get(0),oT.mEx.get(1)));
                    mInters.add(new glPoint(oT.mEx.get(0),oT.mEx.get(1)));
                    mInters.add(new glPoint(oT.mLoc.get(0),oT.mLoc.get(1)));
                    mInters.add(new glPoint(oT.mEx.get(0),oT.mLoc.get(1)));
                              
            } 
        }
        if(BtB.get(0)>0&&TtT.get(0)>0)
        {
           if(BtB.get(1)==0&&TtT.get(1)==0)
            {
                    mInters.add(new glPoint(mLoc.get(0),mEx.get(1)));
                    mInters.add(new glPoint(mEx.get(0),mEx.get(1)));
                    mInters.add(new glPoint(mLoc.get(0),mLoc.get(1)));
                    mInters.add(new glPoint(mEx.get(0),mLoc.get(1)));
                              
            } 
        }
        if(BtB.get(0)==0&&TtT.get(0)==0)
        {
           if(BtB.get(1)<0&&TtT.get(1)<0)
            {
                    mInters.add(new glPoint(oT.mLoc.get(0),oT.mEx.get(1)));
                    mInters.add(new glPoint(oT.mEx.get(0),oT.mEx.get(1)));
                    mInters.add(new glPoint(oT.mLoc.get(0),oT.mLoc.get(1)));
                    mInters.add(new glPoint(oT.mEx.get(0),oT.mLoc.get(1)));
                              
            } 
        }
        if(BtB.get(0)==0&&TtT.get(0)==0)
        {
           if(BtB.get(1)>0&&TtT.get(1)>0)
            {
                    mInters.add(new glPoint(mLoc.get(0),mEx.get(1)));
                    mInters.add(new glPoint(mEx.get(0),mEx.get(1)));
                    mInters.add(new glPoint(mLoc.get(0),mLoc.get(1)));
                    mInters.add(new glPoint(mEx.get(0),mLoc.get(1)));
                              
            } 
        }
        if(BtB.get(0)<0&&TtT.get(0)>0)
        {
            if(BtB.get(1)<0&&TtT.get(1)>0)
            {
                    mInters.add(new glPoint(oT.mLoc.get(0),mEx.get(1)));
                    mInters.add(new glPoint(mEx.get(0),oT.mLoc.get(1)));
                              
            }
            if(BtB.get(1)<0&&TtT.get(1)<0)
            {
                    mInters.add(new glPoint(mEx.get(0),oT.mEx.get(1)));
                    mInters.add(new glPoint(mEx.get(0),oT.mLoc.get(1)));
                              
            }
            if(BtB.get(1)>=0&&TtT.get(1)<0)
            {
                    mInters.add(new glPoint(mEx.get(0),oT.mEx.get(1)));
                    mInters.add(new glPoint(oT.mLoc.get(0),mLoc.get(1)));
                              
            }
        }
        if(BtB.get(0)<0&&TtT.get(0)<0)
        {
            if(BtB.get(1)>0&&TtT.get(1)<0)
            {
                    mInters.add(new glPoint(oT.mLoc.get(0),mLoc.get(1)));
                    mInters.add(new glPoint(oT.mEx.get(0),mLoc.get(1)));
                              
            }
            if(BtB.get(1)<0&&TtT.get(1)>0)
            {
                    mInters.add(new glPoint(oT.mLoc.get(0),mEx.get(1)));
                    mInters.add(new glPoint(oT.mEx.get(0),mEx.get(1)));
                              
            }
        }
        if(BtB.get(0)>0&&TtT.get(0)<0)
        {
            if(BtB.get(1)>0&&TtT.get(1)<0)
            {
                    mInters.add(new glPoint(mLoc.get(0),oT.mEx.get(1)));
                    mInters.add(new glPoint(oT.mEx.get(0),mLoc.get(1)));
                              
            }
            if(BtB.get(1)<0&&TtT.get(1)>0)
            {
                    mInters.add(new glPoint(oT.mEx.get(0),mEx.get(1)));
                    mInters.add(new glPoint(mLoc.get(0),oT.mLoc.get(1)));
                              
            }
            if(BtB.get(1)<0&&TtT.get(1)<0)
            {
                    mInters.add(new glPoint(mLoc.get(0),oT.mEx.get(1)));
                    mInters.add(new glPoint(mLoc.get(0),oT.mLoc.get(1)));
                              
            }
        }*/
    /*
 * glPoint TtB=new glPoint(mLoc.minus(oT.mEx));
        glPoint TtT=new glPoint(oT.mEx.minus(mEx));
        glPoint BtB=new glPoint(mLoc.minus(oT.mLoc));
        if(BtB.get(0)<0)
        {
            if(BtB.get(1)<0)
            {
                mInters.add(new glPoint(oT.mLoc));
                mInters.add(new glPoint(oT.mEx.get(0),oT.mLoc.get(1)));
                if(TtT.get(1)>0)
                {
                    mInters.add(new glPoint(oT.mLoc.get(0),mEx.get(1)));
                }                
            }
            if(BtB.get(1)>0)
            {
                mInters.add(new glPoint(oT.mLoc.get(0),mLoc.get(1)));
                if(TtT.get(1)>0)
                {
                    mInters.add(new glPoint(oT.mLoc.get(0),mEx.get(1)));
                }
            }
            if(TtT.get(1)<=0)
            {
                mInters.add(new glPoint(mEx.get(0),oT.mEx.get(1)));
                mInters.add(new glPoint(oT.mLoc.get(0),oT.mEx.get(1)));
            }
            
            
        }
        if(BtB.get(0)>0)
        {
            if(BtB.get(1)<0)
            {
                
                if(TtT.get(1)<=0)
                {
                    mInters.add(new glPoint(mLoc.get(0),oT.mLoc.get(1)));
                    mInters.add(new glPoint(oT.mEx.get(0),oT.mEx.get(1)));
                    mInters.add(new glPoint(mLoc.get(0),oT.mEx.get(1)));
                    mInters.add(new glPoint(oT.mEx.get(0),oT.mLoc.get(1)));
                }
                if(TtT.get(1)>0)
                {
                   mInters.add(new glPoint(mLoc.get(0),oT.mLoc.get(1)));
                   mInters.add(new glPoint(oT.mEx.get(0),mEx.get(1)));
                   mInters.add(new glPoint(mLoc.get(0),mEx.get(1)));
                }
            }
            if(BtB.get(1)>0)
            {
                mInters.add(new glPoint(oT.mEx.get(0),oT.mEx.get(1)));
                if(TtT.get(1)<=0)
                {
                    mInters.add(new glPoint(mLoc.get(0),oT.mEx.get(1)));
                    mInters.add(new glPoint(oT.mEx.get(0),mLoc.get(1)));
                }
            }
            
            
            
        }
        
        if(mLoc.get(0)<oT.mLoc.get(0)&&mEx.get(0)>oT.mLoc.get(0)&&mEx.get(0)>oT.mEx.get(0))//x is contained within first tool
        {
            if(mLoc.get(1)<oT.mLoc.get(1)&&mEx.get(1)>oT.mLoc.get(1)&&mEx.get(1)>oT.mEx.get(1))
            {
                //oT completely within mT
                mInters.add(new glPoint(oT.mLoc));
                mInters.add(new glPoint(oT.mEx.get(0),oT.mLoc.get(1)));
                mInters.add(new glPoint(oT.mLoc.get(0),oT.mEx.get(1)));               
                mInters.add(new glPoint(oT.mEx));
            }
            if(mLoc.get(1)<oT.mLoc.get(1)&&mEx.get(1)>oT.mLoc.get(1)&&mEx.get(1)<oT.mEx.get(1))
            {
                //oT mLoc.x .y within mLoc.x and .y but not mEx.y
                mInters.add(new glPoint(oT.mLoc));
                mInters.add(new glPoint(oT.mLoc.get(0),mEx.get(1)));
                mInters.add(new glPoint(oT.mEx.get(0),mEx.get(1)));               
                mInters.add(new glPoint(mEx));
            }
            if(mLoc.get(1)>oT.mLoc.get(1)&&mEx.get(1)<oT.mLoc.get(1))
            {
                //oT not contained
            }
            if(mLoc.get(1)>oT.mLoc.get(1)&&mEx.get(1)>oT.mLoc.get(1)&&mEx.get(1)<oT.mEx.get(1))
            {
                mInters.add(new glPoint(oT.mLoc));
                mInters.add(new glPoint(oT.mLoc.get(0),mEx.get(1)));
                mInters.add(new glPoint(oT.mEx.get(0),mEx.get(1)));               
                mInters.add(new glPoint(mEx));
            }
        }*/
    
/*
 * Vector secondRun=new Vector();
        for(int i =0;i<mConnections.size();i++)
        {    
            for(int j=0;j<oT.mTris.size();j++)
            {               
                mInters.add(new glLine((glPoint)mPoints.get(((glTwoInt)mConnections.get(i)).mA),(glPoint)mPoints.get(((glTwoInt)mConnections.get(i)).mB)).plane_intersect(((glPlane)oT.mTris.get(j))));                
               
            }
        }
        for(int i =0;i<oT.mConnections.size();i++)
        {    
            for(int j=0;j<mTris.size();j++)
            {      
                
                secondRun.add(new glLine((glPoint)oT.mPoints.get(((glTwoInt)oT.mConnections.get(i)).mA),(glPoint)oT.mPoints.get(((glTwoInt)oT.mConnections.get(i)).mB)).plane_intersect(((glPlane)mTris.get(j))));                
               
            }
        }
        
        boolean doom=false;
        for(int m=0;m<mInters.size();m++)
        { 
            doom = false;
            for(int n=0;n<secondRun.size();n++)
            {
                if(((glPoint)mInters.get(m)).get(0)==((glPoint)secondRun.get(n)).get(0)&&((glPoint)mInters.get(m)).get(1)==((glPoint)secondRun.get(n)).get(1)&&((glPoint)mInters.get(m)).get(2)==((glPoint)secondRun.get(n)).get(2))
                {
                    doom=true;
                }
            }
            if(doom==false)
            {
                mInters.removeElementAt(m);
            }
        }
        
        for(int m=0;m<mInters.size();m++)
        {
           if(((glPoint)mInters.get(m)).get(0)==0&&((glPoint)mInters.get(m)).get(1)==0&&((glPoint)mInters.get(m)).get(2)==0)
                {
                    mInters.removeElementAt(m);
                } 
        }
        glLine V;
        for(int m=0;m<mInters.size();m++)
        {
            doom =false;
            for(int i =0;i<mConnections.size();i++)
            {
                V=new glLine((glPoint)mPoints.get(((glTwoInt)mConnections.get(i)).mA),(glPoint)mPoints.get(((glTwoInt)mConnections.get(i)).mB));
                if(((glPoint)mInters.get(m)).get(0)-V.A/(V.X-V.A)==((glPoint)mInters.get(m)).get(1)-V.B/(V.Y-V.B)&&((glPoint)mInters.get(m)).get(1)-V.B/(V.Y-V.B)==((glPoint)mInters.get(m)).get(2)-V.C/(V.Z-V.C)&&((glPoint)mInters.get(m)).get(0)-V.A/(V.X-V.A)==((glPoint)mInters.get(m)).get(2)-V.C/(V.Z-V.C))
                {
                    for(int j =0;j<oT.mConnections.size();j++)
                    {
                        V=new glLine((glPoint)mPoints.get(((glTwoInt)oT.mConnections.get(j)).mA),(glPoint)mPoints.get(((glTwoInt)oT.mConnections.get(j)).mB));
                        if(((glPoint)mInters.get(m)).get(0)-V.A/(V.X-V.A)==((glPoint)mInters.get(m)).get(1)-V.B/(V.Y-V.B)&&((glPoint)mInters.get(m)).get(1)-V.B/(V.Y-V.B)==((glPoint)mInters.get(m)).get(2)-V.C/(V.Z-V.C)&&((glPoint)mInters.get(m)).get(0)-V.A/(V.X-V.A)==((glPoint)mInters.get(m)).get(2)-V.C/(V.Z-V.C))
                        {
                            doom=true;
                        }
                    }
                }
                
            }
            if(doom==false)
            {
                //mInters.removeElementAt(m);
            }
        }
        System.out.println("->>>>"+mInters.size());
        
    }
 */