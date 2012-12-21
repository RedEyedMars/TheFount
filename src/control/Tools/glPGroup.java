/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Tools;

import java.awt.Font;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.*;
import glapp.*;
import java.util.*;
/**
 *
 * @author Geoff
 */
public class glPGroup {
    int mT=0;
    int mSz=0;
    int mY=0;
    Vector mPs=new Vector();
    public void Start(int inI, int inY)
    {
        mY=inY;
        mT=inI;
    }
    public void Accept(glPoint inP)
    {
        if(Math.abs((int)inP.tget(2))==mT)
        {
            mPs.add(inP);
        }
    }
    public Vector Publish()
    {
        Vector outV=new Vector();
        outV.add(new glPGroup());
        int[][] gNum= new int[17][17];
        for(int m=0;m<17;m++)
            for(int n=0;n<17;n++)
                gNum[m][n]= -1;
        for(int m=0;m<mPs.size();m++)
        {
            double testX=((glPoint)(mPs.get(m))).get(0);
            double testY=((glPoint)(mPs.get(m))).get(2);
            int[] doom=new int[4];
            doom[0]= -1;
            doom[1]= -1;
            doom[2]= -1;
            doom[3]= -1;
            for(int n=0;n<mPs.size();n++)
            {
                if(m==n)continue;
                if(testX+1==((glPoint)(mPs.get(n))).get(0)){
                    doom[0]=n;
                    continue;
                }
                if(testX-1==((glPoint)(mPs.get(n))).get(0)){
                    doom[1]=n;
                    continue;
                }
                if(testY+1==((glPoint)(mPs.get(n))).get(2)){
                    doom[2]=n;
                    continue;
                }
                if(testY-1==((glPoint)(mPs.get(n))).get(2)){
                    doom[3]=n;
                    continue;
                }
            }
            if(doom[0]!= -1 && doom[1]!= -1 && doom[2]!= -1 && doom[3]!= -1)
            {
                //System.out.println("All around me");
            }
            else
            {
                int done = -1;
            if(doom[0] != -1)
            {
                if(gNum[(int)((glPoint)(mPs.get(doom[0]))).get(0)][(int)((glPoint)(mPs.get(doom[0]))).get(2)]!=-1){
                    ((glPGroup)(outV.get(gNum[(int)((glPoint)(mPs.get(doom[0]))).get(0)][(int)((glPoint)(mPs.get(doom[0]))).get(2)]))).adm((glPoint)(mPs.get(m)));
                    gNum[(int)((glPoint)(mPs.get(m))).get(0)][(int)((glPoint)(mPs.get(m))).get(2)]=gNum[(int)((glPoint)(mPs.get(doom[0]))).get(0)][(int)((glPoint)(mPs.get(doom[0]))).get(2)];
                }
                else
                {
                    gNum[(int)((glPoint)(mPs.get(m))).get(0)][(int)((glPoint)(mPs.get(m))).get(2)]=outV.size();
                    outV.add(new glPGroup());
                    ((glPGroup)(outV.get(outV.size()-1))).adm((glPoint)(mPs.get(m)));
                }
                done = 0;
            }
            if(doom[1] != -1)
            {
                if(done== -1)
                {
                    if(gNum[(int)((glPoint)(mPs.get(doom[1]))).get(0)][(int)((glPoint)(mPs.get(doom[1]))).get(2)]!=-1)
                    {
                        ((glPGroup)(outV.get(gNum[(int)((glPoint)(mPs.get(doom[1]))).get(0)][(int)((glPoint)(mPs.get(doom[1]))).get(2)]))).adm((glPoint)(mPs.get(m)));
                        gNum[(int)((glPoint)(mPs.get(m))).get(0)][(int)((glPoint)(mPs.get(m))).get(2)]=gNum[(int)((glPoint)(mPs.get(doom[1]))).get(0)][(int)((glPoint)(mPs.get(doom[1]))).get(2)];
                    }
                    else
                    {
                        gNum[(int)((glPoint)(mPs.get(m))).get(0)][(int)((glPoint)(mPs.get(m))).get(2)]=outV.size();
                        outV.add(new glPGroup());
                        ((glPGroup)(outV.get(outV.size()-1))).adm((glPoint)(mPs.get(m)));
                    }
                    done = 1;
                }
                else
                {
                    if(gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)]!=-1){
                    //System.out.println(gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)]); 
                    ((glPGroup)(outV.get(gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)]))).adm((glPoint)(mPs.get(m)));
                    gNum[(int)((glPoint)(mPs.get(m))).get(0)][(int)((glPoint)(mPs.get(m))).get(2)]=gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)];
                    
                    if(gNum[(int)((glPoint)(mPs.get(doom[1]))).get(0)][(int)((glPoint)(mPs.get(doom[1]))).get(2)]!=gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)])
                    {                        
                        for(int a=0;a<17;a++)
                            for(int b=0;b<17;b++)
                                if(gNum[a][b]==doom[1])gNum[a][b]=gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)];
                    }
                     }
                        else
                     {
                    gNum[(int)((glPoint)(mPs.get(m))).get(0)][(int)((glPoint)(mPs.get(m))).get(2)]=outV.size();
                    outV.add(new glPGroup());
                    ((glPGroup)(outV.get(outV.size()-1))).adm((glPoint)(mPs.get(m)));
                     }
                    done = 1;
                    
                }
            }
            if(doom[2] != -1)
            {
                if(done==-1)
                {
                   if(gNum[(int)((glPoint)(mPs.get(doom[2]))).get(0)][(int)((glPoint)(mPs.get(doom[2]))).get(2)]!=-1){
                    ((glPGroup)(outV.get(gNum[(int)((glPoint)(mPs.get(doom[2]))).get(0)][(int)((glPoint)(mPs.get(doom[2]))).get(2)]))).adm((glPoint)(mPs.get(m)));
                    gNum[(int)((glPoint)(mPs.get(m))).get(0)][(int)((glPoint)(mPs.get(m))).get(2)]=gNum[(int)((glPoint)(mPs.get(doom[2]))).get(0)][(int)((glPoint)(mPs.get(doom[2]))).get(2)];
                }
                else
                {
                    gNum[(int)((glPoint)(mPs.get(m))).get(0)][(int)((glPoint)(mPs.get(m))).get(2)]=outV.size();
                    outV.add(new glPGroup());
                    ((glPGroup)(outV.get(outV.size()-1))).adm((glPoint)(mPs.get(m)));
                }
                done=2;
                }
                else
                {
                    if(gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)]!=-1){
                        //System.out.println(outV.size());
                        //System.out.println(gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)]);
                        gNum[(int)((glPoint)(mPs.get(m))).get(0)][(int)((glPoint)(mPs.get(m))).get(2)]=gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)];
                    ((glPGroup)(outV.get(gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)]))).adm((glPoint)(mPs.get(m)));
                    
                    if(gNum[(int)((glPoint)(mPs.get(doom[2]))).get(0)][(int)((glPoint)(mPs.get(doom[2]))).get(2)]!=gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)])
                    {                        
                        for(int a=0;a<17;a++)
                            for(int b=0;b<17;b++)
                                if(gNum[a][b]==doom[2])gNum[a][b]=gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)];
                    }
                     }
                        else
                     {
                    gNum[(int)((glPoint)(mPs.get(m))).get(0)][(int)((glPoint)(mPs.get(m))).get(2)]=outV.size();
                    outV.add(new glPGroup());
                    ((glPGroup)(outV.get(outV.size()-1))).adm((glPoint)(mPs.get(m)));
                     }
                    done=2;
                }
            }
            if(doom[3] != -1)
            {
                if(done==-1)
                {
                if(gNum[(int)((glPoint)(mPs.get(doom[3]))).get(0)][(int)((glPoint)(mPs.get(doom[3]))).get(2)]!=-1){
                    ((glPGroup)(outV.get(gNum[(int)((glPoint)(mPs.get(doom[3]))).get(0)][(int)((glPoint)(mPs.get(doom[3]))).get(2)]))).adm((glPoint)(mPs.get(m)));
                    gNum[(int)((glPoint)(mPs.get(m))).get(0)][(int)((glPoint)(mPs.get(m))).get(2)]=gNum[(int)((glPoint)(mPs.get(doom[3]))).get(0)][(int)((glPoint)(mPs.get(doom[3]))).get(2)];
                }
                else
                {
                    gNum[(int)((glPoint)(mPs.get(m))).get(0)][(int)((glPoint)(mPs.get(m))).get(2)]=outV.size();
                    outV.add(new glPGroup());
                    ((glPGroup)(outV.get(outV.size()-1))).adm((glPoint)(mPs.get(m)));
                }
                done=3;
                }
                else
                {
                    if(gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)]!=-1){
                        //System.out.println(outV.size());
                    //System.out.println(gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)]); 
                        ((glPGroup)(outV.get(gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)]))).adm((glPoint)(mPs.get(m)));
                        gNum[(int)((glPoint)(mPs.get(m))).get(0)][(int)((glPoint)(mPs.get(m))).get(2)]=gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)];
                    
                    if(gNum[(int)((glPoint)(mPs.get(doom[3]))).get(0)][(int)((glPoint)(mPs.get(doom[3]))).get(2)]!=gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)])
                    {                        
                        for(int a=0;a<17;a++)
                            for(int b=0;b<17;b++)
                                if(gNum[a][b]==doom[3])gNum[a][b]=gNum[(int)((glPoint)(mPs.get(doom[done]))).get(0)][(int)((glPoint)(mPs.get(doom[done]))).get(2)];
                    }
                     }
                        else
                     {
                    gNum[(int)((glPoint)(mPs.get(m))).get(0)][(int)((glPoint)(mPs.get(m))).get(2)]=outV.size();
                    outV.add(new glPGroup());
                    ((glPGroup)(outV.get(outV.size()-1))).adm((glPoint)(mPs.get(m)));
                     }
                    done=3;
                }
            }
            }
        }
        /*Vector returnV=new Vector();
        for(int m=0;m<17;m++)
        {
            for(int n=0;n<17;n++)
            {
                while(gNum[m][n]>=returnV.size())returnV.add(new glPGroup());
                if(gNum[m][n]>-1)((glPGroup)(returnV.get(gNum[m][n]))).adm(new glPoint(m,mY,n,mT));
            }
        }*/
        return outV;
    }
    public void adm(glPoint inP)
    {
        mPs.add(inP);
        mSz++;
    }
}
