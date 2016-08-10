package com.yitu8.mylinelayout.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 流式文本展示
 * Created by Administrator on 2016/8/10.
 */
public class AutoLinearLayout extends ViewGroup {

    public AutoLinearLayout(Context context) {
        super(context);
    }

    public AutoLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        final int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        final int chlidCount = this.getChildCount();
        int lineWidth = 0, lineHeight = 0;
        int width = 0, height = 0;

        for( int i = 0; i < chlidCount; i++ ){
            View child = this.getChildAt(i);

            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            final int childWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            final int childHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;

            if( lineWidth + childWidth > sizeWidth ){
                width = Math.max(width, lineWidth );
                lineWidth = childWidth;

                height += childHeight;
                lineHeight = childHeight;
            } else {
                lineWidth += childWidth;
                lineWidth = Math.max(lineWidth, childWidth);
            }
        }

        height += lineHeight;
        System.out.println( "width : " + width + "height : " + height );
        this.setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? sizeWidth : width, heightMode == MeasureSpec.EXACTLY ? sizeHeight : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int chlidCount = this.getChildCount();
        int lineWidth, lineHeight;
        int width = getWidth();
        int left = this.getPaddingLeft(), top = this.getPaddingTop();

        for( int i = 0; i < chlidCount; i++ ){
            View child = this.getChildAt(i);

            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            lineWidth = child.getMeasuredWidth();
            lineHeight = child.getMeasuredHeight();

            left += params.leftMargin + params.rightMargin;
            if( left + lineWidth > width ){
                left = params.leftMargin;
                top += lineHeight + params.topMargin + params.bottomMargin;
            }
            final int right = left + lineWidth;
            final int bottom = top + lineHeight;
            child.layout( left, top, right, bottom);
            left = right;
        }
    }

    /**
     * 与当前ViewGroup对应的LayoutParams
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        // TODO Auto-generated method stub

        return new MarginLayoutParams(getContext(), attrs);
    }
}
