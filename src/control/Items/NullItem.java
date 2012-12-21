/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Items;

import control.People.NPC;

/**
 *
 * @author Geoff
 */
public class NullItem extends Item{
         public NullItem(String N){
             Name=N;
         }
        public String use(NPC By, Item With, NPC To) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        public String special(NPC By, Item With, NPC To) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        public String purpose(){
            return "None";
        }
    }
