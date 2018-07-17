package com.dream.moka.Utils.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Window;
import android.widget.EditText;

import com.dream.moka.CumstomView.CarNoKeyboad;
import com.dream.moka.R;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public abstract class CarNoKeyboardDialog  {
    public CarNoKeyboardDialog(final Activity activity, EditText mEdit){
        Dialog dialog = showCarNoDialog(activity, mEdit);
        DialogUtils.setBespreadWindow(dialog,activity);
    }
    public  Dialog showCarNoDialog(final Activity activity, final EditText mEdit) {
        final Dialog headDialog = new Dialog(activity, R.style.ActionSheetDialogStyle1);
        CarNoKeyboad headView = new CarNoKeyboad(activity, mEdit);
        headDialog.setContentView(headView);
        //获取当前Activity所在的窗体
        Window dialogWindow1 = headDialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow1.setGravity(Gravity.BOTTOM);
        headDialog.setCancelable(true);
        headView.onKeyboardListener(new CarNoKeyboad.KeyListener() {
            @Override
            public void onClick(int type, String inputStr) {
                switch (type) {
                    case 1:
                        headDialog.dismiss();
                        break;
                    case 2:
                        headDialog.dismiss();
                        onResult(2,inputStr);
                        break;
                    case 3:
                        onResult(3,inputStr);
                        break;
                    default:
                        onResult(4,inputStr);
                        break;
                }
            }
        });
        mEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mEdit.setSelection(mEdit.length(),mEdit.length());
            }
        });
        headDialog.show();
        return headDialog;
    }

    public abstract void onResult(int type,String inputStr);
}
