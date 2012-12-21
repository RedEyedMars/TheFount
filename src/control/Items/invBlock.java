/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Items;

import control.People.NPC;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Geoff
 */
public class invBlock extends Item{
    public invBlock(){
        super();
    }
    public invBlock(ItemBag i){
        super(i);
    }
    public String use(NPC By, Item With, NPC To) {
        if(By.getEnviron().addBlock(((Block)With).ms,this.hashCode())){
//            System.out.println();
            remove();
        }
        return "Put down block";
    }
    public String special(NPC By, Item With, NPC To) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public String purpose() {
        return "Place Block";
    }
    public class Air extends invBlock{
        public Air(){
            super();
        }
        public Air(ItemBag i){
            super(i);
        }
        public int hashCode(){
            return -126;
        }
    }
    public class Dirt extends invBlock{
        public Dirt(){
            super();
        }
        public Dirt(ItemBag i){
            super(i);
        }
        public int hashCode(){
            return -127;
        }
    }
    public class Stone extends invBlock{
        public Stone(){
            super();
        }
        public Stone(ItemBag i){
            super(i);
        }        
        public int hashCode(){
            return -128;
        }
    }
    
    static invBlock Self=new invBlock() {
            public String special(NPC By, Item With, NPC To) {
            throw new UnsupportedOperationException("Not supported yet.");
        }};
    static public invBlock generate(String s){
        try {
            Class[] C=invBlock.class.getClasses();
            Class r=null;   
            for(Class c:C){
                if(c.getName().equals(invBlock.class.getName()+"$"+s)){
                    r=c;
                    break;
                }
            }
            if(r!=null){
                invBlock w=(invBlock)(r.getConstructors()[0].newInstance(Self));
                return w;
            }
        } catch (InstantiationException ex) {
            Logger.getLogger(invBlock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(invBlock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(invBlock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(invBlock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public boolean equals(Object O){
        return O.hashCode()==hashCode();
    }
}    
    
