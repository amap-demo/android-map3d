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
