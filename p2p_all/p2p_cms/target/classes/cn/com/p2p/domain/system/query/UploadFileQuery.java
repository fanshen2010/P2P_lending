package cn.com.p2p.domain.system.query;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.p2p.domain.system.entity.UploadFile;

/**
 * 业务相关UploadFileQuery定义.
 * <p>
 * 数据访问层<图片表>的组件 : 供业务逻辑层调用的基本数据持久化类
 * 
 * @author 
 */
public interface UploadFileQuery {
    /**
     * 图片信息查询
     * <p>
     * @param  pstrId  id
     * @return  图片信息
     */
    public List<UploadFile> findUploadFileByIds(
            @Param("plstId") List<String> plstId);
}
