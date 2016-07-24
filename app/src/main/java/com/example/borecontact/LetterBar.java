package com.example.borecontact;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/7/24.
 */
public class LetterBar extends LinearLayout {

    public interface OnLetterSelectedListener {
        void OnLetterSelected(String letter);
    }

    private OnLetterSelectedListener onLetterSelectedListener;

    public void setOnLetterSelectedListener(OnLetterSelectedListener onLetterSelectedListener) {
        this.onLetterSelectedListener = onLetterSelectedListener;
    }

    public LetterBar(Context context) {
        this(context, null);
    }

    public LetterBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setBackgroundColor(Color.GRAY);
        setOrientation(VERTICAL);
        for (int i = 0; i < 26; i++) {
            TextView tv = new TextView(context);
            LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 0, 1);
            tv.setLayoutParams(params);
            tv.setText((char) ('A' + i) + "");
            tv.setTextColor(Color.WHITE);

            addView(tv);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                int defSize = getHeight() / getChildCount();
                int index = (int) (y / defSize);
                TextView tv = (TextView) getChildAt(index);

                if (tv != null && onLetterSelectedListener != null) {
                    onLetterSelectedListener.OnLetterSelected(tv.getText().toString());
                }

                break;
            case MotionEvent.ACTION_UP:

                if (onLetterSelectedListener != null) {
                    onLetterSelectedListener.OnLetterSelected("");
                }

                break;
        }
        return true;
    }
}
