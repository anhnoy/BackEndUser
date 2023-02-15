package admindashboard.admindashboard.business;

import admindashboard.admindashboard.entity.UsersEntity;
import admindashboard.admindashboard.exception.BaseException;
import admindashboard.admindashboard.exception.UsersException;
import admindashboard.admindashboard.model.Response;
import admindashboard.admindashboard.model.usersModel.Login;
import admindashboard.admindashboard.model.usersModel.UsersRegister;
import admindashboard.admindashboard.repository.UsersRepository;
import admindashboard.admindashboard.service.TokenService;
import admindashboard.admindashboard.service.UsersService;
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
        if (usersService.existsByUsername(request.getUsername())){
           throw UsersException.userAlreadyExists();
        }
        usersService.createUser(request.getUsername(),request.getEmail(),request.getPassword());
        return new Response().success("success");
    }

    public Object login(Login request) throws UsersException {
        Optional<UsersEntity> byUsername = usersRepository.findByUsername(request.getUsername());
        if (byUsername.isEmpty()){
            throw UsersException.userAlreadyExists();
        }
        UsersEntity user = byUsername.get();
        String tokenize = tokenService.tokenize(user);
        if (!usersService.mathpassword(request.getPassword(),user.getPassword())){
            throw UsersException.passwordNotmatch();
        }
        return new Response().ok("Ok","success",tokenize);
    }

    public Object userProfile() throws BaseException {
        UsersEntity userByIdToken = tokenService.getUserByIdToken();
        System.out.println(userByIdToken);
        Optional<UsersEntity> byUsername = usersRepository.findByUsername(userByIdToken.getUsername());
        if (byUsername.isEmpty()){
            throw UsersException.emailAlreadyExists();
        }
        UsersEntity user = byUsername.get();
        return new Response().ok("ok","user",user);

    }

}
