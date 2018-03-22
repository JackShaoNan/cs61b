package com.nanShao;

public class Main {

    public static void main(String[] args) {
	// write your code here
        X x = new X();
        Y y = new Y();
        X[] xa = new X[7];
        Y[] ya = new Y[7];

//        将子类赋值给父类是可以的，但反之不行。若将父类cast为子类类型
//        再赋值给子类，编译可以通过，但运行错误，因为只是把reference的类型
//        改变，真正的object类型还是父类，无法赋值给子类。

//        若先将子类变量赋值给父类变量，这时父类变量类型还是父类但其真正指向的
//        object是子类，此时只要将父类变量cast为子类，就可避免编译错误和运行错误


        // it is okay
        //x = y;

        //it is okay to compile, but will cause a run-time error, cuz x references an X object
        //y = (Y) x;

        //it is okay
        //xa = ya;

        //it is okay to compile, but will cause a run-time error, cuz x references an X object
        //ya = (Y[]) xa;

        //it is okay both in compiling and running
        //xa = ya;
        //ya = (Y[]) xa;

        y.printName("nan");
        y.printNum();
        ((X) y).printNum();
        x.printNum();
        ((Y)x).printNum();// there is a run-time error
    }
}
