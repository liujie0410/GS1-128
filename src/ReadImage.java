import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/*
* 将图像二值化
* 返回BufferedImage
* */

public class ReadImage {

/*    public void binaryImage() throws IOException{
        File file = new File("code.png");
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);//重点，技巧在这个参数BufferedImage.TYPE_BYTE_BINARY
        for(int i= 0 ; i < width ; i++){
            for(int j = 0 ; j < height; j++){
                int rgb = image.getRGB(i, j);
                grayImage.setRGB(i, j, rgb);
            }
        }

        File newFile = new File("code_binary.png");
        ImageIO.write(grayImage, "jpg", newFile);
    }*/

    /*
    * 二值化图像函数，返回BufferedImage图像
    * */

    public static BufferedImage binaryImage(BufferedImage image) throws Exception {
        int w = image.getWidth();
        int h = image.getHeight();
        float[] rgb = new float[3];
        double[][] point = new double[w][h];
        int black = new Color(0, 0, 0).getRGB();
        int white = new Color(255, 255, 255).getRGB();
        BufferedImage bi= new BufferedImage(w, h, BufferedImage.TYPE_BYTE_BINARY);

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int pixel = image.getRGB(x, y);
                rgb[0] = (pixel & 0xff0000) >> 16;
                rgb[1] = (pixel & 0xff00) >> 8;
                rgb[2] = (pixel & 0xff);
                float avg = (rgb[0]+rgb[1]+rgb[2])/3;
                point[x][y] = avg;

            }
        }

        //这里是阈值，白底黑字还是黑底白字，大多数情况下建议白底黑字，后面都以白底黑字为例
        double SW = 192;
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                if(getGray(point,x,y,w,h) < SW) {
                    bi.setRGB(x,y,black);
                }else {
                    bi.setRGB(x, y, white);
                }

            }
        }

        return bi;
    }

    //自己加周围8个灰度值再除以9，算出其相对灰度值
    public static double getGray(double[][] point, int x, int y, int w, int h) {
        double rs = point[x][y] + (x == 0 ? 255 : point[x - 1][y]) + (x == 0 || y == 0 ? 255 : point[x - 1][y - 1])
                + (x == 0 || y == h - 1 ? 255 : point[x - 1][y + 1]) + (y == 0 ? 255 : point[x][y - 1])
                + (y == h - 1 ? 255 : point[x][y + 1]) + (x == w - 1 ? 255 : point[x + 1][y])
                + (x == w - 1 || y == 0 ? 255 : point[x + 1][y - 1])
                + (x == w - 1 || y == h - 1 ? 255 : point[x + 1][y + 1]);

        return rs / 9;

    }

    /*
    * 得到灰度图像函数，未使用
    * */
    public void grayImage() throws IOException{
        File file = new File("code.png");
        BufferedImage image = ImageIO.read(file);

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);//重点，技巧在这个参数BufferedImage.TYPE_BYTE_GRAY
        for(int i= 0 ; i < width ; i++){
            for(int j = 0 ; j < height; j++){
                int rgb = image.getRGB(i, j);
                grayImage.setRGB(i, j, rgb);
            }
        }

        File newFile = new File("code_gray.png");
        ImageIO.write(grayImage, "jpg", newFile);
    }

    /*public static void main(String[] args) throws Exception {

        //测试
        ReadImage demo = new ReadImage();
        File file = new File("code1.png");
        BufferedImage image = ImageIO.read(file);
        BufferedImage back = demo.binaryImage(image);
        ImageIO.write(back, "jpg", new File("code1_test_binary.jpg"));


//        demo.grayImage();
    }*/

}