package com.example.contactapp.model

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.example.contactapp.databinding.ActivityDetailsContactBinding

class ContactsDetails : AppCompatActivity() {
    lateinit var binding: ActivityDetailsContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActivityView()
        navigateBack()
    }

    private fun navigateBack() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setActivityView() {
        val contact = IntentCompat.getParcelableExtra(intent, Constants.CONTACT, Contacts::class.java)
        contact?.let { contacts ->
            binding.nameDV.text = contacts.name
            binding.nameDV2.text = contacts.name
            binding.phoneDV.text = contacts.phone
            binding.describtionDv.text = contacts.description

        }
    }
}