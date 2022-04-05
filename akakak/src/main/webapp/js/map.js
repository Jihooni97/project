/**
 * Feature : 좌표, 데이터를 담은 객체
 * Source : Feature의 집합 객체
 * Layer : 지도에 표시되는 집합 객체. 내용은 Source와 Style 등으로 결정.
 */
var map;
var position = [ [ 127.027583, 37.497928 ], [ 127.029784, 37.498978 ],
		[ 127.027492, 37.500251 ], [ 127.025731, 37.500499 ],
		[ 127.023083, 37.499284 ] ];
//시작위치
var olCenter = new ol.proj.fromLonLat([ 127.027583, 37.497928 ]); //좌표 변환
var apiKey = '83B7B5FB-6EDC-3BA5-B947-57B80DB11FDA';
var url = 'http://api.vworld.kr/req/wmts/1.0.0/' + apiKey + '/Base/{z}/{y}/{x}.png';
var vectorLayer;
var vectorLayer1;
var colorGreen = 'rgba(51, 255, 51)';
var colorRed = 'rgba(255, 0, 0)';
var width = 3;
var styleGreen = new ol.style.Style({
	image : new ol.style.Circle({
		radius : width * 2,
		fill : new ol.style.Fill({
			color : colorGreen
		})
	})
});
var styleRed = new ol.style.Style({
	image : new ol.style.Circle({
		radius : width * 2,
		fill : new ol.style.Fill({
			color : colorRed
		})
	})
});

//------------------------------------------------------------------------------------------------------------------//

$(function() {
	init_map();
	clickPopup();
	addClick();
//	btnClick();
})

//맵 생성
function init_map() {
	map = new ol.Map({
		target : "vmap",
		layers : [ new ol.layer.Tile({
			source : new ol.source.XYZ({
				url : url
			})
		}) ],
		view : new ol.View({
			center : olCenter,
			zoom : 15
		})
	});
	map.getView().setMinZoom(6);
}

//버튼 클릭시 마커 하나씩 생성
function btnClick(){
	var feature = new ol.Feature({
		geometry : new ol.geom.Point()
	})
	
	var vectorSource = new ol.source.vector();
	
	feature.setStyle(styleRed);
	vectorSource.addFeature(feature);
	vectorLayer = new ol.layer.Vector({
		source : vectorSource
	})	
	
	map.addLayer(vectorLayer);
}

function addClick() {
	map.on('click', function(e) {

		// 레이어 초기화
		if (vectorLayer1 != null) {
			vectorLayer1.getSource().clear();
		}

		const vectorSource = new ol.source.Vector();
		//클릭한 Feature속성을 가져온다.
		var iconFeature = map.getFeaturesAtPixel(e.pixel);
		//Feature가 있다면
		if (iconFeature.length != 0) {
			//x, y좌표를 가져온다.
			var lon = iconFeature[0].A.geometry.flatCoordinates[0];
			var lat = iconFeature[0].A.geometry.flatCoordinates[1];
			var iconFeatureId = iconFeature[0].A.id;
			
			if (iconFeatureId == 'point') {
				var feature = new ol.Feature({
					geometry : new ol.geom.Point([ lon, lat ])
				})

				feature.setStyle(styleGreen);
				vectorSource.addFeature(feature);

				vectorLayer1 = new ol.layer.Vector({
					source : vectorSource
				})
				map.addLayer(vectorLayer1);
			}
		}
	})
}

// 클릭한 위치 마커생성
//function clickLayer() {
//	map.on('click', function(e) {
//
//		// 레이어 초기화
//		if (vectorLayer1 != null) {
//			vectorLayer1.getSource().clear();
//		}
//		// 클릭한 좌표값 x,y 분배
//		let coordinate = e.coordinate;
//		var lon = coordinate[0];
//		var lat = coordinate[1];
//		
//		const vectorSource = new ol.source.Vector();
//				var feature = new ol.Feature({
//					geometry : new ol.geom.Point([ lon, lat ])
//				})
//				feature.setStyle(styleGreen);
//				vectorSource.addFeature(feature);
//				vectorLayer1 = new ol.layer.Vector({
//					source : vectorSource
//				})
//				map.addLayer(vectorLayer1);
//	})
//}

function create_point(){
	var vectorSource = new ol.source.Vector({});
	for (var i = 0; i < position.length; i++) {
		//마커 위치
		var feature = new ol.Feature({
			geometry : new ol.geom.Point(new ol.proj.fromLonLat(position[i])),
			// Feature의 ID지정
			id : "point",
			population : 4000,
			rainfall : 500
		})

		feature.setStyle(styleRed);
		vectorSource.addFeature(feature);
	}

	vectorLayer = new ol.layer.Vector({
		source : vectorSource,
		name : 'point_layer'
	})
	map.addLayer(vectorLayer);
}

function clickPopup(){
//팝업  	
map.on('click', function(evt) {
	//클릭된 곳의  Feature 를 가지고온다 (배열)
	var iconFeature = map.getFeaturesAtPixel(evt.pixel);
	//길이를 비교하여 Feature 가 있는지 확인
	if (iconFeature.length != 0) {
		//Feature 를가지고 와 안에있는 데이터를 조회한다.
		var iconFeatureId = iconFeature[0].A.id;
		//Feature 에 있는 id 가 point 지 확인한다 
		if (iconFeatureId == 'point') {
			alert('point 클릭');
		}
	}
	// 없으면 길이는 0 그럼으로 지도를 클릭
	else {
		alert('지도 클릭');
	}
});
}


//setInterval 실행
function startsetInterval() {

	var vectorSource = new ol.source.Vector({});
		var nm = 0;
		var timer = setInterval(function(){

		  if(nm>position.length){
		    clearInterval(timer)
		  }else{
			  var feature = new ol.Feature({
					geometry : new ol.geom.Point(new ol.proj.fromLonLat(position[nm])),
					// 포인트별 아이디를 생성하여 값을 확인할수 있다 
					id : "point",
					population : 4000,
					rainfall : 500
				})

				feature.setStyle(styleGreen);
			  	vectorSource.clear();
				vectorSource.addFeature(feature);
		    nm++;
		  }
		  if(nm==position.length){
		      nm=0;
		  }

		}, 1000)

	vectorLayer = new ol.layer.Vector({
		source : vectorSource,
		name : 'test_layer'
	})
	map.addLayer(vectorLayer);
}

//마커 제거
function remove_point() {
	// 모든 레이어 지우기 (단 베이스는 살리기 )
	var size = map.getAllLayers().length
	for(var i = 0 ; i < size ; i++ ){
		map.removeLayer(map.getAllLayers()[1]);
	}
	
	// 지정한 레이어 제거 ( name  별로 제거가 가능)
  /*
	for(var i = 0 ; i < size ; i++ ){
		if(map.getAllLayers()[i].A.name == 'test_layer'){
			map.removeLayer(map.getAllLayers()[i]);
		}
	}
  */	
}