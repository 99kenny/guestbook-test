window.addEventListener('load', function(){
	
	//빈 문자열 막아놓기 
	var textarea = document.getElementById("content");
	textarea.addEventListener("input", (e) => {
		var content = e.currentTarget.value.trim();
		var button = document.getElementById("button");
		if(content.length === 0){
			button.disabled = true;
		}
		else{
			button.disabled = false;
		}
	});
});