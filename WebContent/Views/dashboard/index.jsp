<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cine Xd</title>
</head>
<body>

	<jsp:include page="/Views/content/content.jsp"></jsp:include>

	<h3>${permissao}</h3>


	<div class="clock" style="text-align: center;">
		<div id="clock"></div>
	</div>

	<style>
		#clock, #day, #date, #diem {
			color: #a5adb0;
			font-family: 'Droid Sans', sans-serif;
			position: absolute;
			bottom: 0;
			left: 0;
			right: 0;
		}
		
		#clock {
			top: 185px;
			font-size: 65px;
		}
	</style>

	<script type="text/javascript">
		var clock = document.getElementById('clock')
		// var day = document.getElementById('day')
		var date = document.getElementById('date')

		function addZero(i) {
			if (i < 10) {
				i = "0" + i;
			}
			return i;
		}

		function getTime() {
			var today = new Date()
			var hour = addZero(today.getHours())
			var min = addZero(today.getMinutes())
			var sec = addZero(today.getSeconds())
			// var time = hour + ":" + min + ":" + sec
			var time
			setTimeout('getTime()', 500)

			if (hour > 12) {
				hour -= 12
				time = addZero(hour) + ":" + min + ":" + sec + " PM"
			} else {
				time = hour + ":" + min + ":" + sec + " AM"
			}

			clock.textContent = time
		}

		clock.addEventListener('onload', getTime(), false)
	</script>

	<jsp:include page="/Views/footer/footer.jsp"></jsp:include>

</body>
</html>