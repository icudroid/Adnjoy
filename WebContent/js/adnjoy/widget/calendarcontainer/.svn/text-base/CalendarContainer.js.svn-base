YAHOO.widget.CalendarContainer = function(container,title) {
    this._init(container,title);
};

YAHOO.widget.CalendarContainer.prototype = {
		dialog : null,
		calendar : null,
		container : null,
		containerCalendar : null,
		containerDialog : null,
		idDate : null,
		idBtShow : null,
		eventSelected : null,
		selectedDate : null,
		
		_init : function(container,title){
			this.container =container;
			this.containerCalendar = "Calendar"+new Date().getTime();
			this.containerDialog = "Dialog"+new Date().getTime();
			this.idDate = "date"+new Date().getTime();
			this.idBtShow = "BtShow"+new Date().getTime();
			this.eventSelected = new YAHOO.util.CustomEvent("onDateSelected",this);
			
			document.getElementById(this.container).className = "box";
			var eltDatefield = document.createElement("div");
			eltDatefield.className = "datefield";
			eltDatefield.innerHTML = "<label for='"+this.idDate+"'>"+title+" : </label><input type='text' id='"+this.idDate+"' name='"+this.idDate+"' value='' readOnly='true'/><button type='button' id='"+this.idBtShow+"' title='Show Calendar'><img src='includes/calbtn.gif' width='18' height='18' alt='Calendar' ></button>";
			
			var eltContainerDialog = document.createElement("div");
			eltContainerDialog.id = this.containerDialog;
			eltContainerDialog.className = "containerDialog";
			
			var elthd = document.createElement("div");
			elthd.className = "hd";
			elthd.innerHTML = "Calendar";
			eltContainerDialog.appendChild(elthd);

			var eltbd = document.createElement("div");
			eltbd.className = "bd";
			eltContainerDialog.appendChild(eltbd);
			
			var eltContainerCalendar = document.createElement("div");
			eltContainerCalendar.id = this.containerCalendar;
			eltContainerCalendar.className = "containerCalendar";
			eltbd.appendChild(eltContainerCalendar);
			
			document.getElementById(this.container).appendChild(eltDatefield);
			document.getElementById(this.container).appendChild(eltContainerDialog);
	
			this.calendar = new YAHOO.widget.Calendar(this.containerCalendar, {
		        hide_blank_weeks:true  // Enable, to demonstrate how we handle changing height, using changeContent
		    });
			
		      this.dialog = new YAHOO.widget.Dialog(this.containerDialog, {
		            context:[this.idDate, "tl", "bl"],
		            buttons:[ {text:"Select", isDefault:true, handler: this.okHandler}, 
		                      {text:"Cancel", handler: this.cancelHandler}],
		            width:"16em",  // Sam Skin dialog needs to have a width defined (7*2em + 2*1em = 16em).
		            draggable:false,
		            close:true
		        });
		      this.dialog.expando = this;
		        this.calendar.render();
		        this.dialog.render();

		        // Using dialog.hide() instead of visible:false is a workaround for an IE6/7 container known issue with border-collapse:collapse.
		        this.dialog.hide();

		        this.calendar.renderEvent.subscribe(function() {
		            // Tell Dialog it's contents have changed, Currently used by container for IE6/Safari2 to sync underlay size
		            dialog.fireEvent("changeContent");
		        });

		        YAHOO.util.Event.on(this.idBtShow, "click", function() {
		        	this.dialog.show();
		        },this,true);

		},
		
		
		okHandler : function () {
			
	        if (this.expando.calendar.getSelectedDates().length > 0) {
	            var selDate = this.expando.calendar.getSelectedDates()[0];
	            this.expando.selectedDate = ((selDate.getDate()<10)?"0"+selDate.getDate():selDate.getDate())
				+ "/" 
				+ ((selDate.getMonth()+1<10)?"0"+(selDate.getMonth()+1):(selDate.getMonth()+1))
				+ "/" + selDate.getFullYear();
	            YAHOO.util.Dom.get(this.expando.idDate).value = this.expando.selectedDate;
	        } else {
	            YAHOO.util.Dom.get(this.expando.idDate).value = "";
	        }
	        this.hide();
	        this.expando.eventSelected.fire();
	    },
	    
	    cancelHandler : function () {
	        this.hide();
	    }

};