package com.example.Sentiment_Analysis.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Sentiment_Analysis.Model.Submit;


public interface SubmitRepo extends JpaRepository<Submit, Long>{

}
