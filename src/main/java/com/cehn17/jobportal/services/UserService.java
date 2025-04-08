package com.cehn17.jobportal.services;

import com.cehn17.jobportal.entity.JobSeekerProfile;
import com.cehn17.jobportal.entity.RecruiterProfile;
import com.cehn17.jobportal.entity.User;
import com.cehn17.jobportal.repository.JobSeekerProfileRepository;
import com.cehn17.jobportal.repository.RecruiterProfileRepository;
import com.cehn17.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    public UserService(
            UserRepository userRepository,
            JobSeekerProfileRepository jobSeekerProfileRepository,
            RecruiterProfileRepository recruiterProfileRepository)
    {
        this.userRepository = userRepository;
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.recruiterProfileRepository = recruiterProfileRepository;
    }

    public User addNew(User user) {
        user.setActive(true);
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        User savedUser = userRepository.save(user);
        int userTypeId = user.getUserTypeId().getUserTypeId();

        if (userTypeId == 1) {
            recruiterProfileRepository.save(new RecruiterProfile(savedUser));
        }
        else {
            jobSeekerProfileRepository.save(new JobSeekerProfile(savedUser));
        }

        return savedUser;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
