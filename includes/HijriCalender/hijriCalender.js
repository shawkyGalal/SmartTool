function initializeCalenders (jdDateFieldId , hijriDateFieldId , jdInitialValue )
{
	try{
 
				var arabicCalendar = $.calendars.instance('ummalqura', 'ar'); 
				var englishCalendar = $.calendars.instance(); 
				//alert ( $('#' + hijriDateFieldId) ) ; 
				$('#' + hijriDateFieldId).calendarsPicker({calendar: arabicCalendar , onClose: 'hijriCalOnClose_'+ hijriDateFieldId });
				$('#' + jdDateFieldId ).calendarsPicker({calendar: englishCalendar , onClose: 'jdCalOnClose_'+ jdDateFieldId });
				
				$('#' + jdDateFieldId ).calendarsPicker('option', {dateFormat: selectedDateFormat});
				$('#' + hijriDateFieldId ).calendarsPicker('option', {dateFormat: selectedDateFormat});
				
				$('#' + jdDateFieldId ).calendarsPicker({showTrigger: '#calImg'});				
				
				// --------- Setting intial Jd Value and convertig to Hijri				
				$('#' + jdDateFieldId ).val(jdInitialValue) ;
				var dates = $('#' + jdDateFieldId ).val().split(',');
				//alert (dates[0]); 
				$('#' + jdDateFieldId ).calendarsPicker('setDate', dates);
				
				convertJdToHijri(jdDateFieldId , hijriDateFieldId) ;
				}
				catch (e) 
				{
					alert (e) ;
				}
			
}
	function convertJdToHijri(fromDateField , toDateField )
		{
			
			var jd ;
			try { 
			var dates = $('#'+fromDateField).calendarsPicker('getDate');
			var dayJd = dates[0].day() ; 
			var monthJd =dates[0].month() ; 
			var yearJd = dates[0].year() ; 
			jd = $.calendars.instance().newDate( parseInt(yearJd , 10) ,  parseInt(monthJd , 10 ) , parseInt(dayJd , 10  )) .toJD();
	        var hijriDate = $.calendars.instance('ummalqura').fromJD(jd).formatDate(selectedDateFormat); 
	        
	 		$('#'+toDateField).val(hijriDate); 
			$('#' + $('#toDateField' ).val()).calendarsPicker('setDate', dates);        
			} 
			catch (e) { 
				alert(e); 
				return; 
			} 
	    }
		
	
		function convertHijriToJd(fromDateField , toDateField )
		{
			var hd ;
			try 
			{ 
				var dates = $('#'+ fromDateField).calendarsPicker('getDate');
				var dayHd = dates[0].day() ; 
				var monthHd =dates[0].month() ; 
				var yearHd =  dates[0].year() ; 
				hd = $.calendars.instance('ummalqura').newDate( parseInt(yearHd , 10) ,  parseInt(monthHd , 10 ) , parseInt(dayHd , 10  )) .toJD();
		        var jdDate = $.calendars.instance().fromJD(hd).formatDate(selectedDateFormat); 
				$('#'+toDateField).val(jdDate);  
				$('#' + $('#toDateField' ).val()).calendarsPicker('setDate', dates);
				 
			} 
			catch (e) { 
				alert(e); 
				return; 
			} 
 		}