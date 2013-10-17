//YAHOO.namespace('dkahn');

var ADNJOY = YAHOO;

ADNJOY.doExecption = function(data){
	if(data.err.errno!=0){
		if(data.err.message != "")
			alert(data.err.message);
		if(data.err.execJs != "")
			eval(data.err.execJs);
		return true;
	}
	return false;
};

ADNJOY.wait = null;
ADNJOY.showWaitingPanel = function(txt,btCancel){
	if(ADNJOY.wait==null){
		ADNJOY.wait = new YAHOO.widget.Panel("adnjoy_wait",  
				{ width:"280px", 
				  fixedcenter:true, 
				  close:false, 
				  draggable:false, 
				  zindex:4,
				  modal:true,
				  visible:false
				} 
			);
	}
			
		if(typeof txt == 'undefined'){
			ADNJOY.wait.setHeader("Chargement des données en cours ...");
		}else{
			ADNJOY.wait.setHeader(txt);
		}
		
		if(typeof btCancel == 'undefined' || btCancel == true){
			ADNJOY.wait.setBody('<img src="images/yui/panel-wait/loading.gif" /><br/><input class="searchbutton" type="button" value="Annuler" onclick="ADNJOY.hideWaitingPanel();">');		
		}else{
			ADNJOY.wait.setBody('<img src="images/yui/panel-wait/loading.gif" />');
		}
		
		ADNJOY.wait.render(document.body);
		// Show the Panel
		ADNJOY.wait.show();	
};

ADNJOY.hideWaitingPanel = function(){
	if(ADNJOY.wait!=null) ADNJOY.wait.hide();	
};

ADNJOY.loginBox = null;
YAHOO.util.Event.onDOMReady(function(){ 
	ADNJOY.loginBox = new ADNJOY.user.LoginBox();
	YAHOO.util.Event.on("connexion", "click", ADNJOY.showLoginBox,this,true);
	YAHOO.util.Event.on("deconnexion", "click", ADNJOY._disconnect,this,true);
});

ADNJOY.showLoginBox = function(e){
	ADNJOY.loginBox.showPanel();
};

ADNJOY._disconnect = function(e){
	userDwrManager.disconnect(function(dataDwr){
		if(ADNJOY.doExecption(dataDwr))return;
//		document.getElementById("connected").style.display = "none";
//		document.getElementById("noConnexion").style.display = "";
		window.location.href='Home.htm';
	});
};

ADNJOY.showPlayer = function(){
	if ( hasProductInstall && !hasRequestedVersion ) {
		// DO NOT MODIFY THE FOLLOWING FOUR LINES
		// Location visited after installation is complete if installation is required
		var MMPlayerType = (isIE == true) ? "ActiveX" : "PlugIn";
		var MMredirectURL = window.location;
	    document.title = document.title.slice(0, 47) + " - Flash Player Installation";
	    var MMdoctitle = document.title;
	
		AC_FL_RunContent(
			"src", "playerProductInstall",
			"FlashVars", "MMredirectURL="+MMredirectURL+'&MMplayerType='+MMPlayerType+'&MMdoctitle='+MMdoctitle+"",
			"width", "640",
			"height", "480",
			"align", "middle",
			"id", "AdVideo",
			"quality", "high",
			"bgcolor", "#869ca7",
			"name", "AdVideo",
			"allowScriptAccess","sameDomain",
			"type", "application/x-shockwave-flash",
			"pluginspage", "http://www.adobe.com/go/getflashplayer"
		);
	} else if (hasRequestedVersion) {
		// if we've detected an acceptable version
		// embed the Flash Content SWF when all tests are passed
		AC_FL_RunContent(
				"src", "swf/AdVideo",
				"width", "640",
				"height", "480",
				"align", "middle",
				"id", "AdVideo",
				"quality", "high",
				"bgcolor", "#869ca7",
				"name", "AdVideo",
				"allowScriptAccess","sameDomain",
				"type", "application/x-shockwave-flash",
				"pluginspage", "http://www.adobe.com/go/getflashplayer"
		);
	  } else {  // flash is too old or we can't detect the plugin
	    var alternateContent = 'Alternate HTML content should be placed here. '
	  	+ 'This content requires the Adobe Flash Player. '
	   	+ '<a href=http://www.adobe.com/go/getflash/>Get Flash</a>';
	    document.write(alternateContent);  // insert non-flash content
	  }
};