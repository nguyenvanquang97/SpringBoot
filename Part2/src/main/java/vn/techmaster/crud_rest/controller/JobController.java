package vn.techmaster.crud_rest.controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.crud_rest.dto.JobRequest;

import vn.techmaster.crud_rest.model.Job;

@RestController
@RequestMapping("/job")
public class JobController {
  private ConcurrentHashMap<String, Job> job;
  public JobController() {
    job = new ConcurrentHashMap<>();
    job.put("OX-13",new Job("OX-13","Quang","Sinh viên","Hà Nội",1000,2000,"quang@gmail.com"));
    job.put("OX-14",new Job("OX-13","Huy","Sinh viên","Hà Nội",1500,2000,"huy@gmail.com"));
    job.put("OX-15",new Job("OX-13","Minh","Sinh viên","Hà Nội",1300,2000,"minh@gmail.com"));
    job.put("OX-16",new Job("OX-13","Phong","Sinh viên","Hà Nội",1400,2000,"phong@gmail.com"));
    job.put("OX-17",new Job("OX-13","Hiền","Sinh viên","Hà Nội",500,2000,"hien@gmail.com"));
    job.put("OX-18",new Job("OX-13","Dũng","Sinh viên","Hà Nội",700,2000,"dung@gmail.com"));
  }

  @GetMapping
  public List<Job> getJobs() {
    return job.values().stream().toList();
  }

  @PostMapping
  public Job createNewJob(@RequestBody JobRequest jobRequest) {
    String uuid = UUID.randomUUID().toString();
    Job newJob = new Job(uuid,jobRequest.title(), jobRequest.description(), jobRequest.location(),jobRequest.min_salary(),jobRequest.max_salary(),jobRequest.email_to());
    job.put(uuid, newJob);
    return newJob;
  }

  @GetMapping(value="/{id}")
  public Job getBookById(@PathVariable("id") String id) {
    return job.get(id);
  }

  @PutMapping(value="/{id}")
  public Job updateBookById(@PathVariable("id") String id, @RequestBody JobRequest jobRequest) {
    Job updateJob= new Job(id, jobRequest.title(), jobRequest.description(), jobRequest.location(),jobRequest.min_salary(),jobRequest.max_salary(),jobRequest.email_to());
    //books.put(id, updateBook);
    job.replace(id, updateJob);
    return updateJob;
  }

  @DeleteMapping(value="/{id}")
  public Job deleteBookById(@PathVariable("id") String id) {
    Job removeJob = job.remove(id);
    return removeJob;
  }
}
