package com.afsilvatech.whatsappclone.user;

import com.afsilvatech.whatsappclone.chat.ChatRepository;
import io.swagger.v3.oas.models.info.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> finAllUsersExceptSelf(Authentication connectedUser) {
        return userRepository.findAllUsersExceptSelf(connectedUser.getName())
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    public void favoriteChat(Authentication authentication, String chatId) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        user.addFavoriteChat(chatId);
        userRepository.save(user);
    }

    public User findUserById(String userId) {
        return userRepository.findById(userId);
    }
}