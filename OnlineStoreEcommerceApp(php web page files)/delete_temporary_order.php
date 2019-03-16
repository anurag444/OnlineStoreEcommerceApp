<?php

$connection = new mysqli("localhost", "root","","online_store_ecommerce_app_db");
$deleteProducts=$connection->prepare("select * from temporary_order where email=?");
$deleteProducts->bind_param("s",$_GET["email"]);
$deleteProducts->execute();

