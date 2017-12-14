package me.forxx.springboot4kt.web

import com.github.pagehelper.PageHelper
import com.github.pagehelper.PageInfo
import me.forxx.springboot4kt.dao.MessageMapper
import me.forxx.springboot4kt.service.MessageService
import me.forxx.springboot4kt.util.Util
import me.forxx.springboot4kt.vo.R
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

import java.util.HashMap

/**
 * Created by GaoMingQiang on 2017/12/12 0012 14:08.
 * https://4xx.me
 */
@Controller
class MessageWeb {

    @Autowired
    private val messageMapper: MessageMapper? = null
    @Autowired
    private val messageService: MessageService? = null

    @RequestMapping("")
    fun index():String{
        return "forward:/"
    }

    @RequestMapping("/")
    fun selectMessageList(pageNum: Int?,type: Int?,model: ModelMap): String {
        var pageNum = pageNum?:1
        var type = type?:1
        var pageSize = 10
        PageHelper.startPage<Any>(pageNum, pageSize)
        var para = HashMap<Any,Any>()
        if (type==1){
            para.put("sorts","time")
        }else{
            para.put("sorts","praise")
        }
        var messageList = messageMapper!!.selectListByMap(para)
        var pageInfo = PageInfo(messageList)
        model.put("type",type)
        model.put("page",pageInfo)
        return "/msg"
    }


    @RequestMapping("/addMessage.json")
    @ResponseBody
    fun addMessage(nick_name: String, content: String, face: String):R {
        return messageService!!.addMessage(nick_name, content, face)
    }

    @RequestMapping("/praise.json")
    @ResponseBody
    fun praise(id: Int): R{
        var para = HashMap<Any,Any>()
        para.put("id",id)
        var message = messageMapper!!.selectByMap(para)
        if (!Util.isEmptys(message,"")){
            message.praise += 1
        }
        messageMapper!!.updateByPrimaryKeySelective(message)
        return R(1,"")
    }


}
