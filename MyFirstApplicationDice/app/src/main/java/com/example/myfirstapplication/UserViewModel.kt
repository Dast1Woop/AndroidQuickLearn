package com.example.myfirstapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

public
class UserViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

//    private val _resp4WaitCall = MutableLiveData<Resp4WaitCall>()
//    val resp4WaitCall: LiveData<Resp4WaitCall> get() = _resp4WaitCall

    //参数可变时，不能使用createDefault，用create
    private val _resp4WaitCall = BehaviorSubject.create<Resp4WaitCall>()

    //离谱，使用Resp4WaitCall?就报错
    val resp4WaitCall: Observable<Resp4WaitCall> = _resp4WaitCall.hide()


    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val myNetRequestTool = MyNetRequestTool()

    fun getUser(userId: Int) {
        val url = "https://jsonplaceholder.typicode.com/users/$userId"
        val disposable = myNetRequestTool.fetchData<User>(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { user ->
                    _user.value = user
//                    _user.value = null//编译能通过！！！虽然会有红色下划线提示！！！
                },
                { throwable ->
                    _error.value = throwable.message
                }
            )
        compositeDisposable.add(disposable)
    }

    fun getWaitCall(requestBodyString:String) {

        val url = "http://bis.dsat.gov.mo:8015/ddbus/app/call/keep?device=ios&HUID=85e89609-382f-46ed-be5c-11204df9b15e"

        val body = requestBodyString.toRequestBody("application/json; charset=utf-8".toMediaType())
        val disposable = myNetRequestTool.fetchData<Resp4WaitCall>(url,MyNetRequestTool.RequestType.POST,body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { resp ->
                    _resp4WaitCall.onNext(resp)
//                    _resp4WaitCall.onNext(null)
                },
                { throwable ->
                    _error.value = throwable.message
                }
            )
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
