package com.example.root.demokotlin.presentation.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.root.demokotlin.R
import com.example.root.demokotlin.data.model.Friend
import com.example.root.demokotlin.presentation.util.ImageManager.ImageManager
import javax.inject.Inject

/**
 * Created by root on 04/08/2017.
 */

class FriendsAdapter @Inject constructor(private val context: Context,
                                         private val IMAGE_MANAGER: ImageManager) :
        RecyclerView.Adapter<FriendsAdapter.FriendViewHolder>() {

    interface OnItemClickListener {
        fun onUserItemClicked(userModel: Friend)
    }

    private var usersCollection: MutableList<Friend>? = mutableListOf<Friend>()
    private val layoutInflater: LayoutInflater

    private var onItemClickListener: OnItemClickListener? = null

    init {
        this.layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getItemCount(): Int {
        return if (this.usersCollection != null) {
            this.usersCollection!!.size
        } else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendViewHolder {
        val view = this.layoutInflater.inflate(R.layout.row_user, parent, false)
        return FriendViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendViewHolder, position: Int) {
        val friendResponse = this.usersCollection!![position]
        holder.textViewTitle.setText(friendResponse.name)

        IMAGE_MANAGER.load(context, friendResponse.linkAvatar, holder.imgAvatar)

        holder.itemView.setOnClickListener {
            if (this@FriendsAdapter.onItemClickListener != null) {
                this@FriendsAdapter.onItemClickListener!!.onUserItemClicked(friendResponse)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun setUsersCollection(usersCollection: Collection<Friend>) {
        this.validateUsersCollection(usersCollection)

        if (this.itemCount == 0) {
            this.usersCollection = usersCollection.toMutableList()
        } else {
            this.usersCollection!!.addAll(usersCollection)
        }

        this.notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    private fun validateUsersCollection(usersCollection: Collection<Friend>?) {
        if (usersCollection == null) {
            throw IllegalArgumentException("The list friend is null")
        }
    }

    class FriendViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //@BindView(R.id.title)  lateinit var textViewTitle: TextView
        //@BindView(R.id.avatar) lateinit var imgAvatar: ImageView
        //val textViewTitle : TextView?  =
        val textViewTitle : TextView = itemView.findViewById(R.id.title)
        val imgAvatar : ImageView = itemView.findViewById(R.id.avatar)

        init {
            //ButterKnife.bind( this, itemView)
        }
    }
}