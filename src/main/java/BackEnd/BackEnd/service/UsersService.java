package BackEnd.BackEnd.service;

import BackEnd.BackEnd.entity.UsersEntity;
import BackEnd.BackEnd.exception.BaseException;
import BackEnd.BackEnd.repository.UsersRepository;
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

    public void  createUser(String phone,String firstname,String fastname, String email, String password) throws BaseException {

        UsersEntity entity = new UsersEntity();
        entity.setEmail(email);
        entity.setPhone(phone);
        entity.setFirstname(firstname);
        entity.setLastname(fastname);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setCreate_date(new Date());
        entity.setUpdate_date(new Date());

        usersRepository.save(entity);
    }
    public boolean existsByUsername(String phone){
        boolean user = usersRepository.existsByPhone(phone);
        return  user;
    }

    public boolean mathpassword(String rawpassword, String endcodepassword){
        return passwordEncoder.matches(rawpassword,endcodepassword);
    }
}
