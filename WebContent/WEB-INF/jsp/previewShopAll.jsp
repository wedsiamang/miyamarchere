<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.List,model.Shop"  %>
    <%
    List<Shop>shopList=(List<Shop>)request.getAttribute("shopList");
    
    
    %>
<!DOCTYPE html>
<html>
 <head>
  
    <link rel="stylesheet" href="css/halfHeader.css">
    <link rel="stylesheet" href="css/previewShopAll.css">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    
    <!-- JavaScript Bundle with Popper -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	 <!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">   
</head>
  <body>
 
	 <%String login=null;
	if(login!=null){ %>
	<jsp:include page="/WEB-INF/jsp/navigationLoggedIn.jsp" />
	 <%}else{ %>
	 <jsp:include page="/WEB-INF/jsp/navigationLoggedOut.jsp" />
	 <%} %>
	<div class="headbox">
		<div class="visual">
			<h2>登録店舗一覧</h2>
		</div>
	</div>

	<div class="main">
		<div class="container">
			<div class="row">
				<% for(Shop sl:shopList){ %>
					<div class="col-lg-6">
   						  <div class="card mb-3 mx-auto " style="max-width: 1200px;">
   							  <div class="row no-gutters ">
  								  <div class="col-md-6">
  								  	<div class="card border-light"style="max-width: 600px;">
  								  		 <form action="/miyama1/IntroductionShopServlet" method="post">
									    	 <input type="hidden"name="shop_name" value="<%=sl.getShop_name() %>"> 
									     	<button id="btn" class="btn-outline">
									  			<img src="data:image/*;base64,<%=sl.getShop_img() %>" width="250px" height="250px">
									     	</button>
									 	</form>
								   	</div>
								   </div>
 										<div class="col-md-6">
											<div class="card border-light"style="max-width: 600px;">
    										<h3 class="card-title text-center" > <%=sl.getShop_name() %></h3>
    										<br> <br>   
       										 <p class="card-text text-center">営業時間:<%=sl.getBusiness_hour() %>時</p>
									        <p class="card-text text-center">☎<%=sl.getTel() %></p>
									        <p class="card-text text-center"><%=sl.getAddress() %></p>
									 		</div>
    									</div>
								</div>
 						   </div>
     				</div>   
				<%} %>
			</div>
 		</div>
	</div>
	
		<script>
		var btn=document.getElementById('btn');
		btn.addEventListener('click',function()){
		alert(document.form.submit();
		})
		</script>
</body>
</html>