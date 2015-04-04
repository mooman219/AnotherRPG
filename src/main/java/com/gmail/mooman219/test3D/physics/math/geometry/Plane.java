package com.gmail.mooman219.test3D.physics.math.geometry;

public class Plane {
	public Axis perpindicular;
	
	public Plane(Axis perpendicular){
		this.perpindicular = perpendicular;
	}
	
	public Plane(Float3D one, Float3D two, Float3D three){
		Vector v1 = new Vector(two.x-one.x, two.y-one.y, two.z-one.z);
		Vector v2 = new Vector(three.x-one.x, three.y-one.y, three.z-one.z);
		Float3D p1 = new Float3D(v1.x*v2.x, v1.y*v2.y, v1.z*v2.z);
		perpindicular = Axis.axisFromPoints(one, p1);
	}
}
