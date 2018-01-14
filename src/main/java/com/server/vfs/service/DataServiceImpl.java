package com.server.vfs.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DataServiceImpl implements DataService {

    @Override
    public Map<String, Object> getData(int id) {
        if(id < 1 || id > 3 ){
            return Ajax.errorResponse("Нет такого пункта");
        }
        File file;
        List<String> arr=new ArrayList<>();
        try{

            if(id == 2) {
                file = new File("vanityfair.json");
            }else if(id == 1){
                file = new File("peace.json");
            }else{
                file = new File("something.json");
            }
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            while (line != null) {
                arr.add(line);
                line = reader.readLine();
            }

        }catch (Exception e){
            return Ajax.errorResponse("Нет файла");
        }
        return Ajax.successResponse(arr);
    }

}
