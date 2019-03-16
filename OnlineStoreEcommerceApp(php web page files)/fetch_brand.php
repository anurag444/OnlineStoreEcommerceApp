<?php

$connection = new mysqli("localhost", "root","","online_store_ecommerce_app_db");
$selectBrands=$connection->prepare("select distinct brand from electronic_table");
$selectBrands->execute();//distinct is used when there are many brands

$brandResult=$selectBrands->get_result();

$brands = array();

while ($row= $brandResult->fetch_assoc()){
    
    array_push($brands, $row);
}
echo json_encode($brands);


