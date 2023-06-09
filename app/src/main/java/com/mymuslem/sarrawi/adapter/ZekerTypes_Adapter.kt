package com.mymuslem.sarrawi.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mymuslem.sarrawi.R
import com.mymuslem.sarrawi.databinding.ZekerTypesDeBinding
import com.mymuslem.sarrawi.models.Letters
import com.mymuslem.sarrawi.models.ZekerTypes

class ZekerTypes_Adapter(val con: Context):RecyclerView.Adapter<ZekerTypes_Adapter.MyViewHolder>() {

    var onItemClick: ((item:Letters,position:Int) -> Unit)? = null
//    var onItemClick: ((Int,String) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    inner class MyViewHolder(val binding: ZekerTypesDeBinding): RecyclerView.ViewHolder(binding.root) {


        init {

        }

        fun bind(position: Int) {

            val current_zekerTypes_list = zekerTypes_list[position]
            binding.apply {
                titleDoaa.text=current_zekerTypes_list.Name

                if (current_zekerTypes_list!!.Fav==0) {
                    imgFav.setImageResource(R.mipmap.nf)
                } else {
                    imgFav.setImageResource(R.mipmap.f)
                }
            }

            binding.imgFav.setOnClickListener{
                onItemClick?.invoke(zekerTypes_list[position], position)
            }
        }

    }

    private val diffCallback = object : DiffUtil.ItemCallback<Letters>(){
        override fun areItemsTheSame(oldItem: Letters, newItem: Letters): Boolean {
            return oldItem.ID == newItem.ID
        }

        override fun areContentsTheSame(oldItem: Letters, newItem: Letters): Boolean {
            return newItem == oldItem
        }

    }

    private val differ = AsyncListDiffer(this, diffCallback)
    var zekerTypes_list: List<Letters>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ZekerTypesDeBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind(position)
//        holder.binding.apply {
//            titleDoaa.text=current_zekerTypes_list.Name
////            tvMsgM.text=current_zekerTypes_list.MessageName
//        }
    }

    override fun getItemCount(): Int {
        return zekerTypes_list.size
    }
}