CREATE OR REPLACE PACKAGE BODY SPU_Projection AS


	function get_projection_data1 return  projectionTable is
	begin 		return projectionData1; 	end;
	--------------------------------------------------------
	function get_projection_data2 return  projectionTable is
	begin 		return projectionData2; 	end;
	--------------------------------------------------------
	function get_projection_data3 return  projectionTable is
	begin  		return projectionData3; 	end;
	--------------------------------------------------------

  function calc_CV ( v_proposal_no NUMBER,
                    invest NUMBER
                    ) return projectionTable is

 	projectionData projectionTable;

  --act_Premium number;
  comm_Row takaful.tblcomm_loadings%rowtype;
  comm number :=0;
  admn number:=0;
  Row_Count number:=0;
  vest_rate number :=0;
  risk_rate number:=0; risk_rate_0 number:=0;   risk_rate_700 number:=0;
  risk_rate_With_loading number :=0;
  running_age NUMBER;

  ----------For the Basic Plan 0--------		  --------For the 700 rider----------------
  tot_loading_factor_0 number:=1;							tot_loading_factor_700 number:=1;
  load_amount_premuim_plus_0 number:=0;				load_amount_premuim_plus_700 number:=0;
  load_amount_CV_minus_0 number :=0;					load_amount_CV_minus_700 number :=0;

  Premuim_plus boolean; --loading_data_from_DB boolean:= True;
  is_bundled_rider_included boolean :=true;
  counter number:=0;
  

  cursor loading_data (rider_id number, proposal_id number, user_id number) is
		  select * from tblproposal_loadings t
			where t.loading_on = rider_id
			and t.props_1_proposal_id = proposal_id
			and t.props_1_prdcr_1_user_id=user_id;




	BEGIN
				-- initialization ----
				projectionData(0).f_SI:=0;
				projectionData(1).f_SI:=Proposal_data.SUM_insured;
				projectionData(0).t_CV:=0;
				projectionData(0).f_SI:=0;
				projectionData(1).act_premuim:=proposal_data.ANNUALIZED_PREMIUM;
				projectionData(1).act_premuim_with_loading:=proposal_data.ANNUALIZED_PREMIUM;

				projectionData(0).gross_payment:=0;
				-- End of initialization ----

				-- check if the bundeled rider ia included in the proposal it self or not
				--By shawky Foda
				--Date 06-04-2003
				select count(*) into counter from tblriders_per_proposals t , tblriders r
				where t.RDRS_RIDER_ID = r.rider_id
					and t.PROPS_1_PRDCR_1_USER_ID = proposal_data.prdcr_1_user_id
					and t.PROPS_1_PROPOSAL_ID = proposal_data.proposal_id
					and upper(r.bundled) = 'Y';
					if counter =0 then 
						is_bundled_rider_included:= false;
					end if;
					
    running_age :=proposal_data.alb;
		FOR i IN 1 .. proposal_data.maturity_age  LOOP
					IF i> proposal_data.PREMIUM_PAY_YEARS then
						projectionData(i).act_premuim :=0;
					else
						projectionData(i).act_premuim :=proposal_data.ANNUALIZED_PREMIUM;
					end if;

				--1- Getting Loading rate and amount-------------------
				--- 1-1 getting loading factors


				tot_loading_factor_0 :=1;					tot_loading_factor_700 :=1;
				load_amount_premuim_plus_0:=0;		load_amount_premuim_plus_700:=0;
				load_amount_CV_minus_0:=0;				load_amount_CV_minus_700:=0;

				if (IS_loading_data_from_DB) then
					---------------- By loading data from database-------------
						  -----------Basic plan Loadings-------------
							for load_0 in loading_data(0, proposal_data.proposal_id,proposal_data.PRDCR_1_USER_ID ) loop
										if running_age+1>=load_0.LOAD_AGE  and running_age+1<=load_0.LOAD_AGE + load_0.LOAD_DUR then
												tot_loading_factor_0:=tot_loading_factor_0* nvl(load_0.LOAD_PERMILLE,1);
												if load_0.pluse_prm='Y' then 	--add to the premium
													load_amount_premuim_plus_0:=load_amount_premuim_plus_0+nvl(load_0.LOAD_amount,0);
												else 													-- or deducted from The CV
													load_amount_CV_minus_0:= load_amount_CV_minus_0+nvl(load_0.LOAD_amount,0);
												end if;
										end if;
							end loop;

							-----------End of Basic plan Loadings-------
							-----------bundled rider 700 loading--------
							if is_bundled_rider_included then -- no need to calculate it if the bundled rider is already not included in the proposal
								for load_700 in loading_data(700, proposal_data.proposal_id,proposal_data.PRDCR_1_USER_ID ) loop
									if running_age+1>=load_700.LOAD_AGE  and running_age+1<=load_700.LOAD_AGE + load_700.LOAD_DUR then
													tot_loading_factor_700:=tot_loading_factor_700* nvl(load_700.LOAD_PERMILLE,1);
													if load_700.pluse_prm='Y' then --add to the premium
														load_amount_premuim_plus_700:=load_amount_premuim_plus_700+nvl(load_700.LOAD_amount,0);
													else 													-- or deducted from The CV
														load_amount_CV_minus_700:= load_amount_CV_minus_700+nvl(load_700.LOAD_amount,0);
													end if;
											end if;
								end loop;
							end if;
							-----------bundled rider 700 loading--------
					---------------- End of By loading data from Data base------

				end if; ----------------End of Checking loading data from -------------

				load_amount_CV_minus_0:=load_amount_CV_minus_0 *proposal_data.sum_insured/1000; -- actual Loading amount that should be deducted from CV
				load_amount_premuim_plus_0:=load_amount_premuim_plus_0*proposal_data.sum_insured/1000; -- actual Loading amount that should be added to the Premuim

				load_amount_CV_minus_700:=load_amount_CV_minus_700 *proposal_data.sum_insured/1000; -- actual Loading amount that should be deducted from CV due to 700
				load_amount_premuim_plus_700:=load_amount_premuim_plus_700*proposal_data.sum_insured/1000; -- actual Loading amount that should be added to the Premuim due to 700

				--  effect of loading amount by adding to the premuim
				projectionData(i).act_premuim_with_loading:=projectionData(i).act_premuim+load_amount_premuim_plus_0 + load_amount_premuim_plus_700;
				projectionData(i).gross_payment:=projectionData(i-1).gross_payment+ projectionData(i).act_premuim_with_loading;

				-----------------------End of Getting Loading rate and amount---------------

				--  1- calculate Comm and admin--------
				select count(*) into Row_Count from takaful.tblcomm_loadings t
				where pln_plan_id = proposal_data.pln_plan_id ;

				if Row_Count >0 then
					if i<=  Row_Count then
							select * into comm_Row  from takaful.tblcomm_loadings t  where pln_plan_id = proposal_data.pln_plan_id  and dur = i;
					else
							select * into comm_Row  from takaful.tblcomm_loadings t  where pln_plan_id = proposal_data.pln_plan_id  and dur = Row_Count;
					end if;
					comm:= projectionData(i).act_premuim * comm_Row.comm_rate /100;
					admn:= projectionData(i).act_premuim * (comm_Row.admin_rate+ comm_Row.expense_rate) /100;
				else
				 	comm:= 0;
					admn:= 0;
				end if;
				 projectionData(i).f_comm:= comm;
				 projectionData(i).f_admin:= admn;
				-------------------------- end of calculate comm and admin--------------------------------


				-------------------------- 2- calculate Vestmanet rate -----------------------------------
				select count(*) into row_count from takaful.tblvestments t where pln_plan_id =proposal_data.pln_plan_id ;
				if row_count >0 then
					if i<= Row_count then
						select rate/100 into vest_rate from takaful.tblvestments t where pln_plan_id =proposal_data.pln_plan_id  and dur=i;
					else
						select rate/100 into vest_rate from takaful.tblvestments t where pln_plan_id =proposal_data.pln_plan_id  and dur=Row_Count;
					end if;
				end if;
				-------------------------- end of calculate Vestmanet rate--------------------------------
				------------------------------------------------------------------------------------------

				---------------------------3- calculate Risk Rate-----------------------------------------
				---- By Stored Procedure-------

				risk_rate:=takaful.spu_investments.get_rate(running_age, proposal_data.maturity_age, proposal_data.class_id, client_sex, proposal_data.pln_plan_id, 'N')*1000;

				-- Here risk rate should split into two, one for baisc plan and one for the bundeled 700 rider i.e
				SELECT 	decode(SUM(a.bundled_rate),'',0,SUM(a.bundled_rate)) into Risk_rate_700
						FROM 		tblriders a, tblplan_riders b
						where 	a.rider_id = b.RDRS_RIDER_ID
						and			b.PLN_PLAN_ID = proposal_data.pln_plan_id
						and			upper(bundled) = 'Y';

				Risk_rate_0 := Risk_rate - Risk_rate_700;

				-- By Shawky Foda
				-- Date 06-04-2003
				--Purpose : To Remove the effect of bundeled rider (increasing the risk rate ) 
				--           if the rider already not included in the proposal
				if is_bundled_rider_included = false then
					Risk_rate_700 := 0;
				end if;
				-- End of By Shawky Foda
							
				-- modifying risk factor with loading rate
				risk_rate_With_loading:= Risk_rate_700 * tot_loading_factor_700 + Risk_rate_0 * tot_loading_factor_0;
				---------------------------End of calculate Risk Rate--------------------------------------

				-------------- calculating and storing Cash Values CV and Sum@risk f_SI-------------------
				projectionData(i).t_cv:= (projectionData(i-1).t_cv
																	+ projectionData(i).act_premuim
																	- projectionData(i).f_admin
																	- projectionData(i).f_comm
																	- (proposal_data.sum_insured*risk_rate_With_loading/1000))
				                             /(1-risk_rate_With_loading/1000);

				--effect of loading Amount by deducting from CV , taking into consideration risk rate------
				projectionData(i).t_cv:=	projectionData(i).t_cv - load_amount_CV_minus_0/(1-risk_rate_With_loading/1000)
																									 - load_amount_CV_minus_700/(1-risk_rate_With_loading/1000);

				if projectionData(i).t_cv<0 then
				--	projectionData(i).t_cv:=0;
				null;
				end if;

				projectionData(i).f_SI:=proposal_data.sum_insured-projectionData(i).t_cv;
				projectionData(i).f_risk:=projectionData(i).f_SI*risk_rate_With_loading/1000;

				if projectionData(i).f_risk <0 then -- recalculate t_cv and f_SI without risk
					projectionData(i).f_risk:=0;
					projectionData(i).t_cv:= projectionData(i-1).t_cv + projectionData(i).act_premuim-projectionData(i).f_admin-projectionData(i).f_comm;
					projectionData(i).f_SI:=proposal_data.sum_insured-projectionData(i).t_cv;
				end if;

					projectionData(i).t_cv:=	projectionData(i).t_cv*(1+invest);
					projectionData(i).f_cv:=	projectionData(i).t_cv*vest_rate;


				running_age:=running_age+1;
				projectionData(i).age:=running_age;
	END LOOP; ---- End Loop of Years of policy ----
	return projectionData;

	--------Exception Handler Section------
	Exception

	When others  then
  NULL;
	--Message('Error in pu_Projection.calc_cv() procedure........Error ' || to_char(sqlcode) || '-' ||sqlerrm );

END;	---CalcCV End------

-----------this procedure set all data in the pu_projection package properties ---------
procedure set_all_CV_data(v_proposal_no NUMBER, v_invest1 NUMBER, v_invest2 NUMBER, v_invest3 NUMBER) is

	BEGIN
      initialize(v_proposal_no, v_invest1,v_invest2,v_invest3);
			projectionData1 := calc_CV (proposal_data.proposal_no, invest1) ;
			projectionData2 := calc_CV (proposal_data.proposal_no, invest2) ;
		  projectionData3 := calc_CV (proposal_data.proposal_no, invest3) ;

	end;

 ------------------
----------------------------

--- This function gets Loading data from database
function get_loading_data_from_db return  loading_DATA_TYPE is
		CURSOR LOADING_DATA_FROM_DB_CUR IS SELECT * FROM TBlPROPOSAL_LOADINGS t
				  where t.props_1_proposal_id = proposal_data.proposal_id
					and t.props_1_prdcr_1_user_id =proposal_data.PRDCR_1_USER_ID;
		loading_data_from_DB loading_DATA_TYPE;
		counter binary_integer :=0;
begin

				OPEN  loading_data_from_DB_CUR ;
							LOOP
								FETCH loading_data_from_DB_CUR INTO loading_data_from_DB(counter);
								counter:=counter+1;
								EXIT WHEN loading_data_from_DB_CUR%NOTFOUND;
							END LOOP;
				CLOSE loading_data_from_DB_CUR;
				return loading_data_from_DB;
end;-- end of get_loading_data_from_db-----


------
procedure set_loading_data(loading_data loading_data_type)is
begin
	spu_projection.loading_data:=loading_data;
end;

-------------------------------------------------------------------------------------------------------------------

PROCEDURE initialize(v_proposal_no NUMBER, v_invest1 NUMBER , v_invest2 NUMBER , v_invest3 NUMBER) IS

BEGIN
SELECT * INTO proposal_data FROM  tblproposals t WHERE t.proposal_no = v_proposal_no;
invest1:=v_invest1;
invest2:=v_invest2;
invest3:=v_invest3;
client_sex:='M'; --- it supose to get its actual value from tblclients table

END;
------------------------------------------------------------------------------------------------
function get_side_fund_premium1( period number, 			-- period of the sf investment
																invest number, 			-- investment ratio during the sf period in % ,
																gross_amount number -- the gross amount of the sf after the period
															) return number is
--- return_value = gross_amount/sum(1+invest)**n
return_value number := 0;
sum1 number:=0; 
L_sf_percent number :=0;
L_sf_amount number :=0;

begin
	-- getting admin fee % for side fund ( deducted only from the first installment ) 
	select sf_percent , sf_amount into L_sf_percent, L_sf_amount from TBL_REFERENCES; 
	if period = 0 then
		raise_application_error(-20500,'Value Should Not be Zero')	;
	end if;
	
	for i in 1..period-1 loop
		sum1:=sum1 + power ( (1+invest) , i );
	end loop;
	-- the first installment, which will stay invested whole the period, will be deducted by L_sf_percent
	sum1:=sum1 + (1-L_sf_percent/100)* power( (1+invest), period-1 );
	
	return_value := gross_amount/sum1;
	
		if return_value * L_sf_percent/100 > L_sf_amount then
		
			--recalculate based on fixed deducted value = L_sf_amount
			sum1:=0;
			for i in 1..period loop
			 	sum1:=sum1 + power ( (1+invest) , i );
			end loop;
			return_value := (gross_amount + L_sf_amount*power( (1+invest), period ) )/sum1;
		
		end if;
	return return_value;
end;

--------------------------------------------------------------------------------------------------------------------------------
procedure save_projection_data_in_DB (v_proposal_no number, v_invest1 number, v_invest2 number, v_invest3 number, v_side_fund number) is
X NUMBER:=0;
l_side_fund number :=0;
l_sf_percent_fees number := 0;
L_sf_amount_fees number :=0;
begin
	SavePoint abc;

			--- deleting previous projection for the specified proposal no. (if exist)
			
			delete from takaful.tblprojection where proposal_no = v_proposal_no;
			
			---------------------------------------------------------------------------------------------------------			
			for i in 1..projectiondata1.count-1 loop
			-- Calculating accumulated Side fund
				if i= 1 then -- getting adimn fees only for the first year.
					select sf_percent , sf_amount into L_sf_percent_fees, L_sf_amount_fees from TBL_REFERENCES; 
					
					if L_sf_percent_fees* v_side_fund/100 < L_sf_amount_fees then
						l_side_fund:= (l_side_fund + v_side_fund*(1-L_sf_percent_fees/100) )*(1+v_invest1); 
					else
						l_side_fund := (l_side_fund + v_side_fund- L_sf_amount_fees )*(1+v_invest1); 
					end if;
				
				else
					l_side_fund:= (l_side_fund + v_side_fund)*(1+v_invest1);
		
				end if;
			
										
			--- Inserting Data for the first investment ratio----			
			
				insert into takaful.tblprojection ( proposal_no, invest, Year_seq,  age, cv, Sum_at_risk, comm_fee, admin_fee, risk_fee, Gross_payment, Premium, Loaded_premium,side_fund ) 
							values 		(v_proposal_no,
												 v_invest1,
												 i , 
												 round(projectionData1(i).age),
												 round(projectionData1(i).f_cv,2),
												 round(projectionData1(i).f_SI,2),
												 round(projectionData1(i).f_comm,2),
												 round(projectionData1(i).f_admin,2),
												 round(projectionData1(i).f_Risk,2),
												 round(projectionData1(i).gross_payment,2),
												 round(projectionData1(i).act_premuim,2),
												 round(projectionData1(i).act_premuim_with_loading,2),
												 round(l_side_fund,2)
												 );
			end loop;
	
		---------------------------------------------------------------------------------------------------------			
			l_side_fund:=0;
			for i in 1..projectiondata2.count-1 loop
				-- Calculating accumulated Side fund
		if i= 1 then -- getting adimn fees only for the first year.
					select sf_percent , sf_amount into L_sf_percent_fees, L_sf_amount_fees from TBL_REFERENCES; 
					
					if L_sf_percent_fees* v_side_fund /100< L_sf_amount_fees then
						l_side_fund:= (l_side_fund + v_side_fund*(1-L_sf_percent_fees/100) )*(1+v_invest2); 
					else
						l_side_fund := (l_side_fund + v_side_fund- L_sf_amount_fees )*(1+v_invest2); 
					end if;
				
				else
					l_side_fund:= (l_side_fund + v_side_fund)*(1+v_invest2);
		
				end if;
			
				--- Inserting Data for the second investment ratio----						
			
						insert into takaful.tblprojection ( proposal_no, invest, Year_seq,  age, cv, Sum_at_risk, comm_fee, admin_fee, risk_fee, Gross_payment, Premium, Loaded_premium,side_fund ) 
							values 		(v_proposal_no,
												 v_invest2,
												 i , 
												 round(projectionData2(i).age),
												 round(projectionData2(i).f_cv,2),
												 round(projectionData2(i).f_SI,2),
												 round(projectionData2(i).f_comm,2),
												 round(projectionData2(i).f_admin,2),
												 round(projectionData2(i).f_Risk,2),
												 round(projectionData2(i).gross_payment,2),
												 round(projectionData2(i).act_premuim,2),
												 round(projectionData2(i).act_premuim_with_loading,2),
												 round(l_side_fund,2)
												 );
			end loop;
			---------------------------------------------------------------------------------------------------------			
			l_side_fund:=0;
			for i in 1..projectiondata3.count-1 loop
				-- Calculating accumulated Side fund
				if i= 1 then -- getting adimn fees only for the first year.
					select sf_percent , sf_amount into L_sf_percent_fees, L_sf_amount_fees from TBL_REFERENCES; 
					
					if L_sf_percent_fees* v_side_fund/100 < L_sf_amount_fees then
						l_side_fund:= (l_side_fund + v_side_fund*(1-L_sf_percent_fees/100) )*(1+v_invest3); 
					else
						l_side_fund := (l_side_fund + v_side_fund- L_sf_amount_fees )*(1+v_invest3); 
					end if;
				
				else
					l_side_fund:= (l_side_fund + v_side_fund)*(1+v_invest3);
		
				end if;
				
				--- Inserting Data for the third investment ratio----			
				insert into takaful.tblprojection ( proposal_no, invest, Year_seq,  age, cv, Sum_at_risk, comm_fee, admin_fee, risk_fee, Gross_payment, Premium, Loaded_premium,side_fund ) 
							values 		(v_proposal_no,
												 v_invest3,
												 i , 
											   round(projectionData3(i).age),
												 round(projectionData3(i).f_cv,2),
												 round(projectionData3(i).f_SI,2),
												 round(projectionData3(i).f_comm,2),
												 round(projectionData3(i).f_admin,2),
												 round(projectionData3(i).f_Risk,2),
												 round(projectionData3(i).gross_payment,2),
												 round(projectionData3(i).act_premuim,2),
												 round(projectionData3(i).act_premuim_with_loading,2),
												 round(l_side_fund,2)
												 );
			end loop;
			commit;
			
			exception 
				when others then 
						--message ('Unable to Save Projection Data');
						rollback to abc;

end;


END;-- Package end
/