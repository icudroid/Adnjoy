Ext.ns('Ext.user');
(function(){

	var filter = "";
	var store;
	
	Ext.user.Friends = function() {
		this._init();
	};
	
	Ext.user.Friends.prototype = {
		
		_init : function(){
			this._construct();
		},
	
		_construct : function(){
			
			new Ext.Button({renderTo:'btn-add',text:'Ajouter un ami',handler:this.addFriends});
			
//			var addBtn = Ext.get("btn-add");
//			addBtn.on('click', this.addFriends, this);
			new Ext.Button({renderTo:'btn-invite',text:'Inviter un ami à s\'inscrire',handler:this.inviteFriends});
//			var invteBtn = Ext.get("btn-invite");
//			invteBtn.on('click', this.inviteFriends, this);

			var lookfor = Ext.get("filter-friends");
			lookfor.on('keyup', this.lookforFriends, this);
			
			Ext.Ajax.request({
				   url: 'FriendsGroup.htm',
				   success: this._fillFriendsList,
				   params: { listFriends: '' }
				});
		},
		
		_renderAvatar : function(args){
			
		},
		
		_renderValidate : function(args){
			
		},
		
		_fillFriendsList : function(data){
			var jsonData = Ext.util.JSON.decode(data.responseText);
			var nb = document.getElementById("nbFriends");
			nb.innerHTML = jsonData.results;
			
			store = new Ext.data.JsonStore({
			    // store configs
			    autoDestroy: true,
			    url: 'FriendsGroup.htm',
			    storeId: 'myStore',
			    totalProperty: 'results',
			    root: 'rows',
			    idProperty: 'id',
			    fields: ['id', 'pseudo', 'avatar','sexBean','validate']
			});
			
		    var grid = new Ext.grid.GridPanel({
		        store: store,
		        columns: [
		            {id: "avatar", header: "Avatar",width:80, renderer:this._renderAvatar },
		            {id: "pseudo",header: "Pseudo",width:400},
		            {id: "validate",header: "Validé", renderer: this._renderValidate, width:97},
		        ],
//		        stripeRows: true,
		       // autoExpandColumn: 'company',
		        height:450,
		        frame:true,
		        title:'Mes amis'
		    });
			
		    grid.render('list-friends');

		    store.load({params:{pseudoFilter:filter}});
			
			//mettre les buttons de controles
		},
		
		addFriends : function(e){
			document.getElementById("panel").innerHTML ="";
			
			var box = new Ext.Window({
				applyTo:'panel',
				closeAction:'hide',
				modal:true,
				padding:15,
				resizable:false,
				draggable:false,
				items :[
				        l
				        
				],
                buttons: [{
                    text:'Ajouter',
                    handler: function(){
            			box.hide();
                	}
                },{
                    text: 'Fermer',
                    handler: function(){
                		box.hide();
                    }
                }]
			});
			
			box.show(this);
		},
		
		inviteFriends : function(e){
			alert("inviteFriends");
		},
		
		lookforFriends : function(e){
			filter =document.getElementById('filter-friends').value;
			store.load({params:{pseudoFilter:filter}});
		}
	
		
	};
	
})();		

Ext.onReady(function(){
	new Ext.user.Friends();
});
