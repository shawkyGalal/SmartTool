<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
<title>Mail Reader</title>
</head>
<body>
<p align="center"><strong>Under Construction</strong></p>
<p align="center">Currently This page displays a list of input box
for foda.sh@smart-value.com</p>
<p align="center">&nbsp;</p>
<p>
<% 
Support.MailReaderForWeb.setOutx(out);
// For Gmail Account 
/*
Support.MailReaderForWeb.showMsgs("pop.gmail.com"  // Host
                        , "pop3" 
                        , "foda.sh@gmail.com"       // username
                        , "redsea11"                // Password
                        , true                      //verbose
                        , true                      // debug
                        , "INBOX"                   //mbox
                        , null                      //url
                        , 995                        //port
                        , true                      //showStructure
                        , true                      // saveAttachments
                        , false                     //showMessage
                        , true                      // m_showAlert
                        );
*/
// For Smart-value Account 
Support.MailReaderForWeb.showMsgs("mail.smart-value.com"  // Host
                        , "pop3" 
                        , "foda.sh@smart-value.com"  // username
                        , "redsea11"                // Password
                        , true                      //verbose
                        , true                      // debug
                        , "Inbox"                   //mbox
                        , null                      //url
                        , 110                       //port
                        , true                      //showStructure
                        , true                      // saveAttachments
                        , false                     //showMessage
                        , true                      // m_showAlert
                        );

%>
</p>
</body>
</html>
