package xyz.webflutter.myandroidjetpackpro.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import xyz.webflutter.myandroidjetpackpro.data.remote.response.ApiResponse;
import xyz.webflutter.myandroidjetpackpro.resource.Resource;
import xyz.webflutter.myandroidjetpackpro.utils.MyAppExecutor;

abstract class NetworkResource<ResultType, RequestType> {
    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    private final MyAppExecutor myAppExecutor;

    private void onFetchFailed(){

    }

    protected abstract LiveData<ResultType> loadFromDB();

    protected abstract Boolean shouldFetch(ResultType data);

    protected abstract LiveData<ApiResponse<RequestType>> createCall();

    protected abstract void saveCallResult(RequestType data);

    public NetworkResource(MyAppExecutor appExecutor){
        myAppExecutor = appExecutor;

        result.setValue(Resource.loading(null));

        LiveData<ResultType> dbSource= loadFromDB();

        result.addSource(dbSource, data->{
            result.removeSource(dbSource);
            if (shouldFetch(data)){
                fetchFromNetwork(dbSource);
            }else {
                result.addSource(dbSource,newData->result.setValue(Resource.success(newData)));
            }
        });
    }

    private void fetchFromNetwork(LiveData<ResultType> dbSource){
        LiveData<ApiResponse<RequestType>> apiResponse = createCall();

        result.addSource(dbSource,newData->result.setValue(Resource.loading(newData)));

        result.addSource(apiResponse,response->{
            result.removeSource(apiResponse);
            result.removeSource(dbSource);

            switch (response.statusResponse){
                case SUCCES:
                    myAppExecutor.disk().execute(()->{
                        saveCallResult(response.body);
                        myAppExecutor.mainThread().execute(()->result.addSource(loadFromDB(),newData->result.setValue(Resource.success(newData))));
                    });
                    break;

                case EMPTY:
                    myAppExecutor.mainThread().execute(()->result.addSource(loadFromDB(),newData->result.setValue(Resource.success(newData))));
                    break;

                case FAILED:
                    onFetchFailed();
                    result.addSource(dbSource,newData->result.setValue(Resource.failed(response.message,newData)));
                    break;
            }
        });
    }

    public LiveData<Resource<ResultType>> asLiveData(){
        return result;
    }
}