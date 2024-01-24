package com.sumerge.test.course;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseService {

    private final CourseRepository repository;

    @Autowired
    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public List<Course> getCourses(String topicId) {
        return this.repository.findByTopicId(topicId);
    }

    public Course getCourse(Integer id) {
        return this.repository.findById(id).get();

    }

    public void addCourse(Course course) {
        this.repository.save(course);
    }

    public void updateCourse(Course course, Integer id) {
//        this.topics.stream().filter((elem)->elem.getId().equals(id)).findFirst().ifPresent((elem)->{
//            elem.setDescription(topic.getDescription());
//            elem.setName(topic.getName());
//            elem.setId(topic.getId());
//        });
        this.repository.save(course);
    }

    public void deleteCourse(Integer id) {
//      this.topics.removeIf((elem)->elem.getId().equals(id));
        this.repository.deleteById(id);
    }
}
