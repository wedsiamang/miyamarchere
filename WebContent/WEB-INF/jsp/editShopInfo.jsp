<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,model.Goods,model.Shop"%>
<!-- JavaScript Bundle with Popper -->
<%
Shop s = (Shop) request.getAttribute("selectShop");
Shop login = (Shop) session.getAttribute("login");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/halfHeader.css">
<meta name="viewport" content="width=device-width,initial-scale=1">
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
<title>店舗情報編集</title>
<script>
	$(function() {
		$('.js-upload-file2').on('change', function() { //ファイルが選択されたら
			let file = $(this).prop('files')[0]; //ファイルの情報を代入(file.name=ファイル名/file.size=ファイルサイズ/file.type=ファイルタイプ)
			$('.js-upload-filename2').text(file.name); //ファイル名を出力
			$('.js-upload-fileclear2').show(); //クリアボタンを表示
		});
		$('.js-upload-fileclear2').click(function() { //クリアボタンがクリックされたら
			$('.js-upload-file2').val(''); //inputをリセット
			$('.js-upload-filename2').text('ファイルが未選択です'); //ファイル名をリセット
			$(this).hide(); //クリアボタンを非表示
		});
	});
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
			<h3><%=s.getShopName()%>
				店舗情報編集
			</h3>
		</div>
	</div>
	<div class="main">
		<div class="mx-auto" style="width: 600px">
			<form action="/EditShopInfoServlet" method="post"
				enctype="multipart/form-data">
				<div class="mb-3">
					<label for="password" class="form-label">password<br> *パスワードは暗号化のため表示が異なっています<br>5文字から10文字で入力してください</label> <input
						type="password" class="form-control" id="password" name="password"
						value="<%=s.getPassword()%>">
				</div>
				<div class="mb-3">
					<label for="tel" class="form-label">電話番号</label> <input type="text"
						name="tel" class="form-control" value="<%=s.getTel()%>">
				</div>
				<div class="mb-3">
					<label for="address" class="form-label">住所</label> <input
						type="text" class="form-control" name="address"
						value="<%=s.getAddress()%>">
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">EMAIL</label> <input
						type="text" class="form-control" name="email"
						value="<%=s.getEmail()%>">
				</div>
				<div class="mb-3">
					<label for="businessHour" class="form-label">営業時間</label> <input
						type="text" class="form-control" name="businessHour"
						value="<%=s.getBusinessHour()%>">
				</div>
				<div class="mb-3">
					<label for="shopComment" class="form-label">コメント</label> <input
						type="text" class="form-control" name="shopComment"
						value="<%=s.getShopComment()%>">
				</div>
				<div class="mb-3">
					<label for="nearBy" class="form-label">近くの建物からお店まで何分？</label> <input
						type="text" class="form-control" name="nearBy"
						value="<%=s.getNearBy()%>">
				</div>
				<div class="mb-3">
					<label for="shopImg" class="form-label">店舗画像</label> <input
						type="file" class="form-control" name="img"
						class="js-upload-file2" accept="image/*" required>
					<jsp:include page="/WEB-INF/jsp/msg.jsp" />
					<br>
					<button type="submit">更新</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>