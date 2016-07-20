package cn.com.p2p.security.login.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cn.com.p2p.domain.user.criteria.WebUserCriteria;
import cn.com.p2p.domain.user.entity.WebUser;
import cn.com.p2p.domain.user.repository.WebUserRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.security.login.service.FrontLoginService;
import cn.com.p2p.security.login.service.dto.LoginUserDetail;

public class FrontLoginServiceImpl implements UserDetailsService, FrontLoginService {

    @Autowired
    public Properties settings;

    @Autowired
    private WebUserRepository webUserRepo;

//    @Autowired TODOBUG
//    private SendMessage sendMessage;

    /**
     * 用户登录 spring security借口实现
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LoginUserDetail lud = null;
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

        // 前台登录用户取得
        WebUser webUser = null;
        WebUserCriteria criteria = new WebUserCriteria();
        criteria.setLogin(username, Operator.equal);
        List<WebUser> webuserList = webUserRepo.findByCriteria(criteria);

        if (webuserList.size() == 0) {
            criteria = new WebUserCriteria();
            criteria.setCelphone(username, Operator.equal);
            webuserList = webUserRepo.findByCriteria(criteria);
        }

        if (webuserList.size() > 0) {
            webUser = webuserList.get(0);
        }
        // 用户不存在
        if (webUser == null) {
            lud = new LoginUserDetail();
            lud.setId("");
            lud.setUsername("");
            lud.setPassword("");
            lud.setSalt("");
            lud.setAuthorities(list);
        } else {

            // 默认是没有激活状态
            boolean activeflag = false;
            // active 1 是被激活状态 0是没被激活
            if (webUser.getActive() == 1) {
                activeflag = true;
            }

            // 永不过期
            boolean isNotExpired = true;

            // lock 1 是用户锁定 0 是用户没有锁
            boolean isNotLocked = false;
            if ("1".equals(webUser.getValidFlag())) {
                isNotLocked = true;
            }

            lud = new LoginUserDetail(
                    webUser.getId(),
                    webUser.getLogin(),
                    webUser.getPassword(),
                    webUser.getPasswordSalt(),
                    list,
                    webUser.getCelphone(),
                    webUser.getEmail(),
                    webUser.getCiccAccountId(),
                    webUser.getCiccDebitaccountId(),
                    activeflag,
                    isNotExpired,
                    isNotLocked);
        }
        return lud;
    }

    @Override
    public void doLoginSuccess(String currentIp) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() != null && authentication.getPrincipal() instanceof LoginUserDetail) {
                LoginUserDetail userDetails = (LoginUserDetail) authentication.getPrincipal();
                if (userDetails != null) {
                    WebUser webUser = null;
                    WebUserCriteria criteria = new WebUserCriteria();
                    criteria.setLogin(userDetails.getUsername(), Operator.equal);
                    List<WebUser> webuserList = webUserRepo.findByCriteria(criteria);

                    if (webuserList.size() == 0) {
                    	criteria = new WebUserCriteria();
                        criteria.setCelphone(userDetails.getUsername(), Operator.equal);
                        webuserList = webUserRepo.findByCriteria(criteria);
                    }

                    if (webuserList.size() > 0) {
                        webUser = webuserList.get(0);
                    }
                    webUser.setLastLoginAt(webUser.getCurrentLoginAt());// 上次登录时间
                    webUser.setLastLoginIp(webUser.getCurrentLoginIp());// 上次登录ip
                    webUser.setCurrentLoginIp(currentIp);// 本次登录ip
                    webUser.setCurrentLoginAt(new Date());// 最后登录时间
                    webUser.setFailedLoginCount(0);
                    webUserRepo.dynamicUpdate(webUser);
                }
            }
        }

    }

    @Override
    public int doLoginFailure(String j_username, String ip) {
        int failCnt = 0;
        String userName = j_username;
        if (userName != null && userName.length() > 0) {
            // 前台登录用户取得
            WebUser webUser = null;
            WebUserCriteria criteria = new WebUserCriteria();
            criteria.setLogin(j_username, Operator.equal);
            List<WebUser> webuserList = webUserRepo.findByCriteria(criteria);

            if (webuserList.size() == 0) {
                criteria.setCelphone(j_username, Operator.equal);
                webuserList = webUserRepo.findByCriteria(criteria);
            }

            if (webuserList.size() > 0) {
                webUser = webuserList.get(0);
            }
            if (webUser != null) {
                webUser.setLastLoginAt(webUser.getCurrentLoginAt());// 上次登录时间
                webUser.setLastLoginIp(webUser.getCurrentLoginIp());// 上次登录ip
                webUser.setCurrentLoginIp(ip);// 本次登录ip
                webUser.setCurrentLoginAt(new Date());// 最后登录时间
                webUser.setUpdateTime(new Date());
                if (webUser.getFailedLoginCount() != null) {
                    failCnt = webUser.getFailedLoginCount() + 1;
                } else {
                    failCnt = 1;
                }

                webUser.setFailedLoginCount(failCnt);
                int lockcnt = 6;
                String lockcntStr = settings.getProperty("backuser_lockcount");
                if (lockcntStr != null) {
                    lockcnt = Integer.parseInt(lockcntStr);
                }
                if (failCnt >= lockcnt) {
                    // 当天超过10密码输错账户锁定，并且发送短信
                    // lockFlag 0 没有lock 1 lock
                    webUser.setValidFlag("1");
                    // 您正在登录[瑞赢在线]管理平台，并且已经超出允许最大尝试登录次数，为了您的帐户安全，系统已锁定您的帐户，请联系系统管理员
                    // 平台名
                    //String sysName = "多账户P2P平台";
                    // sendMailMessage.send(pfmuser.getTenantCd(),
                    // pfmuser.getEmailAddress(), "3", true, sysName,
                    // pfmuser.getRealName());
                    // SysSetting name =
                    // sysSettingService.getByCode("site_name");
                    // if(name != null)
                    // sysName = name.getSettingValue();
                    // sendMessage.send(pfmuser.getContactPhone(),"18",sysName,sysName);
                    // pfmuser.setActive(false);
                }
                webUserRepo.dynamicUpdate(webUser);
            }
        }
        return failCnt;
    }

}
