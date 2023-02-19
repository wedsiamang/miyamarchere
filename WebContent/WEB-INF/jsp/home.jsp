<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,model.Shop,model.Goods"%>

<%
Shop s = (Shop) request.getAttribute("selectShop");
List<Goods> myGoodsList = (List<Goods>) request.getAttribute("myGoodsList");
List<Goods> myEndGoodsList = (List<Goods>) request.getAttribute("myEndGoodsList");
Shop login = (Shop) session.getAttribute("login");
%>
<!DOCTYPE HTML>
<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="css/halfHeader.css">
<link rel="stylesheet" href="css/card.css">
<link rel="stylesheet" href="css/home.css">
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
	<script>
	setTimeout(function() {
		window.location.href = '/LoginServlet';
	}, 300 * 1000);
</script>
</head>
<body>
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
			<h1>店舗HOME</h1>
		</div>
	</div>
	<div class="main">
		<div class="box1">
			<div class="card2  mx-auto" style="width: 1200px;">
				<div class="card-body2">
					<br> <br>
					<h1 class="card-title text-center"><%=s.getShopName()%></h1>
					<br>
					<h3 class="text-center"><%=s.getNearBy()%></h3>
					<img src="data:image/*;base64,<%=s.getShopImg()%>" width="1200px"
						height="600px"> <br> <br>
					<h3 class="card-text text-center"><%=s.getShopComment()%></h3>
				</div>
			</div>
		</div>
		<br> <br>
		<h4 class="text-center">出品商品</h4>
		<div class="container">
			<div class="row">
				<%
				for (Goods mg : myGoodsList) {
				%>
				<div class="col-md-3">
					<div class="card border-light" style="width: 12rem; top: 50px;">
						<form name="form" action="/PreviewGoodsServlet"
							method="get">
							<input type="hidden" name="goodsId" value="<%=mg.getGoodsId()%>">
							<button id="btn" class="btn-outline">
								<img class="card-img-top"
									src="data:image/*;base64,<%=mg.getGoodsImg()%>" alt="">
								<div
									class="card-img-overlay h-100 d-flex flex-column text-white ">
									<%=mg.getDiscountRate()%>% OFF
								</div>
							</button>
						</form>
						<div class="card-body" style="height: 30px;">
							<h5 class="card-title text-center"><%=mg.getGoodsName()%></h5>
						</div>
					</div>
					<form name="update" action="/HomeServlet" method="post">
						<input type="hidden" name="goodsId" value="<%=mg.getGoodsId()%>">
						<br> <br>
						<button id="btn" class="btn-outline">編集する</button>
					</form>
					<form name="delete" action="/DeleteGoodsServlet"
						method="post">
						<input type="hidden" name="goodsId" value="<%=mg.getGoodsId()%>">
						<br>
						<button id="btn" class="btn-outline">削除する</button>
					</form>
				</div>
				<%
				}
				%>
				<%
				for (Goods eg : myEndGoodsList) {
				%>
				<div class="col-md-3">
					<div class="card border-light" style="width: 12rem; top: 50px;">
						<div class="dark">
							<img class="card-img-top"
								src="data:image/*;base64,<%=eg.getGoodsImg()%>" alt="">
							<div
								class="card-img-overlay h-100 d-flex flex-column text-white ">
								<h3>終了</h3>
							</div>
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
		<div class="box3">
			<div class="card mx-auto" style="max-width: 900px;">
				<div class="row no-gutters">
					<div class="col-md-4">
						<div id="gmap" style="width: 400px; height: 400px;"></div>
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<h4 class="card-title text-center"><%=s.getShopName()%></h4><br>
							<h5 class="text-center"><%=s.getNearBy()%></h5>
							<h5 class="card-text text-center">
								営業時間:<%=s.getBusinessHour()%>時
							</h5>
							<br>
							<h5 class="card-text text-center">☎<%=s.getTel()%></h5>
							<h5 class="card-text text-center"><%=s.getAddress()%></h5>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
			var btn=document.getElementById('btn');
			btn.addEventListener('click',function(){
				alert(document.form.submit());
			})
		</script>
	<script type="text/javascript">
  var map;
  var marker;
  var geocoder;
  function initMap() {
    geocoder = new google.maps.Geocoder();
    geocoder.geocode({
    'address':'<%=s.getAddress()%>' //住所
			}, function(results, status) {
				if (status === google.maps.GeocoderStatus.OK) {
					map = new google.maps.Map(document.getElementById('gmap'),
							{
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

	
</body>
</html>