package com.example.contactapp.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactapp.databinding.ActivitySavedContactBinding

class ContactsAdapter(var contactsList: MutableList<Contacts>) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    class ViewHolder( val savedContactBinding: ActivitySavedContactBinding): RecyclerView.ViewHolder(savedContactBinding.root){
        fun bind(contacts: Contacts){
            savedContactBinding.contactName.text = contacts.name
            savedContactBinding.contactNumber.text = contacts.phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ActivitySavedContactBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return contactsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contacts  = contactsList[position]
        holder.bind(contacts)
        holder.savedContactBinding.root.setOnClickListener {
            onItemClick?.onClick(contacts)
        }
    }

    interface OnItemClick{
        fun onClick(contacts: Contacts){

        }
    }
    var onItemClick: OnItemClick? = null

}
