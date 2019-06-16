package com.showcase.mytube.controller;

import com.showcase.mytube.entity.UserDetailsEntity;
import com.showcase.mytube.service.spec.UserFetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;

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

    @GetMapping("/{limit}/{offset}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<List> getUserList(@PathVariable(required = false) int limit,
                                            @PathVariable(required = false) int offset) {
        return ResponseEntity.accepted().body(userFetchService.getAllUser(limit, offset));
    }

    @GetMapping("/{limit}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<List> getUserList(@PathVariable(required = false) int limit) {
        return getUserList(limit, 0);
    }

    @GetMapping("")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<List> getUserList() {
        return getUserList(DEFAULT_FETCH_LIMIT, 0);
    }

}
