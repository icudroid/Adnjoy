Ext.ns('Ext.jackpot');

Ext.jackpot.Classement = function() {
	this._init();
};

Ext.jackpot.Classement.prototype = {
	_init : function(){
		/*var panel = new Ext.Panel({
			padding:15, 
			applyTo:'panelResult'
		});*/
	
		var panelLot = new Ext.Panel({
			padding:15, 
			applyTo:'lotPanel'
		});
		
		var panelJackPot;
		
		if(document.getElementById("jackPotResult")!=null){
			var panelJackPot = new Ext.Panel({
				padding:15, 
				applyTo:'jackPotResult'
			});
			var btnJackPot  = new Ext.Button( {text: '<h1>Jouer</h1>', renderTo : 'playJackpot',scale:'large'});
			btnJackPot.on("click",this._openJackPot,this);
		}		
	
		
		var store = new Ext.data.JsonStore({
		    // store configs
		    autoDestroy: true,
		    baseParams: {
				jackpot : idJackPot,
				getClassement:''
		    },
		                
		    url: 'ClassmentJP.htm',
		    storeId: 'myStore',
		    totalProperty: 'results',
		    root: 'rows',
		    fields: ['position', 'pseudo', 'score']
		});
		
		
	    var grid = new Ext.grid.GridPanel({
	    	collapsible:true,
	    	collapsed :true,
	        store: store,
	        columns: [
	            {id:'position',header: "Rang",sortable: true,width:180},
	            {id: "pseudo", header: "Pseudo",sortable: true,width:550},
	            {id: "score",header: "Score",sortable: true,width:120,renderer: Ext.util.Format.numberRenderer('0.000')}
	        ],
	        stripeRows: true,
	        height:500,
	//        width:920,
	        frame:true,
	        title:'Classement général',
	
	        bbar: new Ext.PagingToolbar({
	            pageSize: 25,
	            store: store,
	            displayInfo: true,
	
	            plugins: new Ext.ux.ProgressBarPager()
	        })
	    });
		
	    grid.render('panelClassement');
	
	    store.load({params:{start:0, limit:25}});
	    
//	    panel.show(this);
	    panelLot.show(this);
	    if(panelJackPot)panelJackPot.show(this);
	
	},
	
	_openJackPot : function(e){
		//création du Panel
		new Ext.tvjackpot.JackPotPanel(idJackPot);
	}
	

};

Ext.onReady(function(){
	if(!openLogin)new Ext.jackpot.Classement();
});