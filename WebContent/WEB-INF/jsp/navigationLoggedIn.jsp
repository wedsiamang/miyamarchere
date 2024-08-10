<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/navibar.css">
<script>
	setTimeout(function() {
		window.location.href = '/LogoutServlet';
	}, 600 * 1000);
</script>
</head>
<body>
	<nav class="navbar navbar-expand-lg sticky-top navbar-default ">
		<div class="container-fluid ">
			<a class="navbar-brand" href="/PreviewGoodsAllServlet?neo=neo">ミヤマルシェア 商品一覧へ</a>
			<button class="navbar-toggler " type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
				aria-controls="navbarNavDropdown" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse " id="navbarNavDropdown">
				<ul class="navbar-nav">
					<li class="nav-item dropdown text-center"><a
						class="nav-link dropdown-toggle small"
						href="/PreviewGoodsAllServlet" id="navbarDropdownMenuLink"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">
							商品一覧 </a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<li><a class="dropdown-item text-muted">並べ替え</a></li>
							<li><a class="dropdown-item"
								href="/PreviewGoodsAllServlet?neo=neo">新着順</a></li>
							<li><a class="dropdown-item"
								href="/PreviewGoodsAllServlet?shop=shop">店舗別</a></li>
							<li><a class="dropdown-item"
								href="/PreviewGoodsAllServlet?half=half">半額以下</a></li>
						</ul></li>
					<li class="nav-item text-center"><a class="nav-link small"
						href="/PreviewShopAllServlet?loggedIn">店舗一覧</a></li>
					<li class="nav-item text-center"><a class="nav-link small"
						href="/LoginServlet">出品者ログイン</a></li>
					<li class="nav-item dropdown text-center"><a
						class="nav-link dropdown-toggle small" href="#"
						id="navbarDropdownMenuLink" role="button"
						data-bs-toggle="dropdown" aria-expanded="false"> 出品者メニュー</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<li><a class="dropdown-item" href="/HomeServlet">出品者HOME</a></li>
							<li><a class="dropdown-item" href="/AddGoodsServlet">商品追加</a></li>
							<li><a class="dropdown-item"
								href="/EditShopInfoServlet">店舗情報編集</a></li>
							<li><a class="dropdown-item" href="/LogServlet">出品記録</a></li>
							<li><a class="dropdown-item" href="/LogoutServlet">ログアウト</a></li>
						</ul></li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>