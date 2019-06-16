package com.showcase.mytube.controller;

import com.showcase.mytube.entity.UserDetailsEntity;
import com.showcase.mytube.service.spec.UserFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/user")
public class UserDetailsController {

    private static final int DEFAULT_FETCH_LIMIT = 10;

    @Autowired
    UserFetchService userFetchService;

    @GetMapping("/whoami")
    public ResponseEntity<UserDetailsEntity> getMyDetails(Principal principal) {
        UserDetailsEntity userDetails = userFetchService.getUserDetailsByName(principal.getName());
        return ResponseEntity.accepted().body(userDetails);
    }

    @GetMapping("")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<List> getUserList(@RequestParam(required = false) Integer limit,
                                            @RequestParam(required = false) Integer offset) {
        limit = Objects.isNull(limit) ? DEFAULT_FETCH_LIMIT : limit;
        offset = Objects.isNull(offset) ? 0 : offset;
        return ResponseEntity.accepted().body(userFetchService.getAllUser(limit, offset));
    }

}
