package com.chatapp.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {
    @Id
    private String _id;
    private String username;
    private String email;
    private String password;
    private String roles;
}
