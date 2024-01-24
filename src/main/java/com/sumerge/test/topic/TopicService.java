package com.sumerge.test.topic;


import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TopicService {

    private final TopicRepository repository;

    public TopicService(TopicRepository repository) {
        this.repository = repository;
    }

    public List<Topic> getTopics() {
        return this.repository.findAll();
    }

    public Topic getTopic(String id) {
        return this.repository.findById(id).get();

    }

    public void addTopic(Topic topic) {
        this.repository.save(topic);
    }

    public void updateTopic(Topic topic, String id) {
//        this.topics.stream().filter((elem)->elem.getId().equals(id)).findFirst().ifPresent((elem)->{
//            elem.setDescription(topic.getDescription());
//            elem.setName(topic.getName());
//            elem.setId(topic.getId());
//        });
        this.repository.save(topic);
    }

    public void deleteTopic(String id) {
//      this.topics.removeIf((elem)->elem.getId().equals(id));
        this.repository.deleteById(id);
    }
}


