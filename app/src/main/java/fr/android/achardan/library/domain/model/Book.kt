package fr.android.achardan.library.domain.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Book model parcelable, just a data holder
 */
data class Book(var isbn: String? = null, var title: String? = null, var price: String? = null, var cover: String? = null, var synopsis: Array<String>?) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(), parcel.createStringArray())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(isbn)
        parcel.writeString(title)
        parcel.writeString(price)
        parcel.writeString(cover)
        parcel.writeStringArray(synopsis)
    }

    override fun describeContents(): Int = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Book

        if (isbn != other.isbn) return false
        if (title != other.title) return false
        if (price != other.price) return false
        if (cover != other.cover) return false
        if (!Arrays.equals(synopsis, other.synopsis)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isbn?.hashCode() ?: 0
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (price?.hashCode() ?: 0)
        result = 31 * result + (cover?.hashCode() ?: 0)
        result = 31 * result + (synopsis?.let { Arrays.hashCode(it) } ?: 0)
        return result
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book = Book(parcel)
        override fun newArray(size: Int): Array<Book?> = arrayOfNulls(size)
    }
}
