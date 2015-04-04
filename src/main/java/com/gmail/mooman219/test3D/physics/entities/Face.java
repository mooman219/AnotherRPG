package com.gmail.mooman219.test3D.physics.entities;

import com.gmail.mooman219.test3D.physics.math.geometry.Float3D;
import com.gmail.mooman219.test3D.physics.math.geometry.Plane;

public class Face {
	
	public static class FaceData{
		public Float3D[] points;
		public Plane plane;
		
		public FaceData(){}
		
		public FaceData(Plane p, Float3D... floats){
			plane = p;
			points = floats;
		}
	}
	
	public final Float3D[] points;
	public final Plane plane;
	
	public Face(FaceData d){
		points = d.points;
		plane = d.plane;
	}
}
