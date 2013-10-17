Ext.ns('Ext.brand');

(function(){

	var thisObject;
	var selectBrand;
	var brandStore;
	var windowFrom;
	var filterField;
	
	Ext.brand.Create = function() {
		this._init();
	};

	Ext.brand.Create.prototype = {
	
		_updateWindow : function(id){
		
			document.getElementById("panel").innerHTML = "";
			
			var items; 
			if(id){
				items =	[
				         	new Ext.form.Hidden({name:'id',value:id}),
				         	new Ext.form.Hidden({name:'logoId'}),
				         	new Ext.form.TextField({fieldLabel: 'Nom *',name: 'name',allowBlank:false,readOnly:true}),
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
		        title : 'Modification de la compagnie',
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
					                form.submit(	{	url:'AddBrand.htm?saveBrand',
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
											                    		brandStore.load({params:{filterBrand:filterField.getValue()}});
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
							url:'AddBrand.htm?getBrandById',
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
			
			brandStore = new Ext.data.JsonStore({
				autoDestroy: true,
			    totalProperty: 'results',
			    root: 'rows',
				idProperty: 'id',
				storeId: 'BrandStore',
				url:'AddBrand.htm?getBrands',
				fields: ['id', 'logoFile','name','logoId','login','password','email','phone','fax']			
		    });
			
			brandStore.load();
			
			filterField = new Ext.form.TextField({
				name : 'filterBrand',
				fieldLabel : 'filtre',
				enableKeyEvents : true,
				listeners :{
					keyup : function(txtField,e){
						brandStore.load({params:{filterBrand:txtField.getValue()}});
					}
				}
			});
			
			var gridForm = new Ext.FormPanel({
				renderTo:'gridBrand',
			    title: 'Gestion des Compagnies',
			    labelWidth: 150,
			    labelAlign: 'right',
			    layout: 'form',
			    padding: 15,
			    items:[			
						filterField,
						new Ext.grid.GridPanel(
						{
			                title: 'Liste des compagnie',
			                store: brandStore,
			                height: 450,
			                listeners:{
								rowdblclick : function(grid,row,e){
									thisObject._updateWindow(selectBrand);    							
								}
							},
			                sm: new Ext.grid.RowSelectionModel({
			                    listeners: {
			                        rowselect: function(sm, row, rec) {
			                			selectBrand = rec.data.id;
			                        }
			                    }
			                }),
			                columns:[
				 	            {dataIndex :'name',header: "Nom",width:100},
				 	            {dataIndex :'login',header: "Login",width:100},
				 	            {dataIndex :'email',header: "Email",width:100},
				 	            {dataIndex :'phone',header: "Téléphone",width:100},
				 	            {dataIndex :'fax',header: "Fax",width:100}
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
			                        		if(selectBrand){
			                        			gridForm.getForm().submit(
			                        					{
			                        						url:'AddBrand.htm?deleteBrand',
			                        						waitMsg:'Supression en cours',params:{id:selectBrand},
			                        						success: function(fp, o){
											                    Ext.Msg.show({
											                        title: "Information",
											                        msg: "Suppression effectué",
											                        minWidth: 300,
											                        modal: true,
											                        icon: Ext.Msg.INFO,
											                        buttons: Ext.Msg.OK,
											                        fn:	function(){
											                    		brandStore.load({params:{filterBrand:filterField.getValue()}});
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
	if(!openLogin)new Ext.brand.Create();
});	
