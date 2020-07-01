package com.example.bakingapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class items  implements Parcelable {
    int id;
    String name;
    @SerializedName("ingredients")
    public List<ingredientss> ingredients;
    public  List<steps> steps;
    int servings;

    protected items(Parcel in) {
        id = in.readInt();
        name = in.readString();
        servings = in.readInt();
        if (in.readByte() == 0x01) {
            steps = new ArrayList<>();
            in.readList(steps, steps.class.getClassLoader());
        } else {
            steps = null;
        }
        if (in.readByte() == 0x01) {
            ingredients = new ArrayList<>();
            in.readList(ingredients, ingredientss.class.getClassLoader());
        } else {
            ingredients = null;
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(servings);
        if (steps == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(steps);
        }

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<items> CREATOR = new Creator<items>() {
        @Override
        public items createFromParcel(Parcel in) {
            return new items(in);
        }

        @Override
        public items[] newArray(int size) {
            return new items[size];
        }
    };

    public List<steps> getSteps() {
        return steps;
    }
}
