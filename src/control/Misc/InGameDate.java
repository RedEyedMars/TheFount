/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.Misc;

import control.GLApp_LandSmash;

/**
 *
 * @author Geoff
 */
 public class InGameDate extends Thread{
        int SecondsPassed=0;
        int MinutesPassed=0;
        int HoursPassed=0;
        int DaysPassed=0;
        int WeeksPassed=0;
        int MonthsPassed=0;
        int YearsPassed=0;
        public InGameDate(InGameDate rhs){
           SecondsPassed=rhs.SecondsPassed;
           MinutesPassed=rhs.MinutesPassed;
           HoursPassed=rhs.HoursPassed;
           DaysPassed=rhs.DaysPassed;
           WeeksPassed=rhs.WeeksPassed;
           MonthsPassed=rhs.MonthsPassed;
           YearsPassed=rhs.YearsPassed; 
        }
        public InGameDate(){
            
        }
        public void run(){
            while(GLApp_LandSmash.isTimePassing){
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                }
                SecondsPassed++;
                if(SecondsPassed==60){
                    SecondsPassed=0;
                    MinutesPassed++;
                }
                if(MinutesPassed==60){
                    MinutesPassed=0;
                    HoursPassed++;
                }
                if(HoursPassed==24){
                    HoursPassed=0;
                    DaysPassed++;
                }
                if(DaysPassed==7){
                    DaysPassed=0;
                    WeeksPassed++;
                }
                if(WeeksPassed==4){
                    WeeksPassed=0;
                    MonthsPassed++;
                }
                if(MonthsPassed==12){
                    MonthsPassed=0;
                    YearsPassed++;
                }
            }
        }
    }
