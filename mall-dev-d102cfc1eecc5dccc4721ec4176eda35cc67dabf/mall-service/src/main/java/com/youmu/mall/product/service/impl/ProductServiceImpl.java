/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.product.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youmu.common.context.GlobalConstant;
import com.youmu.common.context.UserContext;
import com.youmu.common.global.constant.StatusConstant;
import com.youmu.common.order.utils.OrderUtil;
import com.youmu.common.page.utils.Page;
import com.youmu.common.validate.utils.ValidateUtils;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.ks.service.IKSService;
import com.youmu.mall.product.dao.ProductDao;
import com.youmu.mall.product.dao.ProductGroupDao;
import com.youmu.mall.product.domain.Product;
import com.youmu.mall.product.domain.ProductGroupDetail;
import com.youmu.mall.product.dto.GroupProductIngoDto;
import com.youmu.mall.product.query.ProductQuery;
import com.youmu.mall.product.service.IProductService;
import com.youmu.mall.product.vo.ProductDetailShopVo;
import com.youmu.mall.product.vo.ProductSysVo;
import com.youmu.mall.product.vo.ProductVo;

/**
 * 
 * @author yujiahao
 * @version $Id: ProductServiceImpl.java, v 0.1 2017年2月25日 下午4:44:35 yujiahao Exp $
 */
@Service
public class ProductServiceImpl implements IProductService{
    
    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private ProductGroupDao productGroupDao;

    @Resource
    private IKSService ksService;


    /** 
     * @see com.youmu.mall.product.service.IProductService#insert(com.youmu.mall.product.domain.Product)
     */
    @Override
    @Transactional
    public void insert(Product product) {
        if(product.getOldPrice().compareTo(ValidateUtils.BIGDECIMAL_ZERO)<=0){
            throw new BusinessException("原价不能小于0");
        }
        
        if(product.getPromotionPrice() != null && product.getPromotionPrice().compareTo(ValidateUtils.BIGDECIMAL_ZERO)<0){
            throw new BusinessException("促销价不能为负");
        }
        if(product.getPromotionPrice() != null && product.getPromotionPrice().compareTo(product.getOldPrice())>0){
            throw new BusinessException("促销价不能大于原价");
        }
        if(product.getTotal()<0){
            throw new BusinessException("商品总数不能为负");
        }
        /*
         * 1、创建并保存商品缩略图
         * 2、product.set缩略图
         * 3、保存商品
         * 4、上传多图片，并保存图片路径
         */
        if(productDao.getRepeatCount(product)>0){
            throw new BusinessException("商品名称重复");
        }
        //商品编码规则
        product.setSn(OrderUtil.createProductSn(UserContext.getLongUserId()));
        //保存商品主表
        productDao.insertAndGetId(product);
        if(product.getId() != null){
            List<String> images = product.getImages();
            productDao.batchSaveProductImages(product.getId(),images);
        }else {
            throw new BusinessException("未获取商品id");
        }
    }

    /** 
     * @see com.youmu.mall.product.service.IProductService#putaway(com.youmu.mall.product.domain.Product)
     */
    @Override
    public void putawayById(Long productId) {
        Product product = productDao.getById(productId);
        if(product == null){
            throw new BusinessException("未查询到该商品");
        }
        if(product.getStatus() == GlobalConstant.ONE){
            throw new BusinessException("该商品已经上架");
        }
        productDao.putawayById(productId);
    }

    /** 
     * @see com.youmu.mall.product.service.IProductService#update(com.youmu.mall.product.domain.Product, org.springframework.web.multipart.MultipartFile, org.springframework.web.multipart.MultipartFile[])
     */
    @Override
    @Transactional
    public void update(Product product) {
        
        Long productId = product.getId();
        if(product.getOldPrice().compareTo(ValidateUtils.BIGDECIMAL_ZERO)<0){
            throw new BusinessException("原价不能为负");
        }
        if(product.getPromotionPrice() != null && product.getPromotionPrice().compareTo(ValidateUtils.BIGDECIMAL_ZERO)<0){
            throw new BusinessException("促销价不能为负");
        }
        if(product.getPromotionPrice() != null && product.getPromotionPrice().compareTo(product.getOldPrice())>0){
            throw new BusinessException("促销价不能大于原价");
        }
        if(product.getTotal()<0){
            throw new BusinessException("商品总数不能为负");
        }
        /*
         * 如有图片就先删除图片，再添加
         * 更新缩略图
         * 更行商品图片
         * 保存商品
         */
        if(product.getImages() != null && product.getImages().size() != 0){
            productDao.deleteImagesById(productId);
            productDao.batchSaveProductImages(productId,product.getImages());
        }
        //若状态为已下架，则删除活动组里对应的记录
        if(product.getStatus()!= null && product.getStatus() == StatusConstant.TWO && productId != null){
            outStockFromGroup(productId);
        }
        //更新商品
        productDao.update(product);
    }

    /**
     * 若商品状态为 下架，则需要扣减活动中的商品
     * 1.查询下架商品在活动组里的活动数量
     * 2.删除活动商品明细中的记录(若1查询到数据)
     * 3.扣减活动主表中的活动商品信息的总数量，剩余数量
     * @param product
     * @param productId
     */
    private void outStockFromGroup(Long productId) {
        ProductGroupDetail detail = productGroupDao.getDetailInfoByProductId(productId);
        if(detail != null){
            GroupProductIngoDto groupDto = new GroupProductIngoDto();
            groupDto.setGroupId(detail.getGroupId());
            groupDto.setGroupDetailId(detail.getItemId());
            groupDto.setVersion(detail.getVersion());
            groupDto.setQuantity(detail.getQuantity());
            productGroupDao.deleteProductGroupDetailsById(detail.getItemId());
            productGroupDao.reduceGroupStockDetailById(groupDto);
            productGroupDao.reduceGroupStockById(detail.getGroupId(), detail.getQuantity());
        }
    }

    /** 
     * @see com.youmu.mall.product.service.IProductService#outstockById(java.lang.Long)
     */
    @Override
    public void outstockById(Long productId) {
        Product product = productDao.getById(productId);
        if(product == null){
            throw new BusinessException("未查询到该商品");
        }
        if(product.getStatus() == GlobalConstant.TWO){
            throw new BusinessException("该商品已经下架");
        }
        productDao.outstockById(productId);
    }

    /** 
     * @see com.youmu.mall.product.service.IProductService#getProductVo(com.youmu.mall.product.query.ProductQuery)
     */
    @Override
    public Page<ProductVo> getProductVoByType(ProductQuery productQuery) {
        Page<ProductVo> page = new Page<>(productQuery.getPageNum(), productQuery.getPageSize());
        page.setData(productDao.getProductVoByType(productQuery));
        page.setTotal(productDao.getByCount(productQuery));
        
        // 搜索热词统计 搜索有内容的热词
        ksService.saveSearchKeywords(IKSService.PRODUCT_SEARCH_KEY, productQuery.getKeywords());
        return page;
    }

    /** 
     * @see com.youmu.mall.product.service.IProductService#getProductSysVo(com.youmu.mall.product.query.ProductQuery)
     */
    @Override
    public Page<ProductSysVo> getProductSysVo(ProductQuery productQuery) {
        Page<ProductSysVo> page = new Page<>(productQuery.getPageNum(), productQuery.getPageSize());
        page.setData(productDao.getProductSysVo(productQuery));
        page.setTotal(productDao.getCountBySysVo(productQuery));
        return page;
    }

    /** 
     * @see com.youmu.mall.product.service.IProductService#getProductDetailShopVo(java.lang.Long)
     */
    @Override
    public ProductDetailShopVo getProductDetailShopVo(Long productId) {
        return productDao.getProductDetailShopVo(productId);
    }

    /** 
     * @see com.youmu.mall.product.service.IProductService#getShortageOfStockNumber(com.youmu.mall.product.query.ProductQuery)
     */
    @Override
    public long getShortageOfStockNumber(ProductQuery productQuery) {
        return productDao.getByCount(productQuery);
    }

    /** 
     * @see com.youmu.mall.product.service.IProductService#deleteProductById(java.lang.Long)
     */
    @Override
    public void deleteProductById(Long productId) {
        Product productInDB = productDao.getById(productId);
        ValidateUtils.checkNotNull("未找到该商品", productInDB);
//        if(productInDB.getStatus() == StatusConstant.ONE){
//            throw new ParamException("该商品已经上架,请先下架，再进行删除");
//        }
        //删除活动组里对应的商品记录
        outStockFromGroup(productId);
        //删除商品
        Product product = new Product();
        product.setId(productId);
        product.setDeleteFlag(StatusConstant.ONE);
        productDao.update(product);
    }

    /** 
     * @see com.youmu.mall.product.service.IProductService#getProductSysVoById(java.lang.Long)
     */
    @Override
    public ProductSysVo getProductSysVoById(Long productId) {
        return productDao.getProductSysVoById(productId);
    }

    /** 
     * @see com.youmu.mall.product.service.IProductService#findProductSearchHotKeywords()
     */
    @Override
    public List<String> findProductSearchHotKeywords() {
        return ksService.findHotSearchKeywords(IKSService.PRODUCT_SEARCH_KEY, 8);
    }
    

    
}
