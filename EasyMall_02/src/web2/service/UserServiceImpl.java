package web2.service;

import web2.dao.MySqlUserDaoImpl;
import web2.domain.User;
import web2.exception.MsgException;
import web2.utiles.MD5Utils;

public class UserServiceImpl {
    private MySqlUserDaoImpl userDao = new MySqlUserDaoImpl();

    /**
     * 校验用户名是否存在
     * @param username 要校验的用户名
     * @return 存在反回true，不存在返回false
     */
    public boolean hasUsername(String username) {
        User user = userDao.queryUserByUsername(username);
        return user != null;
    }
    /**
     * 注册的业务
     * @param user
     */
    public void regist(User user) throws MsgException {
        //检查用户名是否存在
        if (userDao.queryUserByUsername(user.getUsername())!=null){
            //抛异常
            throw new MsgException("用户名已经存在");
        }
        //注册
        userDao.addUser(user);
    }
    /**
     * 登录业务
     * @param username 用户名
     * @param password 密码
     * @return 要登陆用户的bean
     * @throws MsgException 如果用户名和密码错误，抛出异常
     */
    public User login(String username,String password) throws MsgException {
        User user = userDao.queryUserByUsernameAndPassword(username,password);
        if (user==null){
            throw new MsgException("用户名或密码不正确");
        }
        return user;
    }
}
