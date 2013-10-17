Ext.ns('Ext.blindtest');

(function(){

	var duration;
	var start;
	var progressBar;
	var btns =[];
	var thisObject;
	var task ={
				run: function(){
					var now = new Date().getTime();
					var p = (now-start)/duration;
					var d = ((duration -(now-start))/1000)+1;
					var s = (((d%60)<10)?'0':'') + parseInt((d%60));
					var m = (((d/60)<10)?'0':'')+parseInt((d/60));
					progressBar.updateProgress(p, m+':'+s);
					if(p>=1){
						progressBar.updateText('Fin de la publicité');
						Ext.TaskMgr.stop(this);	
					}
				},
				interval: 150
			};
	
	Ext.blindtest.BT = function() {
		this._init();
	};
	
	Ext.blindtest.BT.prototype = {
		idBlindTest : null,
		idJackPot : null,
		questions : null,
		cpt : null,
		urls : null,
		
		_init : function(){
			thisObject = this;
			blindTestDwrManager.inscriptionUser(function(dataDwr){
				if(ADNJOY.doExecption(dataDwr))return;
				
				progressBar = new Ext.ProgressBar({
			       text:'Attente du blindtest',
			 	   width:640,
			 	   renderTo : 'progressBarDiv'
			     });
				
				start = new Date().getTime();
				duration = dataDwr.data.beginIn;
				Ext.TaskMgr.start(task);
				
				setTimeout("startGame()",dataDwr.data.beginIn);
				
				thisObject.idBlindTest = dataDwr.data.idBlindTest;
				thisObject.idJackPot = dataDwr.data.idJackPot;
				thisObject.questions = dataDwr.data.questions;
				
				thisObject.urls = [];
				for(var i = 0;i<thisObject.questions.length;i++){
					var host = location.protocol + '//' + location.host+ '/AdnJoy/Download.htm?url='+thisObject.questions[i].url;
					thisObject.urls.push(host);
				}
				
				
				var url = 'swf/AdVideo.swf';
				var html ="<div id='swfContainer' style='width:640px; height:480px'>";
			    html+= "<object id='AdVideo' classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab' height='100%' width='100%'>";
			    html+="<param name='src' value='"+url+"'/>";
			    html+="<param name='flashVars' value=''/>";
			    html+="<embed name='AdVideo' src='"+url+"' pluginspage='http://www.adobe.com/go/getflashplayer' height='100%' width='100%' flashVars=''/>";
			    html+="</object>";
			    html+="</div>";
			    
			    document.getElementById("playerDiv").innerHTML = html;
				
			});
		},
		
		_startGame : function(){
			this.cpt = 0;
			document.getElementById("nbPoint").innerHTML = "0";
			document.getElementById("labelBT").innerHTML = "Nombre de Points : ";
			
			this._drawNextQuestion();
		},
		
		_drawNextQuestion : function(){
			
			btns = [];
			
			if(this.cpt>=this.questions.length){
				//fin du jeu demande du résultat
				document.getElementById("jackpot").value = this.idJackPot;
				document.getElementById("getClassement").submit();
			}else{
				var question = this.questions[this.cpt];
				var elt = document.getElementById("responses");
				elt.innerHTML ="";
				for(var i = 0;i<question.responses.length;i++){
					
					var host = location.protocol + '//' + location.host+ '/AdnJoy/Download.htm?url='+question.responses[i].urlLogo;
					var bt = new Ext.Button(
							{
								cls : 'spacer',
								id:"pushbutton"+(i+1),
								renderTo:"responses",
								height:115,
								width:290,
								text:"<img class='max-img-size' src='"+host+"'>",
								iconCls:'btnSize',
								hideMode:'visibility'
							});
					var obj ={};
					obj.idBlindTest = this.idBlindTest;
					obj.idBrandResponse = question.responses[i].idBrand;
					obj.numAd = this.cpt;
					obj.idElt = "pushbutton"+(i+1);
					obj.host = host;
					bt.obj = obj;
					btns.push(bt);
					

					bt.on("click",this._doResponseUser ,obj);
				}
				
				
				start = new Date().getTime();
				duration = question.duration;
				Ext.TaskMgr.start(task);
				
				setTimeout("nextQuestion()",question.duration);
				getMyApp("AdVideo").setUrlVideoAd(this.urls[this.cpt]);
				this.cpt++;
			}
		},
			
		_doResponseUser : function(e){
			for(var i=0;i<btns.length;i++){
				var bt = btns[i];
				bt.un("click",thisObject._doResponseUser,this);
			}
			var objRep = this;
			blindTestDwrManager.playGame(this.idBlindTest, this.numAd, this.idBrandResponse,function(dataDwr){
				if(ADNJOY.doExecption(dataDwr))return;
				document.getElementById("nbPoint").innerHTML = dataDwr.data.score.toFixed(3);
				if(dataDwr.data.correct){
					for(var i=0;i<btns.length;i++){
						var bt = btns[i];
						if(bt.getId()!= objRep.idElt){
							bt.setVisible(false);
						}
					}
				}else{
					
					var imgSrc;
					for(var i=0;i<btns.length;i++){
						var bt = btns[i];
						if(bt.obj.idBrandResponse==dataDwr.data.correctBrand){
							imgSrc = bt.obj.host;
							break;
						}
					}
					//effacer toutes les réponses
					document.getElementById("responses").innerHTML = "<h2>Dommage !!!</h2><br>La bonne réponse était : </br><br></br><img class='max-img-size' src='"+imgSrc+"'>";
//					document.getElementById("responses").innerHTML = "<h2>Mauvaise réponse !!!</h2>";
				}
			});
		}
			
	};

})();


Ext.onReady(function(){
	if(!openLogin)objectJs = new Ext.blindtest.BT();
	else
		document.getElementById("game").style.display = "none";
});
