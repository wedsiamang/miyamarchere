<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "java.util.List,model.GoodsShop,model.Goods,model.Shop"%>
     <%
 	GoodsShop gs=(GoodsShop)request.getAttribute("gs");
     Goods g=(Goods)request.getAttribute("selectGoods");
     Shop login=(Shop)session.getAttribute("login");
    %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
   <link rel="stylesheet"href="css/halfHeader.css">
   <link rel="stylesheet"href="css/previewGoods.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	 <!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">   
	 <title>商品詳細</title>
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
			<h1>商品詳細</h1>
		</div>
	</div>
		<div class="main">
			<h1 class="text-center"><%=g.getShop_name() %></h1><br>
			<h3 class="card-text text-center">販売時間：<%=g.getStart_time() %>～<%=g.getEnd_time() %></h3> 
			<div class="box1">
				<div class="card  mx-auto"style="width:1000px;">
					<div class="card-body">
						<img src="data:image/*;base64,<%=g.getGoods_img()%>" width="500px"height="400px" class="float-start">
						<div class="btnkinds text-center">
							<button class="btnkinds-outline"><%=g.getKinds() %></button>
						</div><br>
						<h1 class="card-title text-center"><%=g.getGoods_name() %></h1>
						<br><br>
						<h4 class="card-text text-center">残り<%=g.getQuantity() %><%=g.getUnit() %></h4>
						<h4 class="card-text text-center">￥<%=g.getList_price() %>　→　￥<%=g.getSelling_price() %></h4>
						<h2 class="card-text text-center"><%=g.getDiscount_rate() %>%OFF</h2>
						<h4 class="card-text text-center">   出品理由:　<%=g.getGoods_comment() %></h4>
					</div>
				</div>
			</div>
			<div class="card mb-4 mx-auto border-light"style="max-width: 850px; top: 100px; bottom: 100px;">
				<h4 class="card-title text-center">店舗情報</h4><br>
				<div class="row no-gutters">
					<div class="col-md-4">
						<div class="card width: 18rem; border-light">
						<img src="data:image/*;base64,<%=gs.getShop_img() %>"width="280px"height="250px" >
						</div>
					 </div>
					<div class="col-md-4">
						<div class="card border-light"style="width: 18rem;">
							<div class="card-body ">
								<h6 class="card-text text-center"><%=gs.getShop_comment() %></h6><br>
								<h4 class="card-title text-center"><%=gs.getShop_name() %></h4>
								 <h5 class="text-center"><%=gs.getNearBy() %></h5>
								 <h5 class="card-text text-center">営業時間<%=gs.getBusiness_hour() %></h5><br>
								 <h5 class="card-text text-center"><%=gs.getTel()%></h5>
								<h5 class="card-text text-center"><%=gs.getAddress() %></h5>
							 </div>
						</div>
					</div>
								 
  					<div class="col-md-4 ">
  						<div class="card width: 18rem; border-light">
							<div id="gmap" style="width: 250px; height: 250px;"></div>
						</div>
					</div>

				</div>
			</div>
		</div>

 <script type="text/javascript">
  var map;
  var marker;
  var geocoder;
  function initMap() {
    geocoder = new google.maps.Geocoder();
    geocoder.geocode({
    'address':'<%=gs.getAddress() %>' //住所
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