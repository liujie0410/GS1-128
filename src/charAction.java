import java.lang.String;
import java.util.ArrayList;

public class charAction {
	
	static int k;


	 static ArrayList printfirst=new ArrayList();
    static String printresults="Results: ";
	static int m=0;


	
	//判断表中标识符及字符数
	static int getnum(String ai)
	{
       int charnum=0;
       switch(ai)
       {
           case "00" : charnum=20;break;      
           case "01" : charnum=16;break;
           case "02" : charnum=16;break;
           case "03" : charnum=16;break;
           case "04" : charnum=18;break;
           case "11" : charnum=8;break;
           case "12" : charnum=8;break;
           case "13" : charnum=8;break;
           case "14" : charnum=8;break;
           case "15" : charnum=8;break;
           case "16" : charnum=8;break;
           case "17" : charnum=8;break;
           case "18" : charnum=8;break;
           case "19" : charnum=8;break;
           case "20" : charnum=4;break;
           case "31" : charnum=10;break;
           case "32" : charnum=10;break;
           case "33" : charnum=10;break;
           case "34" : charnum=10;break;
           case "35" : charnum=10;break;
           case "36" : charnum=10;break;
           case "41" : charnum=16;break;
           default: charnum=0;break;
       }
       return charnum;   //返回标识符对应的预定义长度
   }

    //输出长字符串结果时，读取数组中的n个字符(或读到结尾)，输出格式为string
    static String getstring(int t,int n,String[] src)
    {   //t为起始位置，n为获取的字符串长度，src为源数组
        String b=src[t];
        for(int i=t+1;(i<t+n)&&(i<src.length);i++)
        {
            String a=src[i];
            b=b+a;
        }
        return b;   
    }

    //扫描并输出字符串，直到遇到FNC1或结束
    static String getprint(int i,String[] src)
    {   //i为起始位置，src为源数组
        String d=src[i];
        while(!src[i].equals("FNC1")&&!(i==src.length-1))
        {
            //依次扫描，直到遇到FNC1或到结尾结束
            i++;
            String c=src[i];
            if(!c.equals("FNC1"))
            	d=d+c;
        }
        k=i+1;  //下次开始扫描的位置
        return d;
    }

    //判断4位标识符
   static boolean getai4(int t,String ai3,String ai4 ,String []arr2)
   {
        if(ai3.equals("310")||ai3.equals("330"))
        {
            String res=getprint(t,arr2);

            printfirst.add("\n"+"第"+(m+1)+"个标识符为："+ai4+"\n"+res);
            System.out.println("test3:"+m+printfirst.get(m));
            m++;

            return true;
        }
        return false;          
    }

    //判断3位标识符
    static boolean getai3(int t,String ai3,String [] arr2)
    {
        if(ai3.equals("401") || ai3.equals("402") || ai3.equals("403") || ai3.equals("410") || ai3.equals("413") || ai3.equals("420") || ai3.equals("421")) 
        {
            String res = getprint(t, arr2);
            printfirst.add("\n"+"第"+(m+1)+"个标识符为："+ai3+"\n"+res);
            System.out.println("test3:"+m+printfirst.get(m));
            m++;

            return true;
        }
        return false;
    }

   //判断2位标识符
   static boolean getai2(int t,String ai2,String [] arr2)
   {

        int charnum=getnum(ai2);  //获得2位标识符对应的字符长度
        if(charnum != 0) 
        {
            String print1 = getstring(t, charnum, arr2);

            printfirst.add("\n"+"第"+(m+1)+"个标识符为："+ai2+"\n"+print1);
            System.out.println("test1:"+m+printfirst.get(m));
            m++;

            k=t+charnum;  //下次开始扫描的位置
            return true;
        }
        else 
        {
        	String res=getprint(t,arr2);

            printfirst.add("\n"+"第"+(m+1)+"个标识符为："+ai2+"\n"+res);
            System.out.println("test3:"+m+printfirst.get(m));
            m++;

            return true;
        }
    }


    //识别标识符并打印一组译码结果
    static void identify(int t,String [] arr2)
    {   //t为扫描字符串的起点，arr2是待扫描的字符串
        System.out.println("---------identify-----");
        String ai2 = getstring(t, 2, arr2);//获得字符串前2个字符，组成一个新字符
        String ai3 = getstring(t, 3, arr2);//获得前3个字符
        String ai4 = getstring(t, 4, arr2);//获得前4个字符

        if(!getai4(t,ai3,ai4, arr2))
        {
            if(!getai3(t,ai3, arr2))
            {
                if(!getai2(t,ai2, arr2))
                {
                    System.out.println("条码有误，无法识别");
                }
                else
                {
                    System.out.println("test2");
                }
            }
            else
            {
                System.out.println("test3");
            }
        }
        else
        {
            System.out.println("test4");
        }
    }
    
    static void getcode(String[] arr,int n)
    {
    	k=0;
        while(k<n-1)            
        {
            identify(k,arr);
        }

        for(int i=0;i<printfirst.size();i++){
          printresults=printresults+printfirst.get(i).toString();
        }
        System.out.println(printresults);
    }
}
