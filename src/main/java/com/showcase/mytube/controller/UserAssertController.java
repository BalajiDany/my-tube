package com.showcase.mytube.controller;


import com.showcase.mytube.entity.UserAssertEntity;
import com.showcase.mytube.service.spec.UserAssertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/v1/assert")
public class UserAssertController {

    private static final int DEFAULT_FETCH_LIMIT = 10;

    @Autowired
    private UserAssertService userAssertService;

    @GetMapping("")
    public ResponseEntity<List> getMyAssert(@RequestParam(required = false) Integer limit,
                                            @RequestParam(required = false) Integer offset,
                                            Principal principal) {
        return getMyAssert(principal.getName(), limit, offset);
    }

    @GetMapping("/{userName}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<List> getMyAssert(@PathVariable(required = false) String userName,
                                            @RequestParam(required = false) Integer limit,
                                            @RequestParam(required = false) Integer offset) {
        limit = Objects.isNull(limit) ? DEFAULT_FETCH_LIMIT : limit;
        offset = Objects.isNull(offset) ? 0 : offset;
        return ResponseEntity.accepted()
                .body(userAssertService.getByUserName(userName, limit, offset));
    }

    @PutMapping("/upload")
    public ResponseEntity<UserAssertEntity> upload(@RequestParam("file") MultipartFile file,
                                                   Principal principal) {
        return ResponseEntity.accepted()
                .body(userAssertService.uploadFile(file, principal.getName()));
    }

}
