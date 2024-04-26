package com.example.Sentiment_Analysis.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Submit {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String cluster;
	private String date;
	private String feedbacks;
	private String positive_feedback;
	private String negative_feedback;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCluster() {
		return cluster;
	}
	public void setCluster(String cluster) {
		this.cluster = cluster;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFeedbacks() {
		return feedbacks;
	}
	public void setFeedbacks(String feedbacks) {
		this.feedbacks = feedbacks;
	}
	public String getPositive_feedback() {
		return positive_feedback;
	}
	public void setPositive_feedback(String positive_feedback) {
		this.positive_feedback = positive_feedback;
	}
	public String getNegative_feedback() {
		return negative_feedback;
	}
	public void setNegative_feedback(String negative_feedback) {
		this.negative_feedback = negative_feedback;
	}
	
}
