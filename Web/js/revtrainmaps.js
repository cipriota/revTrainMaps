var canvas;
var config = { 
	"width": 700, 
	"height": 300,
	//"topY": 42.5,
	//"topX": -11.1,
	//"scale": 6
	//"topY": 38.79,
	//"topX": -9.05,
	//"scale": 0.2
	"topY": 38.79,
	"topX": -9.05,
	"scale": 0.5
	};

$(document).ready(function() {
	canvas = $('#map');
	
	resizeCanvas();
	drawGeoData();
});

function resizeCanvas() {
	canvas.attr('width', config.width);
	canvas.attr('height', config.height);
}

function drawGeoData() {
	
	
	for (var i in geoData.nodes) {
		var m;
		var b;		
		var node = geoData.nodes[i];
		var point = {"x": 0, "y": 0};

		/*
		point.x = node.lon*canvas.attr('width')/(config.topX - config.scale);
		point.y = config.scale/canvas.attr('width') * node.lat + config.topY - config.scale;
		*/

		m = -canvas.attr('width') / config.scale;
		b = -m*config.topX;
		//point.x =  m * node.lon + b;
		point.x = (-Math.abs(node.lon)+Math.abs(config.topX)+config.scale)*canvas.attr('width')/config.scale;

		m = -canvas.attr('height') / config.scale;
		b = -m*config.topY;
		point.y = m * node.lat + b;

		canvas.drawArc({
				layer: true,
				fillStyle: "#AAA",
				x: point.x,
				y: point.y,
				radius: 5,
				
			});
		
		canvas.drawText({
			x: point.x,
			y: point.y-12,
			fontSize: "12px",
			fillStyle: "#AAA",
			text: node.tags[0].v
		});
		/*
		if (i>0) {
			var prevNode = geoData.nodes[i-1];
			canvas.drawLine({
					strokeStyle: "#000",
					strokeWidth: "2",
					x1: node.lon,
					y1: node.lat,
					x2: prevNode.lon,
					y2: prevNode.lat,
				});
		}
		*/
	}
}
