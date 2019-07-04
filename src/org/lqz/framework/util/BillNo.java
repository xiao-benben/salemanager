package org.lqz.framework.util;

/**
 * 说明:用于生成订单号的工具类
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BillNo extends Thread {

	private static long orderNum = 0l;
	private static String date;

	/**
	 * 生成订单编号
	 * 
	 * @return
	 */
	public static synchronized String getBillNo() {
		/*String str = new SimpleDateFormat("yyMMddHHmm").format(new Date());
		if (date == null || !date.equals(str)) {
			date = str;
			orderNum = 0l;
		}
		orderNum++;
		long orderNo = Long.parseLong((date)) * 10000;
		orderNo += orderNum;
		return orderNo + "";*/
		String str = "0123456789";
	     Random random = new Random();
	     StringBuffer sb = new StringBuffer();
	     for(int i = 0; i < 8; i++){
	       int number = random.nextInt(10);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	}

}
