(function(){
	var currentUrl=window.location.href;
	console.log("当前："+currentUrl);
	if(currentUrl.match('/public/portal/project_portal')){
		document.getElementById('left-1').setAttribute('class','active');
	}else if(currentUrl.match('/public/portal/planner_portal')){
		document.getElementById('left-2').setAttribute('class','active');
	}else if(currentUrl.match('/public/portal/file_portal')){
		document.getElementById('left-3').setAttribute('class','active');
	}else if(currentUrl.match('/public/portal/team_portal')){		
		document.getElementById('left-4').setAttribute('class','active');
	}
})();