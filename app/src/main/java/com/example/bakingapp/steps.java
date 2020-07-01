package com.example.bakingapp;

import android.os.Parcel;
import android.os.Parcelable;

public class steps implements Parcelable {
    int id;
    String shortDescription;
    String description;
   String videoURL;
   String thumbnailURL;

    protected steps(Parcel in) {
        id = in.readInt();
        shortDescription = in.readString();
        description = in.readString();
        videoURL = in.readString();
        thumbnailURL = in.readString();
    }

    public static final Creator<steps> CREATOR = new Creator<steps>() {
        @Override
        public steps createFromParcel(Parcel in) {
            return new steps(in);
        }

        @Override
        public steps[] newArray(int size) {
            return new steps[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(shortDescription);
        dest.writeString(description);
        dest.writeString(videoURL);
        dest.writeString(thumbnailURL);
    }
}
