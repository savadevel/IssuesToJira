package ru.savadevel.issuestojira.web;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class JiraDto {

    private String url;
    private String username;
    private String password;
    private MultipartFile file;
}
