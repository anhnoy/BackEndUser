package admindashboard.admindashboard.service;

import admindashboard.admindashboard.entity.UsersEntity;
import admindashboard.admindashboard.exception.BaseException;
import admindashboard.admindashboard.repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void  createUser(String username, String email, String password) throws BaseException {

        UsersEntity entity = new UsersEntity();
        entity.setEmail(email);
        entity.setUsername(username);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setCreate_date(new Date());
        entity.setUpdate_date(new Date());

        usersRepository.save(entity);
    }
    public boolean existsByUsername(String username){
        boolean user = usersRepository.existsByUsername(username);
        return  user;
    }

    public boolean mathpassword(String rawpassword, String endcodepassword){
        return passwordEncoder.matches(rawpassword,endcodepassword);
    }
}
