<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Barang</title>
</head>
<body>
	<c:forEach var="barang" items="${daftarBarang}">
		<p>${barang.nama}</p>
	</c:forEach>
	
	<form action="/barang" method="post">
		<input name="namaBarang" id="namaBarang" />
		<input type="submit" value="Tambah Barang" />
	</form>
	
	<p>Tugas: tampilkan hanya minuman dan/atau makanan saja</p>
	
</body>
</html>