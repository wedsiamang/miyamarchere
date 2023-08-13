
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.List,model.Goods,model.Shop"%>

<%
List<Goods> goodsList = (List<Goods>) request.getAttribute("goodsList");
List<Goods> sortShop = (List<Goods>) request.getAttribute("sortShop");
List<Goods> sortHalf = (List<Goods>) request.getAttribute("sortHalf");
String neo = (String) request.getAttribute("neo");
String shop = (String) request.getAttribute("shop");
String half = (String) request.getAttribute("half");
List<Goods> endGoodsList = (List<Goods>) request.getAttribute("endGoodsList");
Shop login = (Shop) session.getAttribute("login");
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/card.css">
<link rel="stylesheet" href="css/header.css">
<link rel="stylesheet" href="css/PreviewGoodsAll.css">
<link rel="stylesheet" href="css/footer.css">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
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
		window.location.href = '/IndexServlet';
	}, 600 * 1000);
</script>
<title>商品一覧</title>
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
		<!--  <div class="headbox">-->
		<div class="visual">
			<h1>商品一覧</h1>
		</div>

		<div class="main">
			<div class="container">
				<div class="row ">
					<%
					if (neo != null) {
					%>
					<%
					for (Goods gs : goodsList) {
					%>
					<div class="col-md-2 text-center">

						<div
							class="card border-light shadow-sm justify-content-center align-items-center"
							style="width: 100%; top: 100px;">
							<form name="form" action="/PreviewGoodsServlet" method="get">
								<input type="hidden" name="goodsId" value="<%=gs.getGoodsId()%>">
								<button id="btn" class="btn-outline">
									<div class="text-center">
										<img class="card-img-top card-img-fluid"
											src="data:image/*;base64,<%=gs.getGoodsImg()%>" style="">
										<div
											class="card-img-overlay h-100 d-flex flex-column text-white">
											<h4><%=gs.getDiscountRate()%>% OFF
											</h4>
										</div>
									</div>
								</button>
							</form>
							<div class="card-body container-fluid">
								<h5 class="card-title text-center"><%=gs.getGoodsName()%></h5>
								<p class="card-text text-center small red">
									残り <span><%=gs.getQuantity()%></span><%=gs.getUnit()%></p>
								<p class="card-text text-center small red">
									<del><%=gs.getListPrice()%>円
									</del>
									⇒ <span><%=gs.getSellingPrice()%>円</span>
								</p>
								<p class="card-text text-center small"><%=gs.getShopName()%></p>
							</div>
						</div>
					</div>
					<%
					}
					%>
					<%
					for (Goods eg : endGoodsList) {
					%>
					<div class="col-md-2">
						<div
							class="card border-light shadow-sm justify-content-center align-items-center"
							style="width: 100%; top: 100px;">
							<div class="dark">
								<img class="card-img-top"
									src="data:image/*;base64,<%=eg.getGoodsImg()%>">
								<div
									class="card-img-overlay h-100 d-flex flex-column text-secondary">
									<h3>終了</h3>
								</div>
							</div>
							<div class="card-body container-fluid">
								<h5 class="card-title text-center text-muted"><%=eg.getGoodsName()%></h5>
								<p class="card-text text-center text-muted small">
									残り<span><%=eg.getQuantity()%></span><%=eg.getUnit()%></p>
								<p class="card-text text-center text-muted text-danger">
									<del><%=eg.getListPrice()%>円
									</del>
									⇒
									<%=eg.getSellingPrice()%>円
								</p>
								<p class="card-text text-center text-muted small"><%=eg.getShopName()%></p>
							</div>

						</div>
					</div>
				</div>
				<%
				}
				%>
				<%
				} else if (shop != null) {
				%>
				<%
				for (Goods ss : sortShop) {
				%>
				<div class="col-md-2">
					<div
						class="card border-light shadow-sm justify-content-center align-items-center"
						style="width: 100%; top: 100px;">

						<form name="form" action="/PreviewGoodsServlet" method="get">
							<input type="hidden" name="goodsId" value="<%=ss.getGoodsId()%>">
							<button id="btn" class="btn-outline">
								<div class="text-center">
									<img class="card-img-top card-img-fluid"
										src="data:image/*;base64,<%=ss.getGoodsImg()%>" style="">
									<div
										class="card-img-overlay h-100 d-flex flex-column text-white ">
										<h4><%=ss.getDiscountRate()%>% OFF
										</h4>
									</div>
								</div>
							</button>
						</form>
						<div class="card-body container-fluid">
							<h5 class="card-title text-center "><%=ss.getGoodsName()%></h5>
							<p class="card-text text-center" class="small" class="red">
								残り<span><%=ss.getQuantity()%></span><%=ss.getUnit()%></p>
							<p class="card-text text-center" class="small" class="red">
								<del><%=ss.getListPrice()%>円
								</del>
								⇒<span><%=ss.getSellingPrice()%>円</span>
							</p>
							<p class="card-text text-center" class="small">
								<%=ss.getShopName()%></p>
						</div>
					</div>
				</div>
				<%
				}
				%>
				<%
				} else if (half != null) {
				%>
				<%
				for (Goods sh : sortHalf) {
				%>
				<div class="col-md-2">
					<div
						class="card border-light shadow-sm justify-content-center align-items-center"
						style="width: 100%; top: 100px;">
						<form name="form" action="miyama1-heroku8/PreviewGoodsServlet"
							method="get">
							<input type="hidden" name="goodsId" value="<%=sh.getGoodsId()%>">
							<button id="btn" class="btn-outline">
								<div class="text-center">
									<img class="card-img-top card-img-fluid"
										src="data:image/*;base64,<%=sh.getGoodsImg()%>" style="">
									<div
										class="card-img-overlay h-100 d-flex flex-column text-white ">
										<h4><%=sh.getDiscountRate()%>% OFF
										</h4>
									</div>
								</div>
							</button>
						</form>
						<div class="card-body container-fluid">
							<h5 class="card-title text-center "><%=sh.getGoodsName()%></h5>
							<p class="card-text text-center" class="small" class="red">
								残り<span><%=sh.getQuantity()%></span><%=sh.getUnit()%></p>
							<p class="card-text text-center" class="small" class="red">
								<del><%=sh.getListPrice()%>円
								</del>
								⇒<span><%=sh.getSellingPrice()%>円</span>
							</p>
							<p class="card-text text-center" class="small">
								<%=sh.getShopName()%></p>
						</div>
					</div>
				</div>
				<%
				}
				%>
				<%
				}
				%>
			</div>
		</div>
		<script src="https://code.jquery.com/jquery-1.12.4.js"
			type="text/javascript"></script>
		<script type="text/javascript">
   $(".red").children().addBack().contents().each(function(){
      if (this.nodeType == 3) {
         var $this = $(this);
         $this.replaceWith($this.text().replace(/(\S)/g, "<span>$&</span>"));
      }
   });
</script>
		<script>
			var btn=document.getElementById('btn');
			btn.addEventListener('click',function(event){
				event.preventDefault(); 
				document.form.submit();	
			})
		</script>
		<script>
	    fetch('/PrevewGoodsAllServlet');
	    .then(response => {
	    	return response.json();
	    })
	    .then(data => {
	   const timer= 600000
	   window.addEventListener('load',function(){
		   setInterval('location.reload()',timer);
	   });
	    	console.log(data);	
	    })
	    .catch(error => {
	    	console.log("失敗しました");
	    });
	</script>
		<footer class="footer">
			<div class="p text-center">
				<p>© 2023 miyamarchere. All rights reserved.</p>
			</div>
		</footer>
	</div>
</body>
</html>