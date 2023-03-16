package com.example.encryptedstoragekotlin.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.encryptedstoragekotlin.R

class DecryptAdapter:RecyclerView.Adapter<DecryptHolder>(){

    private val decryptedStrings = mutableListOf<Pair<String, String>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DecryptHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.holder_decrypt, parent, false)
        return DecryptHolder(view)
    }

    override fun getItemCount(): Int {
        return decryptedStrings.size
    }

    override fun onBindViewHolder(holder: DecryptHolder, position: Int) {
        val secretString = decryptedStrings[position]
        holder.decryptedText.text = secretString.second
    }

    fun addString(entry:Pair<String, String>){
        decryptedStrings.add(entry)
        notifyItemInserted(decryptedStrings.size -1)
    }

    fun addList(list:List<Pair<String, String>>){
        decryptedStrings.addAll(list)
        notifyItemRangeInserted(decryptedStrings.size - 1, list.size)
    }

    fun removeString(key: String){
        val position:Int = decryptedStrings.indexOfFirst { it.first == key }
        if(position < 0)
            return
        decryptedStrings.removeAt(position)
        notifyItemInserted(position)
    }

}


class DecryptHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
    val decryptedText:TextView = itemView.findViewById(R.id.decrypted_text)
}