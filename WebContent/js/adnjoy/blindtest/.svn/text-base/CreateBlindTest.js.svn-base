Ext.ns('Ext.blindtest');

(function(){

	var thisObject;
	
	Ext.blindtest.Create = function() {
		this._init();
	};
	
	Ext.blindtest.Create.prototype = {
		
		_init : function(){
			thisObject = this;
			this._createForm();
		},
		
		_createForm : function(){
			var fp = new Ext.FormPanel({
				title : 'Création des BT',
				padding:10,
				labelAlign : 'right',
				labelWidth : 150,
				renderTo : 'createBTDiv',
			    baseParams: {
					generateBT : ''
			    },
			    width: 375,
			    defaults: {
			    	width: 175
			    },
			    items: [
			            	new Ext.form.NumberField({fieldLabel: 'Nombre de publicité *',name: 'nb',allowBlank:false}),
							new Ext.form.DateField({fieldLabel: 'Jour de génération *',name: 'day',allowBlank:false,format:'d/m/Y'}),
						],
			    buttons: [{
			        text: 'Créer',
			        handler: function(){
		                var form = fp.getForm();
			            if (form.isValid()) {
			                form.submit(	{	url:'CreateBT.htm',
			                					waitMsg:'Enregistrement en cours ...',
			                					submitEmptyText: false,
			                					timeout:180,
			                					success: function(fp, o){
				                					var msg =  "Génération effectuée avec succès";
									                    Ext.Msg.show({
									                        title: "Génération BT",
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
								                    	title: "Génération BT",
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

})();

Ext.onReady(function(){
	if(!openLogin)objectJs = new Ext.blindtest.Create();
});
