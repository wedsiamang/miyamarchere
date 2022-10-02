<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.util.List,model.Shop,model.Goods"%>
 
    <%
    Shop i=(Shop)request.getAttribute("selectShop");
    List<Goods>myGoodsList=(List<Goods>)request.getAttribute("myGoodsList");
    Shop login=(Shop)session.getAttribute("login");
    List<Goods>myendgoodsList=(List<Goods>)request.getAttribute("myendgoodsList");
    %>
<!DOCTYPE HTML>
<html>
<head>

   <meta name="viewport" content="width=device-width,initial-scale=1">
	  <link rel="stylesheet" href="css/halfHeader.css">
	 <link rel="stylesheet" href="css/card.css">
	 <link rel="stylesheet" href="css/home.css">
	
	   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	 <!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">   


</head>
<body>
	<%if(login!=null){ %>
	<jsp:include page="/WEB-INF/jsp/navigationLoggedIn.jsp" />
	 <%}else{ %>
	 <jsp:include page="/WEB-INF/jsp/navigationLoggedOut.jsp" />
	 <%} %>
	 <div class="headbox">
		<div class="visual">
			<h1>店舗HOME</h1>
		</div>
	</div>
	
		<div class="main">
			<div class="box1">
				<div class="card2  mx-auto"style="width:1200px;">
					<div class="card-body2"><br><br>
						<h1 class="card-title text-center"><%=i.getShop_name() %></h1>
						 <h3 class="text-center"><%=i.getNearBy() %></h3>
						<img src="data:image/*;base64,<%=i.getShop_img() %>" width="1200px"height="600px" >
						<br><br>
						<h3 class="card-text text-center"><%=i.getShop_comment() %></h3>
					</div>
				</div>
			</div>
			<br><br>
			<h4 class="text-center">出品商品</h4>
    		<div class="container">
				<div class="row">   
					<%for(Goods mg:myGoodsList) {%>
 					<div class="col-md-3" >
						<div class="card border-light" style="width: 12rem;top: 50px;">
							<form name="form"action="/miyama1/PreviewGoodsServlet" method="get">
							    <input type="hidden" name="goods_id"value="<%=mg.getGoods_id() %>">
								<button id="btn" class="btn-outline">
								<img class="card-img-top" src="data:image/*;base64,<%=mg.getGoods_img() %>"alt="">
								<div class="card-img-overlay h-100 d-flex flex-column text-white ">
							    	 <%=mg.getDiscount_rate() %>% OFF
							    </div>
							    </button>
							 </form>
							  <div class="card-body"style="height:30px;">
								<h5 class="card-title text-center"><%=mg.getGoods_name() %></h5>
  								</div>
						 </div>
    					<form name="update" action="/miyama1/HomeServlet"method="post">
							<input type="hidden" name="goods_id"value="<%=mg.getGoods_id() %>">
							  <br>  <br><button id="btn" class="btn-outline">編集する</button>
						</form>       
						<form name="delete" action="/miyama1/DeleteGoodsServlet" method="post">
							 <input  type="hidden"  name="goods_id"value="<%=mg.getGoods_id()%>">
							  <br> <button id="btn" class="btn-outline">削除する</button>
						</form>
  					</div>
          			  <%} %>
        			<%for(Goods eg:myendgoodsList) {%>
					<div class="col-md-3" >
						<div class="card border-light" style="width: 12rem;top: 50px;">
							<div class="dark">
 								<img class="card-img-top" src="data:image/*;base64,<%=eg.getGoods_img() %>"alt="">
								<div class="card-img-overlay h-100 d-flex flex-column text-white ">
    								<h3>終了</h3>
    							</div>
    						</div>
    						 <div class="card-body"style="height:30px;">
								<h5 class="card-title text-center text-muted"><%=eg.getGoods_name() %></h5> 
    						</div>
    					</div>
          			</div>
        			<%} %>
       			</div> 
   		    </div>
			<div class="box3">
				<div class="card mx-auto"style="max-width: 900px;" >
					<div class="row no-gutters">
						 <div class="col-md-4">
 							<div id="gmap" style="width: 400px; height: 400px;"></div>
							</div>
						<div class="col-md-8">
							<div class="card-body">
								<h4 class="card-title text-center"><%=i.getShop_name() %></h4>
								 <h5 class="text-center"><%=i.getNearBy() %></h5>
								 <h5 class="card-text text-center">営業時間<%=i.getBusiness_hour() %>時</h5><br>
								 <h5 class="card-text text-center"><%=i.getTel()%></h5>
								<h5 class="card-text text-center"><%=i.getAddress() %></h5>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	<script>
		var btn=document.getElementById('btn');
		btn.addEventListener('click',function()){
			alert(document.form.submit();
		})
	
	</script>
	<!-- main close -->
	
	
	</div>
	<script type="text/javascript">
	  var map;
	  var marker;
	  var geocoder;
	  function initMap() {
	    geocoder = new google.maps.Geocoder();
	    geocoder.geocode({
	    'address':'<%=i.getAddress() %>' //住所
	    }, function(results, status) {
	      if (status === google.maps.GeocoderStatus.OK) {
	      map = new google.maps.Map(document.getElementById('gmap'), {
	        center: results[0].geometry.location,
	        zoom: 17 //ズーム
	     });
	    marker = new google.maps.Marker({
	    position: results[0].geometry.location,
	    map: map
	    });
	    infoWindow = new google.maps.InfoWindow({
	    content: '<h4>ツールチップのタイトル</h4>'
	    });
	    marker.addListener('click', function() {
	      infoWindow.open(map, marker);
	    });
	    } else {
	      alert(status);
	    }
	  });
	}
	</script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDvZjnPMOFvAdG5u4T9LlyYutI8dCLUvO0&callback=initMap" async></script>

</body>
</html>