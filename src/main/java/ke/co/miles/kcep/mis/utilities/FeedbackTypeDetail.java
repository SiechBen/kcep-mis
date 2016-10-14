/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.utilities;

/**
 *
 * @author siech
 */
public enum FeedbackTypeDetail {

    FEEDBACK(new Integer("157"), "Feedback"),
    SUCCESS_STORY(new Integer("158"), "Success story");

    private FeedbackTypeDetail(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static FeedbackTypeDetail getFeedbackTypeDetail(int id) {
        switch (id) {
            case 157:
                return FEEDBACK;
            case 158:
                return SUCCESS_STORY;
            default:
                return null;
        }
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    private final Integer id;
    private final String name;
}
