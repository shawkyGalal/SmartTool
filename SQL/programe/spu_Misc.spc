create or replace package spu_Misc is
 -- Author  : FODA_SH
  -- Created : 01/03/2003 3:12:34 PM
  -- Purpose :


procedure get_user_proposal_id_by_Polcy(v_policy number , v_user_id in out number , v_proposal_id in out number );

procedure get_user_id_and_proposal_id(v_proposal_no number , v_user_id in out number , v_proposal_id in out number );
PROCEDURE write_to_file ;
function get_side_fund_premium( period number, 			-- period of the sf investment
																invest number, 			-- investment ratio during the sf period in % ,
																gross_amount number -- the gross amount of the sf after the period
															) return number;

end spu_Misc;
/
