var geoData;

$(document).ready(function() {
	loadGeoData();
	drawGeoData();
});

function loadGeoData() {
	geoData = {
		"points": [
			{ 
				"id": 1, "type": "track", "latitude": 0, "longitude": 0 
			},
			{ 
				"id": 2, "type": "track", "latitude": 10, "longitude": 10 
			},
			{ 
				"id": 3, "type": "track", "latitude": 15, "longitude": 40 
			},
			{ 
				"id": 4, "type": "track", "latitude": 70, "longitude": 70 
			}
		],
		"paths" : [
			{
				"id": 1, "pathgroup": 1, "startpoint": 1, "endpoint": 2
			},
			{
				"id": 2, "pathgroup": 1, "startpoint": 2, "endpoint": 4
			},
			{
				"id": 3, "pathgroup": 1, "startpoint": 4, "endpoint": 3
			}
		]
	}
}

function drawGeoData() {
	var canvas = $('#map');

	for (var i in geoData.points) {
		canvas.drawArc({
				fillStyle: "black",
				x: geoData.points[i].longitude,
				y: geoData.points[i].latitude,
				radius: 10
			});
		if (i>0) {
			canvas.drawLine({
					strokeStyle: "#000",
					strokeWidth: "5",
					x1: geoData.points[i].longitude,
					y1: geoData.points[i].latitude,
					x2: geoData.points[i-1].longitude,
					y2: geoData.points[i-1].latitude
				});
		}
	}
}
