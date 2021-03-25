package com.example.watopoly.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.watopoly.enums.BuildingDirection;
import com.example.watopoly.model.Building;
import com.example.watopoly.model.CardTile;
import com.example.watopoly.model.Coordinates;
import com.example.watopoly.model.GoTile;
import com.example.watopoly.model.GoToJail;
import com.example.watopoly.model.Jail;
import com.example.watopoly.model.Parking;
import com.example.watopoly.model.Railway;
import com.example.watopoly.model.TaxTile;
import com.example.watopoly.model.Tile;
import com.example.watopoly.model.Utility;

public class BoardView extends View {

    private static final float spaceAboveAndBelowBoard = 60;
    private static final float spaceLeftOfBoard = 100;

    // changing numTilesInColumn and numTilesInRow requires changing the tiles array
    private static final int numTilesInColumn = 8;
    private static final float columnTileWidth = 150;
    private static final int numTilesInRow = 9;
    private static final float rowTileHeight = columnTileWidth;

    private static final int totalNumTiles = (numTilesInColumn * 2) + (numTilesInRow * 2) + 4;
    private Tile[] tiles = new Tile[totalNumTiles];

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initTiles();

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(4);

        float boardHeight = getHeight() - (spaceAboveAndBelowBoard * 2);

        //subtract corner tiles (height of corner tile is width of column tile)
        float cornerTileHeight = columnTileWidth;
        float columnHeight = boardHeight - (cornerTileHeight * 2);

        float tileInColumnHeight = columnHeight/numTilesInColumn;
        float rowTileWidth = tileInColumnHeight;

        float rowWidth = rowTileWidth * numTilesInRow;

        drawColumns(canvas, tileInColumnHeight, cornerTileHeight, rowWidth, paint);
        drawRows(canvas, rowTileWidth, cornerTileHeight, paint);
        drawCornerTiles(canvas, cornerTileHeight, rowWidth, paint);
    }

    public void drawColumns(Canvas canvas, float tileInColumnHeight, float cornerTileHeight, float rowWidth, Paint paint) {
        float bottomOffset = getHeight() - spaceAboveAndBelowBoard - cornerTileHeight;
        float leftOffsetForRightColumn = spaceLeftOfBoard + columnTileWidth + rowWidth;

        for (int i = 0; i < numTilesInColumn; i++) {
            // draw left column
            Coordinates leftColumnCoordinates = new Coordinates(
                    spaceLeftOfBoard,
                    bottomOffset - (tileInColumnHeight * (i + 1)),
                    spaceLeftOfBoard + columnTileWidth,
                    bottomOffset - (tileInColumnHeight * (i)));
            drawRectWithCoordinates(canvas, leftColumnCoordinates, paint);
            tiles[i+1].setCoordinates(leftColumnCoordinates);
            tiles[i+1].drawOn(canvas);

            // draw right column
            Coordinates rightColumnCoordinates = new Coordinates(
                    leftOffsetForRightColumn,
                    bottomOffset - (tileInColumnHeight * (i + 1)),
                    leftOffsetForRightColumn + columnTileWidth,
                    bottomOffset - (tileInColumnHeight * i));
            drawRectWithCoordinates(canvas, rightColumnCoordinates, paint);
            int rightColumnBottomTileIndex = totalNumTiles - numTilesInRow - 2;
            tiles[rightColumnBottomTileIndex-i].setCoordinates(rightColumnCoordinates);
            tiles[rightColumnBottomTileIndex-i].drawOn(canvas);
        }
    }

    public void drawRows(Canvas canvas, float rowTileWidth, float cornerTileHeight, Paint paint) {
        float leftOffset = spaceLeftOfBoard + cornerTileHeight;
        float bottomOffsetForBottomRow = getHeight() - spaceAboveAndBelowBoard;

        for (int i = 0; i < numTilesInRow; i++) {
            // draw bottom row
            Coordinates bottomRowCoordinates = new Coordinates(
                    leftOffset + (rowTileWidth * i),
                    bottomOffsetForBottomRow,
                    leftOffset + (rowTileWidth * (i + 1)),
                    bottomOffsetForBottomRow - rowTileHeight);
            drawRectWithCoordinates(canvas, bottomRowCoordinates, paint);
            int bottomRowLeftmostTileIndex = totalNumTiles - 1;
            tiles[bottomRowLeftmostTileIndex-i].setCoordinates(bottomRowCoordinates);
            tiles[bottomRowLeftmostTileIndex-i].drawOn(canvas);

            // draw top row
            Coordinates topRowCoordinates = new Coordinates(
                    leftOffset + (rowTileWidth * i),
                    spaceAboveAndBelowBoard,
                    leftOffset + (rowTileWidth * (i + 1)),
                    spaceAboveAndBelowBoard + rowTileHeight);
            drawRectWithCoordinates(canvas, topRowCoordinates, paint);
            int topRowRightmostTileIndex = numTilesInColumn + numTilesInRow + 1;
            tiles[topRowRightmostTileIndex-i].setCoordinates(topRowCoordinates);
            tiles[topRowRightmostTileIndex-i].drawOn(canvas);
        }
    }

    public void drawCornerTiles(Canvas canvas, float cornerTileHeight, float rowWidth, Paint paint) {
        float bottomOffset = getHeight() - spaceAboveAndBelowBoard;
        float leftOffset = spaceLeftOfBoard + rowWidth + columnTileWidth;

        // draw go tile
        Coordinates goCoordinates = new Coordinates(
                spaceLeftOfBoard,
                bottomOffset - cornerTileHeight,
                spaceLeftOfBoard + cornerTileHeight,
                bottomOffset);
        drawRectWithCoordinates(canvas, goCoordinates, paint);
        tiles[0].setCoordinates(goCoordinates);
        tiles[0].drawOn(canvas);

        // draw jail tile
        Coordinates jailCoordinates = new Coordinates(
                spaceLeftOfBoard,
                spaceAboveAndBelowBoard,
                spaceLeftOfBoard + cornerTileHeight,
                spaceAboveAndBelowBoard + cornerTileHeight);
        drawRectWithCoordinates(canvas, jailCoordinates, paint);
        tiles[numTilesInColumn+1].setCoordinates(jailCoordinates);
        tiles[numTilesInColumn+1].drawOn(canvas);

        // draw parking tile
        Coordinates parkingCoordinates = new Coordinates(
                leftOffset,
                spaceAboveAndBelowBoard,
                leftOffset + cornerTileHeight,
                spaceAboveAndBelowBoard + cornerTileHeight);
        drawRectWithCoordinates(canvas, parkingCoordinates, paint);
        int parkingIndex = numTilesInColumn + numTilesInRow + 2;
        tiles[parkingIndex].setCoordinates(parkingCoordinates);
        tiles[parkingIndex].drawOn(canvas);

        // draw goToJail tile
        Coordinates goToJailCoordinates = new Coordinates(
                leftOffset,
                bottomOffset - cornerTileHeight,
                leftOffset + cornerTileHeight,
                bottomOffset );
        drawRectWithCoordinates(canvas, goToJailCoordinates, paint);
        int goToJailIndex = totalNumTiles - numTilesInRow -  1;
        tiles[goToJailIndex].setCoordinates(goToJailCoordinates);
        tiles[goToJailIndex].drawOn(canvas);
    }


    public void drawRectWithCoordinates(Canvas canvas, Coordinates coordinates, Paint paint) {
        canvas.drawRect(coordinates.getLeft(),
                coordinates.getTop(),
                coordinates.getRight(),
                coordinates.getBottom(),
                paint);
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
