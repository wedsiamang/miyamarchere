<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import = "java.util.List,model.Goods,model.Shop"%>
 
    <%
    List<Goods>goodsList=(List<Goods>)request.getAttribute("goodsList");
  	List<Shop>shopList=(List<Shop>)request.getAttribute("shopList");
  
    List<Goods>sortHalf=(List<Goods>)request.getAttribute("sortHalf");
   
    Shop login=(Shop)session.getAttribute("login");
    
    %>
<!DOCTYPE html>
<html>

<head>
	 <link rel="stylesheet" href="css/card.css">
	   <link rel="stylesheet" href="css/header.css">
	  
	   <link rel="stylesheet" href="css/previewGoodsAll.css">
	 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
	 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	 <!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">   
</head>
<body>
 
	 <%
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
					<%for(Goods sh : sortHalf){ %>
						<div class="col-md-2">
							<div class="card border-light" style="width: 12rem; top:100px;">
								<form name="form"action="/miyama1/Servlet" method="post">
    								<input type="hidden" name="goods_id"value="<%=sh.getGoods_id() %>">
									<button id="btn" class="btn-outline">
									<img class="card-img-top" src="data:image/*;base64,<%=sh.getGoods_img() %>">
									<div class="card-img-overlay h-100 d-flex flex-column text-white">
										<h2><%=sh.getDiscount_rate() %>% OFF</h2>
									</div>
									</button>
								</form>
 								<div class="card-body">
									<h5 class="card-title text-center" ><%=sh.getGoods_name() %></h5>
       								<p class="card-text text-center"class="small"> <%=sh.getQuantity() %><%=sh.getUnit() %></p>
        							<p class="card-text text-center"class="text-decoration-line-through"class="small">  <%=sh.getList_price()%>円⇒<%=sh.getSelling_price() %>円</p>
								     <p class="card-text  text-center"class="small"><%=sh.getShop_name() %></p>
     							</div>
         					</div>
          				</div>
        			<%} %>
        		</div> 
       		</div>  
		</div>
	</div>	
</body>

</html>