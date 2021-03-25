package com.example.watopoly.util;

import com.example.watopoly.enums.BuildingDirection;
import com.example.watopoly.model.Building;
import com.example.watopoly.model.CardTile;
import com.example.watopoly.model.GoTile;
import com.example.watopoly.model.GoToJail;
import com.example.watopoly.model.Jail;
import com.example.watopoly.model.Parking;
import com.example.watopoly.model.Railway;
import com.example.watopoly.model.TaxTile;
import com.example.watopoly.model.Tile;
import com.example.watopoly.model.Utility;

public final class Tiles {
    private static Tile[] tiles = new Tile[38];

    private Tiles () {
        System.out.println("hello");
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

    public static Tile[] getTiles() {
        return tiles;
    }
}


