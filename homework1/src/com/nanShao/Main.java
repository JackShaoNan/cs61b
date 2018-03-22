package com.nanShao;

/* OpenCommercial.java */

import java.net.*;
import java.io.*;

/**  A class that provides a main function to read five lines of a commercial
 *   Web page and print them in reverse order, given the name of a company.
 */

class Main {

    /** Prompts the user for the name X of a company (a single string), opens
     *  the Web site corresponding to www.X.com, and prints the first five lines
     *  of the Web page in reverse order.
     *  @param arg is not used.
     *  @exception Exception thrown if there are any problems parsing the
     *             user's input or opening the connection.
     */
    public static void main(String[] arg) throws Exception {

        /*BufferedReader keyboard;
        String inputLine;

        keyboard = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Please enter the name of a company (without spaces): ");
        System.out.flush();        *//* Make sure the line is printed immediately. *//*
        inputLine = keyboard.readLine();

        *//* Replace this comment with your solution.  *//*
        URL url = new URL("http://www." + inputLine + ".com");
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        String[] outputLine = new String[5];
        for(int i=0; i<5; i++){
            if((outputLine[i]=in.readLine()) != null){

            }else {
                System.out.println("there is no more content");
                break;
            }
        }
        for(int i=outputLine.length-1; i>=0; i--){
            System.out.println(outputLine[i]);
            System.out.println("******************");
        }
        in.close();*/


        BufferedReader keyboard;
        String inputLine;

        keyboard = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Please enter the name of a company (without spaces): ");
        //System.out.flush();        *//* Make sure the line is printed immediately. *//*
        inputLine = keyboard.readLine();
        System.out.println(inputLine);
        if(inputLine.length()<2){
            System.out.println("your string length is less than 2");
        }else{
            char[] ch = new char[100];
            inputLine.getChars(0,1,ch,0);
            inputLine.getChars(2,inputLine.length(),ch,1);
            System.out.println(ch);
        }
    }
}

