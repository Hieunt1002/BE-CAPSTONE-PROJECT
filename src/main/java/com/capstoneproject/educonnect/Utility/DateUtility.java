package com.capstoneproject.educonnect.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {
	public static String convertDateToSqlString(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String formattedDate = sdf.format(date);
        
        return formattedDate;
	}
}
