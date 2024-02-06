<?php 
    $phpHandle = fopen("http://www.google.com/complete/search?hl=en&js=true&qu=" . $_GET["query"], "r"); 
    while (!feof($phpHandle)){
      $textData = fgets($phpHandle);
      echo $textData;
    }
    fclose($phpHandle);
?>
