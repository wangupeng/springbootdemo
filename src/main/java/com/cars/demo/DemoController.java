package com.cars.demo;

import org.apache.tomcat.util.http.fileupload.FileUploadBase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.naming.SizeLimitExceededException;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * Created by wangyupeng on 2018/4/3 11:07
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/file")
    public String file(){
        return"demo/file";
    }

    /**
     * 文件上传具体实现方法;
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file")MultipartFile file){
        if(!file.isEmpty()){
            try {
                File targetFile = new File("upload");
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                /*
                 * 1、文件路径；2、文件名；3、文件格式;4、文件大小的限制;
                 */
                File f = new File("upload/"+file.getOriginalFilename());
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(f));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return"上传失败,"+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return"上传失败,"+e.getMessage();
            } catch (Exception e){
                e.printStackTrace();
                return"上传失败,"+e.getMessage();
            }
            return"上传成功";
        }else{
            return"上传失败，因为文件是空的.";
        }

    }

    /**
     * 多文件具体上传时间，主要是使用了MultipartHttpServletRequest和MultipartFile
     * @param request
     * @return
     */
    @RequestMapping(value="/batch/upload", method= RequestMethod.POST)
    public@ResponseBody
    String handleFileUpload(HttpServletRequest request){
        File targetFile = new File("upload");
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }

        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;

        for (int i =0; i< files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();

                    File f = new File("upload/"+file.getOriginalFilename());

                    stream = new BufferedOutputStream(new FileOutputStream(f));
                    stream.write(bytes);
                    stream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    return"上传失败,"+e.getMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                    return"上传失败,"+e.getMessage();
                } catch (Exception e){
                    e.printStackTrace();
                    return"上传失败,"+e.getMessage();
                }
            } else {
                return"上传失败，因为文件是空的.";
            }
        }
        return"上传成功";
    }
}
