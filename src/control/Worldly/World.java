
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Worldly;

import control.People.NPC;
import control.Items.Item;
import control.Misc.Interval;
import control.Misc.Condition;
import control.Worldly.Environment;
import control.Ables.Recognizable;
import control.Ables.Drawable;
import control.GLApp_LandSmash;
import java.awt.Font;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.*;
import glapp.*;
import java.awt.Color;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;
/**
 *
 * @author Geoff
 */
public class World{
    ArrayList<Environment> mEnvironments=new ArrayList<Environment>();
    Environment mZoomed=null;
    
    ArrayList mContents = new ArrayList();
    double [][] mLayer;//1: Y coord 2: of the Many PGroups 3: of the points in 
    Range mWata = new Range();
    Range mConta = new Range();
    Range mMunta = new Range();
    static float Sealevel=0;
    static float Moutainline=2.50f;
    
    ArrayList<ArrayList<Interval>> mTemperatures=new ArrayList<ArrayList<Interval>>(11);
    
    ArrayList<Region> mRegions=new ArrayList<Region>();
    Random rgen = new Random();
    int[] TextureHandler;
    int MaxSpread=128;
    
    public World(){}
    public void zoomIn(Environment Z){
        mZoomed=Z;
    }
    public Environment getZoomed(){
        return mZoomed;
    }
    public void Create(){
        TextureHandler=new int[30];
        TextureHandler[0]=GLApp.makeTexture("images/clear.png");
        float level = 1f;      
        
        mLayer=
                cornersSet(
                    setupPointArray(
                    MaxSpread+1,MaxSpread+1,1f)
                ,new double[]{randf(0.018202394f),randf(0.018202394f),randf(0.018202394f),randf(0.018202394f)}
                );
        CreateHeightMap(mLayer,level,(float)(Math.random()*2+1.0f));
        mWata=ArrangeArray(new Condition(){
            public Boolean isTrue(Object o) {
                Double c=(Double)o;
                return c<=Sealevel;
            }
        }, Color.BLUE);
        mConta=ArrangeArray(new Condition() {
            public Boolean isTrue(Object o) {
                Double c=(Double)o;
                return c>Sealevel&&c<=Moutainline;
            }
        }, Color.GREEN);
        mMunta=ArrangeArray(new Condition() {
            public Boolean isTrue(Object o) {
                Double c=(Double)o;
                return c>Moutainline;
            }
        },Color.GRAY);
        System.out.println("Ocean Count: "+mWata.size());
        System.out.println("Greens Count: "+mConta.size());
        System.out.println("Mountain Count: "+mMunta.size());
        
        for(ArrayList<Square> ar:mWata){
            for(Square s:ar){
                mEnvironments.add(new Environment(s));
            }            
        }
        for(ArrayList<Square> ar:mConta){
            for(Square s:ar){
                mEnvironments.add(new Environment(s));
            }
        }
        for(ArrayList<Square> ar:mMunta){
            for(Square s:ar){
                mEnvironments.add(new Environment(s));
            }
        }
    }
    public double[][] setupPointArray(int X, int Y, float r){
        double[][] out=new double[X][Y];
        for (double x = 0;x<X;x++)
        {
            for(double y = 0; y<Y; y++)
            {
                out[(int)x][(int)y]=-128f;
            }
        }
        return out;
    }
    public double[][] cornersSet(double[][] ar,double[] in){
       ar[0][0]= in[0];
       ar[ar.length-1][0]=in[1];
       ar[ar.length-1][ar[ar.length-1].length-1]=in[2];
       ar[0][ar[0].length-1]=in[3]; 
       return ar;
    }
    public void CreateHeightMap(double[][] nLinex, float level, float h){
        Sealevel=(float)(nLinex[0][0]+nLinex[MaxSpread][0]+nLinex[MaxSpread][MaxSpread]+nLinex[0][MaxSpread])/4;
        
        float factor=1.0f;
        for(int E=MaxSpread+1;E!=E/2;E/=2)
        {
            factor/=h;
            //          System.out.println(factor);
            for(int F=0;E*2+F<MaxSpread+1;F+=E*2)
            {
                for(int G=0;E*2+G<MaxSpread+1;G+=E*2)
                {
                    nLinex[E+F][E+G]=((nLinex[F][2*E+G]+nLinex[F][G]+nLinex[2*E+F][2*E+G]+nLinex[2*E+F][G])/4+randf(factor));
                    if(nLinex[E+F][G]==-128)
                        nLinex[E+F][G]=((nLinex[2*E+F][G]+nLinex[F][G]+nLinex[E+F][E+G])/4+randf(factor));
                    else {
                        nLinex[E+F][G]=(nLinex[E+F][G]+nLinex[F+E][E+G]/4);
                    }
                    if(nLinex[F][E+G]==-128)nLinex[F][E+G]=((nLinex[F][2*E+G]+nLinex[F][G])/2+randf(factor));
                    else {
                        nLinex[F][E+G]=(nLinex[F][E+G]+nLinex[F+E][E+G]/4);
                    }
                    if(nLinex[2*E+F][E+G]==-128)
                        nLinex[2*E+F][E+G]=((nLinex[2*E+F][2*E+G]+nLinex[2*E+F][G]+nLinex[E+F][E+G])/4+randf(factor));
                    else {
                        nLinex[2*E+F][E+G]=(nLinex[2*E+F][E+G]+nLinex[F+E][E+G]/4);
                    }
                    if(nLinex[E+F][2*E+G]==-128)
                        nLinex[E+F][2*E+G]=((nLinex[2*E+F][2*E+G]+nLinex[F][2*E+G]+nLinex[E+F][E+G])/4+randf(factor));
                    else {
                        nLinex[E+F][2*E+G]=(nLinex[E+F][2*E+G]+nLinex[F+E][E+G]/4);
                    }
                    //System.out.println(nLinex[E+F][E+G].get(1)); 
                }
            }
        }
        /*for(int m=1;m<MaxSpread+1;m++){
            nLinex[0][m].y=(byte)(nLinex[0][m].y+nLinex[1][m].y/4);
            nLinex[MaxSpread][m].y=nLinex[MaxSpread][m].y+nLinex[MaxSpread-1][m].y/4);
            nLinex[m][0].y=nLinex[m][0].y+nLinex[m][1].y/4);
            nLinex[m][MaxSpread].y=nLinex[m][MaxSpread].y+nLinex[m][MaxSpread-1].y/4);
        }*/     
    }
    public Range ArrangeArray(Condition c, Color C){        
        ArrayList<SeaPoint> tw= new ArrayList<SeaPoint>();
        for(int m=0;m<MaxSpread+1;m++){
            for(int n=0;n<MaxSpread+1;n++){
                if(c.equals(mLayer[m][n])){
                    tw.add(new SeaPoint((byte)(m-1),(byte)(Sealevel-1),(byte)(n-1),C));
                   
                    tw.get(tw.size()-1).y=(byte)((mLayer[m][n]+1)*8.0f);
                    if(tw.get(tw.size()-1).y<Sealevel)
                        tw.get(tw.size()-1).y=(byte)Sealevel;
                }
            }
        }
        return groupArray(tw);
    }
    boolean cREMOVE=false;
    
    public Range groupArray(ArrayList<SeaPoint> arrayIn){
        ArrayList<SeaPoint> temp=new ArrayList<SeaPoint>();
        ArrayList<SeaPoint> t2=new ArrayList<SeaPoint>();
        Range arrayOut=new Range();
        while(arrayIn.size()>0){
            ArrayList<SeaPoint> Sea=new ArrayList<SeaPoint>();
            temp.clear();
            temp.addAll(arrayIn);
            Sea.add(temp.get(0));
            cREMOVE=false;
            temp.retainAll(Sea);
            
            while(Sea.addAll(temp)){
                //System.out.println("Water Size:"+Sea.size());
                cREMOVE=true;
                arrayIn.removeAll(temp);
                t2.addAll(temp);
                temp.clear();
                temp.addAll(arrayIn);
                cREMOVE=false;
                temp.retainAll(t2);
                t2.clear();
            }
            arrayOut.add(Sea);
        }
        return arrayOut;
    }
    Square[][] tzones=new Square[12][MaxSpread+1];
    public void CreateTemperatureZones()
    {
        float factor=1.0f;
        for(int j=0;j<12;j++)
        {
            for(int m=0;m<=MaxSpread;m++)
            {
                tzones[j][m]=new Square((byte)0,(byte)0,(byte)0,Color.white);
            }
            tzones[j][0].xz[2]=(byte)( j*(MaxSpread*0.05f)/12);
            tzones[j][0].xz[0]=(byte)(0);
            tzones[j][MaxSpread].xz[2]=(byte)(j*(MaxSpread*0.05f)/12);
            tzones[j][MaxSpread].xz[0]=(byte)(MaxSpread*0.05f);
            factor=1.0f;
            for(int E=MaxSpread+1;E!=E/2;E/=2)
            {
                factor/=1.89f;
                for(int F=0;E*2+F<MaxSpread+1;F+=E*2)
                {
                    tzones[j][E+F].xz[0]=(byte)((E+F)*0.05f);
                    tzones[j][E+F].xz[2]=(byte)((tzones[j][F].xz[2]+tzones[j][F+E*2].xz[2])/2+randf(factor)/2.5f);
                }
            }
            ArrayList<Interval> temp=new ArrayList<Interval>(MaxSpread+1);
            for(int m=1;m<MaxSpread;m++)
            {
                tzones[j][m].xz[2]=(byte)(tzones[j][m].xz[2]+j*(MaxSpread*0.05f)/12);
                if(j>0){
                    temp.add(new Interval((int)tzones[j-1][m].xz[2],(int)tzones[j][m].xz[2]));
                }
            }
            if(j>0){
                mTemperatures.add(temp);
            }
        }        
        //degrees = (j-6)^2
    }
      
     /*
      for(int t=0;t<10/*number of types;t++)
      {
        for(int m=0;m<mLayer.size();m++)
        {
            glPGroup tempPG=new glPGroup(); 
            tempPG.Start(t,m);
            for(int n=0;n<((ArrayList)mLayer.get(m)).size();n++)
            {                
                for(int o=0;o<((ArrayList)((ArrayList)mLayer.get(m)).get(n)).size();o++)
                {                   
                  tempPG.Accept((Square)((ArrayList)((ArrayList)mLayer.get(m)).get(n)).get(o));
                }
            }
            
            ((ArrayList)mLayer.get(m)).add(tempPG.Publish());
            
         }                    
      }*/
    public void update()
    {                  
    }
      
    /*
    public void DrawHeight(){
        //int counter =0;
        Square tempP;
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, TextureHandler[0]);                    
                
                    //System.out.println(mLayer.size());
                    for(int n=0;n<MaxSpread;n++)
                    {
                        //System.out.println(n);
                        for(int o=0;o<MaxSpread;o++)
                        {     
                            GL11.glBegin(GL11.GL_LINE_LOOP); 
                            //System.out.println(((Square[][])mLayer.get(m))[n][o].y);
                            GLApp.setMaterial( new float[] {0.5f, 0.5f,Math.abs((int)(mLayer)[n][o].y)/10f,1.0f}, 1.0f);
                            
                             tempP=new Square((mLayer)[n][o]);   
                             tempP.set(1, tempP.y/5.0f);
                             tempP.drawPoint();
                             tempP=new Square((mLayer)[n+1][o]);   
                             tempP.set(1, tempP.y/5.0f);
                             tempP.drawPoint();
                             tempP=new Square((mLayer)[n+1][o+1]);   
                             tempP.set(1, tempP.y/5.0f);
                             tempP.drawPoint();
                             tempP=new Square((mLayer)[n][o+1]);   
                             tempP.set(1, tempP.y/5.0f);
                             tempP.drawPoint();
                             GL11.glEnd();
                            }                            
                        }
                    
            }*/
            public void drawConts()
            {
                GL11.glPointSize(8.0f);
                GL11.glBegin(GL11.GL_QUADS);
                //System.out.println((mWata.size()));
                int k=0;
                for(ArrayList<Square> ar:mWata){                    
                    for(Square s:ar){
                        //s.mColour=new Color(((float)k)/mWata.size(),0.1f,1.0f,0.75f);
                        s.draw();
                    }
                    k++;
                }
                k=0;
                for(ArrayList<Square> ar:mConta){
                    
                    for(Square s:ar){
                        //s.mColour=new Color(((float)k)/mConta.size(),1.0f,0.1f,1.0f);
                        s.draw();
                    }
                    k++;
                }
                k=0;
                for(ArrayList<Square> ar:mMunta){
                    
                    for(Square s:ar){
                        //s.mColour=new Color(((float)k)/mMunta.size(),1.0f,((float)k)/mMunta.size(),1.0f);
                        s.draw();
                    }
                    k++;
                }
                GL11.glEnd();
            }
            public void drawTemp()
            {
                
                for(int m=0;m<12;m++)
                {
                    GL11.glBegin(GL11.GL_LINE_STRIP);
                    for(int j=0;j<=MaxSpread;j++)
                    {
                        tzones[m][j].draw();
                    }
                    GL11.glEnd();
                }
                
            }
        
        
        //System.out.println(counter);
    
    public double randf(float fac)
    {           
        
        return (fac*(rgen.nextDouble()*30.0f-15.0f));
    }
    public boolean isUpPassableFrom(Square P, int S){
        return true;
    }
    public boolean isDownPassableFrom(Square P,int S){
        return true;
    }
    public boolean isRightPassableFrom(Square P, int S){
        return true;
    }
    public boolean isLeftPassableFrom(Square P, int S){
        return true;
    }
    public synchronized boolean areaHas(Square P, String N){
        
        return false;
    }
    ArrayList<Drawable> mChanged=new ArrayList();
    public synchronized void receive(NPC in){
        mChanged.add(in);
    }
    ArrayList<Drawable> mEnts=new ArrayList();
    Interval sightX=new Interval(0,10);
    Interval sightY=new Interval(0,10);
    public void movePOV(int x, int y){
        sightX.add(x);
        sightY.add(y);
    }
    public void determineEntites(){
       for(Drawable d:mChanged){
           if(sightX.isBetween(d.getX())&&sightY.isBetween(d.getY()))
               mEnts.add(d);
           else
               mEnts.remove(d);
       }
    }
    public void drawEnttites(){
        for(Drawable d:mEnts){
            d.draw();
        }
    }
    public void draw(){
        if(mZoomed==null){
            drawConts();
        }
        else
            mZoomed.draw();
    }
    public class SeaPoint extends Square{
        public SeaPoint(Square p){
            super(p);
        }
        public SeaPoint(byte x, byte y, byte z, Color c){
            super(x,y,z,c);
        }
        public boolean equals(Object o){
            Square c=(Square)o;
            if(cREMOVE)
                return super.equals(o);
            else
                return super.distance(c)<3;
        }
    }
    public Square getTrag(Environment en, Environment ne){
        return new Square((byte)(Math.cos(en.dif(ne).angle())*64),(byte)0,(byte)(Math.sin(en.dif(ne).angle())*64),Color.black);
    }
    public class Range extends ArrayList<ArrayList<Square>>{
        public boolean add(ArrayList<SeaPoint> c){
            if(c.size()<=0)
                return false;
            ArrayList<Square> temp=new ArrayList<Square>(c.size());
            for(SeaPoint s:c){
                temp.add(new Square(s));
            }
            this.add(temp);
            return c.size()>0;
        }
        public boolean encompasses(Square g){
            for(ArrayList<Square> c:this){
                if(c.contains(g))
                    return true;
            }
            return false;
            
        }
    }
    public static class allRegionTypes{
        static ArrayList<Resourcer> mCons=new ArrayList<Resourcer>();
        static ArrayList<Condition> mCods=new ArrayList<Condition>();
        public allRegionTypes(){
            try {
                Class[] c=Class.forName("World.allRegionTypes").getClasses();
                for(Class C:c){
                    mCons.add((Resourcer)(C.newInstance()));
                    mCods.add(((Recognizable)C.newInstance()).getCondition());                    
                }
            } catch (InstantiationException ex) {
                Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);            
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(World.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        public static Resourcer get(Object[] o){
            return mCons.get(mCods.indexOf(o));
        }
        public class Ocean implements Resourcer{
            public Condition getCondition(){
                return new Condition(){
                    public Boolean isTrue(Object o){
                        return ((Integer)((Object[])o)[0])<=Sealevel;
                    }
                };
            }
            public BlockType getResource(int depth){
                if(depth<=0)
                    return new WaterBlock();
                else
                    return null;
            }
            public String getName(){return "Ocean";};
        }
    }
    public class Region{
        Interval mX;
        Interval mY;
        double[][] mE;
        double[] mH;
        
        String Characterization;
        double mT;
        
        Resourcer mR;
        
        public Region(int x, int c, int y, int u){
            mH=new double[]{mLayer[x][y],mLayer[c][y],mLayer[c][u],mLayer[x][u]};
            mE=cornersSet(setupPointArray(MaxSpread+1,MaxSpread+1,0.5f),mH);
            CreateHeightMap(mE,1,(float)(Math.random()*3+1.0f));
            mX=new Interval(x,c);
            mY=new Interval(y,u);            
            
            int t=0;
            int s=0;
            int avg=(x+c+y+u)/4;
            for(ArrayList<Interval> a:mTemperatures){
                if(a.contains(avg)){
                    t=s;                    
                }
                s++;
            }
            mT=-Math.pow((t-7.23)/3,2)+6;
            mR=GenName();
            Characterization=mR.getName();            
        }
        public Resourcer GenName(){
            int avg=0;
            for(double f: mH)
                avg+=f;
            avg/=4;
            int avg2=0;
            for(double f: mH)
                avg2+=f*f;
            avg2/=4;
            int Max=0;
            for(int m=0;m<4;m++){
                if(mH[Max]<mH[m])
                    Max=m;
            }
            int Min=0;
            for(int m=0;m<4;m++){
                if(mH[Min]>mH[m])
                    Min=m;
            }
            double stdev=Math.sqrt(avg2-(avg)*(avg));
            try{
                return allRegionTypes.get(new Object[]{avg,stdev,Max,Min});
            }catch(NullPointerException e){
                return new Resourcer(){
                    public BlockType getResource(int depth) {
                        throw new UnsupportedOperationException("Not supported yet.");
                    }
                    public Condition getCondition() {
                        return new Condition(){
                            public Boolean isTrue(Object o){
                                return false;
                            }
                        };
                    }
                    public String getName(){
                        return "Null";
                    }
                };
            }
            
        }
        
        public boolean equals(Object o){
            if(o.getClass().getName().equals("World.SeaPoint"))
                return mX.isBetween((int)(((SeaPoint)o).xz[0]))&&mY.isBetween((int)(((SeaPoint)o).xz[2]));
            return false;
        }
        ArrayList<Integer> mMoistureYear=new ArrayList<Integer>();
        double averageRainfall=1;
        public void addMoisture(int a){
            mMoistureYear.add(a);
            int averageRainfall=0;
            for(Integer m:mMoistureYear)
                averageRainfall+=m;
            averageRainfall/=mMoistureYear.size();
        }
        public void YearlyTurnover(){
            mMoistureYear.clear();
        }
    }
    public class Cloud extends ArrayList<Square>{
        public Cloud(Square a){
            super();
            add(new SeaPoint(a));
        }
        public void Burst(){
            int avg=0;
            Square center=new Square((byte)0,(byte)0,(byte)0,Color.black);
            for(Square c:this){
                avg+=c.xz[0];                
            }
            avg/=size();
            for(Square c:this){
                center.xz[0]=(byte)(center.xz[0]+c.xz[0]*c.y);
                center.xz[2]=(byte)(center.xz[2]+c.xz[2]*c.xz[2]);
            }
            center=center.mul(1/size()*1/avg);
            if(avg>10||mMunta.encompasses(center)){
                for(Square c:this){
                    mRegions.get(mRegions.indexOf(c)).addMoisture(avg);
                }
            }
        }
    }
    public class WaterSystem extends Thread{
        Range density=new Range();
        ArrayList<SeaPoint> total=new ArrayList<SeaPoint>();
        public void run(){
            int count=0;
            while(GLApp_LandSmash.running){
                if(Math.random()<0.001f){
                int r=(int)(mWata.size()*Math.random());
                int r2=(int)(mWata.get(r).size()*Math.random());
                total.add(new SeaPoint(mWata.get(r).get(r2)));
                int m=0;
                for(ArrayList<Square> d:density)
                    if(d.contains(mWata.get(r).get(r2)))
                        m=d.indexOf(mWata.get(r).get(r2));
                
                if(m==-1)
                    density.add(new Cloud(new Square(mWata.get(r).get(r2))));
                else
                    density.get(m).add(new Square(mWata.get(r).get(r2)));               
                
                if(count>=100){
                    count=0;
                    density=groupArray(total);
                }
                count++;
                }
                
                for(SeaPoint c: total){
                    c.xz[0]++;
                }
                BurstAll();
            }
        }
        public void BurstAll(){
            for(int m=0;m<density.size();m++){
                ((Cloud)density.get(m)).Burst();
            }
        }
    }
    public interface Resourcer extends Recognizable {
        public BlockType getResource(int depth);
        public String getName();
    }
    
    public abstract static class BlockType{
        Item Resource;
        Item Weakness;
        Item Strength;
        //Texture tex;
        private int hp;
        public abstract String getName();
        public Item hitWith(Item w){
            if(w.getClass().getSuperclass().getName().equals("control.Items.Weapon")){
            }
            else
                hp--;
            if(hp<=0)
                return Resource;
            else return null;
        }
    }
    public static class WaterBlock extends BlockType{
        public String getName(){
            return "Water";
        }
    }
    public void destory(){
        mZoomed.destroy();
    }
}
