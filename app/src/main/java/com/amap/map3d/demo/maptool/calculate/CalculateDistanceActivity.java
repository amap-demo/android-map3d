package com.amap.map3d.demo.maptool.calculate;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.OnMarkerDragListener;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.map3d.demo.R;

/**
 * 两点间距离计算 示例
 */
public class CalculateDistanceActivity extends Activity implements OnMarkerDragListener {
	private AMap aMap;
	private MapView mapView;
	private LatLng latlngA = new LatLng(39.926516, 116.389366);
	private LatLng latlngB = new LatLng(39.924870, 116.403270);
	private LatLng latlngC = new LatLng(39.922038, 116.390608);//116.390608,39.922038
	private LatLng latlngD = new LatLng(39.917825, 116.411636);//116.411636,39.917825
	private Marker markerA;
	private Marker markerB;
	private Marker markerC;
	private Marker markerD;
	private TextView Text;
	private float distance;
	private float area;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.arc_activity);
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// 此方法必须重写
		init();
		distance = AMapUtils.calculateLineDistance(markerA.getPosition(), markerB.getPosition());
		calculatearea(latlngC,latlngD);
		Text.setText("长按Marker可拖动\n两点间距离为："+distance+"m\n矩形面积为："+area+"㎡");

	}
	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();
			setUpMap();
		}
		Text = (TextView) findViewById(R.id.info_text);
	}
	private void setUpMap() {
		aMap.setOnMarkerDragListener(this);
		markerA = aMap.addMarker(new MarkerOptions().position(latlngA)
				.draggable(true)
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
		markerB = aMap.addMarker(new MarkerOptions().position(latlngB)
				.draggable(true)
				.icon(BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
		aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(39.925516,
				116.395366), 15));

//		markerC = aMap.addMarker(new MarkerOptions().position(latlngC)
//				.icon(BitmapDescriptorFactory
//						.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
//		markerD = aMap.addMarker(new MarkerOptions().position(latlngD)
//				.icon(BitmapDescriptorFactory
//						.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
		aMap.addPolygon(new PolygonOptions()
				.add(latlngC)
				.add(new LatLng(latlngD.latitude,latlngC.longitude))
				.add(latlngD)
				.add(new LatLng(latlngC.latitude, latlngD.longitude))
				.fillColor(Color.argb(50, 1, 1, 1))
				.strokeColor(Color.RED)
				.strokeWidth(2));
	}

	/**
	 *  在marker拖动过程中回调此方法, 这个marker的位置可以通过getPosition()方法返回。
	 *  这个位置可能与拖动的之前的marker位置不一样。
	 *  marker 被拖动的marker对象。
     */
	@Override
	public void onMarkerDrag(Marker marker) {

		distance = AMapUtils.calculateLineDistance(markerA.getPosition(), markerB.getPosition());
		Text.setText("长按Marker可拖动\n两点间距离为："+distance+"m\n矩形面积为："+area+"㎡");
		
	}

	/**
	 * 在marker拖动完成后回调此方法, 这个marker的位置可以通过getPosition()方法返回。
	 * 这个位置可能与拖动的之前的marker位置不一样。
	 * marker 被拖动的marker对象。
	 */
	@Override
	public void onMarkerDragEnd(Marker arg0) {
		
	}

	/** 当marker开始被拖动时回调此方法, 这个marker的位置可以通过getPosition()方法返回。
	 * 这个位置可能与拖动的之前的marker位置不一样。
	 * marker 被拖动的marker对象。
	 */
	@Override
	public void onMarkerDragStart(Marker arg0) {
		
	}


	public void calculatearea(LatLng leftTopLatlng, LatLng rightBottomLatlng){
		area = AMapUtils.calculateArea(leftTopLatlng,rightBottomLatlng);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

}
