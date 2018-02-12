package com.xinran.sharelib;

/**
 * Created by houqixin on 2018/2/12.
 */

public class QXShareException extends RuntimeException {
    public QXShareException(String msg){
        super("QXShareException:"+msg);
    }
}
