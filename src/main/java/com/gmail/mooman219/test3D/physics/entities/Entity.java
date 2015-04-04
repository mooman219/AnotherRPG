package com.gmail.mooman219.test3D.physics.entities;

import com.gmail.mooman219.test3D.physics.entities.Face.FaceData;
import com.gmail.mooman219.test3D.physics.math.geometry.Float3D;
import com.gmail.mooman219.test3D.physics.math.geometry.Plane;
import com.gmail.mooman219.test3D.physics.math.geometry.Vector;

public class Entity {
	public static class MalformedDataException extends Exception{
		private static final long serialVersionUID = 1L;
	}
	
	public static class EntityData {
		public float mass = 1;
		public float bounce = 0;
		public float friction = .5f;
		public boolean staticEntity = false;
		public byte id = 0;
		public Float3D location = new Float3D(0,0,0);
		public Face[] faces;
		
		public void setData(int... vals) throws MalformedDataException{
			faces = new Face[vals[0]];
			int i = 0;
			int index = 1;
			int k;
			FaceData current = new FaceData();
			while(i < faces.length){
				if(index >= vals.length) throw new MalformedDataException();
				k = vals[index];
				if(k < 3) throw new MalformedDataException();
				index++;
				current.points = new Float3D[k];
				for(int a = 0; a < k; a++){
					if(index >= vals.length-2) throw new MalformedDataException();
					current.points[a] = new Float3D(vals[index], vals[index+1], vals[index+2]);
					index += 3;
				}
				current.plane = new Plane(current.points[0], current.points[1], current.points[2]);
				faces[i] = new Face(current);
				i++;
			}
		}
	}

	public final float mass;
	public final float bounce;
	public final float friction;
	public final Face[] faces;
	public Float3D location;
	public Vector velocity = new Vector(0, 0, 0);
	public float angle = 0;
	public float rotation = 0;
	public final boolean staticEntity;
	public byte id;
	public final static byte DEFAULT_ID = 0;
	
	private boolean ticked = false;
	
	public Entity(EntityData data){
		location = data.location;
		faces = data.faces;
		mass = data.mass;
		bounce = data.bounce;
		friction = data.friction;
		staticEntity = data.staticEntity;
		id = data.id;
	}
	
	public void tick(){
		if(!ticked){
			if(!this.staticEntity){
				location.x = location.x+velocity.x;
				location.y = location.y+velocity.y;
				location.z = location.z+velocity.z;
			}
			ticked = true;
		}
	}
	
	public void resetTick(){
		ticked = false;
	}
}
