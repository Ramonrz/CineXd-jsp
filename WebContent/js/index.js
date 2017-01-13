
'use strict';

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Fudeu foi tudo"); } }

$('[data-toggle="tooltip"]').tooltip();
var HallStorage = {
	totalTickets: 0,
	ticket: {},
	summ: 0
};

var HallActions = function () {
	function HallActions(data) {
		_classCallCheck(this, HallActions);

		this.storage = data;
	}

	HallActions.prototype.addTicket = function addTicket(ticket) {
		var id = ticket.row + '-' + ticket.tribune;

		if (id in this.storage.ticket) {
			this.storage.ticket[id].push(ticket);
		} else {
			this.storage.ticket[id] = [];
			this.storage.ticket[id].push(ticket);
		}
		++this.storage.totalTickets;
		this.storage.summ += ticket.price;
		this.rerender();
		return this;
	};

	HallActions.prototype.removeTicket = function removeTicket(ticket) {
		var id = ticket.row + '-' + ticket.tribune;

		this.storage.ticket[id].splice(this.storage.ticket[id].indexOf(ticket.place), 1);

		if (this.storage.ticket[id].length === 0) {
			delete this.storage.ticket[id];
		}

		--this.storage.totalTickets;
		this.storage.summ += ticket.price;
		this.rerender();
		return this;
	};

	HallActions.prototype.validate = function validate() {
		/*if (this.storage.totalTickets >= 5) {
			return false;
		} else {
			return true;
		}*/
	};

	HallActions.prototype._renderRow = function _renderRow(places) {
		
		var string = '<div class="hall-buy__places-row"><div class="hall-buy__places-row-num">Fileira :<span class="hall-buy__places-row-value">' + places[0].row + '<input type="hidden" name="linha[]" value="' + places[0].row + '" /></span></div>';
		var arr = [];
		for (var key in places) {
			arr.push(places[key].place);
		}
		string += '<div class="hall-buy__places-row-num">Poltrona :<span class="hall-buy__places-row-value">' + arr.join(', ') + '<input type="hidden" name="poltrona[]" value="' + arr.join(', ') + '" /></span></div></div>';
		return string;
	};

	HallActions.prototype.rerender = function rerender() {
		if (!$.isEmptyObject(this.storage.ticket)) {
			$('#hallNoData').addClass('hidden');
			$('#hallData').removeClass('hidden');
		} else {
			$('#hallNoData').removeClass('hidden');
			$('#hallData').addClass('hidden');
		}

		
		$('#hallCountTickets').html(this.storage.totalTickets + ' Bilhete(s)');
		$('#quantidade').val(this.storage.totalTickets);
		$('#hallTotalSum').html(this.storage.summ );
		var html = '';
		
		for (var ticket in this.storage.ticket) {
			html += this._renderRow(this.storage.ticket[ticket]);
		}
		$('#hallPlaces').html(html);
		return this;
	};

	return HallActions;
}();

var hall = new HallActions(HallStorage);

var $hall = $('#hall');
var $item = $('.hall__places-item', $hall);
var $itemFree = $('.hall__places-item.is-free', $hall);


var blur = function blur() {
	$('.hall__line', $hall).removeClass('is-hover');
	$('.hall__row').removeClass('is-dark');
};
console.log($item);
$item.on('mouseenter', function () {
	blur();
	
	$(this).parents('.hall__row').addClass('is-dark');
	var row = $(this).data('row');
	var tribune = $(this).data('tribune');
	$('#hallLine_' + row + '_' + tribune, $hall).addClass('is-hover');
});

$item.on('mouseout', blur);

$itemFree.on('click', function () {
	var ticket = {
		row: $(this).data('row'),
		tribune: $(this).data('tribune'),
		place: $(this).data('pos-x'),
			};
	if ($(this).hasClass('is-checked')) {
		$(this).removeClass('is-checked');
		hall.removeTicket(ticket);
	} else {
		
		$(this).addClass('is-checked');
		hall.addTicket(ticket);
	}
});
hall.rerender();

var cancelBooking = function cancelBooking() {
	
	$('#timeoutPopUp').removeClass('hidden');
};

$('select').select2({
	minimumResultsForSearch: -1,
	placeholder: 'selecionado'
});
window.cancelBooking = cancelBooking;