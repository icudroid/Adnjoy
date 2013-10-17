Ext.ns('Ext.chanel');

(function(){

	var thisObject;
	var selectChanel;
	var chanelStore;
	var windowFrom;
	var filterField;
	
	Ext.chanel.Create = function() {
		this._init();
	};

	Ext.chanel.Create.prototype = {
	
		_updateWindow : function(id){
			
		
			var countryStore = new Ext.data.JsonStore({
				autoDestroy: true,
			    totalProperty: 'results',
			    root: 'rows',
				idProperty: 'id',
				storeId: 'CountryStore',
				url:'AddChanel.htm?getCountries',
				fields: ['id', 'country']			
		    });
			
			countryStore.load();
			
			document.getElementById("panel").innerHTML = "";
			
			var items; 
			if(id){
				items =	[
				         	new Ext.form.Hidden({name:'id',value:id}),
				         	new Ext.form.Hidden({name:'logoId'}),
				         	new Ext.form.TextField({fieldLabel: 'Nom *',name: 'name',allowBlank:false,readOnly:true}),
				         	new Ext.form.TextField({fieldLabel: 'Pays *',name: 'countryName',allowBlank:false,readOnly:true}),
				         	new Ext.form.TextField({fieldLabel: 'Login *',name: 'login',allowBlank:false}),
				         	new Ext.form.TextField({fieldLabel: 'Mot de passe *',name: 'password',allowBlank:false,inputType:'password'}),
				         	new Ext.form.TextField({fieldLabel: 'Email *',name: 'email',allowBlank:false,vtype:'email'}),
				         	new Ext.form.TextField({fieldLabel: 'Téléphone',name: 'phone'}),
				         	new Ext.form.TextField({fieldLabel: 'Fax',name: 'fax'}),
				         	{
				                 xtype: 'fileuploadfield',
				                 id: 'form-file',
				                 emptyText: 'Sélectionnez une image',
				                 fieldLabel: 'Photo',
				                 name: 'fileBean',
				                 buttonText: '',
				                 buttonCfg: {
				                     iconCls: 'upload-icon'
				                 }
				             }
							];
			}else{
				items =	[
				         	new Ext.form.TextField({fieldLabel: 'Nom *',name: 'name',allowBlank:false}),
			            	new Ext.form.ComboBox(
	    							{	fieldLabel: 'Pays *',
	    								name: 'countryName',
	    								emptyText:'Selectionner...',
	    								allowBlank:false,
	    								store:countryStore,
	    								mode: 'local',
	    								forceSelection: true,
	    								displayField:'country',
										valueField: 'id',
										hiddenName : 'countryId'
	    							}),
				         	new Ext.form.TextField({fieldLabel: 'Login *',name: 'login',allowBlank:false}),
				         	new Ext.form.TextField({fieldLabel: 'Mot de passe *',name: 'password',allowBlank:false,inputType:'password'}),
				         	new Ext.form.TextField({fieldLabel: 'Email *',name: 'email',allowBlank:false,vtype:'email'}),
				         	new Ext.form.TextField({fieldLabel: 'Téléphone',name: 'phone'}),
				         	new Ext.form.TextField({fieldLabel: 'Fax',name: 'fax'}),
				         	{
				                 xtype: 'fileuploadfield',
				                 id: 'form-file',
				                 emptyText: 'Sélectionnez une image',
				                 fieldLabel: 'Photo',
				                 name: 'fileBean',
				                 buttonText: '',
				                 buttonCfg: {
				                     iconCls: 'upload-icon'
				                 }
				             }
						];
			}
			
			
			var fp = new Ext.FormPanel({
				fileUpload: true,
		        width: 500,
		        frame: true,
		        title : 'Modification de la chaîne',
		        autoHeight: true,
		        bodyStyle: 'padding: 10px 10px 0 10px;',
		        labelWidth: 150,
		        defaults: {
		            anchor: '95%',
		            msgTarget: 'side'
		        },
			    reader :  new Ext.data.JsonReader({
			        fields: [
			            {name: 'id'},
			            {name: 'logoFile'},
			            {name: 'logoId'},
			            {name: 'countryName',mapping : 'country.country'},
			            {name: 'countryId',mapping : 'country.id'},
			            {name: 'name'},
			            {name: 'login'},
			            {name: 'password'},
			            {name: 'email'},
			            {name: 'phone'},
			            {name: 'fax'}
			        ]
			    }),
			    items: items,
			    buttons: [
			              {
					        text: 'Valider',
					        handler: function(){
				                var form = fp.getForm();
					            if (form.isValid()) {
					                form.submit(	{	url:'AddChanel.htm?saveChanel',
					                					waitMsg:'Enregistrement en cours ...',
					                					submitEmptyText: false,
					                					success: function(fp, o){
											                    Ext.Msg.show({
											                        title: "Information",
											                        msg: "Enregistrement effectué",
											                        minWidth: 300,
											                        modal: true,
											                        icon: Ext.Msg.INFO,
											                        buttons: Ext.Msg.OK,
											                        fn:	function(){
											                    		chanelStore.load({params:{filterChanel:filterField.getValue()}});
											                    		windowFrom.hide();
											                    	}
											                    });
									                    },
									                    failure: function(fp, o){
									                    	var msg =  "Un problème est servenu sur le serveur veuillez recommencer ultérieurement.<br></br>";
										                    Ext.Msg.show({
										                    	title: "Enregistrement",
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
					    },
					    {
					        text: 'Annuler',
					        handler: function(){
					    		windowFrom.hide();
					    	}
					    }
					    
					    ]
			});
			
			windowFrom = new Ext.Window({
				applyTo : 'panel',
				closeAction: 'hide',
				modal:true,
				resizable:false,
				items:[fp]
			});
			
			windowFrom.show();
			if(id != null){
				fp.getForm().load(
						{
							url:'AddChanel.htm?getChanelById',
							waitMsg:'Chargement',
							params:{id:id},
							success: function(fp, o){
								var url =location.protocol + '//' + location.host+ '/AdnJoy/Download.htm?url='+o.reader.jsonData.logoFile;
								var myImage = new Ext.BoxComponent({
								    autoEl: {
								        tag: 'img',
								        src: url
								    }
								});
								windowFrom.add(myImage);
								windowFrom.doLayout();   
								windowFrom.center();
							}
						});
			}
			
			
		},
			
		_init : function(){
			thisObject = this;
			
			chanelStore = new Ext.data.JsonStore({
				autoDestroy: true,
			    totalProperty: 'results',
			    root: 'rows',
				idProperty: 'id',
				storeId: 'ChanelStore',
				url:'AddChanel.htm?getChanels',
				fields: ['id', 'logoFile','name','logoId','login','password','email','phone','fax',
				            {name: 'country',mapping:'country.id'},
				            {name: 'countryName',mapping:'country.country'}
				         ]			
		    });
			
			chanelStore.load();
			
			filterField = new Ext.form.TextField({
				name : 'filterChanel',
				fieldLabel : 'filtre',
				enableKeyEvents : true,
				listeners :{
					keyup : function(txtField,e){
						chanelStore.load({params:{filterChanel:txtField.getValue()}});
					}
				}
			});
			
			var gridForm = new Ext.FormPanel({
				renderTo:'gridChanel',
			    title: 'Gestion des Chaînes',
			    labelWidth: 150,
			    labelAlign: 'right',
			    layout: 'form',
			    padding: 15,
			    items:[			
						filterField,
						new Ext.grid.GridPanel(
						{
			                title: 'Liste des chaînes de télévision',
			                store: chanelStore,
			                height: 450,
			                listeners:{
								rowdblclick : function(grid,row,e){
									thisObject._updateWindow(selectChanel);    							
								}
							},
			                sm: new Ext.grid.RowSelectionModel({
			                    listeners: {
			                        rowselect: function(sm, row, rec) {
			                			selectChanel = rec.data.id;
			                        }
			                    }
			                }),
			                columns:[
				 	            {dataIndex :'name',header: "Nom",width:100},
				 	            {dataIndex :'login',header: "Login",width:100},
				 	            {dataIndex :'email',header: "Email",width:100},
				 	            {dataIndex :'phone',header: "Téléphone",width:100},
				 	            {dataIndex :'fax',header: "Fax",width:100},
				 	            {dataIndex : "countryName",header: "Pays",width:100}
			                ],
			                tbar: {
			                    xtype: 'toolbar',
			                    items: [
			                        {
			                            xtype: 'button',
			                            text: 'Nouveau',
		                            	handler: function(){
		                        			thisObject._updateWindow();		
			                        	}
			                        },
			                        {
			                            xtype: 'button',
			                            text: 'Supprimer',
			                            handler: function(){
			                        		if(selectChanel){
			                        			gridForm.getForm().submit(
			                        					{
			                        						url:'AddChanel.htm?deleteChanel',
			                        						waitMsg:'Supression en cours',params:{id:selectChanel},
			                        						success: function(fp, o){
											                    Ext.Msg.show({
											                        title: "Information",
											                        msg: "Suppression effectué",
											                        minWidth: 300,
											                        modal: true,
											                        icon: Ext.Msg.INFO,
											                        buttons: Ext.Msg.OK,
											                        fn:	function(){
											                    		chanelStore.load({params:{filterChanel:filterField.getValue()}});
											                    	}
											                    });
											                    },
											                    failure: function(fp, o){
											                    	var msg =  "Un problème est servenu sur le serveur veuillez recommencer ultérieurement.<br></br>";
												                    Ext.Msg.show({
												                    	title: "Suppression",
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
			                        }
			                    ]
			                }
						})
						]
			});
		}
		
	};

})();


Ext.onReady(function(){
	if(!openLogin)new Ext.chanel.Create();
});	
