<?php

$connection = new mysqli("localhost", "root","","online_store_ecommerce_app_db");

$sqlEmailCheck=$connection->prepare("select * from app_users_table where email=?");
$sqlEmailCheck->bind_param("s", $_GET["email"]);
$sqlEmailCheck->execute();
$checkResult=$sqlEmailCheck->get_result();

if($checkResult->num_rows==0){
$sqlCommand = $connection->prepare("insert into app_users_table values(?,?,?)");
$sqlCommand->bind_param("sss", $_GET["email"],$_GET["name"],$_GET["password"]);
$sqlCommand->execute();
echo 'User is Registered';
} else {
    echo 'A user with same Email already exists';    
}

