Ext.ns('Ext.user');

Ext.apply(Ext.form.VTypes, {

    strengthPwd : function(val, field) {
		var enoughRegex = new RegExp("(?=.{6,}).*", "g");
		
		if (enoughRegex.test(val)) 
			return true; 
		 else
			return false;
		
	},
	
	strengthPwdText : 'mots de passe pas assez complexe',
	
    password : function(val, field) {
        if (field.initialPassField) {
            var pwd = Ext.getCmp(field.initialPassField);
            return (val == pwd.getValue());
        }
        return true;
    },

    passwordText : 'mot de passe différent'
	
});

Ext.user.Modify = function() {
	this._init();
};

Ext.user.Modify.prototype = {
	
	_init : function(){
		this._createForm();
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

		var countryStore = new Ext.data.JsonStore({
			idProperty: 'id',
		    fields: ['id', 'country'],
		    data : this._loadCountries()
		});
	
		var fp = new Ext.FormPanel({
			title : 'Modification de votre compte',
			padding:10,
			labelAlign : 'right',
			labelWidth : 150,
			renderTo : 'inscritDiv',
		    /*baseParams: {
				modAccount : ''
		    },*/
		    width: 375,
		    defaults: {
		    	width: 175
		    },
		    reader :  new Ext.data.JsonReader({
		        fields: [
		            {name: 'email'},
		            {name: 'password'},
		            {name: 'pseudo'},
		            {name: 'address1'},
		            {name: 'address2'},
		            {name: 'zipCode'},
		            {name: 'city'},
		            {name: 'pseudo'},
		            {name: 'firstName'},
		            {name: 'lastName'},
		            {name: 'sex'},
		            {name:'birthday', type:'date', dateFormat:'Y-m-d'},
		            {name: 'country',mapping:'country.id'},
		            {name: 'countryName',mapping:'country.name'}
		        ]
		    }),
		    
		    items: [
		            	new Ext.form.TextField({fieldLabel: 'Pseudo *',name: 'pseudo',readOnly:true}),
		            	new Ext.form.TextField({fieldLabel: 'Civilité *',name: 'sex',readOnly:true}),
		            	new Ext.form.TextField({fieldLabel: 'Prénom *',name: 'firstName',readOnly:true}),
		            	new Ext.form.TextField({fieldLabel: 'Nom *',name: 'lastName',readOnly:true}),
		            	new Ext.form.TextField({fieldLabel: 'Email *',name: 'email',allowBlank:false, vtype:'email'}),
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
    								//triggerAction: 'all',
    								forceSelection: true,
    								displayField:'country',
									valueField: 'id',
									hiddenName : 'country'
    							}),
						new Ext.form.DateField({fieldLabel: 'Date de naissance *',name: 'birthday',readOnly:true,format:'d/m/Y'}),
						new Ext.form.TextField({fieldLabel: 'Mot de passe *',id:'password',name: 'password',allowBlank:false,inputType:'password',vtype:'strengthPwd'}),
						new Ext.form.TextField({fieldLabel: 'Confirmation *',name: 'passwordConfirm',initialPassField: 'password',allowBlank:false,inputType:'password',vtype:'password'})
					],
		    buttons: [{
		        text: 'Modifier votre compte',
		        handler: function(){
	                var form = fp.getForm();
		            if (form.isValid()) {
		                form.submit(	{	url:'ModAccount.htm?modAccount',
		                					waitMsg:'Enregistrement en cours ...',
		                					submitEmptyText: false,
		                					success: function(fp, o){
		                						var msg =  "Votre compte à été modifié avec succès.";
								                    Ext.Msg.show({
								                        title: "Modification de votre compte",
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
							                    	title: "Modification de votre compte",
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
		
		fp.getForm().load({url:'ModAccount.htm?loadAccount', waitMsg:'Chargement ...'});
	}
	
};

Ext.onReady(function(){
	if(!openLogin)new Ext.user.Modify();
});
