package com.example.ip_backend.services.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.ip_backend.exceptions.NotFoundException;
import com.example.ip_backend.models.dto.Program;
import com.example.ip_backend.models.dto.User;
import com.example.ip_backend.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;



import com.example.ip_backend.services.EmailService;
import com.example.ip_backend.services.ProgramService;

@Slf4j
@Service
public class ScheduledEmailSend extends TimerTask {

    @Autowired
    private ProgramService programService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    public ScheduledEmailSend(ProgramService programService,UserService userService,EmailService emailService){
        this.emailService=emailService;
        this.programService=programService;
        this.userService=userService;
    }
//    public void sendDailyEmails() throws NotFoundException {
//        List<User> users=userService.findAll(User.class);
//        for(User user:users){
//            if(userService.isSubscribed(user.getId())){
//                Integer categoryid=userService.findCategoryIdByUserId(user.getId());
//                List<Program> programs= programService.findAllAddedLast24Hours(categoryid);
//                emailService.sendEmail(user.getMail(), "Novi programi za kategoriju na koju ste pretplaceni", generateEmailBody(programs));
//            }
//        }
//    }

    private String generateEmailBody(List<Program> programs) {
        StringBuilder sb = new StringBuilder();
        sb.append("Pronađeni su novi programi:\n\n");
        for (Program program : programs) {
            sb.append("Naziv programa: ").append(program.getName()).append("\n");
            sb.append("Opis: ").append(program.getDescription()).append("\n\n");
        }
        return sb.toString();
    }

    @Override
    public void run() {
        List<User> users= null;
        try {
            users = userService.findAll(User.class);
        } catch (Exception e){
            log.error("Izuzetak: ", e);
        }
        for(User user:users){
            if(userService.isSubscribed(user.getId())) {
                Integer categoryid = userService.findCategoryIdByUserId(user.getId());
                List<Program> programs = programService.findAllAddedLast24Hours(categoryid);
                if (!programs.isEmpty()) {
                    emailService.sendEmail(user.getMail(), "Novi programi za kategoriju na koju ste pretplaceni", generateEmailBody(programs));
                }

            }
        }

    }
    @Bean
    public Timer scheduledTaskTimer() {
        Timer timer = new Timer();


        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 18);
        date.set(Calendar.MINUTE, 42);
        date.set(Calendar.SECOND, 0);
        date.set(Calendar.MILLISECOND, 0);

        timer.schedule(this, date.getTime(), 1000 * 60 * 60 * 24); // 24 sata
        return timer;
    }
}
