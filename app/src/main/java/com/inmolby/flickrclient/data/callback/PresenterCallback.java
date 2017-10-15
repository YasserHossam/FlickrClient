package com.inmolby.flickrclient.data.callback;

/**
 * Created by yasser on 15/10/17.
 */

public interface PresenterCallback<T>
{
    void success(T data);
    void error(Throwable t);
}
