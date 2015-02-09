package com.pitch.view;

import android.os.Parcel;
import android.os.Parcelable;

public class MyParcelable implements Parcelable {
	private int mData;
	
    public MyParcelable() {
    }
    
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeInt(mData);
	}
	
    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<MyParcelable> CREATOR = new Parcelable.Creator<MyParcelable>() {
        public MyParcelable createFromParcel(Parcel in) {
            return new MyParcelable(in);
        }

        public MyParcelable[] newArray(int size) {
            return new MyParcelable[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    protected MyParcelable(Parcel in) {
        mData = in.readInt();
    }

}
