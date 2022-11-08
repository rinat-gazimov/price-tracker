package ru.tracker.integration.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import ru.tracker.integration.model.ListResponse;
import ru.tracker.integration.model.ListingResponse;
import ru.tracker.integration.model.ProductDto;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
@Log4j2
public class TrackerClient {

    private final ObjectMapper objectMapper;

    public ListingResponse getProducts(int categoryId) throws IOException {

        UriComponents builder = UriComponentsBuilder.fromHttpUrl("https://www.mvideo.ru/bff/products/listing")
                .queryParam("categoryId", categoryId)
                .queryParam("offset", 0)
                .queryParam("limit", 72)
                .queryParam("filterParams", "WyJ0b2xrby12LW5hbGljaGlpIiwiIiwiZGEiXQ%3D%3D")
                .queryParam("doTranslit", true)
                .build(false);

        URL url = new URL(builder.toUriString());
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("GET");

        httpConn.setRequestProperty("authority", "www.mvideo.ru");
        httpConn.setRequestProperty("accept", "application/json");
        httpConn.setRequestProperty("accept-language", "en-US,en;q=0.9,ru-RU;q=0.8,ru;q=0.7");
        httpConn.setRequestProperty("cookie", "HINTS_FIO_COOKIE_NAME=2; MAIN_PAGE_VARIATION=6; MVID_ABC_TEST_WIDGET=0; MVID_AB_PROMO_DAILY=1; MVID_AB_SERVICES_DESCRIPTION=var4; MVID_AB_TEST_COMPARE_ONBOARDING=true; MVID_ADDRESS_COMMENT_AB_TEST=2; MVID_BLACK_FRIDAY_ENABLED=true; MVID_CALC_BONUS_RUBLES_PROFIT=false; MVID_CART_MULTI_DELETE=false; MVID_CATALOG_STATE=1; MVID_CHECKOUT_REGISTRATION_AB_TEST=2; MVID_CITY_ID=CityCZ_1458; MVID_FILTER_CODES=true; MVID_FILTER_TOOLTIP=1; MVID_FLOCKTORY_ON=true; MVID_GEOLOCATION_NEEDED=true; MVID_GET_LOCATION_BY_DADATA=DaData; MVID_GIFT_KIT=true; MVID_IS_NEW_BR_WIDGET=true; MVID_KLADR_ID=1600000100000; MVID_LAYOUT_TYPE=1; MVID_NEW_ACCESSORY=true; MVID_NEW_LK_CHECK_CAPTCHA=true; MVID_NEW_LK_OTP_TIMER=true; MVID_NEW_MBONUS_BLOCK=true; MVID_PRICE_FIRST=2; MVID_PRM20_CMS=true; MVID_REGION_ID=9; MVID_REGION_SHOP=S910; MVID_SERVICES=111; MVID_SERVICES_MINI_BLOCK=var2; MVID_TAXI_DELIVERY_INTERVALS_VIEW=old; MVID_TIMEZONE_OFFSET=3; MVID_WEBP_ENABLED=true; NEED_REQUIRE_APPLY_DISCOUNT=true; PRESELECT_COURIER_DELIVERY_FOR_KBT=true; PROMOLISTING_WITHOUT_STOCK_AB_TEST=2; flacktory=no; searchType2=3; popmechanic_sbjs_migrations=popmechanic_1418474375998%3D1%7C%7C%7C1471519752600%3D1%7C%7C%7C1471519752605%3D1; __SourceTracker=google__organic; admitad_deduplication_cookie=google__organic; SMSError=; authError=; tmr_lvid=bdc6480ab637ef784f23dd0d44135575; tmr_lvidTS=1651639488254; flocktory-uuid=f1ea8d82-0d43-4a13-aa3d-0f3b2a3d3472-1; BIGipServeratg-ps-prod_tcp80=1694817290.20480.0000; afUserId=1c430272-808e-41b1-9b21-d6aa7bf72caa-p; _ym_uid=1651639491913629062; _ym_d=1651639491; uxs_uid=eeee1030-cb64-11ec-9786-01523ec63442; BIGipServeratg-ps-prod_tcp80_clone=1694817290.20480.0000; MVID_CRM_ID=0031206311; wurfl_device_id=generic_web_browser; MVID_NEW_OLD=eyJjYXJ0IjpmYWxzZSwiZmF2b3JpdGUiOmZhbHNlLCJjb21wYXJpc29uIjpmYWxzZX0=; MVID_GTM_BROWSER_THEME=1; deviceType=desktop; MVID_GUEST_ID=20629985351; MVID_OLD_NEW=eyJjb21wYXJpc29uIjogdHJ1ZSwgImZhdm9yaXRlIjogdHJ1ZSwgImNhcnQiOiB0cnVlfQ==; UX_Taxi=0; ADRUM=s=1651830248049&r=https%3A%2F%2Fwww.mvideo.ru%2Fconfirmation%3F-348706964; COMPARISON_INDICATOR=false; MVID_CART_AVAILABILITY=true; MVID_CREDIT_AVAILABILITY=true; MVID_MCLICK=true; MVID_NEW_DESKTOP_FILTERS=true; MVID_SUPER_FILTERS=false; __js_p_=346,900,0,1,0; BIGipServericerock-prod=3187989514.20480.0000; bIPs=1613809182; __lhash_=0d2f4db1f9f34236f98141e3775ffc41; MVID_GTM_DELAY=true; MVID_LP_HANDOVER=1; MVID_LP_SOLD_VARIANTS=3; MVID_MINI_PDP=true; MVID_NEW_LK=true; MVID_NEW_LK_LOGIN=true; MVID_SMART_BANNER_BOTTOM=true; advcake_track_id=b05f22e3-2fbe-ea74-bdd1-841f5ed02fa7; advcake_session_id=c0bc77e0-f3c2-9fad-3451-e5432c7e4339; AF_SYNC=1659599566383; __zzatgib-w-mvideo=MDA0dC0cTApcfEJcdGswPi17CT4VHThHKHIzd2VkbTE+LRFfNmcUa3osS1I9KkYfKyJcRH1zdUltCzl9Y1lfenscGyZNLzwUYHMhSRpzQnZ6GjhqIWdJWiNMWz56Kx4afHQsVAwRW0NKX28beyJfKggkYzVfGUNqTg1pN1wUPHVlPkdyfC5EbiJnTF0gRFE/SF5dSRIyYhJAQE1HDTdAXjdXYTAPFhFNRxU9VlJPQyhrG3FYVy9wJBdIUn47FmtuR2dHV0wXX0I7OFhBEXVbPURudSlBaB5gOVURCxIXRF5cVWl1ZxlMQFcvDS44Xi1vHmVMWiVGXlYJKB0Td2cVHkBPG1AINDZicFcnKxEmVD9HGUplTnsJXWMTOEQhCXY9PxsQOg==ojWW8w==; __zzatgib-w-mvideo=MDA0dC0cTApcfEJcdGswPi17CT4VHThHKHIzd2VkbTE+LRFfNmcUa3osS1I9KkYfKyJcRH1zdUltCzl9Y1lfenscGyZNLzwUYHMhSRpzQnZ6GjhqIWdJWiNMWz56Kx4afHQsVAwRW0NKX28beyJfKggkYzVfGUNqTg1pN1wUPHVlPkdyfC5EbiJnTF0gRFE/SF5dSRIyYhJAQE1HDTdAXjdXYTAPFhFNRxU9VlJPQyhrG3FYVy9wJBdIUn47FmtuR2dHV0wXX0I7OFhBEXVbPURudSlBaB5gOVURCxIXRF5cVWl1ZxlMQFcvDS44Xi1vHmVMWiVGXlYJKB0Td2cVHkBPG1AINDZicFcnKxEmVD9HGUplTnsJXWMTOEQhCXY9PxsQOg==ojWW8w==; cfidsgib-w-mvideo=FcIRAkJK+63gN80pEojB/uPPfmhRZSdCjezisOvAMQmtqXdIuPqdpUNF9Ksw9kfj6DjlX5QmpgVwZP3jKdAS8p+lzK2f3x/giqIb8j80EezVOnJtP9PK3+eio3LXlU/lE/W4hNC2pCfqeXS7mx/7wFZdUmD3XOsOu9p7CA==; cfidsgib-w-mvideo=FcIRAkJK+63gN80pEojB/uPPfmhRZSdCjezisOvAMQmtqXdIuPqdpUNF9Ksw9kfj6DjlX5QmpgVwZP3jKdAS8p+lzK2f3x/giqIb8j80EezVOnJtP9PK3+eio3LXlU/lE/W4hNC2pCfqeXS7mx/7wFZdUmD3XOsOu9p7CA==; gsscgib-w-mvideo=jo9maysax8CDuYB4hLbYEYBGLznTrni3iBvJtU93/ca2LOr4FV8SRPk7KSKvL07XgTQmYhScuNQl7JmGcDkps9x89g8WEG2u6n4G3b3js887PMK25nIGwX/rwHn6FiNPbupGt8LqtoXWPER0bSMVzxS+7pQlhvR21kuHPI0BKComa3zTXQ2L5gl2cq2xb6wd+UDswMuoH0rAxm/4uVADbBXRqEPo5yFt9u79nzkrIfRyb4d/EuptLes3P52SbQ==; gsscgib-w-mvideo=jo9maysax8CDuYB4hLbYEYBGLznTrni3iBvJtU93/ca2LOr4FV8SRPk7KSKvL07XgTQmYhScuNQl7JmGcDkps9x89g8WEG2u6n4G3b3js887PMK25nIGwX/rwHn6FiNPbupGt8LqtoXWPER0bSMVzxS+7pQlhvR21kuHPI0BKComa3zTXQ2L5gl2cq2xb6wd+UDswMuoH0rAxm/4uVADbBXRqEPo5yFt9u79nzkrIfRyb4d/EuptLes3P52SbQ==; fgsscgib-w-mvideo=Cluv8d8693b999234a36e5c518ee3915fb013d93; fgsscgib-w-mvideo=Cluv8d8693b999234a36e5c518ee3915fb013d93; cfidsgib-w-mvideo=PFZHrLExW6hcrgLn83o327Cha70ivDUBRmA3xqlXsW9AjmxlApBnYRa5NlkwZtyCuQ5s91jncCRJW0j4Wrj4TKa5A9HRP587fEWsK/Wd6ku7pzlRnH6rw8X9K6zJQh4fRvUgcf2pxbOESpVwu5nzDC1yjVbemYhjly06+Q==; CACHE_INDICATOR=false; mindboxDeviceUUID=1d6fba70-97b6-4704-8602-7dccad7b8e29; directCrm-session=%7B%22deviceGuid%22%3A%221d6fba70-97b6-4704-8602-7dccad7b8e29%22%7D; _ga=GA1.2.1125494095.1651639488; tmr_reqNum=210; JSESSIONID=1R0Vvtpp3yRJMfZp6w21h3fgnVLXLTHlB4v8pTJl3bhGr9cL7vJv!-54821496; _ga_CFMZTSS5FM=GS1.1.1659726239.7.0.1659726239.0; _ga_BNX5WPP3YK=GS1.1.1659726239.7.0.1659726239.60; MVID_ENVCLOUD=prod2");
        httpConn.setRequestProperty("referer", "https://www.mvideo.ru/televizory-i-cifrovoe-tv-1/televizory-65/f/tolko-v-nalichii=da?showCount=72");
        httpConn.setRequestProperty("sec-ch-ua", "\".Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"103\", \"Chromium\";v=\"103\"");
        httpConn.setRequestProperty("sec-ch-ua-mobile", "?0");
        httpConn.setRequestProperty("sec-ch-ua-platform", "\"macOS\"");
        httpConn.setRequestProperty("sec-fetch-dest", "empty");
        httpConn.setRequestProperty("sec-fetch-mode", "cors");
        httpConn.setRequestProperty("sec-fetch-site", "same-origin");
        httpConn.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
        httpConn.setRequestProperty("x-set-application-id", "7c67b2a7-6ae0-4ee6-9ddb-73b37959081a");

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";

        return objectMapper.readValue(response, ListingResponse.class);
    }

    public List<ProductDto> getDetailedProducts(List<String> productIds) throws IOException {
        URL url = new URL("https://www.mvideo.ru/bff/product-details/list");
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("POST");

        httpConn.setRequestProperty("authority", "www.mvideo.ru");
        httpConn.setRequestProperty("accept", "application/json");
        httpConn.setRequestProperty("accept-language", "en-US,en;q=0.9,ru-RU;q=0.8,ru;q=0.7");
        httpConn.setRequestProperty("content-type", "application/json");
        httpConn.setRequestProperty("cookie", "HINTS_FIO_COOKIE_NAME=2; MAIN_PAGE_VARIATION=6; MVID_ABC_TEST_WIDGET=0; MVID_AB_PROMO_DAILY=1; MVID_AB_SERVICES_DESCRIPTION=var4; MVID_AB_TEST_COMPARE_ONBOARDING=true; MVID_ADDRESS_COMMENT_AB_TEST=2; MVID_BLACK_FRIDAY_ENABLED=true; MVID_CALC_BONUS_RUBLES_PROFIT=false; MVID_CART_MULTI_DELETE=false; MVID_CATALOG_STATE=1; MVID_CHECKOUT_REGISTRATION_AB_TEST=2; MVID_CITY_ID=CityCZ_1458; MVID_FILTER_CODES=true; MVID_FILTER_TOOLTIP=1; MVID_FLOCKTORY_ON=true; MVID_GEOLOCATION_NEEDED=true; MVID_GET_LOCATION_BY_DADATA=DaData; MVID_GIFT_KIT=true; MVID_IS_NEW_BR_WIDGET=true; MVID_KLADR_ID=1600000100000; MVID_LAYOUT_TYPE=1; MVID_NEW_ACCESSORY=true; MVID_NEW_LK_CHECK_CAPTCHA=true; MVID_NEW_LK_OTP_TIMER=true; MVID_NEW_MBONUS_BLOCK=true; MVID_PRICE_FIRST=2; MVID_PRM20_CMS=true; MVID_REGION_ID=9; MVID_REGION_SHOP=S910; MVID_SERVICES=111; MVID_SERVICES_MINI_BLOCK=var2; MVID_TAXI_DELIVERY_INTERVALS_VIEW=old; MVID_TIMEZONE_OFFSET=3; MVID_WEBP_ENABLED=true; NEED_REQUIRE_APPLY_DISCOUNT=true; PRESELECT_COURIER_DELIVERY_FOR_KBT=true; PROMOLISTING_WITHOUT_STOCK_AB_TEST=2; flacktory=no; searchType2=3; popmechanic_sbjs_migrations=popmechanic_1418474375998%3D1%7C%7C%7C1471519752600%3D1%7C%7C%7C1471519752605%3D1; __SourceTracker=google__organic; admitad_deduplication_cookie=google__organic; SMSError=; authError=; tmr_lvid=bdc6480ab637ef784f23dd0d44135575; tmr_lvidTS=1651639488254; flocktory-uuid=f1ea8d82-0d43-4a13-aa3d-0f3b2a3d3472-1; BIGipServeratg-ps-prod_tcp80=1694817290.20480.0000; afUserId=1c430272-808e-41b1-9b21-d6aa7bf72caa-p; _ym_uid=1651639491913629062; _ym_d=1651639491; uxs_uid=eeee1030-cb64-11ec-9786-01523ec63442; BIGipServeratg-ps-prod_tcp80_clone=1694817290.20480.0000; MVID_CRM_ID=0031206311; wurfl_device_id=generic_web_browser; MVID_NEW_OLD=eyJjYXJ0IjpmYWxzZSwiZmF2b3JpdGUiOmZhbHNlLCJjb21wYXJpc29uIjpmYWxzZX0=; MVID_GTM_BROWSER_THEME=1; deviceType=desktop; MVID_GUEST_ID=20629985351; MVID_OLD_NEW=eyJjb21wYXJpc29uIjogdHJ1ZSwgImZhdm9yaXRlIjogdHJ1ZSwgImNhcnQiOiB0cnVlfQ==; UX_Taxi=0; ADRUM=s=1651830248049&r=https%3A%2F%2Fwww.mvideo.ru%2Fconfirmation%3F-348706964; COMPARISON_INDICATOR=false; MVID_CART_AVAILABILITY=true; MVID_CREDIT_AVAILABILITY=true; MVID_MCLICK=true; MVID_NEW_DESKTOP_FILTERS=true; MVID_SUPER_FILTERS=false; __js_p_=346,900,0,1,0; BIGipServericerock-prod=3187989514.20480.0000; bIPs=1613809182; __lhash_=0d2f4db1f9f34236f98141e3775ffc41; MVID_GTM_DELAY=true; MVID_LP_HANDOVER=1; MVID_LP_SOLD_VARIANTS=3; MVID_MINI_PDP=true; MVID_NEW_LK=true; MVID_NEW_LK_LOGIN=true; MVID_SMART_BANNER_BOTTOM=true; advcake_track_id=b05f22e3-2fbe-ea74-bdd1-841f5ed02fa7; advcake_session_id=c0bc77e0-f3c2-9fad-3451-e5432c7e4339; AF_SYNC=1659599566383; __zzatgib-w-mvideo=MDA0dC0cTApcfEJcdGswPi17CT4VHThHKHIzd2VkbTE+LRFfNmcUa3osS1I9KkYfKyJcRH1zdUltCzl9Y1lfenscGyZNLzwUYHMhSRpzQnZ6GjhqIWdJWiNMWz56Kx4afHQsVAwRW0NKX28beyJfKggkYzVfGUNqTg1pN1wUPHVlPkdyfC5EbiJnTF0gRFE/SF5dSRIyYhJAQE1HDTdAXjdXYTAPFhFNRxU9VlJPQyhrG3FYVy9wJBdIUn47FmtuR2dHV0wXX0I7OFhBEXVbPURudSlBaB5gOVURCxIXRF5cVWl1ZxlMQFcvDS44Xi1vHmVMWiVGXlYJKB0Td2cVHkBPG1AINDZicFcnKxEmVD9HGUplTnsJXWMTOEQhCXY9PxsQOg==ojWW8w==; __zzatgib-w-mvideo=MDA0dC0cTApcfEJcdGswPi17CT4VHThHKHIzd2VkbTE+LRFfNmcUa3osS1I9KkYfKyJcRH1zdUltCzl9Y1lfenscGyZNLzwUYHMhSRpzQnZ6GjhqIWdJWiNMWz56Kx4afHQsVAwRW0NKX28beyJfKggkYzVfGUNqTg1pN1wUPHVlPkdyfC5EbiJnTF0gRFE/SF5dSRIyYhJAQE1HDTdAXjdXYTAPFhFNRxU9VlJPQyhrG3FYVy9wJBdIUn47FmtuR2dHV0wXX0I7OFhBEXVbPURudSlBaB5gOVURCxIXRF5cVWl1ZxlMQFcvDS44Xi1vHmVMWiVGXlYJKB0Td2cVHkBPG1AINDZicFcnKxEmVD9HGUplTnsJXWMTOEQhCXY9PxsQOg==ojWW8w==; cfidsgib-w-mvideo=FcIRAkJK+63gN80pEojB/uPPfmhRZSdCjezisOvAMQmtqXdIuPqdpUNF9Ksw9kfj6DjlX5QmpgVwZP3jKdAS8p+lzK2f3x/giqIb8j80EezVOnJtP9PK3+eio3LXlU/lE/W4hNC2pCfqeXS7mx/7wFZdUmD3XOsOu9p7CA==; cfidsgib-w-mvideo=FcIRAkJK+63gN80pEojB/uPPfmhRZSdCjezisOvAMQmtqXdIuPqdpUNF9Ksw9kfj6DjlX5QmpgVwZP3jKdAS8p+lzK2f3x/giqIb8j80EezVOnJtP9PK3+eio3LXlU/lE/W4hNC2pCfqeXS7mx/7wFZdUmD3XOsOu9p7CA==; gsscgib-w-mvideo=jo9maysax8CDuYB4hLbYEYBGLznTrni3iBvJtU93/ca2LOr4FV8SRPk7KSKvL07XgTQmYhScuNQl7JmGcDkps9x89g8WEG2u6n4G3b3js887PMK25nIGwX/rwHn6FiNPbupGt8LqtoXWPER0bSMVzxS+7pQlhvR21kuHPI0BKComa3zTXQ2L5gl2cq2xb6wd+UDswMuoH0rAxm/4uVADbBXRqEPo5yFt9u79nzkrIfRyb4d/EuptLes3P52SbQ==; gsscgib-w-mvideo=jo9maysax8CDuYB4hLbYEYBGLznTrni3iBvJtU93/ca2LOr4FV8SRPk7KSKvL07XgTQmYhScuNQl7JmGcDkps9x89g8WEG2u6n4G3b3js887PMK25nIGwX/rwHn6FiNPbupGt8LqtoXWPER0bSMVzxS+7pQlhvR21kuHPI0BKComa3zTXQ2L5gl2cq2xb6wd+UDswMuoH0rAxm/4uVADbBXRqEPo5yFt9u79nzkrIfRyb4d/EuptLes3P52SbQ==; fgsscgib-w-mvideo=Cluv8d8693b999234a36e5c518ee3915fb013d93; fgsscgib-w-mvideo=Cluv8d8693b999234a36e5c518ee3915fb013d93; cfidsgib-w-mvideo=PFZHrLExW6hcrgLn83o327Cha70ivDUBRmA3xqlXsW9AjmxlApBnYRa5NlkwZtyCuQ5s91jncCRJW0j4Wrj4TKa5A9HRP587fEWsK/Wd6ku7pzlRnH6rw8X9K6zJQh4fRvUgcf2pxbOESpVwu5nzDC1yjVbemYhjly06+Q==; CACHE_INDICATOR=false; _ga=GA1.2.1125494095.1651639488; tmr_reqNum=210; JSESSIONID=1R0Vvtpp3yRJMfZp6w21h3fgnVLXLTHlB4v8pTJl3bhGr9cL7vJv!-54821496; _ga_CFMZTSS5FM=GS1.1.1659726239.7.0.1659726239.0; _ga_BNX5WPP3YK=GS1.1.1659726239.7.0.1659726239.60; MVID_ENVCLOUD=prod1; mindboxDeviceUUID=1d6fba70-97b6-4704-8602-7dccad7b8e29; directCrm-session=%7B%22deviceGuid%22%3A%221d6fba70-97b6-4704-8602-7dccad7b8e29%22%7D");
        httpConn.setRequestProperty("origin", "https://www.mvideo.ru");
        httpConn.setRequestProperty("referer", "https://www.mvideo.ru/televizory-i-cifrovoe-tv-1/televizory-65/f/tolko-v-nalichii=da?showCount=72");
        httpConn.setRequestProperty("sec-ch-ua", "\".Not/A)Brand\";v=\"99\", \"Google Chrome\";v=\"103\", \"Chromium\";v=\"103\"");
        httpConn.setRequestProperty("sec-ch-ua-mobile", "?0");
        httpConn.setRequestProperty("sec-ch-ua-platform", "\"macOS\"");
        httpConn.setRequestProperty("sec-fetch-dest", "empty");
        httpConn.setRequestProperty("sec-fetch-mode", "cors");
        httpConn.setRequestProperty("sec-fetch-site", "same-origin");
        httpConn.setRequestProperty("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
        httpConn.setRequestProperty("x-set-application-id", "7c67b2a7-6ae0-4ee6-9ddb-73b37959081a");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("productIds", productIds);
        jsonObject.put("mediaTypes", List.of("images"));
        jsonObject.put("category", true);
        jsonObject.put("status", true);
        jsonObject.put("brand", true);
        jsonObject.put("propertyTypes", List.of("KEY"));
        jsonObject.put("propertiesConfig", new JSONObject().put("propertiesPortionSize", 5));
        jsonObject.put("multioffer", false);

        httpConn.setDoOutput(true);
        OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
        writer.write(jsonObject.toString());
        writer.flush();
        writer.close();
        httpConn.getOutputStream().close();

        InputStream responseStream = httpConn.getResponseCode() / 100 == 2
                ? httpConn.getInputStream()
                : httpConn.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";

        return objectMapper.readValue(response, ListResponse.class).getBody().getProducts();
    }

}
