package com.yekong.mystatuslayout_master;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;


/**
 * Created by xigua on 2017/8/21.
 */

public class MyStatusFramlayout extends FrameLayout {

    private View error_layout,loading_layout,empty_layout;
    private int loadStatus;
    private PageStatusListener listener;
    private boolean isLoadSuccess = false;

    public MyStatusFramlayout(@NonNull Context context) {
        super(context);
    }

    public MyStatusFramlayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);

    }

    public MyStatusFramlayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context,attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MyStatusFramlayout);
        int eroorid = a.getResourceId(R.styleable.MyStatusFramlayout_error_layout, -1);
        int loadingid = a.getResourceId(R.styleable.MyStatusFramlayout_loading_layout, -1);
        int emptyid = a.getResourceId(R.styleable.MyStatusFramlayout_empty_layout, -1);
        int status = a.getInt(R.styleable.MyStatusFramlayout_load_status, 0);
        if (eroorid != -1) {
            error_layout = View.inflate(context, eroorid, null);
        }else{
            error_layout = View.inflate(context, R.layout.view_error, null);
        }
        if (loadingid != -1) {
            loading_layout = View.inflate(context, loadingid, null);
        }else{
            loading_layout = View.inflate(context, R.layout.view_loading, null);
        }
        if (emptyid != -1) {
            empty_layout = View.inflate(context, emptyid, null);
        }else{
            empty_layout = View.inflate(context, R.layout.view_empty, null);
        }
        loadStatus = status;

        Log.e("layout","eroorid" + eroorid +
                "loadingid" + loadingid +
                "contentid" + emptyid +
                "status" + status
        );
    }

    private void initPage(){
        if (listener == null) {
            throw new NullPointerException("无效的监听器");
        }
        if (isLoadSuccess)return;
        switch (loadStatus) {
            case 0:
                //载
                removeView(loading_layout);
                removeView(error_layout);
                removeView(empty_layout);
                addView(loading_layout);
                listener.onLoading();
                break;
            case 1:
                //空
                removeView(loading_layout);
                removeView(error_layout);
                removeView(empty_layout);
                addView(empty_layout);
                break;
            case 2:
                //错
                removeView(loading_layout);
                removeView(error_layout);
                removeView(empty_layout);
                addView(error_layout);
                listener.onLoadFail();
                break;
            case 3:
                //OK
                removeView(loading_layout);
                removeView(empty_layout);
                removeView(error_layout);
                listener.onLoadSuccess();
                isLoadSuccess = true;
                break;
        }

        error_layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!listener.onErrorClick()) {
                }
            }
        });
        empty_layout.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!listener.onErrorClick()) {
                }
            }
        });
    }

    public void childLoad() {
        this.loadStatus = 0;
        initPage();
    }

    public void childLoadEmpty(){
        this.loadStatus = 1;
        initPage();
    }

    public void childLoadFail(){
        this.loadStatus = 2;
        initPage();
    }

    public void childLoadSuccess(){
        this.loadStatus = 3;
        initPage();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }



    public interface PageStatusListener{
        void onLoading();
        void onLoadSuccess();
        void onLoadFail();
        boolean onErrorClick();
    }

    public void setListener(PageStatusListener listener) {
        this.listener = listener;
    }
}
