<?php

$connection = new mysqli("localhost", "root","","online_store_ecommerce_app_db");
$Products=$connection->prepare("select * from temporary_order where email=?");
$Products->bind_param("s",$_GET["email"]);
$Products->execute();

$result = $Products->get_result();

$invoiceEmail=$connection->prepare("insert into invoice(email) values(?)");
$invoiceEmail->bind_param("s",$_GET["email"]);
$invoiceEmail->execute();

$invoicenum=$connection->prepare("select max(invoice_num) as latest_invoice_num from invoice where email=?");
$invoicenum->bind_param("s",$_GET["email"]);
$invoicenum->execute();
$invoiceresult=$invoicenum->get_result();
$invoicerow=$invoiceresult->fetch_assoc();

while($row=$result->fetch_assoc()){
    
    $invoicedetails=$connection->prepare("insert into invoice_details values(?,?,?)");
    $invoicedetails->bind_param("iii", $invoicerow["latest_invoice_num"],$row["pid"],$row["amount"]);
    $invoicedetails->execute();
    
    $deletetemporder=$connection->prepare("delete from temporary_order where email=? ");
    $deletetemporder->bind_param("s", $_GET["email"]);
    $deletetemporder->execute();
}


echo $invoicerow["latest_invoice_num"];
