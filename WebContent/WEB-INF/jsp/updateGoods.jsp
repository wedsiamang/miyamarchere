<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,model.Goods,model.Shop"%>
<%
Goods g = (Goods) request.getAttribute("selectGoods");
Goods p = (Goods) request.getAttribute("selectPreview");
Shop login = (Shop) session.getAttribute("login");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/halfHeader.css">
<link rel="stylesheet" href="css/updateGoods.css">

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
	<script>
	setTimeout(function() {
		window.location.href = '/LoginServlet';
	}, 300 * 1000);
</script>
<title>商品登録</title>
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
			<h1><%=g.getShopName()%>
				商品登録更新
			</h1>
		</div>
	</div>

	<div class="main">
		<div class="wrapper-1">
			<div class="mx-auto" style="width: 1000px">
				<form action="/UpdateGoodsServlet" method="post"
					enctype="multipart/form-data">
					<br>
					<h3>
						出品登録日時：<%=g.getSubmitTime()%></h3>
					<br>
					<div class="mb-3">
						<label for="goodsId" class="form-label"></label> <input
							type="hidden" name="goodsId" value="<%=g.getGoodsId()%>">
					</div>
					<div class="form-row">
						<div class="mb-3">
							<label for="shopName" class="form-label"></label> <input
								type="hidden" class="form-control" name="shopName">
						</div>
						<div class="form-group">
							<label for="kinds">食品種別</label> <select  name="kinds"
								class="form-control">
								<option value="<%=g.getKinds()%>"><%=g.getKinds()%></option>
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
								value="<%=g.getGoodsName()%>" >
						</div>
						<colgroup>
							<col style="width: 8%">
							<div class="mb-3">
								<label for="charaQuantity" class="form-label">個数</label> <input
									type="text" class="form-control" name="charaQuantity"
									value="<%=g.getQuantity()%>" >
							</div>
							<div class="mb-3">
								<label for="unit">単位</label> <select name="unit"
									class="form-control" >
									<option value="<%=g.getUnit()%>"><%=g.getUnit()%></option>
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
								value="<%=g.getListPrice()%>" >
						</div>
						<div class="mb-3">
							<label for="charaSellingPrice" class="form-label">出品価格</label> <input
								type="text" class="form-control" name="charaSellingPrice"
								value="<%=g.getSellingPrice()%>">
						</div>
					</div>
					<div class="form-row">
						<div class="mb-3">
							<label for="startTime" class="form-label">販売開始時間</label> <input
								type="datetime-local" class="form-control" name="startTime"
								value="<%=g.getStartTime()%>">
						</div>
						<div class="mb-3">
							<label for="endTime" class="form-label">販売終了時間</label> <input
								type="datetime-local" class="form-control" name="endTime"
								value="<%=g.getEndTime()%>">
						</div>
					</div>
					<div class="mb-3">
						<label for="goodsComment" class="form-label">コメント</label> <input
							type="text" class="form-control" name="goodsComment"
							value="<%=g.getGoodsComment()%>">
					</div>
					<div class="form-row">
						<div class="form-group">
							<input type="file" name="gImg"
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
	</div>
	<div class="box1">
		<div class="card mx-auto" style="width: 1200px; top: 50px;">
			<h1 class="text-align-center" style="color: #d3d3d3;">プレビュー</h1>
			<h1 class="text-center"><%=p.getShopName()%></h1>
			<br>
			<h3 class="card-text text-center">
				販売時間：<%=p.getStartTime()%>～<%=p.getEndTime()%></h3>
			<div class="box1">
				<div class="card  mx-auto" style="width: 1000px;">
					<div class="card-body">
						<img src="data:image/*;base64,<%=p.getGoodsImg()%>" width="500px"
							height="400px" class="float-start">
						<div class="btnkinds text-center">
							<button class="btnkinds-outline"><%=p.getKinds()%></button>
						</div>
						<br>
						<h1 class="card-title text-center"><%=p.getGoodsName()%></h1>
						<br> <br>
					<h4 class="card-text text-center"class="red">
							残り<span><%=p.getQuantity()%></span><%=p.getUnit() %>
						</h4>
						<h4 class="card-text text-center"class="red">
							￥<del><%=p.getListPrice()%></del>
							→ ￥<span><%=p.getSellingPrice()%></span></h4>
						<h2 class="card-text text-center"><%=p.getDiscountRate()%>%OFF
						</h2>
						<h4 class="card-text text-center">
							出品理由:
							<%=p.getGoodsComment()%></h4>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>