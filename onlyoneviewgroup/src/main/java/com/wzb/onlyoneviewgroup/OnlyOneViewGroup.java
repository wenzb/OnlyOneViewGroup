package com.wzb.onlyoneviewgroup;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class OnlyOneViewGroup extends FrameLayout {
    /**
     * 当前显示的View
     */
    private int currentViewIndex = 0;

    /**
     * 通过layout id 引入的布局文件
     */
    private List<View> layoutResViews = new ArrayList<>();

    /**
     * 排好循序的全部view
     */
    private List<View> allOrderViews = new ArrayList<>();

    /**
     * 是否使用返回键 默认不使用
     */
    private boolean isAddToBackTask = false;

    private LayoutInflater layoutInflater;

    public OnlyOneViewGroup(@NonNull Context context) {
        super(context);
    }

    public OnlyOneViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        layoutInflater = LayoutInflater.from(this.getContext());
    }

    public OnlyOneViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int[] layouts) {
        this(context, attrs);
        layoutResViews = layoutsToViews(layouts);
    }

    public OnlyOneViewGroup(@NonNull Context context,int[] layouts) {
        this(context);
        layoutResViews = layoutsToViews(layouts);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int count = this.getChildCount();
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                allOrderViews.add(this.getChildAt(i));
            }
        }
        showIndexView(0);
    }

    /**
     * 添加布局文件到OnlyOneViewGroup
     */
    public void addLayoutRes(int[] layouts) {
        layoutResViews.addAll(layoutsToViews(layouts));
        addLayoutViewsToViewGroup();
    }

    /**
     * 添加布局文件到OnlyOneViewGroup
     */
    public void addLayoutRes(@LayoutRes int layoutId) {
        layoutResViews.add(layoutInflater.inflate(layoutId, null));
        addLayoutViewsToViewGroup();
    }

    /**
     * 清除所有View
     */
    public void clearViews() {
        allOrderViews.clear();
        layoutResViews.clear();
        this.removeAllViews();
    }

    private void addLayoutViewsToViewGroup() {
        allOrderViews.addAll(layoutResViews);
        for (View view : layoutResViews) {
            this.addView(view);
        }
        showIndexView(0);
    }

    /**
     * 显示指定index
     */
    public void showIndexView(int index) {
        if (allOrderViews.size() == 0) {
            return;
        }
        //不设计抛出异常
        if (index < 0) {
            index = 0;
        }
        if (index > allOrderViews.size() - 1) {
            index = allOrderViews.size() - 1;
        }
        for (int i = 0; i < allOrderViews.size(); i++) {
            if (i == index) {
                allOrderViews.get(i).setVisibility(View.VISIBLE);
            } else {
                allOrderViews.get(i).setVisibility(View.INVISIBLE);
            }
        }
        currentViewIndex = index;
    }

    /**
     * 显示下一个View
     */
    public void showNext() {
        if (allOrderViews.size() < 2) {
            return;
        }
        showIndexView(currentViewIndex + 1);
    }

    /**
     * 在最后一个时显示第0个
     */
    public void showNextCirculation() {
        if (allOrderViews.size() < 2) {
            return;
        }
        if (currentViewIndex == allOrderViews.size() - 1) {
            showIndexView(0);
        } else {
            showIndexView(currentViewIndex + 1);
        }
    }


    /**
     * 是否压入返回栈
     */
    public boolean isAddToBackTask() {
        return isAddToBackTask;
    }

    /**
     * 设置压入返回栈
     */
    public void setAddToBackTask(boolean addToBackTask) {
        this.setFocusable(addToBackTask);
        this.setFocusableInTouchMode(addToBackTask);
        isAddToBackTask = addToBackTask;
    }

    private List<View> layoutsToViews(int[] layouts) {
        List<View> views = new ArrayList<>();
        if (layouts.length != 0) {
            for (int id : layouts) {
                views.add(layoutInflater.inflate(id, null));
            }
        }
        return views;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (!isAddToBackTask) {
            return super.onKeyDown(keyCode, event);
        }
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (currentViewIndex == 0) {
                return super.onKeyDown(keyCode, event);
            } else {
                showIndexView(currentViewIndex - 1);
                return true;
            }
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
