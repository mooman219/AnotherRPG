package com.gmail.mooman219.server.handler;

import com.gmail.mooman219.server.annotation.EventHandler;
import com.gmail.mooman219.server.event.Listener;
import com.gmail.mooman219.server.event.core.TickEvent;
import com.gmail.mooman219.server.manager.network.SettingsManager;

public class TPSHandler implements Listener {

    public long lastAverage;

    public TPSHandler() {
        lastAverage = 0;
    }

    @EventHandler
    public void onTick(TickEvent e) {
        lastAverage = (lastAverage + e.getDeltaTime()) / 2;
        SettingsManager.serverGUI.setTitle("AnotherServer TPS: " + lastAverage);
    }
}
