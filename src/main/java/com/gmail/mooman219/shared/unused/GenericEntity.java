package com.gmail.mooman219.shared.unused;

public class GenericEntity {
    public int uniqueID;
    public int x;
    public int y;
    public short type;
    public short direction;

    public GenericEntity(int uniqueID, int x, int y, short type, short direction){
        this.uniqueID = uniqueID;
        this.x = x;
        this.y = y;
        this.type = type;
        this.direction = direction;
    }

    public GenericEntity(int uniqueID, int x, int y, int type, int direction){
        this(uniqueID, x, y, (short)type, (short)direction);
    }
}
