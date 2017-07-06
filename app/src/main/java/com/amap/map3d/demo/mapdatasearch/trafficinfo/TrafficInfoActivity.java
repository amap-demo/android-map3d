package com.amap.map3d.demo.mapdatasearch.trafficinfo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.traffic.RoadTrafficQuery;
import com.amap.api.services.traffic.TrafficSearch;
import com.amap.api.services.traffic.TrafficSearch.OnTrafficSearchListener;
import com.amap.api.services.traffic.TrafficStatusInfo;
import com.amap.api.services.traffic.TrafficStatusResult;
import com.amap.map3d.demo.R;
import com.amap.map3d.demo.map.offlinemap.ToastUtil;
import com.amap.map3d.demo.overlay.TrafficOverlay;

import java.util.List;

/**
 * Created by my94493 on 2017/7/4.
 * 检索某条路的交通拥堵信息
 */

public class TrafficInfoActivity extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener, OnTrafficSearchListener {
    private AMap aMap;
    private MapView mapView;
    private ProgressDialog progDialog = null;// 进度框
    private EditText searchName;// 输入公交线路名称
    private Spinner selectCity;// 选择城市下拉列表
    private TextView traffic_Info;
    private String[] itemCitys = { "北京-110000", "上海-310000" };
    private String adCode = "";// 城市区号
    private TrafficStatusResult trafficResult;// 交通态势搜索返回的结果
    private RoadTrafficQuery roadTrafficQuery;// 公交线路查询的查询类

    private TrafficSearch trafficSearch;// 公交线路列表查询
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trafficinfo);
        mapView = (MapView)findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        init();
    }

    /**
     * 初始化AMap对象
     */
    private void init() {
        if (aMap == null){
            aMap = mapView.getMap();
        }
        findViewById(R.id.search).setOnClickListener(this);
        traffic_Info = (TextView)findViewById(R.id.traffic_info);
        selectCity = (Spinner)findViewById(R.id.cityName);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, itemCitys);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectCity.setAdapter(adapter);
        selectCity.setPrompt("请选择城市：");
        selectCity.setOnItemSelectedListener(this);
        searchName = (EditText)findViewById(R.id.roadName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search:
                searchtraffic();
        }

    }

    private void searchtraffic() {
        showProgressDialog();
        String search = searchName.getText().toString().trim();
        if (TextUtils.isEmpty(search)) {
            search = "阜安西路";
            searchName.setText(search);
        }
        trafficSearch = new TrafficSearch(this);
        trafficSearch.setTrafficSearchListener(this);
        roadTrafficQuery = new RoadTrafficQuery(search,adCode,TrafficSearch.ROAD_LEVEL_NONAME_WAY);
        trafficSearch.loadTrafficByRoadAsyn(roadTrafficQuery);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String cityString = itemCitys[position];
        adCode = cityString.substring(cityString.indexOf("-") + 1);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        adCode = "110000";
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
    public void onRoadTrafficSearched(TrafficStatusResult trafficStatusResult, int errorCode) {
        dissmissProgressDialog();
        if(errorCode == AMapException.CODE_AMAP_SUCCESS){
            trafficResult = trafficStatusResult;
            List<TrafficStatusInfo> trafficinfo = trafficResult.getRoads();
            for (TrafficStatusInfo trafficStatusInfo :trafficinfo){
                String s = trafficStatusInfo.getName()+trafficStatusInfo.getCoordinates()+trafficStatusInfo.getStatus();
                Log.i("MY",s);
            }
            aMap.clear();
            TrafficOverlay overlay = new TrafficOverlay(this.getApplicationContext(),aMap,trafficResult);
            overlay.addToMap();
            overlay.zoomToSpan();
            String info = trafficStatusResult.getDescription();
            traffic_Info.setText(info);
        }else {
            Toast.makeText(TrafficInfoActivity.this,"没有搜到结果，错误："+errorCode,Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 显示进度框
     */
    private void showProgressDialog() {
        if (progDialog == null)
            progDialog = new ProgressDialog(this);
        progDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDialog.setIndeterminate(false);
        progDialog.setCancelable(true);
        progDialog.setMessage("正在搜索:\n");
        progDialog.show();
    }
    /**
     * 隐藏进度框
     */
    private void dissmissProgressDialog() {
        if (progDialog != null) {
            progDialog.dismiss();
        }
    }
}
