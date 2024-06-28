package org.soap.restapiexample.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rpc")
public class RpcApiController {

    private final Map<String, RpcMethod> methods = new HashMap<>();

    public RpcApiController() {
        methods.put("add", this::add);
        methods.put("subtract", this::subtract);
    }

    @PostMapping
    public Map<String, Object> handleRpcCall(@RequestBody RpcRequest request) {
        RpcMethod method = methods.get(request.getMethod());
        if (method == null) {
            throw new IllegalArgumentException("Invalid method: " + request.getMethod());
        }
        return method.execute(request.getParams());
    }

    private Map<String, Object> add(Map<String, Object> params) {
        int a = (int) params.get("a");
        int b = (int) params.get("b");
        int result = a + b;
        Map<String, Object> response = new HashMap<>();
        response.put("result", result);
        return response;
    }

    private Map<String, Object> subtract(Map<String, Object> params) {
        int a = (int) params.get("a");
        int b = (int) params.get("b");
        int result = a - b;
        Map<String, Object> response = new HashMap<>();
        response.put("result", result);
        return response;
    }

    private interface RpcMethod {
        Map<String, Object> execute(Map<String, Object> params);
    }

    public static class RpcRequest {
        private String method;
        private Map<String, Object> params;

        // Getters and Setters
        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public Map<String, Object> getParams() {
            return params;
        }

        public void setParams(Map<String, Object> params) {
            this.params = params;
        }
    }
}
