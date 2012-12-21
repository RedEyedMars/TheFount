/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Items;

import control.People.NPC;
import java.util.ArrayList;
import java.lang.reflect.Method;

/**
 *
 * @author Geoff
 */
public abstract class Item {
    String Name=new String();
    String mQual=new String();
    ItemBag In;
    protected Integer wear=1;
    boolean isWorn=false;
    public Item(){
        Name=this.getClass().getName().replace(this.getClass().getSuperclass().getName()+"$", "");
        mQual="Plain";
    }
    public Item(String N,String Quality){
        Name=N;
        mQual=Quality;
    }
    public Item(String Quality){
        this();
        mQual=Quality;
    }        
    public Item(ItemBag Bag){
        Name=this.getClass().getName().replace(this.getClass().getSuperclass().getName()+"$", "");
        mQual="Plain";
        In=Bag;
    }
    public String getName(){
        return Name;
    }
    public boolean isWornOut(){
        return isWorn;
    }
    public void remove(){
        //wear--;
        if(wear<=0){
            isWorn=true;
            In.remove(this);
        }
    }
    public ItemBag getBag(){
        return In;
    }
    public boolean equals(Object O){
        if(super.equals(O))
            return true;
        boolean doom=false;
        Class[] test=O.getClass().getClasses();
        for(Class c:test){
            if(c.getName().equals("Item"))
                doom=true;
        }
        if(doom)
            return Name.equals(((Item)O).getName())&&mQual.equals(((Item)O).quality());
        if(O.getClass().getName().equals("String"))
            return Name.equals((String)O);
        return false;
    }
    public abstract String use(NPC By,Item With, NPC To);
    public abstract String special(NPC By, Item With, NPC To);
    public String quality(){
        return mQual;
    }
    public abstract String purpose();
    public int hashCode(){
        return Name.hashCode()*100+mQual.hashCode();
    }
    public void setBag(ItemBag b){
        In=b;        
    }
    public String toString(){
        return Name+":"+mQual+"|"+(In!=null);
    }
}
