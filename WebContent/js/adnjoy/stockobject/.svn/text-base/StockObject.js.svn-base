Ext.ns('Ext.stockoject');

(function(){

	var selectSo;
	var selectBrand;
	var windowFrom;
	var thisObject;
	var SOStore;

	Ext.stockoject.Admin = function() {
		this._init();
	};
	
	Ext.stockoject.Admin.prototype = {
		
	_init : function(){
		thisObject = this;
		var brandStore = new Ext.data.JsonStore({
			autoDestroy: true,
		    totalProperty: 'results',
		    root: 'rows',
			idProperty: 'id',
			storeId: 'BrandStore',
			url:'StockObject.htm?getBrands',
			fields: ['id', 'logo','name']			
	    });
		
		brandStore.load();
		SOStore = new Ext.data.JsonStore({
			autoDestroy: true,
		    totalProperty: 'results',
		    root: 'rows',
			idProperty: 'id',
			storeId: 'SOStore',
			url:'StockObject.htm?getStockObjectsByBrand',
            fields: [
                     {name:'id',type:'int'},
                     {name: 'value',type: 'float'},
                     {name: 'name',type: 'string'},
                     {name: 'description',type: 'string'},
                     {name: 'dlFile',type:'int'},
                     {name: 'available',type: 'int'},
                     {name: 'brandId',type:'int'}
                    ]
			
	    });
		
		var gridForm = new Ext.FormPanel({
				renderTo:'StockPanel',
			    title: 'Gestion des stocks',
			    labelWidth: 150,
			    labelAlign: 'right',
			    layout: 'form',
			    padding: 15,
			    items:[
		            	new Ext.form.ComboBox(
    							{	fieldLabel: 'Compagnie',
    								name: 'brandName',
    								allowBlank:false,
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
    										SOStore.load({params:{brandId:obj.data.id}});
    									}
    						    	}
    							}),
    					new Ext.grid.GridPanel(
    						{
				                title: 'Liste des stocks',
				                store: SOStore,
				                height: 450,
				                listeners:{
    								rowdblclick : function(grid,row,e){
    									//affichage de la fenêtre de mise à jour
    									thisObject._updateWindow(selectSo);    							
    									//sm.deselectRow(row);
    								}
    							},
				                sm: new Ext.grid.RowSelectionModel({
				                    listeners: {
				                        rowselect: function(sm, row, rec) {
				                			selectSo = rec.data.id;
//				                			alert(selectSo);
				                            //Ext.getCmp("company-form").getForm().loadRecord(rec);
				                        }
				                    }
				                }),
				                columns:[
					 	            {dataIndex :'name',header: "Nom",width:680},
					 	            {dataIndex : "value",header: "Valeur",width:85},
					 	            {dataIndex : "available",header: "Dispo",width:150}
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
				                        		if(selectSo){
				                        			gridForm.getForm().submit(
				                        					{
				                        						url:'StockObject.htm?deleteStockObject',
				                        						waitMsg:'Chargement',params:{id:selectSo},
				                        						success: function(fp, o){
												                    Ext.Msg.show({
												                        title: "Information",
												                        msg: "Suppression effectué",
												                        minWidth: 300,
												                        modal: true,
												                        icon: Ext.Msg.INFO,
												                        buttons: Ext.Msg.OK,
												                        fn:	function(){
												                    		SOStore.load({params:{brandId:selectBrand}});
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

		
		

	},
	
	_updateWindow : function(idSo){
		document.getElementById("panel").innerHTML = "";
		
		var items; 
		if(idSo){
			items =	[
			         	new Ext.form.Hidden({name:'id',value:idSo}),
			         	new Ext.form.Hidden({name:'brandId',value:selectBrand}),
			         	new Ext.form.TextField({fieldLabel: 'Nom *',name: 'name',allowBlank:false}),
			         	new Ext.form.NumberField({fieldLabel: 'Disponible',name: 'available',readOnly:true}),
			         	new Ext.form.NumberField({fieldLabel: 'Ajouter *',name: 'addNb',allowBlank:false}),
			         	new Ext.form.NumberField({fieldLabel: 'Valeur *',name: 'value',allowBlank:false}),
			         	new Ext.form.TextArea({fieldLabel: 'Description *',name: 'description',allowBlank:false})
			         	,
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
			         	new Ext.form.Hidden({name:'id'}),
			         	new Ext.form.Hidden({name:'brandId',value:selectBrand}),
			         	new Ext.form.TextField({fieldLabel: 'Nom *',name: 'name',allowBlank:false}),
			         	new Ext.form.NumberField({fieldLabel: 'Disponible',name: 'available',allowBlank:false}),
			         	new Ext.form.NumberField({fieldLabel: 'Valeur *',name: 'value',allowBlank:false}),
			         	new Ext.form.TextArea({fieldLabel: 'Description *',name: 'description',allowBlank:false})
			         	,
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
	        title : 'Modification du stock',
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
		            {name: 'dlFile'},
		            {name: 'brandId'},
		            {name: 'name'},
		            {name: 'available'},
		            {name: 'value'},
		            {name: 'city'},
		            {name: 'pseudo'},
		            {name: 'description'}
		        ]
		    }),
		    items: items,
		    buttons: [
		              {
				        text: 'Valider',
				        handler: function(){
			                var form = fp.getForm();
				            if (form.isValid()) {
				                form.submit(	{	url:'StockObject.htm?saveStockObject',
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
										                    		SOStore.load({params:{brandId:selectBrand}});
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
		if(idSo != null){
			fp.getForm().load(
					{
						url:'StockObject.htm?getStockObjectsById',
						waitMsg:'Chargement',
						params:{id:idSo},
						success: function(fp, o){
							var url =location.protocol + '//' + location.host+ '/AdnJoy/Download.htm?url='+o.reader.jsonData.dlFile.dlUrl;
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
	}
			
	
	};

})();


Ext.onReady(function(){
	if(!openLogin)objectJs = new Ext.stockoject.Admin();
});
