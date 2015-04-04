package com.gmail.mooman219.test3D.physics.events;

import java.awt.Point;

import com.gmail.mooman219.test3D.physics.entities.Entity;

public class VelocityCollisionEvent extends Event {
	
	public VelocityCollisionEvent(Entity one, Entity two, Point velocity){
		super(3, one, two, velocity);
	}
}
