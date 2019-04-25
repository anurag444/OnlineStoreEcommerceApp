<?php

$connection = new mysqli("localhost", "root","","online_store_ecommerce_app_db");
$sqlLoginCheck=$connection->prepare("select * from app_users_table where email=? and password=?");
$sqlLoginCheck->bind_param("ss", $_GET["email"] , $_GET["password"]);
$sqlLoginCheck->execute();

$checkResult=$sqlLoginCheck->get_result();

if($checkResult->num_rows==0){
    echo 'User Does Not Exist';
}
else{
    echo 'User Exists';
}