package com.example.shop.system.service.impl;

import com.example.shop.base.SessionUSER;
import com.example.shop.base.util.JwtUtil;
import com.example.shop.base.util.MD5;
import com.example.shop.management.bean.DTO.FuncListDTO;
import com.example.shop.management.bean.LoginUser;
import com.example.shop.management.bean.MemberInfo;
import com.example.shop.pub.Utils.RedisUtils;
import com.example.shop.system.bean.FuncList;
import com.example.shop.system.bean.UserInfo;
import com.example.shop.system.mapper.FuncListMapper;
import com.example.shop.system.mapper.UserInfoMapper;
import com.example.shop.system.service.UserInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 陈志浩123
 * @since 2020-12-22
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private FuncListMapper funcListMDAO;
    @Override
    public LoginUser login(UserInfo user) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());


        UserInfo user1 = null;
        LoginUser loginUser=new LoginUser();
        try {

            List<UserInfo> userList = userInfoMapper.selectByMap(map);
            if (userList == null || userList.size() <= 0){
                loginUser.setLogin(false);
                return loginUser;
            }

            if (StringUtils.equals(user.getState(), "L"))
                if (userList == null || userList.size() <= 0){
                    loginUser.setLogin(false);
                    return loginUser;
                }

            user1=userList.get(0);
            // 校验用户名密码
            String encrypt = MD5.toMD5(user.getPassword() + user.getSalt());
//            if (!StringUtils.equalsIgnoreCase(encrypt, user.getPassword())) {
//                loginUser.setStade(false);
//            }
        } catch (Exception e) {
            // throw new MyException("该用户名或者密码错误,请检查后再登录!");
        }

        //根据电话号码和密码加密生成token
        loginUser.setUserInfo(user1);
        String token = JwtUtil.sign(user1.getUsername(), user1.getPassword());
        redisUtils.set(token,user1,10000L);
        loginUser.setToken(token);
        loginUser.setLogin(true);
        return loginUser;
    }

    @Override

    public List<FuncListDTO> buildMenu() {
        // 查询二级菜单
        String userId = SessionUSER.get(SessionUSER.userId);
        List<FuncListDTO> subFuncList = funcListMDAO.queryLevel2FuncByUserId(userId);

        // 查询3级菜单
        List<FuncListDTO> subFuncList3 = funcListMDAO.queryLevel3FuncByUserId(userId);

        // 查询一级菜单
        FuncListDTO mvo = new FuncListDTO();
        mvo.setSts("A");
        mvo.setMenuLevel(1);
        List<FuncListDTO> funcList = funcListMDAO.queryList(mvo);


        // 查询二级菜单(所有)
        FuncListDTO mvo2 = new FuncListDTO();
        mvo2.setSts("A");
        mvo2.setMenuLevel(2);
        List<FuncListDTO> funcList2 = funcListMDAO.queryList(mvo2);

        //将已有的二级菜单叶子id取出
        String strFun2 = "";
        for (Iterator<FuncListDTO> iter = subFuncList.iterator(); iter.hasNext();) {
            FuncListDTO f2 = iter.next();
            strFun2 = strFun2 +",'"+f2.getFuncId()+"'";
        }

        // 将3级菜单挂到2级菜单下
        for (Iterator<FuncListDTO> iter = funcList2.iterator(); iter.hasNext();) {
            String strFlag = "1";
            FuncListDTO _f2 = iter.next();

            String strFun2Id = "'"+_f2.getFuncId()+"'";
            if(strFun2.indexOf(strFun2Id)<0){//判断本身是否是一个叶子
                strFlag = "2";
            }else{//判断是否有三级

                List<FuncListDTO> _subFuncList = new ArrayList<FuncListDTO>();
                for (FuncListDTO _f3 : subFuncList3) {
                    if (_f3.getParentId()==_f2.getFuncId()) {
                        _subFuncList.add(_f3);
                        strFlag = "2";
                    }

                }
                if (!_subFuncList.isEmpty()) {
                    _f2.setSubFuncList(_subFuncList);
                }
            }

            if ("1".equals(strFlag)) {
                iter.remove();
            }
        }


        // 将二级菜单挂到一级菜单下
        for (Iterator<FuncListDTO> iter = funcList.iterator(); iter.hasNext();) {
            FuncListDTO _f1 = iter.next();

            List<FuncListDTO> _subFuncList = new ArrayList<FuncListDTO>();
            for (FuncListDTO _f2 : funcList2) {
                if ((_f2.getParentId()== _f1.getFuncId())) {
                    _subFuncList.add(_f2);

                }
            }
            if (_subFuncList.isEmpty()) {
                iter.remove();
            } else {
                _f1.setSubFuncList(_subFuncList);
            }
        }
        return funcList;
    }


}
