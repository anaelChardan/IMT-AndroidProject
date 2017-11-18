package fr.android.achardan.book.infrastructure.controller

import fr.android.achardan.R
import fr.android.achardan.book.domain.model.Book
import fr.android.achardan.book.infrastructure.persistence.api.retrofit.RetrofitItemProcessor
import fr.android.achardan.book.infrastructure.ui.BookDetailFragment
import fr.android.achardan.supportingboundedcontext.controller.AbstractListActivity
import fr.android.achardan.supportingboundedcontext.controller.ListActivity
import fr.android.achardan.supportingboundedcontext.persistence.ItemProcessor
import fr.android.achardan.supportingboundedcontext.ui.DetailFragment
import fr.android.achardan.supportingboundedcontext.ui.ListFragment

/**
 * Controller to display the library, that is the only thing to configure for List
 */
class LibraryActivity : ListActivity<Book>, AbstractListActivity<Book>() {
    override fun contentViewLayoutId(): Int = R.layout.activity_library
    override fun containerLayoutFrameId(): Int = R.id.containerFrame

    //List
    override fun listFragmentName(): String = ListFragment::class.java.simpleName

    override fun listLayoutId(): Int = R.layout.list_view_full
    override fun recycledViewId(): Int = R.id.listView
    override fun itemViewListLayoutId(): Int = R.layout.book_view_list
    override fun itemProcessor(): ItemProcessor<Book> = RetrofitItemProcessor()

    //Detail
    override fun detailFragmentName(): String = BookDetailFragment::class.java.simpleName

    override fun detailLayoutFrameId(): Int = R.id.detailsFrame
    override fun detailLayoutId(): Int = R.layout.book_view_full
    override fun instantiateDetailFragment(): DetailFragment = BookDetailFragment.create(selectedItem!!, detailLayoutId())
}