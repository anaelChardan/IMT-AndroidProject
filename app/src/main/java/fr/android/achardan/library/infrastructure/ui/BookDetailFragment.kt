package fr.android.achardan.library.infrastructure.ui

import android.os.Bundle
import com.bumptech.glide.Glide
import fr.android.achardan.library.domain.model.Book
import fr.android.achardan.genericlist.ui.AbstractDetailedFragment
import fr.android.achardan.genericlist.ui.DetailFragment
import kotlinx.android.synthetic.main.book_view_full.*

/**
 * Detail fragment for book
 */
class BookDetailFragment : DetailFragment, AbstractDetailedFragment<Book>() {
    override fun bindObjectToView() {
        this.currentItem?.let {
            full_Detail?.text = it.title
            full_Price?.text = it.price
            full_Isbn?.text = it.isbn
            full_Synopsis?.text = it.synopsis?.joinToString(" ")
            Glide
                    .with(this)
                    .load(it.cover)
                    .into(full_Cover)
        }
    }

    companion object {
        fun create(
                book: Book,
                layoutId: Int
        ): BookDetailFragment {
            val bookDetailFragment = BookDetailFragment()

            val bundle = Bundle()

            bundle.putParcelable(DetailFragment.PARCELABLE_OBJECT_KEY, book)
            bundle.putInt(DetailFragment.DETAIL_VIEW_ID_KEY, layoutId)

            bookDetailFragment.arguments = bundle

            return bookDetailFragment
        }
    }
}