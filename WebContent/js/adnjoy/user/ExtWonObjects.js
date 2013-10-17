Ext.ns('Ext.user');

Ext.user.WonObjects = function() {
	this._init();
};

Ext.user.WonObjects.prototype = {
	
	_init : function(){
	
		var store = new Ext.data.JsonStore({
		    // store configs
		    autoDestroy: true,
		    url: 'getWonObjects.htm',
		    storeId: 'myStore',
		    totalProperty: 'results',
		    root: 'rows',
		    idProperty: 'idWinObject',
		    fields: ['idWinObject', 'name', 'winOn',{name:'value', type: 'float'}, {name:'winDate', type:'date', dateFormat:'Y-m-d'},'status']
		});
		
		
	    var grid = new Ext.grid.GridPanel({
	        store: store,
	        columns: [
	            {id:'idWinObject',header: "Référence",width:100},
	            {id: "name", header: "Gain",width:300},
	            {id: "winOn",header: "Gagné sur",width:190},
	            {id: "value",header: "Valeur",width:100},
	            {id: "winDate",header: "Date", sortable: true, renderer: Ext.util.Format.dateRenderer('d/m/Y'), dataIndex: 'winDate',width:100},
	            {id: "status",header: "Statut",width:140}
	        ],
	        stripeRows: true,
	       // autoExpandColumn: 'company',
	        height:450,
	        frame:true,
	        title:'Récapitulatif de vos gains',

	        bbar: new Ext.PagingToolbar({
	            pageSize: 25,
	            store: store,
	            displayInfo: true,

	            plugins: new Ext.ux.ProgressBarPager()
	        })
	    });
		
	    grid.render('gains');

	    store.load({params:{start:0, limit:25}});
	}
	
};

Ext.onReady(function(){
	if(!openLogin)new Ext.user.WonObjects();
});