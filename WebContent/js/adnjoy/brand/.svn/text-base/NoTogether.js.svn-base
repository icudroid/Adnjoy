Ext.ns('Ext.brand');

(function(){

	var thisObject;
	var selectBrand;
	var selectNoBrand;
	var brandStore;
	var noTogetherBrandStore;
	var windowFrom;
	var filterField;
	
	
	Ext.brand.NoTogether = function() {
		this._init();
	};

	Ext.brand.NoTogether.prototype = {
	
		_showAddBrand : function(){
			
			document.getElementById("panel").innerHTML = "";
			
			var items =	[
						         	new Ext.form.Hidden({name:'brandId',value:selectBrand}),
						         	new Ext.form.ComboBox(
											{	fieldLabel: 'Compagnie',
												name: 'filterBrand',
												allowBlank:false,
												emptyText:'Selectionner...',
												store:brandStore,
												mode: 'local',
												forceSelection: true,
												displayField:'name',
												valueField: 'id',
												hiddenName : 'idNoBrand'
											})
									];
		
			
			
			var fp = new Ext.FormPanel({
		        width: 500,
		        frame: true,
		        title : 'Choix de la compagnie',
		        autoHeight: true,
		        bodyStyle: 'padding: 10px 10px 0 10px;',
		        labelWidth: 150,
		        defaults: {
		            anchor: '95%',
		            msgTarget: 'side'
		        },
			    items: items,
			    buttons: [
			              {
					        text: 'Valider',
					        handler: function(){
				                var form = fp.getForm();
					            if (form.isValid()) {
					                form.submit(	{	url:'NoTogetherBrand.htm?addNoTogetherBrand',
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
											                    		noTogetherBrandStore.load({params:{brandId:selectBrand}});
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
		},
			
		_init : function(){
			
			thisObject = this;
			
			brandStore = new Ext.data.JsonStore({
				autoDestroy: true,
			    totalProperty: 'results',
			    root: 'rows',
				idProperty: 'id',
				storeId: 'BrandStore',
				url:'NoTogetherBrand.htm?getBrands',
				fields: ['id', 'logoFile','name','logoId','login','password','email','phone','fax']			
		    });
			
			brandStore.load();
			
			noTogetherBrandStore = new Ext.data.JsonStore({
				autoDestroy: true,
			    totalProperty: 'results',
			    root: 'rows',
				idProperty: 'id',
				storeId: 'NoTogetherBrandStore',
				url:'NoTogetherBrand.htm?getNoTogetherBrands',
				fields: ['id', 'logoFile','name','logoId','login','password','email','phone','fax']			
		    });
			
			
			filterField = new Ext.form.ComboBox(
					{	fieldLabel: 'Compagnie',
						name: 'filterBrand',
						//allowBlank:false,
						emptyText:'Selectionner...',
						store:brandStore,
						mode: 'local',
						forceSelection: true,
						displayField:'name',
						valueField: 'id',
						hiddenName : 'selectedBrand',
						listeners:{
							scope: this,
							select: function(combo,obj){
								selectBrand = obj.data.id;
								noTogetherBrandStore.load({params:{brandId:obj.data.id}});
							}
				    	}
					});
			
			
			var gridForm = new Ext.FormPanel({
				renderTo:'gridBrand',
			    title: 'Gestion des Compagnies (NO TOGETHER) ',
			    labelWidth: 150,
			    labelAlign: 'right',
			    layout: 'form',
			    padding: 15,
			    items:[			
						filterField,
						new Ext.grid.GridPanel(
						{
			                title: 'Liste des compagnie à ne pas afficher en même temps',
			                store: noTogetherBrandStore,
			                height: 450,
			                sm: new Ext.grid.RowSelectionModel({
			                    listeners: {
			                        rowselect: function(sm, row, rec) {
			                			selectNoBrand = rec.data.id;
			                        }
			                    }
			                }),
			                columns:[
				 	            {dataIndex :'name',header: "Nom",width:100}
			                ],
			                tbar: {
			                    xtype: 'toolbar',
			                    items: [
			                        {
			                            xtype: 'button',
			                            text: 'Ajouter',
		                            	handler: function(){
		                        			thisObject._showAddBrand();		
			                        	}
			                        },
			                        {
			                            xtype: 'button',
			                            text: 'Supprimer',
			                            handler: function(){
			                        		if(selectNoBrand){
			                        			gridForm.getForm().submit(
			                        					{
			                        						url:'NoTogetherBrand.htm?deleteNoTogetherBrand',
			                        						waitMsg:'Supression en cours',params:{brandId:selectBrand,idNoBrand:selectNoBrand},
			                        						success: function(fp, o){
											                    Ext.Msg.show({
											                        title: "Information",
											                        msg: "Suppression effectué",
											                        minWidth: 300,
											                        modal: true,
											                        icon: Ext.Msg.INFO,
											                        buttons: Ext.Msg.OK,
											                        fn:	function(){
											                    		noTogetherBrandStore.load({params:{brandId:selectBrand}});
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
	if(!openLogin)new Ext.brand.NoTogether();
});	
