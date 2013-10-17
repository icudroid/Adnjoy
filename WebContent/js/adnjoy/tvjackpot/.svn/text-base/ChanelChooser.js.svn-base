var ADNJOY = YAHOO;

YAHOO.namespace('tvjackpot');

ADNJOY.tvjackpot.ChanelChooser = function(idContainer) {
	this._init(idContainer);
};

ADNJOY.tvjackpot.ChanelChooser.prototype = {
	
	idContainer : null,
	datas : null,
	eventChanelSelected : null,
	
	left : null,
	
	currentPage : null,
	nbPages : null,
	
	chanelsContainer : null,
	leftArrow : null,
	rightArrow : null,
	
	selectedChanel : null,
	
	nbCol : 2,
	
	_init : function(idContainer){
		this.left = 40;
		this.currentPage = 1;
		this.eventChanelSelected = new YAHOO.util.CustomEvent("onChanelSelected",this);
		this.idContainer = idContainer;
		
		var html = '<div class="explainJP">Sélectionner la chaîne de télévision que vous regardez. Dès le début de la page publicitaire, le jeu démarrera automatiquement. Ce sera alors à vous de jouer.<br>Bonne chance.</div>'  
		
		html+='<div style="text-align: center;border:1px solid #DADADA;padding-bottom:10px;text-align:center;" id="chanelDiv">';
			html+='<span style="display: inline-block;">';
				html+='<div style="padding-top: 15px"></div>';
				html+='<span>Filtre&nbsp;:&nbsp;</span><input type="text" id="filterInput"></input>';
				html+='<div id="chanelChooserDiv"></div>';
				html+='</span>';
		html+='</div>';
		
		var elt = document.getElementById(this.idContainer);
		elt.innerHTML = html;
		elt.expando = this;
		
		YAHOO.util.Event.addListener("filterInput", "keyup", this._doFilter,this,true);
		var thisObject = this;
		chanelDwrManager.getAllFromUserCountry(document.getElementById("filterInput").value,function(dataDwr){
			if(ADNJOY.doExecption(dataDwr))return;
			thisObject.datas = dataDwr.data;
			thisObject._createView();
			thisObject._calculateArrows();
		});
	},
	
	_doFilter : function(e){
		var thisObject = this;
		chanelDwrManager.getAllFromUserCountry(document.getElementById("filterInput").value,function(dataDwr){
			if(ADNJOY.doExecption(dataDwr))return;
			thisObject.datas = dataDwr.data;
			thisObject._createChanelsPresentation();
			thisObject._calculateArrows();
			thisObject.currentPage = 1;
			thisObject.left = 40;
			thisObject.chanelsContainer.style.left= thisObject.left+"px";
		});
	},
	
	
	_calculateArrows : function(){
		this.nbPages = parseInt((this.datas.length/(3*this.nbCol)));
		this.nbPages+= ((this.datas.length%(3*this.nbCol)!=0)?1:0);
		if(this.nbPages<=1){
			this.leftArrow.style.display = "none";
			this.rightArrow.style.display = "none";
		}else{
			this.rightArrow.style.display = "";
			this.leftArrow.style.display = "none";
		}
	},
	
	_leftArrow : function(e){
		this.left+=705;
		this.chanelsContainer.style.left= this.left+"px";
		this.currentPage-=1;
		
		if(this.currentPage == 1){
			this.leftArrow.style.display = "none";
			this.rightArrow.style.display = "";
			
		}else if(this.currentPage == this.nbPages){
			this.leftArrow.style.display = "";
			this.rightArrow.style.display = "none";
		}else{
			this.leftArrow.style.display = "";
			this.rightArrow.style.display = "";
		}
	},

	_rightArrow : function(e){
		this.left-=705;
		this.chanelsContainer.style.left= this.left+"px";
		this.currentPage+=1;
		if(this.currentPage == this.nbPages){
			this.leftArrow.style.display = "";
			this.rightArrow.style.display = "none";
			
		}else{
			this.leftArrow.style.display = "";
		}
	},

	_createChanelsPresentation : function(){
		this.chanelsContainer.innerHTML = "";
		var three = null;
		for(var i = 0;i<this.datas.length;i++){
			if(i%this.nbCol == 0){
				//création nouveau container de nbCol chanels
				var three = document.createElement("div");
				three.className = "threeChanelsContainer";
				this.chanelsContainer.appendChild(three);
			}
			//ajouter chanel
			var chanel = document.createElement("div");
			chanel.className = "chanelContainer";
			chanel.appendChild(document.createElement("span"));
			
			var imgChanel = document.createElement("img");
			var host = location.protocol + '//' + location.host+ '/AdnJoy/Download.htm?url='+this.datas[i].logoFile;
			imgChanel.src = host;
			imgChanel.className = "txtLink";
			imgChanel.className = "imgCenter";
			imgChanel.expando = this;
			chanel.appendChild(imgChanel);
			YAHOO.util.Event.addListener(imgChanel, "click",this._onChanelClicked ,this.datas[i],true);
	
			three.appendChild(chanel);
		}
	},

	
	_createView : function(){
		//create Div container
		var container = document.getElementById(this.idContainer);
		container.className = "chanelComponent";
		
		//fleche gauche
		var eltLeftArrow = document.createElement("div");
		eltLeftArrow.className = "leftArrow";
		container.appendChild(eltLeftArrow);
		
		
		var eltSpanLeftArrow = document.createElement("div");
		eltSpanLeftArrow.className= "centerDiv";
		eltLeftArrow.appendChild(eltSpanLeftArrow);
		this.leftArrow = document.createElement("img");
		this.leftArrow.src = "js/adnjoy/tvjackpot/assets/rightArrow.gif";
		this.leftArrow.className = "txtLink";
		eltSpanLeftArrow.appendChild(this.leftArrow);
		YAHOO.util.Event.addListener(this.leftArrow, "click", this._leftArrow,this,true);
		
		//container de chanel
		this.chanelsContainer = document.createElement("div");
//		var eltChanelContainer = document.createElement("div");
		this.chanelsContainer.className = "chanelsContainer";
		this.chanelsContainer.style.left= this.left+"px";

		container.appendChild(this.chanelsContainer);
		
		this._createChanelsPresentation();
		
		//fleche droit
		var eltRightArrow = document.createElement("div");
		eltRightArrow.className = "rightArrow";
		container.appendChild(eltRightArrow);
		
		var eltSpanRightArrow = document.createElement("div");
		eltSpanRightArrow.className= "centerDiv";
		eltRightArrow.appendChild(eltSpanRightArrow);
		this.rightArrow = document.createElement("img");
		this.rightArrow.src = "js/adnjoy/tvjackpot/assets/leftArrow.gif";
		this.rightArrow.className = "txtLink";
		eltSpanRightArrow.appendChild(this.rightArrow);
		YAHOO.util.Event.addListener(this.rightArrow, "click", this._rightArrow,this,true);


	},
	
	_onChanelClicked : function(e){
		if (e.srcElement && !e.target) { // supplement IE with target
            e.target = e.srcElement;
        }
		var thisObject = e.target.expando;
		thisObject.selectedChanel = this;
		thisObject.eventChanelSelected.fire();
		
	}
	
	
	
};