package com.afsilvatech.whatsappclone.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(Authentication authentication) {
        return ResponseEntity.ok(userService.finAllUsersExceptSelf(authentication));
    }

   @PostMapping("/{userId}/favorite-chats")
   public ResponseEntity<Void> favoriteChat(
           Authentication authentication,
           @PathVariable String userId,
           @RequestParam(name = "chat-id") String chatId) {
       if (!authentication.getName().equals(userService.findUserById(userId).getEmail())) {
           return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
       }
       userService.favoriteChat(authentication, chatId);
       return ResponseEntity.noContent().build();
   }
}