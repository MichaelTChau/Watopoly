package com.example.watopoly.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

import com.example.watopoly.R;
import com.example.watopoly.enums.BuildingDirection;
import com.example.watopoly.model.Building;
import com.example.watopoly.model.CardTile;
import com.example.watopoly.model.Coordinates;
import com.example.watopoly.model.GoTile;
import com.example.watopoly.model.GoToJail;
import com.example.watopoly.model.Jail;
import com.example.watopoly.model.Parking;
import com.example.watopoly.model.Property;
import com.example.watopoly.model.Railway;
import com.example.watopoly.model.TaxTile;
import com.example.watopoly.model.Tile;
import com.example.watopoly.model.Utility;
import com.example.watopoly.util.Tiles;

import java.util.HashMap;

public class BoardView extends View {

    private Tile[] tiles = new Tile[38];

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(4);

        Paint inside = new Paint();
        inside.setColor(Color.YELLOW);

        float screenHeight = getHeight() + 60;
        float screenWidth  = getWidth();

        // 15 and 20 are hardcoded constants
        float topRowTileBottomCoordinate = (float) (screenHeight/15);
        float leftColumnTileLeftCoordinate = (float) (screenWidth/20);

        float columnTileWidth = (float)(leftColumnTileLeftCoordinate * 1.5);
        float diff = (float)(topRowTileBottomCoordinate*3) - columnTileWidth;

        initTiles();
        drawBuildingColumns(canvas, topRowTileBottomCoordinate, topRowTileBottomCoordinate, leftColumnTileLeftCoordinate, topRowTileBottomCoordinate, mPaint);
        drawBuildingRows(canvas, leftColumnTileLeftCoordinate, topRowTileBottomCoordinate, mPaint);
        drawCornerTiles(canvas, leftColumnTileLeftCoordinate, topRowTileBottomCoordinate, diff, columnTileWidth, mPaint);

        Drawable d = ResourcesCompat.getDrawable(getResources(), R.drawable.android, null);
        Bitmap b = ((BitmapDrawable) d).getBitmap();
        canvas.drawBitmap(b, 50, 50, mPaint);
    }

    public void drawBuildingColumns(Canvas canvas, float bottomCoordinate, float topCoordinate, float leftColumnTileLeftCoordinate, float topRowTileBottomCoordinate, Paint mPaint) {

        float buildingRowLength = (float)(9 * topRowTileBottomCoordinate);
        float leftCoordinate = (float) (leftColumnTileLeftCoordinate * 2.5) + buildingRowLength;
        float second = (float) (leftColumnTileLeftCoordinate * 1.5);

        for (int i = 3; i < 11; i++) {
            // draw left column
            canvas.drawRect(leftColumnTileLeftCoordinate, topCoordinate * i, (float) (leftColumnTileLeftCoordinate * 2.5), bottomCoordinate * (i+1), mPaint);
            Coordinates leftCoordinates = new Coordinates (leftColumnTileLeftCoordinate, topCoordinate * i, (float) (leftColumnTileLeftCoordinate * 2.5), bottomCoordinate * (i+1));
            tiles[i-2].setCoordinates(leftCoordinates);
            tiles[i-2].drawOn(canvas);

            // draw right column
            canvas.drawRect(leftCoordinate, topCoordinate * i, leftCoordinate + second, bottomCoordinate * (i+1), mPaint);
            Coordinates rightCoordinates = new Coordinates (leftCoordinate, topCoordinate * i, leftCoordinate + second, bottomCoordinate * (i+1));
            tiles[30-i].setCoordinates(rightCoordinates);
            tiles[30-i].drawOn(canvas);
        }
    }

    public void drawBuildingRows(Canvas canvas, float leftColumnTileLeftCoordinate, float topRowTileBottomCoordinate, Paint mPaint) {

        float endOfLeftColumn = (float)(leftColumnTileLeftCoordinate * 2.5);
        float second = (float) (leftColumnTileLeftCoordinate * 1.5);
        float diff = (float)(topRowTileBottomCoordinate*3) - ((float)(leftColumnTileLeftCoordinate*2.5) - leftColumnTileLeftCoordinate);

        int factor = 16;
        for (int i = 0; i < 9; i++) {
            // draw top row
            canvas.drawRect(endOfLeftColumn + (i * topRowTileBottomCoordinate),(float)(topRowTileBottomCoordinate*3) , endOfLeftColumn + ((i+1)*(topRowTileBottomCoordinate)),diff ,mPaint);
            Coordinates topRowCoordinates = new Coordinates(endOfLeftColumn + (i * topRowTileBottomCoordinate),(float)(topRowTileBottomCoordinate*3) , endOfLeftColumn + ((i+1)*(topRowTileBottomCoordinate)),diff);
            tiles[i+10].setCoordinates(topRowCoordinates);
            tiles[i+10].drawOn(canvas);

            // draw bottom row
            canvas.drawRect(endOfLeftColumn + (i * topRowTileBottomCoordinate),(float)(topRowTileBottomCoordinate*11)+second , endOfLeftColumn + ((i+1)*(topRowTileBottomCoordinate)), (float)(topRowTileBottomCoordinate*11) ,mPaint);
            Coordinates bottomRowCoordinates = new Coordinates(endOfLeftColumn + (i * topRowTileBottomCoordinate),(float)(topRowTileBottomCoordinate*11)+second , endOfLeftColumn + ((i+1)*(topRowTileBottomCoordinate)), (float)(topRowTileBottomCoordinate*11));
            tiles[37-i].setCoordinates(bottomRowCoordinates);
            tiles[37-i].drawOn(canvas);
        }
    }

    public void drawCornerTiles(Canvas canvas, float leftColumnTileLeftCoordinate, float topRowTileBottomCoordinate, float diff, float columnTileWidth, Paint mPaint) {

        // draw jail
        canvas.drawRect((float) leftColumnTileLeftCoordinate, (float)(topRowTileBottomCoordinate*3), (float)(leftColumnTileLeftCoordinate*2.5), diff, mPaint);
        Coordinates jailCoordinates = new Coordinates((float) leftColumnTileLeftCoordinate, (float)(topRowTileBottomCoordinate*3), (float)(leftColumnTileLeftCoordinate*2.5), diff);

        // draw go
        canvas.drawRect((float) leftColumnTileLeftCoordinate, (float)(topRowTileBottomCoordinate*11)+columnTileWidth, (float)(leftColumnTileLeftCoordinate*2.5), (float)(topRowTileBottomCoordinate*11), mPaint);
        Coordinates goCoordinates = new Coordinates((float) leftColumnTileLeftCoordinate, (float)(topRowTileBottomCoordinate*11)+columnTileWidth, (float)(leftColumnTileLeftCoordinate*2.5), (float)(topRowTileBottomCoordinate*11));

        // draw free parking
        canvas.drawRect((float)(leftColumnTileLeftCoordinate*2.5)+(9*topRowTileBottomCoordinate), (float)(topRowTileBottomCoordinate*11)+columnTileWidth, (float)(leftColumnTileLeftCoordinate*2.5)+((9)*(topRowTileBottomCoordinate))+columnTileWidth, (float)(topRowTileBottomCoordinate*11), mPaint);
        Coordinates parkingCoordinates = new Coordinates((float)(leftColumnTileLeftCoordinate*2.5)+(9*topRowTileBottomCoordinate), (float)(topRowTileBottomCoordinate*11)+columnTileWidth, (float)(leftColumnTileLeftCoordinate*2.5)+((9)*(topRowTileBottomCoordinate))+columnTileWidth, (float)(topRowTileBottomCoordinate*11));

        // draw go to jail
        canvas.drawRect((float)(leftColumnTileLeftCoordinate*2.5)+(9*topRowTileBottomCoordinate), (float)(topRowTileBottomCoordinate*3), (float)(leftColumnTileLeftCoordinate*2.5)+((9)*(topRowTileBottomCoordinate))+columnTileWidth, diff, mPaint);
        Coordinates goToJailCoordinates = new Coordinates((float)(leftColumnTileLeftCoordinate*2.5)+(9*topRowTileBottomCoordinate), (float)(topRowTileBottomCoordinate*3), (float)(leftColumnTileLeftCoordinate*2.5)+((9)*(topRowTileBottomCoordinate))+columnTileWidth, diff);
    }

    public void initTiles() {
            tiles[0] = new GoTile();
            tiles[9] = new Jail();
            tiles[19] = new Parking();
            tiles[28] = new GoToJail();

            tiles[1] = new Building("CAMJ", BuildingDirection.LEFT, 2, 60, "#955436");
            tiles[2] = new CardTile("Community Chest", BuildingDirection.LEFT);
            tiles[3] = new Building("Earth Sci", BuildingDirection.LEFT, 4, 60, "#955436");
            tiles[4] = new TaxTile(BuildingDirection.LEFT);
            tiles[5] = new Railway("Ion Train 1", BuildingDirection.LEFT, 25, 200);
            tiles[6] = new Building("CPH", BuildingDirection.LEFT, 6, 100, "#AAE0FA");
            tiles[7] = new CardTile("Chance", BuildingDirection.LEFT);
            tiles[8] = new Building("DWE", BuildingDirection.LEFT, 8, 120, "#AAE0FA");

            tiles[10] =  new Building("RCH", BuildingDirection.TOP, 10, 140, "#D93A96");
            tiles[11] =  new Utility("Eng C&D", BuildingDirection.TOP, 0, 150);
            tiles[12] =  new Building("ALH", BuildingDirection.TOP, 10, 140, "#D93A96");
            tiles[13] =  new Building("HH", BuildingDirection.TOP, 12, 160, "#D93A96");
            tiles[14] =  new Railway("Ion Train 2", BuildingDirection.TOP, 25, 200);
            tiles[15] =  new Building("PAS", BuildingDirection.TOP, 14, 180, "#F7941D");
            tiles[16] =  new CardTile("Community Chest", BuildingDirection.TOP);
            tiles[17] =  new Building("EV2", BuildingDirection.TOP, 14, 180, "#F7941D");
            tiles[18] =  new Building("EV3", BuildingDirection.TOP, 16, 200, "#F7941D");

            tiles[20] =  new Building("ML", BuildingDirection.RIGHT, 18, 220, "#ED1B24");
            tiles[21] =  new CardTile("Chance", BuildingDirection.RIGHT);
            tiles[22] =  new Building("Needles Hall", BuildingDirection.RIGHT, 18, 220, "#ED1B24");
            tiles[23] =  new Building("STC", BuildingDirection.RIGHT, 20, 240, "#ED1B24");
            tiles[24] =  new Railway("Ion Train 3", BuildingDirection.RIGHT, 25, 200);
            tiles[25] =  new Building("QNC", BuildingDirection.RIGHT, 22, 260, "#FEF200");
            tiles[26] =  new Utility("Math C&D", BuildingDirection.RIGHT, 0, 150);
            tiles[27] =  new Building("PAC", BuildingDirection.RIGHT, 24, 280, "#FEF200");

            tiles[29] =  new Building("AHS", BuildingDirection.BOTTOM, 26, 300, "#1FB25A");
            tiles[30] =  new Building("BMH", BuildingDirection.BOTTOM, 26, 300, "#1FB25A");
            tiles[31] =  new CardTile("Community Chest", BuildingDirection.BOTTOM);
            tiles[32] =  new Building("M3", BuildingDirection.BOTTOM, 28, 320, "#1FB25A");
            tiles[33] =  new Railway("Ion Train 4", BuildingDirection.BOTTOM, 25, 200);
            tiles[34] =  new CardTile("Chance", BuildingDirection.BOTTOM);
            tiles[35] =  new Building("DC", BuildingDirection.BOTTOM, 35, 350, "#0072BB");
            tiles[36] =  new TaxTile(BuildingDirection.BOTTOM);
            tiles[37] =  new Building("E7", BuildingDirection.BOTTOM, 50, 400, "#0072BB");
    }

    public Tile[] getTiles() {return tiles;}
}
