package com.cehn17.jobportal.services;

import com.cehn17.jobportal.entity.JobSeekerProfile;
import com.cehn17.jobportal.entity.User;
import com.cehn17.jobportal.repository.JobSeekerProfileRepository;
import com.cehn17.jobportal.repository.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobSeekerProfileService {

    private final JobSeekerProfileRepository jobSeekerProfileRepository;
    private final UserRepository userRepository;

    public JobSeekerProfileService(JobSeekerProfileRepository jobSeekerProfileRepository, UserRepository userRepository) {
        this.jobSeekerProfileRepository = jobSeekerProfileRepository;
        this.userRepository = userRepository;
    }

    public Optional<JobSeekerProfile> getOne(Integer id) {
        return jobSeekerProfileRepository.findById(id);
    }

    public JobSeekerProfile addNew(JobSeekerProfile jobSeekerProfile) {
        return jobSeekerProfileRepository.save(jobSeekerProfile);
    }

    public JobSeekerProfile getCurrentSeekerProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            User users = userRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            Optional<JobSeekerProfile> seekerProfile = getOne(users.getUserId());
            return seekerProfile.orElse(null);
        } else return null;

    }
}