package com.smartValue.components.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.faces.model.SelectItem;

import com.smartValue.database.AttChangeListner;
import com.smartValue.database.Attribute;
import com.smartValue.database.PersistantObject;
import com.smartValue.database.ValidationResult;

public class DateInterval implements AttChangeListner {

	private static SelectItem ANY_SELECT = new SelectItem("Any", "Any");
	private static SelectItem BEFORE_SELECT = new SelectItem("Before", "Before");
	private static SelectItem AFTER_SELECT = new SelectItem("After", "After");
	private static SelectItem BETWEEN_SELECT = new SelectItem("Between",
			"Between");

	private Date m_dateFrom;
	private Date m_dateTo;
	
	private Attribute attDateFrom;
	private Attribute attDateTo;
	private int    period ;
	ArrayList<Integer> weekEndDays ;
	private String m_datePattern = null;
	private boolean m_showFromCalender;
	private boolean m_showToCalender;
	private String m_type;
	private long timeBetween ; 

	public DateInterval() {
	}	
	//......................................................................
	public DateInterval(Attribute attr_dateFrom,Attribute attr_dateTo  ) 
	{
		this(attr_dateFrom , attr_dateTo , null) ; 
	}

	public DateInterval(Attribute attr_dateFrom,Attribute attr_dateTo , ArrayList<Integer> pm_weekEndDays ) {
		if (weekEndDays == null)
		{
		weekEndDays = new ArrayList<Integer>();
		weekEndDays.add(Calendar.SATURDAY) ; 
		weekEndDays.add(Calendar.FRIDAY );
		}
		else 
		weekEndDays = pm_weekEndDays ;
		
		attDateFrom=attr_dateFrom;
		attDateFrom.addChangeListner(this) ;
		attDateTo=attr_dateTo;
		attDateTo.addChangeListner(this);
		this.refreshCalc() ; 

		
		
		
	}
	public int getPeriod() 
	{
		return 	this.period ; 
	}

	public void setPeriod(int period) {
		this.period = period;

		Date  dateFrom  =(Date)(attDateFrom.getValue());   
		Date  dateTo    =(Date)(attDateTo.getValue());
		Calendar cal=Calendar.getInstance();
		cal.setTime(dateFrom);

		int amount=0;
		if(this.period<=0)
			amount=this.period;
		else
			amount=this.period-1;
		
		cal.add(Calendar.DATE,amount);
		dateTo=cal.getTime();
		//set  attr date to 
		this.attDateTo.setValue(dateTo);
		this.refreshCalc() ; 
	   
	}
//   private void  setValidResultAndValidMessagexx()
//   { 
//	   Date  dateFrom  =(Date)(attDateFrom.getValue());   
//	   Date  dateTo    =(Date)(attDateTo.getValue());
//       ValidationResult  dateTovalidResult ;
//       dateTovalidResult =  attDateTo.getValidationResult() ;
//       String validationMessage="";
//	   if(dateFrom.after(dateTo))
//		  {
//		  dateTovalidResult.setValidResult(false) ;
//		  validationMessage= attDateTo.getDisplayText() +" Must be after "+attDateFrom.getDisplayText() ;
//		  }
//	   else
//	   {
//		   dateTovalidResult.setValidResult(true) ;
//		   validationMessage=null;
//	   }
//	   
//	   dateTovalidResult.setInvalidMessage(validationMessage) ;
//   }
     int   countOfPublicHolidays;
     private void calcCountOfPublicHolidays()
     {
    	    BigDecimal count=null ;
		    String query=" select  count( *)   COUNTOFPUBLICHOLIDAYS  from hr_mas_public_holiday t   where t.public_holiday_date between ? and ? ";
		    Object values[]=new Object[2]; 
		     
		    values[0]=new Timestamp( ((Date)attDateFrom.getValue()).getTime());
		    values[1]=new Timestamp( ((Date)attDateTo.getValue()).getTime());
			ArrayList<PersistantObject> poList = attDateFrom.getParentPersistantObject().getDbService().queryForDataSet(query ,values, null ,null).getPersistantObjectList() ;
			if (!poList.isEmpty())
			{
			  count	 =(BigDecimal) poList.get(0).getAttributeValue("COUNTOFPUBLICHOLIDAYS");  
			}
			if(count==null)
			{  count=new BigDecimal(0);}
			
			countOfPublicHolidays= count.intValue();
     }
     
   public  int  getCountOfPublicHolidays()
       {
		  return countOfPublicHolidays;
       }
    private int weeksHolidays;
	public int getWeeksHolidays() 
	{
		return weeksHolidays;
	}
	int workingDays=0;	
	
	private void calcWorkingDays()
	{
		 weeksHolidays=0;
		  Date  dateFrom  =(Date)(attDateFrom.getValue());   
		  Date  dateTo    =(Date)(attDateTo.getValue());
		 //to  set   validationMessag  for attribute   and to  set    ValidResult
		 // this.setValidResultAndValidMessage();
		   workingDays = 0;
		  Calendar cal=Calendar.getInstance();
		  cal.setTime(dateFrom);
			while (!dateFrom.after(dateTo)) 
			{
				 int day = cal.get(Calendar.DAY_OF_WEEK);
				 
				 if (!weekEndDays.contains(day) )
					   	workingDays++;
				 else
					 weeksHolidays++; 
				 cal.add(Calendar.DATE, 1);
				 dateFrom =cal.getTime();
			} 
			//substract PublicHolidays from  working Days
			if(!((Date)(attDateFrom.getValue())).after(dateTo))
			{
				workingDays=workingDays-this.getCountOfPublicHolidays();
			}
	}
	public int getWorkingDays()
	{   
	return workingDays;
	}

	
	public Attribute getAttDateFrom() {
		return attDateFrom;
	}
	public Attribute getAttDateTo() {
		return attDateTo;
	}
	//...........................................................................
	public DateInterval(String pm_datePattern) {
		this.setDatePattern(pm_datePattern);
	}

	public boolean isShowFromCalender() {
		return m_showFromCalender;
	}

	public SelectItem[] getAllAvailableTypes() {
		return new SelectItem[] { ANY_SELECT, BEFORE_SELECT, AFTER_SELECT,
				BETWEEN_SELECT };
	}

	public void setShowFromCalender(boolean fromCalender) {
		m_showFromCalender = fromCalender;
	}

	public boolean isShowToCalender() {
		return m_showToCalender;
	}

	public void setShowToCalender(boolean toCalender) {
		m_showToCalender = toCalender;
	}

	public String getM_type() {
		return m_type;
	}

	public void setM_type(String m_type) {
		this.m_type = m_type;
		renderDateInterval();
	}
	
	public Date getFormatedDateFrom()
	{
		return this.m_datePattern == null? m_dateFrom : DateInterval.getDateFormate(m_dateFrom, this.m_datePattern);
	}

	public Date getDateFrom() {
		return m_dateFrom;
	}

	public void setDateFrom(Date pm_dateFrom) {
		m_dateFrom = pm_dateFrom;
	}

	public Date getFormatedDateTo() {
		return this.m_datePattern == null ? m_dateTo : DateInterval.getDateFormate(m_dateTo, this.m_datePattern);
	}
	public Date getDateTo() {
		return m_dateTo;
	}
	public void setDateTo(Date pm_dateTo) {
		m_dateTo = pm_dateTo;
	}
		 
	public void renderDateInterval() {
		if (m_type.equalsIgnoreCase((String) BEFORE_SELECT.getValue())) {
			m_showFromCalender = false;
			m_showToCalender = true;
		} else if (m_type.equalsIgnoreCase((String) AFTER_SELECT.getValue())) {
			m_showFromCalender = true;
			m_showToCalender = false;
		} else if (m_type.equalsIgnoreCase((String) BETWEEN_SELECT.getValue())) {
			m_showFromCalender = true;
			m_showToCalender = true;
		}
	}

	public void setDatePattern(String m_datePattern) {
		this.m_datePattern = m_datePattern;
	}

	public String getDatePattern() {
		return m_datePattern;
	}
	
	/**
	 * This method formates the date to the required paettern
	 * 
	 * @param date
	 *            the date to be formated
	 * @param pattern
	 *            the pattern required e.g. DD/MM/YYYY
	 * @return date of the required formate in string
	 */
	public static Date getDateFormate(Date date, String pattern) {
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    String dateAsString = format.format(date);
	    try {
	      date = format.parse(dateAsString);
	    } catch (ParseException e) {
	      e.printStackTrace();
	    }
		return date;
	}
	public static String getDateFormateAsString(Date date, String pattern) {
	    SimpleDateFormat format = new SimpleDateFormat(pattern);
	    String dateAsString = format.format(date);	    
		return dateAsString;
	}
	/**
	 * overlap when not ( Start 1 after End 2 Or End 1 before Start 2)
	 * @param pm_DateInterval
	 * @return
	 */
	public boolean isOverlapWith(DateInterval pm_DateInterval)
	{
		return !(this.getDateFrom().after(pm_DateInterval.getDateTo()) || this.getDateTo().before(pm_DateInterval.getDateFrom()));
	}

	
	private void refreshCalc()
	{
		Date dateTo = ((Date)(attDateTo.getValue())) ; 
		Date dateFrom = ((Date)(attDateFrom.getValue()));

		this.timeBetween = dateTo.getTime() - dateFrom.getTime() ;
		boolean timeBetweenNegative = timeBetween < 0 ; 
		this.period=   (int) (this.timeBetween / (1000 * 60 * 60 * 24)) + ((timeBetweenNegative)? 0 : 1) ;
		ValidationResult dateTovalidResult =  attDateTo.getValidationResult() ;
		String validationMessage="";

		if (timeBetweenNegative)
		{
		  dateTovalidResult.setValidResult(false) ;
		  validationMessage= attDateTo.getDisplayText() +" Should be after "+attDateFrom.getDisplayText() ;
		}
		else
		{
		  dateTovalidResult.setValidResult(true) ;
		  validationMessage=null;
		}

		dateTovalidResult.setInvalidMessage(validationMessage) ; 

		this.calcCountOfPublicHolidays();
		this.calcWorkingDays();
		
	}
	
	public void afterChange(Attribute att) {
		this.refreshCalc() ;
	}
	
	public void beforeChange(Attribute att) throws Exception {
				
	}
	public void setTimeBetween(long timeBetween) {
		this.timeBetween = timeBetween;
	}
	public long getTimeBetween() {
		return timeBetween;
	}

public BigDecimal getDateDifferenceInHours()
	{
		BigDecimal value = new BigDecimal(this.m_dateTo.getTime()- this.m_dateFrom.getTime()).divide(new BigDecimal(1000*60*60),5);
		return value;
	}
	public BigDecimal getDateDifferenceInMinutes()
	{
		BigDecimal value = new BigDecimal(this.m_dateTo.getTime()- this.m_dateFrom.getTime()).divide(new BigDecimal(1000*60),5);
		return value;
	}
	public BigDecimal getDateDifferenceInSeconds()
	{
		BigDecimal value = new BigDecimal(this.m_dateTo.getTime()- this.m_dateFrom.getTime()).divide(new BigDecimal(1000),5);
		return value;
	}
}
