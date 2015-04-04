package com.gmail.mooman219.test3D.physics.events;
import com.gmail.mooman219.test3D.physics.entities.Entity;


public class CollisionEvent extends Event {
	
	public CollisionEvent(Entity one, Entity two){
		super(1, one, two);
	}
}
