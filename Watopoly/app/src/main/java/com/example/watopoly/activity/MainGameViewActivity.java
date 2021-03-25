package com.example.watopoly.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.watopoly.R;
import com.example.watopoly.enums.BuildingDirection;
import com.example.watopoly.fragment.PlayerInfoHeaderFragment;
import com.example.watopoly.model.Building;
import com.example.watopoly.model.CardTile;
import com.example.watopoly.model.Coordinates;
import com.example.watopoly.model.Game;
import com.example.watopoly.model.GoTile;
import com.example.watopoly.model.GoToJail;
import com.example.watopoly.model.Jail;
import com.example.watopoly.model.Parking;
import com.example.watopoly.model.Player;
import com.example.watopoly.model.Railway;
import com.example.watopoly.model.TaxTile;
import com.example.watopoly.model.Tile;
import com.example.watopoly.model.Utility;
import com.example.watopoly.view.BoardView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainGameViewActivity extends AppCompatActivity {
    //TODO: move this somewhere else?
    private Game gameState = Game.getInstance();
    private static final double startingMoney = 500;

    private PlayerInfoHeaderFragment playerInfoHeaderFragment;
    private Tile[] tiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game_view);
        linkView();
        setup();
        playerInfoHeaderFragment.setPlayer(gameState.nextTurn());

        boardSetup();


        //TODO: rolling
    }

    @SuppressLint("ClickableViewAccessibility")
    private void boardSetup() {
        BoardView boardView = findViewById(R.id.board);
        boardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // can click on tiles to see tile information in the future
                // System.out.println(motionEvent.getX());
                // System.out.println(motionEvent.getY());
                return false;
            }
        });
    }

    private void linkView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        FragmentManager fm = getSupportFragmentManager();
        playerInfoHeaderFragment = (PlayerInfoHeaderFragment) fm.findFragmentById(R.id.playerInfoHeaderFragment);

        BoardView boardView = findViewById(R.id.board);
        tiles = boardView.getTiles();

        //TODO: bind button to the activity
        Button buyButton = findViewById(R.id.buyPropertyButton);
        buyButton.setVisibility(View.GONE);
        Button viewAssetButton = findViewById(R.id.viewAssetButton);
        Button tradeButton = findViewById(R.id.tradeButton);
        Button mortgageButton = findViewById(R.id.mortgageButton);
        Button endTurnButton = findViewById(R.id.endTurnButton);
    }

    private void setup() {
        Intent intent = getIntent();
        ArrayList<Integer> icons = intent.getIntegerArrayListExtra("icons");
        ArrayList<String> colours = intent.getStringArrayListExtra("colours");
        ArrayList<String> names = intent.getStringArrayListExtra("names");

        //TODO: set other metadata
        for (int x = 0; x < names.size(); x++) {
            Player player = new Player(names.get(x), startingMoney, colours.get(x), icons.get(x));
            gameState.addPlayer(player);
        }
    }
}
