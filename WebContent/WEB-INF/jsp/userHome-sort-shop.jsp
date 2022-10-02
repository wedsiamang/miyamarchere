
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@ page import = "java.util.List,model.Goods,model.Shop"%>
 
    <%
    List<Goods>sortShop=(List<Goods>)request.getAttribute("sortShop");
  	List<Shop>shopList=(List<Shop>)request.getAttribute("shopList");
    
  
    %>

<!DOCTYPE html>

<html>
  <head>
	  <link rel="stylesheet" href="css/card.css">
	   <link rel="stylesheet" href="css/header.css">
	   <link rel="stylesheet" href="css/userHome.css">
	 
	   
	   <!-- bootstrap card -->
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
			<h1>ミヤマルシェア</h1>
		</div>
	</div>
	<div class="main">
   		<div class="container">
			<div class="row">   
				<%for(Goods ss : sortShop){ %>
					<div class="col-md-2" >
						<div class="card border-light shadow-sm" style="width: 12rem; top: 100px;"> 
      						<form name="form"action="/miyama1/CustomerPreviewGoodsServlet" method="post">
    							<input type="hidden" name="goods_id"value="<%=ss.getGoods_id() %>">
								<button id="btn" class="btn-outline">
									<img class="card-img-top" src="data:image/*;base64,<%=ss.getGoods_img() %>">
									<div class="card-img-overlay h-100 d-flex flex-column text-white ">
     									<%=ss.getDiscount_rate() %>% OFF
    								</div>
    							</button>
    						</form>
 							<div class="card-body">
								<h5 class="card-title text-center "><%=ss.getGoods_name() %></h5>
       								<p class="card-text text-center"class="small"> <%=ss.getQuantity() %><%=ss.getUnit() %></p>
        							<p class="card-text text-center"class="text-decoration-line-through"class="small">  <%=ss.getList_price()%>円⇒<%=ss.getSelling_price() %>円</p>
    								<p class="card-text text-center"class="small"> <%=ss.getShop_name() %></p>
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