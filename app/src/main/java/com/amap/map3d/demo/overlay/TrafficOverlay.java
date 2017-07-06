package com.amap.map3d.demo.overlay;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveStep;
import com.amap.api.services.route.TMC;
import com.amap.api.services.traffic.TrafficStatusInfo;
import com.amap.api.services.traffic.TrafficStatusResult;
import com.amap.map3d.demo.R;
import com.amap.map3d.demo.util.AMapUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 路线路线图层类。
 */
public class TrafficOverlay {
    protected AMap mAMap;
    private List<TrafficStatusInfo> tmcs;
    private Context mContext;
    private float mWidth = 15;
    private List<LatLng> mLatLngsOfRoad = new ArrayList<LatLng>();;
    protected List<Polyline> allPolyLines = new ArrayList<Polyline>();
    private TrafficStatusResult mTrafficStatusResult;

	/**
     * 根据给定的参数，构造一个道路路线图层类对象。
     *
     * @param amap      地图对象。
     * @param context   当前的activity对象。
     */
    public TrafficOverlay(Context context, AMap amap,TrafficStatusResult trafficStatusResult ) {
    	mContext = context;
        mAMap = amap;
        mTrafficStatusResult = trafficStatusResult;
    }

    public float getRouteWidth() {
        return mWidth;
    }

    /**
     * 设置路线宽度
     *
     * @param mWidth 路线宽度，取值范围：大于0
     */
    public void setRouteWidth(float mWidth) {
        this.mWidth = mWidth;
    }

    /**
     * 添加驾车路线添加到地图上显示。
     */
	public void addToMap() {
        try {
            if (mAMap == null) {
                return;
            }

            tmcs = new ArrayList<TrafficStatusInfo>();
            tmcs.addAll(mTrafficStatusResult.getRoads());

            if (tmcs.size()>0 ) {
            	colorWayUpdate(tmcs);
			}
            
        } catch (Throwable e) {
        	e.printStackTrace();
        }
    }

    
    private void showcolorPolyline(PolylineOptions polylineOptions) {
    	addPolyLine(polylineOptions);
	}

    /**
     * 根据不同的路段拥堵情况展示不同的颜色
     *
     * @param tmcSection
     */
    private void colorWayUpdate(List<TrafficStatusInfo> tmcSection) {
        if (mAMap == null) {
            return;
        }
        if (tmcSection == null || tmcSection.size() <= 0) {
            return;
        }
        TrafficStatusInfo segmentTrafficStatus;

        for (int i = 0; i < tmcSection.size(); i++) {
            PolylineOptions mPolylineOptionscolor = new PolylineOptions();
            List<Integer> colorList = new ArrayList<Integer>();

        	segmentTrafficStatus = tmcSection.get(i);
        	int color = getcolor(segmentTrafficStatus.getStatus());
        	List<LatLonPoint> mployline = segmentTrafficStatus.getCoordinates();
            if (mployline != null){
                for (int j = 0; j < mployline.size(); j++) {
                    mPolylineOptionscolor.add(AMapUtil.convertToLatLng(mployline.get(j)));
                    colorList.add(color);
                    mLatLngsOfRoad.add(AMapUtil.convertToLatLng(mployline.get(j)));
                }
                mPolylineOptionscolor.colorValues(colorList);
                showcolorPolyline(mPolylineOptionscolor);
            }else{
                Log.i("MY","ployline为空,第"+i+"段 status:"+segmentTrafficStatus.getStatus() + segmentTrafficStatus.getName()+segmentTrafficStatus.getLcodes());
            }

		}
    }
    
    private int getcolor(String status) {

    	if (status.equals("1")) {
    		return Color.GREEN;
		} else if (status.equals("2")) {
			 return Color.YELLOW;
		} else if (status.equals("3")) {
			return Color.RED;
		} else {
			return Color.parseColor("#537edc");
		}	
	}

	public LatLng convertToLatLng(LatLonPoint point) {
        return new LatLng(point.getLatitude(),point.getLongitude());
  }

    protected void addPolyLine(PolylineOptions options) {
        if(options == null) {
            return;
        }
        Polyline polyline = mAMap.addPolyline(options);
        if(polyline != null) {
            allPolyLines.add(polyline);
        }
    }

    public void zoomToSpan() {
        if (mLatLngsOfRoad != null && mLatLngsOfRoad.size() > 0) {
            if (mAMap == null)
                return;
            try {
                LatLngBounds bounds = getLatLngBounds();
                mAMap.animateCamera(CameraUpdateFactory
                        .newLatLngBounds(bounds, 50));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    protected LatLngBounds getLatLngBounds() {
        LatLngBounds.Builder b = LatLngBounds.builder();
        for (LatLng point :mLatLngsOfRoad) {
            b.include(point);
        }
        return b.build();
    }

    public void removeFromMap() {
        for (Polyline polyline : allPolyLines) {
            polyline.remove();
        }
    }
}