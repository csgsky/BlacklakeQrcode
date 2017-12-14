package com.gy.allen.blacklakezbardemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by allen on 17/12/13.
 */

public class ManuBottomSheetDialog extends BottomSheetDialog implements View.OnClickListener {
    private Context mContext;
    private TextView mTvCancel;
    private Button mBtnComfirm;
    private EditText mEtManu;

    public ManuBottomSheetDialog(@NonNull Context context) {
        super(context);
        this.mContext = context;
    }

//    public ManuBottomSheetDialog(@NonNull Context context, int theme) {
//        super(context, theme);
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_manu_input);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT); // 全屏
        View view = getWindow().findViewById(android.support.design.R.id.design_bottom_sheet); // 滑动属性
        BottomSheetBehavior.from(view).setPeekHeight(getScreenSize(mContext)[1]); // 动态设置高度
        initView();
    }

    private void initView() {
        mTvCancel = findViewById(R.id.tv_manu_cancel);
        mBtnComfirm = findViewById(R.id.btn_confirm);
        mEtManu = findViewById(R.id.et_manu);
        mTvCancel.setOnClickListener(this);
        mBtnComfirm.setOnClickListener(this);

    }

    public static int[] getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return new int[]{outMetrics.widthPixels, outMetrics.heightPixels};
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_confirm:
                    String code = mEtManu.getText().toString().trim();
                    if (!TextUtils.isEmpty(code))
                        mManuClickListener.onConfirm(code);
                    break;
                case R.id.tv_manu_cancel:
                    mManuClickListener.onCancel();
                    break;
            }
    }

    // 回调
    public interface ManuClickListener {
        void onCancel(); // 关闭 bottom 页面
        void onConfirm(String code); // 点击确认
    }

    private ManuClickListener mManuClickListener;

    public void setManuClickListener(ManuClickListener manuClickListener) {
        this.mManuClickListener = manuClickListener;
    }

}
