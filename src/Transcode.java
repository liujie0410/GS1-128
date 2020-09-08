import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.String;
import java.util.ArrayList;
import java.lang.*;


public class Transcode {

	static int n;
	static int num1=0;
	public static int[] testvalue = new int[30];  //符号字符值

	//判断方向和起始符
	static char direction(String[] arr) 
	{
		char c;
	    n = getint(0,6,arr);
		if(n==211412){
			c='A';
			storevalue(n);
		}
		else if(n==211214)
		{
			c='B';
			storevalue(n);
		}
		else if(n==211232)
		{
			c='C';
			storevalue(n);
		}
		else 
		{
			n = getint(0,7,arr);
			if(n==2111332)
				c='F';//终止符
			else c='E';//error
		}
		return c;
	}
	
	//数组倒置
	static void invert(String[] arr) 
    {
    	int head=0;
    	int end=arr.length-1;
    	int time=arr.length/2;
    	for(int i=0;i<time;i++)
    	{
    		String tmp = arr[head];
    		arr[head] = arr[end];
    		arr[end] = tmp;
    		head++;
    		end--;
    	}
    }
	
	//读取数组中的n个字符，将字符串转换成int
	static int getint(int t,int n,String [] src)
	{   //t为起始位置，n为获取的字符串长度，src为源数组
		String b=src[t];
		for( int i=t+1 ;i<t+n;i++)
		{
			String a=src[i];
			b=b+a;
		}
		int c = Integer.parseInt(b);
		return c;   
	}

	//查询并保存符号对应字符值
	static void storevalue(int n)
	{
		int value=0;
		try
		{
			Connection con=DBConnect.getConnection();
			String sql="SELECT no FROM characterset WHERE num="+n;  //取字符值语句
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				value = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		testvalue[num1] = value;
		num1++;
	}

	static boolean test(String[] s)
	{
		int i = num1*6;  //校验码起始位置
		int tn = getint(i,6,s);  //校验码的模块数
		storevalue(tn);

		int tvalue = testvalue[num1-1];  //校验符的字符值
		int sum = testvalue[0]+testvalue[1];


		for(int k=2;k<num1-1;k++)
		{
			sum = sum + k*testvalue[k];
		}
		if((sum%103)==tvalue)  //验证数据正确性
		{
			return true;
		}
		else return false;
	}


	//带入模块数和字符集，查询字符
    static String queryNum(char s,int n)
    {
        try{
            Connection con=DBConnect.getConnection();
            String sql1="SELECT A FROM characterset WHERE num="+""+n+"";
            String sql2="SELECT B FROM characterset WHERE num="+""+n+"";
            String sql3="SELECT C FROM characterset WHERE num="+""+n+"";
            PreparedStatement pst = null;
            ResultSet rs = null;

            //确定不同字符集
            switch(s)
            {
                case 'A': pst = con.prepareStatement(sql1);break;
                case 'B': pst = con.prepareStatement(sql2);break;
                case 'C': pst = con.prepareStatement(sql3);break;
            }

            rs = pst.executeQuery();
            String chars = null;
            while(rs.next()) 
            {
            	chars = rs.getString(1);
            }
            return chars;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
