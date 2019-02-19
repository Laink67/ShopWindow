package com.example.potap.shopwindow.dbObjects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "Sizes", foreignKeys =
@ForeignKey(entity = Sneakers.class, parentColumns = "id", childColumns = "sneakersId", onDelete = CASCADE),
        indices = {@Index("sneakersId")})
public class Sizes extends BaseObject {

    @ColumnInfo(name = "size")
    private double size;

    @ColumnInfo(name = "sneakersId")
    private int sneakersId;

    public double getSize() {
        return size;
    }

    public int getSneakersId() {
        return sneakersId;
    }

    public Sizes(double size, int sneakersId) {
        this.size = size;
        this.sneakersId = sneakersId;
    }
}
