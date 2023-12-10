CREATE OR REPLACE PACKAGE  SPU_Projection IS
  
  type projectionRecord is record (
					t_CV number, 
					f_CV number,
				  f_SI number,
				  f_comm number,
				  f_admin number,
				  f_risk number,
				  f_SF number,
				  gross_payment NUMBER,
				  act_premuim number,
				  act_premuim_with_loading number,
				  age number
				  );
  type projectionTable is table of projectionRecord index by binary_integer; 
  
  type loading_DATA_TYPE is table of TBLPROPOSAL_LOADINGS%ROWTYPE index by binary_integer;

  
  projectionData1 projectionTable;
  projectionData2 projectionTable;
  projectionData3 projectionTable;
  loading_data loading_DATA_TYPE;
  IS_loading_data_from_DB BOOLEAN := true;
  TYPE installment IS RECORD(inst number, age number); 
  TYPE installments IS TABLE OF installment INDEX BY BINARY_INTEGER;
  
 	proposal_data  tblproposals%ROWTYPE;
  invest1 NUMBER;   invest2 NUMBER;   invest3 NUMBER; 
  client_sex VARCHAR(1);
  --pkg_proposal_id NUMBER;
  --pkg_PRDCR_1_USER_ID NUMBER;
  --Type table_of_tables is Table of TABLE_OF_NUMBERS INDEX BY BINARY_INTEGER;
  
  -- Hide the following function to be as a private for this package 
  -- "can not be acces from outside of the package" 
  --Function calc_CV (--cv_rate_id varchar2,
  --									age Number, 
  --                  duration number, pay_years number, 
  --                  invest number, cv_class number, cv_Sex varchar2, 
  --                  SI number, m_plan number, premium number) return projectionTable ;
function get_projection_data1 return  projectionTable;
function get_projection_data2 return  projectionTable; 
function get_projection_data3 return  projectionTable;  

procedure set_all_CV_data(v_proposal_no NUMBER, v_invest1 NUMBER, v_invest2 NUMBER, v_invest3 NUMBER);
  --procedure render_header_table(file_id text_io.file_type);
  
  --function get_file_id(file_name varchar2) return text_io.file_type;
  
/*  procedure render_office_only( file_name varchar2--,
															--data1 projectionTable,
															--data2 projectionTable,
															--data3 projectionTable,
															--duration number
                    				);
*/
  
 /* procedure render_CV(	html_file_name varchar2,
  											invest1 number,
  											invest2 number,
  											invest3 number,
												duration number, pay_years number, 
                        SI number, plan_desc varchar2, premium number, ALB number, 
                        retirment number, Pay_mode varchar2,
                        DOB DATE, basic_prem number,
                        rider_prem number, side_fund number,
                        client_name varchar2, propos_no number
                        );
  */                      
 --procedure render_Loading_data(l_data loadingTable);
 --function get_loading_data_from_form return loading_DATA_TYPE;
 function get_loading_data_from_db return loading_DATA_TYPE;
 
  
 /*
 procedure render_all_installments(v_proposal_id number, v_user_id number, file_name varchar2 ) ;
 */
 
 PROCEDURE initialize(v_proposal_no NUMBER, v_invest1 NUMBER , v_invest2 NUMBER , v_invest3 NUMBER);

function get_side_fund_premium1( period number, 			-- period of the sf investment
																invest number, 			-- investment ratio during the sf period in % ,
																gross_amount number -- the gross amount of the sf after the period
															) return number;
procedure save_projection_data_in_DB (v_proposal_no number, v_invest1 number, v_invest2 number, v_invest3 number, v_side_fund number);

END;
/



