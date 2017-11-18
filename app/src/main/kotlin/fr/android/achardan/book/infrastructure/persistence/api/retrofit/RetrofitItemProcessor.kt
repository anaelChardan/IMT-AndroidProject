package fr.android.achardan.book.infrastructure.persistence.api.retrofit

import android.os.Parcel
import android.os.Parcelable
import fr.android.achardan.book.domain.model.Book
import fr.android.achardan.book.domain.model.BookProcessor
import fr.android.achardan.supportingboundedcontext.persistence.ItemProcessor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Adapter of ItemProcessor for Retrofit
 */
class RetrofitItemProcessor() : BookProcessor, ItemProcessor<Book>, Parcelable {
    private var service: HenriPotierService? = Retrofit
            .Builder()
            .baseUrl("http://henri-potier.xebia.fr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HenriPotierService::class.java)

    override fun process(processFunction: (List<Book>) -> Unit, onError: (Throwable) -> Unit) {
        service?.getBooks()?.enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                response.body()?.let {
                    processFunction(it)
                }
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                onError(t)
            }
        })
    }

    //Disgusting thing to be able to inject it into fragment
    constructor(parcel: Parcel) : this()

    override fun writeToParcel(dest: Parcel?, flags: Int) {}

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RetrofitItemProcessor> {
        override fun createFromParcel(parcel: Parcel): RetrofitItemProcessor {
            return RetrofitItemProcessor(parcel)
        }

        override fun newArray(size: Int): Array<RetrofitItemProcessor?> {
            return arrayOfNulls(size)
        }
    }
}