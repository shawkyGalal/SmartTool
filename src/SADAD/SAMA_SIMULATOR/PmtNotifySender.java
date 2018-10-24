package SADAD.SAMA_SIMULATOR;
import Support.HttpPoster;

public class PmtNotifySender 
{
  public PmtNotifySender() throws Exception
  {
  HttpPoster http = new HttpPoster("http://127.0.0.1:8988/SADAD/servlet/SADAD.PmtNotifyReciver",  "",  "userName","pass");
  String PmtNotifyRq = "<?xml version='1.0'?>"
                            +"\n"+ " <SADAD>"
                            +"\n"+ "<SignonRq>"
                            +"\n"+ " <ClientDt>2006-04-08T05:32:26</ClientDt>"
                            +"\n"+ " <LanguagePref>en-gb</LanguagePref> "
                            +"\n"+ " <SignonProfile>"
                            +"\n"+ " <Sender>SADAD-001</Sender> "
                            +"\n"+ " <Receiver>003</Receiver> "
                            +"\n"+ " <MsgCode>PNOTRQ</MsgCode> "
                            +"\n"+ " </SignonProfile>"
                            +"\n"+ " </SignonRq>"
                            +"\n"+ " <BillerSvcRq>"
                            +"\n"+ " <RqUID>109FB7B4-3E1A-D0E6-E043-0A10100DD0E6</RqUID> "
                            +"\n"+ " <PmtNotifyRq>"
                            +"\n"+ " <PmtRec>"
                            +"\n"+ " <PmtTransId>"
                            +"\n"+ " <PmtId>767429</PmtId> "
                            +"\n"+ " <PmtIdType>SPTN</PmtIdType> "
                            +"\n"+ " </PmtTransId> "
                            +"\n"+ " <PmtTransId> "
                            +"\n"+ " <PmtId>X00003453764330012471A6408000001</PmtId> "
                            +"\n"+ " <PmtIdType>BNKPTN</PmtIdType> "
                            +"\n"+ " </PmtTransId>"
                            +"\n"+ " <PmtStatus>"
                            +"\n"+ " <PmtStatusCode>PmtNew</PmtStatusCode> "
                            +"\n"+ " </PmtStatus>"
                            +"\n"+ " <PmtInfo>"
                            +"\n"+ " <CurAmt>374</CurAmt> "
                            +"\n"+ " <PrcDt>2006-04-08T05:14:13</PrcDt> "
                            +"\n"+ " <DueDt>2006-04-08T00:00:00</DueDt>  "
                            +"\n"+ " <BillNumber>5172025</BillNumber> "
                            +"\n"+ " <AccountId>"
                            +"\n"+ " <BillingAcct>3073821</BillingAcct> "
                            +"\n"+ " <BillerId>003</BillerId> "
                            +"\n"+ " </AccountId>"
                            +"\n"+ " <BankId>RJHISARI</BankId> "
                            +"\n"+ " <DistrictCode>11</DistrictCode> "
                            +"\n"+ " <BranchCode>43300</BranchCode> "
                            +"\n"+ " <AccessChannel>ATM</AccessChannel> "
                            +"\n"+ " <PmtMethod>ACTDEB</PmtMethod> "
                            +"\n"+ " <ServiceType>INSR</ServiceType>" 
                            +"\n"+ " </PmtInfo>"
                            +"\n"+ " </PmtRec>"
                            +"\n"+ " </PmtNotifyRq>"
                            +"\n"+ " </BillerSvcRq>"
                            +"\n"+ "</SADAD>";

  http.postRequest(PmtNotifyRq);
  String response = http.readResponse();
  }

  public static void main(String[] args) throws Exception
  {
    PmtNotifySender pmtNotifySender = new PmtNotifySender();
  }
}