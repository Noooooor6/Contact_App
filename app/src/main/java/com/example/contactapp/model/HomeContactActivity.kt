package com.example.contactapp.model

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contactapp.databinding.ActivityHomeContactBinding

class HomeContactActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeContactBinding
    lateinit var name: String
    lateinit var phone: String
    lateinit var description: String
    val contacts = mutableListOf<Contacts>()
    var adapter = ContactsAdapter(contacts)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onSaveBtnClick()
        initRV()

    }

    private fun initRV() {
        binding.ContactRV.adapter = adapter
        adapter.onItemClick = object : ContactsAdapter.OnItemClick{
            override fun onClick(contacts: Contacts) {
                navigateToContactsDetails(contacts)
            }
        }
    }

    private fun navigateToContactsDetails(contacts: Contacts) {
        val intent = Intent(this, ContactsDetails::class.java)
        intent.putExtra(Constants.CONTACT,contacts)
        startActivity(intent)
    }

    private fun onSaveBtnClick() {
        binding.SaveBtn.setOnClickListener {
            if (!ValidateTextField()){
                return@setOnClickListener
            }
            name = binding.NameEdtTxt.text?.trim().toString()
            phone = binding.PhoneEdtTxt.text?.trim().toString()
            description = binding.DiscriptionEdtTxt.text?.trim().toString()

            val contact = Contacts(name,phone, description)
            contacts.add(contact)
            adapter.notifyItemInserted(contacts.size-1)
            ClearTextField()

        }
    }

    private fun ClearTextField() {
        binding.NameEdtTxt.text?.clear()
        binding.PhoneEdtTxt.text?.clear()
        binding.DiscriptionEdtTxt.text?.clear()
    }

    private fun ValidateTextField(): Boolean {
        name = binding.NameEdtTxt.text?.trim().toString()
        phone = binding.PhoneEdtTxt.text?.trim().toString()
        binding.NameContainer.error = validateName(name)
        binding.PhoneNumContainer.error = validatePhone(phone)

        return validateName(name)==null&& validatePhone(phone)==null

    }
    fun validateName(name : String): String?{
        if (name.isEmpty()){
            return "Required"
        }
        if (name.length<3){
            return "Name can't be less than 3"
        }
        val NamePattern = "^[a-zA-Z]+$"
        if (!name.matches(NamePattern.toRegex())){
            return "Name can only contain letters"
        }
        return null
    }

    fun validatePhone(phone : String): String?{
        if (phone.isEmpty()){
            return "Required"
        }
        if (phone.length<11){
            return "Phone can't be less than 11"
        }
        val NamePattern = "^[0-9]+$"
        if (!phone.matches(NamePattern.toRegex())){
            return "Phone can only contain digits"
        }
        return null
    }

}