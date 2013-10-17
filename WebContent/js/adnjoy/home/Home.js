//YAHOO.namespace('ADNJOY');
var ADNJOY = YAHOO;

ADNJOY.Home = function() {
	this._init();
};

ADNJOY.Home.prototype = {
	player : null,
	urlPlayer : null,
		
	_init : function(){
		this._createLastAds();
		this._createLastWinners();
		this._createJackPotChanel();
	},
	
	_createLastAds : function(){
		var thisObject = this;
		adDwrManager.public_getNewAds(function(dataDwr){
			if(ADNJOY.doExecption(dataDwr))return;
			var adsContainer = document.getElementById("newAdsContainer");
			adsContainer.innerHTML = "";
			
			if(dataDwr.data.length==0){
				return;
			}

			var olContainer = document.createElement("ol");
			adsContainer.appendChild(olContainer);
			
			
			for(var i =0;i<dataDwr.data.length;i++){
				var liContainer = document.createElement("li");
				olContainer.appendChild(liContainer);

				var divContainer = document.createElement("div");
				divContainer.className = "centerContainer";
				divContainer.appendChild(document.createElement("span"));
				
				var imgBrand = document.createElement("img");
				var host = location.protocol + '//' + location.host+ '/AdnJoy/Download.htm?url='+dataDwr.data[i].brandLogoFile;
				imgBrand.src = host;
				imgBrand.className = "txtLink";
				imgBrand.className = "imgCenter";
				imgBrand.expando = this;
				divContainer.appendChild(imgBrand);
				var obj={};
				obj.thisObject = thisObject;
				obj.url =location.protocol + '//' + location.host+ '/AdnJoy/Download.htm?url='+dataDwr.data[i].dlFile;
				YAHOO.util.Event.addListener(imgBrand, "click",thisObject._showPlayer ,obj,true);
				
				liContainer.appendChild(divContainer);
			}
			
			//création du carrousel
			var carousel    = new YAHOO.widget.Carousel("newAdsContainer", {
                animation: { speed: 0.5 },
                numVisible: [4,2] 
		      });
			carousel.set("autoPlayInterval", 5000);
			carousel.set("isCircular", true); // make the widget circular
			carousel.registerPagination("<span style='text-align:left;'><h2>Nouveautés de la semaine</h2></span>"); 
		    carousel.render(); // get ready for rendering the widget
		    carousel.show();   // display the widget
		    carousel.render();
		     
		    // Automatically scroll the Carousel after every
		    // autoPlayInterval millisecond.
		    carousel.startAutoPlay();

		});
	},
	
	_createLastWinners : function(){
		var thisObject = this;
		
			var htmlStart ='<div style="text-align: center;padding-bottom: 15px;">';
			htmlStart+='<span style="display: inline-block;">';
 		 	var htmlEnd ='</span></div>';
			
			
			wonJackPotDwrManager.public_getLastTvWinners(function(dataDwr){
				if(ADNJOY.doExecption(dataDwr))return;
				var inObjectHtml = htmlStart;
				for(var i=0;i<dataDwr.data.length;i++){
					inObjectHtml+=thisObject._createTvWinner(dataDwr.data[i]);
				}
				inObjectHtml+=htmlEnd;
				var panel = new Ext.Panel({
					padding:15,
					width :530,
					height:500,
					autoScroll:true,
					applyTo:'LastTVWinnersResult',
					html:inObjectHtml
				});
				
//				document.getElementById("lastTVWinContent").innerHTML = inObjectHtml;
			});
			
			wonJackPotDwrManager.public_getLastBtWinners(function(dataDwr){
				if(ADNJOY.doExecption(dataDwr))return;
				var inObjectHtml = htmlStart;
				for(var i=0;i<dataDwr.data.length;i++){
					inObjectHtml+=thisObject._createBtWinner(dataDwr.data[i]);
				}
				inObjectHtml+=htmlEnd;
				var panel = new Ext.Panel({
					padding:15,
					width :275,
					height:500,
					autoScroll:true,
					applyTo:'LastBTWinnersResult',
					html:inObjectHtml
				});
//				document.getElementById("lastBTWinContent").innerHTML = inObjectHtml;
			});
	},
	
	_createTvWinner : function(u){
		var host = location.protocol + '//' + location.host+ '/AdnJoy/Download.htm?url='+u.winOnImg;
		var html = '<div class="winnerContainer">';
			html+= '<div class="x-panel-header" style="font-size:120%;margin-bottom:10px;">'+u.pseudo+'</div>';
			html+= '<div class="winnerDivTxt"><span class="winnerLabel">Date :</span>&nbsp;<span class="winnerTxt">'+u.dt+'</span></div>';
			html+= '<div class="winnerDivTxt"><span class="winnerLabel">Gain :</span>&nbsp;<span class="winnerTxt">'+u.lotName+'</span></div>';	
			html+= '<div class="winnerDivTxt"><span class="winnerLabel">Valeur :</span>&nbsp;<span class="winnerTxt">'+u.lotValue+'&nbsp;'+u.currency+'</span></div>';
			html+= '<div class="winnerLogoContainer">';
				html+= '<div>Gagné sur :</div>';
					html+= '<img alt="" src="'+host+'">';
				html+= '</div>';
			html+= '</div>';
		return html;
	},
	
	_createBtWinner : function(u){
		var html = '<div class="winnerContainer" style="height: 90px;">';
		html+= '<div class="x-panel-header" style="font-size:120%;margin-bottom:10px;">'+u.pseudo+'</div>';
			html+= '<div class="winnerDivTxt"><span class="winnerLabel">Date :</span>&nbsp;<span class="winnerTxt">'+u.dt+'</span></div>';
			html+= '<div class="winnerDivTxt"><span class="winnerLabel">Gain :</span>&nbsp;<span class="winnerTxt">'+u.lotName+'</span></div>';	
			html+= '<div class="winnerDivTxt"><span class="winnerLabel">Valeur :</span>&nbsp;<span class="winnerTxt">'+u.lotValue+'&nbsp;'+u.currency+'</span></div>';
			html+= '</div>';
		return html;
	},
	
	_createJackPotChanel : function(){
		var thisObject = this;
		chanelDwrManager.public_getChanelsJackPot(function(dataDwr){
			if(ADNJOY.doExecption(dataDwr))return;
			
			var adsContainer = document.getElementById("JackPotChanelContainer");
			
			adsContainer.innerHTML = "";
			var olContainer = document.createElement("ol");
			adsContainer.appendChild(olContainer);
			for(var i =0;i<dataDwr.data.length;i++){
				var liContainer = document.createElement("li");
				olContainer.appendChild(liContainer);

				var spanContainer = document.createElement("span");
				spanContainer.className =  "jackpotDisplay";
				
				
				var divContainer = document.createElement("div");
				divContainer.className = "centerContainer";
				spanContainer.appendChild(divContainer);
				divContainer.appendChild(document.createElement("span"));
				
				var imgBrand = document.createElement("img");
				var host = location.protocol + '//' + location.host+ '/AdnJoy/Download.htm?url='+dataDwr.data[i].chanelImg;
				imgBrand.src = host;
				imgBrand.className = "txtLink";
				imgBrand.className = "imgCenter";
				imgBrand.expando = this;
				divContainer.appendChild(imgBrand);

				var spanValueContainer = document.createElement("div");
				spanValueContainer.className =  "jackpotValue";
				spanContainer.appendChild(spanValueContainer);
				var spanValue = document.createElement("span");
				spanValue.innerHTML = dataDwr.data[i].value + "&nbsp;"+dataDwr.data[i].currency;
				spanValueContainer.appendChild(spanValue);

				var spanWinContainer = document.createElement("div");
				spanWinContainer.className =  "lastWin";
				spanContainer.appendChild(spanWinContainer);
				var spanWin = document.createElement("span");
				var winDate = dataDwr.data[i].lastWonDate;
				if( winDate != "" && winDate != null)
					spanWin.innerHTML = "Dernier gagnant :&nbsp;"+dataDwr.data[i].lastWonDate;
				else
					spanWin.innerHTML = "Aucun gagnant";
				
				spanWinContainer.appendChild(spanWin);

				
				liContainer.appendChild(spanContainer);
			}
			
			//création du carrousel
			var carousel    = new YAHOO.widget.Carousel("JackPotChanelContainer", {
                animation: { speed: 0.5 },
                numVisible: [4,2] 
		      });
			carousel.set("autoPlayInterval", 5000);
			carousel.set("isCircular", true); // make the widget circular
			carousel.registerPagination("<span style='text-align:left;'><h2>Jackpot des chaînes de télévision</h2></span>"); 
		    carousel.render(); // get ready for rendering the widget
		    carousel.show();   // display the widget
		    carousel.render();
		     
		    // Automatically scroll the Carousel after every
		    // autoPlayInterval millisecond.
		    carousel.startAutoPlay();

		});
	},
	
	play : function(){
		this.player.play(this.urlPlayer);
	},

	_showPlayer : function(e){
		this.thisObject.urlPlayer = this.url; 
		this.thisObject.player = new ADNJOY.widget.PlayerPanel();
	}
};

YAHOO.util.Event.onDOMReady(function(){ 
	HomeObj = new ADNJOY.Home();
});
