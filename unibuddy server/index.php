<?php
use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

require '../vendor/autoload.php';

require '../routes/unibuddy.php';

$app = new \Slim\App ();

$app->get ( '/', function () {
	echo "<!DOCTYPE html>";
	echo "<html>";
	echo "<head>";
	echo "<style>";
	echo "body {background-color:red;}";
	echo "table,th,td{border-style:inset;}";
	echo"</style>";
	echo "<script>";
	echo "function poost(idd) {";
	echo "path = \"http://www.unibuddy.com/delete/id/\"+ idd;";
	echo "var form = document.createElement(\"form\");";
	echo "form.setAttribute(\"action\", path);";
	echo "form.setAttribute(\"method\", \"POST\");";
	echo "document.body.appendChild(form);";
	echo "form.submit();}";
	echo "</script>";
	echo "<meta charset=\"UTF-8\">";
	echo "<title>Android Movie Database</title>";
	echo "</head>";
	echo "<body>";
	echo "<h2>Add Movie To Database</h2>";
	echo "<form action=\"http://www.unibuddy.com/add\" method=\"POST\">";
	echo "<table>";
	echo "<tr>";
	echo "<th>id:</th>";
	echo "<th><input type=\"text\" name=\"id\" /></th>";
	echo "</tr>";
	echo "<tr>";
	echo "<th>name:</th>";
	echo "<th><input type=\"text\" name=\"name\" /></th>";
	echo "</tr>";
	echo "<tr>";
	echo "<th>description:</th>";
	echo "<th><input type=\"text\" name=\"description\" /></th>";
	echo "</tr>";
	echo "<tr>";
	echo "<th>director:</th>";
	echo "<th><input type=\"text\" name=\"director\" /></th>";
	echo "</tr>";
	echo "<tr>";
	echo "<th>length:</th>";
	echo "<th><input type=\"text\" name=\"length\" /></th>";
	echo "</tr>";
	echo "<tr>";
	echo "<th>stars:</th>";
	echo "<th><input type=\"text\" name=\"stars\" /></th>";
	echo "</tr>";
	echo "<tr>";
	echo "<th>url:</th>";
	echo "<th><input type=\"text\" name=\"url\" /></th>";
	echo "</tr>";
	echo "<tr>";
	echo "<th>image:</th>";
	echo "<th><input type=\"text\" name=\"image\" /></th>";
	echo "</tr>";
	echo "<tr>";
	echo "<th>year:</th>";
	echo "<th><input type=\"text\" name=\"year\" /></th>";
	echo "</tr>";
	echo "<tr>";
	echo "<th>rating:</th>";
	echo "<th><input type=\"text\" name=\"rating\" /></th>";
	echo "</tr>";
	echo "<tr>";
	echo "<th></th>";
	echo "<th><input type=\"submit\" value=\"Submit\" /></th>";
	echo "</tr>";
	echo "</table>";
	echo "</form>";
	echo "<h2>Delete Movie From Database</h2>";
	echo "<table>";
	echo "<tr>";
	echo "<th>id:</th>";
	echo "<th><input type=\"text\" id=\"id\" /></th>";
	echo "<th><button type=\"button\" onclick=\"poost(document.getElementById('id').value)\">Delete</button></th>";
	echo "</tr>";
	echo "</table>";
	echo "</body>";
	echo "</html>";
} );

$app->get ( '/movies/', function () {
	
	//getMovies ();
} );

$app->get ( '/movies/rating/{rating}', function ($request, $response, $args) {
	
	//getMoviesAboveRating ( $args ['rating'] );
} );

$app->get ( '/movies/id/{id}', function ($request, $response, $args) {
	//getMovie ( $args ['id'] );
} );

$app->post ( '/add', function ($request, $response, $args) {
	
	$data = $request->getParsedBody ();
	//addMovie ( $data ['id'], $data ['name'], $data ['description'], $data ['stars'], $data ['length'], $data ['image'], $data ['year'], $data ['rating'], $data ['director'], $data ['url'] );
} );

$app->post ( '/delete/id/{id}', function ($request, $response, $args) {
	
	//deleteMovie ( $args ['id'] );
} );

$app->get ( '/authenticate/login/{userName}/{pswrd}', function ($request, $response, $args) {
	echo $args['userName'];
	login($args['userName'],$args['pswrd']);
	
	
} );

$app->post ( '/authenticate/signup', function ($request, $response, $args) {
	$data = $request->getParsedBody ();
	echo $data ['userName'];
	signUP($data ['userName'],$data ['password'],'false');
} );


$app->run ();
?>
