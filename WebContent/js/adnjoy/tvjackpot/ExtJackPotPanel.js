Ext.ns('Ext.tvjackpot');

(function(){
	
	var duration;
	var start;
	var panel;
	var progressBar;
	var timer;
	var jackPot;
	var task ={
				run: function(){
					var now = new Date().getTime();
					var p = (now-start)/duration;
					var d = (now-start)/1000;
					var s = (((d%60)<10)?'0':'') + parseInt((d%60));
					var m = (((d/60)<10)?'0':'')+parseInt((d/60));
					progressBar.updateProgress(p, m+':'+s);
					if(p>=1){
						progressBar.updateText('Temps écoulé');
						Ext.TaskMgr.stop(this);
						tvBlindTestDwrManager.toLateJackpot(jackPot,function(data){});
					}
				},
				interval: 150
			};
			
	Ext.tvjackpot.JackPotPanel = function(idJackPot) {
		this._init(idJackPot);
	};
	
	Ext.tvjackpot.JackPotPanel.prototype = {
		
		_init : function(idJackPot){
			jackPot = idJackPot;
			var thisObject = this;
		
			tvBlindTestDwrManager.getJackPotChanel(idJackPot,function(dataDwr){
				if(ADNJOY.doExecption(dataDwr))return;
	
				//création du test
				var html ="<div id='resultContainer'></div>";
				html += "<div id='panelQuestionContainer'>";
				html += '<div class="questionLabel">Question :</div>';
				html += '<div class="question">'+dataDwr.data.question+'</div>';
				html += '<div class="reponseLabel">Réponses :</div>';
				html += '<div id="reponses"></div>';
				var responseArray = ["<h3>A :</h3>","<h3>B :</h3>","<h3>C :</h3>","<h3>D :</h3>","<h3>E :</h3>","<h3>F :</h3>"];
//				for(var i=0;i<dataDwr.data.reponses.length;i++){
//					html += '<button id="q'+i+'">'+responseArray[i]+'<div class="responseDiv">'+dataDwr.data.reponses[i].response+'</div></button>';
//					html += '<div></div>';
//				}
				//ajout da la progressBar
				html += '<div id="jackpotPB"></div>';
				html += "</div>";
	
				document.getElementById("panel").innerHTML = "";
				
				panel = new Ext.Window({
					title:"Répondez correctement à la question pour remporter le <h2 style='display:inline;'>JACKPOT</h2>",
					width:680, 
					height: 630,
					applyTo:'panel',
					closeAction:'close',
					modal:true,
					padding:15,
					resizable:false,
					draggable:false,
					html:html
				});
				
				panel.onHide = function(){
					document.getElementById("jackPotResult").innerHTML = "";
				};

				panel.show();
				
//				for(var i=0;i<dataDwr.data.reponses.length;i++){
//				html += '<button id="q'+i+'">'+responseArray[i]+'<div class="responseDiv">'+dataDwr.data.reponses[i].response+'</div></button>';
//				html += '<div></div>';
//			}
				//initialisation des réponses
				var elt = document.getElementById("reponses");
				for(var i=0;i<dataDwr.data.reponses.length;i++){
					var btn = new Ext.Button(
							{
								id:"q"+i,
								renderTo:"reponses",
								height:120,
								width:600,
								text:responseArray[i]+'<div class="responseDiv">'+dataDwr.data.reponses[i].response+'</div>',
								iconCls:'btnSize',
								hideMode:'visibility'
							});

					var objDwr = {
							idJackPot : idJackPot,
							idResponse : dataDwr.data.reponses[i].idResponse,
							thisObject : thisObject
					};
					
					btn.on("click",thisObject._doResponse,objDwr);
				}
				
				document.getElementById("resultContainer").style.display = "none";
				
				//initialisation de la progress bar
				progressBar = new Ext.ProgressBar({
			       text:'',
			 	   width:600,
			 	   renderTo : 'jackpotPB'
			     });
				
				start = new Date().getTime();
				duration = 30000;
				Ext.TaskMgr.start(task);
				
				thisObject.timer = setTimeout(function(){
					var elt = document.getElementById("resultContainer");
					document.getElementById("panelQuestionContainer").style.display="none";
					elt.innerHTML = "Dommage Vous n'avez pas été assez rapide";
				},30000);
				
				
			});
		},
		
		_doResponse : function(e){
			var thisObject = this.thisObject;
			clearTimeout(thisObject.timer);
			tvBlindTestDwrManager.doResponseJackpot(this.idJackPot,this.idResponse,function(dataDwr){
				if(ADNJOY.doExecption(dataDwr))return;
				var elt = document.getElementById("resultContainer");
				document.getElementById("panelQuestionContainer").style.display="none";
					if(dataDwr.data.win == true){
						elt.innerHTML = "BRAVO !!!!<br><br> Vous avez gagné le Jackpot de "+dataDwr.data.value+"&nbsp;"+dataDwr.data.currency;
					}else{
						elt.innerHTML = "PERDU !!!!<br><br> Retentez votre chance une prochaine fois";
					}
				elt.style.display = "";
			});
			
		}
	};


})();