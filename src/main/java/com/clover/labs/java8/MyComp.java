package com.clover.labs.java8;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyComp  {

	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR,00);
        calendar.set(Calendar.MINUTE,00);
        calendar.set(Calendar.SECOND,00);
        calendar.set(Calendar.MILLISECOND,00);
        
        calendar.add(Calendar.DATE,-1);
        
        
        Date date = calendar.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = format1.format(date);
        
        System.out.println(date1);
        
        Calendar nextDayDate = (Calendar) calendar.clone();
        nextDayDate.add(Calendar.DATE, 1);
        
        String date2 = format1.format(nextDayDate.getTime());
        
        System.out.println(date2);
	}

}
