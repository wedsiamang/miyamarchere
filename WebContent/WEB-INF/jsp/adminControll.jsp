<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,model.Shop,model.Admin"%>
<!DOCTYPE html>
<html>
<head>
<%
List<Shop> toBeApproved = (List<Shop>) request.getAttribute("toBeApproved");
%>
<meta charset="UTF-8">
<title>AdminControll</title>
</head>
<body>
	<div class="headbox">
		<div class="visual">
			<h2>新規仮店舗一覧</h2>
		</div>
	</div>
	<div class="main">
		<div class="container">
			<div class="row">
				<%
				for (Shop tba : toBeApproved) {
				%>
				<div class="col-lg-6">
					<div class="card mb-3 mx-auto " style="max-width: 1200px;">
						<div class="row no-gutters ">
							<div class="col-md-6">
								<div class="card border-light" style="max-width: 600px;">
									<img src="data:image/*;base64,<%=tba.getShopImg()%>"
										width="250px" height="250px">
								</div>
							</div>
							<div class="col-md-6">
								<div class="card border-light" style="max-width: 600px;">
									<h3 class="card-title text-center">
										<%=tba.getShopName()%></h3>
									<br> <br>
									<p class="card-text text-center">
										営業時間:<%=tba.getBusinessHour()%>時
									</p>
									<p class="card-text text-center">
										☎<%=tba.getTel()%></p>
									<p class="card-text text-center"><%=tba.getAddress()%></p>
								</div>
							</div>
						</div>
					</div>
				</div>
				<form name="delete" action="/AdminControllServlet" method="post">
					<input type="hidden" name="shopId" value="<%=tba.getShopId()%>">
					<br>
					<button id="btn" class="btn-outline">削除する</button>
				</form>
				<%
				}
				%>
			</div>
		</div>
		<a class="btn btn--yellow btn--cubic" href="/IndexServlet">TOPへ</a>
	</div>
	<script>
		var btn = document.getElementById('btn');
		btn.addEventListener('click', function() {
			alert(document.form.submit());
		})
	</script>
</body>
</html>