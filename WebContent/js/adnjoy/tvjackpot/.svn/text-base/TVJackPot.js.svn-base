Ext.ns('Ext.tvjackpot');
(function(){

	var duration;
	var start;
	var progressBar;
	var btns =[];
	var thisObject;
	var progressTitle = 'En attente ...';

	var task ={
				run: function(){
					var now = new Date().getTime();
					var p = (now-start)/duration;
					var d = ((duration -(now-start))/1000)+1;
					var s = (((d%60)<10)?'0':'') + parseInt((d%60));
					var m = (((d/60)<10)?'0':'')+parseInt((d/60));
					progressBar.updateProgress(p, m+':'+s);
					if(p>=1){
						progressBar.updateText(progressTitle);
						Ext.TaskMgr.stop(this);	
					}
				},
				interval: 150
			};

	
	Ext.tvjackpot.TVJackPot = function() {
		this._init();
	};
	
	Ext.tvjackpot.TVJackPot.prototype = {
		chanelChooser : null,
		
		idBlindTest : null,
		idJackPot : null,
		questions : null,
		cpt : null,
		selectedChanel : null,
			
		_init : function(){
			thisObject = this;
			document.getElementById("chanelChooser").style.display = "";
			document.getElementById("game").style.display = "none";
	
			//choix de la chaîne		
			this.chanelChooser = new ADNJOY.tvjackpot.ChanelChooser("chanelChooser");
			this.chanelChooser.eventChanelSelected.subscribe(this._getNextTvJackPot);
		},
		
		_getNextTvJackPot : function(e){
			document.getElementById("game").style.display = "";
			document.getElementById("chanelChooser").style.display = "none";
			
			thisObject.progressValue = 0;
			if(Ext.isDefined(this.selectedChanel)){
				thisObject.selectedChanel = this.selectedChanel.id;
			}
			
			tvBlindTestDwrManager.inscriptionUser(thisObject.selectedChanel ,function(dataDwr){
				if(ADNJOY.doExecption(dataDwr))return;
				if(dataDwr.data == null){
					setTimeout(thisObject._getNextTvJackPot,10000);
					return;
				}

				progressBar = new Ext.ProgressBar({
				       text:'Attente du blindtest',
				 	   width:640,
				 	   renderTo : 'progressBarDiv'
				     });
					
				start = new Date().getTime();
				duration = dataDwr.data.beginIn;
				Ext.TaskMgr.start(task);
				
				setTimeout(thisObject._startGame,dataDwr.data.beginIn);
				
				thisObject.idBlindTest = dataDwr.data.idBlindTest;
				thisObject.idJackPot = dataDwr.data.idJackPot;
				thisObject.questions = dataDwr.data.questions;
				
			});
			
		},
		
		_startGame : function(){
			document.getElementById("nbPoint").innerHTML = "0";
			document.getElementById("labelPoint").innerHTML = "Nombre de Points : ";
			thisObject.cpt = 0;
			thisObject._drawNextQuestion();
		},
		
		_drawNextQuestion : function(){
			if(thisObject.cpt>=thisObject.questions.length){
				//fin du jeu demande du résultat
				document.getElementById("jackpot").value = thisObject.idJackPot;
				document.getElementById("getClassement").submit();
			}else{
				var question = thisObject.questions[thisObject.cpt];
				if(question.pauseBefore!=0){
					setTimeout(thisObject._drawNextQuestion,question.pauseBefore);
					question.pauseBefore = 0;
				}else{
					var elt = document.getElementById("responsesBrand");
					elt.innerHTML ="";
					for(var i = 0;i<question.responses.length;i++){
						var host = location.protocol + '//' + location.host+ '/AdnJoy/Download.htm?url='+question.responses[i].urlLogo;
						var bt = new Ext.Button(
								{
									cls : 'spacer',
									id:"pushbutton"+(i+1),
									renderTo:"responsesBrand",
									height:115,
									width:290,
									text:"<img class='max-img-size' src='"+host+"'>",
									iconCls:'btnSize',
									hideMode:'visibility'
								});
						var obj ={};
						obj.idBlindTest = thisObject.idBlindTest;
						obj.idBrandResponse = question.responses[i].idBrand;
						obj.numAd = thisObject.cpt;
						obj.idElt = "pushbutton"+(i+1);
//						obj.idBrandResponse = question.responses[i].idBrand;
						obj.host = host;
						bt.obj = obj;
						btns.push(bt);
						bt.on("click",thisObject._doResponseUser ,obj);
					}
					 
					start = new Date().getTime();
					duration = question.duration;
					Ext.TaskMgr.start(task);
					
					setTimeout(thisObject._drawNextQuestion,question.duration);
					thisObject.cpt++;
				}
			}
		},
			
		_doResponseUser : function(e){
			for(var i=0;i<btns.length;i++){
				var bt = btns[i];
				bt.un("click",thisObject._doResponseUser,this);
			}
			var objRep = this;
			tvBlindTestDwrManager.playGame(thisObject.idJackPot,this.idBlindTest, this.numAd, this.idBrandResponse,function(dataDwr){
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
					document.getElementById("responsesBrand").innerHTML = "<h2>Dommage !!!</h2><br>La bonne réponse était : </br><br></br><img class='max-img-size' src='"+imgSrc+"'>";
				}
			});
		}
		
	};

})();


Ext.onReady(function(){
	if(!openLogin)new Ext.tvjackpot.TVJackPot();
	else
		document.getElementById("game").style.display = "none";
});
