Ext.ns('Ext.user');

Ext.apply(Ext.form.VTypes, {

	heighteen : function(val, field) {
		var date = field.parseDate(val).getTime();
		var today = new Date();
		today.setFullYear(today.getFullYear() - 18);
		
		return (today.getTime()>=date);
	},
	
	heighteenText : 'Vous devez être majeur pour vous inscrire',
	
    strengthPwd : function(val, field) {
		var enoughRegex = new RegExp("(?=.{6,}).*", "g");
		
		if (enoughRegex.test(val)) 
			return true; 
		 else
			return false;
		
	},
	
	strengthPwdText : 'Mot de passe trop simple',
	
    upseudo : function(val, field) {
		var aResults = false;
		
		var callbackProxy = function(results){
	    	 aResults = results.data;
	    };
	    
		var callMetaData = { callback:callbackProxy,  async:false};
		
		userDwrManager.public_checkUniquePseudo(val,callMetaData);
		
		return aResults;		
	},
	
	upseudoText : 'Le pseudo est déjà pris',
	
    umail : function(val, field) {
		var b = this.email(val, field);
		if(b==false) return false;
		
		var aResults = false;
		
		var callbackProxy = function(results){
	    	 aResults = results.data;
	    };
	    
		var callMetaData = { callback:callbackProxy,  async:false};
		
		userDwrManager.public_checkUniqueEmail(val,callMetaData);
		
		return aResults;		
	},
	
	umailText : "Email incorrect ou déjà pris",
	
    password : function(val, field) {
        if (field.initialPassField) {
            var pwd = Ext.getCmp(field.initialPassField);
            return (val == pwd.getValue());
        }
        return true;
    },

    passwordText : 'Mot de passe différent'
	
});


var whyText = [
        	'<h1>Pourquoi vous inscrire ?</h1>',
        	'<br></br>',
        	'<p class="titleInscription">L\'inscription vous permet de :</p>',
        	'<br></br>',
        	'<ul class="ULinscription">',
        		'<li class="LIinscription">gagner de le l\'argent et des lots</li>',
        		'<li class="LIinscription">passer un bon moment durant la publicité</li>',
        		'<li class="LIinscription">se mesurer à des joueurs de la France entière</li>',
//        		'<li class="LIinscription">discuter avec ses amis, grâce à la création de groupe</li>',
//        		'<li class="LIinscription">se mesurer à d\'autres groupes</li>',
        	'</ul>',
        ];


Ext.user.Create = function() {
	this._init();
};

Ext.user.Create.prototype = {
	
	_init : function(){
		this._createForm();
		this._createWhy();
	},
	
	_createWhy : function(){
//		MyPanelUi = Ext.extend(Ext.Panel, {
//		    title: 'My Panel',
//		    width: 400,
//		    height: 250,
//		    tpl: '',
//		    autoHeight: false,
//		    autoWidth: true,
//		    initComponent: function() {
//		        MyPanelUi.superclass.initComponent.call(this);
//		    }
//		});

		
		new Ext.Panel({
			title: 'Pourquoi vous inscrire sur AdBeBack',
			padding: 15,
			height: 385,
			width: 545,
			
			renderTo: 'whyDiv',
			html: whyText.join('')
		});
	
	},
	_loadSexes : function(){
		 var aResults = [];
	     var callbackProxy = function(results){
	    	 aResults = results.data;
	     };
	     //disable async req !  
	     var callMetaData = { callback:callbackProxy,  async:false};  
	     userDwrManager.public_getSexes(callMetaData);   
	     return aResults;
	},
	
	_loadCountries : function(store){
		 var aResults = [];
	     var callbackProxy = function(results){
	    	 aResults = results.data;
	     };
		     
	     //disable async req !  
	     var callMetaData = { callback:callbackProxy,  async:false};  
	     countryDwrManager.public_getAll(callMetaData);
	     return aResults;
	},
	
	_createForm : function(){
		
	    Ext.QuickTips.init();
	    // turn on validation errors beside the field globally
	    Ext.form.Field.prototype.msgTarget = 'side';

		var sexStore = new Ext.data.JsonStore({
			idProperty: 'id',
			fields: ['id', 'label'],
	        data : this._loadSexes()
	    });
		
		var countryStore = new Ext.data.JsonStore({
			idProperty: 'id',
		    fields: ['id', 'country'],
		    data : this._loadCountries()
		});
	
		var fp = new Ext.FormPanel({
			title : 'Votre inscription',
			padding:10,
			labelAlign : 'right',
			labelWidth : 150,
			renderTo : 'inscritDiv',
		    //standardSubmit: true,
		    baseParams: {
				createAccount : ''
		    },
		    width: 375,
		    defaults: {
		    	width: 175
		    },
//		    url: 'CreateAcount.htm',
		    items: [
		            	new Ext.form.TextField({fieldLabel: 'Pseudo *',name: 'pseudo',allowBlank:false,vtype:'upseudo'}),
		            	new Ext.form.ComboBox(
		            							{	fieldLabel: 'Civilité *',
		            								name: 'sexName',
		            								allowBlank:false,
		            								emptyText:'Sélectionner...',
		            								store:sexStore,
		            								mode: 'local',
		            								forceSelection: true,
		            								displayField:'label',
		            								valueField: 'id',
		            								hiddenName : 'sex'
		            							}),
		            	new Ext.form.TextField({fieldLabel: 'Prénom *',name: 'firstName',allowBlank:false}),
		            	new Ext.form.TextField({fieldLabel: 'Nom *',name: 'lastName',allowBlank:false}),
		            	new Ext.form.TextField({fieldLabel: 'Email *',name: 'email',allowBlank:false, vtype:'umail'}),
		            	new Ext.form.TextField({fieldLabel: 'Adresse *',name: 'address1',allowBlank:false}),
		            	new Ext.form.TextField({fieldLabel: 'Adresse suite',name: 'address2',allowBlank:true}),
		            	new Ext.form.NumberField({fieldLabel: 'Code postal *',name: 'zipCode',allowBlank:false}),
		            	new Ext.form.TextField({fieldLabel: 'Ville *',name: 'city',allowBlank:false}),
		            	new Ext.form.ComboBox(
    							{	fieldLabel: 'Pays *',
    								name: 'countryName',
    								emptyText:'Sélectionner...',
    								allowBlank:false,
    								store:countryStore,
    								mode: 'local',
    								forceSelection: true,
    								displayField:'country',
									valueField: 'id',
									hiddenName : 'country'
    							}),
						new Ext.form.DateField({fieldLabel: 'Date de naissance *',name: 'birthday',allowBlank:false,vtype:'heighteen',format:'d/m/Y'}),
						new Ext.form.TextField({fieldLabel: 'Mot de passe *',id:'password',name: 'password',allowBlank:false,inputType:'password',vtype:'strengthPwd'}),
						new Ext.form.TextField({fieldLabel: 'Confirmation *',name: 'passwordConfirm',initialPassField: 'password',allowBlank:false,inputType:'password',vtype:'password'})
					],
		    buttons: [{
		        text: 'Valider votre inscription',
		        handler: function(){
	                var form = fp.getForm();
		            if (form.isValid()) {
		                form.submit(	{	url:'CreateAcount.htm',
		                					waitMsg:'Enregistrement en cours ...',
		                					submitEmptyText: false,
		                					success: function(fp, o){
			                					var msg =  "Merci de votre inscription, vous allez recevoir un Email de confirmation.<br></br>";
			                					msg+="Veuillez suivre les instructions contenues dans cet Email.<br></br>";
			                					msg+="Cordialement<br></br>";
			                					msg+=" Toute l'équipe d'AD BeBack";
								                    Ext.Msg.show({
								                        title: "Votre inscription",
								                        msg: msg,
								                        minWidth: 300,
								                        modal: true,
								                        icon: Ext.Msg.INFO,
								                        buttons: Ext.Msg.OK
								                    });
						                    },
						                    failure: function(fp, o){
						                    	var msg =  "Un problème est servenu sur le serveur, veuillez recommencer ultérieurement.<br></br>";
							                    Ext.Msg.show({
							                    	title: "Votre inscription",
							                        msg: msg,
							                        minWidth: 300,
							                        modal: true,
							                        icon: Ext.Msg.WARNING,
							                        buttons: Ext.Msg.OK
							                    });
						                    }
		                			});
		            }
		        }
		    }]
		});
	}
	
};

Ext.onReady(function(){
	new Ext.user.Create();
});
