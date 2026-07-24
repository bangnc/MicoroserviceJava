package com.bang.authservice.service.imp;

import com.bang.authservice.dto.request.StudentRequest;
import com.bang.authservice.dto.response.StudentResponse;
import com.bang.authservice.entity.Address;
import com.bang.authservice.entity.Student;
import com.bang.authservice.mapper.AddressMapper;
import com.bang.authservice.mapper.StudentMapper;
import com.bang.authservice.repository.StudentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StudentService {
    StudentRepository studentRepository;
    StudentMapper studentMapper;
    AddressMapper addressMapper;

    public StudentResponse create(StudentRequest request){
        // mapper
        Student student = studentMapper.toStudent(request);
        Address address = addressMapper.toAddress(request.getAddress());

        student.setAddress(address);
        student = studentRepository.save(student);

        return studentMapper.toStudentResponse(student);

    }
}
