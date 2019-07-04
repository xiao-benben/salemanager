package org.lqz.framework.util;

import java.util.Random;

public class Id {
	public static String getId(){
	     String str = "abcdefghijklmnopqrstuvwxyz0123456789";
	     Random random = new Random();
	     StringBuffer sb = new StringBuffer();
	     for(int i = 0; i < 32; i++){
	       int number = random.nextInt(36);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	 }
}
