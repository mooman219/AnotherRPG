package com.gmail.mooman219.test3D.input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import com.gmail.mooman219.shared.geo.vec.Vec3f;
import com.gmail.mooman219.test3D.AnotherClient;
import com.gmail.mooman219.test3D.event.EventHandler;
import com.gmail.mooman219.test3D.event.Listener;
import com.gmail.mooman219.test3D.event.core.KeyPressEvent;
import com.gmail.mooman219.test3D.event.core.KeyReleaseEvent;
import com.gmail.mooman219.test3D.event.core.MouseMoveEvent;
import com.gmail.mooman219.test3D.event.core.MousePressEvent;
import com.gmail.mooman219.test3D.event.core.TickEvent;


public class PlayerController implements Listener{
    public final int maxLookUp = 90;
    public final int maxLookDown = -90;
    public int walkingSpeed = 10;
    public int mouseSpeed = 2;
    //
    private boolean keyUp = false;
    private boolean keyDown = false;
    private boolean keyLeft = false;
    private boolean keyRight = false;
    private boolean flyUp = false;
    private boolean flyDown = false;
    private boolean moveFaster = false;
    private boolean moveSlower = false;
    //
    public PlayerController(){}

    @EventHandler
    public void onKeyPress(KeyPressEvent e){
        if (!Mouse.isGrabbed()) {
            return;
        }
        switch(e.getKeyID()){
        case Keyboard.KEY_C:
            AnotherClient.position = new Vec3f(0, 2, 0);
            AnotherClient.rotation = new Vec3f(0, 2, 0);
            break;
        case Keyboard.KEY_O:
            mouseSpeed += 1;
            System.out.println("Mouse speed changed to "+mouseSpeed+".");
            break;
        case Keyboard.KEY_L:
            if (mouseSpeed-1 > 0) {
                mouseSpeed -= 1;
                System.out.println("Mouse speed changed to "+mouseSpeed+".");
            }
            break;
        case Keyboard.KEY_I:
            System.out.println("Walking speed changed to "+walkingSpeed+".");
            walkingSpeed += 1;
            break;
        case Keyboard.KEY_K:
            System.out.println("Walking speed changed to "+walkingSpeed+".");
            walkingSpeed -= 1;
            break;
        case Keyboard.KEY_ESCAPE:
            AnotherClient.shutdown();
            break;
        case Keyboard.KEY_W:
            keyUp = true;
            break;
        case Keyboard.KEY_S:
            keyDown = true;
            break;
        case Keyboard.KEY_A:
            keyLeft = true;
            break;
        case Keyboard.KEY_D:
            keyRight = true;
            break;
        case Keyboard.KEY_SPACE:
            flyUp = true;
            break;
        case Keyboard.KEY_LSHIFT:
            flyDown = true;
            break;
        case Keyboard.KEY_LCONTROL:
            moveFaster = true;
            break;
        case Keyboard.KEY_TAB:
            moveSlower = true;
            break;
        default:
            break;
        }
    }

    @EventHandler
    public void onKeyRelease(KeyReleaseEvent e){
        switch(e.getKeyID()){
        case Keyboard.KEY_W:
            keyUp = false;
            break;
        case Keyboard.KEY_S:
            keyDown = false;
            break;
        case Keyboard.KEY_A:
            keyLeft = false;
            break;
        case Keyboard.KEY_D:
            keyRight = false;
            break;
        case Keyboard.KEY_SPACE:
            flyUp = false;
            break;
        case Keyboard.KEY_LSHIFT:
            flyDown = false;
            break;
        case Keyboard.KEY_LCONTROL:
            moveFaster = false;
            break;
        case Keyboard.KEY_TAB:
            moveSlower = false;
            break;
        default:
            break;
        }
    }

    @EventHandler
    public void onMousePress(MousePressEvent e){
        if (Mouse.isButtonDown(0)) {
            Mouse.setGrabbed(true);
        }else if (Mouse.isButtonDown(1)) {
            Mouse.setGrabbed(false);
        }
    }

    @EventHandler
    public void onMouseMove(MouseMoveEvent e){
        if (Mouse.isGrabbed()) {
            float mouseDX = e.getRealDX()*mouseSpeed*0.16f;
            float mouseDY = e.getRealDY()*mouseSpeed*0.16f;
            Vec3f rotation = AnotherClient.rotation;

            if (AnotherClient.rotation.y+mouseDX >= 360) {
                rotation.y = rotation.y+mouseDX-360;
            } else if (rotation.y+mouseDX < 0) {
                rotation.y = 360-rotation.y+mouseDX;
            } else {
                rotation.y += mouseDX;
            }

            if (rotation.x-mouseDY >= maxLookDown && rotation.x-mouseDY <= maxLookUp) {
                rotation.x += -mouseDY;
            } else if (rotation.x-mouseDY < maxLookDown) {
                rotation.x = maxLookDown;
            } else if (rotation.x-mouseDY > maxLookUp) {
                rotation.x = maxLookUp;
            }

            AnotherClient.rotation = rotation;
        }
    }

    @EventHandler
    public void onTick(TickEvent e){
        if (moveFaster && !moveSlower) {
            walkingSpeed *= 4f;
        }
        if (moveSlower && !moveFaster) {
            walkingSpeed /= 10f;
        }

        if (keyUp && keyRight && !keyLeft && !keyDown) {
            updatePosition(AnotherClient.rotation.y+45, e.getDeltaTime());
        }else if (keyUp && keyLeft && !keyRight && !keyDown) {
            updatePosition(AnotherClient.rotation.y-45, e.getDeltaTime());
        }else if (keyUp && !keyLeft && !keyRight && !keyDown) {
            updatePosition(AnotherClient.rotation.y, e.getDeltaTime());
        }else if (keyDown && keyLeft && !keyRight && !keyUp) {
            updatePosition(AnotherClient.rotation.y-135, e.getDeltaTime());
        }else if (keyDown && keyRight && !keyLeft && !keyUp) {
            updatePosition(AnotherClient.rotation.y+135, e.getDeltaTime());
        }else if (keyDown && !keyUp && !keyLeft && !keyRight) {
            updatePosition(AnotherClient.rotation.y-180, e.getDeltaTime());
        }else if (keyLeft && !keyRight && !keyUp && !keyDown) {
            updatePosition(AnotherClient.rotation.y-90, e.getDeltaTime());
        }else if (keyRight && !keyLeft && !keyUp && !keyDown) {
            updatePosition(AnotherClient.rotation.y+90, e.getDeltaTime());
        }

        if (flyUp && !flyDown) {
            double newPositionY = (walkingSpeed*0.0002)*e.getDeltaTime();
            AnotherClient.position.y -= newPositionY;
        }
        else if (flyDown && !flyUp) {
            double newPositionY = (walkingSpeed*0.0002)*e.getDeltaTime();
            AnotherClient.position.y += newPositionY;
        }

        if (moveFaster && !moveSlower) {
            walkingSpeed /= 4f;
        }
        else if (moveSlower && !moveFaster) {
            walkingSpeed *= 10f;
        }
    }

    private void updatePosition(float angle, long delta){
        Vec3f newPosition = new Vec3f(AnotherClient.position);
        float oblique = (walkingSpeed*0.0002f)*delta;
        float adjacent = oblique*(float) Math.cos(Math.toRadians(angle));
        float opposite = (float) (Math.sin(Math.toRadians(angle))*oblique);
        newPosition.z += adjacent;
        newPosition.x -= opposite;
        AnotherClient.position.z = newPosition.z;
        AnotherClient.position.x = newPosition.x;
    }
}
