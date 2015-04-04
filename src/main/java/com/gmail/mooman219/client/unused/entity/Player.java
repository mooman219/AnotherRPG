package com.gmail.mooman219.client.unused.entity;

import com.gmail.mooman219.client.enums.Direction;
import com.gmail.mooman219.client.enums.EntityType;
import com.gmail.mooman219.client.geo.World;
import com.gmail.mooman219.shared.geo.cord.IntegerCord;


public class Player extends Entity{

    public Player(IntegerCord blockPos){
        super(EntityType.CHAR, blockPos);
    }

    public void move(){
        if(this.getFaceing() == Direction.UP){
            if(this.getBlockPos().y > 0){
                this.setBlockPos(this.getBlockPos().addY(-1));
            }
        }
        if(this.getFaceing() == Direction.DOWN){
            if(this.getBlockPos().y < (World.CHUNK_SIZE * World.WORLD_SIZE) - 1){
                this.setBlockPos(this.getBlockPos().addY(1));
            }
        }
        if(this.getFaceing() == Direction.LEFT){
            if(this.getBlockPos().x > 0){
                this.setBlockPos(this.getBlockPos().addX(-1));
            }
        }
        if(this.getFaceing() == Direction.RIGHT){
            if(this.getBlockPos().x < (World.CHUNK_SIZE * World.WORLD_SIZE) - 1){
                this.setBlockPos(this.getBlockPos().addX(1));
            }
        }
    }
}
