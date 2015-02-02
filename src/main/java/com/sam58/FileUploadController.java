package com.sam58;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

/**
 *
 * Created by sam158 on 02.02.2015.
 */
@Controller
public class FileUploadController {


    @RequestMapping(value="/upload", method= RequestMethod.GET, produces={"text/html; charset=UTF-8"} )
    public @ResponseBody String provideUploadInfo(){

        return "Вы можете загружать";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,@RequestParam("file")MultipartFile file){
        if(!file.isEmpty()){
            try{
                File f = new File(name+"_upload");
                f.createNewFile();
                FileOutputStream fileOutputStream=new FileOutputStream(f);
                fileOutputStream.write(file.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
                return "upload success";
            }catch (Exception e){
                return "ошибка закрузки файла "+name+" "+e.getMessage();
            }
        }
        return name+ " is empty";
    }
}
