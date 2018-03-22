package com.list;

public class Main {

    public static void main(String[] args) {
	// write your code here
        LockDList myLockList = new LockDList();
        myLockList.insertFront(1);
        System.out.println(myLockList.back());
    }
}
