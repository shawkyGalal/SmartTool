-- Drop table                                                                   
drop table LU_ROOT cascade constraints;                                         
-- Create table                                                                 
create table LU_ROOT                                                            
(                                                                               
  ID         NUMBER not null,                                                   
  CODE       VARCHAR2(20) not null,                                             
  Parent_id  NUMBER,                                                            
  Prev_Parent_id  NUMBER,                                                       
  Header_id  NUMBER,
  Prev_Header_id  NUMBER,
  A_DSC      VARCHAR2(200) not null,                                            
  E_DSC      VARCHAR2(200) not null,                                            
  Active     CHAR(1) DEFAULT 'T',                                               
  deleted    CHAR(1) DEFAULT 'F',                                               
  Modify_Seq    NUMBER DEFAULT 0,                                                         
  Update_Status   Number DEFAULT 0                                              
);                                                                              
-- Create/Recreate primary, unique and foreign key constraints                  
alter table LU_ROOT                                                             
  add constraint PK_LU_ROOT primary key (ID);                                   
alter table LU_ROOT                                                             
  add constraint UQ_LU_ROOT_CODE unique (CODE);                                 
alter table LU_ROOT                                                             
  add constraint FK_LU_ROOT foreign key (PARENT_ID)                             
  references LU_ROOT (ID);                                                      
alter table LU_ROOT                                                             
  add constraint CHK_LU_ROOT_Parent CHECK(parent_id <>ID);                      
                                                          
WHENEVER SQLERROR NONE;                                                         
-- Create/Recreate check constraints                                            
alter table LU_ROOT                                                             
  add constraint CHK_LU_ROOT_Active                                             
  check (Active='F'or Active='T');                                              
alter table LU_ROOT                                                             
  add constraint CHK_LU_ROOT_Deleted                                            
  check (Deleted='F'or Deleted='T');                                            
-- Add comments to the columns                                                  
comment on column LU_ROOT.ID                                                    
  is 'Primary Key';                                                             
comment on column LU_ROOT.CODE                                                  
  is 'User Code';                                                               
comment on column LU_ROOT.Parent_id                                             
  is 'Parent Record';                                                           
comment on column LU_ROOT.Header_id                                             
  is '';                                                                      
comment on column LU_ROOT.E_DSC                                                 
  is 'English Desc';                                                            
comment on column LU_ROOT.A_DSC                                                 
  is 'Arabic Desc';                                                             
comment on column LU_ROOT.Active                                                
  is 'For User Activation';                                                     
comment on column LU_ROOT.Deleted                                               
  is 'For Logical Deletion';                                                    
comment on Table LU_ROOT                                                        
 is ' ROOT («·ﬁ«⁄œ…)';                                                          
INSERT INTO LU_ROOT                                                             
  (ID, code,a_dsc, e_dsc )                                                      
   VALUES                                                                       
  (0, '0', '«·ﬁ«⁄œ…'||' ' ,'ROOT'||' ');                                        
Commit;                                                                         
/                                                                               
