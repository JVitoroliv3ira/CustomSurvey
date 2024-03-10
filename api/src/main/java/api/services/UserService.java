package api.services;

import api.exceptions.ApiException;
import api.helpers.EncoderHelper;
import api.models.User;
import api.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final EncoderHelper encoderHelper;

    public User register(User entity) {
        this.validateEmailUniqueness(entity.getEmail());
        this.encodeUserPassword(entity);
        return this.create(entity);
    }

    private void validateEmailUniqueness(String email) {
        if (this.userRepository.existsByEmailIgnoreCase(email)) {
            throw new ApiException(
                    "O email informado está sendo utilizado por outra conta.",
                    "email",
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    private void encodeUserPassword(User entity) {
        entity.setPassword(this.encoderHelper.encode(entity.getPassword()));
    }

    private User create(User entity) {
        entity.setId(null);
        return this.userRepository.save(entity);
    }
}
