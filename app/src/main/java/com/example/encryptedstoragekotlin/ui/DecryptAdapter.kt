package com.example.encryptedstoragekotlin.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.encryptedstoragekotlin.BR
import com.example.encryptedstoragekotlin.R
import com.example.encryptedstoragekotlin.databinding.HolderDecryptBinding

class DecryptAdapter:RecyclerView.Adapter<DecryptHolder>(){

    private val decryptedStrings = mutableListOf<Pair<String, String>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DecryptHolder {
        val view:HolderDecryptBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.holder_decrypt, parent, false)
        return DecryptHolder(view)
    }

    override fun getItemCount(): Int {
        return decryptedStrings.size
    }

    override fun onBindViewHolder(holder: DecryptHolder, position: Int) {
        val secretString = decryptedStrings[position]
        holder.bind(secretString)
    }

    fun addString(entry:Pair<String, String>){
        decryptedStrings.add(entry)
        notifyItemInserted(decryptedStrings.size -1)
    }

    fun addList(list:List<Pair<String, String>>){
        decryptedStrings.addAll(list)
        notifyItemRangeInserted(decryptedStrings.size - 1, list.size)
    }

    fun removeString(position:Int ){
        if(position < 0)
            return
        decryptedStrings.removeAt(position)
        notifyItemRemoved(position)
    }

}


class DecryptHolder(private val binding: HolderDecryptBinding) :RecyclerView.ViewHolder(binding.root){
    fun bind(decrypted:Pair<String, String>){
        binding.setVariable(BR.plainText, decrypted)
        binding.executePendingBindings()
    }
}