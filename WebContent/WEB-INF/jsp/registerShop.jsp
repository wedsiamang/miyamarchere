<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.Shop,java.util.List"%>
<%
Shop login = (Shop) session.getAttribute("login");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/halfHeader.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/halfHeader.css">
<link rel="stylesheet" href="css/card.css">
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
<title>店舗新規登録</title>
<script>
	setTimeout(function() {
		window.location.href = '/LoginServlet';
	}, 300 * 1000);
</script>
</head>
<body>
	<div class="headbox">
		<div class="visual">
			<h1>ミヤマルシェア</h1>
		</div>
	</div>
	<div class="mx-auto" style="width: 600px">
		<div class="mb-3" style="text-align: center">
			<h1>店舗新規登録</h1>
		</div>
		<br>
		<form action="/RegisterShopServlet" method="post"
			enctype="multipart/form-data">
			<div class="mb-3">
				<label for="shopName" class="form-label">店舗名</label> <input
					type="text" class="form-control" id="shopName" name="shopName"
					>
			</div>
			<div class="mb-3">
				<label for="password" class="form-label">password</label> <input
					type="password" id="password" class="form-control" name="password"
					 placeholder="5文字以上1０文字以下" >
			</div>
			<div class="mb-3">
				<label for="tel" class="form-label">電話番号</label> <input type="text"
					class="form-control" name="tel" >
			</div>
			<div class="mb-3">
				<label for="address" class="form-label">住所</label> <input
					type="text" class="form-control" name="address" >
			</div>
			<div class="mb-3">
				<label for="email" class="form-label">EMAIL</label> <input
					type="email" class="form-control" name="email" >
			</div>
			<div class="mb-3">
				<label for="businessHour" class="form-label">営業時間</label> <input
					type="text" class="form-control" name="businessHour" >
			</div>
			<div class="mb-3">
				<label for="file" class="form-label">店舗紹介画像</label> <input
					type="file" name="sImg"  accept="image/*"
					required>
			</div>
			<div class="mb-3">
				<label for="shopComment" class="form-label">コメント</label> <input
					type="text" class="form-control" name="shopComment" >
			</div>
			<div class="mb-3">
				<label for="nearBy" class="form-label">近くの建物からお店まで何分？</label> <input
					type="text" class="form-control" name="nearBy"  placeholder="例)市役所から徒歩５分">
			</div>
			<button type="submit" class="btn new-btn new-btn:hover">新規登録
			</button>
		</form>
		<div style="text-align: center;">
			<a href="/LoginServlet">login</a> <a
				href="/IndexServlet">TOPへ</a>
		</div>
		<jsp:include page="/WEB-INF/jsp/msg.jsp" />
	</div>
</body>
</html>