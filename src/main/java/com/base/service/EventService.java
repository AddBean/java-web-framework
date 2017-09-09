package com.base.service;

import com.base.dao.EventMapper;
import com.base.dao.RecordMapper;
import com.base.entity.Event;
import com.base.entity.Record;
import com.base.entity.condition.EventCondition;
import com.base.entity.condition.RecordCondition;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/9.
 */
@Service
public class EventService {
    @Resource
    private EventMapper mEventMapper;
    @Resource
    private RecordMapper mRecordMapper;

    public List<Event> getEventList(EventCondition event) {
        return mEventMapper.selectByCondition(event);
    }

    public List<Record> getEventRecordList(RecordCondition event) {
        return mRecordMapper.selectByCondition(event);
    }

    public int addRecord(Record record) {
        return mRecordMapper.insert(record);
    }

    public int deltetRecord(int id) {
        return mRecordMapper.deleteByPrimaryKey(id);
    }

    public int addEvent(Event event) {
        return mEventMapper.insert(event);
    }

    public int deleteEvent(int id) {
        return mEventMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据record判断是否要添加新类型；
     *
     * @param record
     */
    public int addEventByRecord(Record record) {
        EventCondition con = new EventCondition();
        con.setName(record.getName());
        con.setcIndex(-1);
        List<Event> list = mEventMapper.selectByCondition(con);
        if (list != null && list.size() > 0) {
            list.get(0).setCount(list.get(0).getCount() + 1);
            mEventMapper.updateByPrimaryKey(list.get(0));
            return 1;
        }
        Event event = new Event();
        event.setName(record.getName());
        event.setAddTime(new Date());
        event.setUpdateTime(event.getAddTime());
        event.setType(record.getName());
        event.setDes("自动创建");
        event.setCount(1);
        mEventMapper.insert(event);
        return 1;
    }
}
