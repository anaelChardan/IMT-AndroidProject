package fr.android.achardan.library.infrastructure.persistence.api.retrofit

import fr.android.achardan.library.domain.model.Book
import retrofit2.Call
import retrofit2.http.GET

/**
 * Service Necessary for Retrofit
 */
interface HenriPotierService {
    @GET("books")
    fun getBooks(): Call<List<Book>>
}