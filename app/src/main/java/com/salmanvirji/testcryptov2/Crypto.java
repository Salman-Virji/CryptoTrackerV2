package com.salmanvirji.testcryptov2;

import android.os.Parcel;
import android.os.Parcelable;

public class Crypto implements Parcelable {

    //Reference for passing custom objects through intent  https://codegrepr.com/question/passing-arraylist-through-intent/
    public String name ;
    public String symbol;
    public double price;

    protected Crypto(Parcel source) {
        name = source.readString();
        symbol = source.readString();
        price = source.readDouble();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Crypto(String name, String symbol, double price) {
        this.name = name;
        this.symbol = symbol;
        this.price = price;
    }
    public int describeContents() {
        return this.hashCode();
    }

    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(symbol);
        dest.writeDouble(price);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final Creator<Crypto> CREATOR = new Creator<Crypto>() {
        @Override
        public Crypto createFromParcel(Parcel in) {
            return new Crypto(in);
        }

        @Override
        public Crypto[] newArray(int size) {
            return new Crypto[size];
        }
    };
}