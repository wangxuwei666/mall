/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.common.upload.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import com.youmu.common.cofig.utils.ConfigUtils;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.exception.ParamException;

import net.coobird.thumbnailator.Thumbnails;

/**
 * 
 * @author zh
 * @version $Id: UploadUtils.java, v 0.1 2017年2月26日 上午10:37:00 yujiahao Exp $
 */
public class ImageUploadUtils {
    
    private static Logger log = LoggerFactory.getLogger(ImageUploadUtils.class);
    
    /** 
     * 不能超过的最大压缩宽 
     */  
    private static final int COMPRESS_MAX_WIDTH = 1920;  
  
    /** 
     * 不能超过的最大压缩长 
     */  
    private static final int COMPRESS_MAX_HEIGHT = 1080;  
  
    /** 
     * 默认图片压缩后的实际存放目录  
     */  
    private static final String DEFAULT_SAVE_PATH = ConfigUtils.getImageUploadPath();  
  
    /** 
     * 图片最大限制 单位MB 
     */  
    private static final int UPLOAD_IMAGE_MAX_SIZE_MB = 2;  
  
    /** 
     * 图片最大限制 单位B 
     */  
    private static final long UPLOAD_IMAGE_MAX_SIZE_B = UPLOAD_IMAGE_MAX_SIZE_MB * 1024 * 1024L; 
    
    /** 输出图片的质量 降低图片的大小 */
    private static final double DEFALUT_IMG_QUALITY = 1d;
  
    /** 
     * 允许的图片格式 
     */  
    private static final String UPLOAD_IMAGE_EXTS = "jpg/jpeg/png";  
    
    /** 默认的后缀 */
    public static final String DEFAULT_EXT = "jpg";
  
    /** 
     * 图片文件过大 提示 
     */  
    private static final String UPLOAD_IMAGR_SIZE_LIMIT = "图片文件过大，不能超过" + UPLOAD_IMAGE_MAX_SIZE_MB  
                                                          + "M";  
  
    /**  
     * 图片上传后缀 提示 
     */  
    private static final String UPLOAD_IMAGE_EXT_LIMIT = "图片格式只能为" + UPLOAD_IMAGE_EXTS;

    public static final String BUSINESS_TYPE_ICON = "bus_type/icon/";  
    
    /**商品图片*/
    public static final String PRODUCT_IMGS = "product/imgs/";
    
    /**商品缩略图片*/
    public static final String PRODUCT_THUMBNAIL_IMG = "product/thumbnail/";
    
    /**订单商品子项的图片*/
    public static final String ORDER_ITEM_IMG = "order/item/";
    
    /**特价商品banner图片*/
    public static final String PRODUCT_GROUP_BANNER = "product/group/banner/";
    
    /**Banner图片*/
    public static final String BANNER_IMG = "banner/";
    
    /** 商户营业执照图片路径  */
    public static final String BUSINESS_LICENSE_IMG = "business/license/";
    
    /** 商户门店图片 */
    public static final String BUSINESS_STORE_IMG = "business/store/";
    
    /** 装修贷款合同身份证图片 */
    public static final String DCR_ORDER_ID_CARTD_IMGS = "dcr/compact/idcard/";
    
    /** 装修贷款合同商户执照 */
    public static final String DCR_ORDER_LICENSE_IMGS = "dcr/compact/license/"; 
    
    /** 装修贷款合同照片 */
    public static final String DCR_ORDER_COMPACT_IMGS = "dcr/compact/compact/";

    /** 用户上传头像的图片文件夹 */
    public static final String USER_HEAD_IMAGE = "user/head_image/";

    /** 富文本图片上传路径 */
    public static final String UM_IMGS = "um/imgs/"; 
  
    public static String compressImage(MultipartFile multiFile, int width, int height, boolean isScale, String dirPath)  
    {  
        InputStream in = null;
        try {
            //判断图片格式  
            String ext = FilenameUtils.getExtension(multiFile.getOriginalFilename());  
            //将文件后缀转成小写  
            if (StringUtils.isNotBlank(ext))  
            {  
                ext = ext.toLowerCase();  
            }  
            if (!UPLOAD_IMAGE_EXTS.contains(ext))  
            {  
                log.warn(" file ext:" + ext + " not allowed in:" + UPLOAD_IMAGE_EXTS);  
                throw new ParamException(UPLOAD_IMAGE_EXT_LIMIT);  
            }  
            //判断图片大小  
            long size = multiFile.getSize();  
            if (size > UPLOAD_IMAGE_MAX_SIZE_B)  
            {  
                log.warn(" file size:" + size + " outof sizeMaxLimit:" + UPLOAD_IMAGE_MAX_SIZE_B);  
                throw new ParamException(UPLOAD_IMAGR_SIZE_LIMIT);  
            } 
            in = multiFile.getInputStream();
            String store = compress(in, ext, width, height, isScale,  dirPath); 
            log.debug(" file store in:" + store);  
            return store;
        } catch (IOException e) {
            log.error("文件上传错误", e);
            return null;
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
    
    /**
     * 压缩文件
     * @return
     */
    private static String compress(InputStream in, String ext, int width, int height, boolean isScale,  String dirPath) {
        String path = null;
        try {
            width = width > COMPRESS_MAX_WIDTH ? COMPRESS_MAX_WIDTH : width;
            height = height > COMPRESS_MAX_HEIGHT ? COMPRESS_MAX_HEIGHT : height;
            path = DEFAULT_SAVE_PATH + dirPath + UUID.randomUUID().toString().replaceAll("-", "") + "." + ext;
            File file = new File(path);
            File dir = file.getParentFile();
            if(!dir.exists()) {
                dir.mkdirs();
            }
            if(isScale) {
                BufferedImage image = ImageIO.read(in);
                double w = image.getWidth();
                double h = image.getHeight();
                if(w > width || h > height){
                    double rateW = width / w  ;
                    double rateH = height / h;
                    double rate = rateW < rateH ? rateW : rateH;
                    int new_w = (int) (w * rate);
                    int new_h = (int) (h * rate);
                    log.debug("scale origin {}, {} to {},{}.", w, h, new_w, new_h);
                    Thumbnails.of(image)
                        .size(new_w, new_h)
                        .keepAspectRatio(true)
                        .outputQuality(DEFALUT_IMG_QUALITY)
                        .outputFormat(ext)
                        .toFile(path);
                } else {
                    log.debug("scale origin {}, {} to {},{}.", w, h, w, h);
                    Thumbnails
                        .of(image)
                        .size((int)w, (int)h)
                        .outputQuality(DEFALUT_IMG_QUALITY)
                        .outputFormat(ext)
                        .toFile(path);
                }
            } else {
                    log.debug("to {},{}.", width, height);
                    Thumbnails
                        .of(in)
                        .size(width, height)
                        .keepAspectRatio(true)
                        .outputQuality(DEFALUT_IMG_QUALITY)
                        .outputFormat(ext)
                        .toFile(path);
            }
        } catch (IOException e) {
            log.error("压缩文件报错", e);
            throw new BusinessException("文件上传错误");
        }
        log.debug("upload file in file path {}.", path);
        return path.substring(DEFAULT_SAVE_PATH.length());
    }

    /**
     * 上传文件使用默认的设置.
     * @param licenseImg
     */
    public static String uploadImgDefault(MultipartFile img, String dirPath) {
        return compressImage(img, COMPRESS_MAX_WIDTH, COMPRESS_MAX_HEIGHT, true, dirPath);
    }
    
    /**
     * 上传文件使用默认的设置.
     * @param licenseImg
     */
    public static List<String> uploadImgDefault(String dirPath, MultipartFile[] imgs) {
        List<String> result = new ArrayList<>();
        for (MultipartFile img : imgs) {
            log.debug("compress {}", img);
            result.add(compressImage(img, COMPRESS_MAX_WIDTH, COMPRESS_MAX_HEIGHT, true, dirPath));
        }
        return result;
    }
    
    /**
     * 上传图片
     */
    public static String uploadImage(String imageBase64, int width, int height, boolean isScale, String dirPath) {
        // base64解码
        byte[] decodeImageBase64Bytes = Base64Utils.decodeFromString(imageBase64);
        ByteArrayInputStream in = new ByteArrayInputStream(decodeImageBase64Bytes);
        return compress(in, DEFAULT_EXT, width, height, isScale, dirPath);
    }
    
    /**
     * 上传图片
     */
    public static String uploadImage(String imageBase64, String ext, int width, int height, boolean isScale, String dirPath) {
        // base64解码
        byte[] decodeImageBase64Bytes = Base64Utils.decodeFromString(imageBase64);
        ByteArrayInputStream in = new ByteArrayInputStream(decodeImageBase64Bytes);
        return compress(in, ext, width, height, isScale, dirPath);
    }
    
    public static List<String>  uploadImage(String[] base64Imgs,int width, int height, boolean isScale,String dirPath) {
        // base64解码
        List<String> result = new ArrayList<>();
        for (String img : base64Imgs) {
            result.add(uploadImage(img, width, height, isScale, dirPath));
        }
        return result;
    }
    
    /**
     * 上传图片 使用默认的压缩方式
     */
    public static List<String>  uploadImageDefault(String dirPath, String... base64Imgs) {
        // base64解码
        List<String> result = new ArrayList<>();
        for (String img : base64Imgs) {
            result.add(uploadImage(img, COMPRESS_MAX_WIDTH, COMPRESS_MAX_HEIGHT, true, dirPath));
        }
        return result;
    }
    
    /**
     * 上传图片 使用默认的压缩方式
     */
    public static String  uploadImageDefault(String dirPath, String imgBase64) {
        return uploadImage(imgBase64, COMPRESS_MAX_WIDTH, COMPRESS_MAX_HEIGHT, true, dirPath);
    }
    
    /**
     * 获取图片完整路径
     * 
     * @param uri
     * @return
     */
    public static String fillPath(String uri) {
        if (StringUtils.isNotBlank(uri)) {
            String imgHostStatic = ConfigUtils.getImgHost();
            imgHostStatic = imgHostStatic.endsWith("/") ? StringUtils.removeEnd(imgHostStatic, "/") : imgHostStatic;
            uri = StringUtils.startsWith(uri, "/") ? uri : StringUtils.join("/", uri);
            return StringUtils.join(imgHostStatic, uri);
        }
        return null;
    }

    /**
     * 填充图片集合
     * @param idCardImgs
     * @return
     */
    public static List<String> fillPath(List<String> imgs) {
        if(imgs == null || imgs.isEmpty()) {
            return null;
        }
        String[] results = new String[imgs.size()];
        int i = 0;
        for (String img : imgs) {
            results[i] = fillPath(img);
            i++;
        }
        return Arrays.asList(results);
    }
    
}
