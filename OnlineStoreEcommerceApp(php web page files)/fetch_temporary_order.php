<?php

$connection = new mysqli("localhost", "root","","online_store_ecommerce_app_db");
$sqlCommandFetch=$connection->prepare("SELECT id,name,price,email,amount FROM temporary_order INNER JOIN electronic_table ON temporary_order.id=electronic_table.id WHERE email = ?");
$sqlCommandFetch->bind_param("s", $_GET["email"]);
$sqlCommandFetch->execute();

$result=$sqlCommandFetch->get_result();

$temporary = array();

while ($row=$result->fetch_assoc()){
    
    array_push($temporary, $row);
}
echo json_encode($temporary);


