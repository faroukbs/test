package com.roky.thunderspi.services;

import java.util.List;
import java.util.Set;

import com.roky.thunderspi.controllers.VonageController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roky.thunderspi.entities.Comment;
import com.roky.thunderspi.entities.Post;
import com.roky.thunderspi.entities.User;
import com.roky.thunderspi.repositories.CommentRepo;
import com.roky.thunderspi.repositories.PostRepo;
import com.roky.thunderspi.repositories.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;


@Service
public class CommentServiceImpl implements ICommentService{
    @Autowired
    CommentRepo cmtRepo;
    @Autowired
    PostRepo postrepo;
    @Autowired
    UserRepo userrepo;
    @Override
    public List<Comment> retrieveAllCommentaires() {
        return cmtRepo.findAll();
    }

    @Override
    public Comment addComment(Comment comment, Long postId,Long iduser) {
        final Logger logger = LoggerFactory.getLogger(VonageController.class);
        cmtRepo.save(comment);
        Post p =postrepo.findById(postId).orElse(null);
        User u = userrepo.findById(iduser).orElse(null);
        comment.setPost(p);
        comment.setUser(u);
        VonageClient client = VonageClient.builder().apiKey("63efceb8").apiSecret("fEo9rEXI97rrRdoH").build();

	/*	TextMessage message = new TextMessage("Comment Notification", "+21695540093",
				"Dear Ghofrane,\n" +
						"\n" +
						"You have a new comment on your post .' \n" +p.getTitle()+
						"' \n" +
						"Please visit our application to see more");

		SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

		if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
			logger.info("Message sent successfully.");
		} else {
			logger.info("Message failed with error: " + response.getMessages().get(0).getErrorText());
		}
*/
        return cmtRepo.save(comment);

    }

    @Override
    public void deleteCommentaire(Long id) {

        cmtRepo.deleteById(id);
    }

    @Override
    public Comment updateCommentaire(Comment cmt,Long cmtid) {
        Comment c =cmtRepo.findById(cmtid).orElse(null);
        c.setContent(cmt.getContent());
        return cmtRepo.save(c);
    }

    @Override
    public Comment retrieveCommentaire(Long id) {
        return cmtRepo.findById(id).orElse(null);
    }

    @Override
    public Set<Comment> getcmtbypost(Long idpost) {
        Post p = postrepo.findById(idpost).orElse(null);
        return p.getComment();
    }


}
