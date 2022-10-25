package com.example.kiemtra;

import com.example.kiemtra.entity.Course;
import com.example.kiemtra.entity.Topic;
import com.example.kiemtra.entity.User;
import com.example.kiemtra.repository.CourseRepository;
import com.example.kiemtra.repository.TopicRepository;
import com.example.kiemtra.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class KiemTraApplicationTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    @Rollback(value = false)
    void save_user() {

        User user = User.builder()
                .name("Nguyễn Văn A")
                .email("a@gmail.com")
                .phone("0988158777")
                .build();

        User user1 = User.builder()
                .name("Tran Văn B")
                .email("b@gmail.com")
                .phone("0989995297")
                .build();

        User user2 = User.builder()
                .name("Nguyễn Văn C")
                .email("c@gmail.com")
                .phone("0988887777")
                .build();

        userRepository.saveAll(List.of(user, user1, user2));
    }
    @Test
    @Rollback(value = false)
    void save_topic(){
        Faker faker = new Faker();

        for (int i = 0; i < 30; i++) {
            Topic topic = Topic.builder()
                    .name(faker.name().fullName())

                    .build();

            topicRepository.save(topic);
        }

    }
    @Test
    @Rollback(value = false)
    void save_course(){
        courseRepository.deleteAll();
        List<User> list = userRepository.findAll();
        List<Topic> topicList = topicRepository.findAll();
        Faker faker = new Faker();
        Random rd = new Random();
        List<String> types= Arrays.asList("online","onlab");
        for (int i = 0; i < 10; i++) {
            User rduser = list.get(rd.nextInt(list.size()));

            List<Topic> myList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Topic rdtopic = topicList.get(rd.nextInt(topicList.size()));
                if(!myList.contains(rdtopic)){
                    myList.add(rdtopic);
                }
            }
            Course course = Course.builder()
                    .name(faker.company().name())
                    .description(faker.lorem().fixedString(20))
                    .type(types.get(rd.nextInt(types.size())))
                    .user(rduser)
                    .topics(myList)
                    .build();
            courseRepository.save(course);
        }
    }
}
