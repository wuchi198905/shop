package com.example.shop.base.connt;

import com.example.shop.base.filter.JwtFilter;
import com.example.shop.base.json.ApiUtil;
import com.example.shop.pub.Utils.DateUtil;
import com.example.shop.pub.Utils.FileUtil;
import com.example.shop.pub.Utils.RedisUtils;
import com.example.shop.pub.bean.AttachFile;
import com.example.shop.pub.service.AttachFileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

@Component
public class DataFileUtil {
    @Autowired
    private AttachFileService attachFileService;
    private static DataFileUtil commonUtil;

    @PostConstruct
    public void init() {
        commonUtil = this;
        System.err.println("测试一下***********");
    }
    private static Logger logger = LoggerFactory.getLogger(DataFileUtil.class);
    public final static String DATA_DIR = "data/";
    public final static String ATTACH_IMAGE_DIR = "/data/attach/image/";
    public final static String url = "data/attach/file/";

    /**
     * 保存上传图片到目标文件夹
     * @param imgFile
     * @param
     * @return
     */
    public static String saveDBImage(MultipartFile imgFile) throws FileNotFoundException {
        // 保存路径
        String relativePath = ATTACH_IMAGE_DIR + DateUtil.now("yyyyMMdd");
        String path = ResourceUtils.getURL("classpath:").getPath()+"/static" + relativePath;
        File imgDir = new File(path);
        if (!imgDir.exists()) {
            imgDir.mkdirs();
        }

        // 文件名
        String uuid = ApiUtil.uuid();
        String filename = uuid + "." + FileUtil.getExtName(imgFile.getOriginalFilename());
        logger.debug("生成文件名：" + filename);
        File file = new File(imgDir, filename);

        // 保存到目标文件夹
        try (FileOutputStream outputStream = new FileOutputStream(file)){
            IOUtils.copy(imgFile.getInputStream(), outputStream);
        } catch (Exception e) {
        }
        String filepath = relativePath + "/" + file.getName();

        // 保存到数据库

        AttachFile attach = new AttachFile();
        attach.setFileId(uuid);
        attach.setFileType("A");
        attach.setFileName(filename);
        attach.setSaveName(filepath);
        attach.setState("A");
        attach.setCreateTime(new Date());

        commonUtil.attachFileService.insert(attach);

        return uuid;
    }

    /**
     * 删除图片到目标文件夹
     * @param fileId
     * @param
     * @return
     */
    public static Boolean delectDBImage(String fileId)throws FileNotFoundException {
        // 保存路径


        String path = ResourceUtils.getURL("classpath:").getPath()+"/static";
        AttachFile attach=commonUtil.attachFileService.selectById(fileId);
        String filepath = path+attach.getSaveName();
        File file = new File(filepath,attach.getFileName());
        // 保存到数据库
        attach.setSts("P");



        return commonUtil.attachFileService.updateById(attach);
    }

}
