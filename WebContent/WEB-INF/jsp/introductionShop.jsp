<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,model.Shop,model.Goods,model.GoodsShop"%>

<%
Shop i = (Shop) request.getAttribute("introShop");
List<Goods> myGoodsList = (List<Goods>) request.getAttribute("myGoodsList");
List<Goods> myEndGoodsList = (List<Goods>) request.getAttribute("myEndGoodsList");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/halfHeader.css">
<link rel="stylesheet" href="css/card.css">
<link rel="stylesheet" href="css/footer.css">

<link rel="stylesheet" href="css/introductionShop.css">
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
		<jsp:include page="/WEB-INF/jsp/navigationLoggedOut.jsp" />
		<div class="headbox">
			<div class="visual">
				<h1>店舗紹介</h1>
			</div>
		</div>
		<main>
			<div class="shopbox">
				<div class="box1">
					<div class="card2 mx-auto" style="max-width: 100%;">
						<div class="card-body2">
							<br> <br>
							<h1 class="card-title text-center"><%=i.getShopName()%></h1>
							<br>
							<h3 class="text-center"><%=i.getNearBy()%></h3>
							<img src="data:image/*;base64,<%=i.getShopImg()%>"
								style="width: 100%; height: auto;"> <br> <br>
							<h3 class="card-text text-center"><%=i.getShopComment()%></h3>
						</div>
					</div>
				</div>
			</div>
			<br> <br>
			<div class="cardbox">
				<h4 class="text-center">出品商品</h4>
				<div class="container">
					<div class="row d-flex justify-content-center">
						<%
						for (Goods mg : myGoodsList) {
						%>
						<div class="col-12 col-md-3 justify-content-center">
							<div class="card border-light" style="width: 12rem; top: 50px;">
								<form name="form" action="/PreviewGoodsServlet" method="get">
									<input type="hidden" name="goodsId"
										value="<%=mg.getGoodsId()%>">
									<button id="btn" class="btn-outline">
										<img class="card-img-top"
											src="data:image/*;base64,<%=mg.getGoodsImg()%>" alt="">
										<div
											class="card-img-overlay h-100 d-flex flex-column text-white ">
											<%=mg.getDiscountRate()%>% OFF
										</div>
									</button>
								</form>
								<div class="card-body">
									<h5 class="card-title text-center"><%=mg.getGoodsName()%></h5>
								</div>
							</div>
						</div>
						<%
						}
						%>
						<%
						for (Goods eg : myEndGoodsList) {
						%>
						<div class="col-12 col-md-3 justify-content-center">
							<div class="card border-light" style="width: 12rem; top: 50px;">
								<img class="card-img-top"
									src="data:image/*;base64,<%=eg.getGoodsImg()%>" alt="">
								<div
									class="card-img-overlay h-100 d-flex flex-column text-white ">
									<h3>終了</h3>
								</div>
								<div class="card-body" style="height: 30px;">
									<h5 class="card-title text-center text-muted"><%=eg.getGoodsName()%></h5>
								</div>
							</div>
						</div>
						<%
						}
						%>
					</div>
				</div>
			</div>
			<div class="box3 ">
				<div class="card mx-auto"
					style="max-width: 850px; height: auto; margin-top: 30px;">
					<div class="row no-gutters">
						<div class="col-md-6">
							<div class="card-body" style="margin-bottom: 50px;">
								<h4 class="card-title text-center"><%=i.getShopName()%></h4>
								<br>
								<h5 class="text-center"><%=i.getNearBy()%></h5>
								<h5 class="card-text text-center">
									営業時間:<%=i.getBusinessHour()%>時
								</h5>
								<br>
								<h5 class="card-text text-center">
									☎<%=i.getTel()%></h5>
								<h5 class="card-text text-center"><%=i.getAddress()%></h5>
							</div>
						</div>
						<div class="col-md-6">
							<div class="card border-light">
									<div id="gmap" style="width: 100%; height: 50vh;"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</main>
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
	    'address':'<%=i.getAddress()%>' //住所
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