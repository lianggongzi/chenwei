package com.example.administrator.chenwei;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.device.ScanManager;
import android.device.scanner.configuration.PropertyID;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.chenwei.base.Basei600sActivity;
import com.example.administrator.chenwei.presenter.MainPresenterImpl;
import com.example.administrator.chenwei.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;

/**
 * Created by Administrator on 2018\12\13 0013.
 */

public class I6200sActivity extends Basei600sActivity<MainPresenterImpl> implements MainView {


    private final static String SCAN_ACTION = ScanManager.ACTION_DECODE;//default action
    @BindView(R.id.ma_edt)
    EditText maEdt;
    @BindView(R.id.main_btn)
    Button mainBtn;
    private Vibrator mVibrator;
    private ScanManager mScanManager;
    private SoundPool soundpool = null;
    private int soundid;
    private String barcodeStr;
    private boolean isScaning = false;

    AlertDialog.Builder builder = null;
    AlertDialog dialog;
    View view;

    private BroadcastReceiver mScanReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            isScaning = false;
            soundpool.play(soundid, 1, 1, 0, 0, 1);
//            i6200s.setText("");
            mVibrator.vibrate(100);
            byte[] barcode = intent.getByteArrayExtra(ScanManager.DECODE_DATA_TAG);
            int barcodelen = intent.getIntExtra(ScanManager.BARCODE_LENGTH_TAG, 0);
            byte temp = intent.getByteExtra(ScanManager.BARCODE_TYPE_TAG, (byte) 0);
            Log.i("debug", "----codetype--" + temp);
            barcodeStr = new String(barcode, 0, barcodelen);
//            i6200s.setText(barcodeStr);
            initData(maEdt.getText().toString(), barcodeStr);

        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mVibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        builder = new AlertDialog.Builder(this);
        dialog = builder.create();
        dialog.setCancelable(false);
        view = View.inflate(this, R.layout.dailog, null);
    }

    @Override
    protected void createPresenter() {
        mPresenter = new MainPresenterImpl();
    }

    private void initScan() {
        // TODO Auto-generated method stub
        mScanManager = new ScanManager();
        mScanManager.openScanner();

        mScanManager.switchOutputMode(0);
        soundpool = new SoundPool(1, AudioManager.STREAM_NOTIFICATION, 100); // MODE_RINGTONE
        soundid = soundpool.load("/etc/Scan_new.ogg", 1);
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        initScan();
        IntentFilter filter = new IntentFilter();
        int[] idbuf = new int[]{PropertyID.WEDGE_INTENT_ACTION_NAME, PropertyID.WEDGE_INTENT_DATA_STRING_TAG};
        String[] value_buf = mScanManager.getParameterString(idbuf);
        if (value_buf != null && value_buf[0] != null && !value_buf[0].equals("")) {
            filter.addAction(value_buf[0]);
        } else {
            filter.addAction(SCAN_ACTION);
        }

        registerReceiver(mScanReceiver, filter);
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }

    @Override
    public void getData(DataBean bean) {
        showDailog(bean);
    }

    @Override
    public void getError(String msg) {
        Toast.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void hideDialog() {

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
