package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String addComments(@PathVariable("imageId") int imageId, @PathVariable("imageTitle") String title,
                              @RequestParam("comment") String comment, Model model, HttpSession session) {

        Image image = imageService.getImage(imageId);
        User user = (User) session.getAttribute("loggeduser");

        Comment newComment = new Comment();
        newComment.setCreatedDate(LocalDate.now());
        newComment.setText(comment);
        newComment.setImage(image);
        newComment.setUser(user);

        commentService.createComment(newComment);

        List<Comment> commentList = image.getComments();
        model.addAttribute("image", image);
        model.addAttribute("comments", commentList);
        return "redirect:/images/" + imageId + "/" + title;
    }
}