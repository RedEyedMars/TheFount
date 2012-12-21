/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Misc;

/**
 *
 * @author Geoff
 */
public class Interval {
    private Integer Start;
    private Integer Finish;
    public Interval(Integer X, Integer Y){
        Start=X;
        Finish=Y;
    }
    public Interval(Integer[] X){
        Start=X[0];
        Finish=X[1];
    }
    public boolean isBetween(Integer O){
        return Start<O&&Finish>O;
    }
    public boolean isContainedWithin(Interval O){
        return Start<O.get()[0]&&Finish>O.get()[1];
    }
    public boolean equals(Object O){
        if(O.getClass().getName().equals("Integer")){
            return Start<(Integer)O&&Finish>(Integer)O;
        }
        if(O.getClass().getName().equals("Interval")){
            return Start<((Interval)O).get()[0]&&Finish>((Interval)O).get()[1];
        }
        return false;
    }
    public Integer[] get(){
        return new Integer[]{Start,Finish};
    }
    public void add(Integer O){
        Start+=O;
        Finish+=0;
    }
}
