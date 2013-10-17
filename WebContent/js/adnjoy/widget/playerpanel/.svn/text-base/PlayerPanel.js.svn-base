YAHOO.namespace('widget');
var ADNJOY = YAHOO;


ADNJOY.widget.PlayerPanel = function() {
	this._init();
};

ADNJOY.widget.PlayerPanel.prototype = {
	panel : null,
	mySWF : null,
	cpt : null,
	
	_init : function(){
		//The second argument passed to the
		//constructor is a configuration object:
	this.cpt= 0 ;
	document.getElementById("planelPlayer").innerHTML = "";
	document.getElementById("planelPlayer").expando = this;

	 this.panel = new YAHOO.widget.Panel("planelPlayer", { 
			 width:"660px", 
			 height: "525px",
			 fixedcenter:true,
			 visible:false, 
			 draggable:false, 
			 close:true ,
			 modal:true
		 } );
	 
	 this.panel.setHeader("Publicité");
	 this.panel.setBody("<div id='swfContainer' style='width:640px; height:480px'></div>");
	 this.panel.beforeHideEvent.subscribe(function(){
		 document.getElementById('swfContainer').innerHTML = "";
	 }); 
	 this.panel.render();
	 this.panel.show();
	 
     var html = "<object id='AdVideo' classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab' height='200' width='400'>";
     html+="<param name='src' value='swf/AdVideo.swf'/>";
     html+="<param name='flashVars' value=''/>";
     html+="<embed name='AdVideo' src='swf/AdVideo.swf' pluginspage='http://www.adobe.com/go/getflashplayer' height='100%' width='100%' flashVars=''/>";
     html+="</object>";
	 
     document.getElementById('swfContainer').innerHTML = html;
//	var thisObject = this;
//	 setTimeout("getMyApp('AdVideo').setUrlVideoAd('"+thisObject.url+"');",750);
	},
	
	play : function(url){
		getMyApp('AdVideo').setUrlVideoAd(url);
	}
};
