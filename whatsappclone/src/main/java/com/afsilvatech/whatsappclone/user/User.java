package com.afsilvatech.whatsappclone.user;

import com.afsilvatech.whatsappclone.chat.Chat;
import com.afsilvatech.whatsappclone.common.BaseAuditingEntity;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@NamedQuery(name = UserConstants.FIND_USER_BY_EMAIL,
            query = "SELECT u FROM User u WHERE u.email = :email"
)
@NamedQuery(name = UserConstants.FIND_ALL_USERS_EXCEPT_SELF,
            query = "SELECT u FROM User u WHERE u.id != :publicId")
@NamedQuery(name = UserConstants.FIND_USER_BY_PUBLIC_ID,
            query = "SELECT u FROM User u WHERE u.id = :publicId")
public class User extends BaseAuditingEntity {

    private static final int LAST_ACTIVATE_INTERVAL = 5;

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime lastSeen;

    @OneToMany(mappedBy = "sender")
    private List<Chat> chatsAsSender;

    @OneToMany(mappedBy = "recipient")
    private List<Chat> chatsAsRecipient;

    @Transient
    public boolean isUserOnline() {
        return lastSeen != null && lastSeen.isAfter(LocalDateTime.now().minusMinutes(LAST_ACTIVATE_INTERVAL));
    }

   @ElementCollection
   @CollectionTable(name = "user_favorite_chats", joinColumns = @JoinColumn(name = "user_id"))
   @Column(name = "chat_id")
   private List<String> favoriteChats = new ArrayList<>();
   
   @ElementCollection
   @CollectionTable(name = "user_blocked_users", joinColumns = @JoinColumn(name = "user_id"))
   @Column(name = "blocked_user_id")
   private List<String> blockedUsers = new ArrayList<>();

    public void addFavoriteChat(String chatId) {
        if (favoriteChats == null) {
            favoriteChats = new ArrayList<>();
        }
        if (!favoriteChats.contains(chatId)) {
            favoriteChats.add(chatId);
        }
        if(favoriteChats.contains(chatId)){
            favoriteChats.remove(chatId);
        }
    }

    public void addBlockedUser(String blockedUserId) {
        if (blockedUsers == null) {
            blockedUsers = List.of(blockedUserId);
        } else {
            blockedUsers.add(blockedUserId);
        }
    }

    public void removeBlockedUser(String blockedUserId) {
        if (blockedUsers != null) {
            blockedUsers.remove(blockedUserId);
        }
    }

}