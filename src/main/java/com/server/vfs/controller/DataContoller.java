package com.server.vfs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.server.vfs.service.DataServiceImpl;

import java.util.Map;

@RestController
@RequestMapping("/data")
public class DataContoller {

    @Autowired
    private DataServiceImpl dataService;

    @RequestMapping(value = "/get/{id}")
    public Map<String,Object> getData(@PathVariable("id") int id){
        return dataService.getData(id);
    }

//    @RequestMapping(value = "/upload",method = RequestMethod.POST)
//    public Map<String,Object> uploadFile(){
//        return dataService.uploadData();
//    }

}
