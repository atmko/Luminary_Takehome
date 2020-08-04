package cvdevelopers.takehome.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import cvdevelopers.takehome.utils.executors.AppExecutors
import cvdevelopers.takehome.api.RandomUserApiEndpoint
import cvdevelopers.takehome.models.database.clientcache.ClientCacheDao
import cvdevelopers.takehome.models.ApiResponse
import cvdevelopers.takehome.models.Client
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ClientListViewModel(private val clientCacheDao: ClientCacheDao,
                          private val appExecutors: AppExecutors,
                          private val randomUserApiEndpoint: RandomUserApiEndpoint): ViewModel() {

    private val page: String = "1"

    private val disposable = CompositeDisposable()

    val clients = MutableLiveData<List<Client>>()
    val isLoading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()

    init {
        clients.value = listOf()
        isLoading.value = false
        isError.value = false
    }

    fun getClients() {
        Log.d(ClientListViewModel::class.simpleName, "fetching cached clients")

        isLoading.value = true
        disposable.add(clientCacheDao.getAllClients()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Client>>() {
                override fun onSuccess(cachedClients: List<Client>) {
                    if (cachedClients.isNotEmpty()) {
                        Log.d(ClientListViewModel::class.simpleName, "cache not empty")
                        updateValuesWithSuccess(cachedClients)

                    } else {
                        Log.d(ClientListViewModel::class.simpleName, "cache empty")
                        getNewClients()
                    }
                }

                override fun onError(e: Throwable) {
                    updateValuesWithFailure(e)
                }
            })
        )
    }

    fun getNewClients() {
        Log.d(ClientListViewModel::class.simpleName, "fetching new clients")

        isLoading.value = true
        disposable.add(randomUserApiEndpoint.getClient(page)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<ApiResponse>() {
                override fun onSuccess(apiResponse: ApiResponse) {
                    Log.d(ClientListViewModel::class.simpleName, "new clients fetched")
                    overwriteClientCacheAndUpdateLiveData(apiResponse.results)
                }

                override fun onError(e: Throwable) {
                    updateValuesWithFailure(e)
                }
            })
        )
    }

    private fun overwriteClientCacheAndUpdateLiveData(newClients: List<Client>) {
        appExecutors.diskIO.execute {
            deleteExistingClients()
            saveNewClients(newClients)

            appExecutors.mainThread.execute {
                updateValuesWithSuccess(newClients)
            }
        }
    }

    private fun deleteExistingClients() {
        Log.d(ClientListViewModel::class.simpleName, "clearing client cache")
        clientCacheDao.deleteCache()

        Log.d(ClientListViewModel::class.simpleName, "cache cleared")
    }

    private fun saveNewClients(newClients: List<Client>) {
        Log.d(ClientListViewModel::class.simpleName, "saving new clients")

        for (client: Client in newClients) {
            clientCacheDao.insertClient(client)
        }

        Log.d(ClientListViewModel::class.simpleName, "save completed")
    }

    private fun updateValuesWithSuccess(clientsUpdate: List<Client>) {
        Log.d(ClientListViewModel::class.simpleName, "updating success values")
        clients.value = clientsUpdate
        isLoading.value = false
        isError.value = false
    }

    private fun updateValuesWithFailure(e: Throwable) {
        e.printStackTrace()
        Log.d(ClientListViewModel::class.simpleName, "updating failure values")
        isLoading.value = false
        isError.value = true
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}