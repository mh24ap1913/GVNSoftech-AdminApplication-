var app = angular.module('loginApp', []);
app.controller('loginController', function($scope,$http) {
    
    $scope.loginf = function() {
        $scope.login = true;
        $scope.register = false;
    };
    
    $scope.registerf = function() {
        $scope.register = true;
        $scope.login = false;
    };
    $scope.registerA= function()
    {
    	 var dataToBeSent;
    	   
     	var email=$scope.emailr;
    	var password=$scope.passwordr;
    	var eid=$scope.eid;
     	
     	dataToBeSent={"empId":eid,"email":email,"password":password}

    	 $http({
             method:'POST',
             url : "http://localhost:8035/AdminAPI/rest/loginUser/signUp",
             dataType: "json",
             contentType:"json",
             data:dataToBeSent,
           })
           .success(function (response) {
        	  if(response.response==-1)
        		  {        	   alert("Please give correct data");
        		  
        		  }
        	  else
        		  {
        		  alert("Successfully signed in");
        		  }
         	  }).


           	
      
           error(function(data, status, headers, config) {
    			 
    			  });
    	
    }
    
    
    $scope.loginA= function()
    {
    	 var dataToBeSent;
    	   
     	var email=$scope.email;
    	var password=$scope.password;
    	//var eid=$scope.eid;
     	
     	dataToBeSent={"mail":email,"password":password}

    	 $http({
             method:'POST',
             url : "http://localhost:8035/AdminAPI/rest/loginUser/login",
             dataType: "json",
             contentType:"json",
             data:dataToBeSent,
           })
           .success(function (response) {
        	 // window.open(url, windowName, windowFeatures, optionalArg4)
        	   if(response.response==-1)
     		  {        	  alert("wrong credentials");
     		  
     		  }
     	  else
     		  {
     		  window.open("index.html", "_self");
     		  }
        	
    		//$scope.output=response.response;
         	  }).


           	
      
           error(function(data, status, headers, config) {
    			
    			  });
    	
    }
});