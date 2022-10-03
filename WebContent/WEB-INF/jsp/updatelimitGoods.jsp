<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,model.Goods,model.Shop"%>

<%
Goods lg = (Goods) request.getAttribute("limitGoods");
Goods lp = (Goods) request.getAttribute("limitGoodsPreview");
Shop login = (Shop) session.getAttribute("login");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/halfHeader.css">
<link rel="stylesheet" href="css/updatelimit.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous">

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
<title>商品登録</title>
<script>
	$(function() {
		$('.js-upload-file').on('change', function() { //ファイルが選択されたら
			let file = $(this).prop('files')[0]; //ファイルの情報を代入(file.name=ファイル名/file.size=ファイルサイズ/file.type=ファイルタイプ)
			$('.js-upload-filename').text(file.name); //ファイル名を出力
			$('.js-upload-fileclear').show(); //クリアボタンを表示
		});
		$('.js-upload-fileclear').click(function() { //クリアボタンがクリックされたら
			$('.js-upload-file').val(''); //inputをリセット
			$('.js-upload-filename').text('ファイルが未選択です'); //ファイル名をリセット
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
			<h2><%=lp.getShopName()%>
				商品登録
			</h2>
		</div>
	</div>
	<div class="main">
		<div class="mx-auto" style="width: 1000px">
			<form action="/UpdateLimitGoodsServlet" method="post"
				enctype="multipart/form-data">
				<br>
				<h3>
					出品登録日時：<%=lp.getSubmitTime()%></h3>
				<br>
				<div class="mb-3">
					<label for="goodsId" class="form-label"></label> <input
						type="hidden" name="goodsId" value="<%=lp.getGoodsId()%>">
				</div>
				<div class="form-row">
					<div class="mb-3">
						<label for="shopName" class="form-label"></label> <input
							type="hidden" class="form-control" name="shopName">
					</div>
					<div class="form-group">
						<label for="kinds">食品種別</label> <select name="kinds"
							class="form-control">
							<option value="<%=lp.getKinds()%>"><%=lp.getKinds()%></option>
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
					<div class="form-group">
						<label for="goodsName">商品名</label> <input type="text"
							class="form-control" name="goodsName"
							value="<%=lp.getGoodsName()%>">
					</div>
					<colgroup>
						<col style="width: 8%">
						<div class="mb-3">
							<label for="charaQuantity" class="form-label">個数</label> <input
								type="text" class="form-control" name="charaQuantity"
								value="<%=lp.getQuantity()%>">
						</div>
						<div class="mb-3">
							<label for="unit">単位</label> <select name="unit"
								class="form-control" >
								<option value="<%=lp.getUnit()%>"><%=lp.getUnit()%></option>
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
					</colgroup>
				</div>
				<div class="form-row">
					<div class="mb-3">
						<label for="charaListPrice" class="form-label">定価</label> <input
							type="text" class="form-control" name="charaListPrice"
							value="<%=lp.getListPrice()%>">
					</div>
					<div class="mb-3">
						<label for="charaSellingPrice" class="form-label">出品価格</label> <input
							type="text" class="form-control" name="charaSellingPrice"
							value="<%=lp.getSellingPrice()%>">
					</div>
				</div>
				<div class="form-row">
					<div class="mb-3">
						<label for="startTime" class="form-label">販売開始時間</label> <input
							type="datetime-local" class="form-control" name="startTime"
							value="<%=lp.getStartTime()%>">
					</div>
					<div class="mb-3">
						<label for="endTime" class="form-label">販売終了時間</label> <input
							type="datetime-local" 
							class="form-control" name="endTime" value="<%=lp.getEndTime()%>">
					</div>
				</div>
				<div class="mb-3">
					<label for="goodsComment" class="form-label">コメント</label> <input
						type="text" class="form-control" name="goodsComment"
						value="<%=lp.getGoodsComment()%>">
				</div>
				<div class="form-row">
					<div class="form-group">
						<input type="file" name="img" class="js-upload-file2"
							accept="image/*" required>
					</div>
				</div>
				<jsp:include page="/WEB-INF/jsp/msg.jsp" />
				<button type="submit">プレビュー</button>
			</form>
			<br>
			<button>
				<a href="/HomeServlet">決定する</a>
			</button>
		</div>
	</div>
	<div class="box1">
		<div class="card mx-auto" style="width: 1200px; top: 50px">
			<h1 class="text-align-center " style="color: #d3d3d3">プレビュー</h1>
			<h1 class="text-center"><%=lg.getShopName()%></h1>
			<br>
			<h3 class="card-text text-center">
				販売時間：<%=lg.getStartTime()%>～<%=lg.getEndTime()%></h3>
			<div class="box1">
				<div class="card  mx-auto" style="width: 1000px;">
					<div class="card-body">
						<img src="data:image/*;base64,<%=lg.getGoodsImg()%>" width="500px"
							height="400px" class="float-start">
						<div class="btnkinds text-center">
							<button class="btnkinds-outline"><%=lg.getKinds()%></button>
						</div>
						<br>
						<h1 class="card-title text-center"><%=lg.getGoodsName()%></h1>
						<br> <br>
						<h4 class="card-text text-center"class="red">
							残り<span><%=lg.getQuantity()%></span><%=lg.getUnit() %>
						</h4>
						<h4 class="card-text text-center"class="red">
							￥<del><%=lg.getListPrice()%></del>
							→ ￥<span><%=lg.getSellingPrice()%></span></h4>
						<h2 class="card-text text-center"><%=lg.getDiscountRate()%>%OFF
						</h2>
						<h4 class="card-text text-center">
							出品理由:
							<%=lg.getGoodsComment()%></h4>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>