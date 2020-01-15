// JavaScript Document final proj demo
var root = "http://comp426.cs.unc.edu:3001/";

$(document).ready(function () {
	$("body").css('background-image', 'url(airlinepagebg.jpg)');
	$("body").css('background-size', 'cover');

	$('#login_btn').on('click', function () {

		let user = $('#login_user').val();
		let pass = $('#login_pass').val();

		console.log(user);
		console.log(pass);

		$.ajax(root + 'sessions', {
			type: 'POST',
			xhrFields: {
				withCredentials: true
			},
			data: {
				username: user,
				password: pass
			},
			success: (response) => {
				if (response.status) {
					buildFlightInterface();
				} else {
					$('#mesg_div').html("Login failed. Try again.");
				}
			},
			error: () => {
				alert('error');
			}
		});
	});

	$('#create_user').on('click', function () {
		let user = $('#newuser').val();
		let pass = $('#newpass').val();

		console.log(user);
		console.log(pass);

		alert(user + " " + pass);
		$.ajax({
			url: root + "users",
			type: 'POST',
			dataType: 'json',
			xhrFields: {
				withCredentials: true
			},
			data: {
				"user": {
					"username": user,
					"password": pass
				}
			},
			success: (response) => {
				if (response.status) {
					alert("User account created!");
				} else {
					$('#mesg_div').html("Login failed. Try again.");
				}
			},
			error: () => {
				alert('error');
			}
		});

	});

	$('#interface').on('click', function () {
		let body = $('body');
		body.empty();
		buildFlightInterface();
	});
});

//Builds the "plan your flight" interface; rn this is the main page, I plan to add a homepage tho
var eventCont = "";
var buildFlightInterface = function () {
	let body = $('body');

	$("body").css('background-image', '');
	$("body").css('background-size', '');
	body.append("<div class='footer'><button id='logout'>Log Out</button></div>");
	body.append("<h1 id='title'>Plan Your Flight</h1><br>");
	body.append("<nav id='navbar'> <ul><li id='flights'>Flights</li><li id='hotels'>Hotels</li><li id='program'>Your Program</li></ul></nav>");
	$("#hotels").on('click', function () {
		buildHotelsInterface();
	});
	$("#program").on('click', function() { //ADDS PROGRAM INTERFACE
		buildProgramInterface();
	});
	
//	$("#flights").on('click', function () {
//		buildFlightInterface();
//	});
	body.append("<div class='grid-container'>");

	$(".grid-container").append("<div class='airlines' id='grid-button'>Airlines</div>");
	$(".airlines").attr("onclick", "airlinesInterface()");
	$(".grid-container").append("<div class='airports' id='grid-button'>Airports</div>");
	$(".airports").attr("onclick", "airportsInterface()");
	$(".grid-container").append("<div class='flights' id='grid-button'>Flights</div>");
	$(".flights").attr("onclick", "flightsInterface()");
	//	$(".grid-container").append("<div class='instances' id='grid-button'>Instances</div>");
	//	$(".grid-container").append("<div class='planes' id='grid-button'>Planes</div>");
	//	$(".grid-container").append("<div class='seats' id='grid-button'>Seats</div>");
	$(".grid-container").append("</div>");

	body.append("<div class='current_program'><p>Current Program:</p></div>");
	$(".current_program").append("<div class='cp'><p id='cp_destination' class='cp_details'>Destination:</p><p class='cp_details'>Travel Date:</p><p class='cp_details'>Return Date:</p><p id='cp_hotel' class='cp_details'>Hotel Reservation:</p>");

	$(".current_program").append("<p id='cp_todolist' class='cp_details'>To-Do List:</p>");
	$(".current_program").append(eventCont);
	todoList = $('#cp_todolist').val();
	body.append("<div class='program-container'></div>");
	
	
};

//Builds the airline select page
var airlinesInterface = function () {
	let body = $('body');
	body.empty();
	$("body").css('background-image', '');
	$("body").css('background-size', '');
	body.append("<button id='flights_page'>Back to Flights</button>");
	$("#flights_page").on('click', function () {
		body.empty();
		buildFlightInterface();
	});
	body.append("<div id='head'><h1 id='list'>Add an Airline</h1><h1 id='selected'>Selected Airline ID</h1></div>");

	$("#list").append("<br><div class='buttonwrapper'><p>Looking for a specific airline?</p><input placeholder='Type the airline here' id='name_box' type='text' class='input'></div>");
	$("#selected").append("<br><div id='id'>id:</div>");

	$('#name_box').keyup(function () {
		var substring = $(this).val().toLowerCase();
		$("body").find("li").each(function () {
			var text = $(this).text().toLowerCase();
			return (text.indexOf(substring) >= 0) ? $(this).show() : $(this).hide();
		});

	});

	let airline_list = $("<ul id='airlines_list'></ul>");
	$("#list").append(airline_list);


	$.ajax(root + "airlines", {
		type: 'GET',
		xhrFields: {
			withCredentials: true
		},
		success: (airlines) => {
			for (let i = 0; i < airlines.length; i++) {
				var id = airlines[i].id;
				airline_list.append("<li onclick='get_ID(" + id + ")'>" + airlines[i].name + "</li>");
			}
		}
	});

	$("#list").append("<div id='airlineAdd'><p>Don't see your airline? Add it here:</p><input placeholder='Type the airline's name here' id='airlineAdd_box' type='text' class='input'>" + "<button id='make_airline'>Add</button></div>");

	$('#make_airline').on('click', () => {
		let airline_name = $('#airlineAdd_box').val();
		$('#airlineAdd_box').val("");
		$.ajax(root + "airlines", {
			type: 'POST',
			data: {
				airline: {
					name: airline_name
				}
			},
			xhrFields: {
				withCredentials: true
			},
			success: (airline) => {
				var id = airline.id;
				airline_list.append("<li onclick='get_ID(" + id + ")'>" + airline.name + "</li>");
			}
		});
	});

	body.append("<div id='paper-airplane'><img src='paper-airplane.gif'></div>");
};

function get_ID(id) {
	console.log(id);
	document.getElementById('id').innerHTML = " ";
	document.getElementById('id').append(" " + id);
}

//builds the airport select page
var airportsInterface = function () {
	let body = $('body');
	body.empty();
	$("body").css('background-image', '');
	$("body").css('background-size', '');
	body.append("<div id='container'><div id='airport_list'></div><div id='airport_map'></div></div>");
	$("#airport_list").append("<h1>Add an Airport</h1>");
	$("#airport_list").append("<div class='buttonwrapper'><p>Looking for a specific airport?</p><input placeholder='Type your airport code' id='code_box' type='text' class='input'><button id='code_btn'>Go</button></div>");

	$("#airport_list").append("<div><p>Or, start typing the name below:</p><input placeholder='Type the airport here' id='name_box' type='text' class='input'></div>");


	$('#name_box').keyup(function () {
		var substring = $(this).val().toLowerCase();
		$("body").find("li").each(function () {
			var text = $(this).text().toLowerCase();
			return (text.indexOf(substring) >= 0) ? $(this).show() : $(this).hide();
		});

	});

	let airport_list = $("<ul id='airlines_list'></ul>");
	$("#airport_list").append(airport_list);

	$("#code_btn").on('click', function () {
		var entered_code = $("#code_box").val();
		airport_list.empty();
		$.ajax(root + "airports?filter[code]=" + entered_code, {
			type: 'GET',
			xhrFields: {
				withCredentials: true
			},
			success: (airports) => {
				console.log(airports);
				for (let i = 0; i < airports.length; i++) {
					airport_list.append("<li>" + airports[i].name + ", " + airports[i].code + "</li>");
				}

			}
		});
	});



	$("#airport_map").append("<h1>Location of Airport:</h1><p>(click on an airport to see its location)</p>");
	$("#airport_map").append("<div id='map'></div>");

	//$("#airport_map").append(addMap(35, -79));


	body.append("<button id='flights_page'>Back to Flights</button>");
	$("#flights_page").on('click', function () {
		body.empty();
		buildFlightInterface();
	});


	$.ajax(root + "airports", {
		type: 'GET',
		xhrFields: {
			withCredentials: true
		},
		success: (airports) => {
			for (let i = 0; i < airports.length; i++) {
				var x = airports[i].latitude;
				var y = airports[i].longitude;
				airport_list.append("<li onclick='getCoords(" + x + "," + y + ")'>" + airports[i].name + ", " + airports[i].code + "</li>");
			}

		}
	});
	$("#airport_list").append("<div id='airportAdd'><p>Don't see your airport? Add it here:</p><input placeholder='Type the airport's name here' id='airportAdd_box' type='text' class='input'>" + "<input placeholder='Type the code here' id='airportAddCode_box' type='text' class='input'>" + "<button id='make_airport'>Add</button></div>");
	
	$('#make_airport').on('click', () => {
		let airport_name = $('#airportAdd_box').val();
		let airport_code = $('#airportAddCode_box').val();
		$('#airportAdd_box').val("");
		$.ajax(root + "airports", {
			type: 'POST',
			data: {
				airport: {
					name: airport_name,
					code: airport_code
				}
			},
			xhrFields: {
				withCredentials: true
			},
			success: (airport) => {
				var id = airport.id;
				airport_list.append("<li>" + airport.name + ", " + airport.code + "</li>");
			}
		});
	});
};

function getCoords(x, y) {
	console.log(x, y);
	document.getElementById('map').append(addMap(x, y));
}

//build flightsInterface
let airline_list;
var flightsInterface = function () {
	let body = $('body');
	body.empty();
	$("body").css('background-image', '');
	$("body").css('background-size', '');
	body.append("<div id='container'><h1>Find Your Flight</h1>");
	$("#container").append("<div id='airline_div'>What airline?</div>");
	$("#airline_div").append("<div id='airline_dropdown' class='dropdown-content'><input type='text' placeholder='Search Airlines' id='myInput' onclick='showAirlines()' onkeyup='filterFunction()'></div>");
	airline_list = $("<ul id='airlines_dropdown_list' class='dropdown_list'></ul>");
	
	document.getElementById('airline_dropdown').append(airline_list);
	
	$.ajax(root + "airlines", {
		type: 'GET',
		xhrFields: {
			withCredentials: true
		},
		success: (airlines) => {
			for (let i = 0; i < airlines.length; i++) {
				var id = airlines[i].id;
				airline_list.append("<li>" + airlines[i].name + "</li>");
			}
		}
	});

	$("#container").append("<div id='fromAirport_div'>From which airport?</div>");
	$("#fromAirport_div").append("<div id='airport_dropdown' class='dropdown-content'><input type='text' placeholder='Search Airports' id='myInput' onclick='showAirports' onkeyup='filterFunction()'></div>");

	$("#container").append("<div id='toAirport_div'>To which airport?</div>");
	$("#toAirport_div").append("<div id='airport_dropdown' class='dropdown-content'><input type='text' placeholder='Search Airports' id='myInput' onfocus='showAirports' onkeyup='filterFunction()'></div>");


	body.append("<button id='flights_page'>Back to Flights</button>");
	$("#flights_page").on('click', function () {
		body.empty();
		buildFlightInterface();
	});
};


//this does not work
function showAirlines() {
	console.log("in focus");
	airline_list.classList.toggle("show");
}

//func to add a map w/ google maps api
function addMap(x, y) {
	var lati = x;
	var long = y;
	var airport = {
		lat: lati,
		lng: long
	};
	var map = new google.maps.Map(
		document.getElementById('map'), {
			zoom: 12,
			center: airport
		});

	var marker = new google.maps.Marker({
		position: airport,
		map: map
	});
}


//everything hotel page related below
var autocomplete, places;
var markers = [];
var MARKER_PATH = 'https://developers.google.com/maps/documentation/javascript/images/marker_green';
let map;
var infoWindow;
var hostnameRegexp = new RegExp('^https?://.+?/');
function buildHotelsInterface() {
	let body = $('body');
	$("#flights").on('click', function () {
		body.empty();
		buildFlightInterface();
	});
	$("#program").on('click', function () {
		$(".grid-container").empty();
		buildProgramInterface();
	});
	$(".grid-container").empty();
	$(".program-container").empty();
	document.getElementById('title').innerHTML = "Choose a Hotel";
	$(".grid-container").append("<div id='hoteltext'><p id='flavortext'>You may want to choose your vacation home by your touchdown city or a destination amidst different attractions. Either way, use this tool to find ones nearby any city you enter.</div><div id='hotelmap'>");
	$("#hotelmap").append("<div id='hotelSearch'>Search for hotels in:<div id='locationInput'><input id='autocomplete' placeholder='Enter a city' type='text'/></div></div>");
	//add a div for the map
	$("#hotelmap").append("<div id='mapholder'>" + "<div id='map'></div>" + "<div id='resultList'><table id='resultsTable'><th>Results:</th><tbody id='results'></tbody></table></div>" + "</div>");

	//adds the info-content window
	$("#hotelmap").append("<div style='display: none'><div id='info-content'><table>						  <tr id='iw-url-row' class='iw_table_row'><td id='iw-icon' class='iw_table_icon'></td>            <td id='iw-url'></td></tr><tr id='iw-address-row' class='iw_table_row'><td class='iw_attribute_name'>Address:</td><td id='iw-address'></td></tr><tr id='iw-phone-row' class='iw_table_row'><td class='iw_attribute_name'>Telephone:</td><td id='iw-phone'></td>          </tr><tr id='iw-website-row' class='iw_table_row'><td class='iw_attribute_name'>Website:</td>        <td id='iw-website'></td></tr><tr id='iw-button-row' class='iw_table_row'><td class='iw_attribute_name'><button id='iw-button'>Reserve</button></td></tr></table></div></div>");
	
	//add functionality to the reserve button
	$("#iw-button").on("click", function() {
		var hotelName = $('#iw-url').text();
		document.getElementById('cp_hotel').innerHTML = "Hotel Reservation: " + hotelName;
	}); 

	map = new google.maps.Map(document.getElementById('map'), {
        zoom: 12,
        center: {lat: 37.1, lng: -95.7},
		panControl: false,
        zoomControl: false,
	});
	
	infoWindow = new google.maps.InfoWindow({
		content: document.getElementById('info-content')
	});
	document.getElementById('map').append(map);

	autocomplete = new google.maps.places.Autocomplete(
		/** @type {!HTMLInputElement} */
		(
			document.getElementById('autocomplete')), {
			types: ['(cities)'],
			componentRestrictions: {
				'country': 'us'
			}
		});
	places = new google.maps.places.PlacesService(map);

	autocomplete.addListener('place_changed', onPlaceChanged);
}


//call when the user selects a city
function onPlaceChanged() {
	var place = autocomplete.getPlace();
	document.getElementById('cp_destination').innerHTML = "Destination: " + place.name;
	if (place.geometry) {
		map.panTo(place.geometry.location);
		search();
	} else {
		document.getElementById('autocomplete').placeholder = 'Enter a city';
	}
}

function search() {
	var search = {
		bounds: map.getBounds(),
		types: ['lodging']
	};

	places.nearbySearch(search, function (results, status) {
		if (status === google.maps.places.PlacesServiceStatus.OK) {
			clearResults();
			clearMarkers();
			// Create a marker for each hotel found, and
			// assign a letter of the alphabetic to each marker icon.
			for (var i = 0; i < results.length; i++) {
				var markerLetter = String.fromCharCode('A'.charCodeAt(0) + (i % 26));
				var markerIcon = MARKER_PATH + markerLetter + '.png';
				// Use marker animation to drop the icons incrementally on the map.
				markers[i] = new google.maps.Marker({
					position: results[i].geometry.location,
					animation: google.maps.Animation.DROP,
					icon: markerIcon
				});
				// If the user clicks a hotel marker, show the details of that hotel
				// in an info window.
				markers[i].placeResult = results[i];
				google.maps.event.addListener(markers[i], 'click', showInfoWindow);
				setTimeout(dropMarker(i), i * 100);
				addResult(results[i], i);
			}
		}
	});
}

function clearMarkers() {
	for (var i = 0; i < markers.length; i++) {
		if (markers[i]) {
			markers[i].setMap(null);
		}
	}
	markers = [];
}

function clearResults() {
	var results = document.getElementById('results');
	while (results.childNodes[0]) {
		results.removeChild(results.childNodes[0]);
	}
}

function dropMarker(i) {
	return function () {
		markers[i].setMap(map);
	};
}

function addResult(result, i) {
	var results = document.getElementById('results');
	var markerLetter = String.fromCharCode('A'.charCodeAt(0) + (i % 26));
	var markerIcon = MARKER_PATH + markerLetter + '.png';

	var tr = document.createElement('tr');
	tr.style.backgroundColor = (i % 2 === 0 ? '#F0F0F0' : '#FFFFFF');
	tr.onclick = function () {
		google.maps.event.trigger(markers[i], 'click');
	};

	var iconTd = document.createElement('td');
	var nameTd = document.createElement('td');
	var icon = document.createElement('img');
	icon.src = markerIcon;
	icon.setAttribute('class', 'placeIcon');
	icon.setAttribute('className', 'placeIcon');
	var name = document.createTextNode(result.name);
	iconTd.appendChild(icon);
	nameTd.appendChild(name);
	tr.appendChild(iconTd);
	tr.appendChild(nameTd);
	results.appendChild(tr);
}

function showInfoWindow() {
	var marker = this;
	places.getDetails({
			placeId: marker.placeResult.place_id
		},
		function (place, status) {
			if (status !== google.maps.places.PlacesServiceStatus.OK) {
				return;
			}
			infoWindow.open(map, marker);
			buildIWContent(place);
		});
}

function buildIWContent(place) {
	document.getElementById('iw-icon').innerHTML = '<img class="hotelIcon" ' +
		'src="' + place.icon + '"/>';
	document.getElementById('iw-url').innerHTML = '<b><a href="' + place.url +
		'">' + place.name + '</a></b>';
	document.getElementById('iw-address').textContent = place.vicinity;

	if (place.formatted_phone_number) {
		document.getElementById('iw-phone-row').style.display = '';
		document.getElementById('iw-phone').textContent =
			place.formatted_phone_number;
	} else {
		document.getElementById('iw-phone-row').style.display = 'none';
	}
	if (place.website) {
		var fullUrl = place.website;
		var website = hostnameRegexp.exec(place.website);
		if (website === null) {
			website = 'http://' + place.website + '/';
			fullUrl = website;
		}
		document.getElementById('iw-website-row').style.display = '';
		document.getElementById('iw-website').textContent = website;
	} else {
		document.getElementById('iw-website-row').style.display = 'none';
	}
}


//everything program related below

function buildProgramInterface() {
	let body = $('body');
	let grid = $('.program-container');
	$("#flights").on('click', function () {
		body.empty();
		buildFlightInterface();
	});
	$("#hotels").on('click', function () {
		$(".grid-container").empty();
		buildHotelsInterface();
	});
	document.getElementById('title').innerHTML = "Plan your Itinerary";
	$(".grid-container").empty();
	//if(document.getElementById('program-container').innerHTML === "") {
	if( $('.program-container').is(':empty') ) {
	grid.append("<h1>Start planning your itinerary by adding things you want to do</h1>");
	grid.append("<input type = 'text' id='eventbox' placeholder='Type something you want to do on your trip'></input");
	grid.append("<button type = 'button' id='createeventbutton'>Create Event");
	grid.append("<div id='event_container'></div>");
	$("#createeventbutton").on('click', function () {
		let eventText = $("#eventbox").val();
		//$('.current_program').append("<br>");
		$('.current_program').append("<div id='todoitembox'></div>");
		$('#todoitembox').append("<li id='todoitem'>" + eventText + "</li>");
		eventCont = eventCont + $("#todoitembox").text();
		$("#eventbox").val("");
	});
	}
}
