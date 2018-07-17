package com.dream.moka.Component;

import com.dream.moka.IM.im.activity.ChatActivity;
import com.dream.moka.UI.Activity.AboutUsActivity;
import com.dream.moka.UI.Activity.AddTicketsZZActivity;
import com.dream.moka.UI.Activity.AppraiseActivity;
import com.dream.moka.UI.Activity.BeDriverWebActivity;
import com.dream.moka.UI.Activity.ChangeUserInfoActivity;
import com.dream.moka.UI.Activity.ChatMyActivity;
import com.dream.moka.UI.Activity.CityChooseActivity;
import com.dream.moka.UI.Activity.DriverInfoActivity;
import com.dream.moka.UI.Activity.DriverOrderDetail2Activity;
import com.dream.moka.UI.Activity.DriverOrderDetailActivity;
import com.dream.moka.UI.Activity.FeedBackServiceActivity;
import com.dream.moka.UI.Activity.GuijiMapActivity;
import com.dream.moka.UI.Activity.InCompanyRvActivity;
import com.dream.moka.UI.Activity.Message.IncreaceSureOrderActivity;
import com.dream.moka.UI.Activity.TuoCarActivity;
import com.dream.moka.UI.Activity.WebActivity;
import com.dream.moka.UI.Activity.maintain.FreeMaitainActivity;
import com.dream.moka.UI.Activity.GetCaredInputActivity;
import com.dream.moka.UI.Activity.GotoGetCarActivity;
import com.dream.moka.UI.Activity.InvoiceActivity;
import com.dream.moka.UI.Activity.MainActivity;
import com.dream.moka.UI.Activity.MerchantDetailActivity;
import com.dream.moka.UI.Activity.Message.OrderAddMaintainActivity;
import com.dream.moka.UI.Activity.Message.MessageRvActivity;
import com.dream.moka.UI.Activity.Message.MessageSpecialActivity;
import com.dream.moka.UI.Activity.OrderDetailActivity;
import com.dream.moka.UI.Activity.OrderPayActivity;
import com.dream.moka.UI.Activity.PurseJifenActivity;
import com.dream.moka.UI.Activity.RepairShopRvActivity;
import com.dream.moka.UI.Activity.ServiceKeeperRvActivity;
import com.dream.moka.UI.Activity.SureOrderActivity;
import com.dream.moka.UI.Activity.drivercenter.DriverCenterActivity;
import com.dream.moka.UI.Activity.maintain.OnekeyMaintainActivity;
import com.dream.moka.UI.Activity.PayOpenServiceActivity;
import com.dream.moka.UI.Activity.UserInfoActivity;
import com.dream.moka.UI.Activity.drivercenter.WithDrawalActivity;
import com.dream.moka.UI.Activity.mycar.CarInfoInputActivity;
import com.dream.moka.UI.Activity.other.DynamicActivity;
import com.dream.moka.UI.Activity.other.OpenServiceActivity;
import com.dream.moka.UI.Activity.other.WebViewActivity;
import com.dream.moka.UI.Activity.set.ChangephoneActivity;
import com.dream.moka.UI.Activity.drivercenter.DriverEquityActivity;
import com.dream.moka.UI.Activity.login_register.ResetPwdActivity;
import com.dream.moka.UI.Activity.drivercenter.AddBankCardActivity;
import com.dream.moka.UI.Activity.drivercenter.BanksRvActivity;
import com.dream.moka.UI.Activity.set.PayPwdActivity;
import com.dream.moka.UI.Activity.drivercenter.DataStaticsActivity;
import com.dream.moka.UI.Activity.drivercenter.RechargeActivity;
import com.dream.moka.UI.Activity.drivercenter.ChangeBankCardActivity;
import com.dream.moka.UI.Activity.drivercenter.DriverPurseActivity;
import com.dream.moka.UI.Activity.address.AddAndEditAddressActivity;
import com.dream.moka.UI.Activity.BeDriverActivity;
import com.dream.moka.UI.Activity.drivercenter.DriverUserInfoActivity;
import com.dream.moka.UI.Activity.login_register.RegisterActivity;
import com.dream.moka.UI.Activity.maintain.OneChooseCarActivity;
import com.dream.moka.UI.Activity.mycar.AddMyCarActivity;
import com.dream.moka.UI.Activity.address.AddressActivity;
import com.dream.moka.UI.Activity.mycar.CarConfigActivity;
import com.dream.moka.UI.Activity.mycar.CarInfoActivity;
import com.dream.moka.UI.Activity.mycar.CarsChooseActivity;
import com.dream.moka.UI.Activity.ChangePwdActivity;
import com.dream.moka.UI.Activity.address.ChoosePointForMapActivity;
import com.dream.moka.UI.Activity.CommonQuestionActivity;
import com.dream.moka.UI.Activity.FeedBackActivity;
import com.dream.moka.UI.Activity.HuodongRvActivity;
import com.dream.moka.UI.Activity.IndustryDynamicsRvActivity;
import com.dream.moka.UI.Activity.login_register.LoginActivity;
import com.dream.moka.UI.Activity.mycar.MyCarsActivity;
import com.dream.moka.UI.Activity.set.SetActivity;
import com.dream.moka.UI.Activity.login_register.SetPwdActivity;
import com.dream.moka.UI.Activity.set.SetIDcardActivity;
import com.dream.moka.UI.Activity.set.SetNewPhoneActivity;
import com.dream.moka.UI.Activity.set.UserXieYiActivity;
import com.dream.moka.UI.ChildFragment.BeMerchant2Fragment;
import com.dream.moka.UI.ChildFragment.CardsFragment;
import com.dream.moka.UI.ChildFragment.DriverCenterOrderAllRvFragment;
import com.dream.moka.UI.ChildFragment.DriverCenterOrderDoingFragment;
import com.dream.moka.UI.ChildFragment.FreeMaintainRvFragment;
import com.dream.moka.UI.ChildFragment.JifenDetailFragment;
import com.dream.moka.UI.ChildFragment.OrdersFragment;
import com.dream.moka.UI.ChildFragment.PurseDetailFragment;
import com.dream.moka.UI.Fragment.DriverCenterMainFragment;
import com.dream.moka.UI.Fragment.DriverCenterMineFragment;
import com.dream.moka.UI.Fragment.DriverCenterOrderFragment;
import com.dream.moka.UI.Fragment.HomeFragment;
import com.dream.moka.UI.Fragment.MerchantFragment;
import com.dream.moka.UI.Fragment.MineFragment;
import com.dream.moka.UI.Fragment.OnlineServiceFragment;

import dagger.Component;

@Component(dependencies = AppComponent.class)
public interface BaseComponent {
    CarsChooseActivity inject(CarsChooseActivity activity);

    CarConfigActivity inject(CarConfigActivity activity);

    HomeFragment inject(HomeFragment activity);

    IndustryDynamicsRvActivity inject(IndustryDynamicsRvActivity activity);

    HuodongRvActivity inject(HuodongRvActivity activity);

    CommonQuestionActivity inject(CommonQuestionActivity activity);

    SetPwdActivity inject(SetPwdActivity activity);

    LoginActivity inject(LoginActivity activity);

    FeedBackActivity inject(FeedBackActivity activity);

    ChangePwdActivity inject(ChangePwdActivity activity);

    SetActivity inject(SetActivity activity);

    AddAndEditAddressActivity inject(AddAndEditAddressActivity activity);

    AddressActivity inject(AddressActivity activity);

    ChoosePointForMapActivity inject(ChoosePointForMapActivity activity);

    AddMyCarActivity inject(AddMyCarActivity activity);

    MyCarsActivity inject(MyCarsActivity activity);

    CarInfoActivity inject(CarInfoActivity activity);

    OneChooseCarActivity inject(OneChooseCarActivity activity);

    MineFragment inject(MineFragment activity);

    DriverUserInfoActivity inject(DriverUserInfoActivity activity);

    BeDriverActivity inject(BeDriverActivity activity);

    RegisterActivity inject(RegisterActivity activity);

    DriverCenterMineFragment inject(DriverCenterMineFragment activity);

    DriverPurseActivity inject(DriverPurseActivity activity);

    PurseDetailFragment inject(PurseDetailFragment activity);

    RechargeActivity inject(RechargeActivity activity);

    ChangeBankCardActivity inject(ChangeBankCardActivity activity);

    PayPwdActivity inject(PayPwdActivity activity);

    SetIDcardActivity inject(SetIDcardActivity activity);

    BanksRvActivity inject(BanksRvActivity activity);

    AddBankCardActivity inject(AddBankCardActivity activity);

    DriverEquityActivity inject(DriverEquityActivity activity);

    DataStaticsActivity inject(DataStaticsActivity activity);

    DriverCenterMainFragment inject(DriverCenterMainFragment activity);

    ChangephoneActivity inject(ChangephoneActivity activity);

    ResetPwdActivity inject(ResetPwdActivity activity);

    WithDrawalActivity inject(WithDrawalActivity activity);

    UserXieYiActivity inject(UserXieYiActivity activity);

    PayOpenServiceActivity inject(PayOpenServiceActivity activity);

    OpenServiceActivity inject(OpenServiceActivity activity);

    DriverCenterOrderFragment inject(DriverCenterOrderFragment activity);

    CardsFragment inject(CardsFragment activity);

    CityChooseActivity inject(CityChooseActivity activity);

    BeMerchant2Fragment inject(BeMerchant2Fragment activity);

    MerchantFragment inject(MerchantFragment activity);

    OnekeyMaintainActivity inject(OnekeyMaintainActivity activity);

    UserInfoActivity inject(UserInfoActivity activity);

    SureOrderActivity inject(SureOrderActivity activity);

    MerchantDetailActivity inject(MerchantDetailActivity activity);

    RepairShopRvActivity inject(RepairShopRvActivity activity);

    InvoiceActivity inject(InvoiceActivity activity);

    OrderPayActivity inject(OrderPayActivity activity);

    ChangeUserInfoActivity inject(ChangeUserInfoActivity activity);

    OrdersFragment inject(OrdersFragment activity);

    SetNewPhoneActivity inject(SetNewPhoneActivity activity);

    OrderDetailActivity inject(OrderDetailActivity activity);

    DriverCenterActivity inject(DriverCenterActivity activity);

    DriverOrderDetailActivity inject(DriverOrderDetailActivity activity);

    JifenDetailFragment inject(JifenDetailFragment activity);

    PurseJifenActivity inject(PurseJifenActivity activity);

    AddTicketsZZActivity inject(AddTicketsZZActivity activity);

    DriverCenterOrderAllRvFragment inject(DriverCenterOrderAllRvFragment activity);

    DriverCenterOrderDoingFragment inject(DriverCenterOrderDoingFragment activity);

    GotoGetCarActivity inject(GotoGetCarActivity activity);

    GetCaredInputActivity inject(GetCaredInputActivity activity);

    MainActivity inject(MainActivity activity);

    DriverOrderDetail2Activity inject(DriverOrderDetail2Activity activity);

    AppraiseActivity inject(AppraiseActivity activity);

    DriverInfoActivity inject(DriverInfoActivity activity);

    BeDriverWebActivity inject(BeDriverWebActivity activity);

    WebViewActivity inject(WebViewActivity activity);

    ServiceKeeperRvActivity inject(ServiceKeeperRvActivity activity);

    FreeMaitainActivity inject(FreeMaitainActivity activity);

    FreeMaintainRvFragment inject(FreeMaintainRvFragment activity);

    MessageRvActivity inject(MessageRvActivity activity);

    OrderAddMaintainActivity inject(OrderAddMaintainActivity activity);

    MessageSpecialActivity inject(MessageSpecialActivity activity);

    IncreaceSureOrderActivity inject(IncreaceSureOrderActivity activity);

    InCompanyRvActivity inject(InCompanyRvActivity activity);

    OnlineServiceFragment inject(OnlineServiceFragment fragment);

    AboutUsActivity inject(AboutUsActivity fragment);

    ChatMyActivity inject(ChatMyActivity fragment);

    ChatActivity inject(ChatActivity fragment);

    FeedBackServiceActivity inject(FeedBackServiceActivity fragment);

    WebActivity inject(WebActivity fragment);

    TuoCarActivity inject(TuoCarActivity object);

    GuijiMapActivity inject(GuijiMapActivity object);

    DynamicActivity inject(DynamicActivity object);

    CarInfoInputActivity inject(CarInfoInputActivity object);


}