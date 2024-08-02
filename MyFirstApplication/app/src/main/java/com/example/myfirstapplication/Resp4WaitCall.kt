package com.example.myfirstapplication


import com.google.gson.annotations.SerializedName

/*** todo
 * http://bis.dsat.gov.mo:8015/ddbus/app/call/keep?device=ios&HUID=85e89609-382f-46ed-be5c-11204df9b15e
 *  post
 *  {
 * 	"keepIds": ["7423375d889845d4a9d46c2ef2108ae3", "03e823eb14344fa5949dfc7bae942853"]
 * }
 */
data class Resp4WaitCall(
    @SerializedName("data")
    val myData: List<Data?>?,
    @SerializedName("header")
    val header: Header?
)
