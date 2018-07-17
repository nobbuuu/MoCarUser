package com.dream.moka.Utils.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.EditText;

import com.dream.moka.CumstomView.CarNoFirstKeyboad;
import com.dream.moka.CumstomView.CarNoKeyboad;
import com.dream.moka.R;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public abstract class CarNoFirstDialog {
    public CarNoFirstDialog(final Activity activity){
        Dialog dialog = showCarNoDialog(activity);
        DialogUtils.setBespreadWindow(dialog,activity);
    }
    public  Dialog showCarNoDialog(final Activity activity) {
        final Dialog headDialog = new Dialog(activity, R.style.ActionSheetDialogStyle1);
        CarNoFirstKeyboad headView = new CarNoFirstKeyboad(activity);
        headDialog.setContentView(headView);
        //获取当前Activity所在的窗体
        Window dialogWindow1 = headDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow1.setGravity(Gravity.BOTTOM);
        headDialog.setCancelable(true);
        headView.onKeyboardListener(new CarNoFirstKeyboad.KeyListener() {
            @Override
            public void onClick(int type, String inputStr) {
                headDialog.dismiss();
                if (type==2){
                    onResult(inputStr);
                }
            }
        });
        headDialog.show();
        return headDialog;
    }

    public abstract void onResult(String inputStr);
}
