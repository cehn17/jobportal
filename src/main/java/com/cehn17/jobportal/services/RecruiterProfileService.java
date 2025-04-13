package com.cehn17.jobportal.services;

import com.cehn17.jobportal.entity.RecruiterProfile;
import com.cehn17.jobportal.entity.User;
import com.cehn17.jobportal.repository.RecruiterProfileRepository;
import com.cehn17.jobportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruiterProfileService {

    private final RecruiterProfileRepository recruiterRepository;
    private final UserRepository userRepository;

    @Autowired
    public RecruiterProfileService(RecruiterProfileRepository recruiterRepository, UserRepository userRepository) {
        this.recruiterRepository = recruiterRepository;
        this.userRepository = userRepository;
    }

    public Optional<RecruiterProfile> getOne(Integer id) {
        return recruiterRepository.findById(id);
    }

    public RecruiterProfile addNew(RecruiterProfile recruiterProfile) {
        return recruiterRepository.save(recruiterProfile);
    }

    public RecruiterProfile getCurrentRecruiterProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            User user = userRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            Optional<RecruiterProfile> recruiterProfile = getOne(user.getUserId());
            return recruiterProfile.orElse(null);
        } else return null;
    }
}
