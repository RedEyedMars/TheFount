/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Items;

import control.Items.Item;
import control.Worldly.Square;
import java.util.ArrayList;

/**
 *
 * @author Geoff
 */
public class ItemBag extends Square{
    ArrayList<Item> filled=new ArrayList<Item>();
    public void remove(Item b){
        filled.remove(b);
    }
    public boolean add(Item b){
        b.setBag(this);
        return filled.add(b);
    }    
    public boolean add(ItemBag b){
        return filled.addAll(b.filled);
    }
    public int size(){
        return filled.size();
    }
    public Item get(Item b){
        return filled.get(filled.indexOf(b));
    }
    public ArrayList<Item> get(){
        return filled;
    }
    public Item get(int b){
        return filled.get(b);
    }
    public int indexOf(String s){
        for(int m=0;m<filled.size();m++)
            if(filled.get(m).getName().equals(s))
                return m;
        return -1;
    }
}
