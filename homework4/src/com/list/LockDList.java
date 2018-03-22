package com.list;

public class LockDList extends DList {

    public void lockNode(DListNode node){
        ((LockDListNode)node).isLocked = true;
    }

    public void remove(DListNode node) {
        // Your solution here.
        if(((LockDListNode)node).isLocked = false){
            super.remove(node);
        }
    }

    //new node
    protected LockDListNode newNode(Object item, DListNode prev, DListNode next) {
        return new LockDListNode(item, prev, next);
    }


}
