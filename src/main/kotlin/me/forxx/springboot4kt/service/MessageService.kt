package me.forxx.springboot4kt.service

import me.forxx.springboot4kt.dao.MessageMapper
import me.forxx.springboot4kt.model.Message
import me.forxx.springboot4kt.vo.R
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * Created by GaoMingQiang on 2017/12/12 0012 14:08.
 */
@Service
class MessageService{

    @Autowired
    private val messageMapper: MessageMapper? = null

    @Transactional
    fun addMessage(nick_name: String, content: String, face: String): R{
        var msg = Message()
        msg.nick_name = nick_name
        msg.content = content
        msg.face = face
        msg.add_time = Date()
        messageMapper!!.insertSelective(msg)
        return R(1,"")
    }



}
