package com.example.myapplication.data

import android.os.Parcel
import android.os.Parcelable


data class Movie (
    @SerializedName("id") var id: Long,
    @SerializedName("title")  var title: String,
    @SerializedName("overview")  var overview: String,
    @SerializedName("release_date")   var releaseDate: String,
    @SerializedName("homepage")   var homepage: String?,
    @SerializedName("poster_path") var posterPath: String?,
    @SerializedName("backdrop_path")  var backdropPath: String?
): Parcelable{
    annotation class SerializedName(val value: String)

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
        parcel.writeString(homepage)
        parcel.writeString(posterPath)
        parcel.writeString(backdropPath)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}

