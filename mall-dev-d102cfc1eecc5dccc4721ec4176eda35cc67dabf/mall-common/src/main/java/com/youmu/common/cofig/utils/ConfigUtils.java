package com.youmu.common.cofig.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

public class ConfigUtils {
  
  /** config properties */
  private static Properties ps = new Properties();
  
  /** 日志 */
  private static Logger logger = LoggerFactory.getLogger(ConfigUtils.class);
  
  static {
    InputStream in = null;
    try {
      File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "config.properties");
      in = new FileInputStream(file);
      logger.debug("read properties :{}." + file.getName());
      ps.load(in);
    } catch (Exception e) {
      logger.error("读取配置config.properties文件错误", e);
    } finally {
      IOUtils.closeQuietly(in);
    }
  }
  
  public static String getString(String key) {
    return ps.getProperty(key, "");
  }

  public static String getAlidayuUrl() {
    return getString("alidayu_url");
  }

  public static String getAlidayuAppkey() {
    return getString("alidayu_app_key");
  }

  public static String getAlidayuSecret() {
    return getString("alidayu_app_secret");
  }

  public static String getAlidayuSMSType() {
    return getString("alidayu_sms_type");
  }

  public static String getAlidayuSMSSign() {
    return getString("alidayu_sms_sign");
  }

    /**
     * 获取阿里大于短信验证码模板代码
     * @return
     */
     public static String getRegistSmsCode() {
        return getString("alidayu_regist_sms");
     }

    /**
     * 获取原始图片的文件夹路径
     * @return
     */
    public static String getImageUploadPath() {
        return getString("img_dir");
    }

    /**
     * 支付宝支付应用私钥
     * @return
     */
    public static String getAlipayAppPrivateKey() {
        return getString("alipay_app_private_key");
    }

    /**
     * 获取支付宝支付的appid
     * @return
     */
    public static String getAlipayAppId() {
        return getString("alipay_app_id");
    }

    /**
     * 获取支付应用宝公钥
     * @return
     */
    public static String getAlipayAppPublicKey() {
        return getString("alipay_app_public_key");
    }
    
    /**
     * 获取支付宝公钥
     * @return
     */
    public static String getAlipayPublicKey() {
        return getString("alipay_public_key");
    }

    /**
     * 获取支付宝支付地址
     * @return
     */
    public static String getAlipayUrl() {
        return getString("alipay_pay_url");
    }

    /**
     * 当前产品的名称
     * @return
     */
    public static String getProductName() {
        return getString("product_name");
    }

    /**
     * 获取重置密码的短息模板编号
     * @return
     */
    public static String getResetPWDSmsCode() {
        return getString("alidayu_resetpwd_sms");
    }

    /**
     * 获取重置手机短息验证码的短息code
     * @return
     */
    public static String getResetMobileSmsCode() {
        return getString("alidayu_resetmob_sms");
    }

    /**
     * 获取微信的token
     * @return
     */
    public static String getWxToken() {
        return getString("wx_token");
    }
    
    /**
     * 获取微信的appid
     * @return
     */
    public static String getWxAppId() {
        return getString("wx_appid");
    }
    
    /**
     * 获取微信的app secret
     * @return
     */
    public static String getWxAppSecret() {
        return getString("wx_appsecret");
    }

    /**
     * 
     * @return
     */
    public static String getWeixinAppId() {
        return getString("weixin_app_id");
    }

    /**
     * 
     * @return
     */
    public static String getWeixinNotifyUrl() {
        return getString("weixin_notify_url");
    }

    /**
     * 
     * @return
     */
    public static String getWeixinPartnerId() {
        return getString("weixin_partner_id");
    }
    
    /**
     * 
     * @return
     */
    public static String getWeixinKey() {
        return getString("weixin_key");
    }
    
    /**
     * 图片访问路径
     */
    public static String getImgHost() {
        return getString("img_host");
    }

    /**
     * 获取微信自动登录的回掉地址.
     * @return
     */
    public static String getWxAutoLoginRedirectUrl() {
        return getString("wx_login_redirect_url");
    }

    /**
     * 获取前端的登录页面地址.
     * @return
     */
    public static String getLoginUrl() {
        return getString("login_url");
    }

    /**
     * 获取登陆成功后台前端跳转的url
     * @return
     */
    public static String getLoginSuccessUrl() {
        return getString("login_success_url");
    }

    
    /**
     * 跳转到绑定微信账号的界面
     * @return
     */
    public static String getBindWxAccountUrl() {
        return getString("bind_wx_account_url");
    }
    
    /**
     * 获取当前服务器地址
     * @return
     */
    public static String getServerHost() {
        return getString("server_host");
    }

    /**
     * 获取微信端的注册地址
     * @return
     */
    public static String getRegistUrl() {
        return getString("regist_url");
    }
    
    /**
     * 微信支付页面
     * @return
     */
    public static String getGoPayUrl() {
        return getString("wx_go_pay");
    }

    /**
     * 支付宝回调地址
     * @return
     */
    public static String getAlipayNotifyUrl() {
        return getString("alipay_notify_url");
    }

    /**
     * 支付宝seller_id
     * @return
     */
    public static String getAlipaySellerId() {
        return getString("alipay_seller_id");
    }
    
}
