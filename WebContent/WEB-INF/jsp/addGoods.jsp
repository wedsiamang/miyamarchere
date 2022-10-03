<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,model.Goods,model.Shop"%>
<%
Goods lg = (Goods) request.getAttribute("limitGoods");
Shop login = (Shop) session.getAttribute("login");
Shop ss = (Shop) request.getAttribute("selectShop");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/halfHeader.css">

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
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
<!--html preview jquery -->
<script src="https://code.jquery.com/jquery-2.0.3.min.js"></script>
<title>サンプルコード</title>
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
			<h2><%=ss.getShopName()%>
				商品登録
			</h2>
		</div>
	</div>
	<div class="footerFixed">
		<div class="main">
			<div class="mx-auto" style="width: 1000px">
				<br>
				<form action="/AddGoodsServlet" method="post"
					enctype="multipart/form-data">
					<div class="form-row">
						<div class="mb-3">
							<label for="shopName" class="form-label"></label> <input
								type="hidden" class="form-control" name="shopName">
						</div>
						<div class="mb-3">
							<label for="kinds">食品種別</label> <select name="kinds"
								class="form-control">
								<option value="">選択してください</option>
								<option value="パン">パン</option>
								<option value="野菜">野菜</option>
								<option value="肉">肉</option>
								<option value="魚">魚</option>
								<option value="弁当">弁当</option>
								<option value="飲料">飲料</option>
								<option value="菓子">菓子</option>
								<option value="食品">食品</option>
								<option value="日用品">日用品</option>
							</select>
						</div>
						<div class="mb-3">
							<label for="goodsName">商品名</label> <input type="text"
								class="form-control" name="goodsName" placeholder="商品名">
						</div>
						<div class="mb-3">
							<label for="charaQuantity" class="form-label">個数</label> <input
								type="text" class="form-control" name="charaQuantity"
								  placeholder="半角数字を入力" >
						</div>
						<div class="mb-3">
							<label for="unit">単位</label> <select name="unit"
								class="form-control">
								<option value="">選択してください</option>
								<option value="個">個</option>
								<option value="本">本</option>
								<option value="袋">袋</option>
								<option value="枚">枚</option>
								<option value="箱">箱</option>
								<option value="パック">パック</option>
								<option value="尾">尾</option>
								<option value="皿">皿</option>
								<option value="膳">膳</option>
							</select>
						</div>
					</div>
					<div class="form-row">
						<div class="mb-3">
							<label for="charaListPrice" class="form-label">定価</label> <input
								type="text" class="form-control" name="charaListPrice"
								 placeholder="半角数字を入力" >
						</div>
						<div class="mb-3">
							<label for="charaSellingPrice" class="form-label">出品価格</label> <input
								type="text" class="form-control" name="charaSellingPrice"
								placeholder="半角数字を入力" >
						</div>
					</div>
					<div class="form-row">
						<div class="mb-3">
							<label for="startTime" class="form-label">販売開始時間</label> <input
								type="datetime-local" class="form-control" name="startTime"
								placeholder="いつから">
						</div>
						<div class="mb-3">
							<label for="endTime" class="form-label">販売終了時間</label> <input
								type="datetime-local" class="form-control" name="endTime"
								placeholder="いつまで">
						</div>
					</div>
					<div class="mb-3">
						<label for="goodsComment" class="form-label">コメント</label> <input
							type="text" class="form-control" name="goodsComment"
							placeholder="コメント">
					</div>
					<div class="form-row">
						<div class="form-group">
							<input type="file" name="img" class="js-upload-file2"
								accept="image/*" required>
						</div>
					</div>
					<jsp:include page="/WEB-INF/jsp/msg.jsp" />
					<input type="submit" value="プレビュー">
				</form>
			</div>
		</div>
	</div>
</body>
</html>
