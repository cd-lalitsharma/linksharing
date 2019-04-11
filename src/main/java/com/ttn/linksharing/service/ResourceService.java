package com.ttn.linksharing.service;

import com.ttn.linksharing.co.PostsCo;
import com.ttn.linksharing.entity.Posts;
import com.ttn.linksharing.entity.Resources;
import com.ttn.linksharing.entity.Topics;
import com.ttn.linksharing.entity.User;
import com.ttn.linksharing.enums.ResourceEnum;
import com.ttn.linksharing.repository.ResourceRepository;
import com.ttn.linksharing.repository.UserRepository;
import com.ttn.linksharing.service.impl.ResourceServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ResourceService implements ResourceServiceInterface {
    
    
    public static String documentUploadDir=System.getProperty("user.dir")+"/uploads/documents/";
    
    private static final Logger logger= LoggerFactory.getLogger(ResourceService.class);
    
    @Autowired
    ResourceRepository resourceRepository;
    
    @Autowired
    UserRepository userRepository;
    
    public Posts saveLinkPost(PostsCo postsCo,Integer userId){
        logger.info("inside save post function of resource service");
        
        
        //getting currentUser
        User user= userRepository.getUserById(userId);
        
        Resources resources = new Resources();
        if (postsCo.getType().equals("LINK")){
            logger.info("resource type is link");
    
            resources.setLocation(postsCo.getLocation());
            resources.setType(ResourceEnum.LINK.toString());
        }else{
            logger.info("resource type is document");
            resources.setLocation(this.uploadDocumentAndReturnPath());
            resources.setType(ResourceEnum.DOCUMENT.toString());
        }
    
        Posts post = new Posts();
        post.setDescription(postsCo.getPostDescription());
        post.settopic(postsCo.getTopic());
        post.setTitle(postsCo.getPostName());
        post.setresources(resources);
        post.setuser(user);
        
        logger.info("now saving post to resource table");
        
        Posts savedpost =resourceRepository.save(post);
        
        logger.info("returning saved post to posts controller");
    
        return savedpost;
    }
    
    private String uploadDocumentAndReturnPath() {
        
        
        
        return "";
    }
    
    
    public Posts saveDocumentPost(PostsCo postsCo,
                                  MultipartFile multipartFile,
                                  Integer userId) {
        String uploadedUrlOfDocument=null;
        
        
        Resources resource = new Resources();
        Posts post = new Posts();
        User user = userRepository.getUserById(userId);
        
        if (postsCo.getType().equals("DOCUMENT")){
            resource.setType(ResourceEnum.DOCUMENT.toString());
        }
        
        
        //uploading file
        try {
            byte[] bytes= multipartFile.getBytes();
            Path path= Paths.get(documentUploadDir+multipartFile.getOriginalFilename());
            Files.write(path,bytes);
    
            logger.info("saved path is "+path.toString());
            
            uploadedUrlOfDocument=path.toString();
    
            resource.setLocation(uploadedUrlOfDocument);
        
        } catch (IOException e) {
            logger.error("error while uploading file",e);
            e.printStackTrace();
        
        }
        logger.info("document uploaded successfully");
        logger.info("uploaded path is ==>> "+uploadedUrlOfDocument);
    
        post.setuser(user);
        post.setresources(resource);
        post.setTitle(postsCo.getPostName());
        post.setDescription(postsCo.getPostDescription());
        post.settopic(postsCo.getTopic());
        
        logger.info("saving post to posts table");
        resourceRepository.save(post);
        
        return post;
    }
    
}
