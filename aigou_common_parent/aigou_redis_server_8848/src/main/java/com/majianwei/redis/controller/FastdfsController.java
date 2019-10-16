package com.majianwei.redis.controller;

import com.majianwei.redis.util.FastDfsApiOpr;
import com.majianwei.util.AjaxResult;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * fastdfs:
 * 上传
 * 下载
 * 删除
 */
@RestController
@RequestMapping("/common")
public class FastdfsController {
    /**
     * 文件上传
     * @param file
     * @return
     */
    @RequestMapping(value = "/fastdfs", method = RequestMethod.POST)
    public AjaxResult upload(@RequestBody MultipartFile file) {
        // xxddxjj.jpg  获取原始的文件名
        String originalFilename = file.getOriginalFilename();
        System.out.println(originalFilename);
        //获取后缀名
        String extName = FilenameUtils.getExtension(originalFilename);
        System.out.println(extName);
        try {
            String uploadResult = FastDfsApiOpr.upload(file.getBytes(), extName);
            return AjaxResult.me().setMsg("文件上传成功").setObject(uploadResult);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMsg("文件上传失败");
        }
    }
    /**
     * 文件的下载:
     * 页面直接使用路径的处理就搞定了。
     * < img src="http://172.16.16.157/group1/M00/00/01/rBAQnVyjc5WAOlbQAABgCMP6H50899.jpg"/>
     *
     * @param filePathName
     * @param response
     */
    // /group1/M00/00/01/rBAQnVyjc5WAOlbQAABgCMP6H50899.jpg===》 group1    M00/00/01/rBAQnVyjc5WAOlbQAABgCMP6H50899.jpg
    @RequestMapping(value = "/fastdfs", method = RequestMethod.GET)
    public void download(@RequestParam String filePathName, HttpServletResponse response) {
        // 从第一个开始截取到最后：group1/M00/00/01/rBAQnVyjc5WAOlbQAABgCMP6H50899.jpg
        String substring = filePathName.substring(1);
        System.out.println(substring);

        // group1
        String groupName = substring.substring(0, substring.indexOf("/"));
        System.out.println(groupName);
        // M00/00/01/rBAQnVyjc5WAOlbQAABgCMP6H50899.jpg
        String fileName = substring.substring(substring.indexOf("/") + 1);

        byte[] download = FastDfsApiOpr.download(groupName, fileName);
        InputStream input = null;
        OutputStream output=null;
        try {
            input=  new ByteArrayInputStream(download);
            output = response.getOutputStream();
            IOUtils.copy(input,output);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     *   调用sdk的删除方法：
     * @param args
     */

    /**
     * 前台：发送一个删除的请求：
     * @param filePathName  删除的组名和文件名：   /group1/M00/00/01/rBAQnVyjc5WAOlbQAABgCMP6H50899.jpg
     * @return
     */
    @RequestMapping(value = "/fastdfs", method = RequestMethod.DELETE)
    public AjaxResult delete(@RequestParam String filePathName) {

        // 从第一个开始截取到最后：group1/M00/00/01/rBAQnVyjc5WAOlbQAABgCMP6H50899.jpg
        String substring = filePathName.substring(1);
        System.out.println(substring);
        // group1
        String groupName = substring.substring(0, substring.indexOf("/"));
        System.out.println(groupName);
        // M00/00/01/rBAQnVyjc5WAOlbQAABgCMP6H50899.jpg
        String fileName = substring.substring(substring.indexOf("/") + 1);
        //  0 for success, none zero for fail (error code)
        int deleteResult = FastDfsApiOpr.delete(groupName, fileName);
        if(deleteResult==0){
            return AjaxResult.me().setMsg("删除成功");
        }else{
            return AjaxResult.me().setMsg("删除失败").setSuccess(false);
        }
    }
    public static void main(String[] args) {
        // /group1/M00/00/01/rBAQnVyjc5WAOlbQAABgCMP6H50899.jpg===》 group1    M00/00/01/rBAQnVyjc5WAOlbQAABgCMP6H50899.jpg
        String path = "/group1/M00/00/01/rBAQnVyjc5WAOlbQAABgCMP6H50899.jpg";

        // 从第一个开始截取到最后：group1/M00/00/01/rBAQnVyjc5WAOlbQAABgCMP6H50899.jpg
        String substring = path.substring(1);
        System.out.println(substring);

        // group1
        String groupName = substring.substring(0, substring.indexOf("/"));
        System.out.println(groupName);

        // M00/00/01/rBAQnVyjc5WAOlbQAABgCMP6H50899.jpg
        String fileName = substring.substring(substring.indexOf("/") + 1);
        System.out.println(fileName);
    }
}
