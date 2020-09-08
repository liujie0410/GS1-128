import java.lang.String;
import java.io.File;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.lang.Exception;
import java.net.URLDecoder;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class Main {

	public static char cs;  //字符集名称
    public static int num;  //译码结果字符个数

	public static int modulenum;

    
     static void store(char c,String[] r,String s) //保存译出字符
    {
    	if(s.equals("FNC1")||c=='A'||c=='B')
    	{
    		r[num]=s;
    		num++;
    	}
    	else 
    	{
    		r[num]=String.valueOf(s.charAt(0));
    		r[num+1]=String.valueOf(s.charAt(1));
    		num=num+2;
    	}
    }

    public static void get(String pic) throws Exception{
		File file = new File(pic);//带入键入的图片地址

		String[] arr = AnalyseImage.analyseImage(file);

		String[] arr2 = new String[arr.length-1];
		String code = null;  //译出的单个字符串
		num=0;

		//判断方向
		cs = Transcode.direction(arr);
		if(cs=='E')
			System.out.println("条码有误，无法识别");
		else if(cs=='F')
		{
			Transcode.invert(arr);  //数组倒置
			cs = Transcode.direction(arr);
			if(cs=='F'||cs=='E')
				System.out.println("条码有误，无法识别");
		}
		String[] arr1 = new String[arr.length-13];  //去掉校验符和结束符
		for(int k=0;k<arr1.length;k++)
		{
			arr1[k]=arr[k];
		}

		if(cs=='A'||cs=='B'||cs=='C')
		{
			//判断FNC1
			modulenum = Transcode.getint(6,6,arr1);
			if(modulenum!=411131)
				System.out.println("条码有误，无法识别");
			else {
				Transcode.storevalue(411131);  //保存FNC1的字符值
				int i = 12;
				int l = arr1.length - 1;
				int shift = 0;
				while (i < l) {
					modulenum = Transcode.getint(i, 6, arr1);
					if (shift > 0) {
						if (cs == 'A') cs = 'B';
						else if (cs == 'B') cs = 'A';
						shift--;
					}
					code = Transcode.queryNum(cs, modulenum);
					switch (code) {
						case "CODEA":
							cs = 'A';
							Transcode.storevalue(modulenum);
							break;
						case "CODEB":
							cs = 'B';
							Transcode.storevalue(modulenum);
							break;
						case "CODEC":
							cs = 'C';
							Transcode.storevalue(modulenum);
							break;
						case "SHIFT":
							shift = 2;
							Transcode.storevalue(modulenum);
							break;
						default:
							Transcode.storevalue(modulenum);
							store(cs, arr2, code);
					}
					i = i + 6;  //继续译码后面6个模块
				}
				for (int a = 0; a < num; a++) {
					System.out.println(arr2[a]);
				}
				String[] arr3 = new String[num];
				for (int a = 0; a < num; a++) {
					arr3[a] = arr2[a];
				}
				if (Transcode.test(arr))  //如果数据正确，开始识别标识符
				{
					charAction.getcode(arr3, num);
				} else {
					System.out.println("数据有误，无法识别");
					charAction.printresults=("数据有误，无法识别");
				}
			}
		}
	}

	public static void main(String[] args) throws Exception
	{

		//************以下为UI*************

		int gap = 10;
		JFrame f = new JFrame("条码识别系统");
		f.setSize(410, 400);
		f.setLocation(200, 200);
		f.setLayout(null);

		JPanel pInput = new JPanel();
		pInput.setBounds(gap, gap,260,120);
		pInput.setLayout(new GridLayout(4,3,gap,gap));


		JLabel warning = new JLabel("请输入图片地址或点击'浏览'选择文件位置");
		JLabel location = new JLabel("图片地址:");
		JTextField locationText = new JTextField();
		JButton b = new JButton("生成");
		JButton btn = new JButton("浏览文件");

		pInput.add(warning);
		pInput.add(location);
		pInput.add(locationText);

		//文本域
		JTextArea ta = new JTextArea();
		ta.setLineWrap(true);
		b.setBounds(150, 130, 80, 30);
		btn.setBounds(280,70,80,30);
		ta.setBounds(gap, 170, 375, 170);


		f.add(pInput);
		f.add(b);
		f.add(ta);
		f.add(btn);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		f.setVisible(true);
		//鼠标监听
		b.addActionListener(new ActionListener() {
			boolean checkedpass = true;
			public void actionPerformed(ActionEvent e) {
				checkedpass = true;
				checkEmpty(locationText,"图片地址");

				String loc = locationText.getText();

				//pic="code1.png";//测试地址
				try{
					get(loc);
				}catch(Exception f){
					f.printStackTrace();
				}


				if(checkedpass){
					String model = charAction.printresults;//获取条码扫描结果
					String result= String.format(model, location);
					ta.setText("");
					ta.append(result);
				}

			}

			//检验是否为空
			private void checkEmpty(JTextField tf, String msg){
				if(!checkedpass)
					return;
				String value = tf.getText();
				if(value.length()==0){
					JOptionPane.showMessageDialog(f, msg + " 不能为空");
					tf.grabFocus();
					checkedpass = false;
				}
			}

		});
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				chooser.showDialog(new JLabel(), "选择");
				File file = chooser.getSelectedFile();
				locationText.setText(file.getAbsoluteFile().toString());

			}
		});



		//**************以上为UI***************


	}

}
