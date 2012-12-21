/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Misc;

import java.util.logging.Level;
import java.util.logging.Logger;
import control.Worldly.World;
/**
 *
 * @author Geoff
 */
public abstract class Condition{
    public abstract Boolean isTrue(Object o);
    public boolean equals(Object o){
        return isTrue(o);
    }
}
