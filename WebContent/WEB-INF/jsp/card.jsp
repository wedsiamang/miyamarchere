<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import = "java.util.List,model.Goods,model.Shop"%>
 
    <%
    List<Goods>goodsList=(List<Goods>)request.getAttribute("goodsList");
  	List<Shop>shopList=(List<Shop>)request.getAttribute("shopList");
    List<Goods>byKinds=(List<Goods>)request.getAttribute("byKinds");
    List<Goods>half=(List<Goods>)request.getAttribute("half");
    List<Goods>endgoodsList=(List<Goods>)request.getAttribute("endgoodsList");
    
  
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
 <!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">   
 

</head>
<body>
<div class="container">
<div class="row">   

<%for(Goods gs : goodsList){ %>

<div class="col-md-2" >
<div class="card border-light shadow-sm" style="width: 12rem;">
 
      <form name="form"action="/miyama1/CustomerPreviewGoodsServlet" method="post">
    <input type="hidden" name="goods_id"value="<%=gs.getGoods_id() %>">
		<button id="btn" class="btn-outline">
		<img class="card-img-top" src="data:image/*;base64,<%=gs.getGoods_img() %>">
<div class="card-img-overlay h-100 d-flex flex-column text-white ">
     <%=gs.getDiscount_rate() %>% OFF
    </div></button></form>

    
 <div class="card-body">
<h5 class="card-title text-center "><%=gs.getGoods_name() %></h5>

       <p class="card-text text-center"class="small"> <%=gs.getQuantity() %>個</p>
        <p class="card-text text-center"class="text-decoration-line-through"class="small">  <%=gs.getList_price()%>円⇒<%=gs.getSelling_price() %>円</p>
    <p class="card-text text-center"class="small"> <%=gs.getShop_name() %></p>
    </div>
    </div>
     </div>
        <%} %>
       
 <%for(Goods eg : endgoodsList){ %> 
<div class="col-md-2" > 
<div class="card border-light shadow-sm" style="width: 12rem;"> 
      
		<div class="dark"> 
		<img class="card-img-top" src="data:image/*;base64,<%=eg.getGoods_img() %>"> 
<div class="card-img-overlay h-100 d-flex flex-column text-white "> 
     <h3>終了</h3> 
    </div></div> 
     
 <div class="card-body"> 
<h5 class="card-title text-center text-muted"><%=eg.getGoods_name() %></h5> 
       <p class="card-text text-center text-muted"class="small"> <%=eg.getQuantity() %>個</p> 
        <p class="card-text text-center text-muted"class="text-decoration-line-through"class="small">  <%=eg.getList_price()%>円⇒<%=eg.getSelling_price() %>円</p> 
    <p class="card-text text-center text-muted"class="small"> <%=eg.getShop_name() %></p> 
    </div> 
    </div> 
     </div> 
        <%} %> 
       
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