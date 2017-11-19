package fr.android.achardan.library.infrastructure.ui

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import fr.android.achardan.library.domain.model.Book
import fr.android.achardan.genericlist.ui.ItemView
import kotlinx.android.synthetic.main.book_view_list.view.*

/**
 * Book item view in list
 */
class BookItemView : LinearLayout, ItemView<Book> {
    var currentBook: Book? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun bind(book: Book): View {
        currentBook = book

        list_Title.text = book.title

        book.cover?.let {
            Glide.with(context).load(it).into(list_Cover)
        }

        return this
    }
}
