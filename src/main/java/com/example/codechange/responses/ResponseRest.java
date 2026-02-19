package com.example.codechange.responses;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Getter
public class ResponseRest {
    private ArrayList<Map<String, String>> metadata = new ArrayList<>();

    public void setMetadata(String tipo, String codigo, String data) {
        HashMap<String, String> map = new HashMap<>();

        map.put("tipo", tipo);
        map.put("código", codigo);
        map.put("data", data);

        metadata.add(map);
    }
}
