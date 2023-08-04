package net.javaguides.springbootrestapi.controller;

import net.javaguides.springbootrestapi.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    // http://localhost:8080/student1
    @GetMapping("/student1")
    public Student getStudent(){

        Student student = new Student(1, "Patrique", "Jarry");

        return student;
    }

    // http://localhost:8080/student
    @GetMapping("/student")
    public ResponseEntity<Student> getStudentRE(){

        Student student = new Student(1, "Patrique", "Jarry");

        //return new ResponseEntity<>(student, HttpStatus.OK);
        // return ResponseEntity.ok(student);
        return ResponseEntity.ok()
                .header("custom-header", "Patrique")
                .body(student);
    }

    // http://localhost:8080/students
    @GetMapping
//    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents(){

        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Patrique", "Jarry"));
        students.add(new Student(2, "Michael", "Jackson"));
        students.add(new Student(3, "Chuck", "Barry"));
        students.add(new Student(4, "Phil", "Collins"));

        return ResponseEntity.ok(students);
    }

    // Sprint BOOT REST API with Path Variable
    // {id} - URI Template Variable
    // http://localhost:8080/students/1/Patrique/Jarry
    @GetMapping("/{id}/{first-name}/{last-name}")
    // @GetMapping("/students/{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> getStudentsPathVariable(
            @PathVariable("id") int studentId,
            @PathVariable("first-name") String studentFirstName,
            @PathVariable("last-name") String studentLastName){

        return ResponseEntity.ok(new Student(studentId, studentFirstName, studentLastName));
    }

    // Sprint BOOT REST API with Request Param
    // {id} - URI Template Variable
    // http://localhost:8080/students/rp?id=1&firstName=Patrique&lastName=Jarry
    @GetMapping("/rp")
    //@GetMapping("/students/rp")
    public ResponseEntity<Student> getStudentsRequestParam(
            @RequestParam int id,
            @RequestParam String firstName,
            @RequestParam(required = false) String lastName){

        return ResponseEntity.ok(new Student(id, firstName, lastName));
    }

    @PostMapping
    //@PostMapping("/students")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> createStudent(@RequestBody Student student){

        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    //@PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){

        student.setId(studentId);

        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    // @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId){

        return ResponseEntity.ok("Student " + studentId + " deleted successfully !");
    }


}
