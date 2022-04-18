package rifqimuhammadaziz.bookinventory.service;

import com.github.javafaker.Faker;
import org.junit.Test;
import rifqimuhammadaziz.bookinventory.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestUserService {

    public UserDaoImpl userDao;
    private List<User> users;

    @Test
    public void testAddData() throws SQLException, IOException, ClassNotFoundException {
        User user = new User();
        userDao = new UserDaoImpl();

        Faker faker = new Faker();
        String username = faker.name().username();
        String fullName = faker.name().fullName();
        String address = faker.address().streetAddress();
        String phonenumber = faker.phoneNumber().cellPhone();

        Random r1 = new Random();
        List<String> listGender = new ArrayList();
        listGender.add("MALE");
        listGender.add("FEMALE");
        int randomGender = r1.nextInt(listGender.size());
        String gender = listGender.get(randomGender);

        Random r2 = new Random();
        List<String> listStatus = new ArrayList();
        listStatus.add("ACTIVE");
        listStatus.add("NON-ACTIVE");
        int randomitem = r2.nextInt(listStatus.size());
        String status = listStatus.get(randomitem);

        user.setUsername(username);
        user.setPassword(username);
        user.setFullName(fullName);
        user.setGender(gender);
        user.setAddress(address);
        user.setPhoneNumber(phonenumber);
        user.setStatus(status);
        System.out.println(user);

        userDao.addData(user);
    }
}
