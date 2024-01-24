package com.sumerge.test.course;


import com.sumerge.test.topic.Topic;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Entity
@Table(
        name = "course"
)
public class Course
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY
    )

    private Long id;
    private String name;
    private String description;
    @ManyToOne
    private Topic topic;

    public Course(String name, String description, String topicId){
        this.name=name;
        this.description=description;
        this.topic=new Topic(topicId,"","");
    }

    public Course() {
    }

}
