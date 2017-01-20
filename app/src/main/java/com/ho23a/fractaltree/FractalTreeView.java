package com.ho23a.fractaltree;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Anh Ho
 */

public class FractalTreeView extends View {
    public static final float GOLDEN_RATIO = 1.618f;
    public static final float MIN_BRANCH_LENGTH = 5;

    private Paint mBackgroundPaint;
    private Paint mPaint;

    private float strokeWidth = 3;

    public FractalTreeView(Context context) {
        super(context);
        init();
    }
    public FractalTreeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(ContextCompat.getColor(getContext(), R.color.background_color));
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public void drawBranch(float x, float y, double angle, float length, Canvas canvas) {
        if (x < 0 || x > getWidth() || y < 0 || y > getHeight() || length < MIN_BRANCH_LENGTH) {
            return;
        }

        float newX = length * (float)Math.cos(angle) + x;
        float newY = length * (float)Math.sin(angle) + y;
        double newAngle = 0d;
        float newLength = length/GOLDEN_RATIO;

        // mPaint.setColor(color?);
        // setStrokeCap - default = BUTT
        mPaint.setStrokeWidth(strokeWidth);
        canvas.drawLine(x, y, newX, newY, mPaint);

        drawBranch(newX, newY, newAngle, newLength, canvas);
    }
}
