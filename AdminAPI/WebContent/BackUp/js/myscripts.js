var iCnt;
var w;
var webSocket;
var ad_status;         
var mainApp = angular.module("mainApp", ['ngRoute']);
         mainApp.config(['$routeProvider', function($routeProvider) {
            $routeProvider.
            
            when('/userinfo', {
               templateUrl: 'User.html',
               controller: 'userController'
            }).
            
            when('/orderinfo', {
                templateUrl: 'Order.html',
                controller: 'orderController'
             }).
             
            

            when('/knowyourorder', {
               templateUrl: '../HTML/KnowMyOrder.html',
               controller: 'KnowMyOrderController'
            }).

            when('/changepwd', {
               templateUrl: '../HTML/ChangePassword.html',
               controller: 'ChangePasswordController'
            })
            .when('/editprofile', {
                templateUrl: '../HTML/EditUser.html',
                controller: 'EditUserController'
            }).
            
            when('/getallmyorders', {
                templateUrl: '../HTML/GAM.html',
                controller: 'GamController'
            }).
/*
            when('/', {
            	 templateUrl: 'User.html',
                 controller: 'userController'
            })*/
           
            
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
        	              


         mainApp.controller('buyGoodsController', function($scope,$rootScope,$http) {
            // w.terminate();
        	 iCnt=0;
        	 var toLat;
             var toLong;
             $("#itemlist").empty();
             $scope.choices = [{id: 'ch1'}];

             $rootScope.refresh = false;
                             var to_address = /** @type {!HTMLInputElement} */(
                         document.getElementById('btoAdd'));
                             geocoder = new google.maps.Geocoder();

                        var  autocomplete1 = new google.maps.places.Autocomplete(to_address);

                        autocomplete1.addListener('place_changed', function() {
                        	var address=document.getElementById('btoAdd').value;
                        	ad_status="bto";
                        	var k=geocodeAddress(geocoder,address);
                        //alert($scope.fromAddress);
                        	
                        });
                        
                        $scope.BuyGoods=function(){
                     	    
                     	    var toaddress=document.getElementById('btoAdd').value;
                     	    var desc=$scope.description;
                     	    var personName=$scope.personName;
                     	    var contact=$scope.bphoneno;
                     	   // var pickAddress=$scope.pickAddress;
                     	    var deladdress=$scope.bdeliveryAddress;
                     	    var list={};
                     	    
                     	    for(i=0;i<$scope.choices.length;i++)
                     	    	{
                     	    	list[$scope.choices[i].id]=$scope.choices[i].name;
                     	    	}
                     	   var listdata=JSON.stringify(list);
                     	     $http({
                     	         method:'POST',
                     	         url : "http://brings.co.in/cluster/rest/OrderbyBuyer/buyGoods",
                     	         dataType: "json",
                     	         contentType:"json",
                     	         data:{"buyerid":localStorage.getItem("buyerid"),"type":"BuyGoods","toaddress":deladdress,"tolat":tolat,"tolng":tolng,"personname":personName,"contactno":contact,"description":desc,"listofitems":listdata},
                     	       })
                     	       .success(function (response) {
                     	    	 if(response=="NSM")
                     	    		 {
                     	    		 alert("Right Now No serviceman");
                     	    		 }
                     	    	 else if(response=="SurgeAcceptance")
                     	    		 {
                     	    		    var r = confirm("Surge is going on");
                     	    		 if(r==true)
                     	    			 {
                     	    			 $http({
                     	                     method:'POST',
                     	                     url : "http://localhost:8080/Cluster/rest/OrderbyBuyer/buysurge",
                     	                     dataType: "json",
                     	                     contentType:"json",
                     	                     data:{"email":localStorage.getItem("UserName"),"surge":"YES"}
                     	                   })
                     	                   .success(function (response) {
                     	                	  if(response=="NoWay" || response=="NSM")
               	                		   {	
               	                		   alert("No ServiceMan Present")
               	                		   //window.open("login.html","_self");
               	                		   }
               	                	   


                     	                 	  }).
                     	              
                     	                   error(function(data, status, headers, config) {
                     	            			  alert("not logged");
                     	            			  });

                     	    			 }
                     	    		 else
                     	    			 {
                     	    			 
                     	    			 }
                     	    		 }

                     	     	  }).


                     	       	
                     	  
                     	       error(function(data) {
                     				  //alert("not logged");
                     				  });
                     		 

                     	    };
                     	    
                     	    $scope.AddItem=function()
                     	    {
                     	         
                     	       var newItemNo = $scope.choices.length+1;
                     	      $scope.choices.push({'id':'ch'+newItemNo});


                     	    };
                     	    $scope.DeleteItem=function()
                     	    {
                     	       var lastItem = $scope.choices.length-1;
                     	      $scope.choices.splice(lastItem);
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
                     