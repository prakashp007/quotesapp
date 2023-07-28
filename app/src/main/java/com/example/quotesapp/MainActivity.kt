package com.example.quotesapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.quotesapp.databinding.ActivityMainBinding
import com.example.quotesapp.model.Quotes
import com.example.quotesapp.viewmodel.MainViewModel
import com.example.quotesapp.viewmodelfactory.MainViewModelFactory

class MainActivity : AppCompatActivity(){
    private lateinit var mainViewModel: MainViewModel
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mainViewModel = ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        setQuotes(mainViewModel.getQuotes())

        binding.btnNext.setOnClickListener {
            onNext()
        }
        binding.btnPrevios.setOnClickListener {
            onPrevious()
        }
        binding.fabShare.setOnClickListener {
            onShare()
        }

    }

    private fun setQuotes(quotes : Quotes){
        binding.quotes = quotes
    }
    private fun onPrevious(){
        setQuotes(mainViewModel.previousQuotes())
    }
    private fun onNext(){
        setQuotes(mainViewModel.nextQuotes())
    }

    private fun onShare(){
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuotes().text)
        startActivity(intent)
    }

//    override fun onClick(v: View) {
//        when(v.id){
//            binding.btnNext.id -> {
//                onNext()
//            }
//            binding.btnPrevios.id -> {
//                onPrevious()
//            }
//            binding.fabShare.id -> {
//                onShare()
//            }
//        }
//    }
}