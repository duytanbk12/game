package itbk.duytan.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

public class ball extends View {

    private int ballRadius=50;
    private int ballX=50;
    private int ballY=50;
    private int minX=0;
    private int maxX;
    private int minY=0;
    private int maxY;
    private float ballSpeedX=100;
    private float ballSpeedY=100;
    //private RectF ballBounds;
    //private Paint paint;
    Bitmap screen = BitmapFactory.decodeResource(getResources(), R.drawable.background);

    Bitmap ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
    Bitmap ballR = Bitmap.createScaledBitmap(ball, 50, 50, false);

    public ball(Context context){
        super(context);
    }
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint paint = new Paint();
        /*paint.setColor(Color.BLUE);
        ballBounds.set(ballX-ballRadius, ballY-ballRadius,ballX+ballRadius,ballY+ballRadius);*/
        /*Bitmap screen = BitmapFactory.decodeResource(getResources(), R.drawable.background);
        Bitmap screenR = Bitmap.createScaledBitmap(screen, canvas.getWidth(), canvas.getHeight(), false);
        canvas.drawBitmap(screenR, 0 , 0 , null);*/

        Bitmap screenR = Bitmap.createScaledBitmap(screen, canvas.getWidth(), canvas.getHeight(), false);
        canvas.drawBitmap(screenR, 0, 0, null);
        canvas.drawBitmap(ballR, ballX, ballY, null);

        update();
        try {
            Thread.sleep(1);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        invalidate();
    }

    //BONG CHAY THANG
    private void update(){
        //nhan vi tri x,y moi
        ballX+= ballSpeedX;
        ballY+= ballSpeedY;
        //detect collision and react
        if(ballX+ballRadius >maxX){
            ballSpeedX =- ballSpeedX;
            ballX = maxX-ballRadius;
        }
        else if(ballX-ballRadius<minX){
            ballSpeedX =- ballSpeedX;
            ballX = minX+ballRadius;
        }
        else if(ballY+ballRadius>maxY){
            ballSpeedY =- ballSpeedY;
            ballY = maxY-ballRadius;
        }
        else if(ballY-ballRadius<minY){
            ballSpeedY=-ballSpeedY;
            ballY = minY+ballRadius;
        }
    }
    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH){
        maxX=w-1;
        maxY=h-1;
    }
}
