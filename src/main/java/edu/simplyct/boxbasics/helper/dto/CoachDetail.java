package edu.simplyct.boxbasics.helper.dto;

import edu.simplyct.boxbasics.model.Coach;
import lombok.Data;

/**
 * Created by cyril on 6/30/15.
 */
@Data
public class CoachDetail {
    Long   coachId;
    String firstName;
    String lastName;
    String email;
    String intro;
    String jobTitle;

    public CoachDetail(Coach coach) {
        this.coachId = coach.getId();
        this.firstName = coach.getUser().getFirstName();
        this.lastName = coach.getUser().getLastName();
        this.email = coach.getUser().getEmail();
        this.intro = coach.getDescription();
        this.jobTitle = coach.getJobTitle();
    }
}
