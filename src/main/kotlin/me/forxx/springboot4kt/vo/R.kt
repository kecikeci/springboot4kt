package me.forxx.springboot4kt.vo

import com.alibaba.fastjson.JSONObject

/**
 * Created by GaoMingQiang on 2017/12/12 0012 10:58.
 */
class R {

    var code: Int? = 0   //0失败，1成功
    var msg = ""    //提示信息
    var data: Any = JSONObject()     //返回数据

    constructor() {
    }

    constructor(code: Int?, msg: String, data: Any) {
        this.code = code
        this.msg = msg
        this.data = data
    }

    /**
     * 只传错误信息
     * @param msg
     */
    constructor(msg: String) {
        this.code = 0
        this.msg = msg
    }

    constructor(code: Int?, data: Any) {
        this.code = code
        this.data = data
    }

    override fun toString(): String {
        return "R(code=$code, msg='$msg', data=$data)"
    }


}
