package com.showcase.mytube.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_file")
public class UserFileModel {


    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY )
    @Column(name = "video_id")
    private Integer videoId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

}
