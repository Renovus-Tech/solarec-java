package tech.renovus.solarec.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * The <code>DateUtil</code> class contains useful methods to work with <code>Date</code> and retrieve 
 * information from it.
 * 
 * @see java.text.SimpleDateFormat
 */
public final class DateUtil {
	//-- Public attributes ----------------------
	/** Standard date separator <code>/</code>. */ 
	public static final char SEPARATOR_DATE		= '/';
	/** Standard time separator <code>:</code>. */
	public static final char SEPARATOR_TIME		= ':';
	
	/** The amount of seconds in a week. */
	public static final int A_WEEK_IN_SECONDS 	= 60 * 60 * 24 * 7;
	
	public static final String FMT_TIME_FULL	= "HH:mm:ss.SSS";
	
	/** Military like date-time format <code>yyyyMMdd-HHmmss</code>. */
	public static final String FMT_DATE_TIME	= "yyyyMMdd'-'HHmmss";

	/** Standard date-time format <code>dd/MM/yyyy HH:mm:ss</code>. */
	public static final String FMT_DATE_TIME_UY = "dd'/'MM'/'yyyy HH':'mm':'ss"; 
	
	/** Military like date format <code>yyyyMMdd</code>. */
	public static final String FMT_DATE 		= "yyyyMMdd";
	
	/** Standard date format <code>dd/MM/yyyy</code>. */
	public static final String FMT_DATE_SLASH 	= "dd'/'MM'/'yyyy";
	
	/** Standard time format <code>HH:mm:ss</code>. */
	public static final String FMT_TIME 		= "HH':'mm':'ss";
	
	/** Military like time format <code>HHmmssSSS</code>. */
	public static final String FMT_TIME_MILI 	= "HHmmssSSS";
	
	/** Military like time format <code>hh:mm:ss.SSS</code>. */
	public static final String FMT_TIME_MILI_2	= "hh':'mm':'ss'.'SSS";
	
	/** RSS date-time format <code>EEE, d MMM yyyy HH:mm:ss Z</code>. */
	public static final String FMT_RSS			= "EEE, d MMM yyyy HH:mm:ss Z";
	
	/** TIMELINE date-time format <code>MMM dd yyyy HH:mm:ss GMT</code>. */
	public static final String FMT_TIMELINE		= "MMM dd yyyy HH:mm:ss 'GMT'";
	
	/** Military like date-time format <code>yyyy-MM-dd HH:mm:ss</code>. */
	public static final String FMT_MILITAR		= "yyyy-MM-dd HH:mm:ss";
	
	/** Military like date-time format <code>yyyy/MM/dd HH:mm:ss</code>. */
	public static final String FMT_JSON_CHART	= "yyyy/MM/dd HH:mm:ss";

	/** Parameter date time text format <code>yyyy-MM-dd HH:mm:ss</code>. */
	public static final String FMT_PARAMETER_DATE_TIME	= "yyyy-MM-dd HH:mm:ss";
	
	/** Parameter date text format <code>yyyy-MM-dd</code>. */
	public static final String FMT_PARAMETER_DATE		= "yyyy-MM-dd";
	
	/** SQL date format <code>dd-MM-yyyy</code>.*/
	public static final String FMT_SQL_STRING	= "dd-MM-yyyy";
	
	/** GENEXUS <code>null</code> date <code>01/01/1753</code>. */
	public static final Date GENEXUS_NULL_DATE	= DateUtil.stringToDate("01/01/1753");
	
	/** Result in milliseconds when calculating the difference between to <code>Date</code>. */
	public static final int DATE_DIFF_IN_MILISEC	= 0;
	/** Result in seconds when calculating the difference between to <code>Date</code>. */
	public static final int DATE_DIFF_IN_SECONDS	= 1;
	/** Result in minutes when calculating the difference between to <code>Date</code>. */
	public static final int DATE_DIFF_IN_MINUTES	= 2;
	/** Result in hours when calculating the difference between to <code>Date</code>. */
	public static final int DATE_DIFF_IN_HOURS		= 3;
	/** Result in days when calculating the difference between to <code>Date</code>. */
	public static final int DATE_DIFF_IN_DAYS		= 4;
	/** Result in years when calculating the difference between to <code>Date</code>. */
	public static final int DATE_DIFF_IN_YEARS		= 5;
	
	//-- Constructor ----------------------------
	private DateUtil() {
	}
	
	//-- Specific methods -----------------------
	/**
	 * Returns a new instance of <code>Date</code> with the current date-time.
	 * 
	 * @return	An instance of <code>Date</code>.
	 */
	public static Date getCurrent() {
		return new Date(System.currentTimeMillis());
	}
	
	/**
	 * Returns the current date with out time.
	 * 
	 * @return	An instance of <code>Date</code> without time
	 * 
	 * @see #clearTime(Date)
	 */
	public static Date getCurrentDay() {
		Date date = DateUtil.getCurrent();
		return DateUtil.clearTime(date);
	}
	
	/**
	 * Returns the yesterday <code>Date</code>. 
	 * 
	 * @return An instance of <code>Date</code> of yesterday.
	 * 
	 * @see #removeDate(Date, int)
	 * @see #removeDate(Date, Integer)
	 */
	public static Date getYestardayDay() {
		Date date = DateUtil.getCurrentDay();
		return DateUtil.removeDate(date,1);
	}
	
	/**
	 * Returns a <code>Date</code> with the first date of the current month.
	 * 
	 * @return	The first <code>Date</code> of the current month.
	 */
	public static Date getCurrentMonthStart() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(DateUtil.getCurrent());
		calendar.set(Calendar.DATE,1);
		return calendar.getTime();
	}

	/**
	 * Returns a <code>Date</code> with the last date of the current month.
	 * 
	 * @return	The last <code>Date</code> of the current month.
	 */
	public static Date getCurrentMonthEnd() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(DateUtil.getCurrent());
		calendar.set(Calendar.DATE,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	/**
	 * Returns the current year.
	 * 
	 * @return	The actual year.
	 */
	public static int getCurrentYear() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(DateUtil.getCurrent());
		return calendar.get(Calendar.YEAR);
	}
	
	/**
	 * Converts a <code>Date</code> into a <code>String</code> using the <b>format</b>specified. If the <b>format</b>
	 * is equals to <code>DateUtil.FMT_RSS</code> or equals to <code>DateUtil.FMT_TIMELINE</code> then the
	 * <code>Locale.US</code> is used with the <b>format</b>.
	 * 
	 * <p>If <b>date</b> is <code>null</code> then <code>StringUtil.EMPTY_STRING</code> is return.
	 * 
	 * @param date		The <code>Date</code> to format
	 * @param format	The format to use
	 * @return			A <code>String</code> with the <code>Date</code> formatted.
	 * 
	 * @see #FMT_RSS
	 * @see #FMT_TIMELINE
	 * @see uy.com.pf.sdk.util.StringUtil#EMPTY_STRING
	 */
	public static String formatDateTime(Date date, String format) {
		if (date != null) {
			SimpleDateFormat formater = null;
			
			if (DateUtil.FMT_RSS.equals(format) || DateUtil.FMT_TIMELINE.equals(format)) {
				formater = new SimpleDateFormat(format,Locale.US);
			} else {
				formater = new SimpleDateFormat(format);
			}
			
			return formater.format(date);
		} else {
			return StringUtil.EMPTY_STRING;
		}
	}
	
	/**
	 * Converts a <code>String</code> to a <code>Date</code> using <code>DateUtil.SEPARATOR_DATE</code> as date
	 * separator and using <code>DateUtil.SEPARATOR_TIME</code>. The time in the <b>stringDate</b> will not be
	 * added to the result.
	 * 
	 * @param stringDate	The <code>String</code> to convert to <code>Date</code>
	 * @return				The <code>Date</code> represented by <b>stringDate</b>
	 * 
	 * @see #stringToDate(String, boolean)
	 * @see #stringToDate(String, char, char, boolean)
	 */
	public static Date stringToDate(String stringDate) {
		return DateUtil.stringToDate(stringDate, DateUtil.SEPARATOR_DATE, DateUtil.SEPARATOR_TIME, false);
	}

	/**
	 * Converts a <code>String</code> to a <code>Date</code> using <code>DateUtil.SEPARATOR_DATE</code> as date
	 * separator and using <code>DateUtil.SEPARATOR_TIME</code>. The time in the <b>stringDate</b> will be
	 * added to the result depending of the value of <b>includeTime</b>.
	 * 
	 * @param stringDate	The <code>String</code> to convert to <code>Date</code>
	 * @param includeTime	If the time in <b>stringDate</b> must be conceder while converting.
	 * @return				The <code>Date</code> represented by <b>stringDate</b>
	 * 
	 * @see #stringToDate(String)
	 * @see #stringToDate(String, char, char, boolean)
	 */
	public static Date stringToDate(String stringDate, boolean includeTime) {
		return DateUtil.stringToDate(stringDate, DateUtil.SEPARATOR_DATE, DateUtil.SEPARATOR_TIME, includeTime);
	}
	
	/**
	 * Converts a <code>String</code> to a <code>Date</code> using <b>dateSeparator</b> as the date separator and
	 * using <code>timeSeparator</code> as the time separator. The time in the <b>stringDate</b> will be
	 * added to the result depending of the value of <b>includeTime</b>.
	 * 
	 * <p>The format that is used to convert is <code>dd'dateSepartor'MM'dateSeparator'yyyy HH'timeseparator'mm'timeseprator'ss</code>
	 * in case of <b>includeTime</b> is <code>true</code> and <code>dd'dateSepartor'MM'dateSeparator'yyyy</code>
	 * in case of <b>includeTime</b> is <code>false</code>.</p>
	 * 
	 * @param stringDate	The <code>String</code> to convert to <code>Date</code>
	 * @param dateSeparator	The date separator to use
	 * @param timeSeparator	The time separator to use
	 * @param includeTime	If the time in <b>stringDate</b> must be conceder while converting.
	 * @return				The <code>Date</code> represented by <b>stringDate</b>
	 * 
	 * @see #stringToDate(String)
	 * @see #stringToDate(String, boolean)
	 */
	public static Date stringToDate(String stringDate, char dateSeparator, char timeSeparator, boolean includeTime) {
		Date date = null;
		SimpleDateFormat formatter = null;
		try {
			if (stringDate != null && stringDate.length() > 0) {
				if (includeTime) {
					formatter = new SimpleDateFormat("dd" + dateSeparator + "MM" + dateSeparator + "yyyy " + " HH" + timeSeparator + "mm" + timeSeparator + "ss");
				} else {
					formatter = new SimpleDateFormat("dd" + dateSeparator + "MM" + dateSeparator + "yyyy");
				}
				date = formatter.parse(stringDate);

			} else {
				return null;
			}
		} catch (ParseException pe) {
			return null;
		}

		return date;
	}
	
	/**
	 * Converts a <code>String</code> to a <code>Date</code> using the format specified in <code>format</code>.
	 * 
	 * @param stringDate The <code>String</code> to convert to <code>Date</code>
	 * @param format	 The format of <code>stringDate</code>
	 * @return			 The <code>Date</code> represented by <b>stringDate</b>
	 */
	public static Date stringToDate(String stringDate, String format) {
		Date date = null;
		SimpleDateFormat formatter = null;
		try {
			if (stringDate != null && stringDate.length() > 0) {
				formatter = new SimpleDateFormat(format);
				date = formatter.parse(stringDate);
			} else {
				return null;
			}
		} catch (ParseException pe) {
			return null;
		}

		return date;
	}
	
	/**
	 * Removes a number of <b>days</b> from a <code>Date</code>. If <b>days</b> is <code>null</code>, then
	 * <b>date</b> is return.
	 * 
	 * @param date	The <code>Date</code> to where remove days
	 * @param days	The days to remove
	 * @return		A new instance of <code>Date</code> without <b>days</b>
	 * 
	 * @see #removeDate(Date, int)
	 */
	public static Date removeDate(Date date, Integer days) {
		if (days == null) {
			return date;
		} else {
			return DateUtil.removeDate(date,days.intValue());
		}
	}
	
	/**
	 * Removes a number of <b>days</b> from a <code>Date</code>.
	 * 
	 * @param date	The <code>Date</code> to where remove days
	 * @param days	The days to remove
	 * @return		A new instance of <code>Date</code> without <b>days</b>
	 * 
	 * @see #removeDate(Date, Integer)
	 */
	public static Date removeDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR,-days);
		return calendar.getTime();
	}
	
	/**
	 * Compares 2 instances <code>Date</code>, comparing only the date part. Before doing the compare
	 * (calling the <code>.equals()</code> method of <b>aDate</b>) the time is removed from both dates.
	 * 
	 * @param aDate			A <code>Date</code> to compare
	 * @param anotherDate	An other <code>Date</code> to compare
	 * @return				If both dates are equals without considering the time.
	 * 
	 * @see #clearTime(Date)
	 */
	public static boolean equalsDate(Date aDate, Date anotherDate) {
		if (aDate == null || anotherDate == null) {
			return false;
		}
		return DateUtil.clearTime(aDate).equals(DateUtil.clearTime(anotherDate));
	}
	
	/**
	 * Clears the hours, minutes, seconds, milliseconds and am/pm from a <code>Date</code>.
	 * 
	 * @param date	A <code>Date</code> to clear
	 * @return		A new <code>Date</code> with time 0.
	 */
	public static Date clearTime(Date date) {
		if (date == null) {
			return date;
		}
		
		Calendar aCalendar = Calendar.getInstance();
		aCalendar.setTime(date);

		aCalendar.set(Calendar.HOUR,0);
		aCalendar.set(Calendar.MINUTE,0);
		aCalendar.set(Calendar.SECOND,0);
		aCalendar.set(Calendar.MILLISECOND,0);
		aCalendar.set(Calendar.AM_PM,Calendar.AM);

		return aCalendar.getTime();
	}

	/**
	 * Adds the <b>time<b> to the <code>Date</code>. The <b>time</b> is in milliseconds.
	 * 
	 * @param date	The <code>Date</code> where to add the time
	 * @param time	The time to add
	 * @return		A new <code>Date</code>
	 * 
	 * @see #addTime(Date, Date)
	 */
	public static Date addTime(Date date, long time) {
		return DateUtil.addTime(date, new Date(time));
	}
	
	/**
	 * Adds the time of a <code>Date</code> to an other <code>Date</code>. If <b>date</b> is <code>null</code> then
	 * <code>null</code> is return. If <b>time</b> is <code>null</code> then <b>date</b> is return. 
	 * 
	 * @param date	The <code>Date</code> where to add
	 * @param time	The <code>Date</code> to add
	 * @return		A new <code>Date</code>
	 * 
	 * @see DateUtil#addDate(Date,Date)
	 */
	public static Date addTime(Date date, Date time) {
		if (time == null) {
			return date;
		}
		if (date == null) {
			return null;
		}
		
		Calendar aCalendar = Calendar.getInstance();
		Calendar aTime = Calendar.getInstance();
		
		aCalendar.setTime(date);
		aTime.setTime(time);
		
		aCalendar.add(Calendar.HOUR,aTime.get(Calendar.HOUR));
		aCalendar.add(Calendar.MINUTE,aTime.get(Calendar.MINUTE));
		aCalendar.add(Calendar.SECOND,aTime.get(Calendar.SECOND));
		aCalendar.add(Calendar.MILLISECOND,aTime.get(Calendar.MILLISECOND));
		aCalendar.add(Calendar.AM_PM,aTime.get(Calendar.AM_PM));
		
		return aCalendar.getTime();
	}
	
	/**
	 * Adds a <code>Date</code> to an other <code>Date</code>. If <b>date</b> is <code>null</code> then
	 * <code>null</code> is return. If <b>time</b> is <code>null</code> then <b>date</b> is return. 
	 * 
	 * @param date	The <code>Date</code> where to add
	 * @param time	The <code>Date</code> to add
	 * @return		A new <code>Date</code>
	 * 
	 * see DateUtil#addTime(Date,Date)
	 */
	public static Date addDate(Date date, Date time) {
		if (time == null) {
			return date;
		}
		if (date == null) {
			return null;
		}
		
		Calendar aCalendar = Calendar.getInstance();
		Calendar aTime = Calendar.getInstance();
		
		aCalendar.setTime(date);
		aTime.setTime(time);
		
		aCalendar.add(Calendar.YEAR, aTime.get(Calendar.YEAR) - 1970);
		aCalendar.add(Calendar.DAY_OF_YEAR, aTime.get(Calendar.DAY_OF_YEAR) - 1);
		aCalendar.add(Calendar.HOUR,aTime.get(Calendar.HOUR));
		aCalendar.add(Calendar.MINUTE,aTime.get(Calendar.MINUTE));
		aCalendar.add(Calendar.SECOND,aTime.get(Calendar.SECOND));
		aCalendar.add(Calendar.MILLISECOND,aTime.get(Calendar.MILLISECOND));
		aCalendar.add(Calendar.AM_PM,aTime.get(Calendar.AM_PM));
		
		return aCalendar.getTime();
	}
	
	/**
	 * Adds a determinate amount of time to a <code>Date</code>. If <b>date</b> is <code>null</code> then
	 * <code>null</code> is return.
	 * 
	 * @param date			The <code>Date</code> where to add the time
	 * @param hour			The amount of hours to add
	 * @param minute		The amount of minutes to add
	 * @param second		The amount of seconds to add
	 * @param millisecond	The amount of milliseconds to add
	 * @param ampm			The the time is <code>Calendar.AM</code> of <code>Calendar.PM</code>
	 * @return				A new <code>Date</code>
	 * 
	 * @see java.util.Calendar#AM
	 * @see java.util.Calendar#PM
	 */
	public static Date addTime(Date date, int hour, int minute, int second, int millisecond, int ampm) {
		if (date == null) {
			return null;
		}
		
		Calendar aCalendar = Calendar.getInstance();

		aCalendar.setTime(date);
		
		aCalendar.add(Calendar.HOUR,hour);
		aCalendar.add(Calendar.MINUTE,minute);
		aCalendar.add(Calendar.SECOND,second);
		aCalendar.add(Calendar.MILLISECOND,millisecond);
		aCalendar.add(Calendar.AM_PM,ampm);
		
		return aCalendar.getTime();
	}
	
	/**
	 * Adds a determinated amount of <b>value</b> in the <b>unit</b>. <b>unit</b> must be a valid constant
	 * from <code>Calendar</code> (Ex: <code>Calendar.HOUR</code>, <code>Calendar.MINUTE</code>...)
	 * 
	 * @param date	The <code>Date</code> where to add 
	 * @param unit	What to add
	 * @param value	The value to add
	 * @return		A new <code>Date</code>
	 */
	public static Date addUnit(Date date, int unit, int value) {
		if (date == null) {
			return null;
		}
		
		Calendar aCalendar = Calendar.getInstance();

		aCalendar.setTime(date);
		
		aCalendar.add(unit,value);
		
		return aCalendar.getTime();
	}	

	/**
	 * Returns the <b>value</b> in the <b>unit</b>. <b>unit</b> must be a valid constant
	 * from <code>Calendar</code> (Ex: <code>Calendar.HOUR</code>, <code>Calendar.MINUTE</code>...)
	 * 
	 * @param date	The <code>Date</code> where to get 
	 * @param unit	What to add
	 * @return		The unit value
	 */
	public static int getUnit(Date date, int unit) {
		if (date == null) {
			return -1;
		}
		
		Calendar aCalendar = Calendar.getInstance();
		
		aCalendar.setTime(date);
		
		return aCalendar.get(unit);
	}	
	
	/**
	 * Returns the differences of 2 <code>Date</code>s in the unit specify by <b>diffType</b>. The <b>diffType</b>
	 * must be one of the following values: <code>DATE_DIFF_IN_MILISEC</code>, <code>DATE_DIFF_IN_SECONDS</code>, 
	 * <code>DATE_DIFF_IN_MINUTES</code>, <code>DATE_DIFF_IN_HOURS</code> or <code>DATE_DIFF_IN_DAYS</code>. If 
	 * <b>aDate</b> is <code>null</code> or <b>anotherDate</b> is <code>null</code> then <code>Long.MIN_VALUE</code> 
	 * is return.
	 * 
	 * @param diffType		The unit in which return the result. Is invalid value, the <code>DATE_DIFF_IN_DAYS</code> is used
	 * @param aDate			The first <code>Date</code>
	 * @param anotherDate	The second <code>Date</code>
	 * @return				The differences of the <code>Date</code> in days, <code>- Long</code> if can't determinate the difference.
	 * 
	 * @see DateUtil#DATE_DIFF_IN_MILISEC
	 * @see DateUtil#DATE_DIFF_IN_SECONDS
	 * @see DateUtil#DATE_DIFF_IN_MINUTES
	 * @see DateUtil#DATE_DIFF_IN_HOURS
	 * @see DateUtil#DATE_DIFF_IN_DAYS
	 * @see Long#MIN_VALUE
	 */
	public static double dateDiffIn(int diffType, Date aDate, Date anotherDate) {
		if (aDate == null) {
			return Long.MIN_VALUE;
		}
		if (anotherDate == null) {
			return Long.MIN_VALUE;
		}
		
		long milliseconds1 = aDate.getTime();
	    long milliseconds2 = anotherDate.getTime();
	    double diff = milliseconds2 - (double) milliseconds1;
	    
	    switch (diffType) {
	    	case DateUtil.DATE_DIFF_IN_MILISEC: return diff;
	    	case DateUtil.DATE_DIFF_IN_SECONDS: return diff / 1000;
	    	case DateUtil.DATE_DIFF_IN_MINUTES: return diff / (60 * 1000);
	    	case DateUtil.DATE_DIFF_IN_HOURS: return diff / (60 * 60 * 1000);
	    	case DateUtil.DATE_DIFF_IN_DAYS: return diff / (24 * 60 * 60 * 1000);
	    	case DateUtil.DATE_DIFF_IN_YEARS: return diff / (24 * 60 * 60 * 1000 * 365);
	    	default:
	    }
	    
	    return diff / (24 * 60 * 60 * 1000);
	}
}