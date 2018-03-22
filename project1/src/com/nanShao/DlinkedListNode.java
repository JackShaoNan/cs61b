package com.nanShao;

public class DlinkedListNode {
    private DlinkedListNode prev;
    private DlinkedListNode next;
    private int red;
    private int green;
    private int blue;
    private int times;

    public DlinkedListNode(){
        prev = null;
        next = null;
        red = 0;
        green = 0;
        blue = 0;
        times = 0;
    }

    public DlinkedListNode(DlinkedListNode prev, DlinkedListNode next, int red, int green, int blue, int times){
        this.prev = prev;
        this.next = next;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.times = times;
    }

    public DlinkedListNode getPrev() {
        return prev;
    }

    public void setPrev(DlinkedListNode prev) {
        this.prev = prev;
    }

    public DlinkedListNode getNext() {
        return next;
    }

    public void setNext(DlinkedListNode next) {
        this.next = next;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}
