package engenoid.tessocrtest.Core.ExtraViews;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import engenoid.tessocrtest.R;

/**
 * Created by Fadi on 5/11/2014.
 */
public class FocusBoxView extends View {

    private static final int MIN_FOCUS_BOX_WIDTH = 120;
    private static final int MIN_FOCUS_BOX_HEIGHT = 20;

    private final Paint paint;
    private final int maskColor;
    private final int frameColor;
    private final int cornerColor;
    //TextView texto1=new TextView;



    static final String TAG = "DBG_" + FocusBoxView.class.getName();

    public FocusBoxView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Resources resources = getResources();

        maskColor = resources.getColor(R.color.focus_box_mask);
        frameColor = resources.getColor(R.color.focus_box_frame);
        cornerColor = resources.getColor(R.color.focus_box_corner);
        //texto1 = (TextView)findViewById(R.id.texto1);
        this.setOnTouchListener(getTouchListener());
    }


    private Rect box;

    private Rect box2;



    private static Point ScrRes;

    private  Rect getBoxRect() {

        if (box == null) {

            ScrRes = FocusBoxUtils.getScreenResolution(getContext());

            int centerX = ScrRes.x/2;
            int centerY = ScrRes.y/2;

            int width = ScrRes.x * 6 / 9;
            int height = ScrRes.y / 7;

            width = width == 0
                    ? MIN_FOCUS_BOX_WIDTH
                    : width < MIN_FOCUS_BOX_WIDTH ? MIN_FOCUS_BOX_WIDTH : width;

            height = height == 0
                    ? MIN_FOCUS_BOX_HEIGHT
                    : height < MIN_FOCUS_BOX_HEIGHT ? MIN_FOCUS_BOX_HEIGHT : height;

            //int left = (ScrRes.x - width) / 2;
            //int top = (ScrRes.y - height) / 2;

            box = new Rect(centerX-(width/2)+30, centerY-(height/2)-300, centerX+(width/2)+30, centerY+(height/2)-300);
        }

        return box;
    }


    private  Rect getBoxRect2() {

        if (box2 == null) {

            ScrRes = FocusBoxUtils.getScreenResolution(getContext());

            int centerX = ScrRes.x/2;
            int centerY = ScrRes.y/2;

            int width = ScrRes.x * 6 / 13;
            int height = ScrRes.y / 15;

            width = width == 0
                    ? MIN_FOCUS_BOX_WIDTH
                    : width < MIN_FOCUS_BOX_WIDTH ? MIN_FOCUS_BOX_WIDTH : width;

            height = height == 0
                    ? MIN_FOCUS_BOX_HEIGHT
                    : height < MIN_FOCUS_BOX_HEIGHT ? MIN_FOCUS_BOX_HEIGHT : height;

            //int left = (ScrRes.x - width) / 2;
            //int top = (ScrRes.y - height) / 2;

            box2 = new Rect(centerX-(width/2)-180, centerY-(height/2)+350, centerX+(width/2)-180, centerY+(height/2)+350);
        }

        return box2;
    }

    public Rect getBox() {
        return box;
    }


    public Rect getBox2() {
        return box2;
    }


    private void updateBoxRect(int dW, int dH) {

        int newWidth = (box.width() + dW > ScrRes.x - 4 || box.width() + dW < MIN_FOCUS_BOX_WIDTH)
                ? 0
                : box.width() + dW;

        int newHeight = (box.height() + dH > ScrRes.y - 4 || box.height() + dH < MIN_FOCUS_BOX_HEIGHT)
                ? 0
                : box.height() + dH;

        int leftOffset = (ScrRes.x - newWidth) / 2;

        int topOffset = (ScrRes.y - newHeight) / 2;


        int centerX = box.centerX();
        int centerY = box.centerY();

        if (newWidth < MIN_FOCUS_BOX_WIDTH || newHeight < MIN_FOCUS_BOX_HEIGHT)
            return;

       // public boolean a;
       // a=texto1.

        box = new Rect(centerX-newWidth/2, centerY-newHeight/2, centerX+newWidth/2, centerY+newHeight/2);
    }


    private void updateBoxRect2(int dW, int dH) {

        int newWidth = (box2.width() + dW > ScrRes.x - 4 || box2.width() + dW < MIN_FOCUS_BOX_WIDTH)
                ? 0
                : box2.width() + dW;

        int newHeight = (box2.height() + dH > ScrRes.y - 4 || box2.height() + dH < MIN_FOCUS_BOX_HEIGHT)
                ? 0
                : box2.height() + dH;

        int leftOffset = (ScrRes.x - newWidth) / 2;

        int topOffset = (ScrRes.y - newHeight) / 2;


        int centerX = box2.centerX();
        int centerY = box2.centerY();

        if (newWidth < MIN_FOCUS_BOX_WIDTH || newHeight < MIN_FOCUS_BOX_HEIGHT)
            return;

        box2 = new Rect(centerX-newWidth/2, centerY-newHeight/2, centerX+newWidth/2, centerY+newHeight/2);
    }



    private void updateBoxCenter(int dW, int dH) {

        int newWidth = box.width();

        int newHeight = box.height();

        int centerX = box.centerX()+dW;
        int centerY = box.centerY()+dH;

        box = new Rect(centerX-newWidth/2, centerY-newHeight/2, centerX+newWidth/2, centerY+newHeight/2);


    }


    private void updateBoxCenter2(int dW, int dH) {

        int newWidth = box2.width();

        int newHeight = box2.height();

        int centerX = box2.centerX()+dW;
        int centerY = box2.centerY()+dH;

        box2 = new Rect(centerX-newWidth/2, centerY-newHeight/2, centerX+newWidth/2, centerY+newHeight/2);
    }


    private OnTouchListener touchListener;


    private OnTouchListener getTouchListener() {

        if (touchListener == null)
            touchListener = new OnTouchListener() {

                int lastX = -1;
                int lastY = -1;

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            lastX = -1;
                            lastY = -1;
                            return true;
                        case MotionEvent.ACTION_MOVE:
                            int currentX = (int) event.getX();
                            int currentY = (int) event.getY();
                            try {
                                Rect rect = getBoxRect();
                                Rect rect2 = getBoxRect2();
                                final int BUFFER = 50;
                                final int BIG_BUFFER = 60;
                                if (lastX >= 0) {
                                    if (((currentX >= rect.left - BIG_BUFFER
                                            && currentX <= rect.left + BIG_BUFFER)
                                            || (lastX >= rect.left - BIG_BUFFER
                                            && lastX <= rect.left + BIG_BUFFER))
                                            && ((currentY <= rect.top + BIG_BUFFER
                                            && currentY >= rect.top - BIG_BUFFER)
                                            || (lastY <= rect.top + BIG_BUFFER
                                            && lastY >= rect.top - BIG_BUFFER))) {
                                        updateBoxRect(2 * (lastX - currentX),
                                                2 * (lastY - currentY));
                                    } else if (((currentX >= rect.right - BIG_BUFFER
                                            && currentX <= rect.right + BIG_BUFFER)
                                            || (lastX >= rect.right - BIG_BUFFER
                                            && lastX <= rect.right + BIG_BUFFER))
                                            && ((currentY <= rect.top + BIG_BUFFER
                                            && currentY >= rect.top - BIG_BUFFER)
                                            || (lastY <= rect.top + BIG_BUFFER
                                            && lastY >= rect.top - BIG_BUFFER))) {
                                        // Top right corner: adjust both top and right sides
                                        updateBoxRect(2 * (currentX - lastX),
                                                2 * (lastY - currentY));
                                    } else if (((currentX >= rect.left - BIG_BUFFER
                                            && currentX <= rect.left + BIG_BUFFER)
                                            || (lastX >= rect.left - BIG_BUFFER
                                            && lastX <= rect.left + BIG_BUFFER))
                                            && ((currentY <= rect.bottom + BIG_BUFFER
                                            && currentY >= rect.bottom - BIG_BUFFER)
                                            || (lastY <= rect.bottom + BIG_BUFFER
                                            && lastY >= rect.bottom - BIG_BUFFER))) {
                                        // Bottom left corner: adjust both bottom and left sides
                                        updateBoxRect(2 * (lastX - currentX),
                                                2 * (currentY - lastY));
                                    } else if (((currentX >= rect.right - BIG_BUFFER
                                            && currentX <= rect.right + BIG_BUFFER)
                                            || (lastX >= rect.right - BIG_BUFFER
                                            && lastX <= rect.right + BIG_BUFFER))
                                            && ((currentY <= rect.bottom + BIG_BUFFER
                                            && currentY >= rect.bottom - BIG_BUFFER)
                                            || (lastY <= rect.bottom + BIG_BUFFER
                                            && lastY >= rect.bottom - BIG_BUFFER))) {
                                        // Bottom right corner: adjust both bottom and right sides
                                        updateBoxRect(2 * (currentX - lastX),
                                                2 * (currentY - lastY));
                                    } else if (((currentX >= rect.left - BUFFER
                                            && currentX <= rect.left + BUFFER)
                                            || (lastX >= rect.left - BUFFER
                                            && lastX <= rect.left + BUFFER))
                                            && ((currentY <= rect.bottom
                                            && currentY >= rect.top)
                                            || (lastY <= rect.bottom
                                            && lastY >= rect.top))) {
                                        // Adjusting left side: event falls within BUFFER pixels of
                                        // left side, and between top and bottom side limits
                                        updateBoxRect(2 * (lastX - currentX), 0);
                                    } else if (((currentX >= rect.right - BUFFER
                                            && currentX <= rect.right + BUFFER)
                                            || (lastX >= rect.right - BUFFER
                                            && lastX <= rect.right + BUFFER))
                                            && ((currentY <= rect.bottom
                                            && currentY >= rect.top)
                                            || (lastY <= rect.bottom
                                            && lastY >= rect.top))) {
                                        // Adjusting right side: event falls within BUFFER pixels of
                                        // right side, and between top and bottom side limits
                                        updateBoxRect(2 * (currentX - lastX), 0);
                                    } else if (((currentY <= rect.top + BUFFER
                                            && currentY >= rect.top - BUFFER)
                                            || (lastY <= rect.top + BUFFER
                                            && lastY >= rect.top - BUFFER))
                                            && ((currentX <= rect.right
                                            && currentX >= rect.left)
                                            || (lastX <= rect.right
                                            && lastX >= rect.left))) {
                                        // Adjusting top side: event falls within BUFFER pixels of
                                        // top side, and between left and right side limits
                                        updateBoxRect(0, 2 * (lastY - currentY));
                                    } else if (((currentY <= rect.bottom + BUFFER
                                            && currentY >= rect.bottom - BUFFER)
                                            || (lastY <= rect.bottom + BUFFER
                                            && lastY >= rect.bottom - BUFFER))
                                            && ((currentX <= rect.right
                                            && currentX >= rect.left)
                                            || (lastX <= rect.right
                                            && lastX >= rect.left))) {
                                        updateBoxRect(0, 2 * (currentY - lastY));
                                        Log.d(TAG, "Atualiza mudando");
                                    } else if (currentX > rect.left
                                            && currentX < rect.right
                                            && currentY > rect.top
                                            && currentY < rect.bottom
                                            ){
                                        Log.d(TAG, "Atualiza Centro");
                                        updateBoxCenter( (currentX - lastX),  (currentY - lastY));
                                    }




                                    else if (((currentX >= rect2.left - BIG_BUFFER
                                            && currentX <= rect2.left + BIG_BUFFER)
                                            || (lastX >= rect2.left - BIG_BUFFER
                                            && lastX <= rect2.left + BIG_BUFFER))
                                            && ((currentY <= rect2.top + BIG_BUFFER
                                            && currentY >= rect2.top - BIG_BUFFER)
                                            || (lastY <= rect2.top + BIG_BUFFER
                                            && lastY >= rect2.top - BIG_BUFFER))) {
                                        updateBoxRect2(2 * (lastX - currentX),
                                                2 * (lastY - currentY));
                                    } else if (((currentX >= rect2.right - BIG_BUFFER
                                            && currentX <= rect2.right + BIG_BUFFER)
                                            || (lastX >= rect2.right - BIG_BUFFER
                                            && lastX <= rect2.right + BIG_BUFFER))
                                            && ((currentY <= rect2.top + BIG_BUFFER
                                            && currentY >= rect2.top - BIG_BUFFER)
                                            || (lastY <= rect2.top + BIG_BUFFER
                                            && lastY >= rect2.top - BIG_BUFFER))) {
                                        // Top right corner: adjust both top and right sides
                                        updateBoxRect2(2 * (currentX - lastX),
                                                2 * (lastY - currentY));
                                    } else if (((currentX >= rect2.left - BIG_BUFFER
                                            && currentX <= rect2.left + BIG_BUFFER)
                                            || (lastX >= rect2.left - BIG_BUFFER
                                            && lastX <= rect2.left + BIG_BUFFER))
                                            && ((currentY <= rect2.bottom + BIG_BUFFER
                                            && currentY >= rect2.bottom - BIG_BUFFER)
                                            || (lastY <= rect2.bottom + BIG_BUFFER
                                            && lastY >= rect2.bottom - BIG_BUFFER))) {
                                        // Bottom left corner: adjust both bottom and left sides
                                        updateBoxRect2(2 * (lastX - currentX),
                                                2 * (currentY - lastY));
                                    } else if (((currentX >= rect2.right - BIG_BUFFER
                                            && currentX <= rect2.right + BIG_BUFFER)
                                            || (lastX >= rect2.right - BIG_BUFFER
                                            && lastX <= rect2.right + BIG_BUFFER))
                                            && ((currentY <= rect2.bottom + BIG_BUFFER
                                            && currentY >= rect2.bottom - BIG_BUFFER)
                                            || (lastY <= rect2.bottom + BIG_BUFFER
                                            && lastY >= rect2.bottom - BIG_BUFFER))) {
                                        // Bottom right corner: adjust both bottom and right sides
                                        updateBoxRect2(2 * (currentX - lastX),
                                                2 * (currentY - lastY));
                                    } else if (((currentX >= rect2.left - BUFFER
                                            && currentX <= rect2.left + BUFFER)
                                            || (lastX >= rect2.left - BUFFER
                                            && lastX <= rect2.left + BUFFER))
                                            && ((currentY <= rect2.bottom
                                            && currentY >= rect2.top)
                                            || (lastY <= rect2.bottom
                                            && lastY >= rect2.top))) {
                                        // Adjusting left side: event falls within BUFFER pixels of
                                        // left side, and between top and bottom side limits
                                        updateBoxRect2(2 * (lastX - currentX), 0);
                                    } else if (((currentX >= rect2.right - BUFFER
                                            && currentX <= rect2.right + BUFFER)
                                            || (lastX >= rect2.right - BUFFER
                                            && lastX <= rect2.right + BUFFER))
                                            && ((currentY <= rect2.bottom
                                            && currentY >= rect2.top)
                                            || (lastY <= rect2.bottom
                                            && lastY >= rect2.top))) {
                                        // Adjusting right side: event falls within BUFFER pixels of
                                        // right side, and between top and bottom side limits
                                        updateBoxRect2(2 * (currentX - lastX), 0);
                                    } else if (((currentY <= rect2.top + BUFFER
                                            && currentY >= rect2.top - BUFFER)
                                            || (lastY <= rect2.top + BUFFER
                                            && lastY >= rect2.top - BUFFER))
                                            && ((currentX <= rect2.right
                                            && currentX >= rect2.left)
                                            || (lastX <= rect2.right
                                            && lastX >= rect2.left))) {
                                        // Adjusting top side: event falls within BUFFER pixels of
                                        // top side, and between left and right side limits
                                        updateBoxRect2(0, 2 * (lastY - currentY));
                                    } else if (((currentY <= rect2.bottom + BUFFER
                                            && currentY >= rect2.bottom - BUFFER)
                                            || (lastY <= rect2.bottom + BUFFER
                                            && lastY >= rect2.bottom - BUFFER))
                                            && ((currentX <= rect2.right
                                            && currentX >= rect2.left)
                                            || (lastX <= rect2.right
                                            && lastX >= rect2.left))) {
                                        updateBoxRect2(0, 2 * (currentY - lastY));
                                        Log.d(TAG, "Atualiza mudando");
                                    } else if (currentX > rect2.left
                                            && currentX < rect2.right
                                            && currentY > rect2.top
                                            && currentY < rect2.bottom
                                            ){
                                        Log.d(TAG, "Atualiza Centro2");
                                        updateBoxCenter2( (currentX - lastX),  (currentY - lastY));
                                    }




                                }
                            } catch (NullPointerException e) {
                            }
                            v.invalidate();
                            lastX = currentX;
                            lastY = currentY;
                            return true;
                        case MotionEvent.ACTION_UP:
                            lastX = -1;
                            lastY = -1;
                            return true;
                    }
                    return false;
                }
            };

        return touchListener;
    }





    @Override
    public void onDraw(Canvas canvas) {

        Rect frame = getBoxRect();
        Rect frame2 = getBoxRect2();

        int width = canvas.getWidth();
        int height = canvas.getHeight();


        paint.setColor(maskColor);
        canvas.drawRect(0, 0, width, frame.top, paint);
        canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
        canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, paint);
        canvas.drawRect(0, frame.bottom + 1, width, height, paint);

        paint.setAlpha(0);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(frameColor);
        canvas.drawRect(frame.left, frame.top, frame.right + 1, frame.top + 2, paint);
        canvas.drawRect(frame.left, frame.top + 2, frame.left + 2, frame.bottom - 1, paint);
        canvas.drawRect(frame.right - 1, frame.top, frame.right + 1, frame.bottom - 1, paint);
        canvas.drawRect(frame.left, frame.bottom - 1, frame.right + 1, frame.bottom + 1, paint);

        paint.setColor(cornerColor);
        canvas.drawCircle(frame.left - 32, frame.top - 32, 32, paint);
        canvas.drawCircle(frame.right + 32, frame.top - 32, 32, paint);
        canvas.drawCircle(frame.left - 32, frame.bottom + 32, 32, paint);
        canvas.drawCircle(frame.right + 32, frame.bottom + 32, 32, paint);




        paint.setColor(maskColor);
        canvas.drawRect(0, 0, width, frame2.top, paint);
        canvas.drawRect(0, frame2.top, frame2.left, frame2.bottom + 1, paint);
        canvas.drawRect(frame2.right + 1, frame2.top, width, frame2.bottom + 1, paint);
        canvas.drawRect(0, frame2.bottom + 1, width, height, paint);

        paint.setAlpha(0);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(frameColor);
        canvas.drawRect(frame2.left, frame2.top, frame2.right + 1, frame2.top + 2, paint);
        canvas.drawRect(frame2.left, frame2.top + 2, frame2.left + 2, frame2.bottom - 1, paint);
        canvas.drawRect(frame2.right - 1, frame2.top, frame2.right + 1, frame2.bottom - 1, paint);
        canvas.drawRect(frame2.left, frame2.bottom - 1, frame2.right + 1, frame2.bottom + 1, paint);

        paint.setColor(cornerColor);
        canvas.drawCircle(frame2.left - 32, frame2.top - 32, 32, paint);
        canvas.drawCircle(frame2.right + 32, frame2.top - 32, 32, paint);
        canvas.drawCircle(frame2.left - 32, frame2.bottom + 32, 32, paint);
        canvas.drawCircle(frame2.right + 32, frame2.bottom + 32, 32, paint);

    }
}
