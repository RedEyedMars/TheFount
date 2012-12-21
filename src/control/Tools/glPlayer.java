/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Tools;

/**
 *
 * @author Geoff
 */


import java.awt.Font;
import java.io.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.*;
import glapp.*;
import java.util.*;
public class glPlayer {
    glPoint[][] mAppear=new glPoint[32][24];
    glPoint[][] colourchoice=new glPoint[24][16];
    float mX=-3;
    float mY=-3.5f;
    glPoint colourhueset=new glPoint(0,1,1,1,1,1);
    glPoint colourSET=new glPoint();
    public void setup(){
        for(int m=0;m<32;m++)
            for(int n=0;n<24;n++)
                mAppear[m][n]=new glPoint();
        setupColor(0,0);
    }
    public void setupColor(float s,float v){
        for(int m=0;m<16;m++)
            for(int n=0;n<24;n++){
                colourchoice[n][m]=new glPoint((n)*360/24,(m+s)/16f,(m+v)/16f,1,1,1);
                if(colourchoice[n][m].pos[1]>1)colourchoice[n][m].pos[1]=1;
                if(colourchoice[n][m].pos[2]>1)colourchoice[n][m].pos[2]=1;
                if(colourchoice[n][m].pos[1]<0)colourchoice[n][m].pos[1]=0;
                if(colourchoice[n][m].pos[2]<0)colourchoice[n][m].pos[2]=0;
            }
    }
    int ponx=-1;
    int pony=-1;
    boolean OnAddColour=true;
    public void saveChar(String filename)
    {
        try{
         // Create file 
        FileWriter fstream = new FileWriter(filename);
        BufferedWriter out = new BufferedWriter(fstream);
        //out.write("Tex1");
        //out.newLine();
        for(int m=0;m<32;m++)
            for(int n=0;n<24;n++)
            {
//                System.out.println(mAppear[m][n].getColourChars());
                out.write(mAppear[m][n].getColourChars());
            }
//        System.out.println((char)50);
        //Close the output stream
        
        out.close();
        }catch (Exception e){//Catch exception if any
        System.err.println("Error: " + e.getMessage());
        }
    }
    public void loadChar(String filename)
    {
        try{
         // Create file 
        FileReader fstream = new FileReader(filename);
        BufferedReader out = new BufferedReader(fstream);
        //if(out.readLine()=="Tex1")
        //{
            //System.out.println("load");
            //out.skip(1);
        char[] buff=new char[3];
        for(int m=0;m<32;m++)
            for(int n=0;n<24;n++)
            {
                out.read(buff);
//                System.out.println((float)buff[0]);
                mAppear[m][n]=new glPoint(buff);                
            }
        //}
        //Close the output stream
        out.close();
        }catch (Exception e){//Catch exception if any
        System.err.println("Error: " + e.getMessage());
        }
    }
    public void deleteCOLOUR(glPoint p)
    {
        for(int x=0;x<24;x++)
            {
                for(int y=0;y<32;y++)
                {
                    if(mAppear[y][x].equals(p))mAppear[y][x]=new glPoint();
                }
            }
    }
    public void moveCOLOUR(int a, int b)
    {
        int startx=0;
        int starty=0;
        int endx=24;
        int endy=32;
        if(a<0){startx=23;endx=-1;}
        if(b<0){starty=31;endy=-1;}
        int incx=a;
        int incy=b;
        if(incx==0)incx=1;
        if(incy==0)incy=1;
        for(int x=startx;x!=endx;x+=incx)
            {
                for(int y=starty;y!=endy;y+=incy)
                {
                    System.out.println(x+""+y);
                    if(((a>0&&a+x<24)||(a<0&&a+x>=0)||(a==0))&&((b>0&&b+y<32)||(b<0&&b+y>=0)||(b==0)))mAppear[y][x]=mAppear[y+b][x+a];
                }
            }
        if(a<0){startx=0;endx=-a;}
        if(a>0){startx=24-a;endx=24;}
        if(a==0){startx=0;endx=24;}
        if(b<0){starty=0;endy=-b;}
        if(b>0){starty=32-b;endy=32;}
        if(b==0){starty=0;endy=32;}
        for(int x=startx;x!=endx;x++)
            {
                for(int y=starty;y!=endy;y++)
                {
                    mAppear[y][x]=new glPoint();
                }
            }
    }
    public void draw()
    {
        GL11.glLineWidth(2.0f);
        GL11.glBegin(GL11.GL_QUADS);
        
            for(int x=0;x<24;x++)
            {
                for(int y=0;y<32;y++)
                {
//                    GLApp.setMaterial( new glPoint(1,0,0,1).colour(), 1.0f);
                    GLApp.setMaterial( mAppear[y][x].colour(), 1.0f);
                    GL11.glVertex3f(mX+(float)x*0.225f, mY+(float)y*0.225f, 0);
                    GL11.glVertex3f(mX+(float)x*0.225f+0.225f, mY+(float)y*0.225f, 0); 
                    GL11.glVertex3f(mX+(float)x*0.225f+0.225f, mY+(float)y*0.225f+0.225f, 0); 
                    GL11.glVertex3f(mX+(float)x*0.225f, mY+(float)y*0.225f+0.225f, 0);
//                    System.out.println((int)(y/0.225f)+"&&"+(int)(x/0.225f));
                    if(pony==y&&ponx==x&&OnAddColour)
                    {
                        GL11.glEnd();
                        GL11.glBegin(GL11.GL_LINE_LOOP);
                        GLApp.setMaterial( new glPoint(1,1,1,1).colour(), 1.0f);
                        GL11.glVertex3f(mX+(float)x*0.225f, mY+(float)y*0.225f, 0);
                        GL11.glVertex3f(mX+(float)x*0.225f+0.225f, mY+(float)y*0.225f, 0); 
                        GL11.glVertex3f(mX+(float)x*0.225f+0.225f, mY+(float)y*0.225f+0.225f, 0); 
                        GL11.glVertex3f(mX+(float)x*0.225f, mY+(float)y*0.225f+0.225f, 0);
                        GL11.glEnd();
                        GL11.glBegin(GL11.GL_QUADS);
//                        System.out.println(colourhueset.hue()[1]);
                        GLApp.setMaterial( colourhueset.hue(), 1.0f);
                    GL11.glVertex3f(mX+(float)x*0.225f, mY+(float)y*0.225f, 0);
                        GL11.glVertex3f(mX+(float)x*0.225f+0.225f, mY+(float)y*0.225f, 0); 
                        GL11.glVertex3f(mX+(float)x*0.225f+0.225f, mY+(float)y*0.225f+0.225f, 0); 
                        GL11.glVertex3f(mX+(float)x*0.225f, mY+(float)y*0.225f+0.225f, 0);
                    }
                }
            }
//            System.out.println((new glPoint(hue,1f,1f,1.0f).hue())[3]);
            
                    
        
        GL11.glEnd();
        if(!OnAddColour)
                    {
                        GL11.glBegin(GL11.GL_QUADS);
                        for(int x=0;x<16;x++)
        {
            for(int y=0;y<24;y++){
            GLApp.setMaterial( colourchoice[y][x].hue(), 1.0f);
                    GL11.glVertex3f(3+(float)x*0.225f+(ponx-12)*0.225f, -3+(float)y*0.225f+(pony-12)*0.225f, 0);
                        GL11.glVertex3f(3+(float)x*0.225f+0.225f+(ponx-12)*0.225f, -3+(float)y*0.225f+(pony-12)*0.225f, 0); 
                        GL11.glVertex3f(3+(float)x*0.225f+0.225f+(ponx-12)*0.225f, -3+(float)y*0.225f+0.225f+(pony-12)*0.225f, 0); 
                        GL11.glVertex3f(3+(float)x*0.225f+(ponx-12)*0.225f, -3+(float)y*0.225f+0.225f+(pony-12)*0.225f, 0); 
                        
            }
        }
        GL11.glEnd();
                        GL11.glBegin(GL11.GL_LINE_LOOP);
                        GLApp.setMaterial( new glPoint(1,1,1,1).colour(), 1.0f);
                        GL11.glVertex3f(3+(float)3*0.225f, -3+(float)11*0.225f, 0);
                        GL11.glVertex3f(3+(float)3*0.225f+0.225f, -3+(float)11*0.225f, 0); 
                        GL11.glVertex3f(3+(float)3*0.225f+0.225f, -3+(float)11*0.225f+0.225f, 0); 
                        GL11.glVertex3f(3+(float)3*0.225f, -3+(float)11*0.225f+0.225f, 0); 
                        GL11.glEnd();
                    }
    
    GL11.glBegin(GL11.GL_LINE_LOOP);
    GLApp.setMaterial( new glPoint(1,1,1,1).colour(), 1.0f);  
     GL11.glVertex3f(mX+(float)0*0.225f, mY+(float)0*0.225f, 0);
     GL11.glVertex3f(mX+(float)24*0.225f, mY+(float)0*0.225f, 0); 
     GL11.glVertex3f(mX+(float)24*0.225f, mY+(float)32*0.225f, 0); 
     GL11.glVertex3f(mX+(float)0*0.225f, mY+(float)32*0.225f, 0);
    GL11.glEnd();
    }
}
