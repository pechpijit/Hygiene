package com.tong.hygiene.okhttp;

/**
 * Created by TheeranaiAsipong on 27/4/2560.
 */

public interface CallServiceListener {
    void ResultData(String data);
    void ResultError(String data);
    void ResultNull(String data);
}
