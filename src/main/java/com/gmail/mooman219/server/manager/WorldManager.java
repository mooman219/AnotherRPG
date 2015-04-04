package com.gmail.mooman219.server.manager;

import java.util.ArrayList;

import com.gmail.mooman219.shared.geo.GenericWorld;

public class WorldManager {
    public static GenericWorld currentWorld;
    public static ArrayList<GenericWorld> worlds = new ArrayList<GenericWorld>();

    public WorldManager(){}

    public static void init(){
        currentWorld = new GenericWorld();
        worlds.add(currentWorld);
    }
}
