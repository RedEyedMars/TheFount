/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Worldly;

import control.Ables.Drawable;
import control.Items.Block;
import control.Items.invBlock;
import control.People.NPC;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import org.lwjgl.opengl.GL11;
/**
 *
 * @author Geoff
 */
public class Environment extends Square implements Drawable{
    Area sb;
    NPCBag nb = new NPCBag();
    Environment Self=this;
    static ArrayList<Color> blockColours=new ArrayList<Color>(Arrays.asList(new Color[]{Color.lightGray,Color.green,Color.cyan}));
    public Environment(){
        create();
        addNPC();
        
    }
    public Environment(Square s,Area A, NPCBag N){
        super(s);
        sb=A;
        for(NPC n:N){
            if(A.contains(n)){
                nb.add(n);
            }
        }
    }
    public Environment(Square s){
        super(s);
    }
    public Environment(Environment e){
        super(e);
        nb=new NPCBag(e.nb);
        sb=new Area(e.sb);
    }
    public void draw() {
        sb.draw();
        nb.draw();
    }
    public Integer getX() {
        return 0;
    }
    public Integer getY() {
        return 0;
    }
    public void create(){
        sb=new Area(127,127,64,64);
        for(byte x=-64;x<63;x++){
            for(byte y=0;y<1;y++){
                for(byte z=-64;z<63;z++){
                    //System.out.println(x+" "+z);
                    byte b=(byte)-128;//(byte)(2*Math.random()-129);
                    sb.add(x,z,new Square(x,(byte)0,z,blockColours.get(b+128),this),b);
                }
            }
        }
    }
    public void addNPC(){
              
         
    }
    public void moveNPC(Square aThis, byte[] b) {
        nb.moveNPC(aThis, b);
    }
    Square temp;
    public boolean isPassable(Square aThis, byte[] b){
        //System.out.println(aThis);
        if(!sb.contains(aThis.add(b)))
            return false; 
//        System.out.println(aThis.add(b));
        temp=sb.gets(aThis.add(b));
//        System.out.println(temp);
        //System.out.println(aThis.xz[1]-temp.xz[1]-temp.y);
        return Math.abs(aThis.xz[1]-temp.xz[1]-temp.y)<15;
    }
    public Square getSquare(Square s){
        return sb.gets(s.xz[0],s.xz[2]);
    }
    public Block getBlock(Square n,Square s){
        return sb.getBlock(n,s.xz[0],s.xz[2]);
    }
    public void destroy(){
        nb.destroy();
    }
    public boolean addBlock(Square S, int i){
        if(sb.contains(S)){
            Square s = S.indexify();
            if(i==sb.getByte(s.xz[0],s.xz[2])){
                sb.gets(s.xz[0],s.xz[2]).increase();
            }
            else{
                Square toAdd=new Square();
                toAdd.xz[0]=s.xz[0];
                toAdd.xz[2]=s.xz[2];
                toAdd.xz[1]=(byte)(sb.gets(s.xz[0],s.xz[2]).xz[1]+sb.gets(s.xz[0],s.xz[2]).y);
                toAdd.y=1;
                toAdd.mColour=blockColours.get(i+128);
                toAdd.mEnviron=this;
                sb.add(s.xz[0],s.xz[2],toAdd,(byte)i);
            }
                
        }
        if(nb.contains(S)){
            for(NPC n:nb){
                n.add(new byte[]{0,1,0});
            }
        }
        return false;
    }
    public class NPCBag extends LinkedList<NPC> implements Drawable{
        public NPCBag(){
            super();
        }
        public NPCBag(NPCBag N){
            super();
            addAll(N);
        }
        public void draw() {
            GL11.glBegin(GL11.GL_QUADS);
            for(Square s:this){
                s.draw();
            }
            GL11.glEnd();
        }
        public Integer getX() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        public Integer getY() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        public void moveNPC(Square o, byte[] b) {
            o.xz[0]=(byte)(o.xz[0]+b[0]);
            o.xz[2]=(byte)(o.xz[2]+b[1]);
            try{
                Square elevator=sb.gets(o);
                o.xz[1]=elevator.top();
            }
            catch(IndexOutOfBoundsException e){
                System.out.println(b[0]+" "+b[1]);
            }
            //System.out.println(o.xz[1]+" "+(elevator.xz[1]+elevator.y));
            
            
        }
        public void destroy(){
            for(NPC n:this){
            }
        }
    }
    public Square getRandomSquare(){
        return sb.getRandom();
    }
    public Area getWholeArea(){
        return sb;
    }
    public Environment VantageOf(Square s){
        Environment ret= new Environment(this,sb.makeArea(sb.getCircle(s)),nb);
        return ret;
    }
    public NPC getNPC(int m){
        return nb.get(m);
    }
}
