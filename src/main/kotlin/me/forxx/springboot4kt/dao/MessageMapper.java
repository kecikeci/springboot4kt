package me.forxx.springboot4kt.dao;

import java.util.List;
import java.util.Map;
import me.forxx.springboot4kt.model.Message;

/**
 * http://4xx.me
 * By 九羲公子
 */
public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int selectCountByMap(Map<Object, Object> map);

    Message selectByMap(Map<Object, Object> map);

    List<Message> selectListByMap(Map<Object, Object> map);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKeyWithBLOBs(Message record);

    int updateByPrimaryKey(Message record);
}