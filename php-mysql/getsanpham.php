<?php


include "connect.php";
$page = $_GET['page'];
$idsp = $_POST['idsanpham']; 


$space = 30;
$limit = ($page - 1) * $space;

$query = "SELECT * FROM sanpham WHERE idsanpham = $idsp LIMIT $limit, $space";   
$data = mysqli_query($conn , $query);	
class Sanpham{		
	function Sanpham($id , $tensp , $giasp , $hinhanhsp , $motasp , $idsanpham){
		$this -> id 		= $id;
		$this -> tensp 		= $tensp;
		$this -> giasp 		= $giasp;
		$this -> hinhanhsp 	= $hinhanhsp;
		$this -> motasp 	= $motasp;
		$this -> idsanpham 	= $idsanpham;	
	}}


$mangsanpham = array();		
while($row = mysqli_fetch_assoc($data)){
array_push($mangsanpham, new Sanpham(
	$row['id'],
	$row['tensanpham'], 
	$row['giasanpham'],
	$row['hinhanhsanpham'],
	$row['motasanpham'],
	$row['idsanpham']));
}
echo json_encode($mangsanpham);	

?>