package com.example.myfirstapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myfirstapplication.MyNetRqstTool
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

public
class UserViewModel : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val myNetRqstTool = MyNetRqstTool()

    fun getUser(userId: Int) {
        val url = "https://jsonplaceholder.typicode.com/users/$userId"
        val disposable = myNetRqstTool.fetchData<User>(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { user ->
                    _user.value = user
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
