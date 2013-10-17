var ADNJOY = YAHOO;
YAHOO.namespace('widget');

(function(){
	var panel;
	
	ADNJOY.widget.PlayerPanel = function(swfUrl) {
		this._init(swfUrl);
	};
	
	ADNJOY.widget.PlayerPanel.prototype = {
		mySWF : null,
		cpt : null,
		
		_init : function(swfUrl){
			//The second argument passed to the
			//constructor is a configuration object:
		this.cpt= 0 ;
		document.getElementById("panel").innerHTML = "";
		document.getElementById("panel").expando = this;
		var url = (swfUrl)?swfUrl:'swf/AdVideo.swf';
		var html ="<div id='swfContainer' style='width:640px; height:480px'>";
	    html+= "<object id='AdVideo' classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab' height='100%' width='100%'>";
	    html+="<param name='src' value='"+url+"'/>";
	    html+="<param name='flashVars' value=''/>";
	    html+="<embed name='AdVideo' src='"+url+"' pluginspage='http://www.adobe.com/go/getflashplayer' height='100%' width='100%' flashVars=''/>";
	    html+="</object>";
	    html+="</div>";
		
	    
	    
        var getViewportWidth = function(el) {
            return documentElement.clientWidth;
        };
	    
		panel = new Ext.Window({
			applyTo:'panel',
			title: 'Publicité',
			closeAction:'hide',
			modal:true,
			padding:15,
			resizable:false,
			draggable:false,
			html:html
		});

		panel.onHide = function(){
			document.getElementById('swfContainer').innerHTML = "";
		};
		
				
		panel.show();
		panel.center();
		
	//	 this.panel = new YAHOO.widget.Panel("planelPlayer", { 
	//			 width:"660px", 
	//			 height: "525px",
	//			 fixedcenter:true,
	//			 visible:false, 
	//			 draggable:false, 
	//			 close:true ,
	//			 modal:true
	//		 } );
		 
	//	 this.panel.setHeader("Publicité");
	//	 this.panel.setBody("");
	//	 this.panel.beforeHideEvent.subscribe(function(){
	//		 document.getElementById('swfContainer').innerHTML = "";
	//	 }); 
	//	 this.panel.render();
	//	 this.panel.show();
	
		 
	//     document.getElementById('swfContainer').innerHTML = html;
	//	var thisObject = this;
	//	 setTimeout("getMyApp('AdVideo').setUrlVideoAd('"+thisObject.url+"');",750);
		},
		
		play : function(url){
			getMyApp('AdVideo').setUrlVideoAd(url);
		}
	};
})();