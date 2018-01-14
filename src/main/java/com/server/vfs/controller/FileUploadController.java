package com.server.vfs.controller;

import com.server.vfs.parsing.TextParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Controller
public class FileUploadController {

    @RequestMapping(value = "/upload",method = RequestMethod.GET)
    public @ResponseBody String provideUploadInfo(){
        return "Ничего нет";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
                                                 @RequestParam("file") MultipartFile file){
        if(!file.isEmpty()){
            try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
                        new File(name+"-uploaded.txt")));
                stream.write(bytes);
                stream.close();
            }catch (Exception e){
                return "Не удалось загрузить файл " + name + " => " + e.getMessage();
            }
        }else {
            return "Вам не удалось загрузить файл, " + name + " так как он пустой!";
        }
        TextParser textParser = new TextParser(name);
        textParser.processing();
        return "Загрузка и обработка прошла успешно";
    }

}
