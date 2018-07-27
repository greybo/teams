package com.example.sbotlevskyi.teams.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.sbotlevskyi.teams.R;
import com.example.sbotlevskyi.teams.entity.Player;
import com.example.sbotlevskyi.teams.entity.Teams;
import com.example.sbotlevskyi.teams.utils.TeamUtils;

import java.util.ArrayList;
import java.util.Stack;

@SuppressLint("DrawAllocation")
public class FootballFieldView extends View {

    private Paint paint = new Paint();
    private Teams teams;
    private int centerWidth = getWidth() / 2;
    private int centerHeight = getHeight() / 2;
    private int margeLine = 50;
    private float strokeWidth = 10;
    private int halfCircleRadius;
    private int playerRadius;
    private int playerPercent = 4;
    private float centerCircleRadius;
    private int centerCircleRadiusPercent = 12;
    private Bitmap bmpBall = BitmapFactory.decodeResource(
            getContext().getResources(), R.drawable.ic_ball_12);

    public FootballFieldView(Context context) {
        super(context);
    }

    public FootballFieldView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setTeams(Teams teams) {
        this.teams = teams;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerWidth = getWidth() / 2;
        centerHeight = getHeight() / 2;
        halfCircleRadius = getWidth() / 7;
        playerRadius = Math.min((getWidth() / 100) * playerPercent, (getHeight() / 100) * playerPercent);
        centerCircleRadius = Math.min((getWidth() / 100) * centerCircleRadiusPercent,
                (getHeight() / 100) * centerCircleRadiusPercent);

        footballField(canvas);
        footballFieldLine(canvas);
        playersFill(canvas);
    }


    private void footballField(Canvas canvas) {
        paint = new Paint();
        paint.setColor(getContext().getResources().getColor(R.color.green_football_field));
        int left = 0;
        int right = centerWidth * 2;
        int top = 0;
        int bottom = centerHeight * 2;
        canvas.drawRect(left, top, right, bottom, paint);

    }

    private void footballFieldLine(Canvas canvas) {
        Paint paintLine = new Paint();
        paintLine.setColor(getContext().getResources().getColor(R.color.football_field_line));
        paintLine.setStrokeWidth(strokeWidth);
        paintLine.setStyle(Paint.Style.STROKE);
        int smallSquare = 7;
        int bigSquare = 4;
        canvas.drawRect(margeLine, margeLine, centerWidth * 2 - margeLine, centerHeight * 2 - margeLine, paintLine);

        float lineCenter[] = {margeLine, centerHeight,
                centerWidth * 2 - margeLine, centerHeight};
        canvas.drawLines(lineCenter, paintLine);

        canvas.drawCircle(centerWidth, centerHeight, centerCircleRadius, paintLine);

        int left = centerWidth - (centerWidth / bigSquare);
        int right = centerWidth + (centerWidth / bigSquare);
        int top = margeLine;
        int bottom = centerHeight / smallSquare;
        canvas.drawRect(left, top, right, bottom, paintLine);

        RectF oval = new RectF();
        top = (centerHeight / bigSquare) - halfCircleRadius / 2;
        bottom = top + halfCircleRadius;
        oval.set(left, top, right, bottom);
        canvas.drawArc(oval, 0, 180, false, paintLine);

        oval = new RectF();
        bottom = (centerHeight * 2 - centerHeight / bigSquare) + halfCircleRadius / 2;
        top = bottom - halfCircleRadius;
        oval.set(left, top, right, bottom);
        canvas.drawArc(oval, 180, 180, false, paintLine);

        top = centerHeight * 2 - centerHeight / smallSquare;
        bottom = centerHeight * 2 - margeLine;
        canvas.drawRect(left, top, right, bottom, paintLine);

        left = centerWidth - (centerWidth / 2);
        right = centerWidth + (centerWidth / 2);
        top = margeLine;
        bottom = centerHeight / bigSquare;
        canvas.drawRect(left, top, right, bottom, paintLine);

        top = centerHeight * 2 - centerHeight / bigSquare;
        bottom = centerHeight * 2 - margeLine;
        canvas.drawRect(left, top, right, bottom, paintLine);
    }

    private void playersFill(Canvas canvas) {
        int centerX = 0, centerY = 0, typeTeam = 0, countRows = 0;
        Stack<Player> playerStack;
        playerStack = TeamUtils.toStackRevert(teams.getPlayersTeam1());
        countRows = teams.getSequenceTeam1().length;
        typeTeam = 1;

        int rowHeight = centerHeight / countRows;

        for (int i = 0; i < teams.getSequenceTeam1().length; i++) {
            int countInRow = teams.getSequenceTeam1()[i];
            centerY = rowHeight * (i + 1) - rowHeight / 2;
            for (int j = 0; j < countInRow; j++) {
                centerX = (getWidth() / (countInRow + 1)) * (j + 1);
                playerDraw(canvas, playerStack, typeTeam, centerX, centerY);
            }
        }

        playerStack = TeamUtils.toStack(teams.getPlayersTeam2());
        ArrayList<Integer> sequence = TeamUtils.arrayRevert(teams.getSequenceTeam2());
        countRows = sequence.size();
        typeTeam = 2;
        rowHeight = centerHeight / countRows;

        for (int i = 0; i < sequence.size(); i++) {
            int countInRow = sequence.get(i);
            centerY = centerHeight + (rowHeight * (i + 1) - rowHeight / 2);
            for (int j = 0; j < countInRow; j++) {
                centerX = (getWidth() / (countInRow + 1)) * (j + 1);
                playerDraw(canvas, playerStack, typeTeam, centerX, centerY);
            }
        }
    }

    private void playerDraw(Canvas canvas, Stack<Player> playerStack, int typeTeam, int centerX, int centerY) {
        paint = new Paint();
        Player player = playerStack.pop();
        Paint paintText = new Paint();
        paintText.setTextSize(playerRadius);
        paintText.setTextAlign(Paint.Align.CENTER);

        if (player.isGoalkeeper()) {
            paint.setColor(getContext().getResources().getColor(R.color.player_white));
            paintText.setColor(getContext().getResources().getColor(R.color.player_black));
        } else {
            if (typeTeam == 1) {
                paint.setColor(getContext().getResources().getColor(R.color.player_orange));
                paintText.setColor(getContext().getResources().getColor(R.color.player_black));
            } else if (typeTeam == 2) {
                paint.setColor(getContext().getResources().getColor(R.color.player_black));
                paintText.setColor(getContext().getResources().getColor(R.color.player_white));
            }
        }

        canvas.drawCircle(centerX, centerY, playerRadius, paint);

        canvas.drawText(player.getNumber(), centerX,
                centerY + (playerRadius / 2), paintText);

        paint.setColor(getContext().getResources().getColor(R.color.player_white));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        canvas.drawCircle(centerX, centerY, playerRadius, paint);

        paintText.setTextSize((float) (playerRadius * 0.7));
        paintText.setColor(getContext().getResources().getColor(R.color.player_black));
        canvas.drawText(player.getName(), centerX,
                centerY + playerRadius * 2, paintText);

        paintText.setColor(getContext().getResources().getColor(R.color.player_white));
        canvas.drawText(player.getName(), centerX - 5,
                centerY + playerRadius * 2 - 5, paintText);

        canvas.drawBitmap(bmpBall, centerX + playerRadius / 2, centerY + playerRadius / 2, null);
    }


}
