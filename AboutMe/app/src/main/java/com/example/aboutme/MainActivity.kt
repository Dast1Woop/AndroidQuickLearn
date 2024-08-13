package com.example.aboutme

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.getSystemService
import com.example.aboutme.databinding.ActivityMainBinding
import com.example.aboutme.ui.theme.AboutMeTheme

class MainActivity : ComponentActivity() {

    lateinit var viewbinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ViewBinding 将为每个布局文件生成一个绑定类。例如，对于 activity_main.xml，将生成 ActivityMainBinding 类。
        viewbinding = ActivityMainBinding.inflate(layoutInflater)

       setContentView(viewbinding.root)

        viewbinding.nameText.text = getString(R.string.hello)

        viewbinding.done.setOnClickListener(::doneBtnDC)
    }

    private fun doneBtnDC(v:View) {
        hideKeyboard(this)

        viewbinding.nickNameText.text = viewbinding.editNameText.text
        viewbinding.nickNameText.visibility = View.VISIBLE
        viewbinding.editNameText.visibility = View.GONE
        viewbinding.done.visibility = View.GONE
    }

    fun hideKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusedView = activity.currentFocus
        if (currentFocusedView != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocusedView.windowToken, 0)
        }else{
            Toast.makeText(this,"focusview is null",Toast.LENGTH_SHORT).show()
        }
    }
}

