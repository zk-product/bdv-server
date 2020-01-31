package cn.piesat.biserver.service.impl;

import cn.piesat.biserver.dao.APIManagerMapper;
import cn.piesat.biserver.dao.APIParamsMapper;
import cn.piesat.biserver.dao.DictionaryDataMapper;
import cn.piesat.biserver.entity.APIManagerEntity;
import cn.piesat.biserver.entity.APIParamsEntity;
import cn.piesat.biserver.service.IAPIManager;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zk
 * @date 2019/9/27 14:49
 */
@Service
public class APIManagerImpl implements IAPIManager {
    @Autowired
    private APIManagerMapper mapper;
    @Autowired
    private APIParamsMapper paramsMapper;
    @Autowired
    private DictionaryDataMapper dicMapper;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean addAPI(APIManagerEntity entity) {
        int insert = mapper.insert(entity);
        if (insert > 0) {
            String APIId = entity.getId();
            List<APIParamsEntity> APIParamsEntity = entity.getApiParamsEntity();
            if (APIParamsEntity != null) {
                for (int i = 0; i < APIParamsEntity.size(); i++) {
                    APIParamsEntity paramsEntity = APIParamsEntity.get(i);
                    if (paramsEntity.getApiParam() == null || "".equals(paramsEntity.getApiParam())) {
                        APIParamsEntity.remove(paramsEntity);
                        continue;
                    }
                    paramsEntity.setApiId(APIId);
                }
                if (APIParamsEntity.size() > 0) {
                    int insertParams = paramsMapper.insertBatch(APIParamsEntity);
                    if (insertParams > 0) {
                        return true;
                    }
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public List<APIManagerEntity> queryAPIByPaging(APIManagerEntity entity) {
        PageHelper.startPage(entity.getPageIndex(), entity.getPageSize());
        List<APIManagerEntity> list = mapper.queryAPIByPaging(entity);
        return list;
    }

    @Override
    public List<APIManagerEntity> queryAllAPI() {
        List<APIManagerEntity> list = mapper.queryAllAPI();
        /**
         *  API无别名时，使用API的字段名作为别名
         */
        list.forEach(API -> {
            API.getApiParamsEntity().forEach(APIParam -> {
                if (APIParam.getParamAlias() == null || "".equals(APIParam.getParamAlias())) {
                    APIParam.setParamAlias(APIParam.getApiParam());
                }
            });
        });
        return list;
    }

    @Override
    public APIManagerEntity queryAPIById(String id) {
        return mapper.queryAPIById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public boolean updateAPIById(APIManagerEntity entity) {
        int i = mapper.updateAPIById(entity);
        if (i >= 0) {
            List<APIParamsEntity> APIParamsEntity = entity.getApiParamsEntity();
            for (APIParamsEntity paramsEntity : APIParamsEntity) {
                paramsEntity.setApiId(entity.getId());
            }
            boolean b = updateParamByAPIId(entity.getId(),APIParamsEntity);
            if (b) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteAPIById(String id) {
        int i = mapper.deleteAPIById(id);
        if (i > 0) {
            return true;
        }
        return false;
    }

    public boolean updateParamByAPIId(String APIId, List<APIParamsEntity> entitys) {
        int i = paramsMapper.deleteParamById(APIId);
        if (i >= 0) {
            int j = paramsMapper.insertBatch(entitys);
            if (j > 0) {
                return true;
            }
        }
        return false;
    }

}
