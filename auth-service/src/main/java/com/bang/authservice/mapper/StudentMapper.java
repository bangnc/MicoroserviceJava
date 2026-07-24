package com.bang.authservice.mapper;

import com.bang.authservice.dto.request.StudentRequest;
import com.bang.authservice.dto.response.StudentResponse;
import com.bang.authservice.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    Student toStudent(StudentRequest request);
    StudentResponse toStudentResponse(Student student);
}
