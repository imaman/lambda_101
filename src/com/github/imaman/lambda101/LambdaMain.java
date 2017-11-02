package com.github.imaman.lambda101;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaMain implements RequestHandler<Map<String, Object>, Map<String, Object>> {
  public Map<String, Object> handleRequest(Map<String, Object> map, Context context) {
    Map<String, Object> ret = new HashMap<>();

    ret.put("statusCode", "200");
    String body = String.format(
        "<html><body><h1>AWS Lambda (" + getClass().getName() + ") is up and running</h1><h2>#Inputs=%s</h2></body></html>\n",
        map.size());
    ret.put("body", body);
    context.getLogger().log("procesing request with map=" + map.entrySet().stream()
        .sorted(Comparator.comparing(e -> e.getKey()))
        .map(Object::toString)
        .collect(Collectors.joining("  \n")));

    Map<String, Object> headers = new HashMap<>();
    headers.put("Content-Type", "text/html");
    ret.put("headers", headers);

    return ret;
  }
}
