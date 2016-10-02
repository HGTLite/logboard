package com.hgt.ip;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.*;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/******************************************************************************
 * Created by  Yao  on  2016/10/2
 * README: 
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class IPParser {

    public static void main(String[] args) {
        // A File object pointing to your GeoIP2 or GeoLite2 database
//        File database = new File("C:\\Users\\Yao\\Documents\\IdeaProjects\\logboard\\project\\common\\tests\\geoip\\target\\classes\\GeoLite2-City.mmdb");
        File database = new File(IPParser.class.getClassLoader().getResource("GeoLite2-City.mmdb").getFile());
        /**查看当前目录的文件，直接用名称读不出来的原因可能是项目的嵌套**/
//        File file1 = new File(".");
//        for(String fileNames : file1.list()) System.out.println(fileNames);
//        File database = new File("./GeoLite2-City.mmdb");

// This creates the DatabaseReader object, which should be reused across
// lookups.
        DatabaseReader reader = null;
        InetAddress ipAddress = null;
        CityResponse response = null;


        try {

            reader = new DatabaseReader.Builder(database).build();
//            ipAddress = InetAddress.getByName("138.128.213.132");
//            ipAddress = InetAddress.getByName("123.206.29.126");
            ipAddress = InetAddress.getByName("115.28.222.135");
            response = reader.city(ipAddress);

        } catch (GeoIp2Exception | IOException e) {
            e.printStackTrace();
        }


        Country country = response.getCountry();
        System.out.println(country.getIsoCode());            // 'US'
        System.out.println(country.getName());               // 'United States'
        System.out.println(country.getNames().get("zh-CN")); // '美国'

        Subdivision subdivision = response.getMostSpecificSubdivision();
        System.out.println(subdivision.getName());    // 'Minnesota'
        System.out.println(subdivision.getIsoCode()); // 'MN'

        City city = response.getCity();
        System.out.println(city.getName()); // 'Minneapolis'

        Postal postal = response.getPostal();
        System.out.println(postal.getCode()); // '55455'

        Location location = response.getLocation();
        System.out.println(location.getLatitude());  // 44.9733
        System.out.println(location.getLongitude()); // -93.2323
    }

}
