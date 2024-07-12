package com.example.quizzify

import android.app.FragmentManager
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.shashank.sony.fancytoastlib.FancyToast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val beginButton = findViewById<Button>(R.id.beginQuizButton)
        beginButton.setOnClickListener {
            val trans = supportFragmentManager.beginTransaction()
            trans.replace(R.id.frame, QuestionsFragment(0, 0))
            trans.addToBackStack(null)
            trans.commit()
        }

        val rulesButton = findViewById<Button>(R.id.rulesButton)
        rulesButton.setOnClickListener{
            val trans = supportFragmentManager.beginTransaction()
            trans.replace(R.id.frame2, RulesFragment())
            trans.addToBackStack(null)
            trans.commit()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val fragmentManager = supportFragmentManager
        val isEmpty = (fragmentManager.backStackEntryCount == 0)
        if(isEmpty){
            finishAffinity()
            return true
        }
        else {
            FancyToast.makeText(this, "Quiz aborted", FancyToast.LENGTH_LONG, FancyToast.INFO, false).show()
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
            return true
        }
    }
}