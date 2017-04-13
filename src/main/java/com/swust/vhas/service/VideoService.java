package com.swust.vhas.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.swust.vhas.dao.VideoDao;
import com.swust.vhas.model.Type;
import com.swust.vhas.model.Video;

@Service
public class VideoService {

    @Autowired
    @Qualifier("videoDao")
    private VideoDao videoDao;

    private static Collection<Type> types;

    public static Collection<Type> getTypes() {
        return types;
    }

    public static void setTypes(Collection<Type> types) {
        VideoService.types = types;
    }

    public int deleteByPrimaryKey(Integer id) {
        return videoDao.deleteByPrimaryKey(id);
    }

    public int insert(Video record) {
        return videoDao.insert(record);
    }

    public int insertSelective(Video record) {
        return videoDao.insertSelective(record);
    }

    public Video selectByPrimaryKey(Integer id) {
        return videoDao.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Video record) {
        return videoDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Video record) {
        return videoDao.updateByPrimaryKey(record);
    }

    public List<Video> selectTop(String beginTime, Integer size, String order, String type, Integer web) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("beginTime", beginTime);
        map.put("size", size);
        map.put("order", order);
        if (exitType(type))
            map.put("type", type);
        map.put("webId", web);
        return videoDao.selectTop(map);
    }

    @SuppressWarnings("static-access")
    public Collection<Type> selectAllTypes(Integer webId) {
        if (types != null)
            return types;
        // this.types = new ArrayList<Type>();
        Map<String, Type> map = new HashMap<String, Type>();
        List<Type> types = videoDao.selectAllTypes(webId);
        for (int i = 0; i < types.size(); i++) {
            String name = types.get(i).name;
            long click = types.get(i).click;
            if (name == null || name.equals("") || name.contains("null"))
                continue;
            int index = name.indexOf('|');
            if (index <= 0)
                continue;
            String parent = null;
            String child = null;
            try {
                parent = name.substring(0, index);// 父级
                child = name.substring(index + 1, name.length());// 子项
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            // System.out.println(parent + "-" + child + ":" + click);
            Type type = map.get(parent);
            if (type == null)
                type = new Type(parent);
            type.addChild(new Type(child, parent, click));
            map.put(parent, type);
        }
        this.types = map.values();
        return this.types;
    }

    public List<Type> selectUpdateByType(String type) {
        return videoDao.selectUpdateByType(type);
    }

    public List<Video> selectUpdate(Integer webId, String vid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("webId", webId);
        map.put("vid", vid);
        return videoDao.selectUpdate(map);
    }

    public List<Video> selectAllByAuthorId(Integer id) {
        return videoDao.selectAllByAuthorId(id);
    }

    private boolean exitType(String type) {
        for (Type type2 : selectAllTypes(null)) {
            if (type2.name.equals(type))
                return true;
        }
        return false;
    }

}
