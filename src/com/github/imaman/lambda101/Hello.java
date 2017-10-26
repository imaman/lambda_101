package com.github.imaman.lambda101;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Hello implements RequestHandler<Map<String, Object>, Map<String, Object>> {
  public Map<String, Object> handleRequest(Map<String, Object> map, Context context) {
    Map<String, Object> ret = new HashMap<>();

    ret.put("statusCode", "200");
    String body = String.format(
        "<html><body><h1>AWS Lambda is up and running</h1><h2>#inputs=%s</h2></body></html>\n",
        map.size());
    ret.put("body", body);

    Map<String, Object> headers = new HashMap<>();
    headers.put("Content-Type", "text/html");
    ret.put("headers", headers);

    return ret;
  }
}
