package com.example.capstone

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*


class voiceRecog : AppCompatActivity() {
    private  val RQ_SPEECH_REC=102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_voice_recog)

        var record_btn:Button = findViewById(R.id.record_btn)
        var tv_result : TextView = findViewById(R.id.converted_tv)
        record_btn.setOnClickListener{
            askSpeechInput()
        }
    }

    private fun askSpeechInput(){
        if (!SpeechRecognizer.isRecognitionAvailable(this)){
            Toast.makeText(this,"Speech Recognition is not available",Toast.LENGTH_SHORT).show()
        }else{
            val i =Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,Locale.getDefault())
            i.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say Something to convert into text")
            startActivityForResult(i,RQ_SPEECH_REC)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var tv_result : TextView = findViewById(R.id.converted_tv)
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK){
            val result: ArrayList<String>? = data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            tv_result.text = result?.get(0).toString()
        }
    }
}