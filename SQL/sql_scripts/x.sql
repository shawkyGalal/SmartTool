begin

insert into edge.sb_mar_certif (inv_no ,order_no , proj_code 
, sabic_claim_no, SB_DATE, e_conv_from, e_conv_to ,price, qtty, inv_val, si 
	, RATE1, RATE2 , basic_contr 
, war_contr , tot, prod , e_vessel,cust_id, currency_code , cert_no  ) 
values ('80140901','80140901','Y','155515'
,'26-06-2004','JEDDAH ','AS PER CERT',TO_NUMBER('830.00'),TO_NUMBER('99.00')
	,TO_NUMBER('82170.00'),TO_NUMBER('90387.00'),TO_NUMBER('0.5476'),TO_NUMBER('0.1924'),TO_NUMBER('49.50')
,TO_NUMBER('17.39'),TO_NUMBER('66.89'),'HDPE',' KAGA', '3229','USD' , -1 );

exception
	when others then
		raise_application_error( -20111, 'error'||sqlerrm);
end;
/


