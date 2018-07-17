package com.dream.moka.UI.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.dream.moka.R;
import com.dream.moka.UI.Activity.MainActivity;
import com.dream.moka.UI.Activity.OrderManageActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderPaySuccessActivity extends AppCompatActivity {


    @Bind(R.id.backHome)
    TextView mBackHome;
    @Bind(R.id.seeOrder)
    TextView mSeeOrder;

    public static void openAct(Context context) {
        Intent intent = new Intent(context, OrderPaySuccessActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pay_success);
        getSupportActionBar().hide();
        ButterKnife.bind(this);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(OrderPaySuccessActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @OnClick({R.id.backHome, R.id.seeOrder})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.backHome:
                Intent intent = new Intent(OrderPaySuccessActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
            case R.id.seeOrder:
                OrderManageActivity.openAct(OrderPaySuccessActivity.this, 0, "pay");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
