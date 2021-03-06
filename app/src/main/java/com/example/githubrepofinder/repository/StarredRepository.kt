package com.example.githubrepofinder.repository

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import com.example.githubrepofinder.network.ApiClient
import com.example.githubrepofinder.response.ReadMeResponse
import com.example.githubrepofinder.response.StarredResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StarredRepository {

    private var repoResponse: MutableLiveData<List<StarredResponse>> = MutableLiveData()
    private var readMeResponse: MutableLiveData<ReadMeResponse> = MutableLiveData()

    fun getRepo(organization: String): MutableLiveData<List<StarredResponse>> {
        ApiClient.getApiClient().getApi().repoList(organization)
            .enqueue(object : retrofit2.Callback<List<StarredResponse>> {
                override fun onResponse(
                    call: Call<List<StarredResponse>>,
                    response: Response<List<StarredResponse>>
                ) {
                    if (response.isSuccessful) {
                        repoResponse.postValue(response.body())

                    }
                }

                override fun onFailure(call: Call<List<StarredResponse>>, t: Throwable) {
                    Log.e("error", t.toString())
                }
            })

        return repoResponse
    }

    fun getReadme(organization: String,repo:String): MutableLiveData<ReadMeResponse>{
        ApiClient.getApiClient().getApi().getReadMe(organization, repo)
            .enqueue(object : retrofit2.Callback<ReadMeResponse> {
                override fun onResponse(
                    call: Call<ReadMeResponse>,
                    response: Response<ReadMeResponse>
                ) {
                    if (response.isSuccessful) {
                        readMeResponse.postValue(response.body())

                    }
                }

                override fun onFailure(call: Call<ReadMeResponse>, t: Throwable) {
                    Log.e("error", t.toString())
                }
            })

        return readMeResponse
    }
}
