/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Worldly;

import control.Ables.Drawable;
import control.Items.Block;
import control.Misc.Bite;
import java.util.ArrayList;
import java.util.Iterator;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author Geoff
 */
public class Area implements Drawable{
    public ArrayList<Square>[][] THIS;
    public ArrayList<Byte>[][] BYTE;
        protected Integer Height;
        protected Integer Width;
        protected Integer OffsetHeight=0;
        protected Integer OffsetWidth=0;
        public Integer getHeight(){return Height;}
        public Integer getOffsetHeight(){return OffsetHeight;}
        public Integer getWidth(){return Width;}
        public Integer getOffsetWidth(){return OffsetWidth;}
        public Area(){
            super();
        }
        public Area(Area a){
            super();
            Height=new Integer(a.Height);
            OffsetHeight=new Integer(a.OffsetHeight);
            Width=new Integer(a.Width);
            OffsetWidth=new Integer(a.OffsetWidth);
            
            THIS=new ArrayList[Height][Width];
            BYTE=new ArrayList[Height][Width];
            for(byte m=0;m<THIS.length;m++){
                for(byte n=0;n<THIS[m].length;n++){
                    THIS[m][n]=new ArrayList<Square>();
                    BYTE[m][n]=new ArrayList<Byte>();
                }
            }
        }
        public Area(Integer W,Integer H){
            THIS=new ArrayList[H][W];
            BYTE=new ArrayList[H][W];
            for(byte m=0;m<THIS.length;m++){
                for(byte n=0;n<THIS[m].length;n++){
                    THIS[m][n]=new ArrayList<Square>();
                    BYTE[m][n]=new ArrayList<Byte>();
                }
            }
            Height=H;
            Width=W;
        }
        public Area(Integer W,Integer H,Integer OH, Integer OW){
            THIS=new ArrayList[H][W];
            BYTE=new ArrayList[H][W];
            for(byte m=0;m<THIS.length;m++){
                for(byte n=0;n<THIS[m].length;n++){
                    THIS[m][n]=new ArrayList<Square>();
                    BYTE[m][n]=new ArrayList<Byte>();
                }
            }
            Height=H;
            Width=W;
            OffsetHeight=OH;
            OffsetWidth=OW;
        }
        public Area(Area A, Square s, Square d){
            THIS=new ArrayList[(d.getXZ()[2]-s.getXZ()[2])][(d.getXZ()[0]-s.getXZ()[0])];
            BYTE=new ArrayList[(d.getXZ()[2]-s.getXZ()[2])][(d.getXZ()[0]-s.getXZ()[0])];
            for(byte m=(byte)(s.getXZ()[2]),i=0;m<d.getXZ()[2];m++,i++){
                for(byte n=(byte)(s.getXZ()[0]),j=0;n<d.getXZ()[0];n++,j++){
                    THIS[i][j]=new ArrayList<Square>();
                    BYTE[i][j]=new ArrayList<Byte>();
                    //System.out.println(n+","+m+" "+s.getXZ()[2]+" "+s.getEnviron().getWholeArea().OffsetHeight);
                    THIS[i][j].addAll(A.THIS[m][n]);
                }
            }
            Height=d.getXZ()[2]-s.getXZ()[2];
            Width=d.getXZ()[0]-s.getXZ()[0];
        }
        public void add(byte x, byte y, Square s, Byte b){
            THIS[y+OffsetHeight][x+OffsetWidth].add(s);
            BYTE[y+OffsetHeight][x+OffsetWidth].add(b);
        }
        public Square gets(byte x,byte y,Object o){
            
            //System.out.println(get(lastIndexOf(o)).getY());
            return THIS[y][x].get(THIS[y][x].lastIndexOf(o));
        }
        public Square gets(byte x,byte y){
            
            //System.out.println(get(lastIndexOf(o)).getY());
            //System.out.println(x+" "+y);
//            System.out.println(THIS[y+OffsetHeight][x+OffsetWidth].size());
            return THIS[y][x].get(THIS[y][x].size()-1);
        }
        public Square gets(Square n){
            Square i=n.indexify();
            byte x=i.getXZ()[0];
            byte y=i.getXZ()[2];
            Square test=n.add(new byte[]{0,-5,0});
            test.y=(byte)(test.y+5);
            for(int m=THIS[y][x].size()-1;m>=0;m--){
                if(BYTE[y][x].get(m)!=-126&&THIS[y][x].get(m).top()>=test.bot()&&THIS[y][x].get(m).bot()<=test.top()){
                    //System.out.println(BYTE[y+OffsetHeight][x+OffsetWidth].get(m));
                    return THIS[y][x].get(m);
                }
            }
            return gets(x,y);
        }
        public Block getBlock(byte x,byte y,Object o){
            int i=THIS[y][x].lastIndexOf(o);
            //System.out.println(this.get(i).getY());
            return Block.generate(BYTE[y][x].get(i),THIS[y][x].get(i));
        }
        public Block getBlock(byte x,byte y){
            //System.out.println(x+", "+y);
            return Block.generate(BYTE[y+OffsetHeight][x+OffsetWidth].get(BYTE[y+OffsetHeight][x+OffsetWidth].size()-1),THIS[y+OffsetHeight][x+OffsetWidth].get(BYTE[y+OffsetHeight][x+OffsetWidth].size()-1));
        }
        public Block getBlock(Square n, byte x, byte y){
            Square test=n.add(new byte[]{0,-5,0});
            test.y=(byte)(test.y+10);
            for(int m=THIS[y+OffsetHeight][x+OffsetWidth].size()-1;m>=0;m--){
                if(THIS[y+OffsetHeight][x+OffsetWidth].get(m).top()>=test.bot()&&THIS[y+OffsetHeight][x+OffsetWidth].get(m).bot()<=test.top())
                    return Block.generate(BYTE[y+OffsetHeight][x+OffsetWidth].get(m),THIS[y+OffsetHeight][x+OffsetWidth].get(m));
            }
            System.out.println("return null");
            return null;
        }
        public Byte getByte(byte x,byte y,Object o){
            return BYTE[y][x].get(THIS[y][x].lastIndexOf(o));
        }
        public Byte getByte(byte x,byte y){
            return BYTE[y+OffsetHeight][x+OffsetWidth].get(BYTE[y+OffsetHeight][x+OffsetWidth].size()-1);
        }
        public Square getNext(byte x, byte y){
            x++;
            if(x>=Width){
                x=0;
                y++;
                if(y>=Height){
                    y=0;
                }
            }
            return gets(x,y);
        }
        public Square getRandom(){
            int Y=(int)(Height*Math.random());
            int X=(int)(Width*Math.random());
            return THIS[Y][X].get((int)(THIS[Y][X].size()*Math.random()));
        }
        public byte averageHeight(){
            int a=0;
            for(byte m=0;m<THIS.length-1;m++){
                for(byte n=0;n<THIS[m].length-1;n++){
                    a+=gets((byte)(n),(byte)(m)).top();
                }
            }
            return (byte)(a/(Height*Width));
        }            
        public void draw() {
            GL11.glBegin(GL11.GL_QUADS);
            for(byte m=0;m<THIS.length;m++){
                for(byte n=0;n<THIS[m].length;n++){
                    for(int i=0;i<THIS[m][n].size();i++){
                        if(BYTE[m][n].get(i)!=-126){
                            THIS[m][n].get(i).draw();
                        }
                    }
                }
            }
            GL11.glEnd();
        }
        public Integer getX() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        public Integer getY() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        public Iterator<Square> startIteratorAt(byte x, byte y){
            return new Lit(x,y);
        }
        public Area makeArea(ArrayList<Byte[]> bs){
            Area ret=new Area(this);
            for(Byte[] b:bs){
                ret.THIS[b[2]+OffsetHeight][b[0]+OffsetWidth]=THIS[b[2]+OffsetHeight][b[0]+OffsetWidth];
                ret.BYTE[b[2]+OffsetHeight][b[0]+OffsetWidth]=BYTE[b[2]+OffsetHeight][b[0]+OffsetWidth];
            }
            return ret;
        }
        public ArrayList<Byte[]> getCircle(Square s){
            ArrayList<Bite> ret=new ArrayList<Bite>();
            ret.add(new Bite(s.getXZ()));
            Bite r=new Bite(s.add(Bite.psbMvs[0].get()).getXZ());
            if(contains(new Square(r))&&gets(new Square(s,r)).top()<s.top())
                ret.add(r);
            ArrayList<Integer> mp=new ArrayList<Integer>(s.top());            
            for(int m=0;m<=s.top();m++){
                mp.add(new Integer(0));
            }
            for(int i=0;i<4;i++){
                int j=i+1;
                if(j==4)
                    j=0;
            while(mp.contains(i)){
                r=new Bite(s.getXZ());
            for(int h=s.top(),m=0;h>=0;m++){
                r=new Bite(r.add(Bite.psbMvs[mp.get(m)].get()));
                if((contains(new Square(r))&&gets(new Square(s,r)).top()<h))
                    ret.add(r);
                else{
                    break;
                }
                if(gets(new Square(s,r)).top()-s.top()>=0)
                    h-=gets(new Square(s,r)).top()-s.top()+1;
                else
                    h--;
            }
            mp.set(mp.indexOf(i),j);
            }
            }
            return Bite.toArray(ret);
        }
        protected class Lit implements Iterator{
            Byte Sx;
            Byte Sy;
            Byte Ix;
            Byte Iy;
            public Lit(byte x, byte y){
                Ix=x;
                Iy=y;
                Sx=x;
                Sy=y;
            }
            boolean onNotGoneAround=true;
            
            public Square next(){
                //System.out.println(Ix+" "+Iy);
                Square ret=gets((byte)(Ix),(byte)(Iy));
                Ix++;
                if(Ix>=Width-OffsetWidth){
                    Ix=(byte)-OffsetWidth;
                    Iy++;
                    if(Iy>=Height-OffsetHeight){
                        Iy=(byte)-OffsetHeight;
                        onNotGoneAround=false;
                    }
                }                        
                //System.out.println((byte)(Ix)+" "+(byte)(Iy)+" "+THIS[Iy].length+" "+THIS.length);
                return ret;
            }
            public boolean hasNext() {
                return (Ix<Sx&&Iy<Sy)||onNotGoneAround;
            }
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
            
        }
        public boolean contains(Square s){
            return (s.xz[0]>=0-OffsetWidth&&s.xz[0]<Width-OffsetWidth)&&(s.xz[2]>=0-OffsetHeight&&s.xz[2]<Height-OffsetHeight)&&(THIS[s.xz[2]+OffsetHeight][s.xz[0]+OffsetWidth].size()>0);
        }
        public ArrayList<Square> TopSquares(){
            ArrayList<Square> TS=new ArrayList<Square> ();
            for(byte m=0;m<THIS.length;m++){
                for(byte n=0;n<THIS[m].length;n++){
                    //System.out.println(gets((byte)(n-OffsetWidth),(byte)(m-OffsetHeight)));
                    TS.add(gets((byte)(n-OffsetWidth),(byte)(m-OffsetHeight)));
                }
            }
            return TS;
        }
        public void clear(){
            for(byte y=0;y<THIS.length;y++){
                for(byte x=0;x<THIS[y].length;x++){
                    THIS[y][x].clear();
                    BYTE[y][x].clear();
                }
            }
        }
    }
