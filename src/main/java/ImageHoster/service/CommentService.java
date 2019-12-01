package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.model.Tag;
import ImageHoster.repository.CommentRepository;
import ImageHoster.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public Comment createComment(Comment comment) {
        return commentRepository.createComment(comment);
    }
}
