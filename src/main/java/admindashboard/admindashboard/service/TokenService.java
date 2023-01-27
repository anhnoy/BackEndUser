package admindashboard.admindashboard.service;

import admindashboard.admindashboard.entity.UsersEntity;
import admindashboard.admindashboard.exception.BaseException;
import admindashboard.admindashboard.exception.UsersException;
import admindashboard.admindashboard.repository.UsersRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {

    private final UsersRepository usersRepository;

    @Value("${app.token.secret}")
    private String secret;

    @Value("${app.token.issuer}")
    private String issuer;

    public TokenService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public String tokenize(UsersEntity user) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, 24);
        Date expiresAt = calendar.getTime();

        return JWT.create()
                .withIssuer(issuer)
                .withClaim("userId", user.getId())
                .withClaim("role", "user")
                .withExpiresAt(expiresAt)
                .sign(algorithm());
    }

    public String tokenizeRefresh(UsersEntity user) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, 7);
        Date expiresAt = calendar.getTime();

        return JWT.create()
                .withIssuer(issuer)
                .withClaim("userId", user.getId().toString())
                .withExpiresAt(expiresAt)
                .sign(algorithm());
    }

    public DecodedJWT verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm())
                    .withIssuer(issuer)
                    .build();

            return verifier.verify(token);

        } catch (Exception e) {
            return null;
        }
    }

    private Algorithm algorithm() {
        return Algorithm.HMAC256(secret);
    }

    public UsersEntity getUserByIdToken() throws BaseException {

        Optional<UsersEntity> opt = usersRepository.findById(this.userId());
        if (opt.isEmpty()) {
            throw UsersException.userAlreadyExists();
        }
        UsersEntity user = opt.get();
        return user;
    }


    public String userId() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String userId = (String) authentication.getPrincipal();

        return userId;
    }

}
