package com.soul.mapdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.amap.api.maps.AMap
import com.amap.api.maps.UiSettings
import com.amap.api.maps.model.LatLng
import com.amap.api.maps.model.LatLngBounds
import com.amap.api.maps.model.MyLocationStyle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    // 西南坐标
    private val southwestLatLng = LatLng(137.503493, 56.251222)
    // 东北坐标
    private val northeastLatLng = LatLng(73.519118, 17.615643)

    private lateinit var aMap: AMap
    private lateinit var mUiSettings: UiSettings
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView(savedInstanceState)
        initEvent()

        initCustomMapStyle()
        //初始化地图蓝点
        initBluePoint()

    }


    private fun initView(bundle: Bundle?) {
        map.onCreate(bundle)
        aMap = map.map;
        mUiSettings = aMap.uiSettings

        aMap.mapType = AMap.MAP_TYPE_NORMAL// 矢量地图模式
        mUiSettings.setRotateGesturesEnabled(false)
        mUiSettings.setTiltGesturesEnabled(false)


        val latLngBounds = LatLngBounds(southwestLatLng, northeastLatLng)
        aMap.setMapStatusLimits(latLngBounds)
    }

    private fun initEvent() {
        aMap.setOnMyLocationChangeListener {

        }

    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onResume()
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        map.onSaveInstanceState(outState)
    }


    /**
     * 初始化自定义样式
     */
    private fun initCustomMapStyle() {
//        aMap.setCustomMapStyleID("ad953604b99948f6747bc05586fb8c7a");

        //该方法在AMap类中提供
        aMap.setCustomMapStylePath("/sdcard/custom_config/style.data");
        //该方法在AMap类中提供
        aMap.setMapCustomEnable(true);//true 开启; false 关闭
    }

    /**
     * 初始化蓝点
     */
    private fun initBluePoint() {
        val myLocationStyle: MyLocationStyle
        myLocationStyle = MyLocationStyle()//初始化定位蓝点样式类
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE)//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000) //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle)//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true)//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true)// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
    }


    override fun onDestroy() {
        super.onDestroy()
        map.onDestroy()
    }


}
