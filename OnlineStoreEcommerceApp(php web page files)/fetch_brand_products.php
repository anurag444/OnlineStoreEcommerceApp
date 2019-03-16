<?php

$connection = new mysqli("localhost", "root","","online_store_ecommerce_app_db");
$fetchProducts=$connection->prepare("select * from electronic_table where brand=?");
$fetchProducts->bind_param("s",$_GET["brand"]);
$fetchProducts->execute();

$result = $fetchProducts->get_result();

$eparray = array();

while($row=$result->fetch_assoc()){
    
    array_push($eparray, $row);
}

echo json_encode($eparray);


