package com.example.youngforyou.commom.util;

import cn.hutool.core.util.NumberUtil;

/**
* <p>Title: MapTool</p>
* <p>Description: 地图类工具</p>
* <p>Company: XSQ</p> 
* @author tyw
* @date 2019年4月19日下午1:41:34
*/
public class MapTool {

    private static final double EARTH_RADIUS = 6378137;//赤道半径(单位m)
    private static final double RAD = Math.PI / 180.0;
	
	/**
	 * 坐标转换，腾讯地图转换成百度地图坐标
	 * @param lat 腾讯纬度
	 * @param lon 腾讯经度
	 * @return 返回结果：经度,纬度
	 */
	public static String changeToDbMap(double lat, double lon){
		double bd_lat;
		double bd_lon;
		double x_pi=3.14159265358979324;
	    double x = lon, y = lat;
	    double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
	    double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
	    bd_lon = z * Math.cos(theta) + 0.0065;
	    bd_lat = z * Math.sin(theta) + 0.006;
	    
	    return bd_lon+","+bd_lat;
	}
	
    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，单位为米 *
     *
     * @param lng1 经度1
     * @param lat1 纬度1
     * @param lng2 经度2
     * @param lat2 纬度2
     * @return
     */
    public static Double getDistance(Double lng1, Double lat1, Double lng2, Double lat2) {
        double radLat1 = lat1 * RAD;
        double radLat2 = lat2 * RAD;
        double a = radLat1 - radLat2;
        Double b = (lng1 - lng2) * RAD;
        Double s = 2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000.0;
        return s;
    }
    
    /**
     * 获取美化距离
     * 1公里以上以千米为单位
     * 1公里以内以米为单位
     * 
     * @param lng1 经度1
     * @param lat1 纬度1
     * @param lng2 经度2
     * @param lat2 纬度2
     * @return
     * @return
     */
    public static String getPrettyDistance(Double lng1, Double lat1, Double lng2, Double lat2) {
        double radLat1 = lat1 * RAD;
        double radLat2 = lat2 * RAD;
        double a = radLat1 - radLat2;
        Double b = (lng1 - lng2) * RAD;
        Double s = 2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return prettyFormat(s);
    }
    
    public static String prettyFormat(Double dis) {
    	if(dis > 1000) {
        	return NumberUtil.div(dis, Double.valueOf(1000), 2) + "km";
        }else {
        	return NumberUtil.round(dis, 0) + "m";
        }
    }

//    public static void main(String[] args) {
//        System.out.println(getDistance(120.601044,31.287867,120.602245,31.288160));
//        System.out.println(getPrettyDistance(120.601044,31.287867,120.602245,31.288160));
//        System.out.println(prettyFormat(getDistance(120.601044,31.287867,120.602245,31.288160)));
//    }
}
