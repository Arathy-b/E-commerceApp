package com.thinkpalm.ecommerceApp.Util;

import java.util.HashMap;
import java.util.Map;

public class AppContext {
    private static String email="email";

    private static ThreadLocal<Map<String,String>> appContext=ThreadLocal.withInitial(HashMap::new);
    public static String getEmail(){

        Map<String,String> contextMap=appContext.get();
        return contextMap.get(email);
    }
    public static void setEmail(String email){
        Map<String,String> contextMap=appContext.get();
         contextMap.put(email,email);

    }
    public static void clearContext(){
        appContext.remove();
    }
}
