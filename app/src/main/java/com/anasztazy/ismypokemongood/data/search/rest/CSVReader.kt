package com.anasztazy.ismypokemongood.data.search.rest


import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.streams.toList

class CSVReader(
    private val context: Context
) {
    fun read(fileName: String): List<String> {

        val inputStream = context.assets.open(fileName)
        val inputStreamReader = InputStreamReader(inputStream)
        val bufferReader = BufferedReader(inputStreamReader)
        bufferReader.readLine()
        val listOfLines = bufferReader.lines()
        return listOfLines.toList()

    }

}