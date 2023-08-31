package web2.service;

import web2.dao.MySqlUserDaoImpl;
import web2.domain.User;
import web2.exception.MsgException;
import web2.utiles.MD5Utils;

public class UserServiceImpl {
    private MySqlUserDaoImpl userDao = new MySqlUserDaoImpl();

    /**
     * У���û����Ƿ����
     * @param username ҪУ����û���
     * @return ���ڷ���true�������ڷ���false
     */
    public boolean hasUsername(String username) {
        User user = userDao.queryUserByUsername(username);
        return user != null;
    }
    /**
     * ע���ҵ��
     * @param user
     */
    public void regist(User user) throws MsgException {
        //����û����Ƿ����
        if (userDao.queryUserByUsername(user.getUsername())!=null){
            //���쳣
            throw new MsgException("�û����Ѿ�����");
        }
        //ע��
        userDao.addUser(user);
    }
    /**
     * ��¼ҵ��
     * @param username �û���
     * @param password ����
     * @return Ҫ��½�û���bean
     * @throws MsgException ����û�������������׳��쳣
     */
    public User login(String username,String password) throws MsgException {
        User user = userDao.queryUserByUsernameAndPassword(username,password);
        if (user==null){
            throw new MsgException("�û��������벻��ȷ");
        }
        return user;
    }
}
