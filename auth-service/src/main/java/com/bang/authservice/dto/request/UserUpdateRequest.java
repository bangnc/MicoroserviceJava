package com.bang.authservice.dto.request;

import java.time.LocalDate;
import java.util.List;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
    List<String> roles;
}