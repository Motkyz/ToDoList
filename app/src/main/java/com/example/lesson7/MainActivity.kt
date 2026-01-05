package com.example.lesson7

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
////        старый вариант
////        val thread2 = Thread({
////            println("Hi")
////        })
////        thread2.start()
////        thread2.join()
//
////        Корутины: scope, dispatcher, context
////        Виды диспетчеров: Default - реальные ядра+2, Main - 1 поток, IO- 64 потока, Unconfined - 1 + Default
//
////        context  = job - описывает работу корутины
////        scope - контролирует jobs
//        val scope = CoroutineScope(Dispatchers.Default)
////        .launch{} - никогда ничего не вернет, возврщает Uint
////        .async{} - может вернуть информацию. результат с помощью .await()
////        runBlocking{} - не требует скоупа, откроет другую корутину и заблокирует поток, в котором вызвался блок
//
//        val job: Job = scope.async {
//            delay(1000)
//            println("first")
//
//            return@async 33883
//        }
//
//        val job2: Job = scope.launch {
//            delay(1000)
//            println("first")
//        }
//
////        suspend - для функций позволяет разделить код для корутин
//
////       handler looper
    }
}