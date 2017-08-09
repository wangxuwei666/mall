/**  
 * youmu Service Inc
 * All Rights Reserved @2017
 *
 */
package com.youmu.mall.test.coupon;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.youmu.common.context.GlobalConstant;
import com.youmu.common.context.UserContext;
import com.youmu.mall.dcr.dao.DcrOrderDao;
import com.youmu.mall.dcr.domain.DCPhase;
import com.youmu.mall.dcr.domain.DCROrder;
import com.youmu.mall.dcr.service.IDcrOrderService;
import com.youmu.mall.exception.BusinessException;
import com.youmu.mall.test.BaseTest;
import com.youmu.mall.user.domain.BusinessUser;

/**
 * 测试装修订单
 * @author zh
 * @version $Id: DCRTest.java, v 0.1 2017年3月12日 下午9:13:21 zh Exp $
 */
public class DCRTest extends BaseTest {
    
    @Resource
    private DcrOrderDao dcrOrderDao;
}
