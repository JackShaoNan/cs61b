package com.nanShao;
/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes
 *  a PixImage object.  Descriptions of the methods you must implement appear
 *  below.  They include constructors of the form
 *
 *      public RunLengthEncoding(int width, int height);
 *      public RunLengthEncoding(int width, int height, int[] red, int[] green,
 *                               int[] blue, int[] runLengths) {
 *      public RunLengthEncoding(PixImage image) {
 *
 *  that create a run-length encoding of a PixImage having the specified width
 *  and height.
 *
 *  The first constructor creates a run-length encoding of a PixImage in which
 *  every pixel is black.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts a PixImage object into a run-length encoding of that image.
 *
 *  See the README file accompanying this project for additional details.
 */

import com.nanShao.PixImage;
import com.nanShao.RunIterator;

import java.util.Iterator;

public class RunLengthEncoding implements Iterable {

    /**
     *  Define any variables associated with a RunLengthEncoding object here.
     *  These variables MUST be private.
     */
    private DlinkedList run;
    private int width;
    private int height;




    /**
     *  The following methods are required for Part II.
     */

    /**
     *  RunLengthEncoding() (with two parameters) constructs a run-length
     *  encoding of a black PixImage of the specified width and height, in which
     *  every pixel has red, green, and blue intensities of zero.
     *
     *  @param width the width of the image.
     *  @param height the height of the image.
     */

    public RunLengthEncoding(int width, int height) {
        // Your solution here.
        if(width>0 && height>0){
            this.width = width;
            this.height = height;
            run = new DlinkedList();
            run.tailInsert(new DlinkedListNode(null, null, 0, 0, 0, width*height));
        }else {
            System.out.println("error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("width = [" + width + "], height = [" + height + "]");
        }
    }

    /**
     *  RunLengthEncoding() (with six parameters) constructs a run-length
     *  encoding of a PixImage of the specified width and height.  The runs of
     *  the run-length encoding are taken from four input arrays of equal length.
     *  Run i has length runLengths[i] and RGB intensities red[i], green[i], and
     *  blue[i].
     *
     *  @param width the width of the image.
     *  @param height the height of the image.
     *  @param red is an array that specifies the red intensity of each run.
     *  @param green is an array that specifies the green intensity of each run.
     *  @param blue is an array that specifies the blue intensity of each run.
     *  @param runLengths is an array that specifies the length of each run.
     *
     *  NOTE:  All four input arrays should have the same length (not zero).
     *  All pixel intensities in the first three arrays should be in the range
     *  0...255.  The sum of all the elements of the runLengths array should be
     *  width * height.  (Feel free to quit with an error message if any of these
     *  conditions are not met--though we won't be testing that.)
     */

    public RunLengthEncoding(int width, int height, int[] red, int[] green,
                             int[] blue, int[] runLengths) {
        // Your solution here.
        if(red.length != green.length || red.length!=blue.length || red.length!=runLengths.length){
            System.out.println("four input arrays should have the same length");
            System.exit(0);
        }
        if(red.length<=0 || blue.length<=0 || green.length<=0){
            System.out.println("arrays' length should not be zero");
            System.exit(0);
        }
        int sumOfRunLength = 0;
        for(int i=0; i<red.length; i++){
            sumOfRunLength +=runLengths[i];
        }
        if(sumOfRunLength!=width*height){
            System.out.println("the sum of all the elements of the runLengths array should be width*height");
            System.exit(0);
        }

        run = new DlinkedList();
        this.width = width;
        this.height = height;
        for(int i=0; i<runLengths.length; i++){
            run.tailInsert(new DlinkedListNode(null, null, red[i], green[i], blue[i], runLengths[i]));
        }
    }

    /**
     *  getWidth() returns the width of the image that this run-length encoding
     *  represents.
     *
     *  @return the width of the image that this run-length encoding represents.
     */

    public int getWidth() {
        // Replace the following line with your solution.
        return width;
    }

    /**
     *  getHeight() returns the height of the image that this run-length encoding
     *  represents.
     *
     *  @return the height of the image that this run-length encoding represents.
     */
    public int getHeight() {
        // Replace the following line with your solution.
        return height;
    }

    /**
     *  iterator() returns a newly created RunIterator that can iterate through
     *  the runs of this RunLengthEncoding.
     *
     *  @return a newly created RunIterator object set to the first run of this
     *  RunLengthEncoding.
     */
    public RunIterator iterator() {
        // Replace the following line with your solution.
        RunIterator i = new RunIterator(run);
        return i;
        // You'll want to construct a new RunIterator, but first you'll need to
        // write a constructor in the RunIterator class.
    }

    /**
     *  toPixImage() converts a run-length encoding of an image into a PixImage
     *  object.
     *
     *  @return the PixImage that this RunLengthEncoding encodes.
     */
    public PixImage toPixImage() {
        // Replace the following line with your solution.
        PixImage result = new PixImage(width, height);
        DlinkedListNode node = run.getHead();
        int times = node.getTimes();
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                if(times>0){
                    times--;
                    result.setPixel(x, y, (short)node.getRed(), (short)node.getGreen(), (short)node.getBlue());
                }else{
                    node = node.getNext();
                    times = node.getTimes();
                    result.setPixel(x, y, (short)node.getRed(), (short)node.getGreen(), (short)node.getBlue());
                    times--;
                }
            }
        }
        return result;
    }

    /**
     *  toString() returns a String representation of this RunLengthEncoding.
     *
     *  This method isn't required, but it should be very useful to you when
     *  you're debugging your code.  It's up to you how you represent
     *  a RunLengthEncoding as a String.
     *
     *  @return a String representation of this RunLengthEncoding.
     */
    public String toString() {
        // Replace the following line with your solution.
        String result = "[";
        RunIterator i = new RunIterator(run);
        while(i.hasNext()){
            int red = i.next()[1];
            result += " "+red;
        }
        result += " "+i.getRed()+"]";
        return result;
    }


    /**
     *  The following methods are required for Part III.
     */

    /**
     *  RunLengthEncoding() (with one parameter) is a constructor that creates
     *  a run-length encoding of a specified PixImage.
     *
     *  Note that you must encode the image in row-major format, i.e., the second
     *  pixel should be (1, 0) and not (0, 1).
     *
     *  @param image is the PixImage to run-length encode.
     */
    public RunLengthEncoding(PixImage image) {
        // Your solution here, but you should probably leave the following line
        // at the end.
        this.width = image.getWidth();
        this.height = image.getHeight();
        short red = image.getRed(0,0);
        short green = image.getGreen(0,0);
        short blue = image.getBlue(0,0);
        run = new DlinkedList();
        run.tailInsert(new DlinkedListNode(null, null, red, green, blue, 0));
        RunIterator i = new RunIterator(run);
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                //只判断了一种颜色 默认三种颜色都是一致的
                if(image.getRed(x,y)==i.getRed()){
                    i.setTimes(i.getTimes()+1);
                }else {
                    run.tailInsert(new DlinkedListNode(null, null, image.getRed(x,y), image.getGreen(x,y), image.getBlue(x,y), 1));
                    int[] temp = i.next();
                }
            }
        }
        check();
    }

    /**
     *  check() walks through the run-length encoding and prints an error message
     *  if two consecutive runs have the same RGB intensities, or if the sum of
     *  all run lengths does not equal the number of pixels in the image.
     */
    public void check() {
        // Your solution here.
        int sum = 0;
        short currentRed = -1;
        RunIterator i = new RunIterator(run);
        while(i.hasNext()){
            int[] trgb = new int[4];
            trgb = i.next();
            sum+=trgb[0];
            if(currentRed!=trgb[1])
                currentRed = (short) trgb[1];
            else {
                System.out.println("error!!!!!!   you should merge the same runs!");
            }
        }
        if(currentRed==i.getRed()){
            System.out.println("error!!!!!!   you should merge the same runs!");
        }
        sum+=i.getTimes();
        if(sum!=width*height){
            System.out.println("all run lengths should equal to width*height");
        }
    }


    /**
     *  The following method is required for Part IV.
     */

    /**
     *  setPixel() modifies this run-length encoding so that the specified color
     *  is stored at the given (x, y) coordinates.  The old pixel value at that
     *  coordinate should be overwritten and all others should remain the same.
     *  The updated run-length encoding should be compressed as much as possible;
     *  there should not be two consecutive runs with exactly the same RGB color.
     *
     *  @param x the x-coordinate of the pixel to modify.
     *  @param y the y-coordinate of the pixel to modify.
     *  @param red the new red intensity to store at coordinate (x, y).
     *  @param green the new green intensity to store at coordinate (x, y).
     *  @param blue the new blue intensity to store at coordinate (x, y).
     */
    public void setPixel(int x, int y, short red, short green, short blue) {
        // Your solution here, but you should probably leave the following line
        //   at the end.
        int index = y*width+x+1;
        if(index>width*height){
            System.out.println("your coordinates are out of range!");
            System.exit(0);
        }
        RunIterator i = new RunIterator(run);
        int times = 0;
        int[] trgb = new int[4];
        //search for insert point
        while(i.hasNext()){
            trgb = i.next();
            times += trgb[0];
            if(times<index){
                continue;
            }else if(times==index){
                //we find it!
                DlinkedListNode currentNode = i.getPrev();
                if(currentNode.getRed() == red){
                    currentNode.setTimes(currentNode.getTimes()+1);
                    check();
                    return;
                }else{
                    if(currentNode.getTimes()-1 > 0){
                        currentNode.setTimes(currentNode.getTimes()-1);
                        //看是否和后面的可以合并
                        if(red == i.getRed()){
                            i.setTimes(i.getTimes()+1);
                            check();
                            return;
                        }else{
                            DlinkedListNode temp = new DlinkedListNode(null, null, red, green, blue, 1);
                            temp.setNext(currentNode.getNext());
                            currentNode.getNext().setPrev(temp);
                            currentNode.setNext(temp);
                            temp.setPrev(currentNode);
                            check();
                            return;
                        }
                    } else{
                        //此run的time为1，将重写当前位置

                        //需要判断和前后是否相等
                        //前面有节点的话
                        if(currentNode.getPrev()!=null){
                            if(red!=currentNode.getNext().getRed() && red!=currentNode.getPrev().getRed()){
                                currentNode.setRed(red);
                                currentNode.setGreen(green);
                                currentNode.setBlue(blue);
                                check();
                                return;
                            }else {
                                //和前面比
                                if(red == currentNode.getPrev().getRed()){
                                    if(red == currentNode.getNext().getRed()){
                                        //前后都相等
                                        currentNode.getPrev().setTimes(1+currentNode.getPrev().getTimes()+currentNode.getNext().getTimes());
                                        currentNode.getPrev().setNext(currentNode.getNext().getNext());
                                        currentNode.getNext().getNext().setPrev(currentNode.getPrev());
                                        check();
                                        return;
                                    }else{
                                        //只和前面一样
                                        currentNode.getPrev().setTimes(currentNode.getPrev().getTimes()+1);
                                        currentNode.getPrev().setNext(currentNode.getNext());
                                        currentNode.getNext().setPrev(currentNode.getPrev());
                                        check();
                                        return;
                                    }
                                }else {
                                    //和前面不一样 看后面
                                    if(red == currentNode.getNext().getRed()){
                                        //和后面一样
                                        currentNode.getNext().setTimes(currentNode.getNext().getTimes()+1);
                                        currentNode.getPrev().setNext(currentNode.getNext());
                                        currentNode.getNext().setPrev(currentNode.getPrev());
                                        check();
                                        return;
                                    }else{
                                        //前后都不一样
                                        currentNode.setRed(red);
                                        currentNode.setGreen(green);
                                        currentNode.setBlue(blue);
                                        currentNode.setTimes(1);
                                        check();
                                        return;
                                    }
                                }
                            }
                        }else {
                            //前面没有 只看后面
                            if(red == currentNode.getNext().getRed()){
                                //和后面一样
                                currentNode.setTimes(1+currentNode.getNext().getTimes());
                                currentNode.setRed(red);
                                currentNode.setGreen(green);
                                currentNode.setBlue(blue);
                                currentNode.setNext(currentNode.getNext().getNext());
                                if(currentNode.getNext().getNext()!=null){
                                    currentNode.getNext().getNext().setPrev(currentNode);
                                }
                                check();
                                return;
                            }else{
                                //和后面不一样
                                currentNode.setRed(red);
                                currentNode.setGreen(green);
                                currentNode.setBlue(blue);
                                currentNode.setTimes(1);
                                check();
                                return;
                            }
                        }

                    }
                }
            }else if(times>index){
                //we find it, it is in currentNode
                DlinkedListNode currentNode = i.getPrev();
                if(currentNode.getRed() == red){
                    currentNode.setTimes(currentNode.getTimes()+1);
                    check();
                    return;
                }else{
                    //判断是否是此run的第一个
                    if(index == times-trgb[0]+1){
                        //先看是否为头节点
                        if(index == 1){
                            //是头节点
                            DlinkedListNode temp = new DlinkedListNode(currentNode, currentNode.getNext(), currentNode.getRed(), currentNode.getGreen(), currentNode.getBlue(), currentNode.getTimes()-1);
                            currentNode.getNext().setPrev(temp);
                            currentNode.setNext(temp);
                            currentNode.setRed(red);
                            currentNode.setGreen(green);
                            currentNode.setBlue(blue);
                            currentNode.setTimes(1);
                            check();
                            return;
                        }else{
                            //先看是否和前面一样
                            if(red == currentNode.getPrev().getRed()){
                                //和前面一样 需要合并
                                currentNode.getPrev().setTimes(currentNode.getPrev().getTimes()+1);
                                currentNode.setTimes(currentNode.getTimes()-1);
                                check();
                                return;
                            }else {
                                //和前面不一样
                                //头插一节点
                                DlinkedListNode temp = new DlinkedListNode(currentNode.getPrev(), currentNode, red, green, blue, 1);
                                currentNode.getPrev().setNext(temp);
                                currentNode.setPrev(temp);
                                currentNode.setTimes(currentNode.getTimes()-1);
                                check();
                                return;
                            }
                        }
                    }else{
                        //必然是中间节点 且此run的times大于等于3
                        DlinkedListNode temp1 = new DlinkedListNode(currentNode, null, red, green, blue, 1);
                        DlinkedListNode temp2 = new DlinkedListNode(temp1, currentNode.getNext(), currentNode.getRed(), currentNode.getGreen(), currentNode.getBlue(), times-index);
                        currentNode.getNext().setPrev(temp2);
                        temp1.setNext(temp2);
                        currentNode.setNext(temp1);
                        currentNode.setTimes(index-times+currentNode.getTimes()-1);
                        check();
                        return;
                    }
                }
            }
        } // end while


        times+=i.getTimes();
        if(times == index){
            //we find it
            if(i.getRed() == red){
                //add 1 to time
                i.setTimes(i.getTimes()+1);
                check();
                return;
            }else{
                if(i.getTimes()>1){
                    DlinkedListNode temp = new DlinkedListNode(null, null, red, green, blue, 1);
                    i.setTimes(i.getTimes()-1);
                    i.setNext(temp);
                    temp.setPrev(i.getNode());
                    check();
                    return;
                }else {
                    //只有一个 将要重写 需要判断是否和前面一样
                    if(index==1){
                        //头节点
                        i.getNode().setRed(red);
                        i.getNode().setGreen(green);
                        i.getNode().setBlue(blue);
                        check();
                        return;
                    }else {
                        //非头节点 需要和前面比较
                        if(red == i.getPrev().getRed()){
                            i.getPrev().setTimes(i.getPrev().getTimes()+1);
                            i.getPrev().setNext(null);
                            check();
                            return;
                        }else {
                            //与前面不相等
                            i.getNode().setRed(red);
                            i.getNode().setGreen(green);
                            i.getNode().setBlue(blue);
                            check();
                            return;
                        }
                    }
                }
            }
        }else{
            if(i.getRed() == red){
                i.setTimes(i.getTimes()+1);
                check();
                return;
            }else {
                //判断是否是此run的第一个
                if(index == times-i.getTimes()+1){
                    //先看是否为头节点
                    if(index == 1){
                        //是头节点
                        DlinkedListNode temp = new DlinkedListNode(i.getNode(), i.getNode().getNext(), i.getNode().getRed(), i.getNode().getGreen(), i.getNode().getBlue(), i.getNode().getTimes()-1);
                        if(i.getNode().getNext()!=null)
                            i.getNode().getNext().setPrev(temp);
                        i.setNext(temp);
                        i.getNode().setRed(red);
                        i.getNode().setGreen(green);
                        i.getNode().setBlue(blue);
                        i.setTimes(1);
                        check();
                        return;
                    }else{
                        //先看是否和前面一样
                        if(red == i.getNode().getPrev().getRed()){
                            //和前面一样 需要合并
                            i.getNode().getPrev().setTimes(i.getNode().getPrev().getTimes()+1);
                            i.getNode().setTimes(i.getNode().getTimes()-1);
                            check();
                            return;
                        }else {
                            //和前面不一样
                            //头插一节点
                            DlinkedListNode temp = new DlinkedListNode(i.getNode().getPrev(), i.getNode(), red, green, blue, 1);
                            i.getNode().getPrev().setNext(temp);
                            i.getNode().setPrev(temp);
                            i.getNode().setTimes(i.getNode().getTimes()-1);
                            check();
                            return;
                        }
                    }
                }else{
                    //必然是中间节点 且此run的times大于等于3
                    DlinkedListNode temp1 = new DlinkedListNode(i.getNode(), null, red, green, blue, 1);
                    DlinkedListNode temp2 = new DlinkedListNode(temp1, null, i.getNode().getRed(), i.getNode().getGreen(), i.getNode().getBlue(), times-index);
                    temp1.setNext(temp2);
                    i.getNode().setNext(temp1);
                    i.setTimes(index-times+i.getTimes()-1);
                    check();
                    return;
                }
            }
        }
    }


    /**
     * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
     * You are welcome to add tests, though.  Methods below this point will not
     * be tested.  This is not the autograder, which will be provided separately.
     */


    /**
     * doTest() checks whether the condition is true and prints the given error
     * message if it is not.
     *
     * @param b the condition to check.
     * @param msg the error message to print if the condition is false.
     */
    private static void doTest(boolean b, String msg) {
        if (b) {
            System.out.println("Good.");
        } else {
            System.err.println(msg);
        }
    }

    /**
     * array2PixImage() converts a 2D array of grayscale intensities to
     * a grayscale PixImage.
     *
     * @param pixels a 2D array of grayscale intensities in the range 0...255.
     * @return a new PixImage whose red, green, and blue values are equal to
     * the input grayscale intensities.
     */
    private static PixImage array2PixImage(int[][] pixels) {
        int width = pixels.length;
        int height = pixels[0].length;
        PixImage image = new PixImage(width, height);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                        (short) pixels[x][y]);
            }
        }

        return image;
    }

    /**
     * setAndCheckRLE() sets the given coordinate in the given run-length
     * encoding to the given value and then checks whether the resulting
     * run-length encoding is correct.
     *
     * @param rle the run-length encoding to modify.
     * @param x the x-coordinate to set.
     * @param y the y-coordinate to set.
     * @param intensity the grayscale intensity to assign to pixel (x, y).
     */
    private static void setAndCheckRLE(RunLengthEncoding rle,
                                       int x, int y, int intensity) {
        rle.setPixel(x, y,
                (short) intensity, (short) intensity, (short) intensity);
        rle.check();
    }

    /**
     * main() runs a series of tests of the run-length encoding code.
     */
    public static void main(String[] args) {
        // Be forwarned that when you write arrays directly in Java as below,
        // each "row" of text is a column of your image--the numbers get
        // transposed.
        PixImage image1 = array2PixImage(new int[][] { { 0, 3, 6 },
                { 1, 4, 7 },
                { 2, 5, 8 } });

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                "on a 3x3 image.  Input image:");
        System.out.print(image1);
        RunLengthEncoding rle1 = new RunLengthEncoding(image1);
        rle1.check();
        System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
        doTest(rle1.getWidth() == 3 && rle1.getHeight() == 3,
                "RLE1 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 3x3 encoding.");
        doTest(image1.equals(rle1.toPixImage()),
                "image1 -> RLE1 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 0, 0, 42);
        image1.setPixel(0, 0, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
           /*
                       array2PixImage(new int[][] { { 42, 3, 6 },
                                                    { 1, 4, 7 },
                                                    { 2, 5, 8 } })),
           */
                "Setting RLE1[0][0] = 42 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 1, 0, 42);
        image1.setPixel(1, 0, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[1][0] = 42 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 0, 1, 2);
        image1.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[0][1] = 2 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 0, 0, 0);
        image1.setPixel(0, 0, (short) 0, (short) 0, (short) 0);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[0][0] = 0 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 2, 2, 7);
        image1.setPixel(2, 2, (short) 7, (short) 7, (short) 7);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[2][2] = 7 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 2, 2, 42);
        image1.setPixel(2, 2, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[2][2] = 42 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle1, 1, 2, 42);
        image1.setPixel(1, 2, (short) 42, (short) 42, (short) 42);
        doTest(rle1.toPixImage().equals(image1),
                "Setting RLE1[1][2] = 42 fails.");


        PixImage image2 = array2PixImage(new int[][] { { 2, 3, 5 },
                { 2, 4, 5 },
                { 3, 4, 6 } });

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                "on another 3x3 image.  Input image:");
        System.out.print(image2);
        RunLengthEncoding rle2 = new RunLengthEncoding(image2);
        rle2.check();
        System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
        doTest(rle2.getWidth() == 3 && rle2.getHeight() == 3,
                "RLE2 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 3x3 encoding.");
        doTest(rle2.toPixImage().equals(image2),
                "image2 -> RLE2 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle2, 0, 1, 2);
        image2.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
        doTest(rle2.toPixImage().equals(image2),
                "Setting RLE2[0][1] = 2 fails.");

        System.out.println("Testing setPixel() on a 3x3 encoding.");
        setAndCheckRLE(rle2, 2, 0, 2);
        image2.setPixel(2, 0, (short) 2, (short) 2, (short) 2);
        doTest(rle2.toPixImage().equals(image2),
                "Setting RLE2[2][0] = 2 fails.");


        PixImage image3 = array2PixImage(new int[][] { { 0, 5 },
                { 1, 6 },
                { 2, 7 },
                { 3, 8 },
                { 4, 9 } });

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                "on a 5x2 image.  Input image:");
        System.out.print(image3);
        RunLengthEncoding rle3 = new RunLengthEncoding(image3);
        rle3.check();
        System.out.println("Testing getWidth/getHeight on a 5x2 encoding.");
        doTest(rle3.getWidth() == 5 && rle3.getHeight() == 2,
                "RLE3 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 5x2 encoding.");
        doTest(rle3.toPixImage().equals(image3),
                "image3 -> RLE3 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 5x2 encoding.");
        setAndCheckRLE(rle3, 4, 0, 6);
        image3.setPixel(4, 0, (short) 6, (short) 6, (short) 6);
        doTest(rle3.toPixImage().equals(image3),
                "Setting RLE3[4][0] = 6 fails.");

        System.out.println("Testing setPixel() on a 5x2 encoding.");
        setAndCheckRLE(rle3, 0, 1, 6);
        image3.setPixel(0, 1, (short) 6, (short) 6, (short) 6);
        doTest(rle3.toPixImage().equals(image3),
                "Setting RLE3[0][1] = 6 fails.");

        System.out.println("Testing setPixel() on a 5x2 encoding.");
        setAndCheckRLE(rle3, 0, 0, 1);
        image3.setPixel(0, 0, (short) 1, (short) 1, (short) 1);
        doTest(rle3.toPixImage().equals(image3),
                "Setting RLE3[0][0] = 1 fails.");


        PixImage image4 = array2PixImage(new int[][] { { 0, 3 },
                { 1, 4 },
                { 2, 5 } });

        System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                "on a 3x2 image.  Input image:");
        System.out.print(image4);
        RunLengthEncoding rle4 = new RunLengthEncoding(image4);
        rle4.check();
        System.out.println("Testing getWidth/getHeight on a 3x2 encoding.");
        doTest(rle4.getWidth() == 3 && rle4.getHeight() == 2,
                "RLE4 has wrong dimensions");

        System.out.println("Testing toPixImage() on a 3x2 encoding.");
        doTest(rle4.toPixImage().equals(image4),
                "image4 -> RLE4 -> image does not reconstruct the original image");

        System.out.println("Testing setPixel() on a 3x2 encoding.");
        setAndCheckRLE(rle4, 2, 0, 0);
        image4.setPixel(2, 0, (short) 0, (short) 0, (short) 0);
        doTest(rle4.toPixImage().equals(image4),
                "Setting RLE4[2][0] = 0 fails.");

        System.out.println("Testing setPixel() on a 3x2 encoding.");
        setAndCheckRLE(rle4, 1, 0, 0);
        image4.setPixel(1, 0, (short) 0, (short) 0, (short) 0);
        doTest(rle4.toPixImage().equals(image4),
                "Setting RLE4[1][0] = 0 fails.");

        System.out.println("Testing setPixel() on a 3x2 encoding.");
        setAndCheckRLE(rle4, 1, 0, 1);
        image4.setPixel(1, 0, (short) 1, (short) 1, (short) 1);
        doTest(rle4.toPixImage().equals(image4),
                "Setting RLE4[1][0] = 1 fails.");
    }
}