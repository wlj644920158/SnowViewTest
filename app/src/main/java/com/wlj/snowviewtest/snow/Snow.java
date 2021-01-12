package com.wlj.snowviewtest.snow;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

public class Snow {
    private int x;
    private int y;
    private int color;
    private int radius;
    private int rotation;
    private int speed;

    private int parentWidth;
    private int parentHeight;

    private Point startPoint;
    private Point middlePoint;
    private Point endPoint;

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    private int strokeWidth;

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public Point getMiddlePoint() {
        return middlePoint;
    }

    public void setMiddlePoint(Point middlePoint) {
        this.middlePoint = middlePoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public int getParentWidth() {
        return parentWidth;
    }

    public void setParentWidth(int parentWidth) {
        this.parentWidth = parentWidth;
    }

    public int getParentHeight() {
        return parentHeight;
    }

    public void setParentHeight(int parentHeight) {
        this.parentHeight = parentHeight;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public static Snow generateSnow(int width, int height) {
        Snow snow = new Snow();
        snow.setParentWidth(width);
        snow.setParentHeight(height);
        Random random = new Random();
        int x = random.nextInt(width);
        int y = -random.nextInt(height);

//        int r = random.nextInt(255);
//        int g = random.nextInt(255);
//        int b = random.nextInt(255);
        int color = Color.WHITE;
        int radius = 10 + random.nextInt(10);
        int rotation = random.nextInt(60);
        int speed = (int) ((2 + Math.abs(radius - 20)) * 0.5);
        int strokeWidth = (int) (radius * 0.2);

        Point start = new Point(random.nextInt(width), -random.nextInt(height));
        Point middle = new Point(random.nextInt(width), random.nextInt(height));
        Point end = new Point(random.nextInt(width), height + random.nextInt(height));

        snow.setX(x);
        snow.setY(y);
        snow.setColor(color);
        snow.setRadius(radius);
        snow.setRotation(rotation);
        snow.setSpeed(speed);
        snow.setStrokeWidth(strokeWidth);

        snow.setStartPoint(start);
        snow.setMiddlePoint(middle);
        snow.setEndPoint(end);


        return snow;
    }


    /**
     * 逻辑函数
     */
    public void step() {
        y += speed;
        if (y > parentHeight) {
            y = -50;
        }
        setRotation(getRotation() + 1);
    }

    public void onDraw(Canvas canvas, Paint paint) {
        canvas.save();
        paint.setColor(getColor());
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(getStrokeWidth());

        canvas.rotate(getRotation(), getX(), getY());//

        for (int i = 0; i < 6; i++) {
            int lineStartX = getX();
            int lineEndX = getX() + getRadius();
            canvas.drawLine(lineStartX, getY(), lineEndX, getY(), paint);

            int line1StartX = (int) (getX() + getRadius() * 0.6);
            int line1StartY = getY();
            double degree60 = Math.toRadians(60);
            int line1EndX = (int) (line1StartX + radius * 0.4 * Math.cos(degree60));
            int line1EndY = (int) (line1StartY - radius * 0.4 * Math.sin(degree60));
            canvas.drawLine(line1StartX, line1StartY, line1EndX, line1EndY, paint);


            int line2StartX = (int) (getX() + getRadius() * 0.6);
            int line2StartY = getY();
            int line2EndX = (int) (line1StartX + radius * 0.4 * Math.cos(degree60));
            int line2EndY = (int) (line1StartY + radius * 0.4 * Math.sin(degree60));
            canvas.drawLine(line2StartX, line2StartY, line2EndX, line2EndY, paint);

            canvas.rotate(60, getX(), getY());
        }

        canvas.restore();
    }

    public int getX() {
        float t = getY() * 1.0f / (getEndPoint().y - getStartPoint().y);
        return BezierUtil.calculatePoint(t, getStartPoint(), getMiddlePoint(), getEndPoint()).x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
}
