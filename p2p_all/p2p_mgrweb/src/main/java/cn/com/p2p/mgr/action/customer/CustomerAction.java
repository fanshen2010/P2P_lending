package cn.com.p2p.mgr.action.customer;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;

import cn.com.p2p.framework.web.action.BaseAction;


@Namespace("/customer")
public class CustomerAction extends BaseAction{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public String init() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Action(value="checkLogin")
    public void checkLogin() throws Exception{
        
    }

}
