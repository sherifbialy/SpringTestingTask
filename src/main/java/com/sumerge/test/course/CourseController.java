package com.sumerge.test.course;

import com.sumerge.test.topic.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {



    private final CourseService courseService;
//    @Qualifier("courseService")
    @Autowired
    public CourseController( CourseService service){
        this.courseService=service;
    }
    @RequestMapping("/topics/{id}/courses")
    public List<Course> getAllCourses(@PathVariable String id){
       return this.courseService.getCourses(id);
    }
    @RequestMapping("/topics/{id}/courses/{courseId}")
    public Course getCourse(@PathVariable Integer courseId){
        return courseService.getCourse(courseId);
    }


    @RequestMapping(value = "/topics/{id}/courses",method = RequestMethod.POST)
    public void addCourse(@RequestBody Course course,@PathVariable String id){
        course.setTopic(new Topic(id,"",""));
        courseService.addCourse(course);

    }
    @RequestMapping(value = "/topics/{id}/courses/{courseId}",method = RequestMethod.PUT)
    public void updateTopic(@RequestBody Course c, @PathVariable Integer courseId, @PathVariable String id){
        c.setTopic(new Topic(id,"",""));
        courseService.updateCourse(c,courseId);

    }
    @RequestMapping(value = "/topics/{id}/courses/{courseId}",method = RequestMethod.DELETE)
    public void deleteTopic(@PathVariable Integer courseId){
        courseService.deleteCourse(courseId);

    }
}
