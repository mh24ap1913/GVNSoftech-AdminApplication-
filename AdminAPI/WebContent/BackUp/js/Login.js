//var x = document.getElementById("demo");
//getLocation();
var app = angular.module('loginApp', []);

app.controller('loginController', function($scope,$http) {
	//getLocation();
	

	$scope.generalFunctions=function(){
  //  var unmae=	$scope.email;
   // var pwd=	$scope.pwd;
   // var buyer_status=	$scope.buyer_status;
  //  var destinationloc=document.getElementById('dest').value;
    var dataToBeSent;
   
    	var type=$scope.type;
    	
    	dataToBeSent={"action":type};

    
   
     $http({
         method:'POST',
         url : "http://brings.co.in/AdminAPI/rest/UserManagementResource/getGeneralInfo",
         dataType: "json",
         contentType:"json",
         data:dataToBeSent,
       })
       .success(function (response) {
    	  
		$scope.output=response.response;
     	  }).


       	
  
       error(function(data, status, headers, config) {
			 
			  });
	
     

    };
    
    $scope.personInfo=function(){
    	 var dataToBeSent;
    	   var email=$scope.email;
     	
     	dataToBeSent={"action":"USER_DETAIL","email":email};

     
    
      $http({
          method:'POST',
          url : "http://brings.co.in/AdminAPI/rest/UserManagementResource/getPersonInfo",
          dataType: "json",
          contentType:"json",
          data:dataToBeSent,
        })
        .success(function (response) {
        		console.log();
        		$scope.personData=response;
      	  }).


        	
   
        error(function(data, status, headers, config) {
 			 
 			  });
 	
	};
	$scope.pendingUsers=function(){
		 var dataToBeSent;
  	   
	     	
	     	dataToBeSent={"action":"PENDING_USERS"};

	     
	    
	      $http({
	          method:'POST',
	          url : "http://brings.co.in/AdminAPI/rest/UserManagementResource/getPendingServiceMan",
	          dataType: "json",
	          contentType:"json",
	          data:dataToBeSent,
	        })
	        .success(function (response) {
	        		$scope.pendingData=response;
	      	  }).


	        	
	   
	        error(function(data, status, headers, config) {
	 			 
	 			  });
	};
	$scope.mailFromLogin=function(){
		 var dataToBeSent;
 	   
	     	var loginId=$scope.loginId;
	     	dataToBeSent={"loginID":loginId};

	     
	    
	      $http({
	          method:'POST',
	          url : "http://brings.co.in/AdminAPI/rest/UserManagementResource/getPersonInfoBasedOnLogin",
	          dataType: "json",
	          contentType:"json",
	          data:dataToBeSent,
	        })
	        .success(function (response) {
	        	$scope.mailoutput=response.response.email_id;
	      	  }).


	        	
	   
	        error(function(data, status, headers, config) {
	 			 
	 			  });
	};
	 $scope.checkAll = function () {
	        if (!$scope.selectedAll) {
	            $scope.selectedAll = true;
	        } else {
	            $scope.selectedAll = false;
	        }
	        angular.forEach($scope.pendingData, function(pendingData) {
	        	pendingData.selected = $scope.selectedAll;
	        });
	    };   
	    
	    $scope.accept = function(){
	    	  var newDataList=[];
	            $scope.selectedAll = false;
	            angular.forEach($scope.pendingData, function(selected){
	                if(selected.selected){
	                    newDataList.push(selected.email_id);
	                }
	            });
	        var	dataToBeSent={"op":"accept","email":newDataList[0]};
	  	      $http({
	  	          method:'POST',
	  	          url : "http://brings.co.in/AdminAPI/rest/UserManagementResource/acceptServiceman",
	  	          dataType: "json",
	  	          contentType:"json",
	  	          data:dataToBeSent,
	  	        })
	  	        .success(function (response) {
	  	        	//	$scope.pendingData=response;
	  	        	alert(response.response);
	  	      	  }).


	  	        	
	  	   
	  	        error(function(data, status, headers, config) {
	  	 			 
	  	 			  });
	            
        };
    
        $scope.reject = function(){
            var newDataList=[];
            $scope.selectedAll = false;
            angular.forEach($scope.pendingData, function(selected){
                if(selected.selected){
                    newDataList.push(selected);
                }
            }); 
            
           // $scope.pendingData = newDataList;
        };
});
