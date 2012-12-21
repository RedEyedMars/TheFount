/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.People;

import control.Items.ItemBag;
import control.Items.Item;
import control.Misc.InGameDate;
import control.Ables.Fallable;
import control.Ables.Drawable;
import control.Worldly.Square;
import control.GLApp_LandSmash;
import control.Items.Block;
import control.Worldly.Area;
import control.Worldly.Environment;
import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Geoff
 */
public class NPC extends Square implements Runnable, Drawable, Fallable{

	@Override
	public boolean canFall() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
    
}
