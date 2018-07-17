package com.dream.moka.Api;


import com.dream.moka.Base.BaseListObserver;
import com.dream.moka.Bean.AboutBean;
import com.dream.moka.Bean.AddServiceUserCarResultBean;
import com.dream.moka.Bean.AddressListResultBean;
import com.dream.moka.Bean.AllActivityBean;
import com.dream.moka.Bean.AllOrderResultBean;
import com.dream.moka.Bean.BankCardBean;
import com.dream.moka.Bean.BankResultBean;
import com.dream.moka.Bean.BannerBean;
import com.dream.moka.Bean.BaoYangFangAnResultBean;
import com.dream.moka.Bean.CarBrandResultBean;
import com.dream.moka.Bean.CarCategoryResultBean;
import com.dream.moka.Bean.CarConfigureResultBean;
import com.dream.moka.Bean.CarDetailResultBean;
import com.dream.moka.Bean.CarResultBean;
import com.dream.moka.Bean.KeysBean;
import com.dream.moka.Bean.ListCouponBean;
import com.dream.moka.Bean.ChangeAddressResultBean;
import com.dream.moka.Bean.CitysResultBean;
import com.dream.moka.Bean.ConfirmOrderResultBean;
import com.dream.moka.Bean.DriverAccountResultBean;
import com.dream.moka.Bean.DriverCenter.DriverOrderDetailBean;
import com.dream.moka.Bean.DriverDataInfoResultBean;
import com.dream.moka.Bean.DriverHomeResultBean;
import com.dream.moka.Bean.DriverResultBean;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.HotActivityBean;
import com.dream.moka.Bean.InComeResultBean;
import com.dream.moka.Bean.InTheRelayResultBean;
import com.dream.moka.Bean.IntegralBean;
import com.dream.moka.Bean.InvoiceBaseBean;
import com.dream.moka.Bean.InvoiceBean;
import com.dream.moka.Bean.Maintain.CarconfigureBean;
import com.dream.moka.Bean.Maintain.FreeMainTainBean;
import com.dream.moka.Bean.Maintain.FreeMaintainAllBean;
import com.dream.moka.Bean.Maintain.ProductSelectBean;
import com.dream.moka.Bean.Merchant.MerchantsBean;
import com.dream.moka.Bean.Merchant.RepairShopInfoBean;
import com.dream.moka.Bean.Message.CheckResultBean;
import com.dream.moka.Bean.Message.MessageDetailBean;
import com.dream.moka.Bean.Message.MessageListBean;
import com.dream.moka.Bean.Message.OderAddDetailBean;
import com.dream.moka.Bean.Message.OrderAddBean;
import com.dream.moka.Bean.MyAccountBean;
import com.dream.moka.Bean.NewsBean;
import com.dream.moka.Bean.NewsDetailBean;
import com.dream.moka.Bean.NomalQuestionBean;
import com.dream.moka.Bean.Online.OnlinerBean;
import com.dream.moka.Bean.OrderDetailBean;
import com.dream.moka.Bean.OrderDriverBean;
import com.dream.moka.Bean.OrderListResultBean;
import com.dream.moka.Bean.Other.CarsbBean;
import com.dream.moka.Bean.Other.DriverGuijiBean;
import com.dream.moka.Bean.Other.GuijiBean;
import com.dream.moka.Bean.Other.IncompanyBean;
import com.dream.moka.Bean.Other.OnlyStringBean;
import com.dream.moka.Bean.Other.UserGuijiBean;
import com.dream.moka.Bean.Other.VersionBean;
import com.dream.moka.Bean.PayConformAlpayResultBean;
import com.dream.moka.Bean.PayConformWechatResultBean;
import com.dream.moka.Bean.PayResultBean;
import com.dream.moka.Bean.PhoneCodeResultBean;
import com.dream.moka.Bean.PicUploadResultBean;
import com.dream.moka.Bean.PriceResultBean;
import com.dream.moka.Bean.ProductDetailBean;
import com.dream.moka.Bean.RepairAreaBean;
import com.dream.moka.Bean.SaveOrderResultBean;
import com.dream.moka.Bean.UserResultBean;
import com.dream.moka.Bean.WalletBean;
import com.dream.moka.Bean.WithDrawDataBean;
import com.dream.moka.Bean.WithDrawResultBean;
import com.dream.moka.Bean.XieYiBean;
import com.dream.moka.Bean.base.BaseBean;
import com.dream.moka.Bean.base.BaseListBean;
import com.dream.moka.Other.Const;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;
import rx.Observable;

/**
 * 接口集中类
 * Created by Administrator on 2017/4/7/007.
 */
public interface ApiService {

    //临时获取验证码
    @FormUrlEncoded
    @POST("login/getPhoneCode4App")
    Observable<BaseBean<PhoneCodeResultBean>> getPhoneCode(@Field("phone") String phone);

    //获取车品牌
    @FormUrlEncoded
    @POST("user/getCarBrand")
    Observable<BaseBean<CarBrandResultBean>> getCarBrand(@FieldMap Map<String,String> map);

    //获取车型
    @FormUrlEncoded
    @POST("user/getCarCategory")
    Observable<BaseListBean<CarCategoryResultBean>> getCarCategory(@Field("brandId") String brandId);

    //获取车配置
    @FormUrlEncoded
    @POST("user/getConfigure")
    Observable<BaseListBean<CarConfigureResultBean>> getConfigure(@Field("categoryId") String categoryId);

    /**
     * 首页相关
     */

    //获取banner列表
    @POST("index/getBannerList")
    Observable<BaseListBean<BannerBean>> getBannerList();

    //获取最新动态
    @POST("index/getNews")
    Observable<BaseListBean<NewsBean>> getNews();

    //通过id获取行业动态详情
    @FormUrlEncoded
    @POST("index/getNewsById")
    Observable<BaseBean<NewsDetailBean>> getNewsById(@Field("id") String id);

    //获取热门活动
    @POST("index/getHotActivity")
    Observable<BaseListBean<HotActivityBean>> getHotActivity();

    //通过id获取活动详情
    @FormUrlEncoded
    @POST("index/getActivityById")
    Observable<BaseBean<HotActivityBean>> getActivityById(@Field("id") String id);

    //获取常见问题
    @POST("index/getNomalQuestion")
    Observable<BaseListBean<NomalQuestionBean>> getNomalQuestion();

    //分页获取全部活动
    @FormUrlEncoded
    @POST("index/getActivityByPage")
    Observable<BaseBean<AllActivityBean>> getActivityByPage(@Field("pageNo") String pageNo, @Field("pageSize") String pageSize);

    /*
    *司机招募
     */
    //检查是否符合司机招募条件
    @FormUrlEncoded
    @POST("index/checkDriver")
    Observable<BaseBean<EmptyBean>> checkDriver(@Field("token") String token);

    //通过token获取司机信息
    @FormUrlEncoded
    @POST("index/getDriverByToken")
    Observable<BaseBean<DriverResultBean>> getDriverByToken(@Field("token") String token);
//
//    //保存司机申请
//    @FormUrlEncoded
//    @POST("index/saveDriver")
//    Observable<BaseBean<EmptyBean>> saveDriver(@FieldMap Map<String,String> map);

    /**
     * 登录注册相关
     */
    //注册
    @FormUrlEncoded
    @POST("login/register")
    Observable<BaseBean<UserResultBean>> register(@Field("phone") String phone, @Field("passWord") String passWord, @Field("xgToken") String xgToken,
                                                  @Field("xgType") String xgType,@Field("nickName") String nickName);

    //用户登录接口
    @FormUrlEncoded
    @POST("login/loginByPhone")
    Observable<BaseBean<UserResultBean>> loginByPhone(@Field("phone") String phone, @Field("passWord") String passWord, @Field("type") String type
            , @Field("xgToken") String xgToken, @Field("xgType") String xgType);// 0:验证码登录 1：密码登录

    //第三方登录接口
    @FormUrlEncoded
    @POST("login/loginByOthers")
    Observable<BaseBean<UserResultBean>> loginByOthers(@Field("openId") String openId, @Field("nickName") String nickName, @Field("headUrl") String headUrl
            , @Field("type") String type, @Field("xgToken") String xgToken, @Field("xgType") String xgType);

    //第三方登录绑定手机号
    @FormUrlEncoded
    @POST("login/loginByOthersBindPhone")
    Observable<BaseBean<UserResultBean>> loginByOthersBindPhone(@FieldMap Map<String,String> map);

    //用户退出登录接口
    @FormUrlEncoded
    @POST("login/loginOut")
    Observable<BaseBean<EmptyBean>> loginOut(@Field("token") String token);

    /**
     * 个人中心相关
     */
    //通过token获取用户信息
    @FormUrlEncoded
    @POST("user/getUserInfoByToken")
    Observable<BaseBean<UserResultBean>> getUserInfoByToken(@Field("token") String token);

    //意见反馈
    @FormUrlEncoded
    @POST("user/saveSuggest")
    Observable<BaseBean<EmptyBean>> saveSuggest(@Field("token") String token, @Field("content") String content,
                                                @Field("name") String name, @Field("mobile") String mobile);

    //修改登录密码
    @FormUrlEncoded
    @POST("user/updateUserPassword")
    Observable<BaseBean<EmptyBean>> updateUserPassword(@Field("token") String token, @Field("password") String password, @Field("newPassword") String newPassword);

    //地址管理列表
    @FormUrlEncoded
    @POST("user/getUserDeliveryAddress")
    Observable<BaseListBean<AddressListResultBean>> getUserDeliveryAddress(@Field("token") String token);

    //新增地址
    @FormUrlEncoded
    @POST("user/addUserDeliveryAddress")
    Observable<BaseBean<ChangeAddressResultBean>> addUserDeliveryAddress(@FieldMap Map<String, String> map);

    //编辑地址
    @FormUrlEncoded
    @POST("user/editUserDeliveryAddress")
    Observable<BaseBean<ChangeAddressResultBean>> editUserDeliveryAddress(@FieldMap Map<String, String> map);

    //删除地址
    @FormUrlEncoded
    @POST("user/deleteUserDeliveryAddress")
    Observable<BaseBean<EmptyBean>> deleteUserDeliveryAddress(@Field("token") String token, @Field("id") String id);

    //默认地址
    @FormUrlEncoded
    @POST("user/setUserDeliveryAddress")
    Observable<BaseBean<EmptyBean>> setUserDeliveryAddress(@Field("token") String token, @Field("id") String id);

    //修改用户信息
    @FormUrlEncoded
    @POST("user/updateUser")
    Observable<BaseBean<EmptyBean>> updateUser(@FieldMap Map<String, String> map);

    //获取我的优惠券列表
    @FormUrlEncoded
    @POST("user/getUserCouponList")
    Observable<BaseListBean<ListCouponBean>> getUserCouponList(@Field("token") String token, @Field("type") String type);

    //获取我的爱车列表
    @FormUrlEncoded
    @POST("user/getUserCar")
    Observable<BaseListBean<CarResultBean>> getUserCar(@Field("token") String token);

    //获取爱车详情
    @FormUrlEncoded
    @POST("user/getUserCarById")
    Observable<BaseBean<CarDetailResultBean>> getUserCarById(@Field("token") String token, @Field("carId") String carId);

    //删除车信息
    @FormUrlEncoded
    @POST("user/deleteUserCar")
    Observable<BaseBean<EmptyBean>> deleteUserCar(@Field("token") String token, @Field("carId") String carId);

    //爱车设为默认
    @FormUrlEncoded
    @POST("user/setUserCarDefault")
    Observable<BaseBean<EmptyBean>> setUserCarDefault(@Field("token") String token, @Field("carId") String carId);

    //编辑车信息
    @FormUrlEncoded
    @POST("user/editUserCar")
    Observable<BaseBean<EmptyBean>> editUserCar(@FieldMap Map<String, String> map);

    //添加车信息
    @FormUrlEncoded
    @POST("user/addUserCar")
    Observable<BaseBean<EmptyBean>> addUserCar(@FieldMap Map<String, String> map);

    //检测是否设置交易密码
    @FormUrlEncoded
    @POST("user/checkExitTradPsw")
    Observable<BaseBean<EmptyBean>> checkExitTradPsw(@Field("token") String token);

    //获取0.1元/KM的开放城市列表和热门列表
    @POST("index/getOpenCity")
    Observable<BaseBean<CitysResultBean>> getOpenCity();

    //设置交易密码（第一步）:输入身份证
    @FormUrlEncoded
    @POST("user/setTradPswFirst")
    Observable<BaseBean<EmptyBean>> setTradPswFirst(@Field("token") String token, @Field("idNo") String idNo, @Field("fromType") String fromType);

    //设置交易密码（第二步）:输入密码
    @FormUrlEncoded
    @POST("user/setTradPswSecond")
    Observable<BaseBean<EmptyBean>> setTradPswSecond(@Field("token") String token, @Field("idNo") String idNo, @Field("tradPsw") String tradPsw);

    //忘记交易密码：通过用户身份证修改
    @FormUrlEncoded
    @POST("user/checkTradPswByIdNo")
    Observable<BaseBean<EmptyBean>> checkTradPswByIdNo(@Field("token") String token, @Field("idNo") String idNo);

    //修改交易密码：判断密码是否正确
    @FormUrlEncoded
    @POST("user/checkTradPsw")
    Observable<BaseBean<EmptyBean>> checkTradPsw(@Field("token") String token, @Field("tradPsw") String tradPsw);

    //修改交易密码：保存新交易密码
    @FormUrlEncoded
    @POST("user/saveTradPsw")
    Observable<BaseBean<EmptyBean>> saveTradPsw(@Field("token") String token, @Field("newTradPsw") String tradPsw);


    //上传图片
    @Multipart
    @POST("system/uploadImg")
    Observable<BaseBean<PicUploadResultBean>> uploadImg(@Part("type") RequestBody type, @Part MultipartBody.Part file);

    //忘记密码用手机号获取信息
    @FormUrlEncoded
    @POST("login/getUserByPhone")
    Observable<BaseBean<UserResultBean>> getUserByPhone(@Field("phone") String phone);

    //忘记密码
    @FormUrlEncoded
    @POST("login/resetForgetPsw")
    Observable<BaseBean<EmptyBean>> resetForgetPsw(@Field("id") String id, @Field("newPsw") String newPsw);

    /**
     * 0.1元保养
     */
    //0.1元/km添加车信息
    @FormUrlEncoded
    @POST("index/addServiceUserCar")
    Observable<BaseBean<AddServiceUserCarResultBean>> addServiceUserCar(@Field("token") String token, @Field("configId") String configId, @Field("carName") String carName
            , @Field("registerCity") String registerCity, @Field("lastMainDate") String lastMainDate, @Field("cardNo") String cardNo, @Field("mileage") String mileage);

    //获取配件详情
    @FormUrlEncoded
    @POST("index/getPartDetail")
    Observable<BaseBean<ProductDetailBean>> getPartDetail(@Field("token") String token, @Field("partId") String partId);

    //确认保养订单：获取优惠券，维修商，用户接车/送车地址(0.1)
    @FormUrlEncoded
    @POST("index/confirmMaintainOrder")
    Observable<BaseBean<ConfirmOrderResultBean>> confirmMaintainOrder(@Field("token") String token, @Field("carId") String carId
            , @Field("planTargetId") String planTargetId, @Field("mile") String mile);

    //确认保养订单：获取优惠券，维修商，用户接车/送车地址(自选)
    @FormUrlEncoded
    @POST("index/confirmSelfChoseOrder")
    Observable<BaseBean<ConfirmOrderResultBean>> confirmSelfChoseOrder(@FieldMap Map<String,String> map);

    //推单确认
    @FormUrlEncoded
    @POST("index/checkPushOrder")
    Observable<BaseBean<ConfirmOrderResultBean>> checkPushOrder(@FieldMap Map<String,String> map);

    //修改地址后改变维修商信息
    @FormUrlEncoded
    @POST("index/changeRepairShop")
    Observable<BaseListBean<ConfirmOrderResultBean.ListShopBean>> changeRepairShop(@Field("token") String token, @Field("carId") String carId
            , @Field("planTargetId") String planTargetId, @Field("addressId") String addressId);

    //提交0.1保养订单
    @FormUrlEncoded
    @POST("index/saveMaintainOrder")
    Observable<BaseBean<SaveOrderResultBean>> saveMaintainOrder(@FieldMap Map<String, String> map);

    //提交自选保养订单
    @FormUrlEncoded
    @POST("index/saveSelfChoseOrder")
    Observable<BaseBean<SaveOrderResultBean>> saveSelfChoseOrder(@FieldMap Map<String, String> map);
    //提交钣喷订单
    @FormUrlEncoded
    @POST("index/saveSprayOrder")
    Observable<BaseBean<SaveOrderResultBean>> saveSprayOrder(@FieldMap Map<String, String> map);
    //提交保险订单
    @FormUrlEncoded
    @POST("index/saveInsuranceOrder")
    Observable<BaseBean<SaveOrderResultBean>> saveInsuranceOrder(@FieldMap Map<String, String> map);

    //支付0.1保养订单
    @FormUrlEncoded
    @POST("index/payMaintainOrder")
    Observable<BaseBean<PayResultBean>> payMaintainOrder(@Field("token") String token, @Field("orderId") String orderId
            , @Field("payType") String payType, @Field("tradPsw") String tradPsw);

    //支付自选保养订单
    @FormUrlEncoded
    @POST("index/paySelfChoseOrder")
    Observable<BaseBean<PayResultBean>> paySelfChoseOrder(@Field("token") String token, @Field("orderId") String orderId
            , @Field("payType") String payType, @Field("tradPsw") String tradPsw);


    //获取用户订单列表
    @FormUrlEncoded
    @POST("order/getUserOrderList")
    Observable<BaseBean<OrderListResultBean>> getUserOrderList(@Field("token") String token, @Field("type") String type
            , @Field("pageNo") String pageNo, @Field("pageSize") String pageSize);

    //删除订单
    @FormUrlEncoded
    @POST("order/deleteOrderById")
    Observable<BaseBean<EmptyBean>> deleteOrderById(@Field("token") String token, @Field("orderId") String orderId);

    //收车
    @FormUrlEncoded
    @POST("order/confirmOrderById")
    Observable<BaseBean<EmptyBean>> confirmOrderById(@Field("token") String token, @Field("orderId") String orderId);

    //申请退款（司机未出发）
    @FormUrlEncoded
    @POST("order/drawbackOrderById")
    Observable<BaseBean<EmptyBean>> drawbackOrderById(@Field("token") String token, @Field("orderId") String orderId, @Field("reason") String reason);

    //待支付：【去支付订单】
    @FormUrlEncoded
    @POST("order/againPayOrder")
    Observable<BaseBean<PayResultBean>> againPayOrder(@Field("token") String token, @Field("orderId") String orderId);

    //获取订单详情
    @FormUrlEncoded
    @POST("order/getUserOrderDetail")
    Observable<BaseBean<OrderDetailBean>> getUserOrderDetail(@Field("token") String token, @Field("orderId") String orderId);

    //获取我的钱包
    @FormUrlEncoded
    @POST("user/getUserAccount")
    Observable<BaseBean<WalletBean>> getUserAccount(@Field("token") String token);

    //获取用户充值消费记录
    @FormUrlEncoded
    @POST("user/getUserRechargeList")
    Observable<BaseBean<MyAccountBean>> getUserRechargeList(@Field("token") String token, @Field("type") String type
            , @Field("pageNo") String pageNo, @Field("pageSize") String pageSize);

    //获取订单司机信息
    @FormUrlEncoded
    @POST("order/getOrderDriverDetail")
    Observable<BaseBean<OrderDriverBean>> getOrderDriverDetail(@Field("token") String token, @Field("driverId") String driverId);

    /**
     * 司机
     */
    //修改司机信息
    @FormUrlEncoded
    @POST("index/updateDriver")
    Observable<BaseBean<EmptyBean>> updateDriver(@Field("token") String token, @Field("driverId") String driverId, @Field("photo") String photo);

    //开启/关闭回家模式
    @FormUrlEncoded
    @POST("user/changeHomeMode")
    Observable<BaseBean<EmptyBean>> changeHomeMode(@Field("token") String token, @Field("homeMode") String homeMode);

    //保存回家模式地址
    @FormUrlEncoded
    @POST("user/saveDriverHomeAddress")
    Observable<BaseBean<EmptyBean>> saveDriverHomeAddress(@Field("token") String token, @Field("homeAddress") String homeAddress,
                                                          @Field("homeLongitude") String homeLongitude, @Field("homeLatitude") String homeLatitude);

    //获取我的钱包
    @FormUrlEncoded
    @POST("user/getDriverAccount")
    Observable<BaseBean<DriverAccountResultBean>> getDriverAccount(@Field("token") String token);

    //获取钱包明细-收入列表
    @FormUrlEncoded
    @POST("user/getDriverIncomeList")
    Observable<BaseBean<InComeResultBean>> getDriverIncomeList(@Field("token") String token, @Field("pageNo") String pageNo, @Field("pageSize") String pageSize);

    //获取钱包明细-提现列表
    @FormUrlEncoded
    @POST("user/getWithdrawalsList")
    Observable<BaseBean<WithDrawResultBean>> getWithdrawalsList(@Field("token") String token, @Field("pageNo") String pageNo, @Field("pageSize") String pageSize);

    //司机交押金、充值、开启服务
    @FormUrlEncoded
    @POST("user/userRecharge")
    Observable<BaseBean<PayConformAlpayResultBean>> DriverAlipayYajin(@Field("token") String token, @Field("payType") String payType, @Field("money") String money
            , @Field("openService") String openService);

    //司机交押金、充值、开启服务
    @FormUrlEncoded
    @POST("user/userRecharge")
    Observable<BaseBean<PayConformWechatResultBean>> DriverWechatYajin(@Field("token") String token, @Field("payType") String payType, @Field("money") String money
            , @Field("openService") String openService);

    //修改司机银行账号-交易密码校验
    @FormUrlEncoded
    @POST("user/checkDriverBankByTradPsw")
    Observable<BaseBean<EmptyBean>> checkDriverBankByTradPsw(@Field("token") String token, @Field("tradPsw") String tradPsw);

    //获取司机银行卡信息
    @FormUrlEncoded
    @POST("user/getDriverBankCard")
    Observable<BaseBean<BankCardBean>> getDriverBankCard(@Field("token") String token);

    //获取开户行信息
    @POST("user/getBankList")
    Observable<BaseListBean<BankResultBean>> getBankList();

    //修改司机银行账号-保存银行卡信息
    @FormUrlEncoded
    @POST("user/saveDriverBankInfo")
    Observable<BaseBean<EmptyBean>> saveDriverBankInfo(@Field("token") String token, @Field("cardId") String cardId, @Field("userName") String userName
            , @Field("bankId") String bankId, @Field("accountNo") String accountNo);


    //获取开启服务的价格
    @POST("index/getPriceRatio")
    Observable<BaseBean<PriceResultBean>> getPriceRatio();

    //开启/关闭接单
    @FormUrlEncoded
    @POST("user/changeDriverOrderStatus")
    Observable<BaseBean<EmptyBean>> changeDriverOrderStatus(@Field("token") String token);

    //数据统计
    @FormUrlEncoded
    @POST("user/getDriverInfo")
    Observable<BaseBean<DriverDataInfoResultBean>> getDriverInfo(@Field("token") String token);

    //每日明细
    @FormUrlEncoded
    @POST("user/getOrderInfoByTime")
    Observable<BaseListObserver<DriverDataInfoResultBean>> getOrderInfoByTime(@Field("token") String token, @Field("searchDate") String searchDate);

    //司机首页数据
    @FormUrlEncoded
    @POST("user/getDriverIndexInfo")
    Observable<BaseBean<DriverHomeResultBean>> getDriverIndexInfo(@Field("token") String token);

    //获取提现信息
    @FormUrlEncoded
    @POST("user/getDriverWithdraw")
    Observable<BaseBean<WithDrawDataBean>> getDriverWithdraw(@Field("token") String token);

    //提现
    @FormUrlEncoded
    @POST("user/saveDriverWithdraw")
    Observable<BaseBean<EmptyBean>> saveDriverWithdraw(@Field("token") String token, @Field("accountId") String accountId, @Field("bankCardId") String bankCardId
            , @Field("withdrawals") String withdrawals, @Field("serviceCharge") String serviceCharge);

    //获取提现信息//1：用户协议 2：司机协议  3：商家协议 4：优惠券使用说明 5：保险准备材料 6：积分规则(String/20/Y)
    @FormUrlEncoded
    @POST("login/getProtocolByType")
    Observable<BaseBean<XieYiBean>> getProtocolByType(@Field("type") String type);

    //获取司机订单列表
    @FormUrlEncoded
    @POST("user/getOrderList")
    Observable<BaseBean<AllOrderResultBean>> getOrderList(@Field("token") String token, @Field("status") String status, @Field("orderCode") String orderCode,
                                                          @Field("beginDate") String beginDate, @Field("endDate") String endDate, @Field("pageNo") String pageNo,
                                                          @Field("pageSize") String pageSize);

    //【评价】订单
    @FormUrlEncoded
    @POST("order/evaluateOrderById")
    Observable<BaseBean<EmptyBean>> evaluateOrderById(@Field("token") String token, @Field("orderId") String orderId, @Field("sendDriverScore") String sendDriverScore
            , @Field("backDriverScore") String backDriverScore, @Field("repairShopScore") String repairShopScore, @Field("technicianScore") String technicianScore
            , @Field("washCarScore") String washCarScore);

    //获取司机接送中订单详情
    @FormUrlEncoded
    @POST("user/getOrderInTheRelay")
    Observable<BaseBean<InTheRelayResultBean>> getOrderInTheRelay(@Field("token") String token);

    //司机退款
    @FormUrlEncoded
    @POST("user/returnDriverMoney")
    Observable<BaseBean<EmptyBean>> returnDriverMoney(@Field("token") String token);

    //获取维修商列表
    @FormUrlEncoded
    @POST("index/getSelfChoseShopList")
    Observable<BaseBean<List<RepairAreaBean>>> getSelfChoseShopList(@FieldMap Map<String, String> map);

    //获取保养方案
    @FormUrlEncoded
    @POST("index/getMaintenancePlan")
    Observable<BaseBean<BaoYangFangAnResultBean>> getMaintenancePlan(@FieldMap Map<String, String> map);

    //开具发票
    @FormUrlEncoded
    @POST("index/saveOrderInvoice")
    Observable<BaseBean<InvoiceBean>> saveOrderInvoice(@Field("token") String token, @FieldMap Map<String, String> map);

    //修改交易密码
    @FormUrlEncoded
    @POST("user/saveUserPhone")
    Observable<BaseBean<EmptyBean>> saveUserPhone(@Field("token") String token, @Field("phone") String phone);

    //验证交易密码
    @FormUrlEncoded
    @POST("user/editUserPhoneByTradPsw")
    Observable<BaseBean<EmptyBean>> editUserPhoneByTradPsw(@Field("token") String token, @Field("tradPsw") String tradPsw);

    //验证原手机号
    @FormUrlEncoded
    @POST("user/editUserPhone")
    Observable<BaseBean<EmptyBean>> editUserPhone(@Field("token") String token, @Field("phone") String phone);

    /*
    * 客户端-维修商
    * */
    //申请加盟
    @FormUrlEncoded
    @POST("maintainer/saveRepairShop")
    Observable<BaseBean<EmptyBean>> saveRepairShop(@FieldMap Map<String, String> map);

    //获取附近维修商信息
    @FormUrlEncoded
    @POST("maintainer/getRepairShopList")
    Observable<BaseBean<List<MerchantsBean>>> getRepairShopList(@FieldMap Map<String, String> map);

    //获取维修商详情
    @FormUrlEncoded
    @POST("maintainer/getRepairShopById")
    Observable<BaseBean<RepairShopInfoBean>> getRepairShopById(@Field("id") String id);

    //保存司机经纬度
    @FormUrlEncoded
    @POST("user/saveDriverLocation")
    Observable<BaseBean<EmptyBean>> saveDriverLocation(@FieldMap Map<String, String> map);

    //获取待、去接车详情
    @FormUrlEncoded
    @POST("user/getOrderDetail")
    Observable<BaseBean<DriverOrderDetailBean>> getOrderDetail(@Field("token") String token, @Field("orderId") String orderId);

    //确认取车
    @FormUrlEncoded
    @POST("user/saveCarLive")
    Observable<BaseBean<KeysBean>> saveCarLive(@Field("token") String token, @Field("orderId") String orderId, @FieldMap Map<String, String> map);


    //去接车（出发）
    @FormUrlEncoded
    @POST("user/toPickUpCar")
    Observable<BaseBean<KeysBean>> toPickUpCar(@FieldMap Map<String, String> map);

    //保存或修改增值税发票信息
    @FormUrlEncoded
    @POST("user/saveOrUpdateCompInvoice")
    Observable<BaseBean<EmptyBean>> saveOrUpdateCompInvoice(@Field("token") String token, @FieldMap Map<String, String> map);

    //查询增值税发票基本信息
    @FormUrlEncoded
    @POST("user/getUserCompInvoice")
    Observable<BaseBean<InvoiceBaseBean>> getUserCompInvoice(@Field("token") String token);

    //查询增值税发票基本信息
    @FormUrlEncoded
    @POST("user/getUserScoreList")
    Observable<BaseBean<List<IntegralBean>>> getUserScoreList(@FieldMap Map<String,String> map);

    //服务管家列表
    @FormUrlEncoded
    @POST("user/getJSDetail")
    Observable<BaseBean<List<OnlinerBean>>> getJSDetail(@FieldMap Map<String,String> map);

    /*//获取自选保养方案
    @FormUrlEncoded
    @POST("index/getSelfChoseServiceList")
    Observable<BaseBean<FreeMainTainBean>> getSelfChoseServiceList(@FieldMap Map<String,String> map);*/

    //获取自选保养方案_数据齐全
    @FormUrlEncoded
    @POST("index/getSelfChoseServicePartList")
    Observable<BaseBean<FreeMaintainAllBean>> getSelfChoseServiceList(@FieldMap Map<String,String> map);

    //获取自选保养方案下的产品列表
    @FormUrlEncoded
    @POST("index/getSelfChosePartList")
    Observable<BaseBean<List<ProductSelectBean>>> getSelfChosePartList(@FieldMap Map<String,String> map);

    //新增消息
    @FormUrlEncoded
    @POST("index/addUserMessage")
    Observable<BaseBean<EmptyBean>> addUserMessage(@Field("token") String token,@Field("messageId") String messageId);

    //获取消息列表
    @FormUrlEncoded
    @POST("index/getMyMessageList")
    Observable<BaseBean<MessageListBean>> getMyMessageList(@Field("token") String token, @FieldMap Map<String,String> map);

    //获取消息详情
    @FormUrlEncoded
    @POST("index/getMyMessageDetail")
    Observable<BaseBean<MessageDetailBean>> getMyMessageDetail(@Field("token") String token, @Field("id") String id);

    //保养、维修、保险、钣喷的推单一级展示页面
    @FormUrlEncoded
    @POST("index/getMyPushOrderDetail")
    Observable<BaseBean<OrderAddBean>> getMyPushOrderDetail(@FieldMap Map<String,String> map);

    //增项推单详情
    @FormUrlEncoded
    @POST("index/getMyOrderIncrease")
    Observable<BaseBean<OderAddDetailBean>> getMyOrderIncrease(@Field("token") String token, @Field("id") String id);

    //增项确定
    @FormUrlEncoded
    @POST("index/checkIncreaseOrder")
    Observable<BaseBean<CheckResultBean>> checkIncreaseOrder(@Field("token") String token, @Field("id") String id);

    //取消增项
    @FormUrlEncoded
    @POST("index/cancleIncreaseOrder")
    Observable<BaseBean<EmptyBean>> cancleIncreaseOrder(@Field("token") String token, @Field("orderId") String orderId);

    //提交增项订单
    @FormUrlEncoded
    @POST("index/confirmIncreaseOrder")
    Observable<BaseBean<EmptyBean>> confirmIncreaseOrder(@FieldMap Map<String,String> map);

    //增项订单详情
    @FormUrlEncoded
    @POST("order/getIncreaseOrderDetail")
    Observable<BaseBean<OderAddDetailBean>> getIncreaseOrderDetail(@Field("token") String token, @Field("orderId") String orderId);

    //改变地址获取代驾费
    @FormUrlEncoded
    @POST("index/changeSelfChoseDJF")
    Observable<BaseBean<OnlyStringBean>> changeSelfChoseDJF(@Field("token") String token, @Field("jcAddressId") String jcAddressId, @Field("scAddressId") String scAddressId, @Field("repairShopId") String repairShopId,@Field("type") String type);

    //获取保险公司列表
    @POST("user/getInsuranceList")
    Observable<BaseBean<List<IncompanyBean>>> getInsuranceList();

    //获取救援电话、关于我们;type：类型（1客户端，2平台，3商户）
    @FormUrlEncoded
    @POST("index/getForHelp")
    Observable<BaseBean<AboutBean>>getForHelp(@Field("type") String type);

    //获取专家
    @FormUrlEncoded
    @POST("technician/getTechnicianOnLine")
    Observable<BaseBean<OnlinerBean>>getTechnicianOnLine(@Field("token") String token,@Field("carId") String carId,@Field("type") String type);

    //上传经纬度
    @GET()
    Observable<BaseBean<OnlinerBean>> uploadLocation(@Url String url);

    //投诉建议
    @FormUrlEncoded
    @POST("technician/addOrderComplaint")
    Observable<BaseBean<EmptyBean>>addOrderComplaint(@FieldMap Map<String,String> map);

    //钣喷确认订单
    @FormUrlEncoded
    @POST("index/confirmSprayOrder")
    Observable<BaseBean<ConfirmOrderResultBean>>confirmSprayOrder(@FieldMap Map<String,String> map);

    //司机查看行车轨迹
    @FormUrlEncoded
    @POST("user/getDrivingTrack")
    Observable<BaseBean<DriverGuijiBean>>getDrivingTrack(@Field(Const.token) String token, @Field("driverHisId") String driverHisId);

    //用户查看行车轨迹
    @FormUrlEncoded
    @POST("order/getUserDrivingTrack")
    Observable<BaseBean<UserGuijiBean>>getUserDrivingTrack(@FieldMap Map<String,String> map);

    //用户查看行车轨迹
    @FormUrlEncoded
    @POST("technician/getTechnicianDetail")
    Observable<BaseBean<OnlinerBean>>getTechnicianDetail(@Field("userId") String userId);

    //获取平台优惠券
    @FormUrlEncoded
    @POST("index/getPlatFormCoupon")
    Observable<BaseBean<List<ListCouponBean>>>getPlatFormCoupon(@Field(Const.token) String token);

    //领取平台优惠券
    @FormUrlEncoded
    @POST("index/acceptCoupon")
    Observable<BaseBean<EmptyBean>>acceptCoupon(@Field(Const.token) String token,@Field("couponIds") String couponId);

    //获取原厂配件参数
    @FormUrlEncoded
    @POST("index/getCarConfigure")
    Observable<BaseBean<CarconfigureBean>>getCarConfigure(@Field(Const.token) String token, @Field("carId") String carId);

    //获取最新版本号
    @FormUrlEncoded
    @POST("version/getVersion")
    Observable<BaseBean<VersionBean>>getVersion(@Field("type") String type, @Field("app") String appType);

    //获取最新版本号
    @FormUrlEncoded
    @POST("user/getDriverInfoByImg")
    Observable<BaseBean<CarsbBean>>getDriverInfoByImg(@Field("token") String token, @Field("imgUrl") String imgUrl);


}
