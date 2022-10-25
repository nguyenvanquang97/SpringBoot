package com.example.kiemtra.service;

import com.example.kiemtra.entity.Course;
import com.example.kiemtra.entity.Topic;
import com.example.kiemtra.entity.User;
import com.example.kiemtra.exeption.BadRequestException;
import com.example.kiemtra.exeption.NotFoundException;
import com.example.kiemtra.repository.CourseRepository;
import com.example.kiemtra.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TopicRepository topicRepository;

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getCoursesByType(String type) {
        List<Course> courses = courseRepository.findByType(type);
            if (courses.isEmpty()) {
                throw new BadRequestException("không có khóa học:" + type);
            }
        return courses;
    }

    public List<Course> getCoursesByNameAndTopic(String name, String topic) {

        if (topic == null && name != null) {
            List<Course> courses=courseRepository.findByNameContains(name);
            if (courses.isEmpty()) {
                throw new NotFoundException("không có khóa học tên:" + name);
            }
            return courses;
        }
        if (name == null && topic != null) {
            List<Topic> topics = topicRepository.findByNameContains(topic);
            List<Course> courses=new ArrayList<>();
            if (topics.isEmpty()){
                throw new NotFoundException("không có topic tên:" + topic);
            }
            for (Topic t :topics){
               courses.addAll(courseRepository.findByTopics_Id(t.getId()));
            }
            if (courses.isEmpty()){
                throw new BadRequestException("không có khóa học nào chứa topic tên:"+topic);
            }
            return courses;
        }
        List<Course> courses = courseRepository.findByNameContains(name);
        List<Topic> topic1 = topicRepository.findByNameContains(topic);
        List<Course> rs = new ArrayList<>();
        for (Course course : courses) {
            if (course.getTopics().contains(topic1)) ;
            rs.add(course);
        }
        if (rs.isEmpty()){
            throw new NotFoundException("không có khóa học nào có tên '"+name+"' và chứa topic tên '"+topic+"'");
        }
        return rs;

    }

    public Course getCoursesById(Integer id) {
        Optional<Course> course=courseRepository.findById(id);
        if (course.isEmpty()){
            throw new NotFoundException("không tìm thấy khóa học có id:"+id);
        }
        return course.get();
    }
}
