package com.sample.mvvmlivedataroomdattbinding.adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.repository.DownloadImage
import com.sample.mvvmlivedataroomdattbinding.R
import com.sample.mvvmlivedataroomdattbinding.databinding.LayoutItemListRowBinding
import com.sample.mvvmlivedataroomdattbinding.models.UserModel
import kotlinx.coroutines.*
import java.net.URL


class UsersListItemViewHolder(val rowBinding: LayoutItemListRowBinding) :
    RecyclerView.ViewHolder(rowBinding.root)

class UsersListAdapter :
    ListAdapter<UserModel, UsersListItemViewHolder>(UserListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListItemViewHolder {
        val binding = DataBindingUtil.inflate<LayoutItemListRowBinding>(
            LayoutInflater.from(parent.context),
            R.layout.layout_item_list_row,
            parent,
            false
        )

        return UsersListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersListItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.rowBinding.user = item
    }

}

class UserListDiffCallback : DiffUtil.ItemCallback<UserModel>() {
    override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel): Boolean {
        return oldItem == newItem
    }
}

/*custom binding adapter for imageurl*/
@BindingAdapter("imageUrl")
fun setImageUrl(imgeView: ImageView, url: String) {
     //DownloadImageTask(imgeView).execute(url)
    CoroutineScope(Dispatchers.IO).launch {
        val bitmap = async { DownloadImage(imgeView.context).loadImage(url) }
        withContext(Dispatchers.Main) {
            imgeView.setImageDrawable(null)
            val result = bitmap.await()
            if (result!=null){
                imgeView.setImageBitmap(result)
            }else{
                imgeView.setImageResource(R.drawable.ic_user_placeholder)
            }
        }
    }
}

