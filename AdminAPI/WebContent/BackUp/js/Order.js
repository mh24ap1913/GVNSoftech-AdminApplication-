//var x = document.getElementById("demo");
//getLocation();
var app = angular.module('orderApp', []);

app.controller('OrderController', function($scope,$http) {
	//getLocation();
	

	$scope.getOrderById=function(){
  //  var unmae=	$scope.email;
   // var pwd=	$scope.pwd;
   // var buyer_status=	$scope.buyer_status;
  //  var destinationloc=document.getElementById('dest').value;
    var dataToBeSent;
   
    	var orderId=$scope.orderId;
    	dataToBeSent={"orderId":orderId};

    
   
     $http({
         method:'POST',
         url : "http://brings.co.in/brings/rest/getOrderDetailsById",
         dataType: "json",
         contentType:"json",
         data:dataToBeSent,
       })
       .success(function (response) {
    	   $scope.orderData=response;
     	  }).


       	
  
       error(function(data, status, headers, config) {
			 
			  });
	
     

    };
    
    $scope.getOrderByMail=function(){
    	 var dataToBeSent;
    	   
     	
     	dataToBeSent={"email":"acd@g.com"};

     
    
      $http({
          method:'POST',
          url : "http://brings.co.in/brings/rest/getOrdersById",
          dataType: "json",
          contentType:"json",
          data:dataToBeSent,
        })
        .success(function (response) {
        		console.log();
        		alert(response);
      	  }).


        	
   
        error(function(data, status, headers, config) {
 			 
 			  });
 	
	};
	
	$scope.orderBasedOnType=function(){
	alert();
	var dataToBeSent;
	   
	var type=$scope.orderType;
	
	dataToBeSent={"type":type};
	alert(dataToBeSent);


 $http({
     method:'POST',
     url : "http://localhost:8059/AdminAPI/rest/OrderManagementResource/getDistributedOrderDetails",
     dataType: "json",
     contentType:"json",
     data:dataToBeSent,
   })
   .success(function (response) {
	  console.log(response);
	$scope.orderDataByType=response.response;
 	  }).


   	

   error(function(data, status, headers, config) {
		 
		  });

	};
});
