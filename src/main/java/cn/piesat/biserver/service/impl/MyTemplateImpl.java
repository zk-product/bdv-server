package cn.piesat.biserver.service.impl;

import cn.piesat.biserver.common.PathConfig;
import cn.piesat.biserver.constant.StatisticsConstant;
import cn.piesat.biserver.dao.AssemblyDataMapper;
import cn.piesat.biserver.dao.AssemblyMapper;
import cn.piesat.biserver.dao.MyTemplateMapper;
import cn.piesat.biserver.entity.AssemblyDataEntity;
import cn.piesat.biserver.entity.AssemblyEntity;
import cn.piesat.biserver.entity.MyTemplateEntity;
import cn.piesat.biserver.service.IMyTemplate;
import cn.piesat.biserver.util.FileUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

/**
 * @author zk
 * @date 2019/8/28 11:27
 */
@Service
public class MyTemplateImpl implements IMyTemplate {

    @Autowired
    private MyTemplateMapper mapper;

    @Autowired
    private AssemblyMapper assemblyMapper;

    @Autowired
    private AssemblyDataMapper assemblyDataMapper;

    @Override
    public List<MyTemplateEntity> queryTemplate(MyTemplateEntity myTemplateEntity) {
        PageHelper.startPage(myTemplateEntity.getPageIndex(),myTemplateEntity.getPageSize());
        return mapper.queryTemplate(myTemplateEntity);
    }

    @Override
    public MyTemplateEntity queryTemplateById(MyTemplateEntity myTemplateEntity) {
        return mapper.queryTemplateById(myTemplateEntity.getId());
    }

    @Override
    public MyTemplateEntity addMyTemplate(MyTemplateEntity myTemplate) {
        int insert = mapper.insert(myTemplate);
        if (insert > 0) {
            return myTemplate;
        }
        return null;
    }

    @Override
    public boolean deleteTemplateById(String id) {
        int i = mapper.deleteTemplateById(id);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTemplateById(MyTemplateEntity entity) {
        int i = mapper.updateTemplateById(entity);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public String addScreenByTemplate(String id, String name) {
        /**
         * 查询并添加大屏
         */
        MyTemplateEntity template = mapper.queryTemplateById(id);
        template.setName(name);
        int insertTemplate = mapper.insert(template);
        if (insertTemplate > 0) {
            /**
             * 查询并添加组件
             */
            AssemblyEntity assemblyEntity = new AssemblyEntity();
            assemblyEntity.setScreenId(id);
            List<AssemblyEntity> assemblyEntities = assemblyMapper.queryAssembly(assemblyEntity);
            if (assemblyEntities.size() == 0) {
                return template.getId();
            }
            //存储旧id和新id，为下面新增数据提供支撑
            Map<String, String> assemblyIds = new HashMap<String, String>();
            List<String> oldAssemblyIds = new ArrayList<String>();
            for (AssemblyEntity entity : assemblyEntities) {
                String newAssemblyId = UUID.randomUUID().toString().replaceAll("-", "");
                String oldAssemblyId = entity.getId();
                entity.setId(newAssemblyId);
                entity.setScreenId(template.getId());
                entity.setCollection(0);

                assemblyIds.put(oldAssemblyId, newAssemblyId);
                oldAssemblyIds.add(oldAssemblyId);
            }
            int insertAssembly = assemblyMapper.insertBatch(assemblyEntities);
            if (insertAssembly > 0) {
                /**
                 * 查询并添加组件数据
                 */
                List<AssemblyDataEntity> assemblyDataEntities = assemblyDataMapper.queryAssemblyDataByIds(oldAssemblyIds);
                for (AssemblyDataEntity assemblyDataEntity : assemblyDataEntities) {
                    String oldAssemblyId = assemblyDataEntity.getAssemblyId();
                    assemblyDataEntity.setAssemblyId(assemblyIds.get(oldAssemblyId));
                }
                assemblyDataMapper.insertBatch(assemblyDataEntities);
            }
        }
        return template.getId();
    }

    @Override
    public String uploadCover(String id, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String filePath = StatisticsConstant.SCREEN_COVER_PATH  + id + suffix;
        try {
            FileUtil.writeFile(filePath, file.getBytes(), true);
            boolean b = updateScreenPicaddr(id, filePath);
            if (!b) {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            filePath = "";
        }

        return filePath;
    }

    @Override
    public String uploadCover(String id, String file) {
        //“，”号之后是base64部分
        file = file.substring(file.indexOf(",") + 1);
        final Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodeFile = decoder.decode(file);

        //与前端约定上传图片格式为.png
        String filePath = StatisticsConstant.SCREEN_COVER_PATH  + id + ".png";
        //将图片保存至磁盘
        FileUtil.writeFile(filePath, decodeFile, true);
        /**
         * 修改模板封面地址
         */
        boolean b = updateScreenPicaddr(id, filePath);
        if (b) {
            return filePath;
        } else {
            return "";
        }
    }

    /**
     * 修改大屏封面地址
     * @param id
     * @param filePath
     * @return
     */
    private boolean updateScreenPicaddr(String id, String filePath) {
        MyTemplateEntity entity = new MyTemplateEntity();
        entity.setId(id);
        entity.setPicAddr(filePath);
        return updateTemplateById(entity);
    }

    /**
     * 修改大屏背景图
     * @param id
     * @param filePath
     * @return
     */
    private boolean updateScreenBackground(String id, String filePath) {
        MyTemplateEntity entity = new MyTemplateEntity();
        entity.setId(id);
        entity.setBackgroundPic(filePath);
        return updateTemplateById(entity);
    }

    @Override
    public String uploadBackground(String id, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String filePath = StatisticsConstant.SCREEN_BACKGROUND_PATH  + id + suffix;
        try {
            FileUtil.writeFile(filePath, file.getBytes(), true);
            boolean b = updateScreenBackground(id, filePath);
            if (!b) {
                return "";
            }
        } catch (IOException e) {
            e.printStackTrace();
            filePath = "";
        }

        return filePath;
    }
}
