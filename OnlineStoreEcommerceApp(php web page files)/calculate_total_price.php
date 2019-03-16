<?php

$connection = new mysqli("localhost", "root","","online_store_ecommerce_app_db");
$total=$connection->prepare("select price,amount FROM electronic_table INNER JOIN invoice_details ON electronic_table.id=invoice_details.pid where invoice_details.invoice-num=?");
$total->bind_param("i",$_GET["invoice_num"]);
$total->execute();

$result=$total->get_result();
$totalPrice=0;
        
        while($row=$result->fetch_assoc()){
    
    $totalPrice=$totalPrice+($row["price"]*$row["amount"]);
    
        }
        echo $totalPrice;

