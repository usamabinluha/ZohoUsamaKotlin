package `in`.usamabinluha.www.zohousamakotlin.ui

import `in`.usamabinluha.www.zohousamakotlin.Injection
import `in`.usamabinluha.www.zohousamakotlin.R
import `in`.usamabinluha.www.zohousamakotlin.R.id.list
import `in`.usamabinluha.www.zohousamakotlin.model.User
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    private val adapter = UsersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(this))
                .get(UserViewModel::class.java)

        initAdapter()
        initSearch()
    }

    private fun initAdapter() {
        list.adapter = adapter
        viewModel.users.observe(this, Observer<PagedList<User>> {
            Log.d("Activity", "list: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })
        viewModel.networkErrors.observe(this, Observer<String> {
            Toast.makeText(this, "${it}", Toast.LENGTH_LONG).show()
        })
    }

    private fun initSearch() {
        list.scrollToPosition(0)
        viewModel.searchUser()
        adapter.submitList(null)
    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            list.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            list.visibility = View.VISIBLE
        }
    }

}
