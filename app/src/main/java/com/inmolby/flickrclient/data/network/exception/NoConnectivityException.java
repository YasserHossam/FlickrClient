package com.inmolby.flickrclient.data.network.exception;

import java.io.IOException;

/**
 * Created by yasser on 15/10/17.
 */

public class NoConnectivityException extends IOException {
        @Override
        public String getMessage() {
            return "No connectivity exception";
        }
}
