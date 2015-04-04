package com.gmail.mooman219.client.manager;


import java.util.ArrayList;

import com.gmail.mooman219.client.geo.World;

public class WorldManager {

    public static World currentWorld;
    public static ArrayList<World> worlds = new ArrayList<World>();

    public WorldManager(){}

    public static void init(){
        currentWorld = new World();
    }
}
