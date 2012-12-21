/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Items;

import control.People.NPC;
import control.Worldly.Square;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Geoff
 */
public class Block extends Item{
    Square ms;
    public Block(){
        super();
    }
    public Block(Square s){
        super();
        ms=s;
    }
    public String use(NPC By, Item With, NPC To) {
        return "Take Block";
    }
    public String special(NPC By, Item With, NPC To) {
        return "Do cool stuff";
    }
    public String purpose() {
        return "Fulfilling imagination";
    }
    public void remove(){
        //System.out.println(ms);
        ms.decrease();
    }
    public class Air extends Block{        
        public Air(){
            super();
        }
        public Air(Square s){
            super(s);
        }
        public int hashCode(){
            return -126;
        }
    }
    public class Dirt extends Block{        
        public Dirt(){
            super();
        }
        public Dirt(Square s){
            super(s);
        }
        public int hashCode(){
            return -127;
        }
    }
    public class Stone extends Block{        
        public Stone(){
            super();
        }
        public Stone(Square s){
            super(s);
        }
        public int hashCode(){
            return -128;
        }
    }
    public boolean isTallerThan(byte b){
        return ms.getAmount()>b;
    }
    public boolean isShorterThan(byte b){
        //System.out.println(ms.getY()+"<"+b);
        return ms.getAmount()<b;
    }
    static Block Self=new Block() {
            public String special(NPC By, Item With, NPC To) {
            throw new UnsupportedOperationException("Not supported yet.");
        }};
    static public Byte generateBlockByte(String s){
        Class[] C=Block.class.getClasses();
        Byte r=-128;
            for(;(r+128<C.length&&r<127);r++){
                if(C[r+128].getName().equals(Block.class.getName()+"$"+s)){
                    return new Byte((byte)(r));
                }
            }
        return -128;
    }
    static public Block generate(String s){
        try {
            Class[] C=Block.class.getClasses();
            Class r=null;
            for(Class c:C){
                if(c.getName().equals(Block.class.getName()+"$"+s)){
                    r=c;
                    break;
                }
            }
            if(r!=null){
                Block w=(Block)(r.getConstructors()[0].newInstance(Block.Self));
                return w;
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static Block generate(Byte b,Square s){
        try {
            Class r=Block.class.getClasses()[b+128];
            /*for(Constructor c:r.getConstructors()){
                System.out.println(c);
            }*/
            if(r!=null){
                Block w=(Block)(r.getConstructors()[1].newInstance(Self,s));;
                return w;
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Block.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean equals(Object O){
        return O.hashCode()==hashCode();
    }
}
    
