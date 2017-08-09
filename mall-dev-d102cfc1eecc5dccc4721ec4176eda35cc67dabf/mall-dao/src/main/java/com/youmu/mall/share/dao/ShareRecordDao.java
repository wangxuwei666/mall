package com.youmu.mall.share.dao;

import org.apache.ibatis.annotations.Param;
import com.youmu.mall.share.domain.ShareRecord;

public interface ShareRecordDao {
	
	/**
	 * 增加有效分享记录
	 * @param shareRecord
	 */
	void insertShareRecord(ShareRecord shareRecord);
	
	/**
	 * 检索已分享过的访客信息 
	 * @param newOpenId
	 * @return
	 */
	ShareRecord getShareRecordByNewOpenId(@Param("newOpenId")String newOpenId);
	

	/**
	 * 根据userid和productid检索是否存在分享记录
	 * @param userid
	 * @return
	 */
	ShareRecord getShareRecordByUserId(@Param("userid")Long userId,@Param("productId")Long productId,@Param("newOpenId")String newOpenId);
}
