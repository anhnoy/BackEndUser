package BackEnd.BackEnd.business;

import BackEnd.BackEnd.entity.UsersEntity;
import BackEnd.BackEnd.exception.UsersException;
import BackEnd.BackEnd.model.usersModel.UsersRegister;
import BackEnd.BackEnd.repository.UsersRepository;
import BackEnd.BackEnd.service.TokenService;
import BackEnd.BackEnd.service.UsersService;
import BackEnd.BackEnd.exception.BaseException;
import BackEnd.BackEnd.model.Response;
import BackEnd.BackEnd.model.usersModel.Login;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersBusiness {

    private final UsersService usersService;
    private final TokenService tokenService;
    private final UsersRepository usersRepository;
    public UsersBusiness(UsersService usersService, TokenService tokenService, UsersRepository usersRepository) {
        this.usersService = usersService;
        this.tokenService = tokenService;
        this.usersRepository = usersRepository;
    }

    public Object register(UsersRegister request) throws BaseException {
        if (usersService.existsByUsername(request.getPhone())){
           throw UsersException.userAlreadyExists();
        }
        usersService.createUser(request.getPhone(),request.getFirstname(),request.getLastname(),request.getEmail(),request.getPassword());
        return new Response().success("success");
    }

    public Object login(Login request) throws UsersException {

        System.out.println(request.getPhone());
        Optional<UsersEntity> byPhone = usersRepository.findByPhoneOrEmail(request.getPhone(), request.getPhone());
        System.out.println(byPhone);
        if (byPhone.isEmpty()){
            throw UsersException.loginFail();
        }
        UsersEntity user = byPhone.get();
        String tokenize = tokenService.tokenize(user);
        if (!usersService.mathpassword(request.getPassword(),user.getPassword())){
            throw UsersException.passwordNotmatch();
        }
        return new Response().ok("Ok","success",tokenize);
    }

    public Object userProfile() throws BaseException {
        UsersEntity userByIdToken = tokenService.getUserByIdToken();
        System.out.println(userByIdToken);
        Optional<UsersEntity> byUsername = usersRepository.findByPhoneOrEmail(userByIdToken.getPhone(),userByIdToken.getPhone());
        if (byUsername.isEmpty()){
            throw UsersException.emailAlreadyExists();
        }
        UsersEntity user = byUsername.get();
        return new Response().ok("ok","user",user);

    }

}
