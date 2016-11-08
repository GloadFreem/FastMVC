<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
<title></title>
<style type="text/css">
html, body {
	margin: 0;
	border: 0;
	vertical-align: baseline;
	font: inherit;
	font-size: 100%;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	font-family: sans-serif;
	/* 1 */
	-webkit-text-size-adjust: 100%;
	-ms-text-size-adjust: 100%;
	/* 2 */
	-webkit-text-size-adjust: 100%;
}

body {
	-webkit-touch-callout: none;
	-webkit-font-smoothing: antialiased;
	font-smoothing: antialiased;
	-webkit-text-size-adjust: none;
	-moz-text-size-adjust: none;
	text-size-adjust: none;
	-webkit-tap-highlight-color: transparent;
	-webkit-tap-highlight-color: transparent;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	margin: 0;
	padding: 0.5rem;
	color: #747474;
	word-wrap: break-word;
	font-size: 14px;
	font-family: -apple-system;
	font-family: "-apple-system", "Helvetica Neue", "Roboto", "Segoe UI",
		sans-serif;
	line-height: 20px;
	text-rendering: optimizeLegibility;
	-webkit-backface-visibility: hidden;
	-webkit-user-drag: none;
	-ms-content-zooming: none;
}

.content {
	margin-top: 1rem;;
	width: 96%;
	margin-left: 2%;
	float: left;
	font-size: 1rem;
	line-height: 1.8rem;
	margin-right: 1.5rem;
	margin-bottom: 1rem;;
	color: #474747
}

.content a {
	text-decoration: none;
}

h1 {
	line-height: 2.5rem;;
	font-size: 2rem;;
	color: #323232;
	padding-bottom: 1rem;
}

u {
	text-decoration: none;
}

img {
	width: 100%;
	height: 60%;
	display: block;
}

span {
	font-size: 0.9rem;
	width: 100%;
	line-height: 1.4rem;
	text-align: center;
}

a, blockquote, body, code, dd, div, dl, dt, em, fieldset, form, h1, h2,
	h3, h4, h5, h6, iframe, img, input, label, li, object, ol, p, q, small,
	span, strong, table, tbody, td, th, tr, ul {
	margin: 0;
}

table {
	width: 100%;
	text-align: center;
	border-top: 1px solid #f1f1f1;
	border-left: 1px solid #f1f1f1;
	border-bottom: 1px solid #f1f1f1;
	border-right: 1px solid #f1f1f1;
	/*border-top-width: 1px;*/
	/*border-right-width: 1px;*/
	/*border-bottom-width: 1px;*/
	/*border-left-width: 1px;*/
	border-spacing: 0;
	/*overflow-x: auto;*/
	font-size: .12rem;
	line-height: 1rem;;
}

tr {
	display: table-row;
	vertical-align: inherit;
	border-color: inherit;
}

td, th {
	display: table-cell;
	vertical-align: inherit;
	padding: 0.2rem;
	text-align: center;
}

.bot {
	width: 100%;
	float: left;
}

.bot img {
	width: 100%;
	height: auto;
	margin: 0 auto;
}
</style>
</head>
<body>

	<div class="content">${Message}</div>
	<div class="bot"><img src="images/logo_bottom.png"></div>


</body>
</html>
