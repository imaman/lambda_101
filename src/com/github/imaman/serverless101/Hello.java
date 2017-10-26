package com.github.imaman.serverless101;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class Hello implements RequestHandler<Map<String, Object>, Map<String, Object>> {
  public Map<String, Object> handleRequest(Map<String, Object> map, Context context) {
    Map<String, Object> ret = new HashMap<>();

    ret.put("statusCode", "200");

    String input = map.entrySet().stream().map(x -> x.toString())
        .collect(Collectors.joining("<br>\n"));
    String body = "<html><body><h1>AWS Lambda 101 is a _GO_, we are at $DATE$ (Map generated!)</h1>\n<h2>$IN$</h2>\n</body></html>\n";
    body = body.replace("$DATE$", new Date().toString()).replace("$IN$", input);
    ret.put("body", body);

    Map<String, Object> headers = new HashMap<>();
    headers.put("Content-Type", "text/html");
    ret.put("headers", headers);

    return ret;
  }
}
