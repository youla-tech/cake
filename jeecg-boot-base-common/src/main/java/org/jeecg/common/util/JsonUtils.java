package org.jeecg.common.util;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Kezai_Su on 2019/12/5.
 */
public class JsonUtils {

  public static String toJson(Object obj) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    return gson.toJson(obj);
  }
}
