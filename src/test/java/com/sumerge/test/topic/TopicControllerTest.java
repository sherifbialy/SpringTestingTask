package com.sumerge.test.topic;


import com.sumerge.test.util.NotFoundException;
import jakarta.servlet.ServletException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TopicController.class)
@RunWith(SpringRunner.class)
@Import(TopicService.class)
public class TopicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TopicService topicService;

    @MockBean
    private TopicRepository topicRepository;
//.none() deprecated in favor of assertThrows, unclear how to use expectedException
//    @Rule
//    public final ExpectedException thrown=ExpectedException.none()
@Rule
public Timeout globalTimeout = Timeout.seconds(10);

    @Test
    public void getAllTopics() throws Exception {
        var topic = new Topic("flutter", "flutter", "Placeholder");
        Mockito.when(topicRepository.findAll()).thenReturn(List.of(topic));

        mockMvc.perform(get("/topics")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("flutter")));
    }
    @Test
    public void getTopicByIdNotFound(){
        Mockito.when(topicRepository.findById("swift")).thenThrow(NullPointerException.class);

        NullPointerException exception=assertThrows(NullPointerException.class,()-> {
            topicRepository.findById("swift");
        });

        assertEquals(NullPointerException.class, exception.getClass());

    }

    //This fails to show the timeout rule works
    @Test
    public void fakeCallToApiTimeout() throws InterruptedException {
        var topic = new Topic("flutter", "flutter", "Placeholder");
        Mockito.when(topicRepository.findAll()).thenReturn(List.of(topic));

        TimeUnit.SECONDS.sleep(11);
        topicRepository.findAll();







    }
}