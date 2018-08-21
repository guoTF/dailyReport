package com.dailyReport.test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Test {

	public static void main(String[] args) throws Exception {
		/*Map<Object, Object> map = new HashMap<>();
		String str = "{payTime:2018-06-25 08:56:33,paySeqId:03300010784N,invoiceAmount:1,settleDate:2018-06-25,buyerId:otdJ_uAsg9ry5fvyIQADbW1g75ms,totalAmount:1,couponAmount:0,billBizType:bills,buyerPayAmount:1,targetOrderId:4200000111201806256585701534,payDetail:现金支付0.01元。,merOrderId:396220180625085615750TF0000050,status:TRADE_SUCCESS,targetSys:WXPay}";
		List list = new ArrayList<>();
		list.add(str);
		map.put("billPayment", str);
		JSONObject jsonArray = new JSONObject(str);
		jsonArray.toString();
		System.out.println(jsonArray.toString());*/
		/*Properties props = new Properties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host","smtp.qq.com");// smtp服务器地址
        
        Session session = Session.getInstance(props);
        session.setDebug(true);
        
        Message msg = new MimeMessage(session);
        msg.setSubject("这是一个测试程序....");
        msg.setText("你好!这是我的第一个javamail程序---WQ");
        msg.setFrom(new InternetAddress("787999964@qq.com"));//发件人邮箱(我的163邮箱)
        msg.setRecipient(Message.RecipientType.TO,
                new InternetAddress("gtf13760191776@163.com")); //收件人邮箱(我的QQ邮箱)
        msg.saveChanges();

        Transport transport = session.getTransport();
        transport.connect("787999964@qq.com","lpqajzdfbunsbfbj");//发件人邮箱,授权码(可以在邮箱设置中获取到授权码的信息)
        
        transport.sendMessage(msg, msg.getAllRecipients());
        
        System.out.println("邮件发送成功...");
        transport.close();*/
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.100.241:3306/daily", "root", "123456");
			String sql = "select * from tb_user";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			System.out.println(1);
			
//			CallableStatement c =conn.prepareCall("{call PROC_AccountComputeNum(?,?,?)}");//调用带参的存储过程
//			//给存储过程的参数设置值
//			c.setInt(1, 1);   //将第一个参数的值设置成空字符串
//			c.setInt(2, 0);   //将第一个参数的值设置成0
//			c.registerOutParameter(2, java.sql.Types.INTEGER);   //将第一个参数的值设置成0
//			c.registerOutParameter(3,java.sql.Types.VARCHAR);//第三个是返回参数  返回字符串VARCHAR类型
//			//执行存储过程
//			c.execute();
//			System.out.println ( c.getInt(2) );//打印返回参数
//			System.out.println ( c.getString(3) );//打印返回参数
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
