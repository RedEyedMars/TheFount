/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Tools;

/**
 *
 * @author Geoff
 */
public class glTwoInt {
    int mA=0;
    int mB=0;
    glTwoInt(int a, int b){mA=a;mB=b;}
    public boolean equals(glTwoInt b){
        System.out.println("equals being called");
        if(b.mA==mA&&b.mB==mB)return true;
        else return false;
    }
}
