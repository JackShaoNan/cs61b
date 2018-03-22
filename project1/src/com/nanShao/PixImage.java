package com.nanShao;
/* PixImage.java */

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 *
 *  All methods in this class must be implemented to complete Part I.
 *  See the README file accompanying this project for additional details.
 */

public class PixImage {

    /**
     *  Define any variables associated with a PixImage object here.  These
     *  variables MUST be private.
     */
    private int width;
    private int height;
    private short[][] color_r;
    private short[][] color_g;
    private short[][] color_b;




    /**
     * PixImage() constructs an empty PixImage with a specified width and height.
     * Every pixel has red, green, and blue intensities of zero (solid black).
     *
     * @param width the width of the image.
     * @param height the height of the image.
     */
    public PixImage(int width, int height) {
        // Your solution here.
        this.width = width;
        this.height = height;
        color_b = new short[width][height];
        color_r = new short[width][height];
        color_g = new short[width][height];
    }

    /**
     * getWidth() returns the width of the image.
     *
     * @return the width of the image.
     */
    public int getWidth() {
        // Replace the following line with your solution.
        return width;
    }

    /**
     * getHeight() returns the height of the image.
     *
     * @return the height of the image.
     */
    public int getHeight() {
        // Replace the following line with your solution.
        return height;
    }

    /**
     * getRed() returns the red intensity of the pixel at coordinate (x, y).
     *
     * @param x the x-coordinate of the pixel.
     * @param y the y-coordinate of the pixel.
     * @return the red intensity of the pixel at coordinate (x, y).
     */
    public short getRed(int x, int y) {
        // Replace the following line with your solution.
        if(x<width && y<height && x>=0 && y>=0)
            return color_r[x][y];
        else
            return -1;
    }

    /**
     * getGreen() returns the green intensity of the pixel at coordinate (x, y).
     *
     * @param x the x-coordinate of the pixel.
     * @param y the y-coordinate of the pixel.
     * @return the green intensity of the pixel at coordinate (x, y).
     */
    public short getGreen(int x, int y) {
        // Replace the following line with your solution.
        if(x<width && y<height && x>=0 && y>=0)
            return color_g[x][y];
        else
            return -1;
    }

    /**
     * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
     *
     * @param x the x-coordinate of the pixel.
     * @param y the y-coordinate of the pixel.
     * @return the blue intensity of the pixel at coordinate (x, y).
     */
    public short getBlue(int x, int y) {
        // Replace the following line with your solution.
        if(x<width && y<height && x>=0 && y>=0)
            return color_b[x][y];
        else
            return -1;
    }

    /**
     * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
     * and blue intensities.
     *
     * If any of the three color intensities is NOT in the range 0...255, then
     * this method does NOT change any of the pixel intensities.
     *
     * @param x the x-coordinate of the pixel.
     * @param y the y-coordinate of the pixel.
     * @param red the new red intensity for the pixel at coordinate (x, y).
     * @param green the new green intensity for the pixel at coordinate (x, y).
     * @param blue the new blue intensity for the pixel at coordinate (x, y).
     */
    public void setPixel(int x, int y, short red, short green, short blue) {
        // Your solution here.
        if(x<width && y<height && x>=0 && y>=0){
            if(red>=0 && red<=255){
                if(green>=0 && green<=255){
                    if(blue>=0 && blue<=255){
                        color_r[x][y] = red;
                        color_g[x][y] = green;
                        color_b[x][y] = blue;
                    }
                }
            }
        }
    }

    /**
     * toString() returns a String representation of this PixImage.
     *
     * This method isn't required, but it should be very useful to you when
     * you're debugging your code.  It's up to you how you represent a PixImage
     * as a String.
     *
     * @return a String representation of this PixImage.
     */
    public String toString() {
        // Replace the following line with your solution.
        String result = "[";
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                result += " "+ color_r[x][y];
            }
        }
        result += "]";
        return result;
    }

    /**
     * boxBlur() returns a blurred version of "this" PixImage.
     *
     * If numIterations == 1, each pixel in the output PixImage is assigned
     * a value equal to the average of its neighboring pixels in "this" PixImage,
     * INCLUDING the pixel itself.
     *
     * A pixel not on the image boundary has nine neighbors--the pixel itself and
     * the eight pixels surrounding it.  A pixel on the boundary has six
     * neighbors if it is not a corner pixel; only four neighbors if it is
     * a corner pixel.  The average of the neighbors is the sum of all the
     * neighbor pixel values (including the pixel itself) divided by the number
     * of neighbors, with non-integer quotients rounded toward zero (as Java does
     * naturally when you divide two integers).
     *
     * Each color (red, green, blue) is blurred separately.  The red input should
     * have NO effect on the green or blue outputs, etc.
     *
     * The parameter numIterations specifies a number of repeated iterations of
     * box blurring to perform.  If numIterations is zero or negative, "this"
     * PixImage is returned (not a copy).  If numIterations is positive, the
     * return value is a newly constructed PixImage.
     *
     * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
     * appear in the new, output PixImage only.
     *
     * @param numIterations the number of iterations of box blurring.
     * @return a blurred version of "this" PixImage.
     */
    // 默认图像不是1*1
    public PixImage boxBlur(int numIterations) {
        // Replace the following line with your solution.
        if(numIterations<=0)
            return this;
        else{
            return boxBlurRGB(this, numIterations);
        }
    }

    //helper func: blur the r g b
    private PixImage boxBlurRGB(PixImage img, int numIterations){
        PixImage[] temp = new PixImage[numIterations+1];
        temp[0] = img;
        for(int i=1; i<numIterations+1; i++){
            temp[i] = new PixImage(img.getWidth(), img.getHeight());
        }
        for(int i=1; i< numIterations+1; i++){
            averageBlur(temp[i-1],temp[i]);
        }
        return temp[numIterations];
    }

    //helper func: blur rgb
    private void averageBlur(PixImage img, PixImage newImg){
        int width = img.getWidth();
        int height = img.getHeight();
        short red;
        short green;
        short blue;
        for(int x=0; x<width; x++){
            for(int y=0; y<height; y++){
                //upper boundary point
                if(y==0){
                    if(x==0){
                        //left corner point
                        red  = (short)((img.getRed(x,y)+img.getRed(x+1,y+1)+img.getRed(x,y+1)+img.getRed(x+1,y))/4);
                        green  = (short)((img.getGreen(x,y)+img.getGreen(x+1,y+1)+img.getGreen(x,y+1)+img.getGreen(x+1,y))/4);
                        blue  = (short)((img.getBlue(x,y)+img.getBlue(x+1,y+1)+img.getBlue(x,y+1)+img.getBlue(x+1,y))/4);
                        newImg.setPixel(x,y,red,green,blue);
                    }else if(x==width-1){
                        // right corner point
                        red  = (short)((img.getRed(x,y)+img.getRed(x-1,y+1)+img.getRed(x,y+1)+img.getRed(x-1,y))/4);
                        green  = (short)((img.getGreen(x,y)+img.getGreen(x-1,y+1)+img.getGreen(x,y+1)+img.getGreen(x-1,y))/4);
                        blue  = (short)((img.getBlue(x,y)+img.getBlue(x-1,y+1)+img.getBlue(x,y+1)+img.getBlue(x-1,y))/4);
                        newImg.setPixel(x,y,red,green,blue);
                    }else{
                        //in the middle of upper boundary
                        red  = (short)((img.getRed(x,y+1)+img.getRed(x+1,y+1)+img.getRed(x,y)+img.getRed(x+1,y)+img.getRed(x-1,y)+img.getRed(x-1,y+1))/6);
                        green  = (short)((img.getGreen(x,y+1)+img.getGreen(x+1,y+1)+img.getGreen(x,y)+img.getGreen(x+1,y)+img.getGreen(x-1,y)+img.getGreen(x-1,y+1))/6);
                        blue  = (short)((img.getBlue(x,y+1)+img.getBlue(x+1,y+1)+img.getBlue(x,y)+img.getBlue(x+1,y)+img.getBlue(x-1,y)+img.getBlue(x-1,y+1))/6);
                        newImg.setPixel(x,y,red,green,blue);
                    }
                }else if(y==height-1){
                    //in the bottom boundary
                    if(x==0){
                        //left corner point
                        red  = (short)((img.getRed(x,y)+img.getRed(x+1,y-1)+img.getRed(x,y-1)+img.getRed(x+1,y))/4);
                        green  = (short)((img.getGreen(x,y)+img.getGreen(x+1,y-1)+img.getGreen(x,y-1)+img.getGreen(x+1,y))/4);
                        blue  = (short)((img.getBlue(x,y)+img.getBlue(x+1,y-1)+img.getBlue(x,y-1)+img.getBlue(x+1,y))/4);
                        newImg.setPixel(x,y,red,green,blue);
                    }else if(x==width-1){
                        // right corner point
                        red  = (short)((img.getRed(x,y)+img.getRed(x-1,y-1)+img.getRed(x,y-1)+img.getRed(x-1,y))/4);
                        green  = (short)((img.getGreen(x,y)+img.getGreen(x-1,y-1)+img.getGreen(x,y-1)+img.getGreen(x-1,y))/4);
                        blue  = (short)((img.getBlue(x,y)+img.getBlue(x-1,y-1)+img.getBlue(x,y-1)+img.getBlue(x-1,y))/4);
                        newImg.setPixel(x,y,red,green,blue);
                    }else{
                        //in the middle of upper boundary
                        red  = (short)((img.getRed(x,y-1)+img.getRed(x+1,y-1)+img.getRed(x,y)+img.getRed(x+1,y)+img.getRed(x-1,y)+img.getRed(x-1,y-1))/6);
                        green  = (short)((img.getGreen(x,y-1)+img.getGreen(x+1,y-1)+img.getGreen(x,y)+img.getGreen(x+1,y)+img.getGreen(x-1,y)+img.getGreen(x-1,y-1))/6);
                        blue  = (short)((img.getBlue(x,y-1)+img.getBlue(x+1,y-1)+img.getBlue(x,y)+img.getBlue(x+1,y)+img.getBlue(x-1,y)+img.getBlue(x-1,y-1))/6);
                        newImg.setPixel(x,y,red,green,blue);
                    }
                }else if(x==0){
                    //left border
                    red  = (short)((img.getRed(x,y-1)+img.getRed(x+1,y-1)+img.getRed(x,y)+img.getRed(x+1,y)+img.getRed(x+1,y+1)+img.getRed(x,y+1))/6);
                    green  = (short)((img.getGreen(x,y-1)+img.getGreen(x+1,y-1)+img.getGreen(x,y)+img.getGreen(x+1,y)+img.getGreen(x+1,y+1)+img.getGreen(x,y+1))/6);
                    blue  = (short)((img.getBlue(x,y-1)+img.getBlue(x+1,y-1)+img.getBlue(x,y)+img.getBlue(x+1,y)+img.getBlue(x+1,y+1)+img.getBlue(x,y+1))/6);
                    newImg.setPixel(x,y,red,green,blue);
                }else if(x==width-1){
                    //right border
                    red  = (short)((img.getRed(x,y-1)+img.getRed(x-1,y-1)+img.getRed(x,y)+img.getRed(x-1,y)+img.getRed(x,y+1)+img.getRed(x-1,y+1))/6);
                    green  = (short)((img.getGreen(x,y-1)+img.getGreen(x-1,y-1)+img.getGreen(x,y)+img.getGreen(x-1,y)+img.getGreen(x,y+1)+img.getGreen(x-1,y+1))/6);
                    blue  = (short)((img.getBlue(x,y-1)+img.getBlue(x-1,y-1)+img.getBlue(x,y)+img.getBlue(x-1,y)+img.getBlue(x,y+1)+img.getBlue(x-1,y+1))/6);
                    newImg.setPixel(x,y,red,green,blue);
                }else{
                    //in the middle part of the img
                    red  = (short)((img.getRed(x+1,y-1)+img.getRed(x+1,y)+img.getRed(x+1,y+1)+img.getRed(x,y-1)+img.getRed(x,y+1)+img.getRed(x,y)+img.getRed(x-1,y-1)+img.getRed(x-1,y)+img.getRed(x-1,y+1))/9);
                    green  = (short)((img.getGreen(x+1,y-1)+img.getGreen(x+1,y)+img.getGreen(x+1,y+1)+img.getGreen(x,y-1)+img.getGreen(x,y+1)+img.getGreen(x,y)+img.getGreen(x-1,y-1)+img.getGreen(x-1,y)+img.getGreen(x-1,y+1))/9);
                    blue  = (short)((img.getBlue(x+1,y-1)+img.getBlue(x+1,y)+img.getBlue(x+1,y+1)+img.getBlue(x,y-1)+img.getBlue(x,y+1)+img.getBlue(x,y)+img.getBlue(x-1,y-1)+img.getBlue(x-1,y)+img.getBlue(x-1,y+1))/9);
                    newImg.setPixel(x,y,red,green,blue);
                }
            }
        }

    }

    /**
     * mag2gray() maps an energy (squared vector magnitude) in the range
     * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
     * is logarithmic, but shifted so that values of 5,080 and below map to zero.
     *
     * DO NOT CHANGE THIS METHOD.  If you do, you will not be able to get the
     * correct images and pass the autograder.
     *
     * @param mag the energy (squared vector magnitude) of the pixel whose
     * intensity we want to compute.
     * @return the intensity of the output pixel.
     */
    private static short mag2gray(long mag) {
        short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

        // Make sure the returned intensity is in the range 0...255, regardless of
        // the input value.
        if (intensity < 0) {
            intensity = 0;
        } else if (intensity > 255) {
            intensity = 255;
        }
        return intensity;
    }

    /**
     * sobelEdges() applies the Sobel operator, identifying edges in "this"
     * image.  The Sobel operator computes a magnitude that represents how
     * strong the edge is.  We compute separate gradients for the red, blue, and
     * green components at each pixel, then sum the squares of the three
     * gradients at each pixel.  We convert the squared magnitude at each pixel
     * into a grayscale pixel intensity in the range 0...255 with the logarithmic
     * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
     * pixel intensities reflect the strength of the edges.
     *
     * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
     *
     * @return a grayscale PixImage representing the edges of the input image.
     * Whiter pixels represent stronger edges.
     */
    public PixImage sobelEdges() {
        // Replace the following line with your solution.
        // Don't forget to use the method mag2gray() above to convert energies to
        // pixel intensities.
        PixImage result = new PixImage(this.getWidth(), this.getHeight());
        int[][] gx_red = new int[this.getWidth()][this.getHeight()];
        int[][] gy_red = new int[this.getWidth()][this.getHeight()];
        int[][] gx_green = new int[this.getWidth()][this.getHeight()];
        int[][] gy_green = new int[this.getWidth()][this.getHeight()];
        int[][] gx_blue = new int[this.getWidth()][this.getHeight()];
        int[][] gy_blue = new int[this.getWidth()][this.getHeight()];
        // calculate gx gy
        calculateG(gx_red,gx_green,gx_blue,gy_red,gy_green,gy_blue);
        //calculate gray edge
        short[][] gray =  calculateGray(gx_red,gx_green,gx_blue,gy_red,gy_green,gy_blue);
        PixImage edge = new PixImage(width,height);
        for(int x=0; x<width; x++){
            for(int y=0; y<height; y++){
                edge.setPixel(x,y,gray[x][y], gray[x][y], gray[x][y]);
            }
        }
        return edge;
    }

    //helper func: calculate gx gy
    private void calculateG(int[][] gx_red, int[][] gx_green, int[][] gx_blue, int[][] gy_red, int[][] gy_green, int[][] gy_blue){
        for(int x=0; x<this.getWidth(); x++){
            for(int y=0; y<this.getHeight(); y++){
                if(y == 0){
                    //upper border
                    if(x == 0){
                        //left corner
                        gx_red[x][y] = 3*color_r[x][y]+color_r[x][y+1]-3*color_r[x+1][y]-color_r[x+1][y+1];
                        gx_green[x][y] = 3*color_g[x][y]+color_g[x][y+1]-3*color_g[x+1][y]-color_g[x+1][y+1];
                        gx_blue[x][y] = 3*color_b[x][y]+color_b[x][y+1]-3*color_b[x+1][y]-color_b[x+1][y+1];

                        gy_red[x][y] = 3*color_r[x][y] +color_r[x+1][y]-3*color_r[x][y+1]-color_r[x+1][y+1];
                        gy_green[x][y] = 3*color_g[x][y] +color_g[x+1][y]-3*color_g[x][y+1]-color_g[x+1][y+1];
                        gy_blue[x][y] = 3*color_b[x][y] +color_b[x+1][y]-3*color_b[x][y+1]-color_b[x+1][y+1];
                    }else if(x == width-1){
                        //right corner
                        gx_red[x][y] = 3*color_r[x-1][y]+color_r[x-1][y+1]-3*color_r[x][y]-color_r[x][y+1];
                        gx_green[x][y] = 3*color_g[x-1][y]+color_g[x-1][y+1]-3*color_g[x][y]-color_g[x][y+1];
                        gx_blue[x][y] = 3*color_b[x-1][y]+color_b[x-1][y+1]-3*color_b[x][y]-color_b[x][y+1];

                        gy_red[x][y] = color_r[x-1][y]+3*color_r[x][y]-color_r[x-1][y+1]-3*color_r[x][y+1];
                        gy_green[x][y] = color_g[x-1][y]+3*color_g[x][y]-color_g[x-1][y+1]-3*color_g[x][y+1];
                        gy_blue[x][y] = color_b[x-1][y]+3*color_b[x][y]-color_b[x-1][y+1]-3*color_b[x][y+1];
                    }else{
                        gx_red[x][y] = 3*color_r[x-1][y]+color_r[x-1][y+1]-3*color_r[x+1][y]-color_r[x+1][y+1];
                        gx_green[x][y] = 3*color_g[x-1][y]+color_g[x-1][y+1]-3*color_g[x+1][y]-color_g[x+1][y+1];
                        gx_blue[x][y] = 3*color_b[x-1][y]+color_b[x-1][y+1]-3*color_b[x+1][y]-color_b[x+1][y+1];

                        gy_red[x][y] = color_r[x-1][y]+2*color_r[x][y]+color_r[x+1][y]-color_r[x-1][y+1]-2*color_r[x][y+1]-color_r[x+1][y+1];
                        gy_green[x][y] = color_g[x-1][y]+2*color_g[x][y]+color_g[x+1][y]-color_g[x-1][y+1]-2*color_g[x][y+1]-color_g[x+1][y+1];
                        gy_blue[x][y] = color_b[x-1][y]+2*color_b[x][y]+color_b[x+1][y]-color_b[x-1][y+1]-2*color_b[x][y+1]-color_b[x+1][y+1];
                    }
                }else if(y == height-1){
                    //bottom border
                    if(x == 0){
                        gx_red[x][y] = color_r[x][y-1]+3*color_r[x][y]-color_r[x+1][y-1]-3*color_r[x+1][y];
                        gx_green[x][y] = color_g[x][y-1]+3*color_g[x][y]-color_g[x+1][y-1]-3*color_g[x+1][y];
                        gx_blue[x][y] = color_b[x][y-1]+3*color_b[x][y]-color_b[x+1][y-1]-3*color_b[x+1][y];

                        gy_red[x][y] = 3*color_r[x][y-1]+color_r[x+1][y-1]-3*color_r[x][y]-color_r[x+1][y];
                        gy_green[x][y] = 3*color_g[x][y-1]+color_g[x+1][y-1]-3*color_g[x][y]-color_g[x+1][y];
                        gy_blue[x][y] = 3*color_b[x][y-1]+color_b[x+1][y-1]-3*color_b[x][y]-color_b[x+1][y];
                    }else if(x == width-1){
                        gx_red[x][y] = color_r[x-1][y-1]+3*color_r[x-1][y]-color_r[x][y-1]-3*color_r[x][y];
                        gx_green[x][y] = color_g[x-1][y-1]+3*color_g[x-1][y]-color_g[x][y-1]-3*color_g[x][y];
                        gx_blue[x][y] = color_b[x-1][y-1]+3*color_b[x-1][y]-color_b[x][y-1]-3*color_b[x][y];

                        gy_red[x][y] = color_r[x-1][y-1]+3*color_r[x][y-1]-color_r[x-1][y]-3*color_r[x][y];
                        gy_green[x][y] = color_g[x-1][y-1]+3*color_g[x][y-1]-color_g[x-1][y]-3*color_g[x][y];
                        gy_blue[x][y] = color_b[x-1][y-1]+3*color_b[x][y-1]-color_b[x-1][y]-3*color_b[x][y];
                    }else {
                        gx_red[x][y] = color_r[x-1][y-1]+3*color_r[x-1][y]-color_r[x+1][y-1]-3*color_r[x+1][y];
                        gx_green[x][y] = color_g[x-1][y-1]+3*color_g[x-1][y]-color_g[x+1][y-1]-3*color_g[x+1][y];
                        gx_blue[x][y] = color_b[x-1][y-1]+3*color_b[x-1][y]-color_b[x+1][y-1]-3*color_b[x+1][y];

                        gy_red[x][y] = color_r[x-1][y-1]+2*color_r[x][y-1]+color_r[x+1][y-1]-color_r[x-1][y]-2*color_r[x][y]-color_r[x+1][y];
                        gy_green[x][y] = color_g[x-1][y-1]+2*color_g[x][y-1]+color_g[x+1][y-1]-color_g[x-1][y]-2*color_g[x][y]-color_g[x+1][y];
                        gy_blue[x][y] = color_b[x-1][y-1]+2*color_b[x][y-1]+color_b[x+1][y-1]-color_b[x-1][y]-2*color_b[x][y]-color_b[x+1][y];
                    }
                }else{
                    if(x == 0){
                        //left border
                        gx_red[x][y] = color_r[x][y-1]+2*color_r[x][y]+color_r[x][y+1]-color_r[x+1][y-1]-2*color_r[x+1][y]-color_r[x+1][y+1];
                        gx_green[x][y] = color_g[x][y-1]+2*color_g[x][y]+color_g[x][y+1]-color_g[x+1][y-1]-2*color_g[x+1][y]-color_g[x+1][y+1];
                        gx_blue[x][y] = color_b[x][y-1]+2*color_b[x][y]+color_b[x][y+1]-color_b[x+1][y-1]-2*color_b[x+1][y]-color_b[x+1][y+1];

                        gy_red[x][y] = 3*color_r[x][y-1]+color_r[x+1][y-1]-3*color_r[x][y+1]-color_r[x+1][y+1];
                        gy_green[x][y] = 3*color_g[x][y-1]+color_g[x+1][y-1]-3*color_g[x][y+1]-color_g[x+1][y+1];
                        gy_blue[x][y] = 3*color_b[x][y-1]+color_b[x+1][y-1]-3*color_b[x][y+1]-color_b[x+1][y+1];
                    }else if(x == width-1){
                        //right border
                        gx_red[x][y] = color_r[x-1][y-1]+2*color_r[x-1][y]+color_r[x-1][y+1]-color_r[x][y-1]-2*color_r[x][y]-color_r[x][y+1];
                        gx_green[x][y] = color_g[x-1][y-1]+2*color_g[x-1][y]+color_g[x-1][y+1]-color_g[x][y-1]-2*color_g[x][y]-color_g[x][y+1];
                        gx_blue[x][y] = color_b[x-1][y-1]+2*color_b[x-1][y]+color_b[x-1][y+1]-color_b[x][y-1]-2*color_b[x][y]-color_b[x][y+1];

                        gy_red[x][y] = color_r[x-1][y-1]+3*color_r[x][y-1]-color_r[x-1][y+1]-3*color_r[x][y+1];
                        gy_green[x][y] = color_g[x-1][y-1]+3*color_g[x][y-1]-color_g[x-1][y+1]-3*color_g[x][y+1];
                        gy_blue[x][y] = color_b[x-1][y-1]+3*color_b[x][y-1]-color_b[x-1][y+1]-3*color_b[x][y+1];
                    }else {
                        //middle part
                        gx_red[x][y] = color_r[x-1][y-1]+2*color_r[x-1][y]+color_r[x-1][y+1]-color_r[x+1][y-1]-2*color_r[x+1][y]-color_r[x+1][y+1];
                        gx_green[x][y] = color_g[x-1][y-1]+2*color_g[x-1][y]+color_g[x-1][y+1]-color_g[x+1][y-1]-2*color_g[x+1][y]-color_g[x+1][y+1];
                        gx_blue[x][y] = color_b[x-1][y-1]+2*color_b[x-1][y]+color_b[x-1][y+1]-color_b[x+1][y-1]-2*color_b[x+1][y]-color_b[x+1][y+1];

                        gy_red[x][y] = color_r[x-1][y-1]+2*color_r[x][y-1]+color_r[x+1][y-1]-color_r[x-1][y+1]-2*color_r[x][y+1]-color_r[x+1][y+1];
                        gy_green[x][y] = color_g[x-1][y-1]+2*color_g[x][y-1]+color_g[x+1][y-1]-color_g[x-1][y+1]-2*color_g[x][y+1]-color_g[x+1][y+1];
                        gy_blue[x][y] = color_b[x-1][y-1]+2*color_b[x][y-1]+color_b[x+1][y-1]-color_b[x-1][y+1]-2*color_b[x][y+1]-color_b[x+1][y+1];
                    }
                }
            }//end inner for loop
        }//end outside for loop
    }


    //helper func: calculate gray intensities
    private short[][] calculateGray(int[][] gx_red, int[][] gx_green, int[][] gx_blue, int[][] gy_red, int[][] gy_green, int[][] gy_blue){
        long[][] energy = new long[width][height];
        short[][] gray = new short[width][height];
        for(int x=0; x<width; x++){
            for(int y=0; y<height; y++){
                energy[x][y] = gx_red[x][y]*gx_red[x][y]+gy_red[x][y]*gy_red[x][y]+gx_green[x][y]*gx_green[x][y]+gy_green[x][y]*gy_green[x][y]+gx_blue[x][y]*gx_blue[x][y]+gy_blue[x][y]*gy_blue[x][y];
                gray[x][y] = mag2gray(energy[x][y]);
            }
        }
        return gray;
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
     * equals() checks whether two images are the same, i.e. have the same
     * dimensions and pixels.
     *
     * @param image a PixImage to compare with "this" PixImage.
     * @return true if the specified PixImage is identical to "this" PixImage.
     */
    public boolean equals(PixImage image) {
        int width = getWidth();
        int height = getHeight();

        if (image == null ||
                width != image.getWidth() || height != image.getHeight()) {
            return false;
        }

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (! (getRed(x, y) == image.getRed(x, y) &&
                        getGreen(x, y) == image.getGreen(x, y) &&
                        getBlue(x, y) == image.getBlue(x, y))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * main() runs a series of tests to ensure that the convolutions (box blur
     * and Sobel) are correct.
     */
    public static void main(String[] args) {
        // Be forwarned that when you write arrays directly in Java as below,
        // each "row" of text is a column of your image--the numbers get
        // transposed.
        PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                { 30, 120, 250 },
                { 80, 250, 255 } });
        System.out.println("Testing getWidth/getHeight on a 3x3 image.  " +
                "Input image:");
        System.out.print(image1);
        doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
                "Incorrect image width and height.");

        System.out.println("Testing blurring on a 3x3 image.");
        doTest(image1.boxBlur(1).equals(
                array2PixImage(new int[][] { { 40, 108, 155 },
                        { 81, 137, 187 },
                        { 120, 164, 218 } })),
                "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));
        doTest(image1.boxBlur(2).equals(
                array2PixImage(new int[][] { { 91, 118, 146 },
                        { 108, 134, 161 },
                        { 125, 151, 176 } })),
                "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));
        doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
                "Incorrect box blur (1 rep + 1 rep):\n" +
                        image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

        System.out.println("Testing edge detection on a 3x3 image.");
        doTest(image1.sobelEdges().equals(
                array2PixImage(new int[][] { { 104, 189, 180 },
                        { 160, 193, 157 },
                        { 166, 178, 96 } })),
                "Incorrect Sobel:\n" + image1.sobelEdges());


        PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
                { 0, 0, 100 } });
        System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                "Input image:");
        System.out.print(image2);
        doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
                "Incorrect image width and height.");

        System.out.println("Testing blurring on a 2x3 image.");
        doTest(image2.boxBlur(1).equals(
                array2PixImage(new int[][] { { 25, 50, 75 },
                        { 25, 50, 75 } })),
                "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));

        System.out.println("Testing edge detection on a 2x3 image.");
        doTest(image2.sobelEdges().equals(
                array2PixImage(new int[][] { { 122, 143, 74 },
                        { 74, 143, 122 } })),
                "Incorrect Sobel:\n" + image2.sobelEdges());
    }
}