package com.example.quotesapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.quotesapp.model.Quotes
import com.google.gson.Gson

class MainViewModel(private val context: Context) : ViewModel() {
    private var quotesList : Array<Quotes> = emptyArray()
    private var index = 0
    init {
        quotesList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets() : Array<Quotes> {
        //file open karavi pade read karava mate
        val inputStream = context.assets.open("Quotes.json")
        //size
        val size : Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)//input stream mathi read karo buffer ma size chene etlu
        val json = String(buffer,Charsets.UTF_8)
        return  Gson().fromJson(json,Array<Quotes>::class.java)
    }

    fun getQuotes() = quotesList[index]

    fun nextQuotes() = if(index == quotesList.size-1) quotesList[quotesList.size-1] else quotesList[++index]

    fun previousQuotes() = if(index==0) quotesList[0] else quotesList[--index]

}
