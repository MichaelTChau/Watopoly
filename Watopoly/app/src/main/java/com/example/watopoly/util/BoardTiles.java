package com.example.watopoly.util;

import com.example.watopoly.enums.Direction;
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

import java.util.ArrayList;
import java.util.Arrays;

public final class BoardTiles {

    private static ArrayList<Tile> boardTiles = new ArrayList<>(Arrays.asList(
            new GoTile(),
            new Building("CAMJ", Direction.LEFT, 2, 60, "#955436"),
            new CardTile("Community Chest", Direction.LEFT),
            new Building("Earth Sci", Direction.LEFT, 4, 60, "#955436"),
            new TaxTile(Direction.LEFT),
            new Railway("Ion Train 1", Direction.LEFT, 25, 200),
            new Building("CPH", Direction.LEFT, 6, 100, "#AAE0FA"),
            new CardTile("Chance", Direction.LEFT),
            new Building("DWE", Direction.LEFT, 8, 120, "#AAE0FA"),
            new Jail(),
            new Building("RCH", Direction.TOP, 10, 140, "#D93A96"),
            new Utility("Eng C&D", Direction.TOP, 0, 150),
            new Building("ALH", Direction.TOP, 10, 140, "#D93A96"),
            new Building("HH", Direction.TOP, 12, 160, "#D93A96"),
            new Railway("Ion Train 2", Direction.TOP, 25, 200),
            new Building("PAS", Direction.TOP, 14, 180, "#F7941D"),
            new CardTile("Community Chest", Direction.TOP),
            new Building("EV2", Direction.TOP, 14, 180, "#F7941D"),
            new Building("EV3", Direction.TOP, 16, 200, "#F7941D"),
            new Parking(),
            new Building("ML", Direction.RIGHT, 18, 220, "#ED1B24"),
            new CardTile("Chance", Direction.RIGHT),
            new Building("Needles Hall", Direction.RIGHT, 18, 220, "#ED1B24"),
            new Building("STC", Direction.RIGHT, 20, 240, "#ED1B24"),
            new Railway("Ion Train 3", Direction.RIGHT, 25, 200),
            new Building("QNC", Direction.RIGHT, 22, 260, "#FEF200"),
            new Utility("Math C&D", Direction.RIGHT, 0, 150),
            new Building("PAC", Direction.RIGHT, 24, 280, "#FEF200"),
            new GoToJail(),
            new Building("AHS", Direction.BOTTOM, 26, 300, "#1FB25A"),
            new Building("BMH", Direction.BOTTOM, 26, 300, "#1FB25A"),
            new CardTile("Community Chest", Direction.BOTTOM),
            new Building("M3", Direction.BOTTOM, 28, 320, "#1FB25A"),
            new Railway("Ion Train 4", Direction.BOTTOM, 25, 200),
            new CardTile("Chance", Direction.BOTTOM),
            new Building("DC", Direction.BOTTOM, 35, 350, "#0072BB"),
            new TaxTile(Direction.BOTTOM),
            new Building("E7", Direction.BOTTOM, 50, 400, "#0072BB")));

    public static ArrayList<Tile> getTiles() {
        return boardTiles;
    }
}


