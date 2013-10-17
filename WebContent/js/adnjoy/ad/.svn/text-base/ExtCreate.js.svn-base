Ext.ns('Ext.Ad');

(function(){

	var thisObject;
	var selectBrand;
	var selectAd;
	var brandStore;
	var adStore;
	var windowFrom;
	var filterField;
	var urlAd;
	var player;
	
	Ext.Ad.Create = function() {
		this._init();
	};

	Ext.Ad.Create.prototype = {
	
		play : function(){
			player.play(urlAd);
		},
			
		_updateWindow : function(id){
		
			document.getElementById("panel").innerHTML = "";
			
			var typeStore = new Ext.data.JsonStore({
				autoDestroy: true,
			    totalProperty: 'results',
			    root: 'rows',
				idProperty: 'id',
				storeId: 'TypeStore',
				url:'AddAd.htm?getTypes',
				fields: ['id', 'code','name']			
		    });
			
			typeStore.load();
			
			var items; 
			if(id){
				items =	[
				         	new Ext.form.Hidden({name:'id',value:id}),
				         	new Ext.form.Hidden({name:'brandId',value:selectBrand}),
				         	new Ext.form.TextField({fieldLabel: 'Compagnie *',name: 'brandName',allowBlank:false,readOnly:true}),
				         	new Ext.form.TextField({fieldLabel: 'Text *',name: 'logoText'}),
			            	new Ext.form.ComboBox(
	    							{	fieldLabel: 'Type *',
	    								name: 'typeName',
	    								emptyText:'Selectionner...',
	    								allowBlank:false,
	    								store:typeStore,
	    								mode: 'local',
	    								forceSelection: true,
	    								displayField:'name',
										valueField: 'id',
										hiddenName : 'typeId'
	    							}),
							new Ext.form.DateField({fieldLabel: 'Début *',name: 'startDate',allowBlank:false,format:'d/m/Y'}),
							new Ext.form.DateField({fieldLabel: 'Fin *',name: 'endDate',allowBlank:false,format:'d/m/Y'}),
				         	new Ext.form.TextField({fieldLabel: 'UID *',name: 'uid',allowBlank:false}),
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
			            	new Ext.form.ComboBox(
	    							{	fieldLabel: 'Compagnie *',
	    								name: 'brandName',
	    								emptyText:'Selectionner...',
	    								allowBlank:false,
	    								store:brandStore,
	    								mode: 'local',
	    								forceSelection: true,
	    								displayField:'name',
										valueField: 'id',
										hiddenName : 'brandId'
	    							}),
				         	new Ext.form.TextField({fieldLabel: 'Text *',name: 'logoText'}),
			            	new Ext.form.ComboBox(
	    							{	fieldLabel: 'Type *',
	    								name: 'typeName',
	    								emptyText:'Selectionner...',
	    								allowBlank:false,
	    								store:typeStore,
	    								mode: 'local',
	    								forceSelection: true,
	    								displayField:'name',
										valueField: 'id',
										hiddenName : 'typeId'
	    							}),
							new Ext.form.DateField({fieldLabel: 'Début *',name: 'startDate',allowBlank:false,format:'d/m/Y'}),
							new Ext.form.DateField({fieldLabel: 'Fin *',name: 'endDate',allowBlank:false,format:'d/m/Y'}),
				         	new Ext.form.TextField({fieldLabel: 'UID *',name: 'uid',allowBlank:false}),
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
		        title : 'Modification de la Publicité',
		        autoHeight: true,
		        bodyStyle: 'padding: 10px 10px 0 10px;',
		        labelWidth: 150,
		        defaults: {
		            anchor: '95%',
		            msgTarget: 'side'
		        },
			    reader :  new Ext.data.JsonReader({
			        fields: [
								{name:'id'}, 
								{name : 'brandId',mapping:'brand.id'},
								{name : 'brandName',mapping:'brand.name'},
								{name:'logoText'},
								{name:'typeId',mapping:'type.id'},
								{name:'typeStr',mapping:'type.name'},
								{name:'duration'},
								{name:'startDate', type:'date', dateFormat:'Y-m-d'},
								{name:'endDate', type:'date', dateFormat:'Y-m-d'},
								{name:'uid',mapping:'uniqueId'},
								{name:'idVideo',mapping:'dlFile.id'},
								{name:'urlVideo',mapping:'dlFile.dlUrl'}
							]
			    }),
			    items: items,
			    buttons: [
			              {
					        text: 'Valider',
					        handler: function(){
				                var form = fp.getForm();
					            if (form.isValid()) {
					                form.submit(	{	url:'AddAd.htm?saveAd',
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
							url:'AddAd.htm?getAdById',
							waitMsg:'Chargement',
							params:{id:id}
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
				url:'AddAd.htm?getBrands',
				fields: ['id', 'logoFile','name','logoId','login','password','email','phone','fax']			
		    });
			
			brandStore.load();
			
			adStore = new Ext.data.JsonStore({
				autoDestroy: true,
			    totalProperty: 'results',
			    root: 'rows',
				idProperty: 'id',
				storeId: 'AdStore',
				url:'AddAd.htm?getAds',
				fields: [
					         'id', 
					         {name : 'brandId',mapping:'brand.id'},
					         {name : 'brandName',mapping:'brand.name'},
					         'logoText',
					         {name:'typeId',mapping:'type.id'},
					         {name:'typeStr',mapping:'type.name'},
					         'duration',
					         {name:'startDate', type:'date', dateFormat:'Y-m-d'},
					         {name:'endDate', type:'date', dateFormat:'Y-m-d'},
					         'uniqueId',
					         {name:'idVideo',mapping:'dlFile.id'},
					         {name:'urlVideo',mapping:'dlFile.dlUrl'}
				         ]
		    });
			
			adStore.load();
			
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
								adStore.load({params:{brandId:obj.data.id}});
							}
				    	}
					});
			
			
			var gridForm = new Ext.FormPanel({
				renderTo:'gridAd',
			    title: 'Gestion des publicités',
			    labelWidth: 150,
			    labelAlign: 'right',
			    layout: 'form',
			    padding: 15,
			    items:[			
						filterField,
						new Ext.grid.GridPanel(
						{
			                title: 'Liste des publicités',
			                store: adStore,
			                height: 450,
			                listeners:{
								rowdblclick : function(grid,row,e){
									thisObject._updateWindow(selectAd);    							
								}
							},
			                sm: new Ext.grid.RowSelectionModel({
			                    listeners: {
			                        rowselect: function(sm, row, rec) {
			                			selectAd = rec.data.id;
			                			urlAd =location.protocol + '//' + location.host+ '/AdnJoy/Download.htm?url='+rec.data.urlVideo;
			                        }
			                    }
			                }),
			                
			                columns:[
				 	            {dataIndex :'uniqueId',header: "UID",width:100},
				 	            {dataIndex :'brandName',header: "Compagnie",width:100},
				 	            {dataIndex :'startDate',header: "Début",width:100},
				 	            {dataIndex :'endDate',header: "Fin",width:100},
				 	            {dataIndex :'duration',header: "Durée pub(ms)",width:100},
				 	            {dataIndex :'typeStr',header: "Type",width:100},
				 	            {dataIndex :'logoText',header: "Text Logo",width:100}
			                ],
			                tbar: {
			                    xtype: 'toolbar',
			                    items: [
			                        {
			                            xtype: 'button',
			                            text: 'Voir la vidéo',
		                            	handler: function(){
			                        		player = new ADNJOY.widget.PlayerPanel('../swf/AdVideo.swf');
			                        	}
			                        },

			                        {
			                            xtype: 'button',
			                            text: 'Nouveau',
		                            	handler: function(){
		                        			thisObject._updateWindow();		
			                        	}
			                        }
			                        ,
			                        {
			                            xtype: 'button',
			                            text: 'Supprimer',
			                            handler: function(){
			                        		if(selectAd){
			                        			gridForm.getForm().submit(
			                        					{
			                        						url:'AddAd.htm?deleteAd',
			                        						waitMsg:'Supression en cours',params:{id:selectAd},
			                        						success: function(fp, o){
											                    Ext.Msg.show({
											                        title: "Information",
											                        msg: "Suppression effectué",
											                        minWidth: 300,
											                        modal: true,
											                        icon: Ext.Msg.INFO,
											                        buttons: Ext.Msg.OK,
											                        fn:	function(){
											                    		adStore.load({params:{filterBrand:filterField.getValue()}});
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
	if(!openLogin)AdCreateObj = new Ext.Ad.Create();
});	
