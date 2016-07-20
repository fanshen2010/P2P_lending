package cn.com.p2p.usermangent.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.p2p.domain.user.criteria.PfmUserCriteria;
import cn.com.p2p.domain.user.entity.PfmTenantDepartment;
import cn.com.p2p.domain.user.entity.PfmTenantPost;
import cn.com.p2p.domain.user.entity.PfmUser;
import cn.com.p2p.domain.user.query.PfmUserManageQuery;
import cn.com.p2p.domain.user.repository.PfmTenantDepartmentRepository;
import cn.com.p2p.domain.user.repository.PfmTenantPostRepository;
import cn.com.p2p.domain.user.repository.PfmUserRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.util.DESPlus;
import cn.com.p2p.framework.util.StringUtils;
import cn.com.p2p.usermangent.service.BusinessStaffService;
import cn.com.p2p.usermangent.service.dto.PfmUserDto;

/**
 * 
 * 业务人员管理接口实现类
 * 
 * @author 
 *
 */
@Service
public class BusinessStaffServiceImpl implements BusinessStaffService {

    /** 商户的管理用户表 数据访问接口 */
    @Autowired
    private PfmUserRepository pfmUserRepository;

    @Autowired
    private PfmUserManageQuery pfmTenantDepartmentManageQuery;

    // 业务人员集合
    private List<PfmUser> pfmUserList;

    @Autowired
    PfmTenantDepartmentRepository pfmTenantDepartmentRepository;

    @Autowired
    PfmTenantPostRepository pfmTenantPostRepository;

    /**
     * 根据条件类查询业务人员列表(分页)
     * @author 
     * 
     * @param criteria 条件参数类
     * @return List<PfmUser> 业务人员列表
     */
    @Override
    public List<PfmUser> getPfmUserPageByCriteria(PfmUserCriteria criteria) {

        return pfmUserRepository.findPageByCriteria(criteria);
    }

    /**
     * 根据条件类查询业务人员Dto列表(分页)
     * @author 
     * 
     * @param criteria 条件参数类
     * @return List<PfmUserDto> 业务人员Dto列表
     */
    @Override
    public List<PfmUserDto> getPfmUserDtoPageByCriteria(PfmUserCriteria criteria) {

        return pfmTenantDepartmentManageQuery.findPagePfmUserDtoByCriteria(criteria);
    }

    /**
     * 根据条件类查询业务人员列表
     * @author 
     * 
     * @param criteria 条件参数类
     * @return List<PfmUser> 业务人员列表
     */
    @Override
    public List<PfmUser> getPfmUserByCriteria(PfmUserCriteria criteria) {

        return pfmUserRepository.findByCriteria(criteria);
    }

    /**
     * 根据ID（主键）查询业务人员实体
     * @author 
     * 
     * @param id 查询参数
     * @return PfmUser 业务人员实体
     */
    @Override
    public PfmUser getPfmUserById(String id) {

        return pfmUserRepository.findOne(id);
    }

    /**
     * 按主键进行动态更新，对Input数据对需要更新的字段进行设值
     * @author 
     * 
     * @param pfmUser 跟新实体
     * @return 更新成功标志
     */
    @Override
    public int dynamicUpdatePfmUser(PfmUser pfmUser) {
        // 密码为""空串则不更新密码
        try {
            if (pfmUser != null && StringUtils.compare("", pfmUser.getPassword())) {
                pfmUser.setPassword(null);
            } else {
                // pfmUser.setPasswordSalt(StringUtils.getRandomSalt());
                PfmUser user = pfmUserRepository.findOne(pfmUser.getId());
                if (user != null) {
                    pfmUser.setPasswordSalt(user.getPasswordSalt());  // 获取当前数据密码秘钥
                    pfmUser.setPassword(DESPlus.byteArr2HexStr((pfmUser.getPassword() + pfmUser.getPasswordSalt())
                            .getBytes()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pfmUserRepository.dynamicUpdate(pfmUser);
    }

    /**
     * 业务人员登录密码校验
     * @author 
     * 
     * @param userName 登录用户名
     * @param passWord 登录密码
     * 
     * @return boolean 校验结果
     */
    @Override
    public boolean pfmUserPassWordCheck(String userName, String passWord) {
        boolean checkFlag = false;

        PfmUser pfmUser = pfmUserRepository.findPfmUserByUserName(userName);

        try {
            if (StringUtils.compare(DESPlus.byteArr2HexStr((passWord + pfmUser.getPasswordSalt()).getBytes()), pfmUser
                    .getPassword())) {
                checkFlag = true;
            }
        } catch (Exception e) {
        }

        return checkFlag;
    }

    /**
     * 根据Id进行Delete删除
     * @author 
     * 
     * @param id 删除Id
     * @return 删除成功标志
     */
    @Override
    public int deletePfmUserById(String id) {

        return pfmUserRepository.delete(id);
    }

    /**
     * 插入一条新纪录，正确插入时返回值为 1
     * @author 
     * 
     * @param pfmUser 插入实体
     * @return 插入成功标志(1为成功，2为用户名冲突，0为异常失败)
     */
    public int insertPfmUser(PfmUser pfmUser) {

        int ret = 0;

        // 单独平台时，商户ID没有设置
        pfmUser.setTenantCd("001");
        pfmUser.setPasswordSalt(StringUtils.getRandomSalt());
        // 用户处于没有锁定状态
        pfmUser.setLockFlag("0");
        // 用户默认被激活状态 未来可能需要发送mail 让用户自己激活
        pfmUser.setActive("1");
        // 用户设定为有效用户，当员工离职后，账户可以设置为实效
        pfmUser.setValidFlag("1");
        try {
            pfmUser.setPassword(DESPlus.byteArr2HexStr((pfmUser.getPassword() + pfmUser.getPasswordSalt()).getBytes()));
        } catch (Exception e) {
        }

        // 主键用户名check
        if (pfmUserNameCheck(pfmUser.getUserName())) {
            ret = 2;
        } else {
            ret = pfmUserRepository.insert(pfmUser);
        }

        return ret;
    }

    /**
     * 业务人员管理 主键用户名验证
     * @author 
     * 
     * @param userName 需要验证用户名
     * @return 验证结果 存在 返回 true
     */
    @Override
    public boolean pfmUserNameCheck(String userName) {

        boolean userNameCheck = false;

        PfmUserCriteria criteria = new PfmUserCriteria();
        criteria.setUserName(userName, Operator.equal);

        // 主键用户名check
        pfmUserList = pfmUserRepository.findByCriteria(criteria);

        if (pfmUserList != null && !pfmUserList.isEmpty()) {
            userNameCheck = true;
        }

        return userNameCheck;
    }

    @Override
    public List<PfmUser> findPfmUserByRealName(String realName) {
        return pfmTenantDepartmentManageQuery.findPfmUserByDepartCd(realName);
    }

    @Override
    public PfmUserDto findPfmUserInfo(String id) {
        PfmUser user = pfmUserRepository.findOne(id);
        PfmTenantDepartment depart = pfmTenantDepartmentRepository.findPfmTenantDepartmentByKeys(user.getTenantCd(),
                user.getDepartCd());
        PfmTenantPost pfmTenantPost = pfmTenantPostRepository.findPfmTenantPostByKeys(user.getTenantCd(), user
                .getPostCd());
        PfmUserDto userDto = new PfmUserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setRealName(user.getRealName());
        userDto.setContactPhone(user.getContactPhone());
        userDto.setEmailAddress(user.getEmailAddress());
        if (depart != null) {
            userDto.setDepartName(depart.getDepartmentName());
        }
        if (pfmTenantPost != null) {
            userDto.setPostName(pfmTenantPost.getPostName());
        }

        return userDto;
    }

    @Override
    public List<PfmUser> findAllUser() {
        return pfmUserRepository.findAll();
    }

    @Override
    public List<PfmUser> getUserByRealName(String realName) {
        PfmUserCriteria criteria = new PfmUserCriteria();
        criteria.setRealName(realName, Operator.equal);
        return pfmUserRepository.findByCriteria(criteria);
    }
}
