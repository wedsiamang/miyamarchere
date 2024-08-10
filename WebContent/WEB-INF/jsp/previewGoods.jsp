<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,model.GoodsShop,model.Goods,model.Shop"%>
<%
GoodsShop gs = (GoodsShop) request.getAttribute("gs");
Goods g = (Goods) request.getAttribute("selectGoods");
Shop login = (Shop) session.getAttribute("login");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/halfHeader.css">
<link rel="stylesheet" href="js/googlemap.js">
<link rel="styelsheet" href="css/card-preview.css">
<link rel="styelsheet" href="css/footer.css">
<link rel="styelsheet" href="css/previewGoods.css">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous">
<!-- <link rel="stylesheet" href="css/customerPreviewGoods.css">-->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<!-- CSS only -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>
	<div class="all">
		<%
	if (login != null) {
	%>
		<jsp:include page="/WEB-INF/jsp/navigationLoggedIn.jsp" />
		<%
	} else {
	%>
		<jsp:include page="/WEB-INF/jsp/navigationLoggedOut.jsp" />
		<%
	}
	%>
		<div class="headbox">
			<div class="visual">
				<h1>商品詳細</h1>
			</div>
		</div>
		<div class="main">
			<h1 class="text-center"><%=gs.getShopName()%></h1>
		</div>

		<h3 class="card-text text-center">
			販売時間：<%=gs.getStartTime()%>～<%=gs.getEndTime()%></h3>
		<div class="box1">
			<div class="card  mx-auto" style="max-width: 1000px;">
				<div class="card-body container-fluid">
					<img src="data:image/*;base64,<%=g.getGoodsImg()%>"
						class="float-start img-fluid" style="margin-right: 20px;"
						alt="Responsive image">
					<div class="btnkinds text-center">
						<button class="btnkinds-outline"><%=g.getKinds()%></button>
					</div>
					<br>
					<h1 class="card-title text-center"><%=g.getGoodsName()%></h1>
					<br> <br>
					<h4 class="card-text text-center">
						残り<%=g.getQuantity()%><%=g.getUnit()%></h4>
					<h4 class="card-text text-center">
						￥<%=g.getListPrice()%>
						→ ￥<%=g.getSellingPrice()%></h4>
					<h2 class="card-text text-center"><%=g.getDiscountRate()%>%OFF
					</h2>
					<h4 class="card-text text-center">
						出品理由:
						<%=g.getGoodsComment()%></h4>
				</div>
			</div>
		</div>
		<div class="card mb-4 mx-auto"
			style="max-width: 850px; top: 20px; bottom: 20px;">
			<h4 class="card-title text-center">店舗情報</h4>
			<br>
			<div class="row justify-content-center">
				<div class="col-md-4">
					<div class="card border-light">
						<form name="form" action="/IntroductionShopServlet" method="get">
							<input type="hidden" name="shopName"
								value="<%=gs.getShopName()%>">
							<button id="btn" class="btn-outline">
								<div class="d-flex justify-content-center">
									<img src="data:image/*;base64,<%=gs.getShopImg()%>"
										style="width: 100%; max-width: 100%; height: 250px;">
								</div>
							</button>
						</form>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card border-light">
						<div class="card-body text-center">
							<h6 class="card-text"><%=gs.getShopComment()%></h6>
							<br>
							<h4 class="card-title"><%=gs.getShopName()%></h4>
							<h5><%=gs.getNearBy()%></h5>
							<h5 class="card-text">
								営業時間
								<%=gs.getBusinessHour()%>時
							</h5>
							<br>
							<h5 class="card-text"><%=gs.getTel()%></h5>
							<h5 class="card-text"><%=gs.getAddress()%></h5>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="card border-light">
							<div id="gmap" style="width: 100%; height: 50vh;"></div>
					</div>
				</div>
			</div>
		</div>
		<script>
			var btn=document.getElementById('btn');
			btn.addEventListener('click',function(event){
				event.preventDefault(); 
				document.form.submit();		
			})
		</script>
		<script type="text/javascript">
	  var map;
	  var marker;
	  var geocoder;
	  function initMap() {
	    geocoder = new google.maps.Geocoder();
	    geocoder.geocode({
	    'address':'<%=gs.getAddress()%>' //住所
				}, function(results, status) {
					if (status === google.maps.GeocoderStatus.OK) {
						map = new google.maps.Map(document
								.getElementById('gmap'), {
							center : results[0].geometry.location,
							zoom : 17
						//ズーム
						});
						marker = new google.maps.Marker({
							position : results[0].geometry.location,
							map : map
						});
						infoWindow = new google.maps.InfoWindow({
							content : '<h4>ツールチップのタイトル</h4>'
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
	
		<footer class="footer">
			<div class="p text-center">
				<p>© 2023 miyamarchere. All rights reserved.</p>
			</div>
		</footer>
	</div>
</body>
</html>