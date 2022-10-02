<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import = "java.util.List,model.Goods,model.Shop"%>
    
    <% 
    Goods g=(Goods)request.getAttribute("selectGoods");
    Goods p=(Goods)request.getAttribute("selectPreview");
    Shop s=(Shop)request.getAttribute("selectShop");
    Shop login=(Shop)session.getAttribute("login");
   
    %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/halfHeader.css">
   
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
 <!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">   
  
    <title>商品登録</title>
    
    <script>
    $(function() {
		  $('.js-upload-file').on('change', function () { //ファイルが選択されたら
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
	if(login!=null){ %>
	<jsp:include page="/WEB-INF/jsp/navigationLoggedIn.jsp" />
	 <%}else{ %>
	 <jsp:include page="/WEB-INF/jsp/navigationLoggedOut.jsp" />
	 <%} %>
	<div class="headbox">
		<div class="visual">
			<h1><%=g.getShop_name() %>　商品登録更新</h1>
		</div>
	</div>
	
  		<div class="main">
  			<div class="wrapper-1">
				<div class="mx-auto" style="width:1000px">
				<form action="/miyama1/UpdateGoodsServlet" method="post"enctype="multipart/form-data" >
					<br>
					<h3>出品登録日時：<%=g.getSubmit_time() %></h3><br>
					<div class="mb-3">
						<label for="goods_id" class="form-label"></label>
						<input type="hidden" name="goods_id" value="<%=g.getGoods_id() %>">
					</div> 
						<div class="form-row">
							<div class="mb-3">
								<label for="shop_name" class="form-label"></label>
								<input type="hidden" class="form-control"  name="shop_name">
							</div> 
							<div class="form-group">
								<label for="kinds">食品種別</label>
								<select  name="kinds" class="form-control">
									<option value="<%=g.getKinds() %>" ><%=g.getKinds() %></option>
									<option value="パン" >パン</option>
									<option value="野菜" >野菜</option>
									<option value="肉" >肉</option>
									<option value="魚" >魚</option>
									<option value="弁当" >弁当</option>
									<option value="飲料" >飲料</option>
									<option value="菓子" >菓子</option>
									<option value="食品" >食品</option>
									<option value="日用品" >日用品</option>
								</select>  
							</div>		
							<div class="form-group">
								<label for="goods_name" >商品名</label>
								<input type="text"class="form-control"  name="goods_name"value="<%=g.getGoods_name()%>">	
							</div>		
							<colgroup>
								<col style= "width:8%">
									<div class="mb-3">
										<label for="quantity" class="form-label">個数</label>
										<input type="number" class="form-control"  name="quantity"value="<%=g.getQuantity()%>">
									</div>		
									<div class="mb-3">
										<label for="unit">単位</label>
										<select  name="unit" class="form-control" required>
											<option value="" >選択してください</option>
											<option value="個" >個</option>
											<option value="本" >本</option>
											<option value="袋" >袋</option>
											<option value="枚" >枚</option>
											<option value="箱" >箱</option>
											<option value="パック" >パック</option>
											<option value="尾" >尾</option>
											<option value="皿" >皿</option>
											<option value="膳" >膳</option>
										</select>
									</div> 
							</colgroup>
						</div>
						<div class="form-row">
							<div class="mb-3">
									<label for="list_price" class="form-label">定価</label>
									<input type="number" class="form-control"  name="list_price"value="<%=g.getList_price()%>">
							</div>
							<div class="mb-3">
									<label for="selling_price" class="form-label">出品価格</label>
									<input type="number" class="form-control" name="selling_price"value="<%=g.getSelling_price()%>">
							</div>
						</div>
		 				<div class="form-row">
							<div class="mb-3">
									<label for="start_time" class="form-label">販売開始時間</label>
									<input type="datetime-local" class="form-control" name="start_time"value="<%=g.getStart_time()%>">
							</div>		
							<div class="mb-3">
									<label for="end_time" class="form-label">販売終了時間</label>
									<input type="datetime-local" class="form-control" name="end_time"value="<%=g.getEnd_time()%>">
							</div>
						</div>
						<div class="mb-3">
							<label for="goods_comment" class="form-label">コメント</label>
							<input type="text" class="form-control"  name="goods_comment"value="<%=g.getGoods_comment()%>">
						</div>
						<div class="form-row">		
							<div class="form-group">
									<input type="file" name="img" class="js-upload-file" accept="image/*"required>		
							</div>
						</div>
							<jsp:include page="/WEB-INF/jsp/msg.jsp" />
							<button type="submit">プレビュー</button>
							<a href="/miyama1/HomeServlet">決定</a>
				</div> 
				</form>
			</div>
		</div>
		<div class="box1">
			<div class="card mx-auto" style="width:1200px; top: 50px;">
				<h1 class="text-align-center"style="color:#d3d3d3;">プレビュー</h1>	
				<h1 class="text-center"><%=p.getShop_name() %></h1><br>
				<h3 class="card-text text-center">販売時間：<%=p.getStart_time() %>～<%=p.getEnd_time() %></h3> 
				<div class="box1">
					<div class="card  mx-auto"style="width:1000px;">
						<div class="card-body">
							<img src="data:image/*;base64,<%=p.getGoods_img() %>" width="500px"height="400px" class="float-start">
							<h1 class="card-title text-center"><%=p.getGoods_name() %></h1>
							<br><br>
							<h4 class="card-text text-center">残り<%=p.getQuantity() %>個</h4>
							<h4 class="card-text text-center">￥<%=p.getList_price() %>　→　￥<%=p.getSelling_price() %></h4>
							<h2 class="card-text text-center"><%=p.getDiscount_rate() %>%OFF</h2>
							<h4 class="card-text text-center">   出品理由:　<%=p.getGoods_comment() %></h4>
						</div>
					</div>
				</div>
			</div>
 		</div>	
</body>
</html>