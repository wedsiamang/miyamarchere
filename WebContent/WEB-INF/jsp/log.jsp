<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,model.Goods,model.Shop"%>

<%
List<Goods> myLog = (List<Goods>) request.getAttribute("myLog");
Shop selectShop = (Shop) request.getAttribute("selectShop");
Shop login = (Shop) session.getAttribute("login");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/halfHeader.css">
<link rel="stylesheet" href="css/log.css">
<link rel="stylesheet" href="css/footer.css">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- bootstrap card -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<title>出品記録</title>
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
<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
<!-- playground-hide -->
<script>
	setTimeout(function() {
		window.location.href = '/LogoutServlet';
	}, 600 * 1000);
</script>
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
				<h2><%=selectShop.getShopName()%>
					出品記録一覧
				</h2>
			</div>
		</div>
		<div class="main">
			<%
			for (Goods ml : myLog) {
			%>
				<div class="card mb-3 justify-content-center align-items-center"
				style="max-width: 100%; top: 100px;">
				<div class="row g-0">
					<div class="card-body">
						<h6>
							登録日：<%=ml.getSubmitTime()%></h6>
						<h5><%=ml.getGoodsName()%></h5>
						<h6>
							・数量<%=ml.getQuantity()%>個・定価￥<%=ml.getListPrice()%>・ 出品価格￥<%=ml.getSellingPrice()%>・値引き率<%=ml.getDiscountRate()%>％
						</h6>
						<h6>
							コメント：<%=ml.getGoodsComment()%></h6>
					</div>
				</div>
			</div>
			<%
			}
			%>
		</div>
	</div>
	<footer class="footer">
		<div class="p text-center">
			<p>© 2023 miyamarchere. All rights reserved.</p>
		</div>
	</footer>
</body>
</html>