Ext.ns('Ext.blindtest');

Ext.blindtest.Classement = function() {
	this._init();
};

Ext.blindtest.Classement.prototype = {
	_init : function(){

		var btnReplay  = new Ext.Button( {text: '<h1>Rejouer une partie</h1>', renderTo : 'replayBtn',id:'ReplayBtn',scale:'large'});
		btnReplay.on("click",function(){
			window.location.href = "BlindTest.htm";
		});
		
//		var panel = new Ext.Panel({
//			padding:15, 
//			applyTo:'panelResult'
//		});

		var panelLot = new Ext.Panel({
			padding:15, 
			applyTo:'lotPanel'
		});

		
		var store = new Ext.data.JsonStore({
		    // store configs
		    autoDestroy: true,
		    baseParams: {
				jackpot : idJackPot,
				getClassement:''
		    },
		                
		    url: 'Classment.htm',
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
//	        width:920,
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
	
	}
	

};

Ext.onReady(function(){
	if(!openLogin)new Ext.blindtest.Classement();
});