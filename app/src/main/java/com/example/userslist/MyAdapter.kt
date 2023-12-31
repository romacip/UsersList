package com.example.userslist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MyAdapter(var con : Context , var list : List<UsersItem>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    inner class ViewHolder(v : View) : RecyclerView.ViewHolder(v)
    {
        var img = v.findViewById<ImageView>(R.id.image)
        var tvName = v.findViewById<TextView>(R.id.username)
        var email = v.findViewById<TextView>(R.id.email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(con).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(con).load(list[position].image).into(holder.img)

        holder.tvName.text = list[position].username
        holder.email.text = list[position].email

    }

    override fun getItemCount(): Int {
        return list.count()
    }

}