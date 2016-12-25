package cn.edu.nju.dislab.moodexp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhantong on 2016/12/25.
 */

public class Survey implements Serializable {
    @SerializedName("intro_message")
    private String introMessage;

    @SerializedName("finish_message")
    private String finishMessage;

    @SerializedName("questions")
    private List<Question> questions;

    List<Question> getQuestions(){
        return questions;
    }
    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append("Survey:\n");
        builder.append("intro_message: "+introMessage+"\n");
        builder.append("finish_message: "+finishMessage+"\n");
        if(questions!=null) {
            builder.append("questions:\n--------\n");
            builder.append(questions.toString() + "\n");
            builder.append("--------\n");
        }
        return builder.toString();
    }
}