package com.nanShao;

public class Y extends X implements X_interface{
        public void printName(String namess){
            System.out.println(namess);
        }

//        public void printNum(){
//            System.out.println("my num is " + myNum);
//        }

        public void printNum(){
            System.out.println("this is the subclass num " + X.myNum);
        }

        public static void main(String[] args){
            System.out.println(X.myNum);
        }

}
