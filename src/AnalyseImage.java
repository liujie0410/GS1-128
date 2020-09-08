import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/*
* 分析图像
* 存储条/空数
* analyseImage()：分析图片，找到第一个黑色像素出现的坐标，从该坐标开始识别条/空
* accessPlace(BufferedImage image,int y,int x)：识别条/空数
* matchChar(int[] moduleStandard)：从数据库中匹配字符
* searchMin(int[] array)：寻找标准宽度
*
* */

public class AnalyseImage {
    private static int black = new Color(0, 0, 0).getRGB();//条的色素值=-16777216
    private static int white = new Color(255, 255, 255).getRGB();//空的色素值=-1
    private static int[] moduleNum = new int[200];//存储单元宽度模块数
    private static int[] moduleType = new int[100];//存放像素列的类型：黑/白
    private static boolean flag = true;//用于第一次判断求StandardWidth的标志
    private static int standardWidth;
    static int k;

    public static String[] analyseImage(File file ) throws Exception{ //file为带入的图片地址

       // File file = new File("code1.png");

        BufferedImage image_0 = ImageIO.read(file);
        ImageIO.write(ReadImage.binaryImage(image_0),"png",new File("code1_binary.png"));//生成二值化后的图像文件

        BufferedImage image = ReadImage.binaryImage(image_0);//调用类ReadImage的函数得到二值化图像
        int width = image.getWidth();//width = 632
        int height = image.getHeight();//height = 152
        String[] arr = new String[k+1];
        for (int y = 0 ; y < height ; y++)
        {
            for (int x = 0 ; x < width; x++)
            {
                //横向扫描，遍历图像像素，一次遇到黑色像素时跳转调用函数，坐标为height=15，width=18
                if (image.getRGB(x,y) == black )
                {
                    arr=accessPlace(image,y,x);
                    for (int i = 0 ; i <=k ; i++){
                        System.out.println("arr["+ i +"] : " + arr[i]);
                    }
                    return arr;
                }
            }
        }
        return arr;
}

    /*
    * y:纵坐标
    * x:横坐标
    * */

    public static String[] accessPlace(BufferedImage image,int y,int x) {

        int currentColor = black;//当前像素颜色
        int count = 0;//像素颜色跳转前计数的像素列
        int index_count = 0;//像素跳转次数，用于数组索引
        int rightBoundary = image.getWidth();//图像有效右边界
        int bottomBoundary = image.getHeight()*2/3;//下边界，采取图片的2/3高度

        /*
        * 循环寻找图像有效的右边界即出现黑色像素的位置
        * rightBoundary：右边界
        * 加2是因为需要判断是否产生了黑白跳转，有跳转才结束
        * */

        int y_right = y;
        for (int x_right = image.getWidth()-1 ; x_right > x ; x_right--){
                if (image.getRGB(x_right,y_right) == black ){
                    rightBoundary = x_right + 2;
                    break;
                }
            }

        //此处为纵向扫描，像素值较多的类型存为当前像素颜色

        moduleType[0] = currentColor;
        int special =0;
        for (int i = x ; i < rightBoundary  ; i++){

            int blackNum = 0;//黑色像素数目
            int whiteNum = 0;//白色像素数目

            for (int j = y ; j < bottomBoundary ; j++){
                if (image.getRGB(i,j) == black){
                    blackNum += 1;
                }else {
                    whiteNum += 1;

                    /*
                    * 根据图片的特点，发现二值化后，像素列第一个像素为白色，往后像素为黑色，
                    * 但是该列按理说应该是白色像素列，因此判断第一个像素点为白色时，该列即为白色像素列
                    * */
                    if (j == y){
                        currentColor = white;
                        if (image.getRGB(i,j+2) == black){
                            special++;
                            System.out.println("special: " + special);//测试
                        }
                        break;
                    }
                }
            }
            System.out.println("blackNum : " + blackNum + " whiteNum ：" + whiteNum);//测试
            currentColor = blackNum > whiteNum ? black : white;
            System.out.println("currentColor: " + currentColor);//测试
            if (count == 0){
                count++;
            }else if ( currentColor == moduleType[count-1]){
                moduleType[count] = currentColor;
                count++;
            }else {
                if(index_count!=0) {
                    count = count + 1;//count从0开始
                }
                if(special > 1){
                    count--;
                }

                /*
                * count计算出来存在误差，在特定边界内的赋值为标准宽度的倍数，
                * 此处前提是通过图片知道了标准宽度是3个像素
                * */
           //     if (count >= 1 && count <= 4){
           //         count = 3;
           //     }else if (count >= 5 && count <=7){
           //         count = 6;
           //     }else if (count >= 8 && count <= 10){
           //         count = 9;
           //     }else {
           //         count = 12;
            //    }
                moduleNum[index_count] = count;
                index_count++;
                count = 0;
                special=0;
                moduleType[count] = currentColor;
            }
        }
        String[] arr = new String[k+1];
        arr=matchChar(moduleNum);//调用字符匹配函数
        return arr;
    }

    /*
    * 数组与数据库字符匹配函数
    * int moduleNum_6[]传入的模块条空数（未转换标准宽度）
    * String content[]用于保存返回的实际字符内容
    * */
    public static String[] matchChar(int[] moduleStandard) {

        if(flag){
           // standardWidth = searchMin(moduleStandard);//寻找数组中最小的宽度作为标准宽度,第一次才需要判断，其他不需要
            standardWidth=moduleStandard[0]/2;
            flag = false;
        }

        //以标准宽度为依据，重置数组
        for (int i = 0 ; i < moduleStandard.length ; i++){
            if (moduleStandard[i] >= standardWidth - 1 && moduleStandard[i] <= 2*standardWidth - 2) {
                moduleStandard[i] = 1;
            }else if (moduleStandard[i] >= 2*standardWidth - 1 && moduleStandard[i] <= 3*standardWidth - 2){
                moduleStandard[i] = 2;
            }else if (moduleStandard[i] >= 3*standardWidth - 1 && moduleStandard[i] <= 4*standardWidth - 2){
                moduleStandard[i] = 3;
            }else if (moduleStandard[i] >= 4*standardWidth - 1 && moduleStandard[i] <= 5*standardWidth ){
                moduleStandard[i] = 4;
            }else {
                moduleStandard[i] = 0;
            }

        }

     //  for (int i = 0 ; i < moduleStandard.length ; i++){
     //       moduleStandard[i] = moduleStandard[i]/standardWidth;//将数组中的模块条/空数设为标准宽度的倍数以便和数据库的内容比对
     //   }

        //测试
        System.out.println("standard: "+standardWidth);
        
        //删去数组后面所有的0       
        k = moduleStandard.length-1;
        while(moduleStandard[k]==0)
        {
        	k--;
        }
        String[] arr = new String[k+1];
        for(int a=0;a<=k;a++)
        	arr[a] = String.valueOf(moduleStandard[a]);
        /*for (int i = 0 ; i <=k ; i++){
            System.out.println("arr["+ i +"] : " + arr[i]);
        }*/
        System.out.println(arr.length);
        
        ///////////////////////////////////////////////////////////////////////
        return arr;

        /*

        * 此处开始利用数组去匹配数据库内容
        * int moduleStandard[]存放的是模块标准条空数
        * String content[]用于保存实际字符内容
        *
        * */
    }

    /*
    * 得到最小宽度作为标准宽度函数
    * */
    public static int searchMin(int[] array){

        int standardWidth = array[0];//standardWidth标准宽度

        for(int i = 0 ; i < 6 ; i++){
            if (array[i] < standardWidth){
                standardWidth = array[i];
            }
        }
        return standardWidth;
    }

    /*public static void main(String[] args) throws Exception {
        AnalyseImage.analyseImage();
        System.out.println("ok");
    }*/
}

