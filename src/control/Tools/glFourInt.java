/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Tools;

/**
 *
 * @author Geoff
 */
public class glFourInt {
    int mA=-1;
    int mB=-1;
    int mC=-1;
    int mD=-1;
    
    glFourInt()
    {
        mA=-1;
        mB=-1;
        mC=-1; 
        mD=-1;
    }
    glFourInt(int a)
    {
        mA=a;
    }
    public void add(int a)
    {
      if(mA==-1)
      {
          mA=a;
          return;
      }
      if(mB==-1)
      {
          mB=a;
          return;
      }
      if(mC==-1)
      {
          mC=a;
          return;
      }
      if(mD==-1)
      {
          mD=a;
      }
      return;
    }
}
