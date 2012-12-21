/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Misc;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Geoff
 */
 public class Bite{
        byte[] mb;
        public static Bite[] psbMvs=new Bite[]{new Bite(new byte[]{0,-1}),new Bite(new byte[]{-1,0}),new Bite(new byte[]{0,1}),new Bite(new byte[]{1,0})};
        public Bite(byte[]b){
            mb=b;
        }
        public Bite(Byte[]b){
            mb=new byte[b.length];
            for(int m=0;m<b.length;m++){
                mb[m]=b[m];
            }
        }       
        public byte[] add(int i){
            byte[] ret=new byte[mb.length];
            for(int m=0;m<mb.length;m++){
                ret[m]=(byte)(mb[m]+i);
            }
            return ret;
        }
        public byte[] add(byte[] i){
            byte[] ret=new byte[mb.length];
            ret[0]=(byte)(mb[0]+i[0]);
            ret[2]=(byte)(mb[2]+i[1]);
            return ret;
        }
        public byte[] mul(int i){
            byte[] ret=new byte[mb.length];
            for(int m=0;m<mb.length;m++){
                ret[m]=(byte)(mb[m]*i);
            }
            return ret;
        }
        public byte[] get(){
            return mb;
        }        
        public Byte[] teg(){
            Byte[] ret=new Byte[mb.length];
            for(int m=0;m<mb.length;m++){
                ret[m]=(Byte)(mb[m]);
            }
            return ret;
        }
        public boolean equals(Object O){
            byte[] ob=((Bite)O).mb;
            //System.out.println(ob[0]+"=="+mb[0]+"&&"+ob[1]+"=="+mb[1]);
            return ob[0]==mb[0]&&ob[1]==mb[1];
        }
        public static ArrayList<Byte[]> toArray(Collection<Bite> bs){
            ArrayList<Byte[]> B=new ArrayList<Byte[]>(bs.size());
            for(Bite b:bs){
                B.add(b.teg());
            }
            return B;
        }
        public String toString(){
            String s=new String();
            for(Byte b:mb){
                s+=b+", ";
            }
            return s;
        }
    }
