package com.inmolby.flickrclient.data.callback;

/**
 * Created by yasser on 15/10/17.
 * <p>
 * This interface is the callbacks called by the data layer to inform presentation layer about any new changes
 */

public interface PresenterCallback<T> {
    void success(T data);

    void error(Throwable t);
}
