<?php
require 'db.php';
function signUP($userName,$pswrd,$dbsetval) {
	
	echo $dbsetval;
	$conn = getDB ();
	
	if ($stmt = $conn->prepare ( "INSERT INTO authentication (UserName,Password,UserDataSet) VALUES ( ?, ?, false);" ) or die(mysql_error())) {
		$stmt->bind_param ('ss', $userName, $pswrd);
		
		if ($row_array ['result'] = $stmt->execute ())
			$row_array ['affected_rows'] = $stmt->affected_rows;
		
		echo json_encode ( $row_array );
		
		$stmt->close ();
	}
	
	$conn->close ();
}

function login($userName,$pswrd) {
	
	
	$conn = getDB ();
	
	if ($stmt = $conn->prepare ( "SELECT * FROM authentication WHERE UserName = ? ;" )) {
		$stmt->bind_param ('s', $userName);
		$stmt->execute ();
		$result = $stmt->get_result ();
		
		if ($row = $result->fetch_assoc ()) {
			if($row['Password'] == $pswrd)
			{
				$row_array ['Authentication'] = 'valid user';
			}
			else{
				$row_array ['Authentication'] = 'InValid password';
			}
			
			
		}
		else
		{
			$row_array ['Authentication'] = 'Invalid user';
		}
		echo json_encode ( $row_array );
		$stmt->close ();
	}
	
	$conn->close ();
}
