package com.dream.moka.Utils.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.dream.moka.CumstomView.PayPsdInputView;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.login_register.ResetPwdActivity;
import com.dream.moka.UI.Activity.set.SetIDcardActivity;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.PopWindowUtil;

/**
 * Created by Administrator on 2018/2/9 0009.
 */
public abstract class PayPwdDialog {

    public PayPwdDialog(Activity activity){
        showPayPwdDialog(activity);
    }
    public  void showPayPwdDialog(final Activity activity) {
        final Dialog dialog_tip = new Dialog(activity, R.style.ActionSheetDialogStyle);
        View login = LayoutInflater.from(activity).inflate(R.layout.dialog_paypassword_input, null);
        PayPsdInputView paypwd_view = (PayPsdInputView) login.findViewById(R.id.paypwd_view);
        TextView forgotpwd_tv = (TextView) login.findViewById(R.id.forgotpwd_tv);
        ImageView close_iv = (ImageView) login.findViewById(R.id.close_iv);
        dialog_tip.requestWindowFeature(Window.FEATURE_NO_TITLE);//（这句设置没有title）
        dialog_tip.setContentView(login);
        dialog_tip.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog_tip.setCancelable(true);
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        int displayHeight = dm.heightPixels;
        WindowManager.LayoutParams p = dialog_tip.getWindow().getAttributes(); //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.75); //宽度设置为屏幕的0.75
//        p.height = (int) (displayHeight * 0.45); //高度设置为屏幕的0.45
        dialog_tip.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog不消失
        dialog_tip.getWindow().setAttributes(p);  //设置生效
        dialog_tip.show();
        PopWindowUtil.backgroundAlpaha(activity, 0.5f);
        dialog_tip.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                PopWindowUtil.backgroundAlpaha(activity, 1f);
            }
        });
        close_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_tip.dismiss();
            }
        });
        paypwd_view.setInputCompleteListener(new PayPsdInputView.inputCompleteListener() {
            @Override
            public void onInputComplete(String password) {
                dialog_tip.dismiss();
//                mValicationPwdPresenter.getValidationPassword(CommonAction.getToken(),password);
                onInputCompleteResult(password);

            }
        });
        forgotpwd_tv.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                dialog_tip.dismiss();
                SetIDcardActivity.openAct(activity, "0", "");
            }
        });
    }

    public abstract void onInputCompleteResult(String pwd);

}
