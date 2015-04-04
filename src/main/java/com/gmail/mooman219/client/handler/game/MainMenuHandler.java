package com.gmail.mooman219.client.handler.game;

import com.gmail.mooman219.client.AnotherRPG;
import com.gmail.mooman219.client.annotation.EventHandler;
import com.gmail.mooman219.client.enums.GameState;
import com.gmail.mooman219.client.event.Listener;
import com.gmail.mooman219.client.event.core.KeyPressEvent;
import com.gmail.mooman219.client.event.core.MouseMoveEvent;
import com.gmail.mooman219.client.event.core.MousePressEvent;
import com.gmail.mooman219.client.event.core.TextEvent;
import com.gmail.mooman219.client.event.core.game.ConnectEvent;
import com.gmail.mooman219.client.event.core.game.ConnectionInfoEvent;
import com.gmail.mooman219.client.event.core.game.UIRenderEvent;
import com.gmail.mooman219.client.manager.ConnectionManager;
import com.gmail.mooman219.client.manager.StateManager;
import com.gmail.mooman219.client.ui.GenericButton;
import com.gmail.mooman219.client.ui.GenericCheckBox;
import com.gmail.mooman219.client.ui.GenericInputField;
import com.gmail.mooman219.client.ui.GenericLabel;
import com.gmail.mooman219.client.ui.GenericRectangle;
import com.gmail.mooman219.shared.Order;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

public class MainMenuHandler implements Listener {

    // Overall

    private GenericRectangle mainBackground;
    // State 0
    private GenericLabel tempTitle;
    private GenericButton loginButton;
    private GenericButton optionButton;
    private GenericButton quitButton;
    // State 1
    private GenericLabel connectTitle;
    private GenericButton backButton;
    private GenericButton connectButton;
    private GenericInputField destField;
    private GenericInputField portField;
    private GenericInputField nameField;
    private GenericLabel destFieldLabel;
    private GenericLabel portFieldLabel;
    private GenericLabel nameFieldLabel;
    private GenericLabel errorLabel;
    private GenericCheckBox autoConnectCheckBox;

    private int state = 0;

    public MainMenuHandler() {
        mainBackground = new GenericRectangle(AnotherRPG.WIDTH / 4, AnotherRPG.HEIGHT / 4, AnotherRPG.WIDTH / 2, AnotherRPG.HEIGHT / 2);
        // State 1
        tempTitle = new GenericLabel(AnotherRPG.WIDTH / 2 - (AnotherRPG.font.getWidth("AnotherRPG (alpha)") / 2), (AnotherRPG.HEIGHT / 2) - 100, "AnotherRPG (alpha)");
        loginButton = new GenericButton(AnotherRPG.WIDTH / 2 - (75), (int) (AnotherRPG.HEIGHT / 1.5) - 160, 150, 28, "Login");
        optionButton = new GenericButton(AnotherRPG.WIDTH / 2 - (75), (int) (AnotherRPG.HEIGHT / 1.5) - 120, 150, 28, "Options");
        quitButton = new GenericButton(AnotherRPG.WIDTH / 2 - (75), (int) (AnotherRPG.HEIGHT / 1.5) - 40, 150, 28, "Quit");

        tempTitle.setTextColor(new Color(0.4f, 0.2f, 0.2f, 1f));
        // State 2
        connectTitle = new GenericLabel(AnotherRPG.WIDTH / 2 - (AnotherRPG.font.getWidth("Connection") / 2), (AnotherRPG.HEIGHT / 2) - 100, "Connection");
        backButton = new GenericButton(AnotherRPG.WIDTH / 2 - (75), (int) (AnotherRPG.HEIGHT / 1.5) - 40, 150, 28, "Back");
        connectButton = new GenericButton(AnotherRPG.WIDTH / 2 - (75), (int) (AnotherRPG.HEIGHT / 1.5) - 80, 150, 28, "Connect");

        nameField = new GenericInputField(AnotherRPG.WIDTH / 2 - (95), (int) (AnotherRPG.HEIGHT / 1.5) - 200, 180, 28, 4);
        destField = new GenericInputField(AnotherRPG.WIDTH / 2 - (95), (int) (AnotherRPG.HEIGHT / 1.5) - 160, 180, 28, 4);
        portField = new GenericInputField(AnotherRPG.WIDTH / 2 - (95), (int) (AnotherRPG.HEIGHT / 1.5) - 120, 180, 28, 4);

        errorLabel = new GenericLabel((AnotherRPG.WIDTH / 4) + 5, (AnotherRPG.HEIGHT / 4 * 3) - 20, "");
        nameFieldLabel = new GenericLabel(AnotherRPG.WIDTH / 2 - (145), (int) (AnotherRPG.HEIGHT / 1.5) - 200, "Name:");
        destFieldLabel = new GenericLabel(AnotherRPG.WIDTH / 2 - (145), (int) (AnotherRPG.HEIGHT / 1.5) - 160, "IP:");
        portFieldLabel = new GenericLabel(AnotherRPG.WIDTH / 2 - (145), (int) (AnotherRPG.HEIGHT / 1.5) - 120, "Port:");
        autoConnectCheckBox = new GenericCheckBox(AnotherRPG.WIDTH / 2 + (90), (int) (AnotherRPG.HEIGHT / 1.5) - 160, 28, 28);

        errorLabel.setTextColor(new Color(0.6f, 0.2f, 0.2f, 1f));
        autoConnectCheckBox.setSelected(false);
        nameField.setText("Temp");
        destField.setText("69.207.153.108");
        portField.setText("32001");
    }

    @EventHandler(state = GameState.MAINMENU)
    public void onRender(UIRenderEvent e) {
        if (state == 0) {
            mainBackground.onRender(e);
            loginButton.onRender(e);
            quitButton.onRender(e);
            optionButton.onRender(e);
            tempTitle.onRender(e);
        } else if (state == 1) {
            errorLabel.onRender(e);
            mainBackground.onRender(e);
            connectTitle.onRender(e);
            backButton.onRender(e);
            connectButton.onRender(e);
            nameField.onRender(e);
            destField.onRender(e);
            portField.onRender(e);
            nameFieldLabel.onRender(e);
            destFieldLabel.onRender(e);
            portFieldLabel.onRender(e);
            autoConnectCheckBox.onRender(e);
        } else if (state == 2) {
            return;
        }
    }

    @EventHandler(state = GameState.MAINMENU)
    public void onMouseMove(MouseMoveEvent e) {
        if (state == 0) {
            loginButton.onMouseMove(e);
            quitButton.onMouseMove(e);
            optionButton.onMouseMove(e);
        } else if (state == 1) {
            backButton.onMouseMove(e);
            connectButton.onMouseMove(e);
            autoConnectCheckBox.onMouseMove(e);
        } else if (state == 2) {
            return;
        }
    }

    @EventHandler(state = GameState.MAINMENU)
    public void onMousePress(MousePressEvent e) {
        if (state == 0) {
            if (loginButton.contains(e.getX(), e.getY())) {
                state = 1;
                refreshKeys();
            } else if (optionButton.contains(e.getX(), e.getY())) {
                System.out.println("How about the option to stop clicking this.");
            } else if (quitButton.contains(e.getX(), e.getY())) {
                Display.destroy();
                System.exit(0);
            }
        } else if (state == 1) {
            if (autoConnectCheckBox.contains(e.getX(), e.getY())) {
                autoConnectCheckBox.setSelected(!autoConnectCheckBox.isSelected());
                destField.setSelected(!autoConnectCheckBox.isSelected());
                destField.setEnabled(!autoConnectCheckBox.isSelected());
            }
            nameField.setSelected(nameField.contains(e.getX(), e.getY()));
            if (!autoConnectCheckBox.isSelected()) {
                destField.setSelected(destField.contains(e.getX(), e.getY()));
            }
            portField.setSelected(portField.contains(e.getX(), e.getY()));
            if (backButton.contains(e.getX(), e.getY())) {
                state = 0;
                refreshKeys();
            } else if (connectButton.contains(e.getX(), e.getY())) {
                ConnectionManager.connection.name = nameField.getText();
                ConnectionManager.connection.serverPort = Integer.parseInt(portField.getText());
                ConnectionManager.connection.destination = destField.getText();
                ConnectionManager.connection.autoConnect = autoConnectCheckBox.isSelected();
                ConnectionManager.establishConnection();
            }
        } else if (state == 2) {
            return;
        }
    }

    @EventHandler(state = GameState.MAINMENU)
    public void onText(TextEvent e) {
        if (state == 1) {
            if (portField.isSelected()) {
                portField.addText(e.getInput());
            } else if (destField.isSelected()) {
                destField.addText(e.getInput());
            } else if (nameField.isSelected()) {
                nameField.addText(e.getInput());
            }
        } else if (state == 2) {
            return;
        }
    }

    @EventHandler(order = Order.EARLY, state = GameState.MAINMENU)
    public void onKeyPress(KeyPressEvent e) {
        if (state == 1) {
            if (portField.isSelected()) {
                if (e.getKeyID() == 28) {
                    portField.setSelected(false);
                }
                if (e.getKeyID() == 14) {
                    portField.backspace();
                }
                e.setCancelled(true);
                if (e.getKeyID() == 28) {
                    portField.setSelected(false);
                }
            } else if (destField.isSelected()) {
                if (e.getKeyID() == 28) {
                    destField.setSelected(false);
                }
                if (e.getKeyID() == 14) {
                    destField.backspace();
                }
                e.setCancelled(true);
                if (e.getKeyID() == 28) {
                    destField.setSelected(false);
                }
            } else if (nameField.isSelected()) {
                if (e.getKeyID() == 28) {
                    nameField.setSelected(false);
                }
                if (e.getKeyID() == 14) {
                    nameField.backspace();
                }
                e.setCancelled(true);
                if (e.getKeyID() == 28) {
                    nameField.setSelected(false);
                }
            }
        }
    }

    @EventHandler(state = GameState.MAINMENU)
    public void onConnect(ConnectEvent e) {
        StateManager.setCurrentState(GameState.INGAME);
        state = 0;
    }

    @EventHandler(state = GameState.MAINMENU)
    public void onConnectionError(ConnectionInfoEvent e) {
        errorLabel.setText(e.getInfoMessage());
    }

    public void refreshKeys() {
        loginButton.onMouseMove(new MouseMoveEvent(0, 0, 0, 0));
        quitButton.onMouseMove(new MouseMoveEvent(0, 0, 0, 0));
        optionButton.onMouseMove(new MouseMoveEvent(0, 0, 0, 0));
        backButton.onMouseMove(new MouseMoveEvent(0, 0, 0, 0));
        connectButton.onMouseMove(new MouseMoveEvent(0, 0, 0, 0));
    }
}
