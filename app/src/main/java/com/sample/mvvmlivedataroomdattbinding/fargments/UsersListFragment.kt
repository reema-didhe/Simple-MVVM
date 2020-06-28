package com.example.mvvm.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.android.notebook.fragment.notelist.UsersListViewModelFactory
import com.sample.mvvmlivedataroomdattbinding.R
import com.sample.mvvmlivedataroomdattbinding.adapter.UsersListAdapter
import com.sample.mvvmlivedataroomdattbinding.databinding.ListUsersFragmentBinding
import com.sample.mvvmlivedataroomdattbinding.models.UserModel

class UsersListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<ListUsersFragmentBinding>(
            inflater,
            R.layout.list_users_fragment,
            container,
            false
        )

        try {
            binding.setLifecycleOwner(this)

            val application = requireNotNull(this.activity).application
            val viewModelFactory = UsersListViewModelFactory(application)
            val usersListViewModel =
                ViewModelProviders.of(
                    this, viewModelFactory
                ).get(UsersListViewModel::class.java)

            binding.usersViewModel = usersListViewModel
            binding.usersListAdapter = UsersListAdapter()
            //fetching users
            usersListViewModel.fetchUsers()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return binding.root
    }

}

/*//custom binding adapter for recyclerview*/
@BindingAdapter("submitList")
fun setSubmitList(recyclerView: RecyclerView, listUsers: List<UserModel>) {
    if (recyclerView!=null && listUsers!=null) {
        (recyclerView?.adapter as UsersListAdapter).submitList(listUsers)
    }
}
