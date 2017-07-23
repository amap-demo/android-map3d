# android-map3d
Android 3D地图SDK demo

## 前述 ##
- [高德官网申请Key](http://lbs.amap.com/dev/#/).
- [开发指南](http://lbs.amap.com/api/android-sdk/summary/).
- 阅读[参考手册](http://a.amap.com/lbs/static/unzip/Android_Map_Doc/index.html).

## 说明 ##

`创建地图`

| 功能说明 | 对应文件名 |
| -----|:-----:|
|显示地图|basemap|
|显示定位蓝点(默认样式)|LocationModeSourceActivity|
|显示定位蓝点(自定义样式)|CustomLocationActivity|
|显示室内地图|IndoorMapActivity|
|切换地图图层|LayersActivity|
|使用离线地图|OfflineMapActivity|
|自定义地图|CustomMapStyleActivity|

`与地图交互`

| 功能说明 | 对应文件名 |
| -----|:-----:|
|控件交互|UiSettingsActivity，LogoSettingsActivity|
|手势交互|GestureSettingsActivity|
|方法交互(改变地图的中心点)|CameraActivity|
|方法交互(自定义地图缩放)|ZoomActivity|
|方法交互(限制地图的显示范围)|LimitBoundsActivity|
|方法交互(改变地图默认显示区域)|MapOptionActivity|
|方法交互(改变地图缩放范围)|MinMaxZoomLevelActivity|
|地图截屏功能|ScreenShotActivity|

`在地图上绘制`

| 功能说明 | 对应文件名 |
| -----|:-----:|
|绘制点标记|CustomMarkerActivity|
|绘制折线|PolylineActivity|
|绘制面(圆形)|CircleActivity|
|绘制面(矩形)|PolygonActivity|
|轨迹纠偏|TraceActivity|
|点平滑移动|SmoothMoveActivity|
|绘制热力图|HeatMapActivity|
|绘制3D模型|OpenglActivity|
|绘制海量点图层|MultiPointOverlayActivity|



`获取地图数据`

| 功能说明 | 对应文件名 |
| -----|:-----:|
|获取POI数据(根据关键字检索POI)|PoiKeywordSearchActivity|
|获取POI数据(根据id检索POI)|PoiIDSearchActivity|
|获取POI数据(检索指定位置周边的POI)|PoiAroundSearchActivity|
|获取POI数据(沿途搜索)|RoutePOIActivity|
|获取POI数据(根据输入自动提示)|InputtipsActivity|
|获取地址描述数据(地址转坐标)|GeocoderActivity|
|获取地址描述数据(坐标转地址)|ReGeocoderActivity|
|获取行政区划数据|DistrictWithBoundaryActivity|
|获取公交数据(线路查询)|BuslineActivity|
|获取公交数据(站点查询)|BusStationActivity|
|获取天气数据|WeatherSearchActivity|
|获取业务数据|CloudActivity|
|获取交通态势信息|TrafficInfoActivity|


`出行路线规划`

| 功能说明 | 对应文件名 |
| -----|:-----:|
|驾车出行路线规划|DriveRouteActivity|
|步行出行路线规划|WalkRouteActivity|
|公交出行路线规划|BusRouteActivity|
|骑行出行路线规划|RideRouteActivity|
|综合路线规划展示|RouteActivity.java|


`地图计算工具`

| 功能说明 | 对应文件名 |
| -----|:-----:|
|坐标转换|CoordConverActivity|
|距离/面积计算|CalculateDistanceActivity|
