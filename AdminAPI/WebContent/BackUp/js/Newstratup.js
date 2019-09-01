
 var values;
 var dataoftickets;
 var ticketid;
 var latfromchange,longfromchange;

    module.config(['$routeProvider',
        function($routeProvider) {
            $routeProvider

            // route for the home page
            .when('/login', {
                templateUrl : 'login.html',
                controller  : 'logincontroller'
            })
            .when('/register', {
                templateUrl : 'register.html',
                controller  : 'registercontroller'
            })
            .when('/raiseclaimuser', {
                templateUrl : 'html/RaiseClaim.html',
                controller  : 'raisecontroller'
            })
             .when('/logout', {
                templateUrl : 'html/carasoul.html',
                controller  : 'logoutcontroller'
            })
            
            // verify claim by contractor
              .when('/verifyclaimcontractor', {
                templateUrl : 'html/5_viewTicket.html',
                controller  : 'verifycontractorcontroller'
            })

            
            .when('/samepageinshowonmap', {
                templateUrl : 'html/RaiseClaim.html',
                controller  : 'showonmap'
            })

            
              .when('/showonmap', {
                templateUrl : 'html/RaiseClaimMap.html',
                controller  : 'showonmapr'
            })
            
              .when('/changelatlong', {
                templateUrl : 'html/RaiseClaimMap.html',
                controller  : 'showonmapr1'
            })
            // route for the about page
           
             .when('/imagesupload', {
                templateUrl : 'html/UploadImages.html',
                controller  : 'imageuploadController'
            })

             .when('/home', {
                templateUrl : 'html/carasoul.html',
                controller  : 'imageuploadController1'
            })
             .when('/googleMap', {
                templateUrl : 'html/carasoul.html',
                controller  : 'imageuploadController2'
            })
  

            // route for the contact page
            .when('/', {
                templateUrl : 'html/carasoul.html',
                controller  : 'imageuploadController1'
            })
           
            //added for tr
            .when('/updateclaimuser', {
                templateUrl : 'html/5_viewTicket.html',
                controller  : 'updateclaimbyuser'
            })
            .when('/showindividual', {
                templateUrl : 'html/7_user_claim_updatepage.html',
                controller  : 'showindividualupdate'
            })
            
             .when('/showindividualbymanager', {
                templateUrl : 'html/6_viewIndTicket.html',
                controller  : 'showindividualupdatebymanager'
            })
              .when('/showindividualbycontractor', {
                templateUrl : 'html/9_con_viewIndTicket.html',
                controller  : 'showindividualupdatebycontractor'
            })
            
            
            .when('/approve-claim-manager', {
                templateUrl : 'html/5_viewTicket.html',
                controller  : 'approvemanager'
            })
            .when('/managerviewall', {
                templateUrl : 'html/ViewMap.html',
                controller  : 'managerviewallcont'
            })
            .when('/viewcharts', {
                templateUrl : 'html/Chartsoftickets.html',
                controller  : 'chartsuser'
            });
            
           
           
    }]);
    
    module.directive('fileModel', ['$parse', function ($parse) {
        return {
            restrict: 'A',
            link: function(scope, element, attrs) {
                var model = $parse(attrs.fileModel);
                var modelSetter = model.assign;
                
                element.bind('change', function(){
                    scope.$apply(function(){
                        modelSetter(scope, element[0].files[0]);
                    });
                });
            }
        };
    }]);

    module.service('fileUpload', ['$http', function ($http) {
        this.uploadFileToUrl = function(file,file1, uploadUrl){
            var fd = new FormData();
         
            fd.append('file', file);
            fd.append('file1', file1);
            
            $http.post(uploadUrl, fd, {
                transformRequest: angular.identity,
                headers: {'Content-Type': undefined},async:false
            })
            .success(function(){
            	
            	
            	alert("uploaded");
            })
            .error(function(){
            });
        }
    }]);
    
    module.controller('showindividualupdate', function($scope,$location,$http) {
        // create a message to display in our view
    	var user= localStorage.getItem('usersessid'); 
		var resource = config.resource;
		
    	 $http({
             method:'GET',
             url : resource+"ticket/"+ "user/"+user+"/"+ticketid,
             dataType: "json",
           })
           .success(function (data) {
        	   
        	 
        	console.log(JSON.stringify(data[0]));
        	   
        	   var a=angular.element($("#7-updteclaim-cause"));
        	   a.val(data[0].cause);
        	  
        	   a=angular.element($("#7-updteclaim-latitude"));
        	a.val(data[0].lat);
        	   a=angular.element($("#7-updteclaim-ticketid"));
        	   a.text(ticketid);
        	   a=angular.element($("#7-updteclaim-longitude"));
        	   a.val(data[0].long);
        	   a=angular.element($("#7-updteclaim-equipment"));
        	   a.val(data[0].equipmentNames);
        	   a=angular.element($("#7-updteclaim-incidentLocation"));
        	   a.val(data[0].incidentLocation);
        	   a=angular.element($("#7-updteclaim-ip_imag1"));
        	   a.attr("src",data[0].image1);
        	   a=angular.element($("#7-updteclaim-ip_imag2"));
        	   a.attr("src",data[0].image2);
        	  
        	   $.ajax({
        		   url: "demo_ajax_script.js", 
        		   dataType: "json",
        		   type:"POST",
        		   
        			   
        	   });
           
		    	
		    	
		    	
           	

         	  }).


           	
      
           error(function(data, status, headers, config) {
   			  alert("not logged");
   			  });
    	 
    	 $scope.clearallupdatepage=function()
    	 {
    		 
    	 };
     
    });
    
    module.controller('logoutcontroller', function($scope,$location,$http) {
        // create a message to display in our view
     localStorage.setItem('usersessid',""); 
     var resourcehome = config.resourcehome;
     window.location=resourcehome+"Home.html";
    });
		
    module.controller('chartsuser', function($scope,$location,$http) {
        // create a message to display in our view
    	var user= localStorage.getItem('usersessid'); 
		var resource = config.resource;
		
    	 $http({
             method:'GET',
             url : resource+"ticket/"+ "chartsfortickets",
             dataType: "json",
           })
           .success(function (data) {
        	   
        	   var data1=[];
        	   var b=Object.keys(data);
        	   for( var i=0;i<b.length;i++)
    		   {
    		var key=b[i];
    		 var val=parseInt(data[b[i]]);
    		   var data2=[key,val];
    		   data1.push(data2);
    		   
    		   }
        	   
        	   
        	   var chart = {
        		       plotBackgroundColor: null,
        		       plotBorderWidth: 0,
        		       plotShadow: false
        		   };
        		   var title = {
        		      text: 'Ticket<br>Status',
        		      align: 'center',
        		      verticalAlign: 'middle',
        		      y: 50	  
        		   };      
        		   var tooltip = {
        		      pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        		   };
        		   var plotOptions = {
        		      pie: {
        		         dataLabels: {
        		            enabled: true,
        		            distance: -50,
        		            style: {
        		               fontWeight: 'bold',
        		               color: 'white',
        		               textShadow: '0px 1px 2px black'
        		            }
        		         },
        		         startAngle: -90,
        		         endAngle: 90,
        		         center: ['50%', '75%']
        		      }
        		   };
        		   var series= [{
        		      type: 'pie',
        		      name: 'Browser share',
        		      innerSize: '50%',
        		      data:data1
        		   }];     
        		      
        		   var json = {};   
        		   json.chart = chart; 
        		   json.title = title;     
        		   json.tooltip = tooltip;  
        		   json.series = series;
        		   json.plotOptions = plotOptions;
        		   $('#containerforcharts').highcharts(json);  
        	  
        	
        	
        	  
         	  }).


           	
      
           error(function(data, status, headers, config) {
   			  alert("not logged");
   			  });
    	 
    	 $scope.clearallupdatepage=function()
    	 {
    		 
    	 };
    	 
    	 $scope.nextinuserupdate=function()
    	 {
    		 var user= localStorage.getItem('usersessid'); 
 			var resource = config.resource;
var ticketid=$("#7-updteclaim-ticketid").text();
			
			console.log($("#7-updteclaim-cause").val());
			var causeTemp=$("#7-updteclaim-cause").val();
			var latTemp=$("#7-updteclaim-latitude").val();
			var longTemp=$("#7-updteclaim-longitude").val();
			var equipmentTemp=$("#7-updteclaim-equipment").val();
			var inclocatTemp=$("#7-updteclaim-incidentLocation").val();
			var img1temp=$("#7-updteclaim-ip_imag1").attr("src");
			var img2temp=$("#7-updteclaim-ip_imag2").attr("src");
			a1={"id":"dummy","Ticket_Id":"dummy","cause":causeTemp,"lat":latTemp,"long":longTemp,"image1":img1temp,"image2":img2temp,"file_Path":"dummy","equipmentNames":equipmentTemp,"incidentLocation":inclocatTemp};
    	 
			

	    	 $http({
	             method:'POST',
	             url :resource+"User/ticket/"+ticketid,
	             contentType : "application/json",
	             dataType: "json",
	           }).
			
			  success(function(data)
					  {
				  alert("logged ....");
			    	alert("Now upload Images");
			    	localStorage.setItem('ticketid', data); 
			   
			    	
			    	$location.path("/imagesupload");
				  
					  }).
					  error(function(data, status, headers, config) {
						  alert("not logged");
						  });
    	 
    	 };
     
    });
    
    
    
    
    module.controller('showindividualupdatebymanager', function($scope,$location,$http) {
        // create a message to display in our view
    	var user= localStorage.getItem('usersessid'); 
		var resource = config.resource;
		
    	 $http({
             method:'GET',
             url : resource+"ticket/"+ "user/"+user+"/"+ticketid,
             dataType: "json",
           })
           .success(function (data) {
        	   
        	 
        	console.log(JSON.stringify(data[0]));
        	   
        	   var a=angular.element($("#6_ip_ticketid"));
        	   a.text(data[0].ticket_Id);
        	  a=angular.element($("#6_ip_ticketcause"));
        	  a.text(data[0].cause);
        	  
       
        	   a=angular.element($("#6_ip_lat"));
        	a.text(data[0].lat);
        	 
        	   a=angular.element($("#6_ip_long"));
        	   a.text(data[0].long);
        	   a=angular.element($("#6_ip_equip_name"));
        	   a.text(data[0].equipmentNames);
        	   a=angular.element($("#6_ip_inc_loc"));
        	   a.text(data[0].incidentLocation);
        	   a=angular.element($("#6_ip_imag1"));
        	   a.attr("src",data[0].image1);
        	   a=angular.element($("#6_ip_imag2"));
        	   a.attr("src",data[0].image2);
        	  
		    	
		    	
		    	
		    	
           	

         	  }).


           	
      
           error(function(data, status, headers, config) {
   			  alert("not logged");
   			  });
    	 
    	 $scope.manapprove=function()
    	 {
    		 
    		 var resource = config.resource;
    			
   		  var user = localStorage.getItem('usersessid');
   		  var ticketid = angular.element($("#6_ip_ticketid")).text();
   		  var ticketstatus="Manager-Approved";
   			  
   		 $http({
             method:'GET',
             url : resource+ "Manager/manager/"+ticketid+"/"+ticketstatus,
    
             dataType: "json",
           })
           .success(function (data) {
        	   
          	 
          alert(data);
   		    	
   		    	
              	

            	  });


              	
         
            
   		 
             
    	 };
    	 
    	 $scope.manreject=function()
    	 {
    		 
    		 var resource = config.resource;
    			
   		  var user = localStorage.getItem('usersessid');
   		  var ticketid = angular.element($("#6_ip_ticketid")).text();
   		  var ticketstatus="Manager-Rejected";
   			  
   		 $http({
             method:'GET',
             url : resource+ "Manager/manager/"+ticketid+"/"+ticketstatus,
    
             dataType: "json",
           })
           .success(function (data) {
        	   
          	 
          alert(data);
   		    	
   		    	
              	

            	  });


              	
         
            
   		 
             
    	 };
    	 
    	
     
    });
    
    
    module.controller('showindividualupdatebycontractor', function($scope,$location,$http) {
        // create a message to display in our view
    	var user= localStorage.getItem('usersessid'); 
		var resource = config.resource;
		
    	 $http({
             method:'GET',
             url : resource+"ticket/"+ "user/"+user+"/"+ticketid,
             dataType: "json",
           })
           .success(function (data) {
        	   
        	 
        	console.log(JSON.stringify(data[0]));
        	   
        	   var a=angular.element($("#9_ip_ticketid"));
        	   a.text(data[0].ticket_Id);
        	  a=angular.element($("#9_ip_ticketcause"));
        	  a.text(data[0].cause);
        	  
       
        	   a=angular.element($("#9_ip_lat"));
        	a.text(data[0].lat);
        	 
        	   a=angular.element($("#9_ip_long"));
        	   a.text(data[0].long);
        	   a=angular.element($("#9_ip_Equipment"));
        	   a.text(data[0].equipmentNames);
        	   a=angular.element($("#9_ip_Incidentlocation"));
        	   a.text(data[0].incidentLocation);
        	   a=angular.element($("#9_ip_imag1"));
        	   a.attr("src",data[0].image1);
        	   a=angular.element($("#9_ip_imag2"));
        	   a.attr("src",data[0].image2);
        	  
		    	
		    	
		    	
		    	
           	

         	  }).


           	
      
           error(function(data, status, headers, config) {
   			  alert("not logged");
   			  });
    	 
    	 $scope.contr_approve=function()
    	 {
    		  var user = localStorage.getItem('usersessid');
       		  var ticketid = angular.element($("#9_ip_ticketid")).text();
    		 var resource = config.resource;
    			
   		 
   		  
   		  var ticketstatus="Contractor-Verified";
   	
   		  
   		  
    		 $http({
              method:'GET',
              url : resource+ "Contractor/"+ticketid+"/"+ticketstatus,
     
              dataType: "json",
            })
            .success(function (data) {
         	   
           	 
           alert(data);
    		    	
    		    	
               	

             	  });



             
    	 };
    	 
    	 $scope.contr_reject=function()
    	 {
    		 
    		 var resource = config.resource;
    			
   		  var user = localStorage.getItem('usersessid');
   		  var ticketid = angular.element($("#9_ip_ticketid")).text();
   		  var ticketstatus="Contractor-Rejected";
   			  
   		 $http({
             method:'GET',
             url : resource+ "Contractor/"+ticketid+"/"+ticketstatus,
    
             dataType: "json",
           })
           .success(function (data) {
        	   
          	 
          alert(data);
   		    	
   		    	
              	

            	  });


              	
         
            
   		 
             
    	 };
    	 $scope.contr_resend=function()
    	 {
    		 
    		 var resource = config.resource;
    			
   		  var user = localStorage.getItem('usersessid');
   		  var ticketid = angular.element($("#9_ip_ticketid")).text();
   		  var ticketstatus="Contractor-Resend";
   			  
   		 $http({
             method:'GET',
             url : resource+ "Contractor/"+ticketid+"/"+ticketstatus,
    
             dataType: "json",
           })
           .success(function (data) {
        	   
          	 
          alert(data);
   		    	
   		    	
              	

            	  });


              	
         
            
   		 
             
    	 };
    	 
    	
     
    });
    
    
    
//added for updateclaimbyuser trilok
    module.controller('updateclaimbyuser', function($scope,$http,$location,$compile) {
    	
    	
    	
    	var user= localStorage.getItem('usersessid'); 
		var resource = config.resource;
		
		  $http({
	            method:'GET',
	            url : resource+"ticket/"+ "user/"+user,
	            dataType: "json",
	          })
	        .success(function (data) {
	        	
	        	var a=angular.element($("#5_tbl_tickdetails"));
	        	a.empty();
	        
	    		  var row = (  "<table class='table' style='text-align:center'>"+"<tr class='success '><td  style=' text-align: left'>" + "Ticket Id" + "</td><td>" + "Ticket Status" +  "</td><td>" + "Raising Date" +"</td></tr>"+"</table>");
	    	 a.append(row);

	    		  for(var i=0;i<data.length;i++)
	      	  {

	    			 
	      	  var row = ("<table class='table' style='text-align: center'>"+"<tr class='info'><td>"+"<a  ng-click='usertickets($event)' data='"+data[i].ticket_Id+"'>"+data[i].ticket_Id+"</a></td><td>"+data[i].ticket_Status+"</td><td>"+data[i].raising_Date+"</td></tr>"+"</table>");
	      	 var temp = $compile(row)($scope);

	       a.append(temp);

	      	  }


	        	
	        }).
		  error(function(data, status, headers, config) {
			  alert("not logged");
			  });
    	
$scope.usertickets=function(obj)
{
	  var id=obj.target.attributes.data.value;
	
		$location.path("/showindividual");
	  	 ticketid=id;
};
    });
    
    
    
    //ended

    // create the controller and inject Angular's $scope
    module.controller('raisecontroller', function($scope,$http,$location) {
    	
    	 var latTemp=17;
   	  var longTemp=78;
    	
    	if(values!=null)
    		{
    		$scope.cause=values.cause;
        	$scope.latitude=values.lat;
        	$scope.longitude=values.long;
        	$scope.EquipmentName=values.equipname;
        	$scope.IncidentLocation=values.incidentloc;
    		}
    	if($scope.latitude!=null)
    		{
    		latTemp=$scope.latitude;
    		}
    	
    	if($scope.longitude!=null)
		{
    		longTemp=$scope.longitude;
		}
    	
    	
        // create a message to display in our view
	 
	  
	
	 var latlng = new google.maps.LatLng(latTemp, longTemp);
	    var mapOptions = {
		    	  center:latlng,
		    	  zoom:15,
		    	  mapTypeId:google.maps.MapTypeId.ROADMAP,
				   /* mapTypeControl:false,
				    navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}*/
	            }
	    var map = new google.maps.Map(document.getElementById("container-mapr"), mapOptions);
	  
    	
	    $scope.submitticket=function()
	    {
	    	var user= localStorage.getItem('usersessid'); 
	    	var resource = config.resource;
	    
	    	causeTemp=$scope.cause;
	    	latTemp=$scope.latitude;
	    	longTemp=$scope.longitude;
	    	equipmentTemp=$scope.EquipmentName;
	    	inclocatTemp=$scope.IncidentLocation;
	    	
	    	values={cause:$scope.cause,lat:$scope.latitude,long:$scope.longitude,equipname:$scope.EquipmentName,incidentloc:$scope.IncidentLocation};
	    	a1={"id":"dummy","Ticket_Id":"dummy","cause":causeTemp,"lat":latTemp,"long":longTemp,"image1":"dummy","image2":"dummy","file_Path":"dummy","equipmentNames":equipmentTemp,"incidentLocation":inclocatTemp};

	    	
	    	$http.put(resource+"ticket/"+ "user/"+user,JSON.stringify(a1)).
			  success(function(data)
					  {
				  alert("logged ....");
			    	alert("Now upload Images");
			    	localStorage.setItem('ticketid', data); 
			   
			    	
			    	$location.path("/imagesupload");
				  
					  }).
					  error(function(data, status, headers, config) {
						  alert("not logged");
						  });
	    };
	    
	    
	    $scope.raiseclaimfromlatlong=function()
	    {
	    	
	    	
	    	
	    	console.log($scope.cause+$scope.latitude+$scope.longitude+$scope.EquipmentName+$scope.IncidentLocation);
	    	
	    	values={cause:$scope.cause,lat:$scope.latitude,long:$scope.longitude,equipname:$scope.EquipmentName,incidentloc:$scope.IncidentLocation};
	    	$location.path("/samepageinshowonmap");
	    	};
	    
	    $scope.showonmap=function()
	    {
	    	values={cause:$scope.cause,lat:$scope.latitude,long:$scope.longitude,equipname:$scope.EquipmentName,incidentloc:$scope.IncidentLocation};
	    };
	   
	
	    
    });

    module.controller('mainController', function($scope,$location,$http) {
    	
    	$scope.register=function()
    	{
    		//alert($scope.fname+" "+$scope.lname+" "+$scope.phno+" "+$scope.email+" "+$scope.pwd);
    		 $http({
 	            method:'POST',
 	            url : config.resource+"register/user",
 	            data: {"firstName" : $scope.fname,"password" : $scope.pwd ,"lastName" :$scope.lname,"email" :$scope.email,"contactNumber":  $scope.phno},
 	            dataType: "text",
 	          })
 	        .success(function (data) {
 	        	alert(data);
 	        	
 		    	}).
 		    	error(function()
 		    			{
 		    		
 		    			});
 	    		
    		
    	};
    	
    	
    });
  
 module.controller('managerviewallcont', function($scope,$location,$http) {
    	
	 var resource = config.resource;
		var latTemp=[];
		var longTemp=[];
		var ticket=[];
		var ticket_status=[];
		var user = localStorage.getItem('usersessid');
		  $http({
	            method:'GET',
	            url : resource+ "Manager/"+"userview/"+user,
	            dataType: "json",
	          })
	        .success(function (data) {
	        	
	        	for(i=0;i<data.length;i++){
	    			latTemp[i]=data[i].lat;
	    			longTemp[i]=data[i].long;
	    			ticket[i]=data[i].ticket_Id;
	    			ticket_status[i]=data[i].ticket_Status;
	    			//alert("data is:"+latTemp[i]+longTemp[i]+ticket[i]);
	    		
		    	}
	    		
	    		
   			 var infoWindow;
   			    var latlngbounds;
   			    var map;
   			    var  ticketdetails;
   			
   			 var mapOptions = {
   					 
   		             center: new google.maps.LatLng(17,78),
   					// center:myCenter,
   		                zoom: 15,
   		                mapTypeId: google.maps.MapTypeId.ROADMAP
   		            };
   		     infoWindow = new google.maps.InfoWindow(
   		    		 /*{ content: 'Latitude: ' +latTemp +
   		    		    '<br>Longitude: ' + longTemp
   		    		 }*/
   		    		 );
   		           latlngbounds = new google.maps.LatLngBounds();
   		            map = new google.maps.Map(document.getElementById("container-map1"), mapOptions);
   		           // alert("center"+center);
   		            var marker=[];
   		            var infowindow="infowindow";
   		           
   		            for(i=0;i<data.length;i++){
   		            	if(ticket_status[i]==="Manager-Approved")
   		            			{
   		            		//alert("enterd to tickt");
   		            		   marker[i]=new google.maps.Marker({
		    		            	  position:new google.maps.LatLng(latTemp[i],longTemp[i]),
		    		            	  icon:'Pink.png',
		    		            	 info:'TicketId:'+ticket[i] +'<br>'+'TicketStatus:'+ticket_status[i],
		    		            	  title:'Latitude: ' +latTemp[i] +'Longitude: ' + longTemp[i]
		    		            	  });
		    		             marker[i].setMap(map);
	    		            	var infowindow = new google.maps.InfoWindow(/*{
	    		            		  content: 'Latitude: ' +latTemp[i] +
	    		  	    		    '<br>Longitude: ' + longTemp[i]
	    		  	    		    
	    		            		  }*/);

	    		            		/*google.maps.event.addListener( marker, 'click', function() {
	    		            			 infowindow.open(map, marker);*/
	    		            	google.maps.event.addListener( marker[i], 'click', function() {
   		            			
   		            			infowindow.setContent(this.info);
   		            			 infowindow.open(map,this);
	    		            		  });
   		            			}
   		            	else if(ticket_status[i]==="Manager-Rejected")
	            			{
	            		//alert("enterd to tickt");
	            		   marker[i]=new google.maps.Marker({
    		            	  position:new google.maps.LatLng(latTemp[i],longTemp[i]),
    		            	  icon:'img/redball.png',
    		            	 info:'TicketId:'+ticket[i] +'<br>'+'TicketStatus:'+ticket_status[i],
    		            	  title:'Latitude: ' +latTemp[i] +'Longitude: ' + longTemp[i]
    		            	  });
    		             marker[i].setMap(map);
		            	var infowindow = new google.maps.InfoWindow(/*{
		            		  content: 'Latitude: ' +latTemp[i] +
		  	    		    '<br>Longitude: ' + longTemp[i]
		  	    		    
		            		  }*/);

		            	google.maps.event.addListener( marker[i], 'click', function() {
		            			
		            			infowindow.setContent(this.info);
		            			 infowindow.open(map,this);
		            		  });
	            			}
   		            	//var data=data[i];
   		            	//alert(data);
   		            	else{
   		            		 /*ticketdetails = new google.maps.LatLng(latTemp[i] +longTemp[i]);*/
   		            		//alert("noooo");
   		            		 marker[i]=new google.maps.Marker({
		    		            	  position:new google.maps.LatLng(latTemp[i],longTemp[i]),
		    		            	  icon:'img/blueball.png',
		    		            	  info:'TicketId:'+ticket[i] +'<br>'+'TicketStatus:'+ticket_status[i],
	 	    		  	    		    
		    		            	  title:'Latitude: ' +latTemp[i] +'Longitude: ' + longTemp[i]
		    		            	  });
		    		             marker[i].setMap(map);
	    		          var  infowindow = new google.maps.InfoWindow(/*{ content: 'Latitude: ' +latTemp[i] +
		  	    		    '<br>Longitude: ' + longTemp[i]}*/ );
	    		            		  
	    		            	
	    		            		google.maps.event.addListener( marker[i], 'click', function() {
	    		            			
	    		            			infowindow.setContent(this.info);
	    		            			 infowindow.open(map,this);
	    		            			 
	    		            		  });
   		            	}
   		          
		            }
   		           
   		            	
   				
   		

	        	
	        }).
		  error(function(data, status, headers, config) {
			  alert("not logged");
			  });
	
		
 
});
  
    
    
  module.controller('approvemanager', function($scope,$location,$http,$compile) {
    	
	  var user= localStorage.getItem('usersessid'); 
		var resource = config.resource;
		  $http({
	            method:'GET',
	            url : resource+ "Manager/"+user,
	            dataType: "json",
	          })
	        .success(function (data) {
	        	
	        	var a=angular.element($("#5_tbl_tickdetails"));
	        	a.empty();
	        
	    		  var row = (  "<table class='table' style='text-align:center'>"+"<tr class='success '><td  style=' text-align: left'>" + "Ticket Id" + "</td><td>" + "Ticket Status" +  "</td><td>" + "Raising Date" +"</td></tr>"+"</table>");
	    	 a.append(row);

	    		  for(var i=0;i<data.length;i++)
	      	  {

	    			 
	      	  var row = ("<table class='table' style='text-align: center'>"+"<tr class='info'><td>"+"<a  ng-click='usertickets($event)' data='"+data[i].ticket_Id+"'>"+data[i].ticket_Id+"</a></td><td>"+data[i].ticket_Status+"</td><td>"+data[i].raising_Date+"</td></tr>"+"</table>");
	      	 var temp = $compile(row)($scope);

	       a.append(temp);

	      	  }


	        	
	        }).
		  error(function(data, status, headers, config) {
			  alert("not logged");
			  });
  	
$scope.usertickets=function(obj)
{
	  var id=obj.target.attributes.data.value;
	
		$location.path("/showindividualbymanager");
	  	 ticketid=id;
};
    	

    	
 
});
    
    
    
  
    
    

    module.controller('imageuploadController', ['$scope','$http','$compile', 'fileUpload',function($scope,$http,$compile,fileUpload) {
    	
    	$scope.uploadimage = function(){
    		var resource = config.resource;
   		 var tid=localStorage.getItem('ticketid');
   	        var file = $scope.image1;
   	        var file1=$scope.image2;
   	        console.log('file is ' );
   	        console.dir(file);
   	        var uploadUrl = "/fileUpload";
   	       
   			 
   	        fileUpload.uploadFileToUrl(file,file1, resource+"ticket/"+ "uploadfile/"+tid);
   	    };
    	
    	
    	
    	
       
    }]);
    
    module.controller('imageuploadController1', function($scope,$location) {
    	
    	
		
		if(localStorage.getItem('usersessid')==null || localStorage.getItem('usersessid')=="")
		{
			var resourcehome = config.resourcehome;
		window.location=resourcehome+"Home.html";
		}
    	
    	$scope.updateticket=function()
    	{
    		var user= localStorage.getItem('usersessid'); 
    		var resource = config.resource;
    		
    		  $http({
  	            method:'GET',
  	            url : resource+"ticket/"+ "user/"+user,
  	            dataType: "json",
  	          })
  	        .success(function (data) {
  	        	
  	        	console.log(data);
  	        	dataoftickets=data;
  	        	
  	        }).
    		  error(function(data, status, headers, config) {
				  alert("not logged");
				  });
    		
    		
  	        	
  	        	
  	        	
    	};
    		        		    	//localStorage.setItem('ticketid', data); 
    		    	
    		    	
    		    	/*$("#main_container").load("html/UploadImages.html",function(){
    		    		
    		    		
    		    	
    		    		
    		    	}); */
    		    	
    		    	
    	
        
    });
    
 module.controller('imageuploadController2', function($scope) {
    	
    	window.scroll(0, 500);
    	
    	
        
    });
    
    
    module.controller('showonmapr', function($scope,$http) {
        
    	$scope.cause=values.cause;
    	$scope.latitude=values.lat;
    	$scope.longitude=values.long;
    	$scope.EquipmentName=values.equipname;
    	$scope.IncidentLocation=values.incidentloc;
    	var resource = config.resource;
		var latTempm=[];
		var longTempm=[];
		var ticketm=[];
		var ticket_statusm=[];
		var latlongfromchange;
		var user = localStorage.getItem('usersessid');
		
		    var latlngbounds;
		    var map;
		    
		    if(values.lat==null )
		    	{
		    	values.lat=17;
		    	
		    	}
		    if(values.long==null)
		    	{
		    	values.long=78;
		    	}
		    var latlng = new google.maps.LatLng(values.lat, values.long);
		    var mapOptions = {
			    	  center:latlng,
			    	  zoom:15,
			    	  mapTypeId:google.maps.MapTypeId.ROADMAP,
					   /* mapTypeControl:false,
					    navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}*/
		            }
		    var map = new google.maps.Map(document.getElementById("container-map"), mapOptions);
		    $http({
	            method:'GET',
	            url : resource+ "Manager/"+"userviewonly/"+user,
	            dataType: "json",
	          })
	        .success(function (data) {
	        	 var infowindowm = new google.maps.InfoWindow(
		    			 
		    	  );
		    			    var latlngboundsm;
		    			
		    			    var  ticketdetails;
		    			    for(var i=0;i<data.length;i++){
   			    			latTempm[i]=data[i].lat;
   			    			longTempm[i]=data[i].long;
   			    			ticketm[i]=data[i].ticket_Id;
   			    			ticket_statusm[i]=data[i].ticket_Status;
   			    			
   				    	}
   			    		
		    			/* var mapOptions = {
		    					 
		    		             center: new google.maps.LatLng(17,78),
		    					// center:myCenter,
		    		                zoom: 10,
		    		                mapTypeId: google.maps.MapTypeId.ROADMAP
		    		            };*/
		    		    
		    		           //latlngbounds = new google.maps.LatLngBounds();
		    		           // map = new google.maps.Map(document.getElementById("container-map"), mapOptions);
		    		           // alert("center"+center);
		    		            var markerm=[];
		    		            //var infowindow="infowindow";
		    		           
		    		            for(i=0;i<data.length;i++){
		    		            	if(ticket_statusm[i]==="Manager-Approved")
		    		            			{
		    		            		
		    		            		   markerm[i]=new google.maps.Marker({
		 		    		            	  position:new google.maps.LatLng(latTempm[i],longTempm[i]),
		 		    		            	  icon:'img/greenball.png',
		 		    		            	 info:'TicketId:'+ticketm[i] +'<br>'+'TicketStatus:'+ticket_statusm[i],
		 		    		            	  title:'Latitude: ' +latTempm[i] +'Longitude: ' + longTempm[i]
		 		    		            	  });
		 		    		             markerm[i].setMap(map);
		 		    		           

		 	    		            		
		 		    		           google.maps.event.addListener( markerm[i], 'click', function() {
		    		            			
		    		            			infowindowm.setContent(this.info);
		    		            			 infowindowm.open(map,this);
	 	    		            		  });
	    		            			}
		    		            	else if(ticket_statusm[i]==="Manager-Rejected")
   		            			{
   		            		//alert("enterd to tickt");
   		            		   markerm[i]=new google.maps.Marker({
		    		            	  position:new google.maps.LatLng(latTempm[i],longTempm[i]),
		    		            	  icon:'img/redball.png',
		    		            	 info:'TicketId:'+ticketm[i] +'<br>'+'TicketStatus:'+ticket_statusm[i],
		    		            	  title:'Latitude: ' +latTempm[i] +'Longitude: ' + longTempm[i]
		    		            	  });
		    		             markerm[i].setMap(map);
	    		            	

	    		            	google.maps.event.addListener( markerm[i], 'click', function() {
	    		            			
	    		            			infowindowm.setContent(this.info);
	    		            			 infowindowm.open(map,this);
	    		            		  });
   		            			}
		    		            	//var data=data[i];
		    		            	//alert(data);
		    		            	else{
		    		            		
		    		            		 markerm[i]=new google.maps.Marker({
		 		    		            	  position:new google.maps.LatLng(latTempm[i],longTempm[i]),
		 		    		            	  icon:'img/blueball.png',
		 		    		            	  info:'TicketId:'+ticketm[i] +'<br>'+'TicketStatus:'+ticket_statusm[i],
			 	    		  	    		    
		 		    		            	  title:'Latitude: ' +latTempm[i] +'Longitude: ' + longTempm[i]
		 		    		            	  });
		 		    		             markerm[i].setMap(map);
		 	    		         
		 	    		            	
		 	    		            		google.maps.event.addListener( markerm[i], 'click', function() {
		 	    		            			
		 	    		            			infowindowm.setContent(this.info);
		 	    		            			 infowindowm.open(map,this);
		 	    		            			 
		 	    		            		  });
		    		            	}
		    		          
	    		            }
		    		           
		    		          
		    		         
		    	
	        })
	        .error(function (error) {
	            alert("error");
	        });
		    var address;
			   var infowindow;
			  
			    var geocoder = geocoder = new google.maps.Geocoder();
			    geocoder.geocode({ 'latLng': latlng }, function (results, status) {
			        if (status == google.maps.GeocoderStatus.OK) {
			            if (results[1]) {
			            	//localStorage.setItem('locationaddress',results[1].formatted_address);
			            	
			            	infowindow = new google.maps.InfoWindow({
			     		        content: results[1].formatted_address+ " "+$scope.latitude+" "+$scope.longitude
			     		      });
	               // alert("Location: " + results[1].formatted_address + "\r\nLatitude: " + latTemp+ "\r\nLongitude: " + longTemp);
	               
			            }
			        }
			    });
			   
			   

			    
			    
			    var marker = new google.maps.Marker({position:latlng,map:map,title:"You are here!",animation:google.maps.Animation.BOUNCE});
			    marker.addListener('click', function() {
			        infowindow.open(map, marker);
			      });
			    
			    
			    google.maps.event.addListener(map, "click", function (e) {

			        //lat and lng is available in e object
			        var latLng = e.latLng;
	latfromchange=e.latLng.lat();
	longfromchange=e.latLng.lng();
			    //alert(latfromchange+"   "+ longfromchange);
			    
			    });
			
			    
			    
			    $scope.backbuttonofraisemap=function()
			    {
			    	//values={cause:$scope.cause,lat:$scope.latitude,long:$scope.longitude,equipname:$scope.EquipmentName,incidentloc:$scope.IncidentLocation};
			    	$scope.cause=values.cause;
			    	$scope.latitude=values.lat;
			    	$scope.longitude=values.long;
			    	$scope.EquipmentName=values.equipname;
			    	$scope.IncidentLocation=values.incidentloc;
			    };
			    
			    
			    
			    $scope.submitfromshowonmap=function()
			    {
			    	var user= localStorage.getItem('usersessid'); 
			    	var resource = config.resource;
			    
			    	causeTemp=$scope.cause;
			    	latTemp=$scope.latitude;
			    	longTemp=$scope.longitude;
			    	equipmentTemp=$scope.EquipmentName;
			    	inclocatTemp=$scope.IncidentLocation;
			    	
			    	a1={"id":"dummy","Ticket_Id":"dummy","cause":causeTemp,"lat":latTemp,"long":longTemp,"image1":"dummy","image2":"dummy","file_Path":"dummy","equipmentNames":equipmentTemp,"incidentLocation":inclocatTemp};

			    	
			    	$http.put(resource+"ticket/"+ "user/"+user,JSON.stringify(a1)).
					  success(function(data)
							  {
						  alert("logged ....");
					    	alert("Now upload Images");
					    	localStorage.setItem('ticketid', data); 
					   
					    	
					    	$location.path("/imagesupload");
						  
							  }).
							  error(function(data, status, headers, config) {
								  alert("not logged");
								  });
			    
			    };
			    
			    $scope.changelatlong=function()
			    {
			    	//values={cause:$scope.cause,lat:$scope.latitude,long:$scope.longitude,equipname:$scope.EquipmentName,incidentloc:$scope.IncidentLocation};
			    	
			    };
			    
			    
			  
			 
    	
    });
    
    
    
    module.controller('showonmapr1', function($scope,$http) {
        
    	var latitude=latfromchange;
    	var longitude=longfromchange;
    	 var infowindow;
    	var latlng = new google.maps.LatLng(latitude, longitude);
    	 var mapOptions = {
    	    	  center:latlng,
    	    	  zoom:10,
    	    	  mapTypeId:google.maps.MapTypeId.ROADMAP,
    			    mapTypeControl:false,
    			    navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}
              };
    	 
    	 var geocoder = geocoder = new google.maps.Geocoder();
    	    geocoder.geocode({ 'latLng': latlng }, function (results, status) {
    	        if (status == google.maps.GeocoderStatus.OK) {
    	            if (results[1]) {
    	            	//localStorage.setItem('locationaddress',results[1].formatted_address);
    	            	
    	            	infowindow = new google.maps.InfoWindow({
    	     		        content: results[1].formatted_address+ " "+latitude+" "+longitude
    	     		      });
         // alert("Location: " + results[1].formatted_address + "\r\nLatitude: " + latTemp+ "\r\nLongitude: " + longTemp);
         
    	            }
    	        }
    	    });
    		var map = new google.maps.Map(document.getElementById("container-map"), mapOptions);
    	
    		
    		//step 1
    		map.setOptions({draggableCursor:'crosshair'});
    	
    	
    	 
    	
    	//alert(latitude+" "+longitude);
    	
    	


    	var marker = new google.maps.Marker({position:latlng,map:map,title:"You are here!",animation:google.maps.Animation.BOUNCE,  });
    	 marker.addListener('click', function() {
    	        infowindow.open(map, marker);
    	      });
    	
    	  google.maps.event.addListener(map, "click", function (e) {
    		  
    		  var longold=  $("#raiseclaim-Map-longitude").val();
    			var latold=$("#raiseclaim-Map-latitude").val();
    		   //lat and lng is available in e object
    	        var latLng = e.latLng;
    latfromchange=e.latLng.lat().toFixed(6);
    longfromchange=e.latLng.lng().toFixed(6);

    		  var radlat1 = Math.PI * latold/180;

    		    var radlat2 = Math.PI * latfromchange/180;

    		    var radlon1 = Math.PI * longold/180;

    		    var radlon2 = Math.PI * longfromchange/180;

    		    var theta = longold-longfromchange;

    		    var radtheta = Math.PI * theta/180;

    		    var dist = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);

    		    dist = Math.acos(dist);

    		    dist = dist * 180/Math.PI;

    		    dist = dist * 60 * 1.1515;

    		    dist = dist * 1.609344;
    		    
    		    dist=dist.toFixed(2);

    		
    		var accept=window.confirm("are u sure u want to change\n"+"old latitude: "+latold+" old longitude: "+longold+"\n"+"new latitude: "+latfromchange+" new longitude: "+longfromchange+"\n"+"distance between two points"+dist+" km");  
    		if(accept==true)
    			{
    			$("#raiseclaim-Map-longitude").val(longfromchange);
    			$("#raiseclaim-Map-latitude").val(latfromchange);
    		
    			//$("#raiseclaim-butt-changelatlong").click();
    			
    			
    			
    			//trail 
    			
    			
    			var latlng1= new google.maps.LatLng(latfromchange, longfromchange);
    		 mapOptions = {
    			    	  center:latlng1,
    			    	  zoom:10,
    			    	  mapTypeId:google.maps.MapTypeId.ROADMAP,
    					    mapTypeControl:false,
    					    navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}
    		          };
    			 geocoder = new google.maps.Geocoder();
    			    geocoder.geocode({ 'latLng': latlng1 }, function (results, status) {
    			        if (status == google.maps.GeocoderStatus.OK) {
    			            if (results[1]) {
    			            	//localStorage.setItem('locationaddress',results[1].formatted_address);
    			            	
    			            	infowindow = new google.maps.InfoWindow({
    			     		        content: results[1].formatted_address+ " "+latfromchange+" "+longfromchange
    			     		      });
    		     // alert("Location: " + results[1].formatted_address + "\r\nLatitude: " + latTemp+ "\r\nLongitude: " + longTemp);
    		     
    			            }
    			        }
    			    });
    				 map = new google.maps.Map(document.getElementById("container-map"), mapOptions);
    			
    				mapfr=mapOptions;
    				//step 1
    			
    			
    			
    			 
    			
    			//alert(latitude+" "+longitude);
    			
    			
    marker.setMap(null);

    		marker = new google.maps.Marker({position:latlng1,map:map,title:"You are here!",animation:google.maps.Animation.BOUNCE,  });
    			 marker.addListener('click', function() {
    			        infowindow.open(map, marker);
    			      });
    			
    			
    			
    			}
    			
    	     








    	   // alert(latfromchange+"   "+ longfromchange);
    	    
    	    });




			    
			    
			    $scope.backbuttonofraisemap=function()
			    {
			    	//values={cause:$scope.cause,lat:$scope.latitude,long:$scope.longitude,equipname:$scope.EquipmentName,incidentloc:$scope.IncidentLocation};
			    	$scope.cause=values.cause;
			    	$scope.latitude=values.lat;
			    	$scope.longitude=values.long;
			    	$scope.EquipmentName=values.equipname;
			    	$scope.IncidentLocation=values.incidentloc;
			    };
			    
			    
			    
			    $scope.submitfromshowonmap=function()
			    {
			    	var user= localStorage.getItem('usersessid'); 
			    	var resource = config.resource;
			    
			    	causeTemp=$scope.cause;
			    	latTemp=$scope.latitude;
			    	longTemp=$scope.longitude;
			    	equipmentTemp=$scope.EquipmentName;
			    	inclocatTemp=$scope.IncidentLocation;
			    	
			    	a1={"id":"dummy","Ticket_Id":"dummy","cause":causeTemp,"lat":latTemp,"long":longTemp,"image1":"dummy","image2":"dummy","file_Path":"dummy","equipmentNames":equipmentTemp,"incidentLocation":inclocatTemp};

			    	
			    	$http.put(resource+"ticket/"+ "user/"+user,JSON.stringify(a1)).
					  success(function(data)
							  {
						  alert("logged ....");
					    	alert("Now upload Images");
					    	localStorage.setItem('ticketid', data); 
					   
					    	
					    	$location.path("/imagesupload");
						  
							  }).
							  error(function(data, status, headers, config) {
								  alert("not logged");
								  });
			    
			    };
			    
			    $scope.changelatlong=function()
			    {
			    	//values={cause:$scope.cause,lat:$scope.latitude,long:$scope.longitude,equipname:$scope.EquipmentName,incidentloc:$scope.IncidentLocation};
			    	
			    };
			    
			    
			  
			 
    	
    });
    
    //verify by contractor
    module.controller('verifycontractorcontroller', function($scope,$http,$location,$compile) {
    	alert("entered into verifycontractorcontroller");
    	var resource = config.resource;
		
		  var user = localStorage.getItem('usersessid');
		 
		 
	
		 

	    	 $http({
	             method:'GET',
	             url : resource+ "Manager/"+user,
	             dataType: "json",
	             async:false,
	           })
	           .success(function (data) {
	        	   var a=angular.element($("#5_tbl_tickdetails"));
		        	a.empty();
		        
		    		  var row = (  "<table class='table' style='text-align:center'>"+"<tr class='success '><td  style=' text-align: left'>" + "Ticket Id" + "</td><td>" + "Ticket Status" +  "</td><td>" + "Raising Date" +"</td></tr>"+"</table>");
		    	 a.append(row);

		    		  for(var i=0;i<data.length;i++)
		      	  {

		    			 
		      	  var row = ("<table class='table' style='text-align: center'>"+"<tr class='info'><td>"+"<a  ng-click='usertickets($event)' data='"+data[i].ticket_Id+"'>"+data[i].ticket_Id+"</a></td><td>"+data[i].ticket_Status+"</td><td>"+data[i].raising_Date+"</td></tr>"+"</table>");
		      	 var temp = $compile(row)($scope);

		       a.append(temp);

		      	  }


		        	
		        }).
			  error(function(data, status, headers, config) {
				  alert("not logged");
				  });
	    	 	
	    	 $scope.usertickets=function(obj)
	    	 {
	    	 	  var id=obj.target.attributes.data.value;
	    	 	
	    	 		$location.path("/showindividualbycontractor");
	    	 	  	 ticketid=id;
	    	 };
	        	 
	        	
   });
    
    //ended by contractor
    

    module.controller('showonmap', function($scope,$http,$location) {
     
    
    	
    	$scope.cause=values.cause;
    	$scope.latitude=values.lat;
    	
    	$scope.longitude=values.long;
    	
    	$scope.EquipmentName=values.equipname;
    	$scope.IncidentLocation=values.incidentloc;
    	
    	
    	var latitude=17.32;
    	var longitude=78.236;
    	 var infowindow;
    	var latlng = new google.maps.LatLng(latitude, longitude);
    	 var mapOptions = {
    	    	  center:latlng,
    	    	  zoom:15,
    	    	  mapTypeId:google.maps.MapTypeId.ROADMAP,
    			    mapTypeControl:false,
    			    navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}
              };
    	 
    	 var geocoder = geocoder = new google.maps.Geocoder();
    	    geocoder.geocode({ 'latLng': latlng }, function (results, status) {
    	        if (status == google.maps.GeocoderStatus.OK) {
    	            if (results[1]) {
    	            	//localStorage.setItem('locationaddress',results[1].formatted_address);
    	            	
    	            	infowindow = new google.maps.InfoWindow({
    	     		        content: results[1].formatted_address+ " "+latitude+" "+longitude
    	     		      });
         // alert("Location: " + results[1].formatted_address + "\r\nLatitude: " + latTemp+ "\r\nLongitude: " + longTemp);
         
    	            }
    	        }
    	    });
    		var map = new google.maps.Map(document.getElementById("container-mapr"), mapOptions);
    	
    		
    		//step 1
    		map.setOptions({draggableCursor:'crosshair'});
    	
    	
    	
    	  google.maps.event.addListener(map, "click", function (e) {
    		  
    		  var longold=  $("#raiseclaim-Map-longitude").val();
    			var latold=$("#raiseclaim-Map-latitude").val();
    		   //lat and lng is available in e object
    	        var latLng = e.latLng;
    latfromchange=e.latLng.lat().toFixed(6);
    longfromchange=e.latLng.lng().toFixed(6);
    
    var accept=window.confirm("New latitude :"+latfromchange+"\n"+"New longitude :"+longfromchange+"\n"); 
	if(accept==true)
		{
		$("#longitude").val(longfromchange);
		$("#latitude").val(latfromchange);
	
		//$("#raiseclaim-butt-changelatlong").click();
		
		
		
		//trail 
		
		
		var latlng1= new google.maps.LatLng(latfromchange, longfromchange);
	 mapOptions = {
		    	  center:latlng1,
		    	  zoom:15,
		    	  mapTypeId:google.maps.MapTypeId.ROADMAP,
				    
	          };
		 geocoder = new google.maps.Geocoder();
		    geocoder.geocode({ 'latLng': latlng1 }, function (results, status) {
		        if (status == google.maps.GeocoderStatus.OK) {
		            if (results[1]) {
		            	//localStorage.setItem('locationaddress',results[1].formatted_address);
		            	
		            	infowindow = new google.maps.InfoWindow({
		     		        content: results[1].formatted_address+ " "+latfromchange+" "+longfromchange
		     		      });
	     // alert("Location: " + results[1].formatted_address + "\r\nLatitude: " + latTemp+ "\r\nLongitude: " + longTemp);
	     
		            }
		        }
		    });
			 map = new google.maps.Map(document.getElementById("container-mapr"), mapOptions);
		
			mapfr=mapOptions;
			//step 1
		
		
		
			 
		
		//alert(latitude+" "+longitude);
		
		


var	marker = new google.maps.Marker({position:latlng1,map:map,title:"You are here!",animation:google.maps.Animation.BOUNCE,  });
		 marker.addListener('click', function() {
		        infowindow.open(map, marker);
		      });
		
		
		
		}
    	  });
    	  
    	  
  	    $scope.submitticket=function()
  	    {
  	    	var user= localStorage.getItem('usersessid'); 
  	    	var resource = config.resource;
  	    	console.log($("#cause").val());
  	    	var causeTemp=$("#cause").val();
  	    	var latTemp=$("#latitude").val();
  	    	var longTemp=$("#longitude").val();
  	    	var equipmentTemp=$("#equipment").val();
  	    	var inclocatTemp=$("#incidentLocation").val();
  	    	a1={"id":"dummy","Ticket_Id":"dummy","cause":causeTemp,"lat":latTemp,"long":longTemp,"image1":"dummy","image2":"dummy","file_Path":"dummy","equipmentNames":equipmentTemp,"incidentLocation":inclocatTemp};

  	    	
  	    	$http.put(resource+"ticket/"+ "user/"+user,JSON.stringify(a1)).
  			  success(function(data)
  					  {
  				  alert("logged ....");
  			    	alert("Now upload Images");
  			    	localStorage.setItem('ticketid', data); 
  			   
  			    	
  			    	$location.path("/imagesupload");
  				  
  					  }).
  					  error(function(data, status, headers, config) {
  						  alert("not logged");
  						  });
  	    };
  	    
  	    
  	    $scope.raiseclaimfromlatlong=function()
  	    {
  	    	
  	    	
  	    	
  	    	console.log($scope.cause+$scope.latitude+$scope.longitude+$scope.EquipmentName+$scope.IncidentLocation);
  	    	
  	    	values={cause:$scope.cause,lat:$scope.latitude,long:$scope.longitude,equipname:$scope.EquipmentName,incidentloc:$scope.IncidentLocation};
  	    	$location.path("/samepageinshowonmap");
  	    	};
  	    
  	    $scope.showonmap=function()
  	    {
  	    	values={cause:$scope.cause,lat:$scope.latitude,long:$scope.longitude,equipname:$scope.EquipmentName,incidentloc:$scope.IncidentLocation};
  	    
  	    };
  	   
		
     


    });
    
    
    
    
    
    
    
    
    
    
 