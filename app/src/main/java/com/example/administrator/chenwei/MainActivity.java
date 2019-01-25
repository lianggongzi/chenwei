package com.example.administrator.chenwei;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.administrator.chenwei.base.BaseActivity;
import com.example.administrator.chenwei.presenter.MainPresenterImpl;
import com.example.administrator.chenwei.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;

public class MainActivity extends BaseActivity<MainPresenterImpl> implements MainView {

    @BindView(R.id.ma_edt)
    EditText maEdt;
    @BindView(R.id.main_btn)
    Button mainBtn;
    AlertDialog.Builder builder = null;
    AlertDialog dialog;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        builder = new AlertDialog.Builder(this);
        dialog = builder.create();
        dialog.setCancelable(false);
        view = View.inflate(this, R.layout.dailog, null);

    }

    @Override
    public void updateCount() {

    }

    @Override
    public void updateList(String data) {
        Log.d("feng", data);
        if (!data.equals("Decode is interruptted or timeout ...")){
            initData(maEdt.getText().toString(), data);
        }
    }

    @Override
    protected void createPresenter() {
        mPresenter = new MainPresenterImpl();
    }


    @Override
    public void getData(DataBean bean) {
//        dialog.dismiss();
        showDailog(bean);
    }

    @Override
    public void getError(String msg) {
        Log.d("feng", msg);
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.main_btn)
    public void onViewClicked() {
        initData(maEdt.getText().toString(), "");
    }

    //网络访问
    private void initData(String key, String ma) {
        if (key.equals("") && ma.equals("")) {
            Toast.makeText(this, "信息错误", Toast.LENGTH_SHORT).show();
        } else {
            FormBody formBody = new FormBody
                    .Builder()
                    .add("key", key)
                    .add("ma", ma)
                    .build();
            mPresenter.getData(formBody);
        }
    }

    public void showDailog(DataBean dataBean) {
        dialog.setView(view);// 设置边距为0,保证在2.x的版本上运行没问题
        TextView ztTextView = view.findViewById(R.id.main_zt_tv);
        TextView titleTextView = view.findViewById(R.id.main_title_tv);
        TextView nameTextView = view.findViewById(R.id.main_name_tv);
        TextView phoneTextView = view.findViewById(R.id.main_phone_tv);
        TextView maTextView = view.findViewById(R.id.main_ma_tv);
        TextView numsTextView = view.findViewById(R.id.main_nums_tv);
        Button btn = view.findViewById(R.id.dailog_btn);
        ztTextView.setText(dataBean.getZt());
        titleTextView.setText(dataBean.getTitle());
        nameTextView.setText(dataBean.getName());
        phoneTextView.setText(dataBean.getPhone());
        maTextView.setText(dataBean.getMa());
        numsTextView.setText(dataBean.getNums());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
