var iCnt;
var w;
var webSocket;
var ad_status;         
var mainApp = angular.module("mainApp", ['ngRoute']);
         mainApp.config(['$routeProvider', function($routeProvider) {
            $routeProvider.
            
            when('/users', {
               templateUrl: 'Users.html',
               controller: 'userController'
            }).
            when('/personInfo', {
                templateUrl: 'PersonInfo.html',
                controller: 'userController'
             }).
            
            when('/pendingUsers', {
                templateUrl: 'PendingUsers.html',
                controller: 'userController'
             }).
             when('/getMail', {
                 templateUrl: 'GetMail.html',
                 controller: 'userController'
              }).
              when('/byOrderNumber', {
                  templateUrl: 'ByOrderNumber.html',
                  controller: 'orderController'
               }).
               when('/byMail', {
                   templateUrl: 'ByMail.html',
                   controller: 'orderController'
                }).
                when('/byStatus', {
                    templateUrl: 'ByStatus.html',
                    controller: 'orderController'
                 }).
             
                 when('/logComplaint', {
                     templateUrl: 'LogComplaint.html',
                     controller: 'complaintController'
                  }).
                  when('/getComplaint', {
                      templateUrl: 'GetComplaints.html',
                      controller: 'complaintController'
                   }).
                   when('/updateComplaint', {
                       templateUrl: 'UpdateComplaint.html',
                       controller: 'complaintController'
                    }).

            
            otherwise({
               redirectTo: '/#'
            });
         }]);
         
         mainApp.controller('userController', function($scope,$rootScope,$http) {
           
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
             
         mainApp.controller('orderController', function($scope,$rootScope,$http,$compile) {
             

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
        		    	   var email=$scope.emailId;
        		     	
        		     	dataToBeSent={"email":email};

        		     
        		    
        		      $http({
        		          method:'POST',
        		          url : "http://brings.co.in/AdminAPI/rest/OrderManagementResource/getOrderDetailsByEmail",
        		          dataType: "json",
        		          contentType:"json",
        		          data:dataToBeSent,
        		        })
        		        .success(function (response) {
        		        	 $scope.orderDataByEmail=response;
        		      	  }).


        		        	
        		   
        		        error(function(data, status, headers, config) {
        		 			 
        		 			  });
        		 	
        			};

        			$scope.orderBasedOnType=function(){
        		//	alert();
        			$scope.showStatus=false;
        			var dataToBeSent;
        			   
        			var type=$scope.orderType;
        			var filterType=$scope.filterType;
        			if(type=="current")
    				{
        				if(filterType==undefined)
        					{
        					filterType="NA";
        					}
        				else
        					{
        				filterType=$scope.filterType;
        					}
    				}
        			else
        				{
        				filterType="NA";
        				}
        			if(type=="current" || type=="pending")
        			$scope.showStatus=true;
        			dataToBeSent={"type":type,"filterType":filterType};
        			
        			
        		//	alert(dataToBeSent);


        		 $http({
        		     method:'POST',
        		     url : "http://brings.co.in/AdminAPI/rest/OrderManagementResource/getDistributedOrderDetails",
        		     dataType: "json",
        		     contentType:"json",
        		     data:dataToBeSent,
        		   })
        		   .success(function (response) {
        			  console.log(response);
        			$scope.orderDataByType=response;
        		 	  }).


        		   	

        		   error(function(data, status, headers, config) {
        				 
        				  });

        			};
        			
        			  $scope.cancel = function(){
        		    	  var newDataList=[];
        		            for(var i=0;i<$scope.orderDataByType.length;i++)
        		            	{
        		            	if($scope.orderDataByType[i].selected)
        		            		{
        		            		newDataList[0]=$scope.orderDataByType[i].id;
        		            		}
        		            	
        		            	}
        		            
        		        var	dataToBeSent={"orderId":newDataList[0]};
        		  	      $http({
        		  	          method:'POST',
        		  	          url : "http://brings.co.in/AdminAPI/rest/OrderManagementResource/cancelOrderByAdmin",
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

        	  
        	                    	});
        	              


         mainApp.controller('complaintController', function($scope,$rootScope,$http,$location) {
            // w.terminate();
        	 iCnt=0;
        	 $("#updateButton").show();
                        $scope.logComplaint=function(){
                     	    
                     	   
                     	    var email=$scope.email;
                     	    var type=$scope.type;
                     	    var reason=$scope.reason;
                     	   // var pickAddress=$scope.pickAddress;
                     	 // 
                     	    if(type=="Payment")
                     	    	{
                     	    	type="P";
                     	    	}
                     	    else if(type=="Order")
                     	    	{
                     	    	type="O";
                     	    	}
                     	    else if(type=="Delivery")
                     	    		{
                     	    		type="D";
                     	    			}
                     	  
                     	     $http({
                     	         method:'POST',
                     	         url : "http://brings.co.in/AdminAPI/rest/ComplaintManagementResource/logComplaint",
                     	         dataType: "json",
                     	         contentType:"json",
                     	         data:{"email":email,"type":type,"complaint":"Payment Failed"},
                     	       })
                     	       .success(function (response) {
                     	    	 if(response.response=="1")
                     	    		 {
                     	    		 alert("Complaint logged");
                     	    		 }
                     	    	
                     	    		
                     	     	  }).


                     	       	
                     	  
                     	       error(function(data) {
                     				  //alert("not logged");
                     				  });
                     		 

                     	    };
                     	    
                     	    $scope.getComplaints=function()
                     	    {
                     	    	  var type=$scope.status;
                     	    	if(type=="Logged")
                     	    	{
                     	    	type="L";
                     	    	}
                     	    else if(type=="InProgress")
                     	    	{
                     	    	type="I";
                     	    	}
                     	    else if(type=="Closed")
                     	    		{
                     	    		type="C";
                     	    			}
                     	    	if ($scope.email==null || $scope.email=="")
                     	    		{
                     	    		dataToBeSent={"email":"NA","status":type};
                     	    		}
                     	    	else
                     	    		{
                     	    		dataToBeSent={"email":$scope.email,"status":type};
                     	    		}
                     	    	

                     	       
                     	       
                     	       $http({
                     	           method:'POST',
                     	           url : "http://brings.co.in/AdminAPI/rest/ComplaintManagementResource/getComplaints",
                     	           dataType: "json",
                     	           contentType:"json",
                     	           data:dataToBeSent,
                     	         })
                     	         .success(function (response) {
                     	         		console.log();
                     	         		$scope.complaintData=response.response;
                     	       	  }).


                     	         	
                     	    
                     	         error(function(data, status, headers, config) {
                     	  			 
                     	  			  });


                     	    };
                     	    $scope.detail=function()
                     	    {
                     	    	$location.url('/updateComplaint');
                     	    	 var newDataList=[];
                 	            $scope.selectedAll = false;
                 	            angular.forEach($scope.complaintData, function(selected){
                 	                if(selected.selected){
                 	                    newDataList.push(selected.complaint_id);
                 	                }
                 	            });
                 	        var	dataToBeSent={"complainId":newDataList[0]};
                 	  	      $http({
                 	  	          method:'POST',
                 	  	          url : "http://brings.co.in/AdminAPI/rest/ComplaintManagementResource/getComplaint",
                 	  	          dataType: "json",
                 	  	          contentType:"json",
                 	  	          data:dataToBeSent,
                 	  	        })
                 	  	        .success(function (response) {
                 	  	        	//	$scope.pendingData=response;
                 	  	        	$("#uid").val(response.response[0].complaint_id);
                 	  	        	$("#uemail").val(response.response[0].user_id);
                 	  	        	$("#utype").val(response.response[0].complain_type);
                 	  	        	$("#ustatus").val(response.response[0].status);
                 	  	        	$("#ureason").val(response.response[0].complain);
                 	  	        	$("#ulastReason").val(response.response[0].last_update);
                 	  	        	$("#ulud").val(response.response[0].last_update_date);
                 	  	        	$("#uld").val(response.response[0].logged_date);
                 	  	        	if(response.response[0].status=="Closed"){
                 	  	        		$("#updateButton").hide();
                 	  	        	}
                 	  	      	  }).


                 	  	        	
                 	  	   
                 	  	        error(function(data, status, headers, config) {
                 	  	 			 
                 	  	 			  });

   };
   
   $scope.update=function(){
	    
 	   var id=$("#uid").val();
	    var email=$("#uemail").val();
	    var type=$("#utype").val();
	   // var reason=$scope.ureason;
	    var status=$("#ustatus").val();
	    var newReason=$("#unewReason").val();
	   // var pickAddress=$scope.pickAddress;
	 // 
	    if(type=="Payment")
	    	{
	    	type="P";
	    	}
	    else if(type=="Order")
	    	{
	    	type="O";
	    	}
	    else if(type=="Delivery")
	    		{
	    		type="D";
	    			}
	    if(status=="Logged")
    	{
	    	status="L";
    	}
    else if(status=="InProgress")
    	{
    	status="I";
    	}
    else if(status=="Closed")
    		{
    	status="C";
    			}
	  
	     $http({
	         method:'POST',
	         url : "http://brings.co.in/AdminAPI/rest/ComplaintManagementResource/updateComplaint",
	         dataType: "json",
	         contentType:"json",
	         data:{"email":email,"type":type,"complaint":newReason,"complaintId":id,"status":status},
	       })
	       .success(function (response) {
	    	 if(response.response=="1")
	    		 {
	    		 alert("Complaint Updated");
	    		 }
	    	
	    		
	     	  }).


	       	
	  
	       error(function(data) {
				  //alert("not logged");
				  });
		 

	    };
                     	    
                     	});
                        

         
         mainApp.controller('maincontroller', function($scope,$rootScope,$http,$compile) {
        	 $rootScope=false;
        	if( localStorage.getItem("UserName")=="null" && localStorage.getItem("buyerid")=="null")
        		{
        		 window.open("../login.html","_self");
        		}
     		
     		
        	 $scope.test = function()
        	 {
        		 check($scope,$rootScope,$http,$compile);
        	 }
        	 $scope.logout=function()
        	 {
        		localStorage.setItem("UserName",null);
        		localStorage.setItem("buyerid",null);
        		 window.open("../login.html","_self");
        	 }
        	 
        	 
         });
		

         mainApp.controller('KnowMyOrderController', function($scope,$rootScope,$http,$compile) {
  
  /*      	 if(typeof(Worker) !== "undefined") {
     	        if(typeof(w) == "undefined") {
     	            w = new Worker("checkstatus.js");
     	        }
     	        w.onmessage = function(event) {
     	            document.getElementById("result").innerHTML = event.data;
     	        };
     	    } else {
     	        document.getElementById("result").innerHTML = "Sorry, your browser does not support Web Workers...";
     	    }
  */
        	 //webSocket.send(localStorage.getItem("buyerid"));
 			
                check($scope,$rootScope,$http,$compile);  
                    	});
                       

         mainApp.controller('ChangePasswordController', function($scope,$rootScope,$http,$compile) {
          	 //webSocket.send(localStorage.getItem("buyerid"));
        	 $scope.changePwd=function()
        	 {
        		 var newpass=$scope.newpwd;
            	 var confpass=$scope.confpwd;
            	
        	 if(newpass==confpass)
        		 {
             $http({
                 method:'POST',
                 url : "http://localhost:8080/Cluster/rest/register/changepass",
                 dataType: "json",
                 contentType:"json",
                 data:{"email":localStorage.getItem("UserName"),"pwd":newpass}
               })
               .success(function (response) {
            	   if(response=="YES")
            		   {	
            		   //window.open("login.html","_self");
            		   }
            	   

             	  }).
          
               error(function(data, status, headers, config) {
        			  alert("not logged");
        			  });
        	 
        		 }
        	 
        	 else
        		 {
        		 alert("Please enter same passwords");
        		 }
        	 }
                    	});
                       


         mainApp.controller('EditUserController', function($scope,$rootScope,$http,$compile) {
        	 //$rootScope=false;
    		 
             $http({
                 method:'POST',
                 url : "http://localhost:8080/Cluster/rest/register/getUserProfile",
                 dataType: "json",
                 contentType:"json",
                 data:{"EMAIL":localStorage.getItem("UserName")}
               })
               .success(function (response) {
            	   $scope.email=localStorage.getItem("UserName");
            	   $scope.username=response["USERNAME"];
            	   $scope.contact=response["CONTACT"];
            	   $scope.address=response["ADDRESS"];

             	  }).
          
               error(function(data, status, headers, config) {
        			  alert("not logged");
        			  });

        	 $scope.SubmitChange=function()
        	 {
        		 
             $http({
                 method:'POST',
                 url : "http://localhost:8080/Cluster/rest/register/updateProfile",
                 dataType: "json",
                 contentType:"json",
                 data:{"username":$scope.username,"phno":$scope.contact,"address":$scope.address,"email":$scope.email}
               })
               .success(function (response) {
            	   if(response=="YES")
            		   {	
            		   //window.open("login.html","_self");
            		   }
            	   

             	  }).
          
               error(function(data, status, headers, config) {
        			  alert("not logged");
        			  });
        	 
        		 }
        	 
        	  
        	 
         });
			
         mainApp.directive('myElement', function ($compile) {

        		var directive = {};
        		   directive.restrict = 'EA';
        		   directive.scope = {
        				   item: '=myElement'
        				   };
        		   	
        		   directive.template = 
        			   "Order Description: <b>{{item.DESCRIPTION}}</b><br>" +
        		   		"CONTACT NUMBER OF PACKED GOODS: <b>{{item.CONTACT_NUM_PCKD_GOODS}}</b><br>" +
        		   		"NAME OF THE PERSON OF PACKED GOODS: <b>{{item.NAME_OF_PERSON_PCKD_GOODS}}</b><br>" +
        		   		"FROM ADDRESS: <b>{{item.FROM_ADDRESS}}</b><br>" +
        		   		"TO ADDRESS: <b>{{item.TO_ADDRESS}}</b><br>" +
        		   		"SERVICE MAN NAME: <b>{{item.SERVICE_MAN_NAME}}</b><br>" +
        		   		"SERVICE MAN CONTACT: <b>{{item.SERVICE_MAN_CONTACT}}</b><br>"+
        		   		"STATUS: <b>{{item.message}}</b><br>"+
        		   		"TOTAL COST: <b>{{item.TOTALBILL}}</b><br>"+
        		   		"<li ng-repeat='x in item.index' ng-show='item.ORDERTYPE' >{{x.name}}"
        		   		+"</li>"+
        		   		"<button ng-model='cancel' ng-click='cancel(item.WORK_ORDER_NUMBER)' ng-show='item.SHOWPROCEED'>Cancel</button>"+
        		   		"<button ng-model='received' ng-click='received(item.WORK_ORDER_NUMBER)' ng-show='item.showreceive'>Received</button>"+
        		   		"<button ng-model='pay' ng-click='makepayment(item.WORK_ORDER_NUMBER)' ng-show='item.ShowPayment'>Payment</button>";	
        		   directive.link= function(scope, elem, attrs) {
       		          scope.makepayment  = function (a) {
       		        	  scope.order.showreceive=true;
       		        	  scope.order.ShowPayment=false;
                  	     /*$http({
                 	         method:'POST',
                 	         url : "http://localhost:8080/Cluster/rest/OrderbyBuyer/confirmationbyserviceman",
                 	         dataType: "text",
                 	         contentType:"json",
                 	         data:{"COMMENTS":scope.comment,"WORK_ORDER_NUMBER":a},
                 	       })
                 	       .success(function (response) {
                 	    	   //checkByService($scope, $rootScope, $http, $compile);
                 	    	 scope .order.SHOWPROCEED=false;
                 	     	  }).


                 	       	
                 	  
                 	       error(function(data, status, headers, config) {
                 				  alert("not logged");
                 				  });
*/                 		 
       		        	  
       		          };
       		        scope.cancel  = function (a) {
       		        	$http({
                	         method:'POST',
                	         url : "http://localhost:8080/Cluster/rest/OrderbyBuyer/cancellationbyserviceman",
                	         dataType: "text",
                	         contentType:"json",
                	         data:{"WORK_ORDER_NUMBER":a},
                	       })
                	       .success(function (response) {
                	    	   
                	     	  }).


                	       	
                	  
                	       error(function(data, status, headers, config) {
                				  alert("not logged");
                				  });
                		 

       		          };
       		        };

        	return directive;
        	});

         
         function check($scope,$rootScope,$http,$compile)
         {
        	 var ret;
        	 $rootScope.refresh = true;
        	  var el;
        	  angular.element(document.getElementById('kyo')).empty();
				$http({
  	         method:'PUT',
  	         url : "http://localhost:8080/Cluster/rest/OrderbyBuyer/"+localStorage.getItem("buyerid"),
  	         dataType: "json",
  	         
  	         
               })
  	       .success(function (response) {
  	    	
  	    	   
  	    	  builtElement($scope,$compile,response);
  	    	var eventSource = new EventSource("http://localhost:8080/Cluster/AutoUpdation?wodn="+localStorage.getItem("buyerid"));
  	    	
  	    		
  	    	eventSource.onmessage = function(event) {
  	    	
  	    		if(event.data=='YES')
					{
  	    			ret=event.data;
				check1($scope,$rootScope,$http,$compile);
					}
				else if(event.data=='NOTHING')
					{
					ret=event.data;
					//event.close();
					eventSource.close();
					alert("Nothing to See");
					angular.element(document.getElementById('kyo')).empty();
					}

  	    	}
/*  			eventSource.addEventListener('up_vote', function(event) {
  				
  				//	document.getElementById('up').innerHTML = event.data;
  				
  			  				}, false);

  	    	   	 		
*/  	    	   
  	     	  		}).

  	       error(function(data, status, headers, config) {
  				  alert("not logged");
  				  });
  		

         }

         function check1($scope,$rootScope,$http,$compile)
         {
        	 $rootScope.refresh = true;
        	  var el;
        	  angular.element(document.getElementById('kyo')).empty();
				$http({
  	         method:'PUT',
  	         url : "http://localhost:8080/Cluster/rest/OrderbyBuyer/"+localStorage.getItem("buyerid"),
  	         dataType: "json",
  	         
  	         
               })
  	       .success(function (response) {
  	    	
  	    	   
  	    	  builtElement($scope,$compile,response);
  	    	  	    	   	 		
  	    	   
  	     	  		}).

  	       error(function(data, status, headers, config) {
  				  alert("not logged");
  				  });
  		

         }

         
         function builtElement($scope,$compile,response)
         {
        	 var index="ITEMID";
        	 var el;
        	 //$scope.x=[];
        	 var demo={};
        	 $scope.ordersdemo=[];
        	 demo["panel"]=[];
        	 for(var i=0;i<=response.length-1;i++)
    		   {
        		 
    		   $scope.x={};
    		   $scope.x.status=response[i]["status"];
    		   $scope.x.CONTACT_NUM_PCKD_GOODS=response[i]["CONTACT_NUM_PCKD_GOODS"];
    		   $scope.x.NAME_OF_PERSON_PCKD_GOODS=response[i]["NAME_OF_PERSON_PCKD_GOODS"];
    		   $scope.x.SERVICE_PERSON_ID=response[i]["SERVICE_PERSON_ID"];
    		   $scope.x.FROM_ADDRESS=response[i]["FROM_ADDRESS"];
    		   $scope.x.TO_ADDRESS=response[i]["TO_ADDRESS"];
    		   $scope.x.WORK_ORDER_NUMBER=response[i]["WORK_ORDER_NUMBER"];
    		   $scope.x.SERVICE_MAN_NAME=response[i]["SERVICE_MAN_NAME"];
    		   $scope.x.SERVICE_MAN_CONTACT=response[i]["SERVICE_MAN_CONTACT"];
    		   $scope.x.DESCRIPTION=response[i]["description"];
    		   $scope.x.TOTALBILL=response[i]["totalbill"];
    		   if(response[i].ORDER_TYPE=="BuyGoods")
    			   {
    			   var items=[];
    			   $scope.x.ORDERTYPE=true;
    			   $scope.x.COUNT=response[i]["COUNT"];
    			   //var itemjson={};
    			   for(ko in response[i])
    				   {
    				   var itemjson={};   
    				   if(ko.match("ITEM"))
    					   {
    					   itemjson.name=response[i][ko];
        				   itemjson.id=ko;
        				   items.push(itemjson);   
    					   }
    				   
    				   }/*
    			   for(k=0;k<response[i]["COUNT"];k++)
    				   {
    				   var itemjson={};
    				   index=index+(k+1);
    				   itemjson.name=response[i][index];
    				   itemjson.id=index;
    				   //$scope.x.index=response[i][index];
    				   items.push(itemjson);
    				   index="ITEMID";
    				   }*/
    			   $scope.x.index=items;
    			   }
    		   if(response[i].SERVICE_PERSON_CONFIRMATION=="YES")
    			   {
    			 $scope.x.SHOWPROCEED=true;
    			$scope.x.ShowPayment=true;
    			$scope.x.message='ORDER CONFIRMED PLEASE MAKE PAYMENT';
    			   }
    		   else
    			   {
    			 $scope.x.SHOWPROCEED=true;
    			$scope.x.ShowPayment=false;
    			$scope.x.message='PROCESSING';
    			   }
    		   $scope.ordersdemo[i]=$scope.x;
    		   demo["panel"][i]=$scope.x;
    		   //$scope.y=$scope.demo.panel[i];
    		   /*	el= $compile( '<userorder name="x"></userorder>' )( $scope );
    		    angular.element(document.getElementById('kyo')).append( el ).append("<br>");
    		   */ //$scope.$apply();
    		   }
    		
         }
/*         var webSocket = 
 			new WebSocket('ws://localhost:8080/Cluster/websocket');

 		webSocket.onerror = function(event) {
 			onError(event)
 		};

 		webSocket.onopen = function(event) {
 			onOpen(event)
 		};

 		webSocket.onmessage = function(event) {
 			onMessage(event)
 		};

 		function onMessage(event) {
 			document.getElementById('messages').innerHTML 
 				+= '<br />' + event.data;
 		}

 		function onOpen(event) {
 			document.getElementById('messages').innerHTML 
 				= 'Connection established';
 		}

 		function onError(event) {
 			alert(event.data);
 		}
*/
                     