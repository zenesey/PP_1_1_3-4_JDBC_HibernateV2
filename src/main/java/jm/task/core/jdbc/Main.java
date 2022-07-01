package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм
        UserService userDao = new UserServiceImpl();
        userDao.createUsersTable();

        userDao.saveUser("Name1", "LastName1", (byte) 20);
        userDao.saveUser("Name2", "LastName2", (byte) 25);
        userDao.saveUser("Name3chmo", "LastName3", (byte) 31);
        userDao.saveUser("Name4", "LastName4", (byte) 1);

        userDao.removeUserById(1);
        userDao.getAllUsers();
//        userDao.cleanUsersTable();
//        userDao.dropUsersTable();
    }
}
