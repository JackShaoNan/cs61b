package com.nanShao;

/* Set.java */

import list.*;

/**
 *  A Set is a collection of Comparable elements stored in sorted order.
 *  Duplicate elements are not permitted in a Set.
 **/
public class Set {
    /* Fill in the data fields here. */
    private List set;

    /**
     * Set ADT invariants:
     *  1)  The Set's elements must be precisely the elements of the List.
     *  2)  The List must always contain Comparable elements, and those elements
     *      must always be sorted in ascending order.
     *  3)  No two elements in the List may be equal according to compareTo().
     **/

    /**
     *  Constructs an empty Set.
     *
     *  Performance:  runs in O(1) time.
     **/
    public Set() {
        // Your solution here.
        set = new DList();
    }

    /**
     *  cardinality() returns the number of elements in this Set.
     *
     *  Performance:  runs in O(1) time.
     **/
    public int cardinality() {
        // Replace the following line with your solution.
        return set.length();
    }

    /**
     *  insert() inserts a Comparable element into this Set.
     *
     *  Sets are maintained in sorted order.  The ordering is specified by the
     *  compareTo() method of the java.lang.Comparable interface.
     *
     *  Performance:  runs in O(this.cardinality()) time.
     **/
    public void insert(Comparable c) {
        // Your solution here.
        if(set.length() == 0){
            set.insertFront(c);
        }else{
            ListNode current = set.front();
            try{
                while (current != set.back() && c.compareTo(current.item()) > 0) {
                    current = current.next();
                }
                if(c.compareTo(current.item())>0){
                    current.insertAfter(c);
                }else if(c.compareTo(current.item())<0){
                    current.insertBefore(c);
                }
            }catch(InvalidNodeException e){
                System.out.println("insertion failed!");
                System.err.println(e);
            }
        }


    }

    /**
     *  union() modifies this Set so that it contains all the elements it
     *  started with, plus all the elements of s.  The Set s is NOT modified.
     *  Make sure that duplicate elements are not created.
     *
     *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
     *
     *  Your implementation should NOT copy elements of s or "this", though it
     *  will copy _references_ to the elements of s.  Your implementation will
     *  create new _nodes_ for the elements of s that are added to "this", but
     *  you should reuse the nodes that are already part of "this".
     *
     *  DO NOT MODIFY THE SET s.
     *  DO NOT ATTEMPT TO COPY ELEMENTS; just copy _references_ to them.
     **/
//    public void union(Set s) {
//        // Your solution here.
//        if(s.cardinality()!=0){
//            ListNode currentThis = this.set.front();
//            ListNode currentS = s.set.front();
//            try{
//                //第一次遍历this
//                while(currentThis!=this.set.back() && currentS!=s.set.back()){
//                    if(((Comparable)currentThis.item()).compareTo(currentS.item()) == 0){
//                        //相等 都进一
//                        currentS = currentS.next();
//                        currentThis = currentThis.next();
//                    }else if(((Comparable)currentThis.item()).compareTo(currentS.item()) < 0 && ((Comparable)currentThis.next().item()).compareTo(currentS.item()) > 0){
//                        //s比this当前大且比后一个小
//                        currentThis = currentThis .next();
//                        currentThis.insertBefore(currentS.item());
//                        currentS = currentS.next();
//                    }else if(((Comparable)currentThis.item()).compareTo(currentS.item()) < 0 && ((Comparable)currentThis.next().item()).compareTo(currentS.item()) <= 0){
//                        //s比this当前大 比后一个不小
//                        currentThis = currentThis.next();
//                    }else if(((Comparable)currentThis.item()).compareTo(currentS.item()) > 0){
//                        //s比this当前小
//                        currentThis.insertBefore(currentS.item());
//                        currentS = currentS.next();
//                    }
//                }
//                //第二次遍历剩下的
//                while(currentS!=s.set.back() || currentThis!=this.set.back()){
//                    if(currentS == s.set.back() && currentThis != this.set.back()){
//                        //s到头 this没到头
//                        if(((Comparable)currentThis.item()).compareTo(currentS.item()) < 0 && ((Comparable)currentThis.next().item()).compareTo(currentS.item()) > 0){
//                            //s比this当前大且比后一个小
//                            currentThis.insertAfter(currentS.item());
//                            return;
//                        }else if(((Comparable)currentThis.item()).compareTo(currentS.item()) < 0 && ((Comparable)currentThis.next().item()).compareTo(currentS.item()) <= 0){
//                            //s比this当前及后一个都大
//                            currentThis = currentThis.next();
//                        }else if(((Comparable)currentThis.item()).compareTo(currentS.item()) > 0){
//                            //s比this当前小
//                            currentThis.insertBefore(currentS.item());
//                            return;
//                        }else{
//                            //相等
//                            return;
//                        }
//                    }else if(currentS != s.set.back() && currentThis == this.set.back()){
//                        //s没到头 this到头
//                        if(((Comparable)currentThis.item()).compareTo(currentS.item()) < 0){
//                            //s比this当前大
//                            currentThis.insertAfter(currentS.item());
//                            currentThis = currentThis.next();
//                            currentS = currentS.next();
//                        }else if(((Comparable)currentThis.item()).compareTo(currentS.item()) < 0){
//                            //s比this 当前小
//                            currentThis.insertBefore(currentS.item());
//                            currentS = currentS.next();
//                        }else{
//                            //相等
//                            currentS = currentS.next();
//                        }
//                    }
//                }
//                //都到头了
//                if(((Comparable)currentThis.item()).compareTo(currentS.item()) < 0){
//                    //s大 插最后
//                    currentThis.insertAfter(currentS.item());
//                }else if(((Comparable)currentThis.item()).compareTo(currentS.item()) > 0){
//                    //s小 插前面
//                    currentThis.insertBefore(currentS.item());
//                }
//            }catch(InvalidNodeException e){
//                System.err.println(e);
//            }
//
//        }
//    }


    public void union(Set s){
        ListNode cur = set.front();
        ListNode curS = s.set.front();
        //若都没有遍历完
        while(cur.isValidNode() && curS.isValidNode()){

            try{
                Comparable curItem = (Comparable) cur.item();
                if(curItem.compareTo(curS.item()) == 0){
                    //相等 都进一
                    cur = cur.next();
                    curS = curS.next();
                }else if(curItem.compareTo(curS.item()) == -1){
                    //this 小
                    cur = cur.next();
                }else {
                    //this 大
                    cur.insertBefore(curS.item());
                    curS = curS.next();
                }
            }catch(InvalidNodeException e){
                e.printStackTrace();
            }
        }
        //只需在意s是否被遍历完, 若s遍历完直接结束
        while(curS.isValidNode()){
            //s没遍历完 this遍历完了 且s中剩下的一定都比this大 需要全部加入this
            try{
                set.back().insertAfter(curS.item());
                curS = curS.next();
            }catch (InvalidNodeException e){
                e.printStackTrace();
            }
        }
    }
    /**
     *  intersect() modifies this Set so that it contains the intersection of
     *  its own elements and the elements of s.  The Set s is NOT modified.
     *
     *  Performance:  Must run in O(this.cardinality() + s.cardinality()) time.
     *
     *  Do not construct any new ListNodes during the execution of intersect.
     *  Reuse the nodes of "this" that will be in the intersection.
     *
     *  DO NOT MODIFY THE SET s.
     *  DO NOT CONSTRUCT ANY NEW NODES.
     *  DO NOT ATTEMPT TO COPY ELEMENTS.
     **/
//    public void intersect(Set s) {
//        // Your solution here.
//        //若s为空 则结果为空 将this清空
//        if(s.cardinality() == 0 || this.cardinality() == 0){
//            for(int i=0; i<this.cardinality(); ++i){
//                try{
//                    this.set.front().remove();
//                }catch (InvalidNodeException e){
//                    System.err.println(e);
//                }
//
//            }
//        }else{
//            //this 和 s 都非空
//            ListNode currentThis =  this.set.front();
//            ListNode currentS =  s.set.front();
//            //遍历第一遍
//            while(currentS!=s.set.back() && currentThis!=this.set.back()){
//                try{
//                    if(((Comparable)currentThis.item()).compareTo(currentS.item()) == 0){
//                        //相等 保留
//                        currentS = currentS.next();
//                        currentThis = currentThis.next();
//                    }else if(((Comparable)currentThis.item()).compareTo(currentS.item()) < 0){
//                        //比s小 删除
//                        currentThis = currentThis.next();
//                        currentThis.prev().remove();
//                    }else {
//                        //比s大 s进一
//                        currentS = currentS.next();
//                    }
//                }catch (InvalidNodeException e){
//                    System.err.println(e);
//                }
//            }
//            //遍历第二遍
//            while(currentS!=s.set.back() || currentThis!=this.set.back()){
//                try{
//                    if(currentS == s.set.back() && currentThis != this.set.back()){
//                        //只s到头
//                        if(((Comparable)currentThis.item()).compareTo(currentS.item()) == 0){
//                            //相等 保留 并删除this中剩余所有
//                            currentThis = currentThis.next();
//                            while(currentThis!=this.set.back()){
//                                currentThis = currentThis.next();
//                                currentThis.prev().remove();
//                            }
//                            currentThis.remove();
//                            return;
//                        }else if(((Comparable)currentThis.item()).compareTo(currentS.item()) < 0){
//                            //比s小 删除
//                            currentThis = currentThis.next();
//                            currentThis.prev().remove();
//                        }else {
//                            //比s大 删除this所有
//                            while(currentThis!=this.set.back()){
//                                currentThis = currentThis.next();
//                                currentThis.prev().remove();
//                            }
//                            currentThis.remove();
//                            return;
//                        }
//                    }else if(currentS != s.set.back() && currentThis == this.set.back()){
//                        //只有this到头
//                        if(((Comparable)currentThis.item()).compareTo(currentS.item()) == 0){
//                            //相等 保留 结束
//                            return;
//                        }else if(((Comparable)currentThis.item()).compareTo(currentS.item()) < 0){
//                            //比s小 删除结束
//                            currentThis.remove();
//                            return;
//                        }else {
//                            //比s大 再往后找
//                            currentS = currentS.next();
//                        }
//                    }
//                }catch  (InvalidNodeException e){
//                    System.err.println(e);
//                }
//            }
//            try{
//                if(((Comparable)currentThis.item()).compareTo(currentS.item()) == 0){
//                    //相等 保留
//                    return;
//                }else{
//                    //不等 删除
//                    currentThis.remove();
//                    return;
//                }
//            }catch (InvalidNodeException e){
//                System.err.println(e);
//            }
//        }
//
//    }

    public void intersect(Set s){
        ListNode cur = set.front();
        ListNode curS = s.set.front();

        while(cur.isValidNode() && curS.isValidNode()){

            try{
                Comparable curItem = (Comparable) cur.item();
                if(curItem.compareTo(curS.item()) == 0){
                    //相等 保留 都进一
                    cur = cur.next();
                    curS = curS.next();
                }else if(curItem.compareTo(curS.item()) == -1){
                    //this  小 删除
                    ListNode temp = cur;
                    cur = cur.next();
                    temp.remove();
                }else {
                    //this 大
                    curS = curS.next();
                }
            }catch (InvalidNodeException e){
                e.printStackTrace();
            }
        }
        //只在意s是否遍历完 若是 则需要将this中剩余的删除
        while(cur.isValidNode()){
            try{
                ListNode temp = cur;
                cur = cur.next();
                temp.remove();
            }catch (InvalidNodeException e){
                e.printStackTrace();
            }

        }
    }

    /**
     *  toString() returns a String representation of this Set.  The String must
     *  have the following format:
     *    {  } for an empty Set.  No spaces before "{" or after "}"; two spaces
     *            between them.
     *    {  1  2  3  } for a Set of three Integer elements.  No spaces before
     *            "{" or after "}"; two spaces before and after each element.
     *            Elements are printed with their own toString method, whatever
     *            that may be.  The elements must appear in sorted order, from
     *            lowest to highest according to the compareTo() method.
     *
     *  WARNING:  THE AUTOGRADER EXPECTS YOU TO PRINT SETS IN _EXACTLY_ THIS
     *            FORMAT, RIGHT UP TO THE TWO SPACES BETWEEN ELEMENTS.  ANY
     *            DEVIATIONS WILL LOSE POINTS.
     **/
    public String toString() {
        // Replace the following line with your solution.
        String setToString = "";
        if(cardinality()==0)
            return "{  }";
        DListNode temp = (DListNode) set.front();
        for(int i=0; i<this.cardinality(); i++){

            try{
                if(i==0){
                    setToString += "{  "+temp.item();
                    if(this.cardinality() == 1){
                        setToString += "  }";
                        break;
                    }
                } else if(i == cardinality()-1){
                    setToString += "  " + temp.item() + "  }";
                } else
                    setToString += "  " + temp.item();
                temp = (DListNode) temp.next();
            }catch (InvalidNodeException e){
                System.err.println(e);
            }

        }
        return setToString;
    }

    public static void main(String[] argv) {
        Set s = new Set();
        s.insert(new Integer(3));
        s.insert(new Integer(4));
        s.insert(new Integer(3));
        System.out.println("Set s = " + s);

        Set s2 = new Set();
        s2.insert(new Integer(4));
        s2.insert(new Integer(5));
        s2.insert(new Integer(5));
        System.out.println("Set s2 = " + s2);

        Set s3 = new Set();
        s3.insert(new Integer(5));
        s3.insert(new Integer(3));
        s3.insert(new Integer(8));
        System.out.println("Set s3 = " + s3);

        s.union(s3);
        System.out.println("After s.union(s3), s = " + s);

        s.intersect(s2);
        System.out.println("After s.intersect(s2), s = " + s);

        System.out.println("s.cardinality() = " + s.cardinality());
        // You may want to add more (ungraded) test code here.
    }
}
