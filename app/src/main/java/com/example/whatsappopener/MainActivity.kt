package com.example.whatsappopener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)

        var number : String = "0"
        button.setOnClickListener{
            number =editText.text.toString();
            startWhatsapp(number)
        }
    }
    private fun startWhatsapp(number: String){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setPackage("com.whatsapp")
        val data: String = if (number[0] == '+'){
            number.substring(1)
        }
        else if (number.length==10){
            "91"+number
        }
        else{
            number
        }
        intent.data = Uri.parse("https://wa.me/$data")
        if (packageManager.resolveActivity(intent,0)!= null){
            startActivity(intent)
        }else{
            Toast.makeText(this,"Please install whatsapp",Toast.LENGTH_SHORT).show()
        }
    }
}