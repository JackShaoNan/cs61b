package com.nanShao;

public class DlinkedList {
    private DlinkedListNode head;
    private DlinkedListNode tail;
    private int size;

    public DlinkedList(){
        head = null;
        tail = head;
        size = 0;
    }

    public void headInsert(DlinkedListNode node){
        if(node != null){
            if(size==0){
                size++;
                head=node;
                tail=node;
            }else{
                size++;
                node.setNext(head);
                node.setPrev(null);
                head.setPrev(node);
                head = node;
            }
        }
    }

    public void tailInsert(DlinkedListNode node){
        if(node != null){
            if(size==0){
                size++;
                head=node;
                tail=node;
            }else {
                size++;
                tail.setNext(node);
                node.setPrev(tail);
                node.setNext(null);
                tail = node;
            }

        }
    }

    public DlinkedListNode getHead() {
        return head;
    }

    public DlinkedListNode getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }
}
