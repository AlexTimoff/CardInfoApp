package com.example.alphatesttasktimofeev.presentation.requestsList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.alphatesttasktimofeev.databinding.CardInfoItemBinding
import com.example.alphatesttasktimofeev.domain.entity.RequestRecord

class RequestsAdapter() : RecyclerView.Adapter<RequestsViewHolder>() {

    private var data: List<RequestRecord> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CardInfoItemBinding.inflate(inflater, parent, false)
        return RequestsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RequestsViewHolder, position: Int) {
        val request = data.getOrNull(position)
        with(holder.binding) {
            id.text = (position + 1).toString()
            bin.text = request?.bin.toString()
            nameOfCountry.text = request?.country.toString()
            typeOfCard.text = request?.type.toString()
            bank.text = request?.bank.toString()
        }
    }

    override fun getItemCount(): Int = data.size

    fun setData(data: List<RequestRecord>) {
        this.data = data
        notifyDataSetChanged()
    }
}




