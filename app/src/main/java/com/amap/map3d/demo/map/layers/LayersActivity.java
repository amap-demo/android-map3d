package com.amap.map3d.demo.map.layers;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyTrafficStyle;
import com.amap.map3d.demo.R;

/**
 * AMapV2地图中简单介绍矢量地图和卫星地图模式切换,几种地图模式切换。
 * 路况图层、文字和建筑图层隐藏显示
 */
public class LayersActivity extends Activity implements OnClickListener {
	private AMap aMap;
	private MapView mapView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layers_activity);
	    /*
         * 设置离线地图存储目录，在下载离线地图或初始化地图设置;
         * 使用过程中可自行设置, 若自行设置了离线地图存储的路径，
         * 则需要在离线地图下载和使用地图页面都进行路径设置
         * */
	    //Demo中为了其他界面可以使用下载的离线地图，使用默认位置存储，屏蔽了自定义设置
       // MapsInitializer.sdcardDir =OffLineMapUtils.getSdCacheDir(this);
		mapView = (MapView) findViewById(R.id.map);
		mapView.onCreate(savedInstanceState);// 此方法必须重写
		init();
	}

	/**
	 * 初始化AMap对象
	 */
	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();
		}
		findViewById(R.id.basicmap).setOnClickListener(this);
		findViewById(R.id.rsmap).setOnClickListener(this);
		findViewById(R.id.nightmap).setOnClickListener(this);
		findViewById(R.id.navimap).setOnClickListener(this);

		findViewById(R.id.traffic).setOnClickListener(this);
		findViewById(R.id.building).setOnClickListener(this);
		findViewById(R.id.maptext).setOnClickListener(this);

		//自定义实时交通信息的颜色样式
		MyTrafficStyle myTrafficStyle = new MyTrafficStyle();
		myTrafficStyle.setSeriousCongestedColor(0xff92000a);
		myTrafficStyle.setCongestedColor(0xffea0312);
		myTrafficStyle.setSlowColor(0xffff7508);
		myTrafficStyle.setSmoothColor(0xff00a209);
		aMap.setMyTrafficStyle(myTrafficStyle);
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.basicmap:
				aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 矢量地图模式
				break;
			case R.id.rsmap:
				aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式
				break;
			case R.id.nightmap:
				aMap.setMapType(AMap.MAP_TYPE_NIGHT);//夜景地图模式
				break;
			case R.id.navimap:
				aMap.setMapType(AMap.MAP_TYPE_NAVI);//导航地图模式
				break;
			case R.id.traffic:
				aMap.setTrafficEnabled(((CheckBox) v).isChecked());// 显示实时交通状况
				break;
			case R.id.building:
				aMap.showBuildings(((CheckBox) v).isChecked());// 显示3D 楼块
				break;
			case R.id.maptext:
				aMap.showMapText(((CheckBox) v).isChecked());// 显示底图文字
		}
	}
}
