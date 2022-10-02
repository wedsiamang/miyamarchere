<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import = "java.util.List,model.Goods,model.Shop"%>
  <!-- JavaScript Bundle with Popper -->
    <%
    Shop s=(Shop)request.getAttribute("select_shop");
    Shop login=(Shop)session.getAttribute("login");
    
    %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
	<link rel="stylesheet" href="css/halfHeader.css">
	  <meta name="viewport" content="width=device-width,initial-scale=1">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	 <!-- CSS only -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">   
	
	<title>店舗情報編集</title>
	
	<script>
	    $(function() {
				  $('.js-upload-file2').on('change', function () { //ファイルが選択されたら
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
	if(login!=null){ %>
	<jsp:include page="/WEB-INF/jsp/navigationLoggedIn.jsp" />
	 <%}else{ %>
	 <jsp:include page="/WEB-INF/jsp/navigationLoggedOut.jsp" />
	 <%} %>
	<div class="headbox">
		<div class="visual">
			<h3><%=s.getShop_name() %>　店舗情報編集</h3>
		</div>
	</div>
	<div class="main">
		<div class="mx-auto" style="width:300px">
			<form action="/miyama1/EditShopInfoServlet" method="post"enctype="multipart/form-data">
				<div class="mb-3">
					<label for="password" class="form-label">password</label>
					<input type="password" id="password"  name="password"value="<%=s.getPassword() %>"required>
				</div>
				<div class="mb-3">
					<label for="tel" class="form-label">電話番号</label>
					<input type="text"  name="tel"value="<%=s.getTel() %>"required>	
				</div>
				<div class="mb-3">
					<label for="address" class="form-label">住所</label>
					<input type="text"  name="address"value="<%=s.getAddress()%>"required>	
				</div>
				<div class="mb-3">
					<label for="email" class="form-label">EMAIL</label>
					<input type="text"  name="email"value="<%=s.getEmail()%>"required>	
				</div>
				<div class="mb-3">
					<label for="business_hour" class="form-label">営業時間</label>
					<input type="text"  name="business_hour"value="<%=s.getBusiness_hour() %>"required>	
				</div>
		 		<div class="mb-3">
					<label for="shop_comment" class="form-label">コメント</label>
					<input type="text"  name="shop_comment"value="<%=s.getShop_comment()%>">	
				</div>
				<div class="mb-3">
					<label for="nearBy" class="form-label">近くの建物からお店まで何分？</label>
					<input type="text"  name="nearBy"value="<%=s.getNearBy()%>"required>	
				</div>
				<div class="mb-3">
					<label for="shop_img" class="form-label">店舗画像</label>
					<input type="file" name="img" class="js-upload-file2" accept="image/*" required >
					<jsp:include page="/WEB-INF/jsp/msg.jsp" />
					<button type="submit" >更新 </button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>