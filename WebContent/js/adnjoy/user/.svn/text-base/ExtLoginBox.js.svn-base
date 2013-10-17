var ADNJOY = YAHOO;
YAHOO.namespace('user');
	

(function(){
	var box;
	
	ADNJOY.user.LoginBox = function() {
		this.init();
	};
	
	ADNJOY.user.LoginBox.prototype = {
			
		//initialisation		
		init : function(){
	//		this._constructPanel();
			this._initEvents();
			
			if(openLogin){
				this.showPanel();
			}
		},
		
		//construction du panel
		_constructPanel : function(){
	//		document.getElementById("loginBox").style.display = "";
			//this.box = new YAHOO.widget.Panel("loginBox", { xy:xy ,modal:true,fixedcenter: true,width:"450px", visible:false, constraintoviewport:true } ); 
	//		this.box.render();
			
			box = new Ext.Window({
					applyTo:'loginBox',
					closeAction:'hide',
					modal:true,
					padding:15,
					resizable:false,
					draggable:false
				});
		},
				
		showPanel : function(){
			if(!box) this._constructPanel();
			document.getElementById("loginDiv").style.display = "";
			document.getElementById("forgotDiv").style.display = "none";
			document.getElementById("messageDiv").style.display = "none";
			document.getElementById("loginBoxErr").style.display = "none";
			box.show();
		},
				
		//cacher le panel
		hidePanel : function(){
			document.getElementById("loginDiv").style.display = "";
			document.getElementById("forgotDiv").style.display = "none";
			document.getElementById("messageDiv").style.display = "none";
			document.getElementById("loginBoxErr").style.display = "none";
			box.hide();
		},
				
		//initialisation des événements
		_initEvents : function(){
			new YAHOO.widget.Button("loginBtn").on("click", this._connect,this,true);
			new YAHOO.widget.Button("findPwdBtn").on("click", this._sendPwd,this,true);
			YAHOO.util.Event.on("inPwd", "inLogin", this._checkEnter,this,true);
			YAHOO.util.Event.on("inPwd", "keydown", this._checkEnter,this,true);
			YAHOO.util.Event.on("forgotPwd", "click", this._forgotPwd,this,true);
			YAHOO.util.Event.on("backLogin", "click", this._backLogin,this,true);
		},
		
		_checkEnter : function(e){
			if(e.keyCode==13){
				this._connect(e);
			}
		},
		
		_sendPwd : function(e){
			var email = document.getElementById("inEmail").value;
			var thisObject =this;
			userDwrManager.public_sendPassorwd(email,function(dataDwr){
				if(ADNJOY.doExecption(dataDwr))return;
				if(dataDwr.data){
					document.getElementById("messageDiv").style.display = "";
					document.getElementById("messageDiv").innerHTML = "Connectez vous sur votre Email pour obtenir votre mot de passe.";
	//				document.getElementById("loginDiv").style.display = "";
	//				document.getElementById("forgotDiv").style.display = "none";
					document.getElementById("loginBoxErr").style.display = "none";
	//				thisObject.hidePanel();
				}else{
					document.getElementById("messageDiv").style.display = "none";
					document.getElementById("loginBoxErr").style.display = "";
					document.getElementById("loginBoxErr").innerHTML = "Il n'existe pas de compte avec cet Email.";
				}
			});
		},
		
		_connect : function(e){
			document.getElementById("loginBoxErr").style.display = "none";
			var speudo = document.getElementById("inLogin").value;
			var pwd = document.getElementById("inPwd").value;
			var thisObject = this;
			userDwrManager.public_connectUser(speudo,pwd,function(dataDwr){
				if(ADNJOY.doExecption(dataDwr))return;
				if(dataDwr.data){
					document.getElementById("loginBoxErr").style.display = "none";
					thisObject.hidePanel();
					document.getElementById("inLogin").value = "";
					document.getElementById("inPwd").value = "";
					window.location.reload();
				}else{
					document.getElementById("loginBoxErr").style.display = "";
					document.getElementById("loginBoxErr").innerHTML = "Votre mot de passe ou votre pseudo est incorrect";
				}
			});
		},
		
		_forgotPwd : function(e){
			document.getElementById("messageDiv").style.display = "none";
			document.getElementById("loginBoxErr").style.display = "none";
			document.getElementById("loginDiv").style.display = "none";
			document.getElementById("forgotDiv").style.display = "";
			document.getElementById("forgotPwd").style.display = "none";
			document.getElementById("backLogin").style.display = "";
		},
		
		_backLogin : function(e){
			document.getElementById("messageDiv").style.display = "none";
			document.getElementById("loginBoxErr").style.display = "none";
			document.getElementById("loginDiv").style.display = "";
			document.getElementById("forgotDiv").style.display = "none";
			document.getElementById("forgotPwd").style.display = "";
			document.getElementById("backLogin").style.display = "none";
		}
	};
})();