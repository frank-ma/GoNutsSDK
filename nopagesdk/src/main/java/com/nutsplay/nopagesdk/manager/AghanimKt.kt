//package com.nutsplay.nopagesdk.manager
//
//import android.content.Context
//import android.util.Log
//
///**
// * Created by frankma on 2026/7/13 17:45
// * Email: frankma9103@gmail.com
// * Desc:
// */
//class AghanimKt {
//
//    lateinit var aghanim: Aghanim
//
//    fun testPrint(): String {
//        return "kotlin环境正常"
//    }
//
//    fun init(cont: Context, apiKeyStr: String) {
//        aghanim = Aghanim(
//            context = cont, apiKey = apiKeyStr
//        )
//    }
//
//    fun getLocalPrice(items: List<String>): List<String> {
//        when (val result = aghanim.items.get(
//            skus = listOf(items),
//        )) {
//            is ApiResult.Success -> {
//                val items = result.value
//                items.forEach { item ->
//                    // Use item.name, item.price.display, item.imageUrl to populate your store
//                    Log.d("Items", "${item.name}: ${item.price.display}")
//                }
//                return items
//            }
//
//            is ApiResult.Failure -> {
//                // Log debug information for troubleshooting
//                Log.e("Items", "Failed to get items: ${result.error}")
//                // TODO: Handle error
//            }
//        }
//    }
//}