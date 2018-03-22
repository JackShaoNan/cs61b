package com.nanShao;



/* Date.java */

import java.io.*;

class Date {

    /* Put your private data fields here. */

    /** Constructs a date with the given month, day and year.   If the date is
     *  not valid, the entire program will halt with an error message.
     *  @param month is a month, numbered in the range 1...12.
     *  @param day is between 1 and the number of days in the given month.
     *  @param year is the year in question, with no digits omitted.
     */

    private int month;
    private int day;
    private int year;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    /** Constructs a Date object corresponding to the given string.
     *  @param s should be a string of the form "month/day/year" where month must
     *  be one or two digits, day must be one or two digits, and year must be
     *  between 1 and 4 digits.  If s does not match these requirements or is not
     *  a valid date, the program halts with an error message.
     */
    public Date(String s) {
        String[] spiltString = s.split("/");
        //month must be one or two digits
        if(spiltString[0].length() == 1 || spiltString[0].length() == 2){
            month = Integer.parseInt(spiltString[0]);
        }else{
            System.out.println("your month is wrong!");
            System.exit(0);
        }
        if(spiltString[1].length() == 1 || spiltString[1].length() == 2){
            day = Integer.parseInt(spiltString[1]);
        }else{
            System.out.println("your day is wrong!");
            System.exit(0);
        }
        if(spiltString[2].length() >= 1 && spiltString[2].length() <= 4){
            year = Integer.parseInt(spiltString[2]);
        }else{
            System.out.println("your year is wrong!");
            System.exit(0);
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /** Checks whether the given year is a leap year.
     *  @return true if and only if the input year is a leap year.
     */
    public static boolean isLeapYear(int year) {
        if((year%4==0 && year%100!=0) || year%400==0){
            return true;
        }else
            return false;
    }

    /** Returns the number of days in a given month.
     *  @param month is a month, numbered in the range 1...12.
     *  @param year is the year in question, with no digits omitted.
     *  @return the number of days in the given month.
     */
    public static int daysInMonth(int month, int year) {
        switch (month){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 2:
                if(isLeapYear(year)){
                    return 29;
                }else
                    return 28;
            default:
                return 30;
        }
    }

    /** Checks whether the given date is valid.
     *  @return true if and only if month/day/year constitute a valid date.
     *
     *  Years prior to A.D. 1 are NOT valid.
     */
    public static boolean isValidDate(int month, int day, int year) {
        if(month<=12 && month>=1){
            if(year>=1 && year<=9999){
                if(day>=1 && day<=daysInMonth(month,year)){
                    return true;
                }else {
                    System.out.println("your day is not valid!");
                    return false;
                }
            }else{
                System.out.println("your year is not valid!");
                return false;
            }
        }else{
            System.out.println("your month is not valid!");
            return false;
        }
    }

    /** Returns a string representation of this date in the form month/day/year.
     *  The month, day, and year are expressed in full as integers; for example,
     *  12/7/2006 or 3/21/407.
     *  @return a String representation of this date.
     */
    public String toString() {
        return this.month+"/"+this.day+"/"+this.year;                     // replace this line with your solution
    }

    /** Determines whether this Date is before the Date d.
     *  @return true if and only if this Date is before d.
     */
    public boolean isBefore(Date d) {
        if(!isValidDate(d.getMonth(),d.getDay(),d.getYear()))
            System.exit(0);
        if(this.year > d.year){
            return false;
        }else {
            if(this.year == d.year){
                if(this.month > d.month){
                    return false;
                }else {
                    if(this.month == d.month){
                        if(this.day < d.day){
                            return true;
                        }else
                            return false;
                    }else
                        return true;
                }
            }else {
                return true;
            }

        }
    }

    /** Determines whether this Date is after the Date d.
     *  @return true if and only if this Date is after d.
     */
    public boolean isAfter(Date d) {
        /*if(!isValidDate(d.getMonth(),d.getDay(),d.getYear()))
            System.exit(0);
        if(this.year<d.year){
            return false;
        }else {
            if(this.year == d.year){
                if(this.month<d.month){
                    return false;
                }else {
                    if(this.month == d.month){
                        if(this.day > d.day){
                            return true;
                        }else
                            return false;
                    }else
                        return true;
                }
            }else {
                return true;
            }
        }*/

        if(this.isEqual(d)){
            return false;
        }else
            return !this.isBefore(d);
    }

    /*
    compare two date objects
     */
    public boolean isEqual(Date d){
        if(this.day == d.day){
            if(this.month == d.month){
                if(this.year == d.year)
                    return true;
            }else
                return false;
        }
        return false;
    }

    /** Returns the number of this Date in the year.
     *  @return a number n in the range 1...366, inclusive, such that this Date
     *  is the nth day of its year.  (366 is used only for December 31 in a leap
     *  year.)
     */
    public int dayInYear() {
        int nthDay = this.day;
        for(int i=1; i<this.month; i++){
            nthDay += daysInMonth(i,this.year);
        }
        return nthDay;                           // replace this line with your solution
    }



    /** Determines the difference in days between d and this Date.  For example,
     *  if this Date is 12/15/2012 and d is 12/14/2012, the difference is 1.
     *  If this Date occurs before d, the result is negative.
     *  @return the difference in days between d and this date.
     */
    public int difference(Date d) {
        if(!isValidDate(d.getMonth(),d.getDay(),d.getYear()))
            System.exit(0);
        int diff = 0;
        if(isBefore(d)){
            if(this.year == d.year){
                diff = this.dayInYear()-d.dayInYear();
            }else{
                if(isLeapYear(this.year)){
                    diff += 366-this.dayInYear();
                }else {
                    diff += 365-this.dayInYear();
                }
                for(int i=this.year+1; i<d.year; i++){
                    if(isLeapYear(i)){
                        diff += 366;
                    }else
                        diff += 365;
                }
                diff += d.dayInYear();
                diff = -diff;
            }
        }else{
            if(this.year == d.year){
                diff = this.dayInYear()-d.dayInYear();
            }else{
                if(isLeapYear(d.year)){
                    diff += 366-d.dayInYear();
                }else {
                    diff += 365-d.dayInYear();
                }
                for(int i=d.year+1; i<this.year; i++){
                    if(isLeapYear(i)){
                        diff += 366;
                    }else
                        diff += 365;
                }
                diff += this.dayInYear();
            }
        }
        return diff;                           // replace this line with your solution
    }

    public static void main(String[] argv) {
        System.out.println("\nTesting constructors.");
        Date d1 = new Date(1, 1, 1);
        System.out.println("Date should be 1/1/1: " + d1);
        d1 = new Date("2/4/2");
        System.out.println("Date should be 2/4/2: " + d1);
        d1 = new Date("2/29/2000");
        System.out.println("Date should be 2/29/2000: " + d1);
        d1 = new Date("2/29/1904");
        System.out.println("Date should be 2/29/1904: " + d1);

        d1 = new Date(12, 31, 1975);
        System.out.println("Date should be 12/31/1975: " + d1);
        Date d2 = new Date("1/1/1976");
        System.out.println("Date should be 1/1/1976: " + d2);
        Date d3 = new Date("1/2/1976");
        System.out.println("Date should be 1/2/1976: " + d3);

        Date d4 = new Date("2/27/1977");
        Date d5 = new Date("8/31/2110");

        /* I recommend you write code to test the isLeapYear function! */
        System.out.println("\nTesting isLeapYear function");
        System.out.println("2000 should be a leap year: " + isLeapYear(2000));
        System.out.println("2018 should not be a leap year: " + isLeapYear(2018));
        System.out.println("400 should be a leap year: " + isLeapYear(400));
        System.out.println("2004 should be a leap year: " + isLeapYear(2004));

        System.out.println("\nTesting before and after.");
        System.out.println(d2 + " after " + d1 + " should be true: " +
                d2.isAfter(d1));
        System.out.println(d3 + " after " + d2 + " should be true: " +
                d3.isAfter(d2));
        System.out.println(d1 + " after " + d1 + " should be false: " +
                d1.isAfter(d1));
        System.out.println(d1 + " after " + d2 + " should be false: " +
                d1.isAfter(d2));
        System.out.println(d2 + " after " + d3 + " should be false: " +
                d2.isAfter(d3));

        System.out.println(d1 + " before " + d2 + " should be true: " +
                d1.isBefore(d2));
        System.out.println(d2 + " before " + d3 + " should be true: " +
                d2.isBefore(d3));
        System.out.println(d1 + " before " + d1 + " should be false: " +
                d1.isBefore(d1));
        System.out.println(d2 + " before " + d1 + " should be false: " +
                d2.isBefore(d1));
        System.out.println(d3 + " before " + d2 + " should be false: " +
                d3.isBefore(d2));

        System.out.println("\nTesting difference.");
        System.out.println(d1 + " - " + d1  + " should be 0: " +
                d1.difference(d1));
        System.out.println(d2 + " - " + d1  + " should be 1: " +
                d2.difference(d1));
        System.out.println(d3 + " - " + d1  + " should be 2: " +
                d3.difference(d1));
        System.out.println(d3 + " - " + d4  + " should be -422: " +
                d3.difference(d4));
        System.out.println(d5 + " - " + d4  + " should be 48762: " +
                d5.difference(d4));
    }
}