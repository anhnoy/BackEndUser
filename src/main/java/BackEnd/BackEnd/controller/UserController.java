package BackEnd.BackEnd.controller;


import BackEnd.BackEnd.entity.UsersEntity;
import BackEnd.BackEnd.model.usersModel.UsersRegister;
import BackEnd.BackEnd.repository.UsersRepository;
import BackEnd.BackEnd.service.TokenService;
import BackEnd.BackEnd.business.UsersBusiness;
import BackEnd.BackEnd.exception.BaseException;
import BackEnd.BackEnd.exception.UsersException;
import BackEnd.BackEnd.model.usersModel.Login;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UsersRepository usersRepository;
    private final UsersBusiness usersBusiness;
    private final TokenService tokenService;

    public UserController(UsersRepository usersRepository, UsersBusiness usersBusiness, TokenService tokenService) {
        this.usersRepository = usersRepository;
        this.usersBusiness = usersBusiness;
        this.tokenService = tokenService;
    }
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UsersRegister request)throws BaseException {
        Object response = usersBusiness.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<Object>login(@RequestBody Login request) throws UsersException {
        Object response = usersBusiness.login(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/profile")
    public ResponseEntity<Object>getProfile() throws BaseException {
        Object response = usersBusiness.userProfile();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/allusers")
    public ResponseEntity<Object> all(){
        List<UsersEntity> all = usersRepository.findAll();
        return ResponseEntity.ok(all);
    }

//    @GetMapping("/test")
//    public List<Map<String, Object>> test(){
//            Map<String,Object> map = new HashMap<>();
//            map.put("KKKK",true);
////        return map;
//        return usersRepository.test();
//    }
}
