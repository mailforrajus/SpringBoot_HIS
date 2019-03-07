package com.his.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class DateFormator {
	public static Date strintToDate(String sdate) {
		SimpleDateFormat sdf =null;
		Date date=null;
		sdf=new SimpleDateFormat("dd-mm-yy");
		try {
			date = new Date((sdf.parse(sdate)).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
		
	}

	public static Date utilDateToSqlDate(java.util.Date datei) {
		Date date=null;
			date = new Date(datei.getTime());
		return date;	
	}
	
	public static java.util.Date sqlDateToUtilDate(Date datei) {
		java.util.Date date=null;
			date = new Date(datei.getTime());
		return date;	
	}
}//DateFormator
