<?php

$connection = new mysqli("localhost", "root","","online_store_ecommerce_app_db");
$sqlCommand=$connection->prepare("insert into temporary_order values(?,?,?)");
$sqlCommand->bind_param("sii", $_GET["email"],$_GET["id"],$_GET["amount"]);
$sqlCommand->execute();
