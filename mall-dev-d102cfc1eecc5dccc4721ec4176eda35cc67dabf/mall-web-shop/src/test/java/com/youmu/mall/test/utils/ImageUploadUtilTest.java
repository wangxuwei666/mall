/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.test.utils;

import static org.junit.Assert.*;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.youmu.common.upload.utils.ImageUploadUtils;
import com.youmu.mall.test.BaseTest;

/**
 * 文件上传工具类测试
 * @author zh
 * @version $Id: ImageUploadUtilTest.java, v 0.1 2017年3月12日 下午6:21:14 zh Exp $
 */
public class ImageUploadUtilTest extends BaseTest {
    
    @Test
    public void testGetImgUrl() throws Exception {
        String base64 = FileUtils.readFileToString(new File("C:\\Users\\zh\\Desktop\\test_img.txt"));
        ImageUploadUtils.uploadImage(base64, 150, 150, false, ImageUploadUtils.BANNER_IMG);
    }
    
}
