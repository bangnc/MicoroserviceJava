package com.bang.authservice.controller;

import com.bang.authservice.dto.request.ApiResponse;
import com.bang.authservice.dto.request.StudentRequest;
import com.bang.authservice.dto.response.StudentResponse;
import com.bang.authservice.service.imp.StudentService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class StudentController {
    StudentService studentService;

    @PostMapping
    public ApiResponse<StudentResponse> create(@RequestBody  @Valid StudentRequest request){
        var result = studentService.create(request);
        return ApiResponse.<StudentResponse>builder()
                .result(result)
                .build();
    }
}
