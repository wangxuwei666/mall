/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.web.sys.product.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.youmu.common.page.utils.Page;
import com.youmu.common.result.utils.JsonResult;
import com.youmu.common.result.utils.JsonResultUtils;
import com.youmu.common.upload.utils.ImageUploadUtils;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.exception.ParamException;
import com.youmu.mall.product.domain.Product;
import com.youmu.mall.product.query.ProductQuery;
import com.youmu.mall.product.service.IProductService;
import com.youmu.mall.product.vo.ProductSysVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 管理后台-商品
 * @author yujiahao
 * @version $Id: ProductController.java, v 0.1 2017年2月26日 上午11:43:17 yujiahao Exp $
 */
@RestController
@RequestMapping("/product/")
@Api("商品管理")
public class ProductController {
    
    @Autowired
    private IProductService productService;
    
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @ApiOperation(value = "管理后台商品 - 根据商品id查询明细")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "productId", value = "id", required = true, dataType = "Long", paramType = "query"),
    })
    @RequestMapping(value ="getProductSysVoById",method=RequestMethod.POST)
    public JsonResult getProductSysVoById(Long productId){
        if(productId == null){
            throw new ParamException("未找到该商品");
        }
        ProductSysVo vo = productService.getProductSysVoById(productId);
        return JsonResultUtils.ok(vo);
    }
    
    @ApiOperation(value = "管理后台商品列表（商品列表+库存紧张）", consumes="application/x-www-form-urlencoded")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "keywords", value = "关键词", required = false, dataType = "Long", paramType = "query"),
        @ApiImplicitParam(name = "businessTypeId", value = "商户类别id", required = false, dataType = "Long", paramType = "query"),
        @ApiImplicitParam(name = "status", value = "状态", required = false, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "stock", value = "库存量(默认值为5，表示小于等于5的库存对应的商品)", required = false, dataType = "Long", paramType = "query"),
        @ApiImplicitParam(name = "pageNum", value = "当前页", required = true, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "页码尺寸", required = true, dataType = "Integer", paramType = "query")
    })
    @RequestMapping(value ="getProductSysVo",method=RequestMethod.POST)
    public JsonResult getProductSysVo(ProductQuery productQuery){
        if(productQuery == null){
            throw new ParamException("查询对象为空！");
        }
        Page<ProductSysVo> page = productService.getProductSysVo(productQuery);
        return JsonResultUtils.ok(page);
    }
    
    
    @ApiOperation(value = "添加商品")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "name", value = "商品名称", required = true, dataType = "Long", paramType = "form"),
        @ApiImplicitParam(name = "businessId", value = "商户id", required = true, dataType = "Long", paramType = "form"),
        @ApiImplicitParam(name = "promotionPrice", value = "促销价", required = false, dataType = "Double", paramType = "form"),
        @ApiImplicitParam(name = "oldPrice", value = "原价", required = true, dataType = "Double", paramType = "form"),
        @ApiImplicitParam(name = "categoryId", value = "分类id", required = true, dataType = "int", paramType = "form"),
        @ApiImplicitParam(name = "digest", value = "商品摘要", required = false, dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "total", value = "商品的库存数量", required = true, dataType = "Integer", paramType = "form"),
        @ApiImplicitParam(name = "status", value = "商品的状态 0初始  1已上架  2已下架", required = true, dataType = "Integer", paramType = "form"),
        @ApiImplicitParam(name = "thumbnail", value = "缩略图", required = true, dataType = "String", paramType = "form"),
        @ApiImplicitParam(name="productImgs[]", value="商品图片,数组逗号隔开", dataType="string[]", paramType="form"),
        @ApiImplicitParam(name = "intro", value = "商品的描述，富文本编辑框，保存的内容为，使用editor.getContent()方法获得的编辑器的内容", required = false, dataType = "String", paramType = "form")
    })
    @RequestMapping(value ="insert", method=RequestMethod.POST, consumes="application/x-www-form-urlencoded")
    public JsonResult insert(@RequestParam("productImgs[]") String[] productImgs,String thumbnail,Product product){
        if(product == null){
            throw new ParamException("商品对象为空！");
        }
        if(productImgs.length < 1) {
            throw new ParamException("商品图片不能为空");
        }
        if(productImgs.length >= 6){
            throw new ParamException("添加商品图片不能超过5张");
        }
        ValidateUtils.checkNotNull("参数不能为空"
                , product.getName()
                ,product.getBusinessId()
                ,product.getOldPrice()
                ,product.getTotal()
                ,product.getStatus()
                );
        product.setThumbnail(ImageUploadUtils.uploadImage(thumbnail, 400, 400, false, ImageUploadUtils.PRODUCT_THUMBNAIL_IMG));
        product.setImages(ImageUploadUtils.uploadImage(productImgs, 750, 750, false, ImageUploadUtils.PRODUCT_IMGS));
        productService.insert(product);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value = "修改商品")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "商品id", required = true, dataType = "Long", paramType = "form"),
        @ApiImplicitParam(name = "name", value = "商品名称", required = true, dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "businessId", value = "商户id", required = true, dataType = "Long", paramType = "form"),
        @ApiImplicitParam(name = "promotionPrice", value = "促销价", required = false, dataType = "Double", paramType = "form"),
        @ApiImplicitParam(name = "oldPrice", value = "原价", required = false, dataType = "Double", paramType = "form"),
        @ApiImplicitParam(name = "digest", value = "商品摘要", required = false, dataType = "String", paramType = "form"),
        @ApiImplicitParam(name = "total", value = "商品的库存数量", required = false, dataType = "Integer", paramType = "form"),
        @ApiImplicitParam(name = "status", value = "商品的状态 0初始  1上架  2下架", required = false, dataType = "Integer", paramType = "form"),
        @ApiImplicitParam(name = "thumbnail", value = "缩略图", required = false, dataType = "String", paramType = "form"),
        @ApiImplicitParam(name="productImgs[]", value="商品图片,数组逗号隔开", required = false,dataType="String[]", paramType="form"),
        @ApiImplicitParam(name = "intro", value = "商品的描述", required = false, dataType = "String", paramType = "form")
    })
    @RequestMapping(value ="update", method=RequestMethod.POST, consumes="application/x-www-form-urlencoded")
    public JsonResult update(@RequestParam(value="productImgs[]", required = false)String[] productImgs,Product product){
        if(product == null){
            throw new ParamException("商品为空！");
        }
        if(!StringUtils.isBlank(product.getThumbnail())){
            product.setThumbnail(ImageUploadUtils.uploadImage(product.getThumbnail(), 400, 400, false, ImageUploadUtils.PRODUCT_THUMBNAIL_IMG));
        }else {
            product.setThumbnail(null);
        }
        if(productImgs != null && productImgs.length > 0){
            if(productImgs.length >= 6){
                throw new ParamException("添加商品图片不能超过5张");
            }
            product.setImages(ImageUploadUtils.uploadImage(productImgs, 750, 750, false, ImageUploadUtils.PRODUCT_IMGS));
        }
        
        productService.update(product);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value = "商品上架")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "productId", value = "商品id", required = true, dataType = "Long", paramType = "query"),
    })
    @RequestMapping(value ="putawayById",method=RequestMethod.POST)
    public JsonResult putawayById(Long productId){
        if(productId == null){
            throw new ParamException("商品id为空！");
        }
        productService.putawayById(productId);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value = "商品下架")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "productId", value = "商品id", required = true, dataType = "Long", paramType = "query"),
    })
    @RequestMapping(value ="outstockById",method=RequestMethod.POST)
    public JsonResult outstockById(Long productId){
        if(productId == null){
            throw new ParamException("商品id为空！");
        }
        productService.outstockById(productId);
        return JsonResultUtils.ok();
    }
    
    @ApiOperation(value = "商品库存紧张数目")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "status", value = "状态", required = false, dataType = "Integer", paramType = "query"),
        @ApiImplicitParam(name = "stock", value = "库存量(默认值为5，表示小于等于5的库存对应的商品)", required = false, dataType = "Long", paramType = "query")
    })
    @RequestMapping(value ="getShortageOfStockNumber",method=RequestMethod.POST)
    public JsonResult getShortageOfStockNumber(ProductQuery productQuery){
        ValidateUtils.checkNotNull("参数异常", productQuery.getStock(),productQuery.getStatus());
        long stockNumber = productService.getShortageOfStockNumber(productQuery);
        return JsonResultUtils.ok(stockNumber);
    }
    
    @ApiOperation(value = "删除商品")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "productId", value = "商品id", required = true, dataType = "long", paramType = "query"),
    })
    @RequestMapping(value ="deleteProductById",method=RequestMethod.POST)
    public JsonResult deleteProductById(Long productId){
        ValidateUtils.checkNotNull("参数异常", productId);
        productService.deleteProductById(productId);
        return JsonResultUtils.ok();
    }
    
    /**
     * 富文本编辑框 -上传图片接口
     * 
     * @param
     */
    @RequestMapping(value="/uploadImages", method=RequestMethod.POST)
    @ApiOperation(value = "富文本编辑框 -上传图片接口")
    public void uploadImages(HttpServletRequest request, HttpServletResponse response, MultipartFile upfile){
        String result = null;
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            String path  = ImageUploadUtils.uploadImgDefault(upfile, ImageUploadUtils.UM_IMGS);
            result = "{\"name\":\""+ "ue.jpg" +"\", \"originalName\": \""+ "ue.jpg" +"\", \"size\": "+ upfile.getSize() +", \"state\": \""+ "SUCCESS" +"\", \"type\": \""+ "." +ImageUploadUtils.DEFAULT_EXT +"\", \"url\": \""+ path +"\"}";
        }  catch (Exception e) {
            logger .error("ueditor 文件上传出错", e);
            result = "{\"name\":\""+ "ue.jpg" +"\", \"originalName\": \""+ "ue.jpg" +"\", \"size\": "+ "" +", \"state\": \""+ "UNKNOWN" +"\", \"type\": \""+ "." +ImageUploadUtils.DEFAULT_EXT +"\", \"url\": \""+ "" +"\"}";
        }
        String callback = request.getParameter("callback");
        result = result.replaceAll( "\\\\", "\\\\" );
        
        try {
            if( callback == null ){
                response.getWriter().print( result );
            }else{
                response.getWriter().print("<script>"+ callback +"(" + result + ")</script>");
            }
        } catch (IOException e) {
            logger.error("图片上传io异常", e);
        }
    }
}
