package com.nanShao;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("test");
        DList1 test = new DList1();
        test.insertFront(1);
        test.insertFront(2);
        test.insertFront(3);
        System.out.println(test);
        change(test.head);
        System.out.println(test);
    }

    public static void change(DListNode1 temp){
        if(temp!=null){
            temp = null;
        }
    }
}

class merge{
//    public int[] mergeSort(int[] array){
//        if(array.length == 0)
//            return array;
//        int[] helper = new int[array.length];
//        mergeSort(array, helper, 0, array.length-1);
//        return array;
//    }
//
//    private void mergeSort(int[] array, int[] helper, int left, int right){
//        if(left>=right)
//            return;
//        int mid = left + (right-left)/2;
//        mergeSort(array, helper, left, mid);
//        mergeSort(array, helper, mid+1, right);
//        merge(array, helper, left, mid, right);
//    }
//
//    public void merge(int[] array, int[] helper, int left, int mid, int right){
//        for(int i=left; i<=right; i++){
//            helper[i] = array[i];
//        }
//
//        int leftIndex = left;
//        int rightIndex = mid+1;
//        int midIndex = mid;
//        while(leftIndex<=midIndex && rightIndex<=right){
//            if(helper[leftIndex]<helper[rightIndex]){
//                array[left++] = helper[leftIndex++];
//            }else{
//                array[left++] = helper[rightIndex++];
//            }
//        }
//        while(leftIndex<=midIndex){
//            array[left++] = helper[leftIndex++];
//        }
//    }














    public int[] mergeSort(int[] array){
        int left = 0;
        int right = array.length-1;
        //int mid = left + (right - left)/2;
        int[] helper = new int[array.length];
        mergeSort(array, helper, left, right);
        //merge(array, helper, left, mid, right);
        return array;
    }

    private void mergeSort(int[] array, int[] helper, int left, int right){
        //base case
        if(left>=right)
            return;
        int mid = left + (right-left)/2;
        mergeSort(array, helper, left, mid);
        mergeSort(array, helper, mid+1, right);
        merge(array, helper, left, mid, right);
    }

    private void merge(int[] array, int[] helper, int left, int mid, int right){
        for(int i=left; i<=right; i++){
            helper[i] = array[i];
        }
        int leftIndex = left;
        int rightIndex = mid+1;
        while(leftIndex<=mid && rightIndex<=right){
            if(helper[leftIndex]<helper[rightIndex]){
                array[left++] = helper[leftIndex++];
            }else{
                array[left++] = helper[rightIndex++];
            }
        }
        while(leftIndex<=mid){
            array[left++] = helper[leftIndex++];
        }
    }



























}